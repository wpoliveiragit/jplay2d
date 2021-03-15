package projetos.investigador;

import java.awt.event.KeyEvent;

import br.com.wellington.jplay2D.imageProcessing.Sprite;

/**
 * Definição do que a classe representa ou administra
 *
 * @author Wellington Pires de Oliveira.
 * @date 09/05/2019
 * @path Jogo01.Jogo.Tiro
 */
public class Tiro extends Sprite {

	/** Velocidade da bala */
	private static final float VELOCIDADE_MOVIMENTO = 7f;

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
		super(InvestigadorMain.SPRITE_TIRO, 12);
		super.setTotalDuration(500);
		this.x = x;
		this.y = y;

		switch (direcao) {// escolha da direção da bala
		case KeyEvent.VK_LEFT:
			setSequence(9, 12);
			addX = -VELOCIDADE_MOVIMENTO;
			break;
		case KeyEvent.VK_RIGHT:// correto
			setSequence(0, 3);
			addX = +VELOCIDADE_MOVIMENTO;
			break;
		case KeyEvent.VK_UP:// correto
			setSequence(3, 6);
			addY = -VELOCIDADE_MOVIMENTO;
			break;
		case KeyEvent.VK_DOWN:
			setSequence(6, 9);
			addY = +VELOCIDADE_MOVIMENTO;
			break;
		}

	}

	public void mover() {
		x += addX;
		y += addY;
		update();
	}

}
