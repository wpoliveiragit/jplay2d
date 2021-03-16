package projetos.rotacionando;

import java.awt.event.KeyEvent;

import br.com.wellington.jplay2D.imageProcessing.GameImage;
import br.com.wellington.jplay2D.imageProcessing.Physics;
import br.com.wellington.jplay2D.imageProcessing.Sprite;
import br.com.wellington.jplay2D.oi.InputBase;
import br.com.wellington.jplay2D.oi.Keyboard;
import br.com.wellington.jplay2D.window.Window;
import projetos.utils.Constantes;

public class Rotacionando implements Constantes {

	public static void main(String[] args) {

		Window win = Window.create(800, 600);
		GameImage fundo = new GameImage(ROTACIONANDO_FUNDO);

		Physics fisica = new Physics();
		fisica.createWorld(800, 600);

		Sprite quadrado = new Sprite(ROTACIONANDO_SQUARE);

		fisica.createBodyFromSprite(quadrado, false);

		quadrado.setX(200);
		quadrado.setY(0);

		Sprite ground = new Sprite(ROTACIONANDO_GROUND);
		fisica.createBodyFromSprite(ground, true);

		ground.setY(500);
		ground.setFriction(0);

		Sprite spr = new Sprite(ROTACIONANDO_SPRITE);
		fisica.createBodyFromSprite(spr, true);

		// Lembrando que o método setRotation deve ser chamada antes dos metódos setX
		// e setY.
		// Os metódos setX e setY devem ser chamados mesmo que suas posições sejam
		// 0,0.

		spr.setRotation(Math.PI / 4);
		spr.setX(100);
		spr.setY(100);

		Sprite spr2 = new Sprite(ROTACIONANDO_SPRITE);
		fisica.createBodyFromSprite(spr2, true);

		spr2.setRotation((Math.PI * 135) / 180);
		spr2.setX(300);
		spr2.setY(300);

		// Abaixo são apenas sprites que serão os limites da tela,
		// Dessa forma, a colisão entre o quadrado e os limites são executados.

		Sprite boundX = new Sprite(ROTACIONANDO_LIMITE);
		fisica.createBodyFromSprite(boundX, true);

		Sprite boundY = new Sprite(ROTACIONANDO_LIMITE);
		fisica.createBodyFromSprite(boundY, true);
		boundY.setRotation(Math.PI / 2);
		boundY.setX(-375);
		boundY.setY(300);

		Sprite boundY2 = new Sprite(ROTACIONANDO_LIMITE);
		fisica.createBodyFromSprite(boundY2, true);
		boundY2.setRotation(Math.PI / 2);
		boundY2.setX(375);
		boundY2.setY(300);

		Keyboard keyboard = win.getKeyboard();

		keyboard.addKeyPressed(KeyEvent.VK_ESCAPE);
		keyboard.addKeyPressed(KeyEvent.VK_ENTER);
		keyboard.addKeyPressed(KeyEvent.VK_SPACE);

		keyboard.addKeyHeldDown(KeyEvent.VK_UP);
		keyboard.addKeyHeldDown(KeyEvent.VK_DOWN);
		keyboard.addKeyHeldDown(KeyEvent.VK_LEFT);
		keyboard.addKeyHeldDown(KeyEvent.VK_RIGHT);

		while (true) {

			fundo.draw();
			// Não é necessário agora setar como 'true' no último parâmetro deste
			// metódo, pois os sprites boundX, boundY e boundY2
			// já são encarregados de limitar o quadrado dentro da tela.

			quadrado.applyForceXFromKeyboard(KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, 1, InputBase.BEHAVIOR_KEY_HELD_DOWN,
					false);
			quadrado.applyForceYFromKeyboard(KeyEvent.VK_UP, KeyEvent.VK_DOWN, 50, InputBase.BEHAVIOR_KEY_HELD_DOWN,
					false);
			// Se apertar a tecla ESC, sai da tela inicial.
			if (win.getKeyboard().keyDown(KeyEvent.VK_ESCAPE))
				break;

			fisica.update();
			win.update();
			win.delay(12);
		}
		win.exit();
	}
}
