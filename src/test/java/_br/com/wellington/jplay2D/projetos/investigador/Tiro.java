package _br.com.wellington.jplay2D.projetos.investigador;

import br.com.wellington.jplay2D.imageProcessing.Sprite;
import br.com.wellington.jplay2D.oi.Keyboard;

/**
 * Definição do que a classe representa ou administra
 *
 * @author Wellington Pires de Oliveira.
 * @date 09/05/2019
 * @path Jogo01.Jogo.Tiro
 */
public class Tiro extends Sprite implements Constantes {

	/**
	 * A velocidade em que a bala irá percorrer o mapa.
	 */
	private static final float VELOCIDADE = 1f;
	/**
	 * A progreção da direção da bala pelo eixo x.
	 */
	private double addX = 0;
	/**
	 * A progreção da bala pelo eixo y.
	 */
	private double addY = 0;

	/**
	 * Cria um tiro de uma.
	 *
	 * @param x       O ponto do eixo x de onde o tiro iniciará sua trajetória.
	 * @param y       O ponto do eixo y de onde o tiro iniciará sua trajetória.
	 * @param direcao A direção de onde o tiro ira seguir.
	 */
	public Tiro(double x, double y, int direcao) {
		super(SPRITE_TIRO, 12);
		super.setTotalDuration(500);
		this.x = x;
		this.y = y;
		switch (direcao) {
		case Keyboard.LEFT_KEY:
			setSequence(9, 12);
			addX = -VELOCIDADE;
			break;
		case Keyboard.RIGHT_KEY:// correto
			setSequence(0, 3);
			addX = +VELOCIDADE;
			break;
		case Keyboard.UP_KEY:// correto
			setSequence(3, 6);
			addY = -VELOCIDADE;
			break;
		case Keyboard.DOWN_KEY:
			setSequence(6, 9);
			addY = +VELOCIDADE;
			break;
		}

	}

	public void mover() {
		x += addX;
		y += addY;
		update();
	}

}
