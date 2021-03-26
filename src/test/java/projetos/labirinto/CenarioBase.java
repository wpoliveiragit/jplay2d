package projetos.labirinto;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import br.com.wellington.jplay2D.image.TileInfo;
import br.com.wellington.jplay2D.scenery.Scenery;

public class CenarioBase implements Constantes {

	/**
	 * O cenario corrente.
	 */
	protected Scenery cena;

	/**
	 * O jogo.
	 */
	protected final Jogo jogo;

	/**
	 * Construtor base do cenario.
	 * 
	 * @param jogo
	 */
	public CenarioBase(Jogo jogo, String pathScn) {
		this.jogo = jogo;
		cena = new Scenery();
		cena.loadFromFile(pathScn,"Colocar aqui o raiz onde estão os tiles deste projeto");
		cena.setDrawStartPos(DESLOQUE_CENARIO_X, DESLOQUE_CENARIO_Y);
	}

	/**
	 * Solicita que o jogo desenhe o cenario.
	 */
	public void draw() {
		cena.draw();
	}

	/**
	 * retorna o cenario corrente.
	 * 
	 * @return O cenario corrente.
	 */
	public Scenery getCenario() {
		return cena;
	}

	/**
	 * Finaliza a janela e o jogo.
	 */
	public void finaliza() {
		jogo.finaliza();
	}

	/**
	 * Retorna as dimencoes da janela
	 * 
	 * @return As dimencoes da janela.
	 */
	public Dimension getDimencao() {
		return jogo.getDimencao();
	}

	/**
	 * Retorna uma lista contendo todos os tiles correspondentes ao quadrante
	 * indicado.
	 * 
	 * @param pa Ponto superior esquerdo.
	 * @param pb Ponto inferior direito.
	 * @return Uma lista contendo todos os tiles correspondentes ao quadrante
	 *         indicado.
	 */
	@SuppressWarnings("unchecked")
	public List<TileInfo> getTilesFromRect(Point pa, Point pb) {
		return new ArrayList<TileInfo>(getCenario().getTilesFromRect(pa, pb));
	}

}
