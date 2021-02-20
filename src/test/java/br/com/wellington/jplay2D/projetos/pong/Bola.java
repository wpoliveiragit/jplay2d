/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.wellington.jplay2D.projetos.pong;

import br.com.wellington.jplay2D.imageProcessing.Sprite;
import br.com.wellington.jplay2D.sound.Sound;
import br.com.wellington.jplay2D.utils.Constantes;

/**
 * @author Gefersom Cardoso Lima Federal Fluminense University - UFF - Brazil
 *         Computer Science
 */

public class Bola extends Sprite implements Constantes {
	int sentidoX = PONG_LEFT;
	int sentidoY = PONG_STOP;
	double velocidadeX = 4.5;
	double velocidadeY = 2.0;

	public Bola() {
		// Abra a imagem da bolinha "pong.png" na pasta do projeto e você
		// verá que há sete bolinhas ligeiramente diferentes em uma única
		// imagem, a isso damos o nome de sprite e cada bolinha ligeiramente
		// diferente é chamada de frame.
		// Pelo comando abaixo, temos:
		// "pong.png" = nome da imagem a ser carregada
		// 7 = número de frames
		super(PONG_IMG_BOLA, 7);

		setTotalDuration(210);
	}

	public void moveX() {
		// Verifica em que sentido a bola está se deslocando e se a mesma não
		// está tentanto ultrapassar a tela.
		if (sentidoX == PONG_RIGHT && this.x + this.width < PONG_LIMITE_DIREITA_X) {
			this.x += velocidadeX;
		}

		// Verifica em que sentido a bola está se deslocando e se a mesma não
		// está tentanto ultrapassar a tela.
		if (sentidoX == PONG_LEFT && this.x > PONG_LIMITE_ESQUERDA_X) {
			this.x -= velocidadeX;
		}
	}

	public void moveY() {
		// Verifica em que sentido a bola está se deslocando e se a mesma não
		// está tentanto ultrapassar a tela
		if ((sentidoY == PONG_DOWN) && ((this.y + this.height) < PONG_LIMITE_INFERIOR_Y)) {
			this.y += velocidadeY;
		} else {
			// Verifica em que sentido a bola está se deslocando e se a mesma não
			// está tentanto ultrapassar a tela.
			if ((sentidoY == PONG_UP) && (this.y > PONG_LIMITE_SUPERIOR_Y)) {
				this.y -= velocidadeY;
			} else { // Só entrará nesse else se a bola bateu em cima ou embaixo da tela

				// Verifica se a bola bateu na parte de baixo da tela,
				// se isso ocorreu então a bola deve ter o seu sentido invertido.
				boolean bateu = true;
				if ((this.y + this.height) >= PONG_LIMITE_INFERIOR_Y) {
					sentidoY = PONG_UP;
				} else {
					// Verifica se a bola bateu na parte de cima da tela,
					// se isso ocorreu então a bola deve ter o seu sentido invertido.
					if (this.y <= PONG_LIMITE_SUPERIOR_Y) {
						sentidoY = PONG_DOWN;
					} else
						bateu = false;
				}

				if (bateu)
					new Sound(PONG_SOM_BATEU).play();
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
		// largura da tela de jogo
		int largura = PONG_LIMITE_DIREITA_X - PONG_LIMITE_ESQUERDA_X;

		/// altura da tela de jogo
		int altura = PONG_LIMITE_INFERIOR_Y - PONG_LIMITE_SUPERIOR_Y;

		this.x = (largura - this.width) / 2;
		this.y = (altura + 7 * this.height) / 2;
		sentidoY = PONG_STOP;
	}
}
