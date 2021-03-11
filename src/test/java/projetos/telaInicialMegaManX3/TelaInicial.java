package projetos.telaInicialMegaManX3;

import br.com.wellington.jplay2D.imageProcessing.Animation;
import br.com.wellington.jplay2D.imageProcessing.Sprite;
import br.com.wellington.jplay2D.oi.Keyboard;
import br.com.wellington.jplay2D.sound.Sound;
import br.com.wellington.jplay2D.window.Window;

/**
 * @author Gefersom Cardoso Lima Federal Fluminense University - UFF - Brazil
 *         Computer Science
 */
public class TelaInicial {

	private Keyboard keyboard;
	private Animation backGround;

	private Sound musica;
	private boolean loop = true;
	private int escolha = 0;
	private Window win;

	public TelaInicial() {
		win = Window.create(800, 600);
		win.getMouse().setCursorImage(Main.PATH_MOUSE);

		backGround = new Sprite(Main.PATH_TELA_INICIAL, 3);

		keyboard = win.getKeyboard();
		keyboard.setBehavior(Keyboard.UP_KEY, Keyboard.DETECT_INITIAL_PRESS_ONLY);
		keyboard.setBehavior(Keyboard.DOWN_KEY, Keyboard.DETECT_INITIAL_PRESS_ONLY);

		musica = new Sound(Main.PATH_MUSICA);
		musica.setRepeat(true);
	}

	public void update() {
		backGround.draw();
		win.update();
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
		win.exit();
	}
}
