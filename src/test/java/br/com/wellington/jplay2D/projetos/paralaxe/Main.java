package br.com.wellington.jplay2D.projetos.paralaxe;

import br.com.wellington.jplay2D.utils.Constantes;

public class Main {

	public static final String PATH_PARALAXE = Constantes.PATH + "/paralaxe/";
	public static final String FUNDO_0 = PATH_PARALAXE + "f0.png";
	public static final String FUNDO_1 = PATH_PARALAXE + "f1.png";
	public static final String FUNDO_2 = PATH_PARALAXE + "f2.png";
	public static final String FUNDO_3 = PATH_PARALAXE + "f3.png";
	public static final String FUNDO_4 = PATH_PARALAXE + "f4.png";

	public static void main(String[] args) {
		new Paralaxe().start();
	}

}
