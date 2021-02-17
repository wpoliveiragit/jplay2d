package br.com.wellington.jplay2D.projetos.megaMan;

import br.com.wellington.jplay2D.utils.Constantes;

public class Main {

	// MEGAMAN
	public static final String MEGAMAN_SPRITE_TIRO = Constantes.PATH_MEGAMAN + "tiro.png";
	public static final String MEGAMAN_SPRITE_MEGAMAN = Constantes.PATH_MEGAMAN + "boneco.png";
	public static final String MEGAMAN_BACKDROP = Constantes.PATH_MEGAMAN + "fundo.png";
	public static final String MEGAMAN_SOM_EXPLOSAO = Constantes.PATH_MEGAMAN + "explosao.wav";
	public static final String MEGAMAN_SOM_MUZZLESHOT = Constantes.PATH_MEGAMAN + "muzzleshot.wav";

	public static void main(String[] args) {
		new Janela(800, 600).start();
	}
}
