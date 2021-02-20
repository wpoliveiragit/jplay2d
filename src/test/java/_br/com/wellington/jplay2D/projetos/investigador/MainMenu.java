package _br.com.wellington.jplay2D.projetos.investigador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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

	private final GameImage plano;
	private final Window janela;
	private boolean loopMainMenu = false;
	private boolean openCenario01 = false;

	public MainMenu() {
		plano = new GameImage(IMG_BACKGROUND);
		janela = new Window(plano.width, plano.height);
		start();
	}

	/**
	 * Inicia o aplicativo.
	 */
	private void start() {
		loopMainMenu = true;
		addTeclado();
		while (loopMainMenu) {
			plano.draw();
			janela.update();
			if (openCenario01) {
				new Cenario01(janela);
			}
		}
		janela.exit();
	}

	/**
	 * Adiciona o teclado do MainMenu.
	 */
	private void addTeclado() {
		janela.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				switch (e.getKeyChar()) {
				case Keyboard.ENTER_KEY:
					openCenario01 = true;
					break;
				case Keyboard.ESCAPE_KEY:
					loopMainMenu = false;
					break;
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {

			}
		});
	}
}
