package br.com.wellington.jplay2D.scene;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.wellington.jplay2D.imageProcessing.GameImage;
import br.com.wellington.jplay2D.imageProcessing.GameObject;
import br.com.wellington.jplay2D.imageProcessing.Sprite;
import br.com.wellington.jplay2D.imageProcessing.TileInfo;
import br.com.wellington.jplay2D.window.Window;

/**
 * Class responsible for handling a Scenario.
 */
public class Scene {
	private GameImage backDrop;
	private GameImage[] tiles;
	private String nameImages[];// É usado quando queremos salvar o estado da cena
	private ArrayList tileLayer;
	private ArrayList overlays;
	private int drawStartX = 0;
	private int drawStartY = 0;
	private int centerPositionX;
	private int centerPositionY;
	private boolean movedx;
	private boolean movedy;
	private double xOffset = 0;
	private double yOffset = 0;

	public Scene() {
		centerPositionX = Window.getInstance().getJFrame().getWidth() / 2;
		centerPositionY = Window.getInstance().getJFrame().getHeight() / 2;
	}

	/**
	 * Carrega uma cena de um arquivo.
	 * 
	 * @param sceneFile Caminho de arquivo.
	 */
	public void loadFromFile(String sceneFile) {
		tileLayer = new ArrayList();
		overlays = new ArrayList();

		try {
			BufferedReader input = new BufferedReader(new FileReader(new File(sceneFile)));

			// primeiro leia o número de imagens de blocos
			String line = input.readLine();
			int numOfTileImages = Integer.parseInt(line, 10);
			tiles = new GameImage[numOfTileImages];
			nameImages = new String[numOfTileImages + 1];

			for (int i = 0; i < numOfTileImages; i++) {
				// leia o nome de cada imagem de bloco
				line = input.readLine();
				tiles[i] = new Sprite(line);
				nameImages[i] = line;
			}

			// agora leia o mapa do conjunto de blocos até o final
			// caractere encontrado "%"
			String endTileSet = "%";

			line = input.readLine();

			while (line.equals(endTileSet) != true) {
				ArrayList tileLine = new ArrayList();

				String[] tileIndices = line.split(",");

				for (int i = 0; i < tileIndices.length; i++) {
					TileInfo tileInfo = new TileInfo();
					tileInfo.id = Integer.parseInt(tileIndices[i]);
					tileLine.add(tileInfo);
				}
				tileLayer.add(tileLine);

				line = input.readLine();
			}

			//agora leia o arquivo do pano de fundo
			line = input.readLine();
			backDrop = new GameImage(line);
			nameImages[numOfTileImages] = line;

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Adiciona uma cena de sobreposição.
	 * 
	 * @param overlay Qualquer GameObject.
	 */
	public void addOverlay(GameObject overlay) {
		overlays.add(overlay);
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

	/**
	 * Draws the scene on the screen.
	 */
	public void draw() {
		// primeiro limpe a cena
		Graphics g = Window.getInstance().getGameGraphics();
		Window.getInstance().clear(Color.BLACK);

		// primeiro desenhe o pano de fundo
		int startDrawX = drawStartX;
		int startDrawY = drawStartY;

		backDrop.draw();

		// agora desenhe o conjunto de peças
		int tileWidth = tiles[0].width;
		int tileHeight = tiles[0].height;

		int line = 0;
		int drawY = startDrawY;

		do {
			ArrayList tileLine = (ArrayList) tileLayer.get(line);

			int drawX = startDrawX;

			for (int c = 0; c < tileLine.size(); c++, drawX += tileWidth) {
				TileInfo tileInfo = (TileInfo) tileLine.get(c);

				if (tileInfo.id == 0) {
					continue;
				}
				tiles[tileInfo.id - 1].x = drawX;
				tiles[tileInfo.id - 1].y = drawY;
				tiles[tileInfo.id - 1].draw();
			}

			drawY += tileHeight;
			line++;

		} while (line < tileLayer.size());

		// finalmente desenhe as sobreposições
		for (int i = 0; i < overlays.size(); i++) {
			GameImage element = (GameImage) overlays.get(i);
			element.draw();
		}
	}

	/**
	 * Retorna as informações do arquivo armazenadas na posição da linha e coluna da matriz.
	 * 
	 * @param row    Linha na matriz.
	 * @param colunm Coluna na matriz.
	 * @return TileInfo
	 */
	public TileInfo getTile(int row, int colunm) {
		ArrayList<TileInfo> tileLine = (ArrayList<TileInfo>) tileLayer.get(row);
		return tileLine.get(colunm);
	}

	/**
	 * Retorna os blocos abaixo da área limitada pelos pontos máximo e mínimo.
	 * 
	 * @param min Ponto do canto superior esquerdo da área.
	 * @param max Ponto inferior direito da área.
	 * @return Vector
	 */
	@SuppressWarnings("UseOfObsoleteCollectionType")
	public Vector getTilesFromRect(Point min, Point max) {
		Vector v = new Vector();

		int startDrawX = drawStartX;
		int startDrawY = drawStartY;

		int tileWidth = tiles[0].width;
		int tileHeight = tiles[0].height;

		int minLine = max(0, (centerPositionY - Window.getInstance().getJFrame().getHeight() / 2) / tileHeight);
		int maxLine = min(tileLayer.size(), (int) Math.ceil(
				((double) centerPositionY + Window.getInstance().getJFrame().getHeight() / 2) / (double) tileHeight));

		int line = minLine;
		int drawY = startDrawY;

		do {
			ArrayList tileLine = (ArrayList) tileLayer.get(line);

			int drawX = startDrawX;

			int minColumn = max(0, (centerPositionX - Window.getInstance().getJFrame().getWidth() / 2) / tileWidth);
			int maxColumn = min(tileLine.size(),
					(int) Math.ceil(((double) centerPositionX + Window.getInstance().getJFrame().getWidth() / 2.0)
							/ (double) tileWidth));

			for (int c = minColumn; c < maxColumn; c++) {
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
				boolean add = v.add(tile);
			}

			drawY += tileHeight;
			line++;

		} while (line < maxLine);

		return v;
	}

	@SuppressWarnings("UseOfObsoleteCollectionType")
	public Vector getTilesFromPosition(Point min, Point max) {
		Vector v = new Vector();

		int tileWidth = tiles[0].width;
		int tileHeight = tiles[0].height;

		int line = 0;
		int drawY = -(centerPositionY - Window.getInstance().getJFrame().getHeight() / 2);

		do {
			ArrayList tileLine = (ArrayList) tileLayer.get(line);

			int drawX = -(centerPositionX - Window.getInstance().getJFrame().getWidth() / 2);

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
				boolean add = v.add(tile);
			}

			drawY += tileHeight;
			line++;

		} while (line < tileLayer.size());

		return v;
	}

	/**
	 * Remove um ladrilho da matriz.
	 * 
	 * @param row
	 * @param colunm
	 */
	public void removeTile(int row, int colunm) {
		ArrayList<TileInfo> tileLine = (ArrayList<TileInfo>) tileLayer.get(row);
		if (colunm < tileLine.size())
			tileLine.remove(colunm);
	}

	/**
	 * Altera o armazenamento do bloco de id na matriz.
	 * 
	 * @param row    Linha da matriz.
	 * @param colunm Coluna da matriz.
	 * @param newID  Novo código que irá substituir o antigo Id representado pela linha e coluna.
	 */
	public void changeTile(int row, int colunm, int newID) {
		ArrayList<TileInfo> tileLine = (ArrayList<TileInfo>) tileLayer.get(row);
		tileLine.get(colunm).id = newID;
	}

	/**
	 * Salve o estado atual da cena em um novo arquivo.
	 * 
	 * @param fileName Caminho do arquivo para salvar a cena.
	 */
	public void saveToFile(String fileName) {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(fileName));

			out.write(this.tiles.length + "\n");
			for (int i = 0; i < tiles.length; i++)
				out.write(nameImages[i] + "\n");

			for (int i = 0; i < tileLayer.size(); i++) {
				ArrayList<TileInfo> tileLine = (ArrayList<TileInfo>) tileLayer.get(i);
				int j = 0;
				for (j = 0; j < tileLine.size() - 1; j++)
					out.write(tileLine.get(j).id + ",");

				out.write(tileLine.get(j).id + "\n");
			}

			out.write("%\n");
			out.write(this.nameImages[tiles.length]);

			out.close();

		} catch (IOException ex) {
			Logger.getLogger(Scene.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	public void moveScene(GameObject object) {
		// primeiro limpe a cena
		Graphics g = Window.getInstance().getGameGraphics();
		Window.getInstance().clear(Color.BLACK);
		xOffset = 0;
		yOffset = 0;

		backDrop.draw();

		// agora desenhe o conjunto de peças
		int tileWidth = tiles[0].width;
		int tileHeight = tiles[0].height;

		double x = object.x - Window.getInstance().getJFrame().getWidth() / 2;
		double y = object.y - Window.getInstance().getJFrame().getHeight() / 2;

		UpdateCenterPosition(x, y);

		int line = 0;
		int drawY = -(centerPositionY - Window.getInstance().getJFrame().getHeight() / 2);

		do {
			ArrayList tileLine = (ArrayList) tileLayer.get(line);

			int drawX = -(centerPositionX - Window.getInstance().getJFrame().getWidth() / 2);

			for (int c = 0; c < tileLine.size(); c++) {
				TileInfo tileInfo = (TileInfo) tileLine.get(c);

				if (tileInfo.id != 0) {
					tileInfo.x = drawX;
					tileInfo.y = drawY;
					tiles[tileInfo.id - 1].x = drawX;
					tiles[tileInfo.id - 1].y = drawY;
					tiles[tileInfo.id - 1].draw();
				}
				drawX += tileWidth;
			}
			drawY += tileHeight;
			line++;

		} while (line < tileLayer.size());

		// finalmente desenhe as sobreposições
		for (int i = 0; i < overlays.size(); i++) {
			GameImage element = (GameImage) overlays.get(i);
			element.draw();
		}
		if (movedx)
			xOffset = Window.getInstance().getJFrame().getWidth() / 2 - object.x;
		if (movedy)
			yOffset = Window.getInstance().getJFrame().getHeight() / 2 - object.y;

	}

	private void UpdateCenterPosition(double x, double y) {
		centerPositionX += x;
		centerPositionY += y;
		movedx = true;
		movedy = true;

		int tileWidth = tiles[0].width;
		int tileHeight = tiles[0].height;

		ArrayList tileLine = (ArrayList) tileLayer.get(0);

		if (centerPositionX > tileWidth * tileLine.size() - Window.getInstance().getJFrame().getWidth() / 2) {
			centerPositionX = tileWidth * tileLine.size() - Window.getInstance().getJFrame().getWidth() / 2;
			movedx = false;
		} else if (centerPositionX < Window.getInstance().getJFrame().getWidth() / 2) {
			centerPositionX = Window.getInstance().getJFrame().getWidth() / 2;
			movedx = false;
		}

		if (centerPositionY > tileHeight * tileLayer.size() - Window.getInstance().getJFrame().getHeight() / 2) {
			centerPositionY = tileHeight * tileLayer.size() - Window.getInstance().getJFrame().getHeight() / 2;
			movedy = false;
		} else if (centerPositionY < Window.getInstance().getJFrame().getHeight() / 2) {
			centerPositionY = Window.getInstance().getJFrame().getHeight() / 2;
			movedy = false;
		}
	}

	public double getXOffset() {
		return (xOffset);
	}

	public double getYOffset() {
		return (yOffset);
	}

	private int max(int a, int b) {
		if (a > b)
			return a;
		else
			return b;
	}

	private int min(int a, int b) {
		if (a < b)
			return a;
		else
			return b;
	}
}
