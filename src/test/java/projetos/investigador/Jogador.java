package projetos.investigador;

import br.com.wellington.jplay2D.oi.Keyboard;
import br.com.wellington.jplay2D.scene.Scene;
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
	private Scene cena;

	// private Keyboard teclado;
	public Jogador(Window janela, Scene cena, int x, int y) {
		super(Constantes.SPRITE_PLAYER, 20);
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
			if (super.posicao != Keyboard.LEFT_KEY) {
				setSequence(4, 8);
				super.posicao = Keyboard.LEFT_KEY;
			}
		}
	}

	public void andaDireita() {
		if (x < janela.getJFrame().getWidth() - 45) {
			x += super.velocidade;
			if (super.posicao != Keyboard.RIGHT_KEY) {
				setSequence(8, 12);
				super.posicao = Keyboard.RIGHT_KEY;
			}
		}
	}

	public void andaCima() {
		if (y > 0) {
			y -= super.velocidade;
			if (super.posicao != Keyboard.UP_KEY) {
				setSequence(12, 16);
				super.posicao = Keyboard.UP_KEY;
			}
		}
	}

	public void andaBaixo() {
		if (y < janela.getJFrame().getHeight() - 60) {
			y += super.velocidade;
			if (super.posicao != Keyboard.DOWN_KEY) {
				setSequence(0, 4);
				super.posicao = Keyboard.DOWN_KEY;
			}

		}
	}

}
