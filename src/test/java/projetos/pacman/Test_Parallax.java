/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projetos.pacman;

import jplay.Parallax;
import jplay.Window;

public class Test_Parallax {

	private Parallax parallax;

	public Test_Parallax() {
		new Window(800, 600);
		/** O primeiro adicionado ser� o ultimo a ser desenhado */
		parallax = new Parallax();
		parallax.add(PathsTest.FUNDO_F0);
		parallax.add(PathsTest.FUNDO_F1);
		parallax.add(PathsTest.FUNDO_F2);
		parallax.add(PathsTest.FUNDO_F3);
		parallax.add(PathsTest.FUNDO_F4);
		parallax.setVelAllLayers(1, 0);

	}

	public void start() {
		while (true) {
			parallax.drawLayers();
			// O método abaixo é responsavel em manter a repetição infinita das camadas.
			parallax.repeatLayers(0, 0, true);
			
			parallax.moveLayersStandardX(true);
			
			Window.getInstance().update();
		}
	}

	public static void main(String[] args) {
		new Test_Parallax().start();

	}

}
