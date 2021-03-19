package testFramework;

import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import br.com.wellington.jplay2D.imageProcessing.GameObject;
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
	List<TextWindow> txtList;
	TextWindow latencia;
	TextWindow tempo;
	TextWindow mouseTxt;

	GameObject gameObject;

	public TestJogo() {
		Window.getInstance(800, 600);
		win = Window.getInstance();
		keyboard = win.getKeyboard();
		mouse = win.getMouse();
		timeBase = win.getGameTime();
		configuration();
	}

	private void configuration() {
		// [MOUSE - configuração]
		mouse.setCursorImage(TestMain.IMG_MOUSE);

		mouse.getLeftButton().changeBehavior();

		// [KEYBOARD - remover todas as chaves]
		keyboard.cleanKeys();

		// [KEYBOARD - configuração]
		keyboard.addKeyBehaviorActuatorRequest(KeyEvent.VK_ESCAPE);
		keyboard.addKeyBehaviorActuatorRequest(KeyEvent.VK_ENTER);
		keyboard.addKeyBehaviorActuatorRequest(KeyEvent.VK_1);
		keyboard.addKeyBehaviorActuatorRequest(KeyEvent.VK_2);
		keyboard.addKeyBehaviorActuatorRequest(KeyEvent.VK_3);

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
		txtList = new ArrayList<>();

		// [TXT -Window]
		int x;
		int y = 20;
		int addY = 17;

		TextWindow tw = new TextWindow(txtList.size(), 0, y, Color.blue, TestMain.FONT_COMIC_SANS_MS_20);
		tw.setTxt(new StringBuilder().append("[TESTE] jplay 2D"));

		latencia = new TextWindow(txtList.size(), 0, y, Color.red, TestMain.FONT_COMIC_SANS_MS_20);
		latencia.setTxt(new StringBuilder().append("latencia[]"));

		tempo = new TextWindow(txtList.size(), 5, Window.getInstance().getJFrame().getHeight() - 8, Color.blue,
				TestMain.FONT_COMIC_SANS_MS_20);
		tempo.setTxt(new StringBuilder().append("tempo(mili)["));

		x = Window.getInstance().getJFrame().getWidth();
		x -= (tw.getWidth() + latencia.getWidth());
		x = (x / 2) - 20;

		tw.setX(x);
		latencia.setX(tw.getX() + tw.getWidth() + 10);

		txtList.add(tw);
		txtList.add(latencia);
		txtList.add(tempo);

		x = 25;
		y += 18;
		txtList.add(mouseTxt = new TextWindow(txtList.size(), x += 30, y += addY, Color.yellow,
				TestMain.FONT_COMIC_SANS_MS_15));
		mouseTxt.setTxt(new StringBuilder().append("MOUSE ["));

		txtList.add(tw = new TextWindow(txtList.size(), x, y += addY, Color.yellow, TestMain.FONT_COMIC_SANS_MS_15));
		tw.setTxt(new StringBuilder().append("Leve o mouse ao centro da tela"));

		txtList.add(tw = new TextWindow(txtList.size(), x, y += addY, Color.yellow, TestMain.FONT_COMIC_SANS_MS_15));
		tw.setTxt(new StringBuilder().append("[ESC] FECHAR"));

		txtList.add(tw = new TextWindow(txtList.size(), x, y += addY, Color.yellow, TestMain.FONT_COMIC_SANS_MS_15));
		tw.setTxt(new StringBuilder().append("[1] FULLSCREEN"));

		txtList.add(tw = new TextWindow(txtList.size(), x, y += addY, Color.yellow, TestMain.FONT_COMIC_SANS_MS_15));
		tw.setTxt(new StringBuilder().append("[2] RESTORESCREEN"));

		txtList.add(tw = new TextWindow(txtList.size(), x, y += addY, Color.yellow, TestMain.FONT_COMIC_SANS_MS_15));
		tw.setTxt(new StringBuilder().append("[3] screen 1024 x 768"));

		txtList.add(tw = new TextWindow(txtList.size(), x, y += addY, Color.yellow, TestMain.FONT_COMIC_SANS_MS_15));
		tw.setTxt(new StringBuilder().append("[DisplayMode]: " + listDisplayMode.size()));

		x += 15;
		y += 3;
		addY -= 3;
		for (DisplayMode dm : listDisplayMode) {
			tw = new TextWindow(txtList.size(), x, y += addY, Color.white, TestMain.FONT_COMIC_SANS_MS_9);
			txtList.add(tw);
			tw.setTxt(new StringBuilder().append("( ").append(dm.getWidth()).append(" x ").append(dm.getHeight())
					.append(" )"));
		}

		gameObject = new GameObject();
		gameObject.x = win.getJFrame().getWidth() / 3;
		gameObject.y = win.getJFrame().getHeight() / 3;
		gameObject.width = win.getJFrame().getWidth() / 3;
		gameObject.height = win.getJFrame().getHeight() / 3;

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
		win.clear(Color.black);

		latencia.setTxt(new StringBuilder(latencia.getTxt().substring(0, 9)).append(timeBase.latecy()).append("]"));
		tempo.setTxt(new StringBuilder(tempo.getTxt().substring(0, 12)).append(timeBase.getTotalTime()).append("]"));
		mouseTxt.setTxt(new StringBuilder(mouseTxt.getTxt().substring(0, 7))
				.append((mouse.isOverObject(gameObject)) ? "X]" : " ]"));

		for (TextWindow tw : txtList) {
			tw.draw();
		}

	}

	private void update() {
		win.update();
	}

	private void control() {
		if (keyboard.checkKey(KeyEvent.VK_ESCAPE)) {// encerra o jogo
			LOOP = false;
		}
		if (keyboard.checkKey(KeyEvent.VK_1)) {
			win.setFullScreen();

		}

		if (keyboard.checkKey(KeyEvent.VK_2)) {
			win.restoreScreen();

		}

		if (keyboard.checkKey(KeyEvent.VK_3)) {
			// [DISPLAY MODE - configuração]
			for (DisplayMode item : win.getCompatibleDisplayMode()) {
				if (item.getWidth() == 1024 && item.getHeight() == 768) {
					win.setDisplayMode(item);
					keyboard.removeKey(KeyEvent.VK_3);
					System.out.println("botão 3 removido");
					break;
				}
			}

		}
	}

	private void usar() {
		// [ok]-[WindowGameTime]
		// -[mouse]
		// -[window]
		win.getGameGraphics();

	}

}
