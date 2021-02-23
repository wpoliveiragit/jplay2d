
package br.com.wellington.jplay2D.imageProcessing;

import java.util.ArrayList;

import org.jbox2d.collision.AABB;
import org.jbox2d.collision.PolygonDef;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.World;

public class Physics {

	public static double PIXELS_PER_METER = 30;

	private static double WIN_WIDTH_PIXELS = 0;
	private static double WIN_HEIGHT_PIXELS = 0;

	private static float timeStep = 1f / 60f;
	protected static float gravity = -9.8f; // O valor negativo implica força gravitacional para baixo.
	private static float wind = 0f;

	World world;

	ArrayList<Sprite> sprs = new ArrayList<Sprite>();

	/**
	 * O método a seguir se encarrega de criar o mundo no qual todos os objetos
	 * serão simulados.
	 * 
	 * @param winWidth   window width (Window Class)
	 * @param winHeight window height (Window Class) O limite inferior e o limite
	 *                  superior são, respectivamente, os limites inferior e
	 *                  superior relacionados ao espaço correspondente ao mundo
	 *                  físico. vento - adiciona uma gravidade horizontal pode ser
	 *                  usado como vento.
	 */
	public void createWorld(double winWidth, double winHeight, float lowerBoundX, float lowerBoundY, float upperBoundX,
			float upperBoundY, float wind, float gravity) {

		this.setWindowWidth(winWidth);
		this.setWindowHeight(winHeight);

		AABB worldAABB = new AABB();

		worldAABB.lowerBound.set((float) pixelsToMeterX(lowerBoundX), (float) pixelsToMeterY(lowerBoundY));
		worldAABB.upperBound.set((float) pixelsToMeterX(upperBoundX), (float) pixelsToMeterY(upperBoundY));

		Vec2 gravityOn = new Vec2(wind, gravity);

		Boolean doSleep = true;

		World w = new World(worldAABB, gravityOn, doSleep);

		this.setWorld(w);
	}

	/**
	 * Sobrecarga do void createWorld (double winWidth, double winHeight, float
	 * lowerBoundX, float lowerBoundY, float upperBoundX, float upperBoundY, float
	 * wind, float gravity) {Os parâmetros 'float lowerBoundX, float lowerBoundY,
	 * float upperBoundX e float upperBoundY' têm respectivamente - 900f, 900f,
	 * 900f, -900f. O parâmetro 'vento flutuante' é igual a 0. O parâmetro
	 * 'gravidade flutuante' é igual a -9,8f (o valor é negativo, mas a gravidade é
	 * baixa).
	 */
	public void createWorld(double winWidth, double winHeight) {

		this.createWorld(winWidth, winHeight, -90000f, (float) winHeight + 90000, (float) winWidth + 90000, -90000f, 0f,
				gravity);

	}

	/**
	 * Este método é responsável por criar um corpo do sprite.
	 * 
	 * @param spr      Caminho do arquivo
	 * @param isStatic 'true' para corpos estáticos e 'falso' para objetos
	 *                 dinâmicos.
	 */
	public void createBodyFromSprite(Sprite spr, boolean isStatic) {

		PolygonDef pd = new PolygonDef();

		// pd.setAsBox((float)pixelsToMeterX(spr.width/2),
		// (float)pixelsToMeterY(spr.height/2));
		pd.setAsBox((float) ((spr.width / 2) / PIXELS_PER_METER), (float) ((spr.height / 2) / PIXELS_PER_METER));

		// setAsBox - os parâmetros passados são atribuídos com o dobro do valor.

		BodyDef bd = new BodyDef();

		bd.position.set((float) pixelsToMeterX((spr.x + (spr.width / 2))),
				(float) pixelsToMeterY((spr.y + (spr.height / 2))));

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

		if (!isStatic)
			body.setMassFromShapes();

		spr.setBody(body);
		sprs.add(spr); // adiciona na lista

	}

	/**
	 * Método responsável por atualizar todas as leis físicas aplicadas aos corpos.
	 */
	public void update() {

		world.step(timeStep, 500);

		// this.checkPositionBodyWithSprite();

		for (int i = 0; i < sprs.size(); i++) {

			sprs.get(i).x = meterToPixels_X_Axis(sprs.get(i).getBody().getPosition().x) - (sprs.get(i).width / 2);
			sprs.get(i).y = meterToPixels_Y_Axis(sprs.get(i).getBody().getPosition().y) - (sprs.get(i).height / 2);

			sprs.get(i).rotation = sprs.get(i).getBody().getAngle();

			sprs.get(i).draw();
			sprs.get(i).update();

		}
	}

	/**
	 *
	 * @param g defina o valor da gravidade.
	 */
	public void setGravity(float g) {
		gravity = -g;
	}

	/**
	 * Retorna o valor da gravidade.
	 * 
	 * @return float
	 */
	public float getGravity() {
		return -gravity;
	}

	/**
	 *
	 * @param w definir o valor do vento.
	 */
	public void setWind(float w) {
		wind = w;
	}

	/**
	 * Retorna o valor do vento.
	 * 
	 * @return float
	 */
	public float getWind() {
		return wind;
	}

	private void setWorld(World w) {
		world = w;
	}

	/**
	 * Defina a largura da janela.
	 * 
	 * @param width - window width (Window Class).
	 */
	public void setWindowWidth(double width) {
		WIN_WIDTH_PIXELS = width;
	}

	/**
	 * Defina a altura da janela.
	 * 
	 * @param height - window height (Window Class).
	 */
	public void setWindowHeight(double height) {
		WIN_HEIGHT_PIXELS = height;
	}

	/**
	 * Define a taxa.
	 * 
	 * @param ts - TimeStep. O valor padrão = 1f / 60f.
	 */
	public void setTimeStep(float ts) {
		timeStep = ts;
	}

	protected static double pixelsToMeterX(double pixels) {
		return (pixels - (WIN_WIDTH_PIXELS / 2)) / PIXELS_PER_METER;
	}

	protected static double pixelsToMeterY(double pixels) {
		return -(pixels - (WIN_HEIGHT_PIXELS / 2)) / PIXELS_PER_METER;
	}

	protected static double meterToPixels_Y_Axis(double pixelsY) {

		return ((WIN_HEIGHT_PIXELS / 2) - (pixelsY * PIXELS_PER_METER));
	}

	protected static double meterToPixels_X_Axis(double pixelsX) {

		return ((WIN_WIDTH_PIXELS / 2) + (pixelsX * PIXELS_PER_METER));
	}

}
