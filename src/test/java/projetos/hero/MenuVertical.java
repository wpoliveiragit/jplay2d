package projetos.hero;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;

import jplay.DrawImage;
import jplay.Window;

/** Classe de controle do menu vertical */
class MenuVertical implements DrawImage {

	private Font font = new Font("Comic Sans MS", Font.TRUETYPE_FONT, 35);

	public final Color COR_OFF = Color.yellow;
	public final Color COR_ON = Color.blue;

	public

	/** Classe que representa uma seleção da lista */
	class Selection {
		/** O texto da seleção */
		String txt;
		/** A cor da seleção */
		Color cor;
		/** O local do texto */
		Point local;

		/**
		 * 
		 * @param txt   O texto da seleção.
		 * @param cor   A cor da seleção.
		 * @param local
		 */
		Selection(String txt, Point local) {
			this.txt = txt;
			this.local = local;
			this.cor = COR_OFF;
		}
	}

	Selection[] selections;

	/** Seleção corrente */
	int currentSelection;

	/**
	 * 
	 * @param textos Cada linha de texto.
	 * @param local  Local de cada texto.
	 */
	MenuVertical(String[] textos, Point[] locais) {
		currentSelection = 0;
		selections = new Selection[textos.length];
		for (int i = 0; i < selections.length; i++) {
			selections[i] = new Selection(textos[i], locais[i]);
		}
		selection(currentSelection);
	}

	private void selection(int index) {

		selections[index].cor = COR_ON;
		currentSelection = index;
	}

	/** seleciona o item acima ou vai para o último da fila */
	void up() {
		selections[currentSelection].cor = COR_OFF;
		if (--currentSelection < 0) {
			currentSelection = selections.length - 1;
		}
		selections[currentSelection].cor = COR_ON;
	}

	/** Seleciona o item abaixo ou volta vai para o primeiro */
	void down() {
		selections[currentSelection].cor = COR_OFF;
		if (++currentSelection == selections.length) {
			currentSelection = 0;
		}
		selections[currentSelection].cor = COR_ON;
	}

	@Override
	public void draw() {
		for (Selection s : selections) {
			Window.getInstance().write(s.local.x, s.local.y, s.cor, font, s.txt);
		}
	}

}
