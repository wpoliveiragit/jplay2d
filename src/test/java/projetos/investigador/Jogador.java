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
public class Jogador extends Personagem implements Constantes {

	private ControleTiro tiros = new ControleTiro();
	private Window janela;
	private Scene cena;

	// private Keyboard teclado;
	public Jogador(int x, int y, Window janela, Scene cena) {
		super(SPRITE_PLAYER, 20);
		this.janela = janela;
		this.cena = cena;
		this.x = x;
		this.y = y;

	}

	public void atirar() {
		if (janela.getKeyboard().keyDown(Keyboard.SPACE_KEY)) {
			tiros.adicionaTiro(x + 5, y + 11, posicao, cena);
		}
		tiros.run();
	}

	public void controle() {

		if (janela.getKeyboard().keyDown((Keyboard.LEFT_KEY))) {
			if (x > 0) {
				x -= velocidade;
				if (posicao != Keyboard.LEFT_KEY) {
					setSequence(4, 8);
					posicao = Keyboard.LEFT_KEY;
				}
				update();
			}
		} else if (janela.getKeyboard().keyDown((Keyboard.RIGHT_KEY))) {
			if (x < janela.getJFrame().getWidth() - 45) {
				x += velocidade;
				if (posicao != Keyboard.RIGHT_KEY) {
					setSequence(8, 12);
					posicao = Keyboard.RIGHT_KEY;
				}
				update();
			}
		} else if (janela.getKeyboard().keyDown((Keyboard.UP_KEY))) {
			if (y > 0) {
				y -= velocidade;
				if (posicao != Keyboard.UP_KEY) {
					setSequence(12, 16);
					posicao = Keyboard.UP_KEY;
				}
				update();
			}
		} else if (janela.getKeyboard().keyDown((Keyboard.DOWN_KEY))) {
			if (y < janela.getJFrame().getHeight() - 60) {
				y += velocidade;
				if (posicao != Keyboard.DOWN_KEY) {
					setSequence(0, 4);
					posicao = Keyboard.DOWN_KEY;
				}
				update();
			}
		}

	}

}
