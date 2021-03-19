package br.com.wellington.jplay2D.imageProcessing;

import java.awt.event.KeyEvent;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

import br.com.wellington.jplay2D.oi.Keyboard;
import br.com.wellington.jplay2D.window.Window;

/**
 * Classe responsável por controlar todas as ações e comportamentos do sprite.
 */
public class Sprite extends Animation {
	private double mass = 0.5;
	private double friction = 0.5;
	private double restitution = 0.5;
	private float forceX = 0;
	private float forceY = 0;

	private Body body = null; // É usado em física

	private double jumpVelocity = 5.3;// É usado para o salto
	private double velocityY = 0;
	private double gravity = 0.098;
	private boolean onFloor = false;
	private int floor;

	/**
	 * Construtor da classe. Cria uma classe com um quadro.
	 * 
	 * @param fileName Caminho do arquivo.
	 */
	public Sprite(String fileName) {
		this(fileName, 1);
	}

	/**
	 * Construtor da classe.
	 * 
	 * @param fileName  Caminho do arquivo.
	 * @param numFrames número de quadros.
	 */
	public Sprite(String fileName, int numFrames) {
		super(fileName, numFrames);
		velocityY = 0;
	}

	/**
	 * Método usado para mover o sprite pelo eixo x. As chaves usadas para mover o
	 * sprite são LEFT_KEY e RIGHT_KEY.
	 * 
	 * @param velocity velocidade de locomoção em pixels.
	 */

	public void moveX(double velocity) {
		moveX(KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, velocity);
	}

	/**
	 * Método usado para mover o sprite pelo eixo x.
	 * 
	 * @param leftKey  Chave usada para mover o sprite para a esquerda.
	 * @param rightKey Chave usada para mover o sprite para a direita.
	 * @param velocity velocidade de locomoção em pixels.
	 */
	public void moveX(int leftKey, int rightKey, double velocity) {
		Keyboard keyboard = Window.getInstance().getKeyboard();
		if (keyboard.checkKey(leftKey) && this.x > 1) {
			this.x -= velocity;
		}

		if (keyboard.checkKey(rightKey) && this.x + this.width < Window.getInstance().getJFrame().getWidth()) {
			this.x += velocity;
		}
	}

	/**
	 * Método usado para mover o sprite pelo eixo y. As teclas usadas para mover o
	 * sprite são UP_KEY e DOWN_KEY.
	 * 
	 * @param velocity velocidade de locomoção em pixels.
	 */
	public void moveY(double velocity) {
		this.moveY(KeyEvent.VK_UP, KeyEvent.VK_DOWN, velocity);
	}

	/**
	 * Método usado para mover o sprite pelo eixo y.
	 * 
	 * @param upKey    Chave usada para mover o sprite para a esquerda.
	 * @param downKey  Chave usada para mover o sprite para a direita.
	 * @param velocity velocidade de locomoção em pixels.
	 */
	public void moveY(int upKey, int downKey, double velocity) {
		Keyboard keyboard = Window.getInstance().getKeyboard();
		if (keyboard.checkKey(upKey) && this.y > 1) {
			this.y -= velocity;
		}

		if (keyboard.checkKey(downKey) && this.y + this.height < Window.getInstance().getJFrame().getHeight()) {
			this.y += velocity;
		}
	}

	/**
	 * aplica uma força à esquerda usando o teclado.
	 * 
	 * @param leftKey
	 * @param velocity
	 * @param behaviorKeyboard
	 */
	public void applyForceXFromKeyboardLeft(int leftKey, double velocity, int behaviorKeyboard) {

		Keyboard keyboard = Window.getInstance().getKeyboard();

		double px = Physics.meterToPixels_X_Axis(body.getPosition().x);
		float velMeter = -(float) (velocity * 3 + 10);

		if (keyboard.checkKey(leftKey))
			this.body.applyForce(new Vec2(velMeter, 0), new Vec2(body.getPosition().x, body.getPosition().y));

	}

	/**
	 * aplica uma força à direita usando o teclado.
	 * 
	 * @param rightKey
	 * @param velocity
	 * @param behaviorKeyboard
	 */
	public void applyForceXFromKeyboardRight(int rightKey, double velocity, int behaviorKeyboard) {

		Keyboard keyboard = Window.getInstance().getKeyboard();

		double px = Physics.meterToPixels_X_Axis(body.getPosition().x);
		float velMeter = -(float) (velocity * 3 + 10);

		if (keyboard.checkKey(rightKey))
			this.body.applyForce(new Vec2(-velMeter, 0), new Vec2(body.getPosition().x, body.getPosition().y));
	}

