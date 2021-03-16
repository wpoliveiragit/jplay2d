package testFramework;

import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import br.com.wellington.jplay2D.oi.Keyboard;
import br.com.wellington.jplay2D.oi.Mouse;
import br.com.wellington.jplay2D.window.Window;
import br.com.wellington.jplay2D.window.WindowGameTime;

public class TestJogo {

	private static boolean LOOP = true;

	private Window win;
	private Keyboard keyboard;
	private Mouse mouse;
	private WindowGameTime timeBase;

	List<DisplayMode> listDisplayMode;
//	Point p1;
//	Point p2;

	public TestJogo() {
		Window.create(800, 600);
		win = Window.getInstance();
		keyboard = win.getKeyboard();
		mouse = win.getMouse();
		timeBase = win.getGameTime();
		configuration();
	}

	private void configuration() {
		// [geral]
		atualizaAreaMouse();

		// [MOUSE - configuração]
		mouse.setCursorImage(TestMain.IMG_MOUSE);
		mouse.setBehavior(Mouse.BUTTON_LEFT, Keyboard.BEHAVIOR_KEY_HELD_DOWN);
		mouse.setBehavior(Mouse.BUTTON_MIDDLE, Keyboard.BEHAVIOR_KEY_HELD_DOWN);
		mouse.setBehavior(Mouse.BUTTON_RIGHT, Keyboard.BEHAVIOR_KEY_HELD_DOWN);

		// [KEYBOARD - remover todas as chaves]
		keyboard.cleanKeys();

		// [KEYBOARD - configuração]
		keyboard.addKeyPressed(KeyEvent.VK_ESCAPE);
		keyboard.addKeyPressed(KeyEvent.VK_ENTER);
		keyboard.addKeyPressed(KeyEvent.VK_1);
		keyboard.addKeyPressed(KeyEvent.VK_2);
		keyboard.addKeyPressed(KeyEvent.VK_3);

		// [DISPLAY MODE - remove duplicatas]
		listDisplayMode = new ArrayList<DisplayMode>();
		for (DisplayMode display : win.getCompatibleDisplayMode()) {
			boolean add = true;
			for (DisplayMode item : listDisplayMode) {
				if (item.getWidth() == display.getWidth() && item.getHeight() == display.getHeight()) {
					add = false;
					break;
				}
			}
			if (add) {
				listDisplayMode.add(display);
			}
		}
	}

	void atualizaAreaMouse() {
//		p1 = new Point(0, win.getJFrame().getHeight() / 2);
//		p2 = new Point(win.getJFrame().getWidth(), win.getJFrame().getHeight());
	}

	public void startGame() {
		while (LOOP) {
			drawn();
			update();
			control();
			win.delay(30);
		}
		win.exit();
	}

	private void drawn() {
		int x = 25;
		int y = 20;
		win.clear(Color.blue);

		StringBuilder txt = new StringBuilder();
		txt.append("[TESTE] jplay 2D").append(" [").append(win.getJFrame().getWidth()).append("x")
				.append(win.getJFrame().getHeight()).append("]");

		txt.append(" Mouse[").append((mouse.isLeftButtonPressed()) ? "X]" : " ]");
		txt.append(" [").append((mouse.isMiddleButtonPressed()) ? "X]" : " ]");
		txt.append(" [").append((mouse.isRightButtonPressed()) ? "X]" : " ]");
		txt.append(" [").append(mouse.getPosition().x).append("x").append(mouse.getPosition().y).append("]");

		win.drawText(txt.toString(), x, y, Color.black, TestMain.FONT_COMIC_SANS_MS_20);

		txt = new StringBuilder();
		txt.append("LATENCIA[").append(timeBase.latecy()).append("]");

		txt.append("TEMPO (mili)[").append(timeBase.getTotalTime()).append("]");

		win.drawText(txt.toString(), x, y += 25, Color.yellow, TestMain.FONT_COMIC_SANS_MS_15);
		win.drawText("[ESC] FECHAR", x, y += 17, Color.white);
		win.drawText("[1] FULLSCREEN", x, y += 17, Color.white);
		win.drawText("[2] RESTORESCREEN", x, y += 17, Color.white);
		win.drawText("[3] screen 1024 x 768", x, y += 17, Color.white);

		txt = new StringBuilder("Leve o mouse até a metade inferior da janela");

		Point p1 = new Point(0, win.getJFrame().getHeight() / 2);
		Point p2 = new Point(win.getJFrame().getWidth(), win.getJFrame().getHeight());

		txt.append("[").append((mouse.isOverArea(p1, p2)) ? "X]" : " ]");
		win.drawText(txt.toString(), x, y += 17, Color.white);

		win.drawText("[DisplayMode]: " + listDisplayMode.size(), x, y += x * 2, Color.white);
		y += 6;
		for (DisplayMode dm : listDisplayMode) {
			txt = new StringBuilder();
			txt.append("[").append(dm.getWidth()).append("]");
			txt.append("x[").append(dm.getHeight()).append("]");
			win.drawText(txt.toString(), x + 20, y += x - 12, Color.white, TestMain.FONT_COMIC_SANS_MS_9);
		}

	}

	private void update() {
		win.update();
	}

	private void control() {
		if (keyboard.keyDown(KeyEvent.VK_ESCAPE)) {// encerra o jogo
			LOOP = false;
		}
		if (keyboard.keyDown(KeyEvent.VK_1)) {
			win.setFullScreen();
			atualizaAreaMouse();
		}

		if (keyboard.keyDown(KeyEvent.VK_2)) {
			win.restoreScreen();
			atualizaAreaMouse();
		}

		if (keyboard.keyDown(KeyEvent.VK_3)) {
			// [DISPLAY MODE - configuração]
			for (DisplayMode item : win.getCompatibleDisplayMode()) {
				if (item.getWidth() == 1024 && item.getHeight() == 768) {
					win.setDisplayMode(item);
					keyboard.removeKey(KeyEvent.VK_3);
					System.out.println("botão 3 removido");
					break;
				}
			}
			atualizaAreaMouse();
		}
	}

	private void usar() {
		// [ok]-[WindowGameTime]
		// -[mouse]
		// -[window]
		win.getGameGraphics();

	}

}
