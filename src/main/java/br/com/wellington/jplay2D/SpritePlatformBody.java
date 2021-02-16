package br.com.wellington.jplay2D;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

public class SpritePlatformBody extends SpritePlatform {

	/**  */
	protected double mass = 0.5;

	/**  */
	protected double friction = 0.5;

	/**  */
	protected double restitution = 0.5;

	/** */
	protected Body body = null; // Usado com a classe Physics

	public SpritePlatformBody(String name, int frames, boolean loop) {
		super(name, frames, loop);
	}

	/**
	 * Calcula a aceleração do objeto.
	 * 
	 * @param velocity Velocidade base do objeto.
	 * @return
	 */
	private float velocityMeter(double velocity) {
		return (float) (velocity * 3 + 10);
	}

	/**
	 * Retorna um Vec2 da posição atual da sprite.
	 */
	private Vec2 getAtualPosition() {
		return new Vec2(body.getPosition().x, body.getPosition().y);
	}

	/**
	 * Ajusta a velocidade do eixo Y.
	 * 
	 * @param velocity a velocidade.
	 * @return a velocidade ajustada no eixo y.
	 */
	private float calcVelAxisY(double velocity) {
		return (float) ((velocity) + (body.getMass() * Physics.GRAVITY));
	}

	/**
	 * Aplicando uma for�a para cima usando o teclado.
	 * 
	 * @param velocity A velocidade de deslocamento.
	 */
	public void applyForceYFromKeyboardUp(double velocity) {
		body.applyForce(new Vec2(0, calcVelAxisY(velocity)), getAtualPosition());
	}

	/**
	 * aplicando uma for�a para baixo usando o teclado.
	 * 
	 * applying a down force by using the keyboard.
	 * 
	 * @param velocity
	 */
	public void applyForceYFromKeyboardDown(double velocity) {
		body.applyForce(new Vec2(0, -calcVelAxisY(velocity)), getAtualPosition());
	}

	/**
	 * Aplica uma for�a para cima ou para baixo usando o teclado.
	 * 
	 * @param upKey
	 * @param downKey
	 * @param velocity
	 * @param boundsScreen sets the limits in which the body can move.(the bounds
	 *                     are between 0 and window height)
	 */
	public void applyForceYFromKeyboard(boolean upKey, boolean downKey, double velocity, boolean boundsScreen) {
		double py = Physics.toPixelsY(body.getPosition().y);
		double centerSpriteY = height / 2;
		if (boundsScreen) {
			if (upKey && py - centerSpriteY > 0)
				body.applyForce(new Vec2(0, calcVelAxisY(velocity)), getAtualPosition());
			else if (py - centerSpriteY < 0) {
				cancelForces();
				setY(0);
			}

			if (downKey && py + centerSpriteY < Window.getInstance().getHeight())
				body.applyForce(new Vec2(0, -calcVelAxisY(velocity)), getAtualPosition());
			else if (py + centerSpriteY > Window.getInstance().getHeight()) {
				cancelForces();
				setY(Window.getInstance().getHeight() - height);
			}

		} else {
			if (upKey)
				body.applyForce(new Vec2(0, calcVelAxisY(velocity)), getAtualPosition());
			if (downKey)
				body.applyForce(new Vec2(0, -calcVelAxisY(velocity)), getAtualPosition());
		}
	}

	/**
	 * Aplica uma for�a a esquerda usando o teclado.
	 * 
	 * 
	 * @param forceLeft A for�a aplicada.
	 */
	public void applyForceXFromKeyboardLeft(double forceLeft) {
		body.applyForce(new Vec2(-velocityMeter(forceLeft), 0), getAtualPosition());
	}

	/**
	 * applies a force to the right using the keyboard
	 * 
	 * @param forceRight A for�a aplicada.
	 */
	public void applyForceXFromKeyboardRight(double forceRight) {
		body.applyForce(new Vec2(velocityMeter(forceRight), 0), getAtualPosition());
	}

	/**
	 * Configura uma nova posi��o no corpo da sprite.
	 * 
	 * @param x O ponto do eixo x.
	 * @param y O ponto do eixo y
	 */
	private void setBodyVec2(double x, double y) {
		float newX = (float) Physics.toMeterX(x + width / 2);
		float newY = (float) Physics.toMeterY(y + height / 2);
		body.setXForm(new Vec2(newX, newY), (float) rotation);
	}

	/** Cancela todas as for�as aplicadas no corpo. */
	public void cancelForces() {
		body.setLinearVelocity(new Vec2(0, 0));
	}

	/**
	 * applies a force to the left or to the right using the keyboard
	 * 
	 * @param leftKey
	 * @param rightKey
	 * @param velocity
	 * @param behaviorKeyboard
	 * @param boundsScreen     sets the limits in which the body can move.(the
	 *                         bounds are between 0 and window width)
	 */
	public void applyForceXFromKeyboard(boolean leftKey, boolean rightKey, double velocity, boolean boundsScreen) {
		double px = Physics.toPixelsX(body.getPosition().x);
		float velMeter = -velocityMeter(velocity);
		final double centroSpriteX = width / 2;

		if (boundsScreen) {
			if (leftKey && px - centroSpriteX > 0)
				body.applyForce(new Vec2(velMeter, 0), getAtualPosition());
			else if (px - centroSpriteX < 0) {
				cancelForces();
				setX(0);
			}
			if (rightKey && px + centroSpriteX < Window.getInstance().getWidth())
				body.applyForce(new Vec2(-velMeter, 0), getAtualPosition());
			else if (px + centroSpriteX > Window.getInstance().getWidth()) {
				cancelForces();
				setX(Window.getInstance().getWidth() - width);
			}
		} else {
			if (leftKey)
				body.applyForce(new Vec2(velMeter, 0), getAtualPosition());
			else if (px - centroSpriteX < 0)
				setX(0);
			if (rightKey)
				body.applyForce(new Vec2(-velMeter, 0), getAtualPosition());
			else if (px + centroSpriteX > Window.getInstance().getWidth())
				setX(Window.getInstance().getWidth() - width);
		}
	}

