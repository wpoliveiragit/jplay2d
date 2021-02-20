package br.com.wellington.jplay2D.imageProcessing;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;

import javax.swing.ImageIcon;

import br.com.wellington.jplay2D.window.Window;

/** Class responsible for modeling an image. */
public class GameImage extends GameObject {

	/**
	 * Reference for an Image.
	 * 
	 * @see java.awt.Image
	 */
	Image image;

	/**
	 * Class constructor, it loads an image.
	 * 
	 * @param fileName path of the image and its name.
	 * @see #loadImage(java.lang.String)
	 */
	public GameImage(String fileName) {
		loadImage(fileName);
	}

	/**
	 * This method loads an image.
	 * 
	 * @param fileName path of the image and its name.
	 */
	public void loadImage(String fileName) {
		ImageIcon icon = new ImageIcon(fileName);
		this.image = icon.getImage();
		this.width = image.getWidth(null);
		this.height = image.getHeight(null);
	}

	/** Draw an image on the screen. */
	public void draw() {
		Graphics2D g2d = (Graphics2D) Window.getInstance().getGameGraphics();
		AffineTransform tx = new AffineTransform();

		double rot = rotation;
		tx.rotate(-rot, width / 2, height / 2);

		int newy = (int) (x * Math.sin(rot) + y * Math.cos(rot));
		int newx = (int) (x * Math.cos(rot) - y * Math.sin(rot));

		g2d.setTransform(tx);

		g2d.drawImage(image, newx, newy, width, height, null);
	}

	public void drawPartially(int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2) {
		Window.getInstance().getGameGraphics().drawImage(image, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, null);
	}
}
