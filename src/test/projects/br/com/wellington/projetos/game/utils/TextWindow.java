package br.com.wellington.projetos.game.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import br.com.wellington.jplay2D.image.GameObject;
import br.com.wellington.jplay2D.window.Window;
import br.com.wellington.projetos.game.constants.Constants;

public class TextWindow extends GameObject {

	@SuppressWarnings("deprecation")
	private static final Color COLOR = Window.getInstance().getGameGraphics().getColor();
	@SuppressWarnings("deprecation")
	private static final Font FONT = Window.getInstance().getGameGraphics().getFont();
	@SuppressWarnings("deprecation")
	private static final Graphics GRAPHICS = Window.getInstance().getGameGraphics();
	private static final Window WINDOW = Window.getInstance();

	private String txt;
	private Font font;
	private Color color;
	private int index;

	public TextWindow(Color color, Font font, String txt) {
		this.x = 0;
		this.y = 0;
		this.color = color;
		this.font = font;
		this.txt = txt;
		width = GRAPHICS.getFontMetrics(font).stringWidth(txt);
		height = GRAPHICS.getFontMetrics(font).getHeight();
	}

	public TextWindow(String txt) {
		this(COLOR, FONT, txt);
	}

	public void draw() {
		WINDOW.drawText(txt.toString(), (int) x, (int) y, color, font);
	}

	public void setTxt(String txt) {
		this.txt = txt;
		width = GRAPHICS.getFontMetrics(font).stringWidth(txt.toString());
	}

	public void setFont(Font font) {
		this.font = font;
		width = GRAPHICS.getFontMetrics(font).stringWidth(txt.toString());
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

	public int getIndex() {
		return index;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setColorOff() {
		color = Constants.COR_OFF;
	}

	public void setColorOn() {
		color = Constants.COR_ON;
	}

	public StringBuilder getTxt() {
		return new StringBuilder(txt);
	}

}
