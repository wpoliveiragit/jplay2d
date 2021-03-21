package testFramework;

import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import br.com.wellington.jplay2D.image.GameImage;
import br.com.wellington.jplay2D.image.GameObject;
import br.com.wellington.jplay2D.image.Sprite;
import br.com.wellington.jplay2D.oi.Keyboard;
import br.com.wellington.jplay2D.oi.Mouse;
import br.com.wellington.jplay2D.sound.Sound;
import br.com.wellington.jplay2D.window.WindowGameTime;
import projetos.jogoDaMemoria.GameControl;

public class TelaInicial extends GameControl {

	private Keyboard keyboard;
	private Mouse mouse;

	private GameImage logoJogo;
	private Sprite start;
	private Sprite option;
	private Sprite credits;
	private Sprite moeda;

	private static final byte POSICAO_MIN = 0;
	private static final byte POSICAO_MAX = 2;
	private byte escolha;

	private WindowGameTime timeBase;

	private List<DisplayMode> listDisplayMode;
	private List<TextWindow> txtList;
	private TextWindow latencia;
	private TextWindow tempo;
	private TextWindow mouseTxt;

	GameObject gameObject;

	public TelaInicial() {
		super(30);
		keyboard = WINDOW.getKeyboard();
		mouse = WINDOW.getMouse();
		timeBase = WINDOW.getGameTime();
		escolha = 0;
	}

	@Override
	protected void init() {

		// [MOUSE - config]
		mouse.setCursorImage(TestMain.IMG_MOUSE);

		// [KEYBOARD - config]
		keyboard.cleanKeys();
		keyboard.addKeyBehaviorActuatorRequest(KeyEvent.VK_ESCAPE);
		keyboard.addKeyBehaviorActuatorRequest(KeyEvent.VK_ENTER);
		keyboard.addKeyBehaviorActuatorRequest(KeyEvent.VK_UP);
		keyboard.addKeyBehaviorActuatorRequest(KeyEvent.VK_DOWN);

		// [SPRITES - config]
		logoJogo = new GameImage(TestMain.IMG_LOGO_JOGO);
		logoJogo.x = WINDOW.getJFrame().getWidth() - logoJogo.width - 20;
		logoJogo.y = 20;

		start = new Sprite(TestMain.SPT_START, 2);
		start.setCurrFrame(1);
		start.x = (WINDOW.getJFrame().getWidth() - start.width) / 2;
		start.y = WINDOW.getJFrame().getHeight() / 2;

		option = new Sprite(TestMain.SPT_OPTIONS, 2);
		option.x = start.x;
		option.y = start.y + option.height;

		credits = new Sprite(TestMain.SPT_CREDITS, 2);
		credits.x = start.x;
		credits.y = start.y + option.height + credits.height;

		moeda = new Sprite(TestMain.SPT_MOEDA_OURO, 10);
		moeda.setTotalDuration(175);
		moeda.setLoop(true);
		moeda.x = start.x - moeda.width * 1.5;
		moeda.y = start.y;

		// [TEXTS- CONFIGURATION]
		latencia = new TextWindow(50, 50, Color.yellow, TestMain.FONT_COMIC_SANS_MS_15);
		latencia.setTxt("latencia: ");
//		// [DISPLAY MODE - remove duplicatas]
//		listDisplayMode = new ArrayList<DisplayMode>();
//		for (DisplayMode display : WINDOW.getCompatibleDisplayMode()) {
//			boolean add = true;
//			for (DisplayMode item : listDisplayMode) {
//				if (item.getWidth() == display.getWidth() && item.getHeight() == display.getHeight()) {
//					add = false;
//					break;
//				}
//			}
//			if (add) {
//				listDisplayMode.add(display);
//			}
//		}
//		txtList = new ArrayList<>();
//
//		// [TXT -Window]
//		int x;
//		int y = 20;
//		int addY = 17;
//
//		TextWindow tw = new TextWindow(txtList.size(), 0, y, Color.blue, TestMain.FONT_COMIC_SANS_MS_20);
//		tw.setTxt(new StringBuilder().append("[TESTE] jplay 2D"));
//
//		latencia = new TextWindow(txtList.size(), 0, y, Color.red, TestMain.FONT_COMIC_SANS_MS_20);
//		latencia.setTxt(new StringBuilder().append("latencia[]"));
//
//		tempo = new TextWindow(txtList.size(), 5, Window.getInstance().getJFrame().getHeight() - 8, Color.blue,
//				TestMain.FONT_COMIC_SANS_MS_20);
//		tempo.setTxt(new StringBuilder().append("tempo(mili)["));
//
//		x = Window.getInstance().getJFrame().getWidth();
//		x -= (tw.getWidth() + latencia.getWidth());
//		x = (x / 2) - 20;
//
//		tw.setX(x);
//		latencia.setX(tw.getX() + tw.getWidth() + 10);
//
//		txtList.add(tw);
//		txtList.add(latencia);
//		txtList.add(tempo);
//
//		x = 25;
//		y += 18;
//		txtList.add(mouseTxt = new TextWindow(txtList.size(), x += 30, y += addY, Color.yellow,
//				TestMain.FONT_COMIC_SANS_MS_15));
//		mouseTxt.setTxt(new StringBuilder().append("MOUSE ["));
//
//		txtList.add(tw = new TextWindow(txtList.size(), x, y += addY, Color.yellow, TestMain.FONT_COMIC_SANS_MS_15));
//		tw.setTxt(new StringBuilder().append("Leve o mouse ao centro da tela"));
//
//		txtList.add(tw = new TextWindow(txtList.size(), x, y += addY, Color.yellow, TestMain.FONT_COMIC_SANS_MS_15));
//		tw.setTxt(new StringBuilder().append("[ESC] FECHAR"));
//
//		txtList.add(tw = new TextWindow(txtList.size(), x, y += addY, Color.yellow, TestMain.FONT_COMIC_SANS_MS_15));
//		tw.setTxt(new StringBuilder().append("[1] FULLSCREEN"));
//
//		txtList.add(tw = new TextWindow(txtList.size(), x, y += addY, Color.yellow, TestMain.FONT_COMIC_SANS_MS_15));
//		tw.setTxt(new StringBuilder().append("[2] RESTORESCREEN"));
//
//		txtList.add(tw = new TextWindow(txtList.size(), x, y += addY, Color.yellow, TestMain.FONT_COMIC_SANS_MS_15));
//		tw.setTxt(new StringBuilder().append("[3] screen 1024 x 768"));
//
//		txtList.add(tw = new TextWindow(txtList.size(), x, y += addY, Color.yellow, TestMain.FONT_COMIC_SANS_MS_15));
//		tw.setTxt(new StringBuilder().append("[DisplayMode]: " + listDisplayMode.size()));
//
//		x += 15;
//		y += 3;
//		addY -= 3;
//		for (DisplayMode dm : listDisplayMode) {
//			tw = new TextWindow(txtList.size(), x, y += addY, Color.white, TestMain.FONT_COMIC_SANS_MS_9);
//			txtList.add(tw);
//			tw.setTxt(new StringBuilder().append("( ").append(dm.getWidth()).append(" x ").append(dm.getHeight())
//					.append(" )"));
//		}
//
//		gameObject = new GameObject();
//		gameObject.x = WINDOW.getJFrame().getWidth() / 3;
//		gameObject.y = WINDOW.getJFrame().getHeight() / 3;
//		gameObject.width = WINDOW.getJFrame().getWidth() / 3;
//		gameObject.height = WINDOW.getJFrame().getHeight() / 3;

	}

