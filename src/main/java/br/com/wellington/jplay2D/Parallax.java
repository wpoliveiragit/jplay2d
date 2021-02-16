
package br.com.wellington.jplay2D;

import java.util.ArrayList;

public class Parallax {

	/** Lista das camadas de imagem */
	protected ArrayList<ParallaxLayers> imageLayerList = new ArrayList<ParallaxLayers>();

	protected ParallaxLayers mainLayer;

	/**
	 * Adds the sprite in the list. This method adds the sprite twice in the list to
	 * be made to repeat the background. Defines the last layer added as main layer
	 * 
	 * @param name nome da imagem.
	 */
	public void add(String name) {
		imageLayerList.add(new ParallaxLayers(name));
		imageLayerList.add(new ParallaxLayers(name));
		mainLayer = imageLayerList.get(imageLayerList.size() - 2);
		// sao adicionados dois, pois será mais facil para usar no metodo repeatLayers.
	}

	/** Desenha todas as camadas de imagem */
	public void drawLayers() {
		for (int i = 0; i < imageLayerList.size(); i++) {
			imageLayerList.get(i).draw();
		}
	}

	/**
	 * Repete todas as camadas no eixo x.
	 * 
	 * @param windowWidth  - window width (Window Class).
	 * @param windowHeigth - window height (Window Class).
	 * @param moveXY       true move no eixo x e move no eixo y.
	 */
	public void repeatLayers(int windowWidth, int windowHeigth, boolean moveXY) {
		if (moveXY) {
			for (int i = 0; i < imageLayerList.size(); i++) {
				this.repeatLayerX(windowWidth, i);
			}
		} else {
			for (int i = 0; i < imageLayerList.size(); i++) {
				this.repeatLayerY(windowHeigth, i);
			}
		}
	}

	/**
	 * It sets the velocity of all the layers from the main layer, dividing the
	 * following layers by half the speed of the main layer. It sets the velocity of
	 * all layers . In this method the speed of the main layer(mainLayer) must have
	 * been set before.
	 */
	public void setVelAllLayers() {
		// velocidade na camada principal(mainLayer) deve ter sido setada antes.
		for (int i = imageLayerList.size() - 4; i >= 0; i--) {
			if (i % 2 == 0) {
				imageLayerList.get(i).setVel(imageLayerList.get(i + 2).getVelX() / 2,
						imageLayerList.get(i + 2).getVelY() / 2);
			}
		}
		for (int i = 0; i < imageLayerList.size(); i++) {
			if (i % 2 != 0) {
				imageLayerList.get(i).setVel(imageLayerList.get(i - 1).getVelX(), imageLayerList.get(i - 1).getVelY());
			}
		}
	}

	/**
	 * It sets the velocity of all the layers from the main layer, dividing the
	 * following layers by half the velocity of the main layer.
	 * 
	 * @param velMainLayerX - velocity of the main layer on the X axis
	 * @param velMainLayerY - velocity of the main layer on the Y axis
	 */
	public void setVelAllLayers(double velMainLayerX, double velMainLayerY) {
		// vel na camada principal (camada da frente, ou seja, a ultima a ser pintada).
		mainLayer.setVel(velMainLayerX, velMainLayerY);
		for (int i = imageLayerList.size() - 4; i >= 0; i--)
			if (i % 2 == 0)
				imageLayerList.get(i).setVel(imageLayerList.get(i + 2).getVelX() / 2,
						imageLayerList.get(i + 2).getVelY() / 2);

		for (int i = 0; i < imageLayerList.size(); i++)
			if (i % 2 != 0)
				imageLayerList.get(i).setVel(imageLayerList.get(i - 1).getVelX(), imageLayerList.get(i - 1).getVelY());

	}

	/**
	 * Sets the velocity of all the layers from their widths. Dividing the width of
	 * the layer by the width of the main layer
	 */
	public void setVelAllLayersStandard() {
		for (int i = 0; i < imageLayerList.size(); i++) {
			imageLayerList.get(i).setVel((float) imageLayerList.get(i).width / (float) mainLayer.width,
					(float) imageLayerList.get(i).height / (float) mainLayer.height);
		}
	}

	/**
	 * Move all the layers in a direction along the x axis.
	 * 
	 * @param left - 'true' move all layers to the left, 'false' move all layers to
	 *             the right
	 */
	public void moveLayersStandardX(boolean left) {
		for (int i = 0; i < imageLayerList.size(); i++)
			imageLayerList.get(i).moveLayerX(left);

	}