	/**
	 * aplica uma força à esquerda ou à direita usando o teclado.
	 * 
	 * @param leftKey
	 * @param rightKey
	 * @param velocity
	 * @param behaviorKeyboard
	 * @param boundsScreen     define os limites nos quais o corpo pode se mover.
	 *                         (os limites são entre 0 e a largura da janela)
	 */
	public void applyForceXFromKeyboard(int leftKey, int rightKey, double velocity, int behaviorKeyboard,
			boolean boundsScreen) {

		Keyboard keyboard = Window.getInstance().getKeyboard();

		double px = Physics.meterToPixels_X_Axis(body.getPosition().x);
		float velMeter = -(float) (velocity * 3 + 10);

		if (boundsScreen) {
			if (keyboard.checkKey(leftKey) && px - this.width / 2 > 0)

				this.body.applyForce(new Vec2(velMeter, 0), new Vec2(body.getPosition().x, body.getPosition().y));

			else if (px - this.width / 2 < 0) {
				this.cancelForces();
				this.setX(0);
			}

			if (keyboard.checkKey(rightKey) && px + this.width / 2 < Window.getInstance().getJFrame().getWidth())

				this.body.applyForce(new Vec2(-velMeter, 0), new Vec2(body.getPosition().x, body.getPosition().y));

			else if (px + this.width / 2 > Window.getInstance().getJFrame().getWidth()) {
				this.cancelForces();
				this.setX(Window.getInstance().getJFrame().getWidth() - this.width);
			}

		} else {
			if (keyboard.checkKey(leftKey))

				this.body.applyForce(new Vec2(velMeter, 0), new Vec2(body.getPosition().x, body.getPosition().y));

			else if (px - this.width / 2 < 0)

				this.setX(0);

			if (keyboard.checkKey(rightKey))

				this.body.applyForce(new Vec2(-velMeter, 0), new Vec2(body.getPosition().x, body.getPosition().y));

			else if (px + this.width / 2 > Window.getInstance().getJFrame().getWidth())

				this.setX(Window.getInstance().getJFrame().getWidth() - this.width);

		}
	}

	/**
	 * aplicar uma força para cima usando o teclado.
	 * 
	 * @param upKey
	 * @param downKey
	 * @param velocity
	 * @param behaviorKeyboard
	 */
	public void applyForceYFromKeyboardUp(int upKey, int downKey, double velocity, int behaviorKeyboard) {

		Keyboard keyboard = Window.getInstance().getKeyboard();

		double py = Physics.meterToPixels_Y_Axis(body.getPosition().y);
		float velMeter = (float) ((velocity) + (this.body.getMass() * Physics.gravity));

		if (keyboard.checkKey(upKey))
			this.body.applyForce(new Vec2(0, velMeter), new Vec2(body.getPosition().x, body.getPosition().y));
	}

	/**
	 * aplicar uma força para baixo usando o teclado.
	 * 
	 * @param upKey
	 * @param downKey
	 * @param velocity
	 * @param behaviorKeyboard
	 */
	public void applyForceYFromKeyboardDown(int upKey, int downKey, double velocity, int behaviorKeyboard) {

		Keyboard keyboard = Window.getInstance().getKeyboard();

		double py = Physics.meterToPixels_Y_Axis(body.getPosition().y);
		float velMeter = (float) ((velocity) + (this.body.getMass() * Physics.gravity));

		if (keyboard.checkKey(downKey))

			this.body.applyForce(new Vec2(0, -velMeter), new Vec2(body.getPosition().x, body.getPosition().y));
	}

	/**
	 * aplica uma força para cima ou para baixo usando o teclado.
	 * 
	 * @param upKey
	 * @param downKey
	 * @param velocity
	 * @param behaviorKeyboard
	 * @param boundsScreen     define os limites nos quais o corpo pode se mover.
	 *                         (os limites estão entre 0 e a altura da janela)
	 */
	public void applyForceYFromKeyboard(int upKey, int downKey, double velocity, int behaviorKeyboard,
			boolean boundsScreen) {

		Keyboard keyboard = Window.getInstance().getKeyboard();

		double py = Physics.meterToPixels_Y_Axis(body.getPosition().y);
		float velMeter = (float) ((velocity) + (this.body.getMass() * Physics.gravity));

		if (boundsScreen) {
			if (keyboard.checkKey(upKey) && py - this.height / 2 > 0)

				this.body.applyForce(new Vec2(0, velMeter), new Vec2(body.getPosition().x, body.getPosition().y));

			else if (py - this.height / 2 < 0) {

				this.cancelForces();
				this.setY(0);
			}

			if (keyboard.checkKey(downKey) && py + this.height / 2 < Window.getInstance().getJFrame().getHeight())

				this.body.applyForce(new Vec2(0, -velMeter), new Vec2(body.getPosition().x, body.getPosition().y));

			else if (py + this.height / 2 > Window.getInstance().getJFrame().getHeight()) {
				this.cancelForces();
				this.setY(Window.getInstance().getJFrame().getHeight() - this.height);
			}
		} else {
			if (keyboard.checkKey(upKey))

				this.body.applyForce(new Vec2(0, velMeter), new Vec2(body.getPosition().x, body.getPosition().y));

			if (keyboard.checkKey(downKey))

				this.body.applyForce(new Vec2(0, -velMeter), new Vec2(body.getPosition().x, body.getPosition().y));
		}
	}

