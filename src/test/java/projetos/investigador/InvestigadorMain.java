package projetos.investigador;

/**
 * Definição do que a classe representa ou administra
 *
 * @author Wellington Pires de Oliveira.
 * @date 04/05/2019
 * @path Jogo01.Jogo.Main
 */
public class InvestigadorMain {

	// [ROOT]
	static String PATH = projetos.utils.Constantes.ROOT + "/investigador/";

	// [PATH]
	static String FILE_IMAGE = PATH + "img/";
	static String FILE_SCENE = PATH + "scn/";
	static String FILE_SPRITE = PATH + "sprite/";
	static String FILE_TILE = PATH + "tile/";
	static String FILE_SONG = PATH + "song/";

	// [IMAGENS]
	static String IMG_BACKGROUND = FILE_IMAGE + "menu.png";

	// [CENARIOS]
	static String SCN_CENARIO = FILE_SCENE + "investigador.scn";

	// [SPRITES]
	static String SPRITE_ZUMBI = FILE_SPRITE + "zumbi.png";
	static String SPRITE_PLAYER = FILE_SPRITE + "jogador.png";
	static String SPRITE_TIRO = FILE_SPRITE + "bolafogo.png";

	public static void main(String[] args) {
		new MainMenu().start();
	}

}
