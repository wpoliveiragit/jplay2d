package br.com.wellington.jplay2D;

/** Base de todos os objetos do jogo */
public class GameObject {

	/* # Obs.: Criar uma hitbox nesta classe ## */

	/** Posicao da imagem no eixo x. */
	protected double x;
	/** Posicao da imagem no eixo y. */
	protected double y;
	/** A largura em pixel da imagem */
	protected int width;
	/** A altura em pixel da imagem. */
	protected int height;

	/** Cria um GameObject com todos os atributo com valor igual a zero */
	public GameObject() {
		x = 0;
		y = 0;
		width = 0;
		height = 0;
	}

	/**
	 * Verifica se ocorreu uma colisao entre este e o objeto do parametro.
	 * 
	 * @param GameObject O objeto.
	 * @return boolean true caso tenha coledido.
	 */
	public boolean collided(GameObject obj) {
		// corrigir '>='
		if (x >= (obj.x + obj.width) || (x + width) <= obj.x) {
			return false;
		}
		if (y >= (obj.y + obj.height) || (y + height) <= obj.y) {
			return false;
		}
		return true;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
