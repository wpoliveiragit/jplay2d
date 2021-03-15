/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projetos.megaMan;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import br.com.wellington.jplay2D.imageProcessing.GameImage;
import br.com.wellington.jplay2D.oi.Keyboard;
import br.com.wellington.jplay2D.window.Window;

public class Janela {

	private Window window;
	private Keyboard keyboard;
	private GameImage background;
	private boolean loop = true;
	private Boneco boneco;
	private List<Tiro> balas = new ArrayList<Tiro>();

	public Janela(int x, int y) {
		window = Window.create(x, y);
		keyboard = window.getKeyboard();
		keyboard.addKey(KeyEvent.VK_CONTROL);
		background = new GameImage(MegaManMain.IMG_BD);
		boneco = new Boneco(window, 500);
		configuration();
	}

	private void configuration() {
		keyboard.addKey(KeyEvent.VK_ESCAPE);
		keyboard.addKey(KeyEvent.VK_ENTER);
		keyboard.addKey(KeyEvent.VK_SPACE);
		keyboard.addKey(KeyEvent.VK_UP, Keyboard.DETECT_EVERY_PRESS);
		keyboard.addKey(KeyEvent.VK_DOWN, Keyboard.DETECT_EVERY_PRESS);
		keyboard.addKey(KeyEvent.VK_LEFT, Keyboard.DETECT_EVERY_PRESS);
		keyboard.addKey(KeyEvent.VK_RIGHT, Keyboard.DETECT_EVERY_PRESS);
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
		for (int i = 0; i < balas.size(); i++) {
			Tiro tiro = balas.get(i);
			if (tiro.move()) {
				balas.remove(i--);
			}

		}
		window.drawText("Pular:  Barra de espaço.", 20, 20, Color.YELLOW);
		window.drawText("Atirar: Control.", 20, 40, Color.YELLOW);
		window.drawText("Setas movem o boneco.", 20, 60, Color.YELLOW);
		window.drawText("ESC: Fecha o aplicativo.", 20, 80, Color.YELLOW);
		window.update();
	}

	private void controle() {
		if (keyboard.keyDown(KeyEvent.VK_ESCAPE)) {
			loop = false;
		}

		boneco.jump();

		if (keyboard.keyDown(KeyEvent.VK_CONTROL)) {
			balas.add(boneco.atira());
		}

		if (keyboard.keyDown(KeyEvent.VK_LEFT)) {
			boneco.andaEsquerda();
			return;
		}
		if (keyboard.keyDown(KeyEvent.VK_RIGHT)) {
			boneco.andaDireita();
			return;
		}
		boneco.ficaParado();

	}

}
