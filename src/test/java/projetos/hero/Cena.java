package projetos.hero;

import jplay.Controller;
import jplay.DrawImage;
import jplay.Keyboard;
import jplay.Scene;
import jplay.Sound_Original;
import jplay.Window;

public class Cena implements ControleJanela {

	private Window janela;
	private Keyboard teclado;
	private Scene cena;

	private DrawImage backDrop;
	private Controller controle;

	private Hero hero;
	private Morcego morcego;

	public Cena() {
		janela = Window.getInstance();
		teclado = janela.getKeyboard();
		teclado.clean();
		teclado.addKey(Keyboard.VK_ENTER);
		
		

		cena = new Scene("scene_hero.scn");
		cena.changeTile(2, 2, 3);
		cena.removeTile(2, 2);
		cena.saveToFile("scene_hero_copia.scn");
		cena.loadScene("scene_hero_copia.scn");
		cena.setDrawStartPos(1, 32);

		hero = new Hero(100, 176, 326);
		morcego = new Morcego(100, 100);
		cena.addOverlay(morcego.getGameObject());

		controle = new Controller() {

			@Override
			public void control() {
				hero.action();
				morcego.action();
				if (teclado.conpareKey(Keyboard.VK_ENTER)) {
					_MainGame.controleJanela = new MenuPrincipal();
				}
			}

		};
		backDrop = new DrawImage() {

			@Override
			public void draw() {
				cena.draw();
			}
		};
	}

	@Override
	public void draw() {
		backDrop.draw();
		hero.draw();
	}

	@Override
	public void control() {
		controle.control();
	}

}
