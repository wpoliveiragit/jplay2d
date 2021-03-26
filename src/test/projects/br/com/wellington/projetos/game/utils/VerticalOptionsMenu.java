package br.com.wellington.projetos.game.utils;

public class VerticalOptionsMenu {

	private final int min;

	private int max;

	private int selecao;

	public VerticalOptionsMenu(int tamanho) {
		this.max = tamanho - 1;
		min = 0;
		selecao = 0;
	}

	/** sobe a seleção. */
	public void desce() {
		selecao++;
		ajustaSelecao();
	}

	/** desce a seleção. */
	public void sobe() {
		selecao--;
		ajustaSelecao();
	}

	/** Ajusta os limites da seleção corrente */
	private void ajustaSelecao() {
		if (selecao > max) {
			selecao = min;
			return;
		}
		if (selecao < min) {
			selecao = max;
		}
	}

	public int getSelecao() {
		return selecao;
	}

}
