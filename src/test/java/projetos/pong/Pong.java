package projetos.pong;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;

import br.com.wellington.jplay2D.imageProcessing.GameImage;
import br.com.wellington.jplay2D.oi.Keyboard;
import br.com.wellington.jplay2D.sound.Sound;
import br.com.wellington.jplay2D.time.Time;
import br.com.wellington.jplay2D.window.Window;

public class Pong {

	// RECURSOS APLICAÇÃO
	private Window window;
	private Keyboard keyboard;
	private GameImage fundo;

	// RECURSOS DO JOGO
	private Bola bola;
	private Barra barraVerde;
	private Barra barraRoxa;

	private Time tempo;
	private Font fonte = PongMain.FONT_COMIC_SANS_MS_24;

	private Sound musica;
	private static boolean LOOP;

	private int pontuacaoRoxo = 0;
	private int pontuacaoVerde = 0;
	private TextGame txtGameOvertime;
	private TextGame txtGamePointRoxo;
	private TextGame txtGamePointVerde;

	public Pong(int width, int height) {
		window = Window.getInstance(width, height);
		window.getMouse().setCursorImage("");

		keyboard = window.getKeyboard();

		fundo = new GameImage(PongMain.IMG_BG);

		bola = new Bola(300, 300);

		barraVerde = new Barra(PongMain.IMG_BAR_VERDE, 40, 300);
		barraRoxa = new Barra(PongMain.IMG_BAR_ROXA, 745, 300);

		barraVerde.centraliza();
		barraRoxa.centraliza();

		tempo = new Time(0, 0, 10, 400, 588, false);
		tempo.setColor(Color.WHITE);
		tempo.setFont(PongMain.FONT_COMIC_SANS_MS_16);

		musica = new Sound(PongMain.SOM_MUSICA);
		musica.setRepeat(true);// faz a música ser tocada continuamente.

		txtGameOvertime = new TextGame(window, 247, 105, Color.cyan, fonte);
		txtGameOvertime.setTxt("O tempo está terminando!");

		txtGamePointRoxo = new TextGame(window, 475, 70, Color.black, fonte);
		txtGamePointRoxo.setTxt("" + pontuacaoRoxo);

		txtGamePointVerde = new TextGame(window, 295, 70, Color.black, fonte);
		txtGamePointVerde.setTxt("" + pontuacaoVerde);

		LOOP = false;
		configuration();
	}

	private void configuration() {
		keyboard.addKeyBehaviorActuatorRequest(KeyEvent.VK_ESCAPE);
		keyboard.addKeyBehaviorActuatorRequest(KeyEvent.VK_ENTER);
		keyboard.addKeyBehaviorActuatorRequest(KeyEvent.VK_SPACE);

		keyboard.addKeyBehaviorActuatorRequestPress(KeyEvent.VK_S);
		keyboard.addKeyBehaviorActuatorRequestPress(KeyEvent.VK_W);

		keyboard.addKeyBehaviorActuatorRequestPress(KeyEvent.VK_UP);
		keyboard.addKeyBehaviorActuatorRequestPress(KeyEvent.VK_DOWN);
	}

	public void start() {
		if (LOOP) {
			return;
		}
		musica.play();
		LOOP = true;
		while (LOOP) {
			draw();
			window.update();
			if (bola.collided(barraVerde)) {
				bola.setSentidoX(PongMain.STD_DIREITA);
				bola.setSentidoY(barraVerde.getSentido());
				bola.somColisao();
			} else if (bola.collided(barraRoxa)) {
				bola.setSentidoX(PongMain.STD_ESQUERDA);
				bola.setSentidoY(barraRoxa.getSentido());
				bola.somColisao();
			}
			if (bola.x < PongMain.LIM_X_ESQ + 1) {
				txtGamePointRoxo.setTxt("" + ++pontuacaoRoxo);
				iniciaJogo();
			} else if (bola.x + bola.width > PongMain.LIM_X_DIR - 1) {
				txtGamePointVerde.setTxt("" + ++pontuacaoVerde);
				iniciaJogo();
			}
			bola.moveX();
			bola.moveY();
			controle();
			window.delay(10);
		}

		TextGame txtG = new TextGame(window, 0, window.getJFrame().getHeight() / 2, Color.yellow,
				PongMain.FONT_COMIC_SANS_MS_60);
		txtG.setTxt("FIM DE JOGO");
		txtG.setX((window.getJFrame().getWidth() - txtG.getTamanhoTexto()) / 2);
		txtG.drawn();
		window.update();

		window.delay(3000);
		window.exit();
	}

	private void controle() {
		barraVerde.moveY(keyboard, KeyEvent.VK_W, KeyEvent.VK_S);
		barraRoxa.moveY(keyboard, KeyEvent.VK_UP, KeyEvent.VK_DOWN);
		if (keyboard.checkKey(KeyEvent.VK_ESCAPE) || tempo.timeEnded()) {
			LOOP = false;
		}
	}

	private void draw() {
		fundo.draw();
		bola.draw();
		barraVerde.draw();
		barraRoxa.draw();
		tempo.draw();
		if (tempo.getTotalSecond() < 30) {
			txtGameOvertime.drawn();
		}
		txtGamePointRoxo.drawn();
		txtGamePointVerde.drawn();
	}

	private void iniciaJogo() {
		// Centraliza bola
		int largura = PongMain.LIM_X_DIR - PongMain.LIM_X_ESQ;
		int altura = PongMain.LIM_Y_INF - PongMain.LIM_Y_SUP;
		bola.x = (largura - bola.width) / 2;
		bola.y = (altura + 7 * bola.height) / 2;
		bola.sentidoY = PongMain.STD_PARADO;

		// Centraliza barra Verde
		int centroY = (PongMain.LIM_Y_INF - barraVerde.height / 2) / 2;

		barraVerde.y = centroY;
		barraVerde.sentido = PongMain.STD_PARADO;

		// Centraliza Barra Roxa
		barraRoxa.y = centroY;
		barraRoxa.sentido = PongMain.STD_PARADO;

		// som de ponto
		new Sound(PongMain.SOM_PONTO).play();

	}

}