package br.com.wellington.jplay2D.image;

/**
 * A classe mais básica apresentada no framework. Classe base para quase todas
 * as classes deste framework.
 */
public class GameObject {

	/** Ponto superior esquerdo no eixo x do GameObject. */
	public double x;

	/** Ponto superior esquerdo no eixo y do GameObject. */
	public double y;

	/** A largura em pixels do GameObject. */
	public int width;

	/** A altura em pixels dGameObject. */
	public int height;

	/**
	 * Crie um GameObject posicionado em x = 0, y = 0 e sua dimensão é largura = 0 e
	 * altura = 0.
	 */
	protected double rotation = 0;// acredito que esta no local errado

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
