package br.com.wellington.jplay2D.scenery;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;

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

	/**
	 * 
	 * @param gameObject
	 * @return
	 */
	public List<TileInfo> getTilesFromPosition(GameObject gameObject) {
		TileInfo obj = new TileInfo();
		obj.x = gameObject.x;
		obj.y = gameObject.y;
		obj.width = (int) gameObject.x + gameObject.width + 1;
		obj.height = (int) gameObject.y + gameObject.height + 1;
		Check.tile = obj;

		List<TileInfo> list = new ArrayList<>();

		int drawX;
		int drawY = (int) -(centerPointY - WINDOW.getJFrame().getHeight() / 2);

		for (ArrayList<TileInfo> tileLine : mapMatriz) {
			drawX = (int) -(centerPointX - WINDOW.getJFrame().getWidth() / 2);
			for (TileInfo tile : tileLine) {
				tile.x = drawX;
				tile.y = drawY;
				tile.width = super.tileWidth;
				tile.height = super.tileWidth;

				drawX += super.tileWidth;

				if ((Check.axisX((int) tile.x) || Check.axisX((int) tile.x + tile.width))
						&& (Check.axisY((int) tile.y) || Check.axisY((int) tile.y + tile.height))) {
					list.add(tile);
				}

			}
			drawY += super.tileHeight;
		}
		return list;
	}

	/** ALTERANDO AQUI */
	public List<List<TileInfo>> getMatrixFromPosition(GameObject go) {
		List<List<TileInfo>> subMatrix = new ArrayList<>();
		TileInfo obj = new TileInfo();
		obj.x = go.x;
		obj.y = go.y;
		obj.width = (int) go.x + go.width + 1;
		obj.height = (int) go.y + go.height + 1;
		Check.tile = obj;

		int drawX;
		int drawY = (int) -(centerPointY - WINDOW.getJFrame().getHeight() / 2);

		for (ArrayList<TileInfo> tileLine : mapMatriz) {
			List<TileInfo> list = new ArrayList<>();
			drawX = (int) -(centerPointX - WINDOW.getJFrame().getWidth() / 2);
			for (TileInfo tile : tileLine) {
				tile.x = drawX;
				tile.y = drawY;
				tile.width = super.tileWidth;
				tile.height = super.tileWidth;

				drawX += super.tileWidth;

				if ((Check.axisX((int) tile.x) || Check.axisX((int) tile.x + tile.width))
						&& (Check.axisY((int) tile.y) || Check.axisY((int) tile.y + tile.height))) {
					list.add(tile);
				}

			}
			if (!list.isEmpty()) {
				subMatrix.add(list);
			}

			drawY += super.tileHeight;
		}
		return subMatrix;
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
		WINDOW.clear(Color.BLACK);
		backDrop.draw();
		xOffset = 0;
		yOffset = 0;
		JFrame frame = WINDOW.getJFrame();

		// UpdateCenterPosition
		centerPointX += (object.x - frame.getWidth() / 2);
		centerPointY += object.y - frame.getHeight() / 2;
		movedx = true;
		movedy = true;

		ArrayList<TileInfo> lin = mapMatriz.get(0);
		double value = (tileWidth * lin.size()) - frame.getWidth() / 2;
		if (centerPointX > value) {
			centerPointX = value;
			movedx = false;
		} else {
			value = frame.getWidth() / 2;
			if (centerPointX < value) {
				centerPointX = value;
				movedx = false;
			}
		}
		value = tileHeight * mapMatriz.size() - frame.getHeight() / 2;
		if (centerPointY > value) {
			centerPointY = value;
			movedy = false;
		} else {
			value = frame.getHeight() / 2;
			if (centerPointY < value) {
				centerPointY = value;
				movedy = false;
			}
		}
		// UpdateCenterPosition

		value = centerPointX - frame.getWidth() / 2;
		double drawY = -(centerPointY - frame.getHeight() / 2);
		for (ArrayList<TileInfo> tileLine : mapMatriz) {
			double drawX = -value;
			for (TileInfo tileInfo : tileLine) {
				if (tileInfo.id > 0 && drawX > -tileWidth && drawY > -tileHeight) {
					tileInfo.x = drawX;
					tileInfo.y = drawY;
					tileList[tileInfo.id - 1].x = drawX;
					tileList[tileInfo.id - 1].y = drawY;
					tileList[tileInfo.id - 1].draw();
				}
				drawX += tileWidth;
			}
			drawY += tileHeight;
		}

		// finally draw the overlays
		for (int i = 0; i < sceneElements.size(); i++) {
			GameImage element = (GameImage) sceneElements.get(i);
			element.draw();
		}
		if (movedx)
			xOffset = frame.getWidth() / 2 - object.x;
		if (movedy)
			yOffset = frame.getHeight() / 2 - object.y;

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

	private static class Check {
		static TileInfo tile = new TileInfo();

		static boolean axisX(int x) {
			return (tile.x < x) && (x < tile.width);
		}

		static boolean axisY(int y) {
			return (tile.y < y) && (y < tile.height);
		}
	}

}
