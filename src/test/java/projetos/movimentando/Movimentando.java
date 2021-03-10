package projetos.movimentando;

import br.com.wellington.jplay2D.imageProcessing.GameImage;
import br.com.wellington.jplay2D.imageProcessing.Physics;
import br.com.wellington.jplay2D.imageProcessing.Sprite;
import br.com.wellington.jplay2D.oi.Keyboard;
import br.com.wellington.jplay2D.window.Window;
import projetos.utils.Constantes;

/**
 *
 * @author Leandro Emiliano Guimarães ---UFF--- Computer Science
 */
public class Movimentando {
	private static final long serialVersionUID = 1L;

	private GameImage fundo;
	private Sprite quadrado;
	private Sprite ground;
	private Physics fisica;
	private boolean loop = true;
	private Window win;

	public Movimentando() {
		win = Window.create(800, 600);
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
			if (win.getKeyboard().keyDown(Keyboard.ESCAPE_KEY)) {
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
			win.update();
			win.delay(12);

		}
		win.exit();
	}

}
