package projetos.pacman;

import jplay.Scene;

public abstract class Fantasma extends Personagem implements Personalidade {

	public Fantasma(Scene cena, ConfiguracaoSprite config, double x, double y) {
		super(cena, config, x, y);
	}

	@Override
	public void atualiza() {
		movimento();
		super.atualiza();
	}

}
