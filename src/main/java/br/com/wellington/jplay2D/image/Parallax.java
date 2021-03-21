package br.com.wellington.jplay2D.image;

import java.util.ArrayList;

public class Parallax {

	ArrayList<ParallaxLayers> layers = new ArrayList<ParallaxLayers>();

	private ParallaxLayers mainLayer;

	/**
	 * Adiciona o sprite à lista. Este método adiciona o sprite duas vezes na lista
	 * a ser feito para repetir o fundo. Define a última camada adicionada como
	 * camada principal.
	 * 
	 * @param bg - Caminho do arquivo.
	 */
	public void add(String bg) {

		layers.add(new ParallaxLayers(bg));
		layers.add(new ParallaxLayers(bg));
		mainLayer = layers.get(layers.size() - 2);
		// são adicionados dois, pois será mais fácil para usar no método repeatLayers.
	}

	/**
	 * Desenha todas as camadas.
	 */
	public void drawLayers() {

		for (int i = 0; i < layers.size(); i++)
			layers.get(i).draw();

	}

	/**
	 * Repete todas as camadas no eixo x.
	 * 
	 * @param windowWidth  window width (Window Class).
	 * @param windowHeigth window height (Window Class).
	 * @param axisX        true para mover o eixo X e 'false para mover no eixo y.
	 */
	public void repeatLayers(int windowWidth, int windowHeigth, boolean axisX) {
		if (axisX)
			for (int i = 0; i < layers.size(); i++)
				this.repeatLayerX(windowWidth, i);

		else
			for (int i = 0; i < layers.size(); i++)
				this.repeatLayerY(windowHeigth, i);

	}

	/**
	 * Ele define a velocidade de todas as camadas da camada principal, dividindo as
	 * camadas seguintes pela metade da velocidade da camada principal. Ele define a
	 * velocidade de todas as camadas. Neste método, a velocidade da camada
	 * principal (mainLayer) deve ter sido definida antes.
	 */
	public void setVelAllLayers() {

		// velocidade na camada principal(mainLayer) deve ter sido setada antes.

		for (int i = layers.size() - 4; i >= 0; i--)
			if (i % 2 == 0)
				layers.get(i).setVel(layers.get(i + 2).getVelX() / 2, layers.get(i + 2).getVelY() / 2);

		for (int i = 0; i < layers.size(); i++)
			if (i % 2 != 0)
				layers.get(i).setVel(layers.get(i - 1).getVelX(), layers.get(i - 1).getVelY());

	}

	/**
	 * Ele define a velocidade de todas as camadas da camada principal, dividindo as
	 * camadas seguintes pela metade da velocidade da camada principal.
	 * 
	 * @param velMainLayerX - velocidade da camada principal no eixo X.
	 * @param velMainLayerY - velocidade da camada principal no eixo Y.
	 */
	public void setVelAllLayers(double velMainLayerX, double velMainLayerY) {

		/*
		 * velocidade na camada principal (camada da frente, ou seja, a ultima a ser
		 * pintada).
		 */
		mainLayer.setVel(velMainLayerX, velMainLayerY);

		for (int i = layers.size() - 4; i >= 0; i--)
			if (i % 2 == 0)
				layers.get(i).setVel(layers.get(i + 2).getVelX() / 2, layers.get(i + 2).getVelY() / 2);

		for (int i = 0; i < layers.size(); i++)
			if (i % 2 != 0)
				layers.get(i).setVel(layers.get(i - 1).getVelX(), layers.get(i - 1).getVelY());

	}

	/**
	 * Define a velocidade de todas as camadas de suas larguras. Dividindo a largura
	 * da camada pela largura da camada principal.
	 */
	public void setVelAllLayersStandard() {
		for (int i = 0; i < layers.size(); i++) {
			layers.get(i).setVel((float) layers.get(i).width / (float) mainLayer.width,
					(float) layers.get(i).height / (float) mainLayer.height);
		}
	}

	/**
	 * Mova todas as camadas em uma direção ao longo do eixo x.
	 * 
	 * @param left true mova todas as camadas para a esquerda, 'false' mova todas as
	 *             camadas para a direita.
	 */
	public void moveLayersStandardX(boolean left) {
		for (int i = 0; i < layers.size(); i++)
			layers.get(i).moveLayerX(left);

	}

