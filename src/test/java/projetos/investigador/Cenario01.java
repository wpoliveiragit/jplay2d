package projetos.investigador;

import br.com.wellington.jplay2D.oi.Keyboard;
import br.com.wellington.jplay2D.scene.Scene;
import br.com.wellington.jplay2D.window.Window;

/**
 * Definição do que a classe representa ou administra
 *
 * @author Wellington Pires de Oliveira.
 * @date 04/05/2019
 * @path Jogo01.Jogo.Cenario01
 */
public class Cenario01 {

	private static boolean LOOP;
	private Window win;
	private Keyboard keyboard;

	private Scene cena; // define o arquivo que move o cenário
	private Jogador player;
	private Zumbi zumbi;

	public Cenario01(Window win) {
		this.win = win;
		keyboard = win.getKeyboard();
		cena = new Scene();
		LOOP = false;
		init();
	}

	private void init() {
		cena.loadFromFile(Constantes.SCN_CENARIO, Constantes.FILE_TILE);
		player = new Jogador(win, cena, 640, 350);
		zumbi = new Zumbi(50, 400);
		cena.addOverlay(zumbi);
	}

	public void start() {
		if (LOOP) {
			return;
		}
		LOOP = true;
		while (LOOP) {
			cena.drawnMoveScene(player);

			player.caminho(cena);
			zumbi.caminho(cena);
			zumbi.perseguir(player.x, player.y);

			player.x += cena.getXOffset();
			player.y += cena.getYOffset();

			player.atirar();

			zumbi.x += cena.getXOffset();
			zumbi.y += cena.getYOffset();

			player.draw();
			zumbi.draw();
			win.update();

			control();
			win.delay(30);
		}
		win.exit();
	}

	private void control() {
		// esc
		if (keyboard.keyDown(Keyboard.ESCAPE_KEY)) {
			LOOP = false;
		}

		// atira
		if (keyboard.keyDown(Keyboard.SPACE_KEY)) {
			player.atirar();
		}

		// movimento player
		if (keyboard.keyDown((Keyboard.LEFT_KEY))) {// esquerda
			player.andaEsquerda();
			player.update();
		} else if (keyboard.keyDown((Keyboard.RIGHT_KEY))) {// direita
			player.andaDireita();
			player.update();

		}

		if (keyboard.keyDown((Keyboard.UP_KEY))) {// cima
			player.andaCima();
			player.update();
		} else if (keyboard.keyDown((Keyboard.DOWN_KEY))) {// baixo
			player.andaBaixo();
			player.update();
		}
	}

}
