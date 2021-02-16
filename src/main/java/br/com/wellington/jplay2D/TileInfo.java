/*
 * The use this code commercially must only be done with permission of the author.
 * Any modification shall be advised and sent to the author.
 * The author is not responsible for any problem therefrom the use of this frameWork.
 *
 * @author Gefersom Cardoso Lima
 * Federal Fluminense University
 * Computer Science
 */
package br.com.wellington.jplay2D;

/**
 * Manipulador de Tile.
 */
public class TileInfo extends GameObject {

	/** Number used to identify the Tile. */
	private int id;

	/**
	 * Construtor base.
	 * 
	 * @param id O indice da imagem na lista do arquivo.
	 */
	public TileInfo(int id) {
		setId(id);
	}

	/**
	 * Configura um novo id .
	 * 
	 * @param id O novo id.
	 */
	public final void setId(int id) {
		this.id = id;
	}

	/**
	 * retorna o id.
	 * 
	 * @return O id.
	 */
	public int getId() {
		return id;
	}
}