	/**
	 * Mova todas as camadas em uma direção ao longo do eixo x.
	 * 
	 * @param up 'true' todas as camadas se movem para cima, 'false' move todas as
	 *           camadas para baixo.
	 */
	public void moveLayersStandardY(boolean up) {
		for (int i = 0; i < layers.size(); i++)
			layers.get(i).moveLayerY(up);

	}

	/**
	 * Retorna a camada. Para retornar, a camada deve ultrapassar sua posição na
	 * lista. lembrando que a primeira posição é '0'.
	 * 
	 * @param n posição da camada na lista.
	 * @return ParallaxLayers
	 */
	public ParallaxLayers getLayer(int n) {
		if (n % 2 != 0) // se for ímpar
			return layers.get((n * 2)); // é multiplicado por 2 para não retornar o indice das cópias, desta forma ele
										// retorna apenas as originais.
		else
			return layers.get(n * 2);
	}

	/**
	 * Define a camada principal.
	 * 
	 * @param layer camada que pertence à lista 'ParallaxLayers'.
	 */
	public void setMainLayer(ParallaxLayers layer) {
		mainLayer = layer;
	}

	/**
	 * Retorna a camada principal.
	 * 
	 * @return GameImage
	 */
	public GameImage getMainLayer() {
		return mainLayer;
	}

	/**
	 * Define a velocidade da camada principal no eixo x e y.
	 * 
	 * @param x velocidade da camada principal no eixo x.
	 * @param y velocidade da camada principal no eixo y.
	 */
	public void setVelMainLayer(double x, double y) {
		mainLayer.setVel(x, y);
	}

	/**
	 * Define a velocidade de uma camada no eixo xey.
	 * 
	 * @param velX nova velocidade no eixo x.
	 * @param velY nova velocidade no eixo y.
	 * @param bg   Camada à qual será atribuído o valor da nova velocidade.
	 */
	public void setVelLayer(double velX, double velY, ParallaxLayers bg) {
		bg.setVelX(velX);
		bg.setVelY(velY);
		layers.get(layers.indexOf(bg) + 1).setVelX(velX);
		layers.get(layers.indexOf(bg) + 1).setVelX(velY);
	}

	/**
	 * Define a velocidade de uma camada no eixo x.
	 * 
	 * @param velX nova velocidade no eixo x.
	 * @param bg   Camada à qual será atribuído o valor da nova velocidade.
	 */
	public void setVelLayerX(double velX, ParallaxLayers bg) {
		bg.setVelX(velX);
		layers.get(layers.indexOf(bg) + 1).setVelX(velX);
	}

	/**
	 * Define a velocidade de uma camada no eixo y.
	 * 
	 * @param velY nova velocidade no eixo y.
	 * @param bg   Camada à qual será atribuído o valor da nova velocidade.
	 */
	public void setVelLayerY(double velY, ParallaxLayers bg) {
		bg.setVelX(velY);
		layers.get(layers.indexOf(bg) + 1).setVelX(velY);
	}

	private void repeatLayerX(int windowWidth, int i) {
		if (i % 2 != 0) {
			if (layers.get(i - 1).x < 0)
				layers.get(i).x = layers.get(i - 1).x + layers.get(i - 1).width;

			if (layers.get(i - 1).x > 0)
				layers.get(i).x = layers.get(i - 1).x - layers.get(i).width;

			if (layers.get(i - 1).x > windowWidth)
				layers.get(i - 1).x = layers.get(i).x - layers.get(i).width;

			if (layers.get(i - 1).x + layers.get(i - 1).width < 0)
				layers.get(i - 1).x = layers.get(i).x + layers.get(i).width;
		}
	}

	private void repeatLayerY(int windowHeight, int i) {
		if (i % 2 != 0) {
			if (layers.get(i - 1).y < 0)
				layers.get(i).y = layers.get(i - 1).y + layers.get(i - 1).height;

			if (layers.get(i - 1).y > 0)
				layers.get(i).y = layers.get(i - 1).y - layers.get(i).height;

			if (layers.get(i - 1).y > windowHeight)
				layers.get(i - 1).y = layers.get(i).y - layers.get(i).height;

			if (layers.get(i - 1).y + layers.get(i - 1).height < 0)
				layers.get(i - 1).y = layers.get(i).y + layers.get(i).height;
		}
	}
}
