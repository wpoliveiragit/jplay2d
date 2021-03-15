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

}
