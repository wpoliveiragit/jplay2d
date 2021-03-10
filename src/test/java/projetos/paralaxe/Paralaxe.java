package projetos.paralaxe;

import br.com.wellington.jplay2D.imageProcessing.Parallax;
import br.com.wellington.jplay2D.oi.Keyboard;
import br.com.wellington.jplay2D.window.Window;

/**
 *
 * @author Leandro Emiliano Guimarães ---UFF--- Computer Science
 */
public class Paralaxe {

	Parallax parallax = new Parallax(); // O primeiro adicionado será o último a ser pintado.
	private boolean loop = true;
	private Window win;

	public Paralaxe() {
		win = Window.create(800, 600);
	}

	public void start() {
		parallax.add(Main.FUNDO_4);
		parallax.add(Main.FUNDO_3);
		parallax.add(Main.FUNDO_2);
		parallax.add(Main.FUNDO_1);
		parallax.add(Main.FUNDO_0);
		parallax.setVelAllLayers(1, 0);
		while (loop) {
			parallax.drawLayers();
			// mantem a repetição das camadas.
			parallax.repeatLayers(800, 600, true);
			parallax.moveLayersStandardX(true);
			win.update();
			controle();
		}
		win.exit();
	}

	private void controle() {
		if (win.getKeyboard().keyDown(Keyboard.ESCAPE_KEY)) {
			loop = false;
		}
	}

}
