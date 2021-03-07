package projetos.pong;

import java.awt.Font;

import projetos.utils.Constantes;

public class PongApplication {

	public static Font FONTE_COMIC_SANS_MS_16 = new Font("Comic Sans MS", Font.TRUETYPE_FONT, 16);
	public static Font FONTE_COMIC_SANS_MS_24 = new Font("Comic Sans MS", Font.BOLD, 24);

	public static final String ROOT = Constantes.ROOT + "/pong/";
	public static final String PONG_IMG_FUNDO = ROOT + "fundo.png";
	public static final String PONG_IMG_BARRA_VERDE = ROOT + "barraVerde.png";
	public static final String PONG_IMG_BARRA_ROXA = ROOT + "barraRoxa.png";
	public static String PONG_IMG_BOLA = ROOT + "bola.png";

	
	public static String PONG_SOM_MUSICA = ROOT + "musica.wav";
	public static String PONG_SOM_PONTO = ROOT + "ponto.wav";
	public static String PONG_SOM_BATEU = ROOT + "bateu.wav";

	public static final int PONG_LIMITE_SUPERIOR_Y = 118;
	public static final int PONG_LIMITE_INFERIOR_Y = 565;
	public static final int PONG_LIMITE_DIREITA_X = 774;
	public static final int PONG_LIMITE_ESQUERDA_X = 28;

	public static final int PONG_SENTIDO_PARADO = 1;
	public static final int PONG_SENTIDO_ESQUERDA = 2;
	public static final int PONG_SENTIDO_DIREITA = 3;
	public static final int PONG_SENTIDO_CIMA = 4;
	public static final int PONG_SENTIDO_BAIXO = 5;

	public static void main(String[] args) {
		new Pong(800, 600);
	}
}
