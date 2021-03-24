package testFramework;

import java.awt.event.KeyEvent;

import br.com.wellington.jplay2D.scene.Scene;
import projetos.jogoDaMemoria.GameControl;
import projetos.megaMan.MegaManMain;

public class Cena01 extends GameControl {

	private Scene cena;

	private Jogador megaMan;

	public Cena01() {
		super(30);
	}

	@Override
	protected void loadResources() {
		KEYBOARD = WINDOW.getKeyboard();
		KEYBOARD.cleanKeys();
		KEYBOARD.addKeyBehaviorActuatorRequest(KeyEvent.VK_ESCAPE);
		KEYBOARD.addKeyBehaviorActuatorRequest(KeyEvent.VK_ENTER);
		KEYBOARD.addKeyBehaviorActuatorRequest(KeyEvent.VK_W);
		KEYBOARD.addKeyBehaviorActuatorRequestPress(KeyEvent.VK_A);
		KEYBOARD.addKeyBehaviorActuatorRequest(KeyEvent.VK_S);
		KEYBOARD.addKeyBehaviorActuatorRequestPress(KeyEvent.VK_D);
		KEYBOARD.addKeyBehaviorActuatorRequest(KeyEvent.VK_SPACE);
	}

	@Override
	protected void beforeStart() {
		// [SCENE]
		cena = new Scene();
		cena.loadFromFile(TestMain.SCN_CENA01, TestMain.PATH_TILE);

		// [SPRITE]
		megaMan = new Jogador();
		megaMan.x = 0;
		megaMan.y = 0;
		megaMan.setFloor(200);
		cena.addSceneElements(megaMan);

	}

	@Override
	protected void updateScene() {
		megaMan.jump();
	}

	@Override
	protected void draw() {
		megaMan.update();
		cena.drawnMoveScene(megaMan);
	}

	@Override
	protected void control() {
		if (KEYBOARD.checkKey(KeyEvent.VK_ESCAPE)) {// volta a tela inicial
			super.exist();
		}
		if (KEYBOARD.checkKey(KeyEvent.VK_A)) {
			megaMan.x -= 3;

		} else if (KEYBOARD.checkKey(KeyEvent.VK_D)) {
			megaMan.x += 3;
		}

		megaMan.x += cena.getXOffset();
	}

	@Override
	protected void afterStart() {
		cena.save("src/test/resources/geral/scene/Teste_scene01.scn");
	}

}
