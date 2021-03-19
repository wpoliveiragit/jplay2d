package projetos.investigador;

import java.awt.event.KeyEvent;

import br.com.wellington.jplay2D.imageProcessing.GameImage;
import br.com.wellington.jplay2D.oi.Keyboard;
import br.com.wellington.jplay2D.window.Window;

/**
 * Classe responsavem pela apresentação da imagem inicial do programa.
 *
 * @author Wellington Pires de Oliveira.
 * @date 10/05/2019
 * @path Jogo01.Cenarios.MainMenu
 */
public class MainMenu {

	private final Window win;
	private GameImage plano;
	private Keyboard keyboard;
	private static boolean LOOP = false;

	public MainMenu() {
		plano = new GameImage(InvestigadorMain.IMG_BACKGROUND);
		win = Window.getInstance(plano.width, plano.height);
		keyboard = win.getKeyboard();
		configuration();
	}

	private void configuration() {
		// [TECLADO - add botão de evento por tecla]
		keyboard.addKeyBehaviorActuatorRequest(KeyEvent.VK_ESCAPE);
		keyboard.addKeyBehaviorActuatorRequest(KeyEvent.VK_ENTER);
		keyboard.addKeyBehaviorActuatorRequest(KeyEvent.VK_SPACE);

		// [TECLADO - add botão de evento por pressão]
		keyboard.addKeyBehaviorActuatorRequestPress(KeyEvent.VK_UP);
		keyboard.addKeyBehaviorActuatorRequestPress(KeyEvent.VK_DOWN);
		keyboard.addKeyBehaviorActuatorRequestPress(KeyEvent.VK_LEFT);
		keyboard.addKeyBehaviorActuatorRequestPress(KeyEvent.VK_RIGHT);
	}

	/**
	 * Inicia o aplicativo.
	 */
	public void start() {
		if (LOOP) {
			return;
		}

		LOOP = true;
		while (LOOP) {
			plano.draw();
			win.update();
			control();
			win.delay(30);
		}
		win.exit();
	}

	private void control() {
		if (keyboard.checkKey(KeyEvent.VK_ENTER)) {// ENTER
			new Cenario01(win).start();
		}

		if (keyboard.checkKey(KeyEvent.VK_ESCAPE)) {
			LOOP = false;
		}
	}

}
