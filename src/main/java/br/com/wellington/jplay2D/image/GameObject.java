package br.com.wellington.jplay2D.image;

/**
 * A classe mais básica apresentada no framework. Classe base para quase todas
 * as classes deste framework.
 */
public class GameObject {

	/**
	 * Posição da imagem na tela. Eixo x (horizontal). Quando seu valor é usado para
	 * desenho, ele é convertido em um inteiro.
	 */
	public double x;

	/**
	 * Posição da imagem na tela. Eixo y (vertical). Quando seu valor é usado para
	 * desenho, ele é convertido em um inteiro.
	 */
	public double y;

	/** A largura em pixels da imagem. */
	public int width;

	/** A altura em pixels da imagem. */
	public int height;

	/**
	 * Crie um GameObject posicionado em x = 0, y = 0 e sua dimensão é largura = 0 e
	 * altura = 0.
	 */

	protected double rotation = 0;

	public GameObject() {
		this.x = 0;
		this.y = 0;
		this.width = 0;
		this.height = 0;
	}

	/**
	 * Método usado para saber se um GameObject colidiu com outro GameObject.
	 * 
	 * @param GameObject Almeje GameObject para verificar se houve uma colisão.
	 * @return boolean - verdadeiro se colidiu, falso caso contrário.
	 */
	public boolean collided(GameObject obj) {
		return Collision.collided(this, obj);
	}
}
