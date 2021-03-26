package br.com.wellington.jplay2D.scenery;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Vector;

import br.com.wellington.jplay2D.image.GameImage;
import br.com.wellington.jplay2D.image.GameObject;
import br.com.wellington.jplay2D.image.TileInfo;
import br.com.wellington.jplay2D.window.Window;

/** Class responsible for handling a Scenario. */
public class Scenery extends FileScenery {
	private static Window WINDOW;

	private ArrayList<GameObject> sceneElements;
	private int drawStartX = 0;
	private int drawStartY = 0;
	private double centerPointX;
	private double centerPointY;
	private boolean movedx;
	private boolean movedy;
	private double xOffset = 0;
	private double yOffset = 0;

	public Scenery() {
		super();
		centerPointX = Window.getInstance().getJFrame().getWidth() / 2;
		centerPointY = Window.getInstance().getJFrame().getHeight() / 2;
		WINDOW = Window.getInstance();
	}

	/**
	 * Define a posição inicial X e Y que será usada para desenhar a cena.
	 * 
	 * @param drawStartX
	 * @param drawStartY
	 */
	public void setDrawStartPos(int drawStartX, int drawStartY) {
		this.drawStartX = drawStartX;
		this.drawStartY = drawStartY;
	}

	/** Draws the scene on the screen. */
	public void draw() {
		// primeiro limpe a cena
		WINDOW.clear(Color.BLACK);

		// primeiro desenhe o pano de fundo
		int startDrawX = drawStartX;
		int startDrawY = drawStartY;

		backDrop.draw();

		// agora desenhe o conjunto de peças
		int line = 0;
		int drawY = startDrawY;

		do {
			ArrayList<TileInfo> tileLine = mapMatriz.get(line);

			int drawX = startDrawX;

			for (int c = 0; c < tileLine.size(); c++, drawX += tileWidth) {
				TileInfo tileInfo = (TileInfo) tileLine.get(c);

				if (tileInfo.id == 0) {
					continue;
				}
				tileList[tileInfo.id - 1].x = drawX;
				tileList[tileInfo.id - 1].y = drawY;
				tileList[tileInfo.id - 1].draw();
			}

			drawY += tileHeight;
			line++;

		} while (line < mapMatriz.size());

		// finalmente desenha os elementos do cenário
		for (int i = 0; i < sceneElements.size(); i++) {
			GameImage element = (GameImage) sceneElements.get(i);
			element.draw();
		}
	}

	/**
	 * Retorna as informações do arquivo armazenadas na posição da linha e coluna da
	 * matriz.
	 * 
	 * @param row    Linha na matriz.
	 * @param colunm Coluna na matriz.
	 * @return TileInfo
	 */
	public TileInfo getTile(int row, int colunm) {
		return mapMatriz.get(row).get(colunm);
	}

	/**
	 * Retorna os blocos abaixo da área limitada pelos pontos máximo e mínimo.
	 * 
	 * @param min Ponto do canto superior esquerdo da área.
	 * @param max Ponto inferior direito da área.
	 * @return Vector
	 */
	public Vector<TileInfo> getTilesFromRect(Point min, Point max) {
		Vector<TileInfo> v = new Vector<>();

		int startDrawX = drawStartX;
		int startDrawY = drawStartY;

		int minLine = max(0, (centerPointY - WINDOW.getJFrame().getHeight() / 2) / tileHeight);

		int maxLine = min(mapMatriz.size(),
				Math.ceil((centerPointY + WINDOW.getJFrame().getHeight() / 2) / tileHeight));

		int line = minLine;
		int drawY = startDrawY;

		do {
			ArrayList<TileInfo> tileLine = mapMatriz.get(line);

			int drawX = startDrawX;

			double minColumn = max(0, (centerPointX - WINDOW.getJFrame().getWidth() / 2) / tileWidth);

			int maxColumn = min(tileLine.size(), (int) Math
					.ceil(((double) centerPointX + WINDOW.getJFrame().getWidth() / 2.0) / (double) tileWidth));

			for (int c = (int) minColumn; c < maxColumn; c++) {
				TileInfo tile = (TileInfo) tileLine.get(c);

				// tile.x = drawX;
				// tile.y = drawY;
				tile.width = tileWidth;
				tile.height = tileHeight;

				drawX += tileWidth;
				if ((min.x > drawX + tileWidth - 1) || (max.x < tile.x)) {
					continue;
				}
				if ((min.y > drawY + tileHeight + 1) || (max.y < tile.y)) {
					continue;
				}
				v.add(tile);
			}

			drawY += tileHeight;
			line++;

		} while (line < maxLine);

		return v;
	}

	public Vector<TileInfo> getTilesFromPosition(Point min, Point max) {
		Vector<TileInfo> v = new Vector<>();

		int tileWidth = tileList[0].width;
		int tileHeight = tileList[0].height;

		int line = 0;
		int drawY = (int) -(centerPointY - Window.getInstance().getJFrame().getHeight() / 2);

		do {
			ArrayList<TileInfo> tileLine = mapMatriz.get(line);

			int drawX = (int) -(centerPointX - Window.getInstance().getJFrame().getWidth() / 2);

			for (int c = 0; c < tileLine.size(); c++) {
				TileInfo tile = (TileInfo) tileLine.get(c);

				// tile.x = drawX;
				// tile.y = drawY;
				tile.width = tileWidth;
				tile.height = tileHeight;

				drawX += tileWidth;
				if ((min.x > drawX + tileWidth - 1) || (max.x < tile.x)) {
					continue;
				}
				if ((min.y > drawY + tileHeight + 1) || (max.y < tile.y)) {
					continue;
				}
				v.add(tile);
			}

			drawY += tileHeight;
			line++;

		} while (line < mapMatriz.size());

		return v;
	}

