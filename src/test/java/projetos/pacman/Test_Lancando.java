/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projetos.pacman;

import jplay.GameImage;
import jplay.Keyboard;
import jplay.Physics;
import jplay.SpritePlatform;
import jplay.SpritePlatformBody;
import jplay.Window;

public class Test_Lancando {

	public Test_Lancando() {

		new Window(800, 600);
		Window.getInstance().setDelay(12);
		GameImage fundo = new GameImage(PathsTest.FUNDO);

		Window.getInstance().getKeyboard().addKey(Keyboard.VK_LEFT);
		Window.getInstance().getKeyboard().addKey(Keyboard.VK_RIGHT);
		Window.getInstance().getKeyboard().addKey(Keyboard.VK_SPACE);
		Window.getInstance().getKeyboard().addKey(Keyboard.VK_ESCAPE);

		Physics fisica = new Physics();
		fisica.createWorld(800, 600);

		SpritePlatformBody quadrado = new SpritePlatformBody(PathsTest.QUADRADO, 1, false);

		fisica.createBodyFromSprite(quadrado, false);

		quadrado.setX(200);
		quadrado.setY(0);

		quadrado.setBullet(true); // Evita que o sprite ignore a colis�o com outro objeto.

		SpritePlatformBody ground = new SpritePlatformBody(PathsTest.CHAO, 1, false);
		fisica.createBodyFromSprite(ground, true);

		ground.setY(500);
		ground.setFriction(0);

		SpritePlatformBody spr = new SpritePlatformBody(PathsTest.BARREIRA, 1, false);
		fisica.createBodyFromSprite(spr, true);

		spr.setX(100);
		spr.setY(200);

		SpritePlatformBody boundX = new SpritePlatformBody(PathsTest.LIMITE, 1, false);
		fisica.createBodyFromSprite(boundX, true);

		SpritePlatformBody boundY = new SpritePlatformBody(PathsTest.LIMITE, 1, false);
		fisica.createBodyFromSprite(boundY, true);

		boundY.setRotation(Math.PI / 2);
		boundY.setX(-375);
		boundY.setY(300);

		SpritePlatformBody boundY2 = new SpritePlatformBody(PathsTest.LIMITE, 1, false);
		fisica.createBodyFromSprite(boundY2, true);

		boundY2.setRotation(Math.PI / 2);
		boundY2.setX(375);
		boundY2.setY(300);

		while (true) {

			fundo.draw();
			if (Window.getInstance().getKeyboard().conpareKey(Keyboard.VK_RIGHT))
				quadrado.applyForceX(5000);

			if (Window.getInstance().getKeyboard().conpareKey(Keyboard.VK_SPACE))
				quadrado.cancelForces();

			if (Window.getInstance().getKeyboard().conpareKey(Keyboard.VK_LEFT))
				quadrado.applyForceX(-5000);

			if (Window.getInstance().getKeyboard().conpareKey(Keyboard.VK_ESCAPE))
				Window.getInstance().exit();

			fisica.update();
			Window.getInstance().update();
			Window.getInstance().delay();
		}

	}

	public static void main(String[] args) {
		new Test_Lancando();
	}
}
