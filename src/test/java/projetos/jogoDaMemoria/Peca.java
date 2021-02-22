/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projetos.jogoDaMemoria;

import br.com.wellington.jplay2D.imageProcessing.Animation;

/**
 * @author Gefersom Cardoso Lima Federal Fluminense University Computer Science
 */
public class Peca extends Animation {

	public int id;
	boolean pecaEscolhida;

	public Peca(String fileName, int id) {
		super(fileName, 2); // 2 = número de frames de cada peça
		setCurrFrame(1);
		this.id = id;
		pecaEscolhida = false;
	}

	public int getID() {
		return id;
	}

	public void setID(int id) {
		this.id = id;
	}

	public boolean pecaEstaVirada() {
		return pecaEscolhida;
	}

	public void mostrar() {
		setCurrFrame(0);
		pecaEscolhida = true;
	}

	public void esconder() {
		setCurrFrame(1);
		pecaEscolhida = false;
	}

	public boolean foiEscolhida() {
		return pecaEscolhida;
	}

}