	/**
	 * Aplica uma for�a no eixo x do corpo.
	 * 
	 * @param fx A for�a aplicada no eixo x.
	 */
	public void applyForceX(double fx) {
		body.applyForce(new Vec2((float) (fx), 0), getAtualPosition());
	}

	/**
	 * Aplica uma for�a no eixo y do corpo.
	 * 
	 * @param fy A for�a aplicada no eixo y.
	 */
	public void applyForceY(double fy) {
		body.applyForce(new Vec2(0, (float) (fy)), getAtualPosition());
	}

	/**
	 * Cancels all forces applied to the body and sets limits on x-axis in which the
	 * body can move
	 * 
	 * @param value
	 * @param lowerBoundX
	 */
	public void cancelForcesAndSetBoundsX(double value, boolean lowerBoundX) {
		cancelForces();
		if (lowerBoundX) {
			if (x < value) {
				setX(value);
			} else if (x + width > value) {
				setX(value - width);
			}
		}
	}

	/**
	 * Cancels all forces applied to the body and sets limits on y-axis in which the
	 * body can move
	 * 
	 * @param value
	 * @param lowerBoundY
	 */
	public void cancelForcesAndSetBoundsY(double value, boolean lowerBoundY) {
		cancelForces();
		if (lowerBoundY) {
			if (y < value)
				setY(value);
		} else if (y + height > value)
			setY(value - height);
	}

	/**
	 * Sets all the attributes.
	 * 
	 * @param mass
	 * @param friction
	 * @param restituion
	 * @param rotation
	 */
	public void setAllAttributes(double mass, double friction, double restituion, double rotation) {
		this.mass = mass;
		this.friction = friction;
		this.restitution = restituion;
		this.rotation = rotation;
	}

	/**
	 * Sets the sprite like a bullet to prevent it being ignored collisions with
	 * other sprites.
	 * 
	 * @param value
	 */
	public void setBullet(boolean value) {
		if (value) {
			body.setBullet(true);
		}
	}

	/**
	 * Sets the value for restitution.
	 * 
	 * @param restitution
	 */
	public void setRestitution(double restitution) {
		this.restitution = restitution;
	}

	/**
	 * Sets the value for rotation.
	 * 
	 * @param rotation
	 */
	public void setRotation(double rotation) {
		this.rotation = -rotation;
	}

	/**
	 * Sets the value for friction.
	 * 
	 * @param friction
	 */
	public void setFriction(double friction) {
		this.friction = friction;
	}

	/**
	 * Sets the value for mass.
	 * 
	 * @param mass
	 */
	public void setMass(double mass) {
		this.mass = mass;
	}

	/**
	 * Configura um corpo f�sico a sprite..
	 * 
	 * @param body O corpo.
	 */
	public void setBody(Body body) {
		this.body = body;
	}

	/**
	 * Configura uma nova posi��o do eixo x.
	 * 
	 * @param x O ponto do eixo x.
	 */
	@Override
	public void setX(double x) {
		if (body == null)
			this.x = x;
		else {
			setBodyVec2(x, y);
			this.x = x;
		}
	}

	@Override
	public void setY(double y) {
		if (body == null) {
			this.y = y;
		} else {
			setBodyVec2(x, y);
			this.y = y;
		}
	}

	/**
	 * Returns 'true' if the sprite has defined behavior as a bullet or 'false'
	 * otherwise.
	 * 
	 * @return boolean
	 */
	public boolean isBullet() {
		return body.isBullet();
	}

	/**
	 * Returns the value used for restitution.
	 * 
	 * @return double
	 */
	public double getRestitution() {
		return restitution;
	}

	/**
	 * Returns the value used for rotation
	 * 
	 * @return double
	 */
	public double getRotation() {
		return rotation;
	}

	/**
	 * Returns the value used for friction.
	 * 
	 * @return double
	 */
	public double getFriction() {
		return friction;
	}

	/**
	 * Retorna o corpo.
	 * 
	 * @return Body O corpo.
	 */
	public Body getBody() {
		return body;
	}

	/**
	 * Retorna o valor da massa da sprite.
	 * 
	 * @return double O valor da massa da sprite.
	 */
	public double getMass() {
		return mass;
	}

	/**
	 * Retorna o valor atual aplicado no eixo y.
	 * 
	 * @return float O valor atual aplicado no eixo y.
	 */
	public float getForceY() {
		return body.getLinearVelocity().y;
	}

	/**
	 * Retorna O valor atual aplicado no eixo x.
	 * 
	 * @return float O valor atual aplicado no eixo x.
	 */
	public float getForceX() {
		return body.getLinearVelocity().x;
	}

}
