package br.com.wellington.projetos.game.cenarios;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.Vector;

import br.com.wellington.jplay2D.image.TileInfo;
import br.com.wellington.jplay2D.scenery.Scenery;
import br.com.wellington.projetos.game.constants.Constants;
import br.com.wellington.projetos.game.personagens.Jogador;
import projetos.jogoDaMemoria.GameControl;

public class Cenario01 extends GameControl {

	private Scenery cena;

	private Jogador megaMan;

	public Cenario01() {
		super(30);
	}

	@Override
	protected void loadResources() {
		KEYBOARD = WINDOW.getKeyboard();
		KEYBOARD.cleanKeys();
		KEYBOARD.addKeyBehaviorActuatorRequest(KeyEvent.VK_SPACE);
		KEYBOARD.addKeyBehaviorActuatorRequest(KeyEvent.VK_ESCAPE);
		KEYBOARD.addKeyBehaviorActuatorRequest(KeyEvent.VK_ENTER);

		KEYBOARD.addKeyBehaviorActuatorRequestPress(KeyEvent.VK_W);
		KEYBOARD.addKeyBehaviorActuatorRequestPress(KeyEvent.VK_A);
		KEYBOARD.addKeyBehaviorActuatorRequestPress(KeyEvent.VK_S);
		KEYBOARD.addKeyBehaviorActuatorRequestPress(KeyEvent.VK_D);

	}

	@Override
	protected void beforeStart() {
		// [SCENE]
		cena = new Scenery();
		cena.loadFromFile(Constants.SCN_CENA01, Constants.PATH_TILE);

		// [SPRITE]
		megaMan = new Jogador();
		megaMan.x = -0;
		megaMan.y = 705;
		megaMan.setFloor(753);
		cena.addSceneElements(megaMan);

	}

	@Override
	protected void update() {

	}

	@Override
	protected void draw() {
		megaMan.fall();
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

			Point min = new Point((int) megaMan.x, (int) megaMan.y);
			Point max = new Point((int) (megaMan.x + megaMan.width + 20), ((int) megaMan.y) + megaMan.height + 20);

			Vector<TileInfo> quadrante = cena.getTilesFromRect(min, max);
			for (TileInfo tile : quadrante) {
				if (tile.id == 4) {
					megaMan.y -= 1;
					megaMan.setFloor((int) tile.x);
				} else if (tile.id == 7) {
					megaMan.y += 1;
					megaMan.setFloor((int) tile.x);
				}
			}
		} else if (KEYBOARD.checkKey(KeyEvent.VK_W)) {
			megaMan.y -= 3;
		} else if (KEYBOARD.checkKey(KeyEvent.VK_S)) {
			megaMan.y += 3;
		}

		megaMan.x += cena.getXOffset();
		megaMan.y += cena.getYOffset();
	}

	@Override
	protected void afterStart() {
		cena.save("src/test/resources/geral/scene/Teste_scene01.scn");
	}

}
