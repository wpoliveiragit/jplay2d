package projetos.hero;

import java.awt.Point;

import jplay.DrawImage;
import jplay.GameObject;
import jplay.Mouse;
import jplay.SpriteBase;
import jplay.SpritePlatform;
import jplay.Window;

public class Morcego implements DrawImage {

	// #############################################################################
	private Mouse mouse = Window.getInstance().getMouse();
	{
		mouse.setButton(Mouse.MOUSE_COD_BUTTON_LEFT);
	}

	// #############################################################################
	private static final int[] LADO_ESQUERDO = new int[] { 0, 8 };
	private static final int[] LADO_DIREITA = new int[] { 8, 16 };
	private static final int VELOCIDADE_VOO = 4;

	// #############################################################################
	private SpriteBase sprite;

	public Morcego(int x, int y) {
		sprite = new SpritePlatform("morcego.png", 16, true);
		sprite.setTotalDuration(600);
		sprite.setX(x);
		sprite.setY(y);
		sprite.setSpeed(VELOCIDADE_VOO);
		sprite.setGrab(LADO_DIREITA[0], LADO_DIREITA[1]);
	}

	public void action() {
		if (mouse.isLeftButtonPressed()) {// botão direito clicado
			Point p = mouse.getPosition();
			sprite.moveXY(p.x, p.y);
			if (sprite.getX() < p.x) {
				sprite.setGrab(LADO_DIREITA[0], LADO_DIREITA[1]);
			} else {
				sprite.setGrab(LADO_ESQUERDO[0], LADO_ESQUERDO[1]);
			}
		}
		sprite.moveXY();
		sprite.update();
	}

	@Override
	public void draw() {
		sprite.draw();
	}

	public GameObject getGameObject() {
		return sprite;
	}

}
