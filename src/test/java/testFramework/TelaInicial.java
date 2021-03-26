package testFramework;

import java.awt.Color;
import java.awt.event.KeyEvent;

import br.com.wellington.jplay2D.audio.Audio;
import br.com.wellington.jplay2D.image.GameImage;
import br.com.wellington.jplay2D.image.Sprite;
import projetos.jogoDaMemoria.GameControl;

public class TelaInicial extends GameControl {
	private static final byte POSICAO_MIN = 0;
	private static final byte POSICAO_MAX = 2;

	private GameImage logoJogo;

	private Audio musica;

	private Sprite moeda;
	private Sprite start;
	private Sprite option;
	private Sprite credits;

	private byte escolha;

	private TextWindow latencia;

	private Volume volume;

	public TelaInicial() {
		super(30);
	}

	@Override
	protected void loadResources() {
		KEYBOARD = WINDOW.getKeyboard();
		KEYBOARD.cleanKeys();
		KEYBOARD.addKeyBehaviorActuatorRequest(KeyEvent.VK_ESCAPE);
		KEYBOARD.addKeyBehaviorActuatorRequest(KeyEvent.VK_ENTER);
		KEYBOARD.addKeyBehaviorActuatorRequest(KeyEvent.VK_UP);
		KEYBOARD.addKeyBehaviorActuatorRequest(KeyEvent.VK_DOWN);
		KEYBOARD.addKeyBehaviorActuatorRequest(KeyEvent.VK_LEFT);
		KEYBOARD.addKeyBehaviorActuatorRequest(KeyEvent.VK_RIGHT);

		MOUSE = WINDOW.getMouse();
		MOUSE.setCursorImage(TestMain.IMG_MOUSE);

		musica = new Audio(TestMain.SND_ENTRADA_MEGAMAN);
		musica.setLoop(true);
		volume = new Volume(musica.getMinimumVolume());

		musica.setVolume(volume.getVolume());
		musica.play();
		escolha = 0;
	}

	@Override
	protected void beforeStart() {

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

		new Audio(TestMain.SND_SELECAO).play();

	}

	@Override
	protected void control() {
		if (KEYBOARD.checkKey(KeyEvent.VK_ESCAPE)) {// encerra o jogo
			super.exist();
		}

		if (KEYBOARD.checkKey(KeyEvent.VK_ENTER)) {
			switch (escolha) {
			case 0:
				musica.stop();
				new Cena01().start();

				loadResources();
				break;
			case 1:
				break;
			case 2:
				break;
			}
			return;
		}

		// [CONTROLE]
		if (KEYBOARD.checkKey(KeyEvent.VK_UP)) {// [SOBE]
			escolha--;
			ajustaSelecao();
			return;
		} else if (KEYBOARD.checkKey(KeyEvent.VK_DOWN)) {// [DESCE]
			escolha++;
			ajustaSelecao();
			return;
		}

		// [VOLUME]
		if (KEYBOARD.checkKey(KeyEvent.VK_LEFT)) {// [AUMENTA]
			musica.setVolume(volume.diminueVolume());
			return;
		} else if (KEYBOARD.checkKey(KeyEvent.VK_RIGHT)) {// [DIMINUE]
			musica.setVolume(volume.aumentaVolume());
			return;
		}

	}

	@Override
	protected void afterStart() {
		WINDOW.exit();
	}

	@Override
	protected void updateScene() {
		if (MOUSE.isOverObject(latencia)) {
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

	class Volume {

		static final int MAX = 5;
		int volumeMax;
		int corrente;
		int base;

		Volume(float min) {
			volumeMax = (int) min;
			corrente = MAX;
			base = -(volumeMax / MAX);
			System.out.println("BASE: " + base);
			System.out.println("CORRENTE: " + corrente);
			System.out.println("VOLUME MAX: " + volumeMax);
		}

		int aumentaVolume() {
			if (corrente < MAX) {
				corrente++;
				System.out.println("CORRENTE: " + corrente);
			}
			return volumeMax + (base * corrente);
		}

		int diminueVolume() {
			if (corrente > 1) {
				corrente--;
				System.out.println("CORRENTE: " + corrente);
			}
			return volumeMax + (base * corrente);
		}

		int getVolume() {
			return volumeMax + (base * corrente);
		}
	}

}
