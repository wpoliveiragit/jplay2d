package projetos.lancando;

import java.awt.event.KeyEvent;

import br.com.wellington.jplay2D.imageProcessing.GameImage;
import br.com.wellington.jplay2D.imageProcessing.Physics;
import br.com.wellington.jplay2D.imageProcessing.Sprite;
import br.com.wellington.jplay2D.oi.Keyboard;
import br.com.wellington.jplay2D.window.Window;

public class Lancando {

	private static boolean LOOP;

	private Window windows;
	private Keyboard keyboard;
	private GameImage backdrop;
	private Physics fisica;
	private Sprite quadrado;
	private Sprite sprite;
	private Sprite boundX;

	private Sprite ground;
	private Sprite boundY;
	private Sprite boundY2;

	public Lancando(int width, int height) {
		backdrop = new GameImage(Main.BACKDROP);

		fisica = new Physics();
		fisica.createWorld(width, height);

		quadrado = new Sprite(Main.TILE_BLOCO);
		quadrado.setX(200);
		quadrado.setY(0);

		fisica.createBodyFromSprite(quadrado, false);

		windows = Window.create(width, height);
		keyboard = windows.getKeyboard();
		keyboard.addKeyPressed(KeyEvent.VK_RIGHT);
		keyboard.addKeyPressed(KeyEvent.VK_LEFT);

		quadrado.setBullet(true); // Evita que o sprite ignore a colisão com outro objeto.

		ground = new Sprite(Main.GROUND);
		fisica.createBodyFromSprite(ground, true);

		ground.setY(500);
		ground.setFriction(0);

		sprite = new Sprite(Main.SPRITE);
		fisica.createBodyFromSprite(sprite, true);

		sprite.setX(100);
		sprite.setY(200);

		boundX = new Sprite(Main.LIMITE);
		fisica.createBodyFromSprite(boundX, true);

		boundY = new Sprite(Main.LIMITE);
		fisica.createBodyFromSprite(boundY, true);

		boundY.setRotation(Math.PI / 2);
		boundY.setX(-375);
		boundY.setY(300);

		boundY2 = new Sprite(Main.LIMITE);
		fisica.createBodyFromSprite(boundY2, true);

		boundY2.setRotation(Math.PI / 2);
		boundY2.setX(375);
		boundY2.setY(300);
		LOOP = false;
		configuration();
	}

	private void configuration() {
		keyboard.addKeyPressed(KeyEvent.VK_ESCAPE);
		keyboard.addKeyPressed(KeyEvent.VK_ENTER);
		keyboard.addKeyPressed(KeyEvent.VK_SPACE);

		keyboard.addKeyHeldDown(KeyEvent.VK_UP);
		keyboard.addKeyHeldDown(KeyEvent.VK_DOWN);
		keyboard.addKeyHeldDown(KeyEvent.VK_LEFT);
		keyboard.addKeyHeldDown(KeyEvent.VK_RIGHT);
	}

	public void start() {
		if (LOOP) {
			return;
		}
		LOOP = true;
		while (LOOP) {
			backdrop.draw();
			controle();
			fisica.update();
			windows.update();
			windows.delay(12);
		}
		windows.exit();
	}

	private void controle() {
		if (keyboard.keyDown(KeyEvent.VK_RIGHT)) {
			quadrado.applyForceX(5000);
		}
		if (keyboard.keyDown(KeyEvent.VK_LEFT)) {
			quadrado.applyForceX(-5000);
		}
		if (keyboard.keyDown(KeyEvent.VK_SPACE)) {
			quadrado.cancelForces();
		}
		// Se apertar a tecla ESC, sai da tela inicial.
		if (keyboard.keyDown(KeyEvent.VK_ESCAPE)) {
			LOOP = false;
		}
	}

}
