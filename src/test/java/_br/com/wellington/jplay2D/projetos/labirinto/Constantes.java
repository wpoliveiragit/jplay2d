package _br.com.wellington.jplay2D.projetos.labirinto;

public interface Constantes {

	// PATHS ///////////////////////////////////////////////////////////////////////
	/** Deslocamento do eixo x do cenario na janela. */
	public static final int DESLOQUE_CENARIO_X = 15;

	/** Deslocamento do eixo y do cenario na janela. */
	public static final int DESLOQUE_CENARIO_Y = 30;

	public static String PATH = "recursos\\labirinto\\";
	/** link do arquivo do primeiro cenario do jogo. */
	public static final String SCN_CENARIO01 = PATH + "cenario01.scn";

	/** Caminho da sprite do personagem. */
	public static final String SPRITE_PLAYER = PATH + "player.png";

	/** Caminho da sprite da moeda. */
	public static final String SPRITE_MOEDA = PATH + "moeda.png";

	// CONSTANTES ///////////////////////////////////////////////////////////
	public static final int TILE_ALTURA = 32;

	public static final int TILE_LARGURA = 32;

	/** Identificador do tile Parede. */
	public static final int TILE_ID_PAREDE = 1;

	/** Tile que finaliza o jogo */
	public static final int TILE_ID_GRAMA_EVENTO = 18;

	/** Posicao da sprite do personagem pra baixo. */
	public static final int POSE_BAIXO = 0;

	/** Posicao da sprite do personagem pra esquerda. */
	public static final int POSE_ESQUERDA = 1;

	/** Posicao da sprite do personagem pra direita. */
	public static final int POSE_DIREITA = 2;

	/** Posicao da sprite do personagem pra cima. */
	public static final int POSE_CIMA = 3;

	/** Distancia em pixel que o personagem anda a cada atualiza��o. */
	public static final int PASSO = 1;

}
