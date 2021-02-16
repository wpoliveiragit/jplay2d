
package projetos.pacman;

import jplay.GameImage;
import jplay.Keyboard;
import jplay.Physics;
import jplay.SpritePlatform;
import jplay.SpritePlatformBody;
import jplay.Window;

public class Test_Rotacionando {

	/** Fundo da janela */
	private GameImage backGround;

	private SpritePlatformBody quadrado;
	private Physics fisica;

	/** CONSTRUTOR */
	public Test_Rotacionando() {
		new Window(800, 600);

		Window.getInstance().getKeyboard().addKeyPress(Keyboard.VK_LEFT);
		Window.getInstance().getKeyboard().addKeyPress(Keyboard.VK_RIGHT);
		Window.getInstance().getKeyboard().addKeyPress(Keyboard.VK_UP);
		Window.getInstance().getKeyboard().addKeyPress(Keyboard.VK_DOWN);
		Window.getInstance().getKeyboard().addKeyPress(Keyboard.VK_ESCAPE);
		Window.getInstance().setDelay(12);

		backGround = new GameImage(PathsTest.FUNDO);

		fisica = new Physics();
		fisica.createWorld(800, 600);
		quadrado = new SpritePlatformBody(PathsTest.QUADRADO, 1, false);
		quadrado.setX(200);
		quadrado.setY(0);
		SpritePlatformBody ground = new SpritePlatformBody(PathsTest.CHAO, 1, false);
		ground.setY(500);
		ground.setFriction(0);

		SpritePlatformBody spr = new SpritePlatformBody(PathsTest.BARREIRA, 1, false);
		/**
		 * O m�todo setRotation deve ser chamada antes dos m�todos setX e setY. Os
		 * m�todos setX e setY devem ser chamados mesmo que suas Posi��es sejam (0,0).
		 */
		spr.setRotation(Math.PI / 4);
		spr.setX(100);
		spr.setY(100);

		SpritePlatformBody spr2 = new SpritePlatformBody(PathsTest.BARREIRA, 1, false);
		spr2.setRotation((Math.PI * 135) / 180);
		spr2.setX(300);
		spr2.setY(300);

		/**
		 * Abaixo s�o apenas sprites que ser�o os limites da tela, Dessa forma, a
		 * colis�o entre o quadrado e os limites ser�o executados.
		 */
		SpritePlatformBody boundX = new SpritePlatformBody(PathsTest.LIMITE, 1, false);

		SpritePlatformBody boundY = new SpritePlatformBody(PathsTest.LIMITE, 1, false);
		boundY.setRotation(Math.PI / 2);
		boundY.setX(-375);
		boundY.setY(300);

		SpritePlatformBody boundY2 = new SpritePlatformBody(PathsTest.LIMITE, 1, false);
		boundY2.setRotation(Math.PI / 2);
		boundY2.setX(375);
		boundY2.setY(300);

		/** Aplicando a fisica */
		fisica.createBodyFromSprite(quadrado, false);
		fisica.createBodyFromSprite(ground, true);
		fisica.createBodyFromSprite(spr, true);
		fisica.createBodyFromSprite(spr2, true);
		fisica.createBodyFromSprite(boundX, true);
		fisica.createBodyFromSprite(boundY, true);
		fisica.createBodyFromSprite(boundY2, true);

	}

	public final void start() {
		Keyboard t = Window.getInstance().getKeyboard();
		while (true) {
			backGround.draw();

			/**
			 * N�o � necess�rop setar como 'true' no �ltimo par�metro deste m�todo, pois os
			 * sprites boundX, boundY e boundY2 j� s�o encarregados de limitar o quadrado
			 * dentro da tela.
			 */
			quadrado.applyForceXFromKeyboard(t.conpareKey(Keyboard.VK_LEFT), t.conpareKey(Keyboard.VK_RIGHT), 1, false);
			quadrado.applyForceYFromKeyboard(t.conpareKey(Keyboard.VK_UP), t.conpareKey(Keyboard.VK_DOWN), 500, false);

			if (t.conpareKey(Keyboard.VK_ESCAPE)) {
				Window.getInstance().exit();
			}
			fisica.update();
			Window.getInstance().update();
			Window.getInstance().delay();
		}
	}

	public static void main(String[] args) {
		new Test_Rotacionando().start();
	}
}
