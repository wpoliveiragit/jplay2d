package projetos.pong;

import br.com.wellington.jplay2D.imageProcessing.Sprite;
import br.com.wellington.jplay2D.oi.Keyboard;

public class Barra extends Sprite {
	int sentido = PongMain.STD_PARADO;

	public Barra(String nomeImagem,int x, int y) {
		super(nomeImagem);
		this.x = x;
		this.y = y;
	}

	public void moveY(Keyboard teclado, int upKey, int downKey) {

		if (teclado.checkKey(upKey) && this.y > PongMain.LIM_Y_SUP + 5) {
			this.y -= 2;
			sentido = PongMain.STD_CIMA;
		} else {
			if (teclado.checkKey(downKey) && this.y + this.height < PongMain.LIM_Y_INF - 5) {
				this.y += 2;
				sentido = PongMain.STD_BAIXO;
			} else {
				sentido = PongMain.STD_PARADO;
			}
		}
	}

	public int getSentido() {
		return this.sentido;
	}

	public void centraliza() {
		this.y = (PongMain.LIM_Y_INF - this.height / 2) / 2;
		sentido = PongMain.STD_PARADO;
	}
}