	/**
	 * Move o sprite de um ponto a outro.
	 * 
	 * @param x        Ponto de destino no eixo x.
	 * @param y        Ponto alvo no eixo y.
	 * @param velocity velocidade de locomoção em pixels.
	 */
	public void moveTo(double x, double y, double velocity) {
		if (this.x < x && (this.x + this.width < Window.getInstance().getJFrame().getWidth())) {
			this.x += velocity;
		} else {
			if (this.x > x)
				this.x -= velocity;
		}

		if (this.y > y) {
			this.y -= velocity;
		} else {
			if (this.y < y)
				this.y += velocity;
		}
	}

	/**
	 * Define o piso que será utilizado para os métodos de salto ou queda.
	 * 
	 * @param floor Valor da coordenada x.
	 */
	public void setFloor(int floor) {
		this.floor = floor;
	}

	/**
	 * Retorna o valor do andar atual.
	 * 
	 * @return int
	 */
	public int getFloor() {
		return floor;
	}

	/**
	 * Faz o sprite pular.
	 * 
	 * @param jumpKey tecla usada para iniciar o salto.
	 */
	public void jump(int jumpKey) {
		Keyboard keyboard = Window.getInstance().getKeyboard();
		if (keyboard.checkKey(jumpKey) && onFloor == true) {
			onFloor = false;
			velocityY = -jumpVelocity;
		}

		velocityY += gravity;
		this.y += velocityY;

		if (this.y + this.height - floor > 0.0001) {
			velocityY = 0;
			this.y = floor - this.height;
			onFloor = true;
		}
	}

	/**
	 * Faz o sprite pular. A chave usada é SPACE_KEY (SPACE_BAR).
	 */
	public void jump() {
		jump(KeyEvent.VK_SPACE);
	}

	/**
	 * Retorna verdadeiro se o sprite está executando o salto, falso caso contrário.
	 */
	public boolean isJumping() {
		return !onFloor;
	}

	/**
	 * Este método simula a gravidade. É necessário definir o piso antes de chamar
	 * este método.
	 */
	public void fall() {
		if (floor - this.y - this.height < 1) {
			velocityY = 0;
			this.y = floor - this.height;
		} else
			velocityY += gravity;

		this.y += velocityY;
	}

	/**
	 * Retorna verdadeiro se o sprite estiver no chão, falso caso contrário.
	 * 
	 * @return boolean
	 */
	public boolean isOnFloor() {
		return (this.y + this.height) >= floor;
	}

	/**
	 * Define a velocidade de salto para o sprite. Essa velocidade é usada para
	 * controlar a altura do salto.
	 * 
	 * @param velocity velocidade de locomoção em pixels.
	 */
	public void setJumpVelocity(double velocity) {
		this.jumpVelocity = velocity;
	}

	/**
	 * Retorna a velocidade do salto.
	 * 
	 * @return double
	 */
	public double getJumpVelocity() {
		return this.jumpVelocity;
	}

	/**
	 * Retorna a velocidade de movimento no eixo Y.
	 * 
	 * @return double
	 */
	public void setVelocityY(double velocityY) {
		this.velocityY = velocityY;
	}

	/**
	 * Retorna a velocidade de movimento no eixo Y.
	 * 
	 * @return double
	 */
	public double getVelocityY() {
		return this.velocityY;
	}

	/**
	 * Retorna o valor usado para gravidade.
	 * 
	 * @return double
	 */
	public double getGravity() {
		return this.gravity;
	}

	/**
	 * Define o valor da gravidade.
	 * 
	 * @param gravity
	 */
	public void setGravity(double gravity) {
		this.gravity = gravity;
	}

	/**
	 * Define o valor para restituição.
	 * 
	 * @param restitution
	 */
	public void setRestitution(double restitution) {
		this.restitution = restitution;
	}

	/**
	 * Retorna o valor usado para restituição.
	 * 
	 * @return double
	 */
	public double getRestitution() {
		return restitution;
	}

	/**
	 * Define o valor da rotação.
	 * 
	 * @param rotation
	 */
	public void setRotation(double rotation) {
		this.rotation = -rotation;
	}

	/**
	 * Retorna o valor usado para rotação.
	 * 
	 * @return double
	 */
	public double getRotation() {
		return rotation;
	}

