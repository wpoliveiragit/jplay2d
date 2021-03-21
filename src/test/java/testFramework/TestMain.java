package testFramework;

import java.awt.Font;

import br.com.wellington.jplay2D.window.Window;
import projetos.utils.Constantes;

public class TestMain {

	// [PATH]
	public static final String ROOT = Constantes.ROOT + "/geral/";

	// [PATH-Imagens]
	private static final String PATH_IMG = ROOT + "image/";
	public static final String IMG_MOUSE = PATH_IMG + "mouse.png";
	public static final String IMG_LOGO_JOGO = PATH_IMG + "logoJogo.png";

	// [PATH-sprite]
	private static final String PATH_SPT = ROOT + "sprite/";
	public static final String SPT_START = PATH_SPT + "start.png";
	public static final String SPT_CREDITS = PATH_SPT + "credits.png";
	public static final String SPT_OPTIONS = PATH_SPT + "options.png";
	public static final String SPT_MOEDA_OURO = PATH_SPT + "moedaOuro.png";

	// [PATH-SOUND]
	private static final String PATH_SND = ROOT + "sound/";
	public static final String SND_SELECAO = PATH_SND + "selecao.wav";

	// [PATH-SCENE]
	private static final String PATH_SCN = ROOT + "scene/";
	public static final String SCN_CENA01 = PATH_SCN + "scene01.scn";

	// [PATH-TILE]
	public static final String PATH_TILE = ROOT + "tile/";

	// [PATH-FONT]
	public static final Font FONT_COMIC_SANS_MS_20 = new Font("Comic Sans MS", Font.BOLD, 20);
	public static final Font FONT_COMIC_SANS_MS_15 = new Font("Comic Sans MS", Font.BOLD, 15);
	public static final Font FONT_COMIC_SANS_MS_9 = new Font("Comic Sans MS", Font.BOLD, 9);

	public static void main(String[] args) {
		Window.getInstance(1024, 768);
		new TelaInicial().start();
	}

}
