package _br.com.wellington.jplay2D.projetos.labirinto;

import br.com.wellington.jplay2D.Sprite;

public class ObjetoBase extends Sprite implements Constantes {

	/**
	 * Construtor base de um objeto do cenario.
	 * 
	 * @param cenario    O cenario.
	 * @param pathSprite O caminho do arquivo da Sprite do objeto
	 * @param nFrame     A quantidade de frames que a sprite possui.
	 * @param tmepo      O tempo total em que a sprite deve ser percorrida.
	 */
	public ObjetoBase(Cenario cenario, String pathSprite, int nFrame, int tempo, int x, int y) {
		super(pathSprite, nFrame);
		cenario.getCenario().addOverlay(this);
		setTotalDuration(tempo);
		this.x = DESLOQUE_CENARIO_X + (TILE_LARGURA * x);
		this.y = DESLOQUE_CENARIO_Y + (TILE_ALTURA * y);
	}

}
