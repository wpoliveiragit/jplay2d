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
public class Movimentando extends Window {
	private static final long serialVersionUID = 1L;

	private GameImage fundo;
	private Sprite quadrado;
	private Sprite ground;
	private Physics fisica;
	private boolean loop = true;

	public Movimentando() {
		super(800, 600);
		fundo = new GameImage(Constantes.MOVIMENTANDO_FUNDO);

		fisica = new Physics();
		fisica.createWorld(800, 600);

		quadrado = new Sprite(Constantes.MOVIMENTANDO_SQUARE);

		fisica.createBodyFromSprite(quadrado, false);

		quadrado.setX(400);
		quadrado.setY(0);

		ground = new Sprite(Constantes.MOVIMENTANDO_GROUND);
		fisica.createBodyFromSprite(ground, true);

		ground.setY(500);
		ground.setFriction(0);
	}

	public void start() {
		while (loop) {
			// Se apertar a tecla ESC, sai da tela inicial.
			if (getKeyboard().keyDown(Keyboard.ESCAPE_KEY)) {
				loop = false;
			}
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
			update();
			delay(12);

		}
		exit();
	}

}
