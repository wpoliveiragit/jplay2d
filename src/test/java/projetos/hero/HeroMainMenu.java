package projetos.hero;

import java.awt.Color;
import java.awt.event.KeyEvent;

import br.com.wellington.jplay2D.image.GameImage;
import br.com.wellington.jplay2D.oi.Keyboard;
import br.com.wellington.jplay2D.window.TextWindow;
import br.com.wellington.jplay2D.window.Window;

public class HeroMainMenu {

	private static boolean LOOP = false;

	private Window window;
	private GameImage plano;
	private Keyboard keyboard;

	private TextWindow nome;
	private TextWindow esc;
	private TextWindow enter;

	public HeroMainMenu() {
		plano = new GameImage(HeroMain.IMG_MAIN_MENU);
		window = Window.getInstance(plano.width, plano.height);
		keyboard = window.getKeyboard();
		configuration();
	}

	private void configuration() {
		// [TEXTOS]
		nome = new TextWindow(Color.red, HeroMain.FONT_COMIC_SANS_MS_40, "WELLINGTON PIRES DE OLIVEIRA");
		nome.setX((window.getJFrame().getWidth() - nome.getWidth()) / 2);
		nome.setY((int) (1.3 * nome.getHeight()));

		enter = new TextWindow(Color.yellow, HeroMain.FONT_COMIC_SANS_MS_40, "[ENTER] INICIAR");
		enter.setX((window.getJFrame().getWidth() - enter.getWidth()) / 2);
		enter.setY(window.getJFrame().getHeight() - 2 * enter.getHeight());

		esc = new TextWindow(Color.yellow, HeroMain.FONT_COMIC_SANS_MS_40, "[ESC] SAIR");
		esc.setX((window.getJFrame().getWidth() - esc.getWidth()) / 2);
		esc.setY(window.getJFrame().getHeight() - esc.getHeight());

		// [TECLADO]
		keyboard.addKeyBehaviorActuatorRequest(KeyEvent.VK_ENTER);
		keyboard.addKeyBehaviorActuatorRequest(KeyEvent.VK_ESCAPE);
	}

	/** Inicia o aplicativo. */
	public void start() {
		if (LOOP) {
			return;
		}
		LOOP = true;
		while (LOOP) {
			draw();
			window.update();
			control();
			window.delay(30);
		}
		window.exit();
	}

	private void control() {
		if (keyboard.checkKey(KeyEvent.VK_ENTER)) {// ENTER
			// new Cenario01(win).start();//entra no jogoOS
		}

		if (keyboard.checkKey(KeyEvent.VK_ESCAPE)) {
			LOOP = false;
		}
	}

	private void draw() {
		plano.draw();
		nome.draw();
		enter.draw();
		esc.draw();
	}

}
