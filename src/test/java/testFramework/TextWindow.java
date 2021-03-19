package testFramework;

import java.awt.Color;
import java.awt.Font;

import br.com.wellington.jplay2D.window.Window;

public class TextWindow {

	@SuppressWarnings("deprecation")
	private static final Color COLOR = Window.getInstance().getGameGraphics().getColor();
	@SuppressWarnings("deprecation")
	private static final Font FONT = Window.getInstance().getGameGraphics().getFont();

	private StringBuilder txt;
	private Font font;
	private Color color;
	private int index;

	int x;
	int y;
	int width;
	int height;

	public TextWindow(int index, int x, int y, Color color, Font font) {
		this.index = index;
		this.x = x;
		this.y = y;
		this.color = color;
		this.font = font;
		this.txt = new StringBuilder("");
		width = Window.getInstance().getGameGraphics().getFontMetrics(font).stringWidth(txt.toString());
		height = Window.getInstance().getGameGraphics().getFontMetrics(font).getHeight();

	}

	public TextWindow(int index, int x, int y) {
		this(index, x, y, COLOR, FONT);
	}

	public TextWindow(int index, int x, int y, Font font) {
		this(index, x, y, COLOR, font);
	}

	public TextWindow(int index, int x, int y, Color color) {
		this(index, x, y, color, FONT);
	}

	public void draw() {
		Window.getInstance().drawText(txt.toString(), x, y, color, font);
	}

	public void setTxt(StringBuilder txt) {
		this.txt = txt;
		width = Window.getInstance().getGameGraphics().getFontMetrics(font).stringWidth(txt.toString());
	}

	public void setFont(Font font) {
		this.font = font;
		width = Window.getInstance().getGameGraphics().getFontMetrics(font).stringWidth(txt.toString());
		height = Window.getInstance().getGameGraphics().getFontMetrics(font).getHeight();
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
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
