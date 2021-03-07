package projetos.pong;

import br.com.wellington.jplay2D.imageProcessing.Sprite;
import br.com.wellington.jplay2D.sound.Sound;
import projetos.utils.Constantes;

public class Bola extends Sprite {
	int sentidoX = Constantes.PONG_SENTIDO_ESQUERDA;
	int sentidoY = Constantes.PONG_SENTIDO_PARADO;
	double velocidadeX = 4.5;
	double velocidadeY = 2.0;

	public Bola() {
		super(Constantes.PONG_IMG_BOLA, 7);
		setTotalDuration(210);
	}

	public void moveX() {
		if (sentidoX == Constantes.PONG_SENTIDO_DIREITA && this.x + this.width < Constantes.PONG_LIMITE_DIREITA_X) {
			this.x += velocidadeX;
		}
		if (sentidoX == Constantes.PONG_SENTIDO_ESQUERDA && this.x > Constantes.PONG_LIMITE_ESQUERDA_X) {
			this.x -= velocidadeX;
		}
	}

	public void moveY() {
		if ((sentidoY == Constantes.PONG_SENTIDO_BAIXO)
				&& ((this.y + this.height) < Constantes.PONG_LIMITE_INFERIOR_Y)) {
			this.y += velocidadeY;
		} else {
			if ((sentidoY == Constantes.PONG_SENTIDO_CIMA) && (this.y > Constantes.PONG_LIMITE_SUPERIOR_Y)) {
				this.y -= velocidadeY;
			} else {
				boolean bateu = true;
				if ((this.y + this.height) >= Constantes.PONG_LIMITE_INFERIOR_Y) {
					sentidoY = Constantes.PONG_SENTIDO_CIMA;
				} else {
					if (this.y <= Constantes.PONG_LIMITE_SUPERIOR_Y) {
						sentidoY = Constantes.PONG_SENTIDO_BAIXO;
					} else
						bateu = false;
				}

				if (bateu)
					new Sound(Constantes.PONG_SOM_BATEU).play();
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
		int largura = Constantes.PONG_LIMITE_DIREITA_X - Constantes.PONG_LIMITE_ESQUERDA_X;
		int altura = Constantes.PONG_LIMITE_INFERIOR_Y - Constantes.PONG_LIMITE_SUPERIOR_Y;
		this.x = (largura - this.width) / 2;
		this.y = (altura + 7 * this.height) / 2;
		sentidoY = Constantes.PONG_SENTIDO_PARADO;
	}
}
