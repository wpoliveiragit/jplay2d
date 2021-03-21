package br.com.wellington.jplay2D.image;

public class ParallaxLayers extends GameImage {

	private double velX = 0;
	private double velY = 0;

	/**
	 * 
	 * 
	 * @param background caminho do arquivo.
	 */
	public ParallaxLayers(String background) {
		super(background);

	}

	/**
	 * Define a velocidade.
	 * 
	 * @param vel_X defina a velocidade da camada no eixo x.
	 * @param vel_Y defina a velocidade da camada no eixo y.
	 */
	public void setVel(double vel_X, double vel_Y) {
		this.velX = vel_X;
		this.velY = vel_Y;

	}

	/**
	 * Define a velocidade no eixo x.
	 * 
	 * @param velX defina a velocidade da camada no eixo x.
	 */
	public void setVelX(double velX) {
		this.velX = velX;
	}

	/**
	 * Define a velocidade no eixo y.
	 * 
	 * @param vel_Y defina a velocidade da camada no eixo y.
	 */
	public void setVelY(double vel_Y) {
		this.velY = vel_Y;
	}

	/**
	 * Retorna a velocidade no eixo x.
	 * 
	 * @return double a velocidade no eixo x.
	 */
	public double getVelX() {
		return velX;
	}

	/**
	 * Retorna a velocidade no eixo y
	 * 
	 * @return double a velocidade no eixo y
	 */
	public double getVelY() {
		return velY;
	}

	/**
	 * Mova a camada no eixo x.
	 * 
	 * @param left 'true' para mover a camada para a esquerda, 'false' para mover a
	 *             camada para a direita.
	 */
	public void moveLayerX(boolean left) {
		if (left)
			this.x -= velX;
		else
			this.x += velX;
	}

	/**
	 * Mova a camada no eixo y.
	 * 
	 * @param up 'true' para mover a camada para cima, 'false' para mover a camada
	 *           para baixo.
	 */
	public void moveLayerY(boolean up) {
		if (up)
			this.y -= velY;
		else
			this.y += velY;
	}

}
