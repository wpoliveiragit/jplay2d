package br.com.wellington.projetos.game.constants;

import java.awt.Color;
import java.awt.Font;

public interface Constants {

	/** Caminhos raizes para os recursos. */
	public static final String ROOT = "src/test/resources";
	// [PATH]
	public static final String PATH = ROOT + "/geral/";

	// [PATH-Imagens]
	static final String PATH_IMG = PATH + "image/";
	public static final String IMG_MOUSE = PATH_IMG + "mouse.png";
	public static final String IMG_LOGO_JOGO = PATH_IMG + "logoJogo.png";

	// [PATH-sprite]
	static final String PATH_SPT = PATH + "sprite/";
	public static final String SPT_START = PATH_SPT + "start.png";
	public static final String SPT_CREDITS = PATH_SPT + "credits.png";
	public static final String SPT_OPTIONS = PATH_SPT + "options.png";
	public static final String SPT_MOEDA_OURO = PATH_SPT + "moedaOuro.png";
	public static final String SPT_MEGAMAN = PATH_SPT + "megaman.png";

	// [PATH-SOUND]
	static final String PATH_SND = PATH + "sound/";
	public static final String SND_SELECAO = PATH_SND + "selecao.wav";
	public static final String SND_ENTRADA_MEGAMAN = PATH_SND + "entradaMegaMan.wav";

	// [PATH-SCENE]
	static final String PATH_SCN = PATH + "scene/";
	public static final String SCN_CENA01 = PATH_SCN + "scene01.scn";

	// [PATH-TILE]
	public static final String PATH_TILE = PATH + "tile/";

	// [PATH-FONT]
	public static final Font FONT_COMIC_SANS_MS_20 = new Font("Comic Sans MS", Font.BOLD, 20);
	public static final Font FONT_COMIC_SANS_MS_15 = new Font("Comic Sans MS", Font.BOLD, 15);
	public static final Font FONT_COMIC_SANS_MS_9 = new Font("Comic Sans MS", Font.BOLD, 9);
	public static final Font FIXEDSYS_40 = new Font("Fixedsys", Font.BOLD, 30);

	// [COLOR]
	public static final Color COR_ON = Color.yellow;
	public static final Color COR_OFF = Color.white;

}
