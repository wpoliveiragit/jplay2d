/*
 * The use this code commercially must only be done with permission of the author.
 * Any modification shall be advised and sent to the author.
 * The author is not responsible for any problem therefrom the use of this frameWork.
 *
 * @author Gefersom Cardoso Lima
 * Universidade Federal Fluminense - UFF - Brasil - 2010
 * Ciência da Computação
 */

package br.com.wellington.jplay2D;

import java.awt.Image;

import javax.swing.ImageIcon;

import jplay.url.Url;

/** Modelador de imagens. */
public class GameImage extends GameObject implements DrawImage {

	/** Referencia da imagem */
	protected Image image;
	/** O nome da imagem. */
	protected String name;

	/**
	 * Carrega a imagem do objeto.
	 * 
	 * @param name O caminho da imagem.
	 */
	public GameImage(String name) {
		this.name = name;
		loadImage(name);
	}

	/**
	 * Carrega uma nova imagem para o objeto.
	 * 
	 * @param name O nome da imagem.
	 */
	public final void loadImage(String name) {//ok
		image = new ImageIcon(Url.getURL(name)).getImage();
		height = image.getHeight(null);
		width = image.getWidth(null);
		this.name = name;
	}

	@Override
	public void draw() {
		Window.INSTANCE.getGameGraphics2D().drawImage(image, (int) x, (int) y, width, height, null);
	}

	/**
	 * Retorna o nome da imagem.
	 * 
	 * @returnO O nome da imagem.
	 */
	public String getName() {
		return name;
	}

}
