package projetos.utils;

import java.awt.Font;

public interface Constantes {

	/** Caminhos raizes para os recursos. */
	public static final String ROOT = "src/test/resources";
	// AREA DE RECURSOS de IMAGENS E VIDEOS
	public static final String PATH_JOGO_DA_MEMORIA = ROOT + "/jogoDaMemoria/";
	public static final String PATH_LANCANDO = ROOT + "/lancando/";
	public static final String PATH_MOVIMENTANDO = ROOT + "/movimentando/";

	public static final String PATH_PONG = ROOT + "/pong/";
	public static final String PATH_ROTACIONANDO = ROOT + "/rotacionando/";

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
	public static final String LIMITE = PATH_LANCANDO + "/limite.png";

	// MOVIMENTANDO
	public static final String MOVIMENTANDO_FUNDO = PATH_MOVIMENTANDO + "fundo.png";
	public static final String MOVIMENTANDO_SQUARE = PATH_MOVIMENTANDO + "square.png";
	public static final String MOVIMENTANDO_GROUND = PATH_MOVIMENTANDO + "ground.png";

	// ROTACIONANDO
	public static final String ROTACIONANDO_FUNDO = PATH_ROTACIONANDO + "fundot1.png";
	public static final String ROTACIONANDO_SQUARE = PATH_ROTACIONANDO + "square.png";
	public static final String ROTACIONANDO_GROUND = PATH_ROTACIONANDO + "ground.png";
	public static final String ROTACIONANDO_SPRITE = PATH_ROTACIONANDO + "sprite.png";
	public static final String ROTACIONANDO_LIMITE = PATH_ROTACIONANDO + "limite.png";

	// FONTS
	public static final Font FONTE_SANSSERIF = new Font("sansserif", Font.TRUETYPE_FONT, 12);
	public static final Font FONTE_COMIC_SAMS_MS = new Font("Comic Sans MS", Font.TRUETYPE_FONT, 20);

}
