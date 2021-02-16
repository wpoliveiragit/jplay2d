package projetos.hero;

import jplay.DrawImage;
import jplay.Keyboard;
import jplay.SpritePlatform;
import jplay.Window;

public class Hero implements DrawImage {

	// #############################################################################
	/** Deslocamento do passo do personagem */
	private static final int DESLOC_PASSO = 4;
	/** Deslocamento do voo do personagem */
	private static final int DESLOC_VOO = 6;
	/** Deslocamento do pulo para do chão para voar */
	private static final int DESLOC_PULO_VOO = 3;

	private static final int[] LADO_ESQUERDO_CHAO_PARADO = new int[] { 0, 5 };
	private static final int[] LADO_ESQUERDO_CHAO_ANDANDO = new int[] { 5, 10 };
	private static final int[] LADO_ESQUERDO_VOANDO = new int[] { 10, 15 };
	private static final int[] LADO_DIREITO_CHAO_PARADO = new int[] { 15, 20 };
	private static final int[] LADO_DIREITO_CHAO_ANDANDO = new int[] { 20, 25 };
	private static final int[] LADO_DIREITO_VOANDO = new int[] { 25, 30 };
	private Keyboard kb;
	{
		kb = Window.getInstance().getKeyboard();
		kb.addKeyPress(Keyboard.VK_RIGHT);
		kb.addKeyPress(Keyboard.VK_LEFT);
		kb.addKeyPress(Keyboard.VK_DOWN);
		kb.addKeyPress(Keyboard.VK_UP);
		kb.addKey(Keyboard.VK_SPACE);
	}

	// #############################################################################
	private class Status {
		/** O lado em que o personagem esta virado true para direita. */
		private boolean lado;// true = direita
		/** Define se o personagem esta em movimento ou parado */
		private boolean parado;// true = parado
		/** Define se o persoangem esta voando ou andando. true para voando */
		private boolean voar;
		/** Define se o personagem teve um movimento. true pra sim */
		private boolean movimento;

		Status() {
			lado = true;
			parado = false;
			voar = false;
			movimento = false;
		}
	}

	// #############################################################################
	protected SpritePlatform sprite;
	private Status status;

	/**
	 * 
	 * @param x
	 * @param y
	 * @param alturaChao
	 */
	public Hero(int x, int y, int alturaChao) {
		sprite = new SpritePlatform("sprite_hero.png", 30, true);
		sprite.setTotalDuration(2000);
		sprite.setPlatformHeight(alturaChao);
		sprite.setX(x);
		sprite.setY(y);
		status = new Status();
	}

	public void action() {
		gravidade();
		pulo();
		movimentoEsquerdaDireita();
		movimentoCimaBaixo();
		if (status.movimento) {// COM MOVIMENTO
			sprite.update();
			status.movimento = false;
		} else { // SEM CAPTURA DE CONTROLE
			movimentoSem();
		}
	}

	/** Vira o personagem para um lado */
	private void setViraLado(boolean lado, int[] config) {
		sprite.setGrab(config[0], config[1], true);// teste do metodo
		status.lado = lado;
	}

	private void setAndarLado(int[] config) {
		sprite.setGrab(config[0], config[1]);
		status.parado = false;
	}

	private void movimentoDireita() {
		if (status.voar) {// voando
			if (status.lado) {// direita
				sprite.setX(sprite.getX() + DESLOC_VOO);
			} else {// esquerda
				setViraLado(true, LADO_DIREITO_VOANDO);
			}
		} else {// no chão
			if (status.lado) {// direira
				if (status.parado) {// parado
					setAndarLado(LADO_DIREITO_CHAO_ANDANDO);
				}
				sprite.setX(sprite.getX() + DESLOC_PASSO);
			} else {// esquerda parado
				setViraLado(true, LADO_DIREITO_CHAO_PARADO);
				status.parado = true;// não esta mais parado
			}
		}
		status.movimento = true;
	}

	private void movimentoEsquerda() {
		if (status.voar) {// voando
			if (status.lado) {// direita
				setViraLado(false, LADO_ESQUERDO_VOANDO);
			} else {
				sprite.setX(sprite.getX() - DESLOC_VOO);
			}
		} else {// no chão
			if (status.lado) {// vira pra direita
				setViraLado(false, LADO_ESQUERDO_CHAO_PARADO);
				status.parado = true;
			} else {
				if (status.parado) {
					// movimento de andar pra direita
					setAndarLado(LADO_ESQUERDO_CHAO_ANDANDO);
				}
				sprite.setX(sprite.getX() - DESLOC_PASSO);
			}
		}
		status.movimento = true;
	}

	private void movimentoCima() {
		if (status.voar) {// voando
			sprite.setY(sprite.getY() - DESLOC_VOO);
		} else {// no chão
			if (status.lado) {
				sprite.setGrab(LADO_DIREITO_VOANDO[0], LADO_DIREITO_VOANDO[1]);
			} else {
				sprite.setGrab(LADO_ESQUERDO_VOANDO[0], LADO_ESQUERDO_VOANDO[1]);
			}
			status.voar = true;

			sprite.setY(sprite.getY() - DESLOC_PULO_VOO);
		}
		status.movimento = true;
	}

	private void movimentoBaixo() {
		if (status.voar) {// voando
			if (sprite.getPlatformHeight() - (sprite.getY() + sprite.getHeight()) < DESLOC_PULO_VOO) {
				status.voar = false;
				sprite.setY(sprite.getPlatformHeight() - sprite.getHeight());

				if (status.lado) {
					sprite.setGrab(LADO_DIREITO_CHAO_PARADO[0], LADO_DIREITO_CHAO_PARADO[1]);
				} else {
					sprite.setGrab(LADO_ESQUERDO_CHAO_PARADO[0], LADO_ESQUERDO_CHAO_PARADO[1]);
				}
				status.parado = true;

			} else {
				sprite.setY(sprite.getY() + DESLOC_VOO);
			}
			status.movimento = true;
		}
		// no chão-sem ação
	}

	private void movimentoSem() {
		if (!status.parado) {// parar o personagem
			if (status.lado) {
				sprite.setGrab(LADO_DIREITO_CHAO_PARADO[0], LADO_DIREITO_CHAO_PARADO[1]);
			} else {
				sprite.setGrab(LADO_ESQUERDO_CHAO_PARADO[0], LADO_ESQUERDO_CHAO_PARADO[1]);
			}
			status.parado = true;
		}
		sprite.update();
	}

	private void gravidade() {
		if (!status.voar) {
			sprite.gravityFloor();
		}
	}

	private void pulo() {
		if (!status.voar && kb.conpareKey(Keyboard.VK_SPACE)) {
			sprite.jump();
		}
	}

	private void movimentoEsquerdaDireita() {
		if (kb.conpareKey(Keyboard.VK_RIGHT)) {// para direita
			movimentoDireita();
		} else if (kb.conpareKey(Keyboard.VK_LEFT)) {// para esquerda
			movimentoEsquerda();
		}
	}

	private void movimentoCimaBaixo() {
		if (kb.conpareKey(Keyboard.VK_UP)) {// para cima
			movimentoCima();
		} else if (kb.conpareKey(Keyboard.VK_DOWN)) {// para baixo
			movimentoBaixo();
		}
	}

	@Override
	public void draw() {
		sprite.draw();
	}

}
