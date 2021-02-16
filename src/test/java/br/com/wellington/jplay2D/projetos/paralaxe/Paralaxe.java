/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.wellington.jplay2D.projetos.paralaxe;

import br.com.wellington.jplay2D.Keyboard;
import br.com.wellington.jplay2D.Parallax;
import br.com.wellington.jplay2D.Window;
import br.com.wellington.jplay2D.utils.Constantes;

/**
 *
 * @author Leandro Emiliano Guimarães ---UFF--- Computer Science
 */
public class Paralaxe {

	public static void main(String[] args) {

		Window janela = new Window(800, 600);
		Parallax p = new Parallax();
		// O primeiro adicionado será o último a ser pintado.
		// Como o f0.png foi o último a ser adicionado a lista, ele será a camada
		// principal(mainLayer).
		p.add(Constantes.FUNDO_4);
		p.add(Constantes.FUNDO_3);
		p.add(Constantes.FUNDO_2);
		p.add(Constantes.FUNDO_1);
		p.add(Constantes.FUNDO_0);
		p.setVelAllLayers(1, 0);
		while (true) {
			if (janela.getKeyboard().keyDown(Keyboard.ESCAPE_KEY))
				break;
			p.drawLayers();
			// O metódo abaixo é responsável em manter a repetição infinita das
			// camadas.
			p.repeatLayers(800, 600, true);
			p.moveLayersStandardX(true);
			janela.update();
		}
		janela.exit();
	}

}
