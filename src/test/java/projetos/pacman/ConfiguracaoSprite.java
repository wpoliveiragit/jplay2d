package projetos.pacman;

import java.awt.Point;

public enum ConfiguracaoSprite implements _mainPacMan.Constantes {

	/**
	 * Sprite moeda ouro.
	 */
	MOEDA_OURO(PATH_SPRITE_MOEDA_OURO, 10, 300, null, null, null, null),

	/**
	 * 
	 */
	FANTASMA_AZUL(PATH_FANTASMA_AZUL, 8, 1000, new Point(0, 2), new Point(2, 4), new Point(4, 6), new Point(6, 8)),
	/**
	 * 
	 */
	FANTASMA_VERMELHO(PATH_FANTASMA_VERMELHO, 8, 1000, new Point(0, 2), new Point(2, 4), new Point(4, 6),
			new Point(6, 8)),
	/**
	 * 
	 */
	FANTASMA_LARANJA(PATH_FANTASMA_LARANJA, 8, 1000, new Point(0, 2), new Point(2, 4), new Point(4, 6),
			new Point(6, 8)),
	/**
	 * 
	 */
	FANTASMA_ROSA(PATH_FANTASMA_ROSA, 8, 1000, new Point(0, 2), new Point(2, 4), new Point(4, 6), new Point(6, 8)),

	/**
	 * Sprite pacman.
	 */
	PACMAN(PATH_SPRITE_PACMAN, 8, 1500, new Point(0, 2), new Point(2, 4), new Point(4, 6), new Point(6, 8));

	/**
	 * Caminho da sprite.
	 */
	private String pathSprite;

	/**
	 * numero de frames da sprite.
	 */
	private int qtdFrames;

	/**
	 * O tempo de troca de frame da sprite.
	 */
	private int tempoPadrao;

	/**
	 * marcacao do inicio e fim dos frames na sprite pra baixo.
	 */
	private Point framesBaixo;

	/**
	 * marcacao do inicio e fim dos frames na sprite pra cima.
	 */
	private Point framesCima;

	/**
	 * marcacao do inicio e fim dos frames na sprite pra esquerda.
	 */
	private Point framesEsquerda;

	/**
	 * marcacao do inicio e fim dos frames na sprite pra direita.
	 */
	private Point framesDireita;

	/**
	 * Construtor padrao da sprite.
	 * 
	 * @param pathSprite  o caminho do arquivo da sprite.
	 * @param qtdFrames   quantidade de frames que a sprite possui.
	 * @param tempoPadrao
	 */
	private ConfiguracaoSprite(String pathSprite, int qtdFrames, int tempoPadrao, Point framesBaixo,
			Point framesDireita, Point framesCima, Point framesEsquerda) {
		this.pathSprite = pathSprite;
		this.qtdFrames = qtdFrames;
		this.tempoPadrao = tempoPadrao;

		this.framesBaixo = framesBaixo;
		this.framesCima = framesCima;
		this.framesDireita = framesDireita;
		this.framesEsquerda = framesEsquerda;
	}

	/**
	 * retorna a sprite.
	 * 
	 * @return A sprite.
	 */
	public String getPathSprite() {
		return pathSprite;
	}

	/**
	 * Retorna a quantidade de frames da sprite.
	 * 
	 * @return A quantidade de frames da sprite.
	 */
	public int getTotalFrames() {
		return qtdFrames;
	}

	/**
	 * Retorna o tempo padrao de atualizacao da sprite.
	 * 
	 * @return O tempo padrao de atualizacao da sprite.
	 */
	public int getTempoPadrao() {
		return tempoPadrao;
	}

	public Point getFramesBaixo() {
		return framesBaixo;
	}

	public Point getFramesCima() {
		return framesCima;
	}

	public Point getFramesEsquerda() {
		return framesEsquerda;
	}

	public Point getFramesDireita() {
		return framesDireita;
	}

}
