package projetos.investigador;

import java.awt.event.KeyEvent;

import br.com.wellington.jplay2D.scenery.Scenery;
import br.com.wellington.jplay2D.window.Window;

/**
 * Definição do que a classe representa ou administra
 *
 * @author Wellington Pires de Oliveira.
 * @date 05/05/2019
 * @path Jogo01.Jogo.Jogador
 */
public class Jogador extends Personagem {

	private ControleTiro tiros = new ControleTiro();
	private Window janela;
	private Scenery cena;

	// private Keyboard teclado;
	public Jogador(Window janela, Scenery cena, int x, int y) {
		super(InvestigadorMain.SPRITE_PLAYER, 20);
		this.janela = janela;
		this.cena = cena;
		this.x = x;
		this.y = y;

	}

	public void atirar() {

		tiros.adicionaTiro(x + 5, y + 11, super.posicao, cena);

		tiros.run();
	}

	public void andaEsquerda() {
		if (x > 0) {
			x -= super.velocidade;
			if (super.posicao != KeyEvent.VK_LEFT) {
				setSequence(4, 8);
				super.posicao = KeyEvent.VK_LEFT;
			}
		}
	}

	public void andaDireita() {
		if (x < janela.getJFrame().getWidth() - 45) {
			x += super.velocidade;
			if (super.posicao != KeyEvent.VK_RIGHT) {
				setSequence(8, 12);
				super.posicao = KeyEvent.VK_RIGHT;
			}
		}
	}

	public void andaCima() {
		if (y > 0) {
			y -= super.velocidade;
			if (super.posicao != KeyEvent.VK_UP) {
				setSequence(12, 16);
				super.posicao = KeyEvent.VK_UP;
			}
		}
	}

	public void andaBaixo() {
		if (y < janela.getJFrame().getHeight() - 60) {
			y += super.velocidade;
			if (super.posicao != KeyEvent.VK_DOWN) {
				setSequence(0, 4);
				super.posicao = KeyEvent.VK_DOWN;
			}

		}
	}

}
