package projetos.pong;

import br.com.wellington.jplay2D.imageProcessing.Sprite;
import br.com.wellington.jplay2D.sound.Sound;

public class Bola extends Sprite {
	int sentidoY = PongMain.STD_PARADO;
	double velocidadeX = -4.5;
	double velocidadeY = 2.0;

	public Bola(int x, int y) {
		super(PongMain.IMG_BOLA, 7);
		setTotalDuration(210);
		this.x = x;
		this.y = y;
	}

	public void moveX() {
		this.x += velocidadeX;
	}

	public void moveY() {
		if ((sentidoY == PongMain.STD_BAIXO) // Indo pra baixo
				&& ((this.y + this.height) < PongMain.LIM_Y_INF)) {
			this.y += velocidadeY; // contimua descendo
			return;
		}
		if ((sentidoY == PongMain.STD_CIMA) // indo pra cima
				&& (this.y > PongMain.LIM_Y_SUP)) {
			this.y -= velocidadeY;
			return;
		}
		if ((this.y + this.height) >= PongMain.LIM_Y_INF) {// bateu em baixo
			sentidoY = PongMain.STD_CIMA;
			new Sound(PongMain.SOM_BATEU).play();
			return;
		}
		if (this.y <= PongMain.LIM_Y_SUP) {// bateu em sima
			sentidoY = PongMain.STD_BAIXO;
			new Sound(PongMain.SOM_BATEU).play();
		}
		//
	}

	public void setSentidoX(int sentido) {
		velocidadeX = -velocidadeX;
	}

	public void setSentidoY(int sentido) {
		this.sentidoY = sentido;
	}

	public void centraliza() {
		int largura = PongMain.LIM_X_DIR - PongMain.LIM_X_ESQ;
		int altura = PongMain.LIM_Y_INF - PongMain.LIM_Y_SUP;
		this.x = (largura - this.width) / 2;
		this.y = (altura + 7 * this.height) / 2;
		sentidoY = PongMain.STD_PARADO;
	}

	public void somColisao() {
		Sound somColisao = new Sound(PongMain.SOM_BATEU);
		somColisao.play();
	}
}
