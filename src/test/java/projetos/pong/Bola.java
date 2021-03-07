package projetos.pong;

import br.com.wellington.jplay2D.imageProcessing.Sprite;
import br.com.wellington.jplay2D.sound.Sound;

public class Bola extends Sprite {
	int sentidoX = PongApplication.PONG_SENTIDO_ESQUERDA;
	int sentidoY = PongApplication.PONG_SENTIDO_PARADO;
	double velocidadeX = 4.5;
	double velocidadeY = 2.0;

	public Bola() {
		super(PongApplication.PONG_IMG_BOLA, 7);
		setTotalDuration(210);
	}

	public void moveX() {
		if (sentidoX == PongApplication.PONG_SENTIDO_DIREITA
				&& this.x + this.width < PongApplication.PONG_LIMITE_DIREITA_X) {
			this.x += velocidadeX;
		}
		if (sentidoX == PongApplication.PONG_SENTIDO_ESQUERDA && this.x > PongApplication.PONG_LIMITE_ESQUERDA_X) {
			this.x -= velocidadeX;
		}
	}

	public void moveY() {
		if ((sentidoY == PongApplication.PONG_SENTIDO_BAIXO)
				&& ((this.y + this.height) < PongApplication.PONG_LIMITE_INFERIOR_Y)) {
			this.y += velocidadeY;
		} else {
			if ((sentidoY == PongApplication.PONG_SENTIDO_CIMA) && (this.y > PongApplication.PONG_LIMITE_SUPERIOR_Y)) {
				this.y -= velocidadeY;
			} else {
				boolean bateu = true;
				if ((this.y + this.height) >= PongApplication.PONG_LIMITE_INFERIOR_Y) {
					sentidoY = PongApplication.PONG_SENTIDO_CIMA;
				} else {
					if (this.y <= PongApplication.PONG_LIMITE_SUPERIOR_Y) {
						sentidoY = PongApplication.PONG_SENTIDO_BAIXO;
					} else
						bateu = false;
				}

				if (bateu)
					new Sound(PongApplication.PONG_SOM_BATEU).play();
			}
		}
	}

	public void setSentidoX(int sentido) {
		this.sentidoX = sentido;
	}

	public void setSentidoY(int sentido) {
		this.sentidoY = sentido;
	}

	public void centralizarNaTela() {
		int largura = PongApplication.PONG_LIMITE_DIREITA_X - PongApplication.PONG_LIMITE_ESQUERDA_X;
		int altura = PongApplication.PONG_LIMITE_INFERIOR_Y - PongApplication.PONG_LIMITE_SUPERIOR_Y;
		this.x = (largura - this.width) / 2;
		this.y = (altura + 7 * this.height) / 2;
		sentidoY = PongApplication.PONG_SENTIDO_PARADO;
	}
}
