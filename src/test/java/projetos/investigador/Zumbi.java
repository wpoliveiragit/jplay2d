package projetos.investigador;

/**
 * Definição do que a classe representa ou administra
 *
 * @author Wellington Pires de Oliveira.
 * @date 09/05/2019
 */
public class Zumbi extends Personagem implements Constantes {

	public Zumbi(int x, int y) {
		super(SPRITE_ZUMBI, 16);
		this.x = x;
		this.y = y;
		this.velocidade = 0.3;
	}

	public void perseguir(double x, double y) {
		if (this.x > x && this.y < y + 51 && this.y > y - 51) {
			moveTo(x, y, velocidade);
			if (posicao != 1) {
				setSequence(5, 8);
				posicao = 1;
			}
			movendo = true;
		} else if (this.x < x && this.y < y + 51 && this.y > -51) {
			moveTo(x, y, velocidade);
			if (posicao != 2) {
				setSequence(9, 12);
				posicao = 2;
			}
			movendo = true;
		} else if (this.y > y) {
			moveTo(x, y, velocidade);
			if (posicao != 3) {
				setSequence(13, 16);
				posicao = 3;
			}
			movendo = true;
		} else if (this.y < y) {
			if (posicao != 4) {
				setSequence(1, 4);
				posicao = 4;
			}
		}

		if (movendo) {
			update();
			movendo = false;
		}

	}

}
