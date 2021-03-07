package projetos.pong;

import br.com.wellington.jplay2D.imageProcessing.Sprite;
import br.com.wellington.jplay2D.oi.Keyboard;
import projetos.utils.Constantes;

public class Barra extends Sprite {
	int sentido = Constantes.PONG_SENTIDO_PARADO;

	public Barra(String nomeImagem) {
		super(nomeImagem);
	}

	public void moveY(Keyboard teclado, int upKey, int downKey) {

		if (teclado.keyDown(upKey) && this.y > Constantes.PONG_LIMITE_SUPERIOR_Y + 5) {
			this.y -= 2;
			sentido = Constantes.PONG_SENTIDO_CIMA;
		} else {
			if (teclado.keyDown(downKey) && this.y + this.height < Constantes.PONG_LIMITE_INFERIOR_Y - 5) {
				this.y += 2;
				sentido = Constantes.PONG_SENTIDO_BAIXO;
			} else {
				sentido = Constantes.PONG_SENTIDO_PARADO;
			}
		}
	}

	public int getSentido() {
		return this.sentido;
	}

	public void centralizarNaTela() {
		this.y = (Constantes.PONG_LIMITE_INFERIOR_Y - this.height / 2) / 2;
		sentido = Constantes.PONG_SENTIDO_PARADO;
	}
}
