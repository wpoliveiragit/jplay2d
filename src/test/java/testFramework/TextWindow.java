package testFramework;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import br.com.wellington.jplay2D.image.GameObject;
import br.com.wellington.jplay2D.window.Window;

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

	public TextWindow(int x, int y, Color color, Font font) {
		this.x = x;
		this.y = y;
		this.color = color;
		this.font = font;
		this.txt = "";
		width = GRAPHICS.getFontMetrics(font).stringWidth(txt.toString());
		height = GRAPHICS.getFontMetrics(font).getHeight();
	}

	public TextWindow(int x, int y) {
		this(x, y, COLOR, FONT);
	}

	public TextWindow(int x, int y, Font font) {
		this(x, y, COLOR, font);
	}

	public TextWindow(int x, int y, Color color) {
		this(x, y, color, FONT);
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

	public void setColor(Color color) {
		this.color = color;
	}

	public StringBuilder getTxt() {
		return new StringBuilder(txt);
	}

}