	/**
	 * Remove um ladrilho da matriz.
	 * 
	 * @param row
	 * @param colunm
	 */
	public void removeTile(int row, int colunm) {
		ArrayList<TileInfo> tileLine = (ArrayList<TileInfo>) mapMatriz.get(row);
		if (colunm < tileLine.size())
			tileLine.remove(colunm);
	}

	/**
	 * Altera o armazenamento do bloco de id na matriz.
	 * 
	 * @param row    Linha da matriz.
	 * @param colunm Coluna da matriz.
	 * @param newID  Novo código que irá substituir o antigo Id representado pela
	 *               linha e coluna.
	 */
	public void changeTile(int row, int colunm, int newID) {
		mapMatriz.get(row).get(colunm).id = newID;
	}

	/**
	 * Desenha o cenário com o GameObject centralizado caso o mapa se mova.
	 * 
	 * @param object O objeto a ser centralizado.
	 */
	public void drawnMoveScene(GameObject object) {

		// first clear the scene
		Graphics g = Window.getInstance().getGameGraphics();
		Window.getInstance().clear(Color.BLACK);
		xOffset = 0;
		yOffset = 0;

		backDrop.draw();

		// now draw the tile set
		int tileWidth = tileList[0].width;
		int tileHeight = tileList[0].height;

		double x = object.x - Window.getInstance().getJFrame().getWidth() / 2;
		double y = object.y - Window.getInstance().getJFrame().getHeight() / 2;

		UpdateCenterPosition(x, y);

		int line = 0;
		int drawY = (int) -(centerPointY - Window.getInstance().getJFrame().getHeight() / 2);

		do {
			ArrayList<TileInfo> tileLine = mapMatriz.get(line);

			int drawX = (int) -(centerPointX - Window.getInstance().getJFrame().getWidth() / 2);

			for (int c = 0; c < tileLine.size(); c++) {
				TileInfo tileInfo = (TileInfo) tileLine.get(c);

				if (tileInfo.id != 0) {
					tileInfo.x = drawX;
					tileInfo.y = drawY;
					tileList[tileInfo.id - 1].x = drawX;
					tileList[tileInfo.id - 1].y = drawY;
					tileList[tileInfo.id - 1].draw();
				}
				drawX += tileWidth;
			}
			drawY += tileHeight;
			line++;

		} while (line < mapMatriz.size());

		// finally draw the overlays
		for (int i = 0; i < sceneElements.size(); i++) {
			GameImage element = (GameImage) sceneElements.get(i);
			element.draw();
		}
		if (movedx)
			xOffset = Window.getInstance().getJFrame().getWidth() / 2 - object.x;
		if (movedy)
			yOffset = Window.getInstance().getJFrame().getHeight() / 2 - object.y;

	}

	/**
	 * 
	 * @param x
	 * @param y
	 */
	private void UpdateCenterPosition(double x, double y) {
		centerPointX += x;
		centerPointY += y;
		movedx = true;
		movedy = true;

		int tileWidth = tileList[0].width;
		int tileHeight = tileList[0].height;

		ArrayList<TileInfo> tileLine = mapMatriz.get(0);

		if (centerPointX > tileWidth * tileLine.size() - Window.getInstance().getJFrame().getWidth() / 2) {
			centerPointX = tileWidth * tileLine.size() - Window.getInstance().getJFrame().getWidth() / 2;
			movedx = false;
		} else if (centerPointX < Window.getInstance().getJFrame().getWidth() / 2) {
			centerPointX = Window.getInstance().getJFrame().getWidth() / 2;
			movedx = false;
		}

		if (centerPointY > tileHeight * mapMatriz.size() - Window.getInstance().getJFrame().getHeight() / 2) {
			centerPointY = tileHeight * mapMatriz.size() - Window.getInstance().getJFrame().getHeight() / 2;
			movedy = false;
		} else if (centerPointY < Window.getInstance().getJFrame().getHeight() / 2) {
			centerPointY = Window.getInstance().getJFrame().getHeight() / 2;
			movedy = false;
		}
	}

	public double getXOffset() {
		return xOffset;
	}

	public double getYOffset() {
		return yOffset;
	}

	private int max(double a, double b) {
		return (int) ((a > b) ? a : b);
	}

	private int min(double a, double b) {
		return (int) ((a < b) ? a : b);
	}

	@Override
	public void loadFromFile(String sceneFilePath, String tilesPath) {
		super.loadFromFile(sceneFilePath, tilesPath);
		sceneElements = new ArrayList<>();
	}

	/**
	 * Adiciona um novo elemento a cena a ser desenhado após o do mapa.
	 * 
	 * @param elements Um GameObject.
	 */
	public void addSceneElements(GameObject elements) {
		sceneElements.add(elements);
	}
}
