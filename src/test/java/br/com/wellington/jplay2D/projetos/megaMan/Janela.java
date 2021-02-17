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

public class Janela {

	private Window window;
	private Keyboard keyboard;
	private GameImage background;
	private boolean loop = true;
	private Boneco boneco;

	public Janela(int x, int y) {
		window = new Window(x, y);
		keyboard = window.getKeyboard();
		keyboard.addKey(KeyEvent.VK_CONTROL);
		background = new GameImage(Main.MEGAMAN_BACKDROP);
		boneco = new Boneco(window, 500);
	}

	public void start() {
		while (loop) {
			draw();
			window.delay(10);
			controle();
		}
		window.exit();
	}

	private void draw() {
		background.draw();
		boneco.draw();
		window.drawText("Pular:  Barra de espaço.", 20, 20, Color.YELLOW);
		window.drawText("Atirar: Control.", 20, 40, Color.YELLOW);
		window.drawText("Setas movem o boneco.", 20, 60, Color.YELLOW);
		window.drawText("ESC: Fecha o aplicativo.", 20, 80, Color.YELLOW);
		window.update();
	}

	private void controle() {
		if (keyboard.keyDown(Keyboard.ESCAPE_KEY)) {
			loop = false;
		}
		boneco.comando();
	}

}
