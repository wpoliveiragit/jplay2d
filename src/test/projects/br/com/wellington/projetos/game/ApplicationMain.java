package br.com.wellington.projetos.game;

import br.com.wellington.jplay2D.window.Window;
import br.com.wellington.projetos.game.menus.TelaInicial;

public class ApplicationMain {

	public static void main(String[] args) {
		Window.getInstance(1024, 768);
		new TelaInicial().start();
	}

}
