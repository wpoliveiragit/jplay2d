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
public class MainMenu implements Constantes {

	private final Window win;
	private GameImage plano;
	private Keyboard keyboard;
	private static boolean LOOP = false;

	public MainMenu() {
		plano = new GameImage(IMG_BACKGROUND);
		win = Window.create(plano.width, plano.height);
		keyboard = win.getKeyboard();
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

		if (keyboard.keyDown(Keyboard.ESCAPE_KEY)) {
			LOOP = false;
		}
	}

}
