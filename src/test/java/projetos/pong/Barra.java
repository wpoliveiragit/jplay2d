package projetos.pong;

import br.com.wellington.jplay2D.imageProcessing.Sprite;
import br.com.wellington.jplay2D.oi.Keyboard;

public class Barra extends Sprite {
	int sentido = PongApplication.PONG_SENTIDO_PARADO;

	public Barra(String nomeImagem) {
		super(nomeImagem);
	}

	public void moveY(Keyboard teclado, int upKey, int downKey) {

		if (teclado.keyDown(upKey) && this.y > PongApplication.PONG_LIMITE_SUPERIOR_Y + 5) {
			this.y -= 2;
			sentido = PongApplication.PONG_SENTIDO_CIMA;
		} else {
			if (teclado.keyDown(downKey) && this.y + this.height < PongApplication.PONG_LIMITE_INFERIOR_Y - 5) {
				this.y += 2;
				sentido = PongApplication.PONG_SENTIDO_BAIXO;
			} else {
				sentido = PongApplication.PONG_SENTIDO_PARADO;
			}
		}
	}

	public int getSentido() {
		return this.sentido;
	}

	public void centralizarNaTela() {
		this.y = (PongApplication.PONG_LIMITE_INFERIOR_Y - this.height / 2) / 2;
		sentido = PongApplication.PONG_SENTIDO_PARADO;
	}
}