	@Override
	protected void draw() {
		WINDOW.clear(Color.black);
		latencia.draw();
		logoJogo.draw();
		start.draw();
		option.draw();
		credits.draw();
		moeda.update();
		moeda.draw();
	}

	private void ajustaSelecao() {
		if (escolha < POSICAO_MIN) {
			escolha = POSICAO_MAX;
		} else if (escolha > POSICAO_MAX) {
			escolha = POSICAO_MIN;
		}

		switch (escolha) {
		case 0:
			moeda.y = start.y;
			start.setCurrFrame(1);
			option.setCurrFrame(0);
			credits.setCurrFrame(0);
			break;
		case 1:
			moeda.y = option.y;
			start.setCurrFrame(0);
			option.setCurrFrame(1);
			credits.setCurrFrame(0);
			break;
		case 2:
			moeda.y = credits.y;
			start.setCurrFrame(0);
			option.setCurrFrame(0);
			credits.setCurrFrame(1);
			break;
		}

		new Sound(TestMain.SND_SELECAO).play();

	}

	@Override
	protected void control() {
		if (keyboard.checkKey(KeyEvent.VK_ESCAPE)) {// encerra o jogo
			super.exist();
		}

		if (keyboard.checkKey(KeyEvent.VK_ENTER)) {
			switch (escolha) {
			case 0:
				new Cena01().start();
				;
				break;
			case 1:
				break;
			case 2:
				break;
			}
			return;
		}

		if (keyboard.checkKey(KeyEvent.VK_UP)) {
			escolha--;
			ajustaSelecao();
			return;
		}

		if (keyboard.checkKey(KeyEvent.VK_DOWN)) {
			escolha++;
			ajustaSelecao();
			return;
		}

	}

	@Override
	protected void end() {
		WINDOW.exit();
	}

	@Override
	protected void collisionCheck() {
		if (mouse.isOverObject(latencia)) {
			latencia.setColor(Color.blue);
			new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						Thread.sleep(1000);
					} catch (Exception ex) {

					}
					latencia.setColor(Color.yellow);
				}
			}).start();
		}

	}

}
