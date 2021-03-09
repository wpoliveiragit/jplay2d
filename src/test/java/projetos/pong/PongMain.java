package projetos.pong;

import java.awt.Font;

import projetos.utils.Constantes;

public class PongMain {

	public static final Font FONT_COMIC_SANS_MS_16 = new Font("Comic Sans MS", Font.TRUETYPE_FONT, 16);
	public static final Font FONT_COMIC_SANS_MS_24 = new Font("Comic Sans MS", Font.BOLD, 24);
	public static final Font FONT_COMIC_SANS_MS_60 = new Font("Comic Sans MS", Font.BOLD, 60);

	public static final String ROOT = Constantes.ROOT + "/pong/";
	public static final String IMG_BG = ROOT + "fundo.png";
	public static final String IMG_BAR_VERDE = ROOT + "barraVerde.png";
	public static final String IMG_BAR_ROXA = ROOT + "barraRoxa.png";
	public static final String IMG_BOLA = ROOT + "bola.png";

	public static final String SOM_MUSICA = ROOT + "musica.wav";
	public static final String SOM_PONTO = ROOT + "ponto.wav";
	public static final String SOM_BATEU = ROOT + "bateu.wav";

	public static final int LIM_Y_SUP = 118;
	public static final int LIM_Y_INF = 565;
	public static final int LIM_X_DIR = 774;
	public static final int LIM_X_ESQ = 28;

	public static final int STD_PARADO = 1;
	public static final int STD_ESQUERDA = 2;
	public static final int STD_DIREITA = 3;
	public static final int STD_CIMA = 4;
	public static final int STD_BAIXO = 5;

	public static void main(String[] args) {
		new Pong(800, 600).start();
	}
}
