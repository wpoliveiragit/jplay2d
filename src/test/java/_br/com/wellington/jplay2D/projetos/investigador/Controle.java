package _br.com.wellington.jplay2D.projetos.investigador;

import br.com.wellington.jplay2D.imageProcessing.GameObject;
import br.com.wellington.jplay2D.imageProcessing.TileInfo;

/**
 * Definição do que a classe representa ou administra
 *
 * @author Wellington Pires de Oliveira.
 * @date 07/05/2019
 * @path Jogo01.Jogo.Controle
 */
public class Controle {

	/**
	 * Verifica se um objeto do game colediu com uma tile que não permite a
	 * passagem.
	 *
	 * @param obj  O objeto.
	 * @param tile A tile.
	 * @return true caso o objeto tenha coledido com uma tile que não permite a
	 *         passagem dele.
	 */
	public boolean colisao(GameObject obj, TileInfo tile) {
		if (tile.id > 6 && obj.collided(tile)) {
			return true;
		}
		return false;
	}
}
