package testFramework;

import br.com.wellington.jplay2D.image.Sprite;
import br.com.wellington.jplay2D.oi.Keyboard;
import br.com.wellington.jplay2D.oi.Mouse;
import br.com.wellington.jplay2D.scene.Scene;
import projetos.jogoDaMemoria.GameControl;

public class Cena01 extends GameControl {

	private Keyboard keyboardOld;
	private Keyboard keyboard;

	private Mouse mouseOld;
	private Mouse mouse;

	private Scene cena;

	private Sprite moeda;

	public Cena01() {
		super(30);
	}

	@Override
	protected void init() {
		cena = new Scene();
		cena.loadFromFile(TestMain.SCN_CENA01, TestMain.PATH_TILE);

		keyboardOld = WINDOW.getKeyboard();
		keyboard = new Keyboard();
		WINDOW.setKeyboard(keyboard);

		mouseOld = WINDOW.getMouse();
		mouse = new Mouse();
		WINDOW.setMouse(mouse);

		moeda = new Sprite(TestMain.SPT_MOEDA_OURO, 10);
		moeda.setTotalDuration(175);
		moeda.setLoop(true);
		
	}

	@Override
	protected void draw() {
		cena.drawnMoveScene(moeda);
		moeda.update();
		moeda.draw();
	}

	@Override
	protected void control() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void end() {
		WINDOW.setKeyboard(keyboardOld);
		WINDOW.setMouse(mouseOld);
	}

	@Override
	protected void collisionCheck() {
		// TODO Auto-generated method stub

	}

}
