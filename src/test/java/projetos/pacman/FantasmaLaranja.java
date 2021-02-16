package projetos.pacman;

import jplay.Scene;

/**
 * 
 * @author Wellington Pires de Oliveira.
 * @date 16/41/2019
 */
public class FantasmaLaranja extends Fantasma {

	public FantasmaLaranja(Scene cena, double x, double y) {
		super(cena, ConfiguracaoSprite.FANTASMA_LARANJA, x, y);
		passoDireita();
		atualiza();
		
	}

	@Override
	public void movimento() {
		verCaminho();
		switch (poseAtual) {
		case POSE_CIMA:
			passoCima();
			break;
		case POSE_BAIXO:
			passoBaixo();
			break;
		case POSE_ESQUERDA:
			passoEsquerda();
			break;
		case POSE_DIREITA:
			passoDireita();
			break;
		}
	}
	
	private void verCaminho() {
		
	}

}
