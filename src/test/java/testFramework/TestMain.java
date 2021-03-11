package testFramework;

import java.awt.Font;

import projetos.utils.Constantes;

public class TestMain {

	public static void main(String[] args) {
		new TestJogo().startGame();
	}

	// [PATHS]
	public static final String ROOT = Constantes.ROOT + "/geral/";

	// [PATHS Imagens]
	private static final String PATH_IMG = ROOT + "image/";
	public static final String IMG_MOUSE = PATH_IMG + "mouse.png";

	// [FONTS]
	public static final Font FONT_COMIC_SANS_MS_20 = new Font("Comic Sans MS", Font.BOLD, 20);
	public static final Font FONT_COMIC_SANS_MS_15 = new Font("Comic Sans MS", Font.BOLD, 15);
	public static final Font FONT_COMIC_SANS_MS_9 = new Font("Comic Sans MS", Font.BOLD, 9);

}
