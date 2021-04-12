package br.com.wellington.projetos.game.cenarios;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.List;

import br.com.wellington.jplay2D.image.TileInfo;
import br.com.wellington.jplay2D.scenery.Scenery;
import br.com.wellington.projetos.game.personagens.Jogador;
import br.com.wellington.projetos.game.utils.Constants;
import projetos.jogoDaMemoria.GameControl;

public class Cenario01 extends GameControl {

	private Scenery cena;

	private Jogador megaMan;

	public Cenario01() {
		super(30);
	}

	@Override
	protected void load() {
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
		megaMan.x = 5;
		megaMan.y = 0;
		megaMan.setFloor(752);
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
		// [VOLTA A TELA INICIAL]
		if (KEYBOARD.checkKey(KeyEvent.VK_ESCAPE)) {
			super.exist();
		}

		// [PERSONAGEM ANDA PRA ESQUERDA]
		if (KEYBOARD.checkKey(KeyEvent.VK_A)) {
			megaMan.x -= 3;
		}

		// [PERSONAGEM ANDA PRA DIREITA]
		else if (KEYBOARD.checkKey(KeyEvent.VK_D)) {
			List<List<TileInfo>> subMatriz = cena.getMatrixFromPosition(megaMan.getHitBox());

			printTest(subMatriz);//remover
			
			final double value = 2;
			Point xy = new Point();

			xy.setLocation(1, 1);// (2,2)
			TileInfo t = subMatriz.get(xy.y).get(xy.x);
			if (t.id != 9) {
				megaMan.x += 3;
				
				xy.setLocation(0, 3);// (1,4)
				t = subMatriz.get(xy.y).get(xy.x);
				if (t.id == 2) {
					megaMan.setFloor(t.y);
				}
				
				
				
				//[subir rampa]
				xy.setLocation(1, 2);// (2,3)
				t = subMatriz.get(xy.y).get(xy.x);
				if (t.id == 4) {
					megaMan.setFloor(megaMan.getFloor() - value);
				}

				xy.setLocation(1, 3);// (2,4)
				t = subMatriz.get(xy.y).get(xy.x);
				if (t.id == 4) {
					megaMan.setFloor(megaMan.getFloor() - value);
				}

				xy.setLocation(0, 3);// (1,4)
				t = subMatriz.get(xy.y).get(xy.x);
				if (t.id == 4) {
					megaMan.setFloor(t.y);
				}
				
				xy.setLocation(1, 2);// (2,3)
				t = subMatriz.get(xy.y).get(xy.x);
				if (t.id == 5) {
					megaMan.setFloor(t.y);
				}
				
				//[descer rampa]
				xy.setLocation(1, 3);// (2,4)
				t = subMatriz.get(xy.y).get(xy.x);
				if (t.id == 7) {
					megaMan.setFloor(megaMan.getFloor() + value);
				}
				
				xy.setLocation(0, 3);// (2,4)
				t = subMatriz.get(xy.y).get(xy.x);
				if (t.id == 7) {
					megaMan.setFloor(megaMan.getFloor() + value);
				}
//
//				xy.setLocation(1, 3);// (2,4)
//				t = subMatriz.get(xy.y).get(xy.x);
//				if (t.id == 4) {
//					megaMan.setFloor(megaMan.getFloor() - value);
//				}
//
//				xy.setLocation(0, 3);// (1,4)
//				t = subMatriz.get(xy.y).get(xy.x);
//				if (t.id == 4) {
//					megaMan.setFloor(t.y);
//				}
//				
//				xy.setLocation(1, 2);// (2,3)
//				t = subMatriz.get(xy.y).get(xy.x);
//				if (t.id == 5) {
//					megaMan.setFloor(t.y);
//				}
			}

		} else if (KEYBOARD.checkKey(KeyEvent.VK_W)) {
			// megaMan.y -= 1;
			megaMan.setFloor(megaMan.getFloor() - 1);
		} else if (KEYBOARD.checkKey(KeyEvent.VK_S)) {
			// megaMan.y += 1;
			megaMan.setFloor(megaMan.getFloor() + 1);
		}

//		megaMan.x += cena.getXOffset();
//		megaMan.y += cena.getYOffset();
	}

	@Override
	protected void afterStart() {
		cena.save("src/test/resources/geral/scene/Teste_scene01.scn", "2");
	}

	// remover
	private void printTest(List<List<TileInfo>> subMatriz) {
		StringBuilder txt = new StringBuilder();
		txt.append("subMatriz[").append(subMatriz.get(0).size()).append("] [").append(subMatriz.size()).append("]");
		for (List<TileInfo> linha : subMatriz) {
			txt.append("\n");
			for (TileInfo t : linha) {
				txt.append(" ").append(t.id);
			}

		}
		System.out.println(txt);
	}

}
