package projetos.investigador;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import br.com.wellington.jplay2D.imageProcessing.Sprite;
import br.com.wellington.jplay2D.imageProcessing.TileInfo;
import br.com.wellington.jplay2D.oi.Keyboard;
import br.com.wellington.jplay2D.scene.Scene;

/**
 * Definição do que a classe representa ou administra
 *
 * @author Wellington Pires de Oliveira.
 * @date 09/05/2019
 * @path Jogo01.Jogo.Ator
 */
public class Personagem extends Sprite {

	private static final byte ESQUERDA = Keyboard.LEFT_KEY;
	private static final byte DIREITA = Keyboard.RIGHT_KEY;
	private static final byte CIMA = Keyboard.UP_KEY;
	private static final byte BAIXO = Keyboard.DOWN_KEY;
	
	protected int posicao;
	protected Controle controle;
	protected double velocidade;
	protected boolean movendo;
	private final Local local;

	public Personagem(String fileName, int frames) {
		super(fileName, frames);
		setConfig();
		controle = new Controle();
		velocidade = 3;
		movendo = false;
		local = new Local();
	}

	/**
	 * Configurações iniciais do persoange.
	 */
	private void setConfig() {
		setTotalDuration(3000);// tempo da troca de sprite do personagem em movimento
		posicao = Keyboard.DOWN_KEY;

	}

	/**
	 * Controle bloqueio de caminho.
	 *
	 * @param cena
	 */
	public void caminho(Scene cena) {
		// coleta a posição atual do personagem
		local.min.x = (int) x;
		local.min.y = (int) y;
		local.max.x = (int) x + width;
		local.max.y = (int) y + height;

		// obtem a lista dos tiles adjacentes do personagem
		@SuppressWarnings("unchecked")
		List<TileInfo> listaTiles = new ArrayList<>(cena.getTilesFromPosition(local.min, local.max));

		for (TileInfo tile : listaTiles) {
			if (controle.colisao(this, tile)) {
				if (posicao == ESQUERDA) { // indo para esquerda
					x = tile.x + tile.width;
				} else if (posicao == DIREITA) { // indo para direita
					x = tile.x - width;
				} else if (posicao == CIMA) {// subindo
					y = tile.y + tile.height;
				} else if (posicao == BAIXO) {// descendo
					y = tile.y - height;
				}
			}
		}
	}

	/**
	 * Controle de pontos de posição do tile do persoangem.
	 */
	private class Local {

		/**
		 * Ponto superior esquerdo do quadrante do tile do personagem.
		 */
		Point min;
		/**
		 * Ponto inferior direito do quadrante do tile do personagem.
		 */
		Point max;

		Local() {
			min = new Point();
			max = new Point();
		}
	}
}
