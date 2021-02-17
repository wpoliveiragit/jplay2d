package br.com.wellington.jplay2D.projetos.telaInicialMegaManX3;

import br.com.wellington.jplay2D.Animation;
import br.com.wellington.jplay2D.Keyboard;
import br.com.wellington.jplay2D.Sound;
import br.com.wellington.jplay2D.Sprite;
import br.com.wellington.jplay2D.Window;

/**
 * @author Gefersom Cardoso Lima Federal Fluminense University - UFF - Brazil
 *         Computer Science
 */
public class TelaInicial extends Window {
	private static final long serialVersionUID = 1L;

	private Animation backGround;
	private Keyboard keyboard;
	private int escolha = 0;
	private Sound musica;
	private boolean loop = true;

	public TelaInicial() {
		super(800, 600);
		setCursorImage(Main.PATH_MOUSE);

		backGround = new Sprite(Main.PATH_TELA_INICIAL, 3);

		keyboard = getKeyboard();
		keyboard.setBehavior(Keyboard.UP_KEY, Keyboard.DETECT_INITIAL_PRESS_ONLY);
		keyboard.setBehavior(Keyboard.DOWN_KEY, Keyboard.DETECT_INITIAL_PRESS_ONLY);

		musica = new Sound(Main.PATH_MUSICA);
		musica.setRepeat(true);
	}

	@Override
	public void update() {
		backGround.draw();
		super.update();
	}

	private void controle() {
		if (keyboard.keyDown(Keyboard.ESCAPE_KEY)) {// SAIR
			loop = false;
		}
		if (keyboard.keyDown(Keyboard.UP_KEY)) {// CIMA
			escolha--;
			if (escolha < 0) {
				escolha = 0;
				return;
			}
			backGround.setCurrFrame(escolha);
			new Sound(Main.PATH_SOM_TROCA_SELECAO).play();
			return;
		}
		if (keyboard.keyDown(Keyboard.DOWN_KEY)) {// BAICO
			escolha++;
			if (escolha > 2) {
				escolha = 2;
				return;
			}
			backGround.setCurrFrame(escolha);
			new Sound(Main.PATH_SOM_TROCA_SELECAO).play();
		}
	}

	public void start() {
		musica.play();
		while (loop) {
			update();
			controle();
		}
		exit();
	}
}
