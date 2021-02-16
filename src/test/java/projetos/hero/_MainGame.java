package projetos.hero;

import jplay.Window;
import jplay.WindowBaseSetDisplayModeRuntimeException;

public class _MainGame {

	/** Inicia o jogo */
	public static void main(String[] args) {
		new _MainGame();
	}

	/** Janela principal */
	static Window janela;
	/** amarra o loop central da janela */
	static boolean loop;
	/** Controle da janela */
	static ControleJanela controleJanela;

	public _MainGame() {
		try {
			janela = new Window(808, 600);
		} catch (WindowBaseSetDisplayModeRuntimeException ex) {
			System.out.println("Teste de definição de janela");
			janela = new Window(800, 600);
		}
		ThreadTime(250);
		janela.fullScreen();
		ThreadTime(250);
		janela.restoreScreen();
		janela.changeCursosImage("mouse.png");
		start();
	}

	void start() {
		janela.setDelay(30);
		controleJanela = new MenuPrincipal();
		loop = true;
		while (loop) {
			controleJanela.control();
			controleJanela.draw();
			janela.update();
			janela.delay();
		}
		janela.exit();
	}

	void ThreadTime(long time) {
		try {
			Thread.sleep(time);
		} catch (Exception e) {

		}
	}

}
