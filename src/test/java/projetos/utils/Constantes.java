package projetos.utils;

import java.awt.Font;

public interface Constantes {

	public static final Font FONTE_SANSSERIF = new Font("sansserif", Font.TRUETYPE_FONT, 12);
	public static final Font FONTE_COMIC_SAMS_MS = new Font("Comic Sans MS", Font.TRUETYPE_FONT, 20);
	public static Font FONTE_COMIC_SANS_MS_16 = new Font("Comic Sans MS", Font.TRUETYPE_FONT, 16);
	public static Font FONTE_COMIC_SANS_MS_24 = new Font("Comic Sans MS", Font.BOLD, 24);

	// caminho raiz
	/** Caminhos raizes para os recursos. */
	public static final String PATH = "src/test/resources/";

	public static final String PATH_MEGAMAN = PATH + "/megaMan/";
	public static final String PATH_JOGO_DA_MEMORIA = PATH + "/jogoDaMemoria/";
	public static final String PATH_LANCANDO = PATH + "/lancando/";
	public static final String PATH_MOVIMENTANDO = PATH + "/movimentando/";

	public static final String PATH_PONG = PATH + "/pong/";
	public static final String PATH_ROTACIONANDO = PATH + "/rotacionando/";

	// JOGO DA MEMORIA
	public static final String JOGO_DA_MEMORIA_IMG_MOUSE = PATH_JOGO_DA_MEMORIA + "mouse.png";
	public static final String JOGO_DA_MEMORIA_IMG_FUNDO = PATH_JOGO_DA_MEMORIA + "fundo.png";
	public static final String JOGO_DA_MEMORIA_IMG_BOTAO = PATH_JOGO_DA_MEMORIA + "botao.png";
	public static final String JOGO_DA_MEMORIA_IMG_BOTAO_FUNDO = PATH_JOGO_DA_MEMORIA + "fundoBotao.png";

	public static final String IMG_PECA_01 = PATH_JOGO_DA_MEMORIA + "peca01.png";
	public static final String IMG_PECA_02 = PATH_JOGO_DA_MEMORIA + "peca02.png";
	public static final String IMG_PECA_03 = PATH_JOGO_DA_MEMORIA + "peca03.png";
	public static final String IMG_PECA_04 = PATH_JOGO_DA_MEMORIA + "peca04.png";
	public static final String IMG_PECA_05 = PATH_JOGO_DA_MEMORIA + "peca05.png";
	public static final String IMG_PECA_06 = PATH_JOGO_DA_MEMORIA + "peca06.png";
	public static final String IMG_PECA_07 = PATH_JOGO_DA_MEMORIA + "peca07.png";
	public static final String IMG_PECA_08 = PATH_JOGO_DA_MEMORIA + "peca08.png";
	public static final String IMG_PECA_09 = PATH_JOGO_DA_MEMORIA + "peca09.png";
	public static final String IMG_PECA_10 = PATH_JOGO_DA_MEMORIA + "peca10.png";

	public static final String SOM_SOM1 = PATH_JOGO_DA_MEMORIA + "som1.wav";
	public static final String SOM_SOM2 = PATH_JOGO_DA_MEMORIA + "som2.wav";
	public static final String SOM_SOM3 = PATH_JOGO_DA_MEMORIA + "som3.wav";

	// LANCAMDO
	public static final String LANCANDO_BACKDROP = PATH_LANCANDO + "/fundo.png";
	public static final String LANCANDO_TILE_BLOCO_1 = PATH_LANCANDO + "/square.png";
	public static final String LANCANDO_GROUND = PATH_LANCANDO + "/ground.png";
	public static final String LANCANDO_SPRITE = PATH_LANCANDO + "/sprite.png";
	public static final String LANCANDO_LIMITE = PATH_LANCANDO + "/limite.png";

	// MOVIMENTANDO
	public static final String MOVIMENTANDO_FUNDO = PATH_MOVIMENTANDO + "fundo.png";
	public static final String MOVIMENTANDO_SQUARE = PATH_MOVIMENTANDO + "square.png";
	public static final String MOVIMENTANDO_GROUND = PATH_MOVIMENTANDO + "ground.png";

	// PONG
	public static String PONG_SOM_BATEU = PATH_PONG + "bateu.wav";
	public static String PONG_SOM_MUSICA = PATH_PONG + "musica.wav";
	public static String PONG_SOM_PONTO = PATH_PONG + "ponto.wav";

	public static String PONG_IMG_FUNDO = PATH_PONG + "fundo.png";
	public static String PONG_IMG_BARRA_VERDE = PATH_PONG + "barraVerde.png";
	public static String PONG_IMG_BARRA_ROXA = PATH_PONG + "barraRoxa.png";
	public static String PONG_IMG_BOLA = PATH_PONG + "bola.png";

	// Valores relativos a área jogavel
	public static final int PONG_LIMITE_SUPERIOR_Y = 118;
	public static final int PONG_LIMITE_INFERIOR_Y = 565;
	public static final int PONG_LIMITE_DIREITA_X = 774;
	public static final int PONG_LIMITE_ESQUERDA_X = 28;

	public static final int PONG_STOP = 1;
	public static final int PONG_LEFT = 2;
	public static final int PONG_RIGHT = 3;
	public static final int PONG_UP = 4;
	public static final int PONG_DOWN = 5;

	// ROTACIONANDO
	public static final String ROTACIONANDO_FUNDO = PATH_ROTACIONANDO + "fundot1.png";
	public static final String ROTACIONANDO_SQUARE = PATH_ROTACIONANDO + "square.png";
	public static final String ROTACIONANDO_GROUND = PATH_ROTACIONANDO + "ground.png";
	public static final String ROTACIONANDO_SPRITE = PATH_ROTACIONANDO + "sprite.png";
	public static final String ROTACIONANDO_LIMITE = PATH_ROTACIONANDO + "limite.png";

}