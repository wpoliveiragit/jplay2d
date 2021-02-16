/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.wellington.jplay2D.projetos.pong;

import br.com.wellington.jplay2D.Keyboard;
import br.com.wellington.jplay2D.Sprite;
import br.com.wellington.jplay2D.utils.Constantes;

/**
 * @author Gefersom Cardoso Lima Federal Fluminense University - UFF - Brazil
 *         Computer Science
 */

public class Barra extends Sprite implements Constantes {
	int sentido = PONG_STOP;

	public Barra(String nomeImagem) {
		super(nomeImagem);
	}

	public void moveY(Keyboard teclado, int upKey, int downKey) {
		// A segunda condição do if não permite que a barra saia da tela.
		// Retire-a e veja o que acontece.
		if (teclado.keyDown(upKey) && this.y > PONG_LIMITE_SUPERIOR_Y + 5) {
			this.y -= 2;
			sentido = PONG_UP;
		} else {
			// A segunda condição do if não permite que a barra saia da tela.
			// Retire-a e veja o que acontece.
			if (teclado.keyDown(downKey) && this.y + this.height < PONG_LIMITE_INFERIOR_Y - 5) {
				this.y += 2;
				sentido = PONG_DOWN;
			} else {
				// Serve para dizer que a barra não está se movendo
				sentido = PONG_STOP;
			}
		}
	}

	public int getSentido() {
		return this.sentido;
	}

	public void centralizarNaTela() {
		this.y = (PONG_LIMITE_INFERIOR_Y - this.height / 2) / 2;
		sentido = PONG_STOP;
	}
}
