package testFramework;

import java.awt.event.KeyEvent;

import br.com.wellington.jplay2D.oi.Keyboard;
import br.com.wellington.jplay2D.window.Window;

public class JogoTest {

	private static boolean LOOP = true;
	public Window win;
	private Keyboard keyboard;

	public JogoTest() {
		new Window(800, 600);
		initConfig();

		win = Window.getInstance();
		keyboard = win.getKeyboard();
	}

	private void initConfig() {
		// remove todas as interações adicionadas por default
		keyboard.removeKey(Keyboard.UP_KEY);
		keyboard.removeKey(Keyboard.LEFT_KEY);
		keyboard.removeKey(Keyboard.RIGHT_KEY);
		keyboard.removeKey(Keyboard.DOWN_KEY);
		keyboard.removeKey(Keyboard.ESCAPE_KEY);
		keyboard.removeKey(Keyboard.SPACE_KEY);
		keyboard.removeKey(Keyboard.ENTER_KEY);

		// Adicionando novas interações
		keyboard.addKey(KeyEvent.VK_ENTER, Keyboard.DETECT_INITIAL_PRESS_ONLY);
		keyboard.addKey(KeyEvent.VK_ESCAPE, Keyboard.DETECT_INITIAL_PRESS_ONLY);

	}

	public void startGame() {

		while (LOOP) {
			drawn();
			win.update();
			control();
			win.delay(30);
		}

	}

	private void drawn() {

	}

	private void control() {
		if (keyboard.keyDown(KeyEvent.VK_ESCAPE)) {// encerra o jogo
			LOOP = false;
		}

	}

}
