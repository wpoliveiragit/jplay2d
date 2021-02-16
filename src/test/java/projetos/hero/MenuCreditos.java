package projetos.hero;

import java.awt.Color;

import jplay.Keyboard;
import jplay.Window;

public class MenuCreditos implements ControleJanela {

	private Window janela;
	private Keyboard teclado;

	public MenuCreditos() {
		janela = Window.getInstance();
		teclado = janela.getKeyboard();
		teclado.clean();
		teclado.addKey(Keyboard.VK_ENTER);
	}

	@Override
	public void draw() {
		janela.clear(Color.blue);
		janela.write(400, 300, Color.GREEN, "Tecle entar para voltar ao menu principal");
	}

	@Override
	public void control() {
		if (teclado.conpareKey(Keyboard.VK_ENTER)) {
			_MainGame.controleJanela = new MenuPrincipal();
		}

	}

}
