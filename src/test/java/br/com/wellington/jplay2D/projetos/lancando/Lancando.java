package br.com.wellington.jplay2D.projetos.lancando;

import br.com.wellington.jplay2D.imageProcessing.GameImage;
import br.com.wellington.jplay2D.imageProcessing.Physics;
import br.com.wellington.jplay2D.imageProcessing.Sprite;
import br.com.wellington.jplay2D.oi.Keyboard;
import br.com.wellington.jplay2D.utils.Constantes;
import br.com.wellington.jplay2D.window.Window;

public class Lancando {

	public static void main(String[] args) {
		Window win = new Window(800, 600);
		Keyboard keyboard = win.getKeyboard();
		GameImage fundo = new GameImage(Constantes.LANCANDO_BACKDROP);
		Keyboard teclado = win.getKeyboard();

		teclado.addKey(Keyboard.RIGHT_KEY);
		teclado.addKey(Keyboard.LEFT_KEY);

		Physics fisica = new Physics();
		fisica.createWorld(800, 600);

		Sprite quadrado = new Sprite(Constantes.LANCANDO_TILE_BLOCO_1);

		fisica.createBodyFromSprite(quadrado, false);

		quadrado.setX(200);
		quadrado.setY(0);

		quadrado.setBullet(true); // Evita que o sprite ignore a colisÃ£o com outro objeto.

		Sprite ground = new Sprite(Constantes.LANCANDO_GROUND);
		fisica.createBodyFromSprite(ground, true);

		ground.setY(500);
		ground.setFriction(0);

		Sprite spr = new Sprite(Constantes.LANCANDO_SPRITE);
		fisica.createBodyFromSprite(spr, true);

		spr.setX(100);
		spr.setY(200);

		Sprite boundX = new Sprite(Constantes.LANCANDO_LIMITE);
		fisica.createBodyFromSprite(boundX, true);

		Sprite boundY = new Sprite(Constantes.LANCANDO_LIMITE);
		fisica.createBodyFromSprite(boundY, true);

		boundY.setRotation(Math.PI / 2);
		boundY.setX(-375);
		boundY.setY(300);

		Sprite boundY2 = new Sprite(Constantes.LANCANDO_LIMITE);
		fisica.createBodyFromSprite(boundY2, true);

		boundY2.setRotation(Math.PI / 2);
		boundY2.setX(375);
		boundY2.setY(300);

		while (true) {

			fundo.draw();
			if (teclado.keyDown(Keyboard.RIGHT_KEY))
				quadrado.applyForceX(5000);

			if (teclado.keyDown(Keyboard.LEFT_KEY))
				quadrado.applyForceX(-5000);

			if (teclado.keyDown(Keyboard.SPACE_KEY))
				quadrado.cancelForces();

			// Se apertar a tecla ESC, sai da tela inicial.
			if (keyboard.keyDown(Keyboard.ESCAPE_KEY))
				break;

			fisica.update();
			win.update();
			win.delay(12);
		}
		win.exit();
	}
}
