package projetos.telaInicialMegaManX3;

import java.awt.event.KeyEvent;

import br.com.wellington.jplay2D.imageProcessing.Animation;
import br.com.wellington.jplay2D.imageProcessing.Sprite;
import br.com.wellington.jplay2D.oi.Keyboard;
import br.com.wellington.jplay2D.sound.Sound;
import br.com.wellington.jplay2D.window.Window;

/**
 * @author Gefersom Cardoso Lima Federal Fluminense University - UFF - Brazil
 *         Computer Science
 */
public class TelaInicialMegaManX3 {

	private Keyboard keyboard;
	private Animation backGround;

	private Sound musica;
	private boolean loop = true;
	private int escolha = 0;
	private Window win;

	public TelaInicialMegaManX3() {
		win = Window.getInstance(800, 600);
		win.getMouse().setCursorImage(TelaInicialMegaManX3Main.PATH_MOUSE);

		backGround = new Sprite(TelaInicialMegaManX3Main.PATH_TELA_INICIAL, 3);

		keyboard = win.getKeyboard();
		keyboard.addKeyBehaviorActuatorRequest(KeyEvent.VK_UP);
		keyboard.addKeyBehaviorActuatorRequest(KeyEvent.VK_DOWN);

		musica = new Sound(TelaInicialMegaManX3Main.PATH_MUSICA);
		musica.setRepeat(true);
		configuration();
	}

	private void configuration() {
		keyboard.addKeyBehaviorActuatorRequest(KeyEvent.VK_ESCAPE);
		keyboard.addKeyBehaviorActuatorRequest(KeyEvent.VK_UP);
		keyboard.addKeyBehaviorActuatorRequest(KeyEvent.VK_DOWN);
	}

	public void update() {
		backGround.draw();
		win.update();
	}

	private void controle() {
		if (keyboard.checkKey(KeyEvent.VK_ESCAPE)) {// SAIR
			loop = false;
		}
		if (keyboard.checkKey(KeyEvent.VK_UP)) {// CIMA
			escolha--;
			if (escolha < 0) {
				escolha = 0;
				return;
			}
			backGround.setCurrFrame(escolha);
			new Sound(TelaInicialMegaManX3Main.PATH_SOM_TROCA_SELECAO).play();
			return;
		}
		if (keyboard.checkKey(KeyEvent.VK_DOWN)) {// BAICO
			escolha++;
			if (escolha > 2) {
				escolha = 2;
				return;
			}
			backGround.setCurrFrame(escolha);
			new Sound(TelaInicialMegaManX3Main.PATH_SOM_TROCA_SELECAO).play();
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
