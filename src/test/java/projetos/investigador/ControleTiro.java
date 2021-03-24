package projetos.investigador;

import java.util.ArrayList;
import java.util.List;

import br.com.wellington.jplay2D.scene.Scene;

/**
 * Definição do que a classe representa ou administra
 *
 * @author Wellington Pires de Oliveira.
 * @date 09/05/2019
 * @path Jogo01.Jogo.ControleTiro
 */
public class ControleTiro {

	private final List<Tiro> tiros;

	public ControleTiro() {
		tiros = new ArrayList<Tiro>();
	}

	public void adicionaTiro(double x, double y, int caminho, Scene cena) {
		Tiro tiro = new Tiro(x, y, caminho);
		tiros.add(tiro);
		cena.addSceneElements(tiro);
	}

	public void run() {
		for (int i = 0; i < tiros.size(); i++) {
			tiros.get(i).mover();
		}

	}

}
