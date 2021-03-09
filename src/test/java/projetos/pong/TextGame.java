package projetos.pong;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import br.com.wellington.jplay2D.window.Window;

public class TextGame {
	private String txt;
	private int x = 0;
	private int y = 0;
	private Color cor = Color.yellow;
	private Font font;
	private Window win;

	TextGame(Window win,int x, int y , Color cor, Font font) {
		this.win = win;
		txt = "";
		this.x = x;
		this.y = y;
		this.cor = cor;
		this.font =font;
	}

	void drawn() {
		win.drawText(txt, x, y, cor, font);
	}
	
	int getTamanhoTexto() {
		Graphics g = win.getJFrame().getGraphics();
		g.setFont(font);
		FontMetrics teste = g.getFontMetrics();		
		return teste.stringWidth(txt);
	}

	public String getTxt() {
		return txt;
	}

	public void setTxt(String txt) {
		this.txt = txt;
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
