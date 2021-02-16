package projetos.pacman;

import jplay.GameImage;
import jplay.Keyboard;
import jplay.Physics;
import jplay.SpritePlatform;
import jplay.SpritePlatformBody;
import jplay.Window;

public class Test_Movimentando {

	private GameImage fundo;
	private SpritePlatformBody quadrado;
	private SpritePlatformBody chao;
	private Physics fisica;

	public Test_Movimentando() {
		new Window(800, 600);

		// adiciona um atraso na atualiza��o
		Window.getInstance().setDelay(12);

		// adiciona as chaves que o teclado ir� usar
		Window.getInstance().getKeyboard().addKeyPress(Keyboard.VK_LEFT);
		Window.getInstance().getKeyboard().addKeyPress(Keyboard.VK_RIGHT);
		Window.getInstance().getKeyboard().addKeyPress(Keyboard.VK_DOWN);
		Window.getInstance().getKeyboard().addKeyPress(Keyboard.VK_UP);
		Window.getInstance().getKeyboard().addKeyPress(Keyboard.VK_ESCAPE);

		// cria as imagens e as posiciona na janela
		fundo = new GameImage(PathsTest.FUNDO);
		quadrado = new SpritePlatformBody(PathsTest.QUADRADO, 1, false);
		quadrado.setX(400);
		quadrado.setY(0);

		chao = new SpritePlatformBody(PathsTest.CHAO, 1, false);
		chao.setY(500);
		chao.setFriction(0);

		// adiciona a f�sica dos objetos
		fisica = new Physics();
		fisica.createWorld(800, 600);
		fisica.createBodyFromSprite(quadrado, false);
		fisica.createBodyFromSprite(chao, true);

	}

	public void start() {
		Keyboard kb = Window.getInstance().getKeyboard();
		while (true) {
			fundo.draw();
			fisica.update();
			Window.getInstance().update();

			if (quadrado.getX() > 0) {
				if (kb.conpareKey(Keyboard.VK_LEFT)) {
					quadrado.applyForceXFromKeyboardLeft(1);
				}
			} else {
				quadrado.cancelForcesAndSetBoundsX(0, true);
			}

			if (quadrado.getX() + quadrado.getWidth() < 800) {
				if (kb.conpareKey(Keyboard.VK_RIGHT)) {
					quadrado.applyForceXFromKeyboardRight(1);
				}
			} else {
				quadrado.cancelForcesAndSetBoundsX(800, false);
			}

			// Outra opção
			quadrado.applyForceXFromKeyboard(kb.conpareKey(Keyboard.VK_LEFT), kb.conpareKey(Keyboard.VK_RIGHT), 1, true);

			// O mesmo poderia ser feito no eixo Y(limitar manualmente os limites).
			quadrado.applyForceYFromKeyboard(kb.conpareKey(Keyboard.VK_UP), kb.conpareKey(Keyboard.VK_DOWN), 50, true);

			if (kb.conpareKey(Keyboard.VK_ESCAPE)) {
				Window.getInstance().exit();
			}

			Window.getInstance().delay();

		}
	}

	public static void main(String[] args) {
		new Test_Movimentando().start();
	}
}
