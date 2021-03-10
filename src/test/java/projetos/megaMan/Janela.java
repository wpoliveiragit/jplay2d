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
		window =  Window.create(x, y);
		keyboard = window.getKeyboard();
		keyboard.addKey(KeyEvent.VK_CONTROL);
		background = new GameImage(MegaManApplication.IMG_BD);
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
		if (keyboard.keyDown(Keyboard.ESCAPE_KEY)) {
			loop = false;
		}

		boneco.jump();

		if (keyboard.keyDown(KeyEvent.VK_CONTROL)) {
			balas.add(boneco.atira());
		}

		if (keyboard.keyDown(Keyboard.LEFT_KEY)) {
			boneco.andaEsquerda();
			return;
		}
		if (keyboard.keyDown(Keyboard.RIGHT_KEY)) {
			boneco.andaDireita();
			return;
		}
		boneco.ficaParado();

	}

}
