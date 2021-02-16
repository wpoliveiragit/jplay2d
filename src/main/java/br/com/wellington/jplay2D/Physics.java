
package br.com.wellington.jplay2D;

import java.util.ArrayList;
import org.jbox2d.collision.AABB;
import org.jbox2d.collision.shapes.PolygonDef;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.World;

public class Physics extends PhysicsStatic {

	protected World world;

	protected ArrayList<SpritePlatformBody> sprs = new ArrayList<SpritePlatformBody>();

	/**
	 * Configura a dimen��o do quadro onde ocorrer� a influencia da �rea f�sica.
	 * 
	 * cria o mundo da f�sica, os par�metros lowerBoundX, lowerBoundY, upperBoundX,
	 * upperBoundY se referem ao tamanho da janela onde ocorrer� a influencia da
	 * f�sica.
	 * 
	 * @param width       A largura da �rea f�sica.
	 * @param height      A altura da �rea f�sica.
	 * 
	 *                    lowerbound and upperbound are respectively the lower and
	 *                    upper bounds related to the space corresponding to the
	 *                    physical world. wind - adds a horizontal gravity can be
	 *                    used as wind.
	 * @param lowerBoundX
	 * @param lowerBoundY
	 * @param upperBoundX
	 * @param upperBoundY
	 * @param wind
	 * @param gravity
	 */
	private void createWorld(double width, double height, float lowerBoundX, float lowerBoundY, float upperBoundX,
			float upperBoundY, float wind, float gravity) {

		setWidth(width);
		setHeight(height);

		AABB worldAABB = new AABB();
		worldAABB.lowerBound.set((float) toMeterX(lowerBoundX), (float) toMeterY(lowerBoundY));

		worldAABB.upperBound.set((float) toMeterX(upperBoundX), (float) toMeterY(upperBoundY));

		Vec2 gravityOn = new Vec2(wind, gravity);
		setWorld(new World(worldAABB, gravityOn, true));
	}

	/**
	 * Overloading of the public void createWorld(double winWidth,double winHeight,
	 * float lowerBoundX, float lowerBoundY, float upperBoundX,float upperBoundY,
	 * float wind , float gravity){ The parameters 'float lowerBoundX, float
	 * lowerBoundY , float upperBoundX and float upperBoundY ' have respectively
	 * -900f,900f,900f,-900f. The parameter 'float wind' is equal to 0. The
	 * parameter 'float gravity' is equal to -9.8f (the value is negative but the
	 * gravity is down).
	 * 
	 * cria o mundo da fisica.
	 * 
	 * @param width
	 * @param height
	 */
	public void createWorld(double width, double height) {
		createWorld(width, height, -90000f, (float) height + 90000, (float) width + 90000, -90000f, 0f, GRAVITY);
	}

	/**
	 * Este m�todo � respons�vel por criar um corpo do sprite.
	 * 
	 * cria um corpo para o sprite.
	 * 
	 * @param spr      - Path of the file
	 * @param isStatic - 'true' for static bodies and 'false' for dynamic objects
	 */
	public void createBodyFromSprite(SpritePlatformBody spr, boolean isStatic) {
		PolygonDef pd = new PolygonDef();
		pd.setAsBox((float) ((spr.width / 2) / PIXELS_PER_METER), (float) ((spr.height / 2) / PIXELS_PER_METER));
		// setAsBox - os par�metros passados s�o atributos com o dobro do valor.
		BodyDef bd = new BodyDef();
		bd.position.set((float) toMeterX((spr.x + (spr.width / 2))), (float) toMeterY((spr.y + (spr.height / 2))));
		/*
		 * body.position.set atribui esse valor ao centro do sprite, por isso deve ser
		 * somado a metade da largura do sprite. O mesmo acontece para a altura
		 */

		Body body = new Body(bd, world);
		pd.density = (float) spr.getMass();
		pd.friction = (float) spr.getFriction();
		pd.restitution = (float) spr.getRestitution();
		bd.angle = (float) spr.getRotation();

		body = world.createBody(bd);
		body.createShape(pd);

		if (!isStatic) {
			body.setMassFromShapes();
		}

		spr.setBody(body);
		sprs.add(spr); // adiciona na lista

	}

	/**
	 * M�todo respons�vel por atualizar todas as leis f�sicas aplicadas aos �rg�os
	 * 
	 * atualiza tudo que est� dentro dos limites da tela da fisica
	 */
	public void update() {
		world.step(TIMESTEP, 500);

		sprs.forEach((s) -> {
			s.x = toPixelsX(s.getBody().getPosition().x) - (s.width / 2);
			s.y = toPixelsY(s.getBody().getPosition().y) - (s.height / 2);
			s.rotation = s.getBody().getAngle();
			s.draw();
			s.update();
		});

//		for (int i = 0; i < sprs.size(); i++) {
//			sprs.get(i).x = toPixelsX(sprs.get(i).getBody().getPosition().x) - (sprs.get(i).width / 2);
//			sprs.get(i).y = toPixelsY(sprs.get(i).getBody().getPosition().y) - (sprs.get(i).height / 2);
//			sprs.get(i).rotation = sprs.get(i).getBody().getAngle();
//			sprs.get(i).draw();
//			sprs.get(i).update();
//		}
	}

	/**
	 * Configura uma nova gravidade.
	 *
	 * @param g set the gravity value
	 */
	public void setGravity(float g) {
		GRAVITY = -g;
	}

	private void setWorld(World w) {
		world = w;
	}

	/**
	 * Configura uma nova largura da �rea fisica.
	 * 
	 * @param width A largura da �rea f�sica.
	 */
	public void setWidth(double width) {
		WIDTH = width;
	}

	/**
	 * Set the window height
	 * 
	 * atribui valor para a altura da janela
	 * 
	 * @param height - window height (Window Class).
	 */
	public void setHeight(double height) {
		HEIGHT = height;
	}

	/**
	 * Sets the rate. atribui valor para a velocidade dos frames
	 * 
	 * @param ts - TimeStep. The default value = 1f/60f.
	 */
	public void setTimeStep(float ts) {
		TIMESTEP = ts;
	}

	/**
	 * atribui um valor ao vento
	 *
	 * @param w - set the wind value
	 */
	public void setWind(float w) {
		WILD = w;
	}

	/**
	 * Retorna a gravidade
	 * 
	 * @return float
	 */
	public float getGravity() {
		return -GRAVITY;
	}

	/**
	 * Retorna a for�a do vento.
	 * 
	 * @return float A for�a do vento.
	 */
	public float getWind() {
		return WILD;
	}

}