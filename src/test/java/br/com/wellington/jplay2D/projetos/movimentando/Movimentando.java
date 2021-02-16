package br.com.wellington.jplay2D.projetos.movimentando;

import br.com.wellington.jplay2D.GameImage;
import br.com.wellington.jplay2D.Keyboard;
import br.com.wellington.jplay2D.Physics;
import br.com.wellington.jplay2D.Sprite;
import br.com.wellington.jplay2D.Window;
import br.com.wellington.jplay2D.utils.Constantes;

/**
 *
 * @author Leandro Emiliano Guimarães ---UFF--- Computer Science
 */
public class Movimentando {

	public static void main(String[] args) {

		Window win = new Window(800, 600);
		GameImage fundo = new GameImage(Constantes.MOVIMENTANDO_FUNDO);

		Physics fisica = new Physics();
		fisica.createWorld(800, 600);

		Sprite quadrado = new Sprite(Constantes.MOVIMENTANDO_SQUARE);

		fisica.createBodyFromSprite(quadrado, false);

		quadrado.setX(400);
		quadrado.setY(0);

		Sprite ground = new Sprite(Constantes.MOVIMENTANDO_GROUND);
		fisica.createBodyFromSprite(ground, true);

		ground.setY(500);
		ground.setFriction(0);

		while (true) {
			// Se apertar a tecla ESC, sai da tela inicial.
			if (win.getKeyboard().keyDown(Keyboard.ESCAPE_KEY))
				break;
			fundo.draw();

			if (quadrado.getX() > 0)
				quadrado.applyForceXFromKeyboardLeft(Keyboard.LEFT_KEY, 1, Keyboard.DETECT_EVERY_PRESS);
			else
				quadrado.cancelForcesAndSetBoundsX(0, true);
			if (quadrado.getX() + quadrado.width < 800)
				quadrado.applyForceXFromKeyboardRight(Keyboard.RIGHT_KEY, 1, Keyboard.DETECT_EVERY_PRESS);
			else
				quadrado.cancelForcesAndSetBoundsX(800, false);

			// Outra opção :

			quadrado.applyForceXFromKeyboard(Keyboard.LEFT_KEY, Keyboard.RIGHT_KEY, 1, Keyboard.DETECT_EVERY_PRESS,
					true);

			// O mesmo poderia ser feito no eixo Y(limitar manualmente os limites).
			quadrado.applyForceYFromKeyboard(Keyboard.UP_KEY, Keyboard.DOWN_KEY, 50, Keyboard.DETECT_EVERY_PRESS, true);

			fisica.update();
			win.update();
			win.delay(12);

		}
		win.exit();
	}
}
