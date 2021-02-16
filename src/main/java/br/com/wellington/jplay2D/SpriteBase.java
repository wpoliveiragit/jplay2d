package br.com.wellington.jplay2D;

/**
 * Um sprite é um objeto Gráfico, animado, e interativo ou não, que representa
 * personagem, objeto, ou parte do cenário, dentro de uma situação do jogo, ou
 * seja, pode ser qualquer coisa que possamos ver. Carregar a imagem com os
 * possíveis movimentos na memória e fazer o ‘grab’ (clip de uma parte da
 * imagem) em tempo real de acordo com a necessidade. Um ponto importante para
 * manter a suavidade da animação é os frames por segundo. Isso é necessário
 * para que ela tenha o mesmo desempenho em máquinas menos ou mais potentes –
 * para que em máquinas rápidas ela não fique rápida de mais e em máquinas mais
 * lentas não fique muito travado.
 * 
 * <p>
 * http://gamerdesconstrutor.blogspot.com/2014/12/sprite-sheets-definicao.html
 * </p>
 */
public abstract class SpriteBase extends Animation {

	/** Velocidade de deslocamento */
	protected double speed;

	protected double speedX;
	protected double speedY;
	protected double distX;
	protected double distY;

	public SpriteBase(String name, int frames, boolean loop) {
		super(name, frames, loop);
		speed = 1;
		speedX = speed;
		speedY = speed;
		distX = 0;
		distY = 0;
	}

	/**
	 * Define o ponto onde quer que a sprite se mova. Use o metodo moveXY() em loop.
	 * 
	 * @param moveX ponto x
	 * @param moveY ponto y.
	 */
	public void moveXY(double moveX, double moveY) {// ok
		distX = x - moveX;
		distY = y - moveY;
		/* Criar um calculo de mediação de distancia entre os eixos */
		if (distX < 0) {
			distX = -distX;
			speedX = speed;
		} else {
			speedX = -speed;
		}
		if (distY < 0) {
			distY = -distY;
			speedY = speed;
		} else {
			speedY = -speed;
		}
	}

	/** Move a sprite para o ponto */
	public void moveXY() {// ok
		if (distX > 0) {
			x += speedX;
			distX -= speed;
		}
		if (distY > 0) {
			y += speedY;
			distY -= speed;
		}
	}

	/** Define uma nova velocidade de deslocamento */
	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getSpeed() {
		return speed;
	}

}
