package br.com.wellington.jplay2D.window;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import br.com.wellington.jplay2D.image.GameObject;

public class TextWindow extends GameObject {

	private static final Window WINDOW = Window.getInstance();

	/** A cor do texto atual do sistema. */
	@SuppressWarnings("deprecation")
	private static final Color COLOR = WINDOW.getGameGraphics().getColor();

	/** A fonte do texto atual do sistema. */
	@SuppressWarnings("deprecation")
	private static final Font FONT = WINDOW.getGameGraphics().getFont();

	/** O controle grafico do sistema. */
	@SuppressWarnings("deprecation")
	private static final Graphics GRAPHICS = WINDOW.getGameGraphics();

	/** A cor do texto */
	private Color color;
	/** O texto */
	private String txt;
	/** A conte do texto */
	private Font font;

	public TextWindow(Color color, Font font, String txt) {
		this.color = color;
		this.font = font;
		this.txt = txt;
		this.x = 0;
		this.y = 0;
		width = GRAPHICS.getFontMetrics(font).stringWidth(txt);
		height = GRAPHICS.getFontMetrics(font).getHeight();
	}

	public TextWindow(String txt) {
		this(COLOR, FONT, txt);
	}

	public void draw() {
		WINDOW.drawText(txt, (int) super.x, (int) super.y, color, font);
	}

	public void setTxt(String txt) {
		this.txt = txt;
		width = GRAPHICS.getFontMetrics(font).stringWidth(txt.toString());
	}

	public void setFont(Font font) {
		this.font = font;
		width = GRAPHICS.getFontMetrics(font).stringWidth(txt);
		height = GRAPHICS.getFontMetrics(font).getHeight();
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

	public int getHeight() {
		return height;
	}

	public String getTxt() {
		return txt;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Font getFont() {
		return font;
	}

}
