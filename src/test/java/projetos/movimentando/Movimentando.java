package projetos.movimentando;

import java.awt.event.KeyEvent;

import br.com.wellington.jplay2D.image.GameImage;
import br.com.wellington.jplay2D.image.Physics;
import br.com.wellington.jplay2D.image.Sprite;
import br.com.wellington.jplay2D.oi.Keyboard;
import br.com.wellington.jplay2D.window.Window;
import projetos.utils.Constantes;

/**
 *
 * @author Leandro Emiliano Guimarães ---UFF--- Computer Science
 */
public class Movimentando {
	private GameImage fundo;
	private Sprite quadrado;
	private Sprite ground;
	private Physics fisica;
	private boolean loop = true;
	private Window win;

	public Movimentando() {
		win = Window.getInstance(800, 600);
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
		configuration();
	}

	private void configuration() {
		Keyboard keyboard = win.getKeyboard();
		keyboard.addKeyBehaviorActuatorRequest(KeyEvent.VK_ESCAPE);
		keyboard.addKeyBehaviorActuatorRequest(KeyEvent.VK_ENTER);
		keyboard.addKeyBehaviorActuatorRequest(KeyEvent.VK_SPACE);

		keyboard.addKeyBehaviorActuatorRequestPress(KeyEvent.VK_UP);
		keyboard.addKeyBehaviorActuatorRequestPress(KeyEvent.VK_DOWN);
		keyboard.addKeyBehaviorActuatorRequestPress(KeyEvent.VK_LEFT);
		keyboard.addKeyBehaviorActuatorRequestPress(KeyEvent.VK_RIGHT);
	}

	public void start() {
		while (loop) {
			// Se apertar a tecla ESC, sai da tela inicial.
			if (win.getKeyboard().checkKey(KeyEvent.VK_ESCAPE)) {
				loop = false;
			}
			fundo.draw();

			if (quadrado.getX() > 0)
				quadrado.applyForceXFromKeyboardLeft(KeyEvent.VK_LEFT, 1, Keyboard.ACTUATOR_REQUEST_PRESS);
			else
				quadrado.cancelForcesAndSetBoundsX(0, true);
			if (quadrado.getX() + quadrado.width < 800)
				quadrado.applyForceXFromKeyboardRight(KeyEvent.VK_RIGHT, 1, Keyboard.ACTUATOR_REQUEST_PRESS);
			else
				quadrado.cancelForcesAndSetBoundsX(800, false);

			// Outra opção :

			quadrado.applyForceXFromKeyboard(KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, 1, Keyboard.ACTUATOR_REQUEST_PRESS,
					true);

			// O mesmo poderia ser feito no eixo Y(limitar manualmente os limites).
			quadrado.applyForceYFromKeyboard(KeyEvent.VK_UP, KeyEvent.VK_DOWN, 50, Keyboard.ACTUATOR_REQUEST_PRESS,
					true);

			fisica.update();
			win.update();
			win.delay(12);

		}
		win.exit();
	}

}