	/**
	 * Define o valor da fricção.
	 * 
	 * @param friction
	 */
	public void setFriction(double friction) {
		this.friction = friction;
	}

	/**
	 * Retorna o valor usado para fricção.
	 * 
	 * @return double
	 */
	public double getFriction() {
		return friction;
	}

	/**
	 * Define o valor da massa.
	 * 
	 * @param mass
	 */
	public void setMass(double mass) {
		this.mass = mass;
	}

	/**
	 * Retorna o valor usado para massa.
	 * 
	 * @return double
	 */
	public double getMass() {
		return mass;
	}

	/**
	 * Define corpo.
	 * 
	 * @param b
	 */
	public void setBody(Body b) {

		body = b;
	}

	/**
	 * Retorna o corpo.
	 * 
	 * @return Body
	 */
	public Body getBody() {

		return body;

	}

	/**
	 * Define a posição no eixo x.
	 * 
	 * @param x
	 */
	public void setX(double x) {
		if (body == null)

			this.x = x;

		else {

			float newX = (float) Physics.pixelsToMeterX(x + this.width / 2); // centro do sprite(this.width/2)
			float newY = (float) Physics.pixelsToMeterY(y + this.height / 2);

			Vec2 vec = new Vec2(newX, newY);
			body.setXForm(vec, (float) this.rotation);
			this.x = x;

		}
	}

	/**
	 * Retorna a posição no eixo x.
	 * 
	 * @return double
	 */
	public double getX() {
		return this.x;
	}

	/**
	 * Define a posição no eixo y.
	 * 
	 * @param y
	 */
	public void setY(double y) {
		if (body == null)

			this.y = y;

		else {

			float newX = (float) Physics.pixelsToMeterX(x + this.width / 2);
			float newY = (float) Physics.pixelsToMeterY(y + this.height / 2);

			Vec2 vec = new Vec2(newX, newY);
			body.setXForm(vec, (float) this.rotation);
			this.y = y;

		}
	}

	/**
	 * Retorna a posição no eixo y.
	 * 
	 * @return double
	 */
	public double getY() {

		return this.y;
	}

	/**
	 * Define todos os atributos.
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
	 * Aplica uma força no corpo no eixo x.
	 * 
	 * @param fx
	 */
	public void applyForceX(double fx) {
		float fx_meter = (float) (fx);

		this.body.applyForce(new Vec2((float) fx_meter, 0), new Vec2(body.getPosition().x, body.getPosition().y));

	}

	/**
	 * aplica uma força no corpo no eixo y.
	 * 
	 * @param fy
	 */
	public void applyForceY(double fy) {

		float fy_meter = (float) (fy);

		this.body.applyForce(new Vec2(0, (float) fy_meter), new Vec2(body.getPosition().x, body.getPosition().y));

	}

	/**
	 * Retorna o valor da força aplicada ao eixo x.
	 * 
	 * @return float
	 */
	public float getForceX() {
		return body.getLinearVelocity().x;
	}

	/**
	 * Retorna o valor da força aplicada ao eixo y.
	 * 
	 * @return float
	 */
	public float getForceY() {
		return body.getLinearVelocity().y;
	}

	/**
	 * Cancela todas as forças aplicadas ao corpo.
	 */
	public void cancelForces() {
		body.setLinearVelocity(new Vec2(0, 0));

	}

	/**
	 * Cancela todas as forças aplicadas ao corpo e define limites no eixo x em que
	 * o corpo pode se mover.
	 * 
	 * @param value
	 * @param lowerBoundX
	 */
	public void cancelForcesAndSetBoundsX(double value, boolean lowerBoundX) {
		cancelForces();
		if (lowerBoundX) {
			if (this.getX() < value)
				this.setX(value);
		} else if (this.getX() + this.width > value)
			this.setX(value - this.width);

	}

	/**
	 * Cancela todas as forças aplicadas ao corpo e define limites no eixo y em que
	 * o corpo pode se mover.
	 * 
	 * @param value
	 * @param lowerBoundY
	 */
	public void cancelForcesAndSetBoundsY(double value, boolean lowerBoundY) {
		cancelForces();
		if (lowerBoundY) {
			if (this.getY() < value)
				this.setY(value);

		} else if (this.getY() + this.height > value)
			this.setY(value - this.height);
	}

	/**
	 * Define o sprite como um marcador para evitar que sejam ignoradas colisões com
	 * outros sprites.
	 * 
	 * @param value
	 */
	public void setBullet(boolean value) {
		if (value)
			this.body.setBullet(true);

	}

	/**
	 * Retorna 'verdadeiro' se o sprite definiu o comportamento como um marcador ou
	 * 'falso' caso contrário.
	 * 
	 * @return boolean
	 */
	public boolean isBullet() {
		return body.isBullet();
	}

}
