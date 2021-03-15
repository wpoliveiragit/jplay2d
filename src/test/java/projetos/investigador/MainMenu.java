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
		win = Window.create(plano.width, plano.height);
		keyboard = win.getKeyboard();
		configuration();
	}

	private void configuration() {
		keyboard.addKey(KeyEvent.VK_ESCAPE);
		keyboard.addKey(KeyEvent.VK_ENTER);
		keyboard.addKey(KeyEvent.VK_SPACE);
		keyboard.addKey(KeyEvent.VK_UP, Keyboard.DETECT_EVERY_PRESS);
		keyboard.addKey(KeyEvent.VK_DOWN, Keyboard.DETECT_EVERY_PRESS);
		keyboard.addKey(KeyEvent.VK_LEFT, Keyboard.DETECT_EVERY_PRESS);
		keyboard.addKey(KeyEvent.VK_RIGHT, Keyboard.DETECT_EVERY_PRESS);
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
		if (keyboard.keyDown(KeyEvent.VK_ENTER)) {// ENTER
			new Cenario01(win).start();
		}

		if (keyboard.keyDown(KeyEvent.VK_ESCAPE)) {
			LOOP = false;
		}
	}

}
