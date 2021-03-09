package projetos.hero;

import java.awt.Color;
import java.awt.event.KeyEvent;

import br.com.wellington.jplay2D.imageProcessing.GameImage;
import br.com.wellington.jplay2D.oi.Keyboard;
import br.com.wellington.jplay2D.window.TextGame;
import br.com.wellington.jplay2D.window.Window;

public class HeroMainMenu {

	private static boolean LOOP = false;

	private Window win;
	private GameImage plano;
	private Keyboard keyboard;

	private TextGame nome;
	private TextGame esc;
	private TextGame enter;

	public HeroMainMenu() {
		plano = new GameImage(HeroMain.IMG_MAIN_MENU);
		win = new Window(plano.width, plano.height);
		keyboard = win.getKeyboard();
		init();
	}

	private void init() {
		nome = TextGame.builder()
				.setCor(Color.red)
				.setFont(HeroMain.FONT_COMIC_SANS_MS_40)
				.setText("WELLINGTON PIRES DE OLIVEIRA")
				.build();
		nome.setX((win.getJFrame().getWidth() - nome.getWidth()) / 2);
		nome.setY((int) (1.3 * nome.getHeight()));
		
		enter = TextGame.builder()
				.setCor(Color.yellow)
				.setFont(HeroMain.FONT_COMIC_SANS_MS_40)
				.setText("[ENTER] INICIAR")
				.build();
		enter.setX((win.getJFrame().getWidth() - enter.getWidth()) / 2);
		enter.setY(win.getJFrame().getHeight() - 2 * enter.getHeight());

		esc = TextGame.builder()
				.setCor(Color.yellow)
				.setFont(HeroMain.FONT_COMIC_SANS_MS_40)
				.setText("[ESC] SAIR")
				.build();
		esc.setX((win.getJFrame().getWidth() - esc.getWidth()) / 2);
		esc.setY(win.getJFrame().getHeight() - esc.getHeight());
	}

	/** Inicia o aplicativo. */
	public void start() {
		if (LOOP) {
			return;
		}
		LOOP = true;
		while (LOOP) {
			draw();
			win.update();
			control();
			win.delay(30);
		}
		win.exit();
	}

	private void control() {
		if (keyboard.keyDown(KeyEvent.VK_ENTER)) {// ENTER
			// new Cenario01(win).start();//entra no jogoOS
		}

		if (keyboard.keyDown(Keyboard.ESCAPE_KEY)) {
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
