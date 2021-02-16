/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.wellington.jplay2D.projetos.megaMan;

import java.awt.Color;
import java.awt.event.KeyEvent;

import br.com.wellington.jplay2D.GameImage;
import br.com.wellington.jplay2D.Keyboard;
import br.com.wellington.jplay2D.Window;
import br.com.wellington.jplay2D.utils.Constantes;

public class MainMegaMan {

	private Window window;
	private Keyboard keyboard;
	private GameImage fundoDaTela;

	private Boneco boneco;

	public MainMegaMan() {
		window = new Window(800, 600);
		keyboard = window.getKeyboard();
		keyboard.addKey(KeyEvent.VK_CONTROL);
		fundoDaTela = new GameImage(Constantes.MEGAMAN_BACKDROP);
		boneco = new Boneco(window, 500);
	}

	public void start() {
		while (true) {
			draw();
			boneco.comando();
			window.delay(10);
			if (keyboard.keyDown(Keyboard.ESCAPE_KEY)) {
				break;
			}
		}
		window.exit();
	}

	private void draw() {
		fundoDaTela.draw();
		boneco.draw();
		window.drawText("Pular:  Barra de espaço.", 20, 20, Color.YELLOW);
		window.drawText("Atirar: Control.", 20, 40, Color.YELLOW);
		window.drawText("Setas movem o boneco.", 20, 60, Color.YELLOW);
		window.update();
	}

}
