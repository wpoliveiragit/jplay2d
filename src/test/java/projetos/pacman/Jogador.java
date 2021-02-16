package projetos.pacman;

import jplay.Scene;

/**
 * 
 * @author Cantina
 */
public class Jogador extends Personagem {

	/**
	 * CONSTRUTOR base do jogador
	 * 
	 * @param config
	 */
	public Jogador(Scene cena, double x, double y) {
		super(cena, ConfiguracaoSprite.PACMAN, x, y);
		passoDireita();
		atualiza();
	}

}
