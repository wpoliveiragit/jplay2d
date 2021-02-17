package br.com.wellington.jplay2D.projetos.paralaxe;

import br.com.wellington.jplay2D.Keyboard;
import br.com.wellington.jplay2D.Parallax;
import br.com.wellington.jplay2D.Window;

/**
 *
 * @author Leandro Emiliano Guimarães ---UFF--- Computer Science
 */
public class Paralaxe extends Window {
	private static final long serialVersionUID = 1L;

	Parallax parallax = new Parallax(); // O primeiro adicionado será o último a ser pintado.
	private boolean loop = true;

	public Paralaxe() {
		super(800, 600);
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
			update();
			controle();
		}
		exit();
	}

	private void controle() {
		if (getKeyboard().keyDown(Keyboard.ESCAPE_KEY)) {
			loop = false;
		}
	}

}