	/**
	 * Move all the layers in a direction along the x axis.
	 * 
	 * @param up - 'true' all layers move upward, 'false' move all the layers down
	 */
	public void moveLayersStandardY(boolean up) {
		for (int i = 0; i < imageLayerList.size(); i++)
			imageLayerList.get(i).moveLayerY(up);

	}

	/**
	 * Returns the layer. To return the layer must be past its position in the list.
	 * remembering that the first position is '0 '
	 * 
	 * @param n - layer position in the list
	 * @return ParallaxLayers
	 */
	public ParallaxLayers getLayer(int n) {
		if (n % 2 != 0) // se for ímpar
			return imageLayerList.get((n * 2)); // é multiplicado por 2 para não retornar o indice das cópias, desta
												// forma
		// ele retorna apenas as originais.
		else
			return imageLayerList.get(n * 2);
	}

	/**
	 * Sets the main layer
	 * 
	 * @param layer - layer that belongs to list 'ParallaxLayers'
	 */
	public void setMainLayer(ParallaxLayers layer) {
		mainLayer = layer;
	}

	/**
	 * Returns the main layer.
	 * 
	 * @return GameImage
	 */
	public GameImage getMainLayer() {
		return mainLayer;
	}

	/**
	 * Sets velocity of the main layer in the x axis and y.
	 * 
	 * @param x - velocity of the main layer in the x-axis
	 * @param y - velocity of the main layer in the y-axis
	 */
	public void setVelMainLayer(double x, double y) {
		mainLayer.setVel(x, y);
	}

	/**
	 * Sets velocity of a layer in the x axis and y.
	 * 
	 * @param velX - new velocity in the x-axis.
	 * @param velY - new velocity in the y-axis.
	 * @param bg   - Layer which will be assigned the value of the new velocity.
	 */
	public void setVelLayer(double velX, double velY, ParallaxLayers bg) {
		bg.setVelX(velX);
		bg.setVelY(velY);
		imageLayerList.get(imageLayerList.indexOf(bg) + 1).setVelX(velX);
		imageLayerList.get(imageLayerList.indexOf(bg) + 1).setVelX(velY);
	}

	/**
	 * Sets velocity of a layer in the x axis.
	 * 
	 * @param velX - new velocity in the x-axis.
	 * @param bg   - Layer which will be assigned the value of the new velocity.
	 */
	public void setVelLayerX(double velX, ParallaxLayers bg) {
		bg.setVelX(velX);
		imageLayerList.get(imageLayerList.indexOf(bg) + 1).setVelX(velX);
	}

	/**
	 * Sets velocity of a layer in the y axis
	 * 
	 * @param velY - new velocity in the y-axis.
	 * @param bg   - Layer which will be assigned the value of the new velocity.
	 */
	public void setVelLayerY(double velY, ParallaxLayers bg) {
		bg.setVelX(velY);
		imageLayerList.get(imageLayerList.indexOf(bg) + 1).setVelX(velY);
	}

	protected void repeatLayerX(int windowWidth, int i) {
		if (i % 2 != 0) {
			if (imageLayerList.get(i - 1).x < 0)
				imageLayerList.get(i).x = imageLayerList.get(i - 1).x + imageLayerList.get(i - 1).width;

			if (imageLayerList.get(i - 1).x > 0)
				imageLayerList.get(i).x = imageLayerList.get(i - 1).x - imageLayerList.get(i).width;

			if (imageLayerList.get(i - 1).x > windowWidth)
				imageLayerList.get(i - 1).x = imageLayerList.get(i).x - imageLayerList.get(i).width;

			if (imageLayerList.get(i - 1).x + imageLayerList.get(i - 1).width < 0)
				imageLayerList.get(i - 1).x = imageLayerList.get(i).x + imageLayerList.get(i).width;
		}
	}

	protected void repeatLayerY(int windowHeight, int i) {
		if (i % 2 != 0) {
			if (imageLayerList.get(i - 1).y < 0)
				imageLayerList.get(i).y = imageLayerList.get(i - 1).y + imageLayerList.get(i - 1).height;

			if (imageLayerList.get(i - 1).y > 0)
				imageLayerList.get(i).y = imageLayerList.get(i - 1).y - imageLayerList.get(i).height;

			if (imageLayerList.get(i - 1).y > windowHeight)
				imageLayerList.get(i - 1).y = imageLayerList.get(i).y - imageLayerList.get(i).height;

			if (imageLayerList.get(i - 1).y + imageLayerList.get(i - 1).height < 0)
				imageLayerList.get(i - 1).y = imageLayerList.get(i).y + imageLayerList.get(i).height;
		}
	}
}
