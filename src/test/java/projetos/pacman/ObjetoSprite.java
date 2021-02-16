package projetos.pacman;

import java.awt.Point;

import _mainPacMan.ConfiguracaoSprite;
import jplay.SpritePlatform;

public class ObjetoSprite implements Desenho, Constantes {

	/**
	 * A sprite do objeto
	 */
	protected SpritePlatform sprite;
	protected ConfiguracaoSprite config;

	/**
	 * CONSTRUTOR do objeto.
	 * 
	 * @param pathSprite caminho da sprite.
	 * @param nFrames    quantidade de frames que a sprite possui.
	 * @param tempo      velocidade de atualiza��o dos frames
	 * @param x          Ponto do eixo x onde o objeto ira ser desenhado.
	 * @param y          Ponto do eixo y onde o objeto ira ser desenhado.
	 */
	public ObjetoSprite(ConfiguracaoSprite config) {
		this.config = config;
		sprite = new SpritePlatform(config.getPathSprite(), config.getTotalFrames(), true);
		sprite.setTotalDuration(config.getTempoPadrao());
	}

	/**
	 * Move o objeto para um ponto do frame.
	 * 
	 * @param x eixo do x.
	 * @param y eixo do y.
	 */
	public void move(double x, double y) {

		sprite.setX(x);
		sprite.setY(y);
	}

	/**
	 * Retorna 2 pontos indicando os limites do quadro do objeto na janela.
	 * 
	 * ponto[0] ponto superior esquerdo do quadro.
	 * 
	 * ponto[1] ponto inferior esquerdo do quadro.
	 */
	public Point[] getLocalObjeto() {
		return new Point[] { new Point((int) sprite.getX(), (int) sprite.getY()),
				new Point((int) (sprite.getX() + sprite.getWidth()), (int) (sprite.getY() + sprite.getHeight())) };
	}

	/**
	 * Retorna a sprite do personagem.
	 * 
	 * @return A sprite do personagem.
	 */
	public SpritePlatform getSprite() {
		return sprite;
	}

	@Override
	public void desenha() {
		sprite.draw();
	}

	@Override
	public void atualiza() {
		sprite.update();
	}

}
