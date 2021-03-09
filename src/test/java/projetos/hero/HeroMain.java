package projetos.hero;

import java.awt.Font;

import projetos.utils.Constantes;

public class HeroMain {

	public static final Font FONT_COMIC_SANS_MS_16 = new Font("Comic Sans MS", Font.TRUETYPE_FONT, 16);
	public static final Font FONT_COMIC_SANS_MS_40 = new Font("Comic Sans MS", Font.BOLD, 40);
	public static final Font FONT_COMIC_SANS_MS_60 = new Font("Comic Sans MS", Font.BOLD, 60);

	public static final String PATH = Constantes.ROOT + "/hero";
	public static final String FILE_IMAGE = PATH + "/img";
	public static final String IMG_MAIN_MENU = FILE_IMAGE + "/mainMenu.png";

	public static void main(String[] args) {
		new HeroMainMenu().start();
	}
}
