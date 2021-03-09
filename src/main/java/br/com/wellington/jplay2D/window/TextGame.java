package br.com.wellington.jplay2D.window;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import lombok.Builder;

@Builder
public class TextGame {

	public static class TextGameBuilder {

		private Color cor;
		private Font font;
		private String text;
		private int x = 0;
		private int y = 0;

		public TextGameBuilder() {
		}

		public TextGameBuilder setText(String text) {
			this.text = text;
			return this;
		}

		public TextGameBuilder setX(int x) {
			this.x = x;
			return this;
		}

		public TextGameBuilder setY(int y) {
			this.y = y;
			return this;
		}

		public TextGameBuilder setCor(Color cor) {
			this.cor = cor;
			return this;
		}

		public TextGameBuilder setFont(Font font) {
			this.font = font;
			return this;
		}

		public TextGame build() {
			return new TextGame(cor, font, text, x, y);
		}

	}

	private Color cor;
	private Font font;
	private String text;
	private int x = 0;
	private int y = 0;

	private TextGame(Color cor, Font font, String text, int x, int y) {
		this.cor = cor;
		this.font = font;
		this.text = text;
		this.x = 0;
		this.y = 0;
	}

	public static TextGameBuilder builder() {
		return new TextGameBuilder();
	}

	/** Desenha o texto no frame. */
	public void draw() {
		Window.getInstance().drawText(text, x, y, cor, font);
	}

	/**
	 * Retorna o tamanho do texto no eixo x em pixels.
	 * 
	 * @return O tamanho do texto no eixo x em pixels.
	 */
	public int getWidth() {
		Graphics g = Window.getInstance().getJFrame().getGraphics();
		g.setFont(font);
		return g.getFontMetrics().stringWidth(text);
	}

	/**
	 * Retorna o tamanho do texto no eixo x em pixels.
	 * 
	 * @return O tamanho do texto no eixo x em pixels.
	 */
	public int getHeight() {
		Graphics g = Window.getInstance().getJFrame().getGraphics();
		g.setFont(font);
		return g.getFontMetrics().getHeight();
	}

	// GETTERS & SETTERS

	public String getText() {
		return text;
	}

	public void setText(String txt) {
		this.text = txt;
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

	public Color getCor() {
		return cor;
	}

	public void setCor(Color cor) {
		this.cor = cor;
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}

}
