/*
 * The use this code commercially must only be done with permission of the author.
 * Any modification shall be advised and sent to the author.
 * The author is not responsible for any problem therefrom the use of this frameWork.
 *
 * 
 * @author VisionLab/PUC-Rio
 * Modifications by Gefersom Cardoso Lima  
 *                  Federal Fluminense University
 *                  Computer Science
 */

package br.com.wellington.jplay2D;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/** Class responsible for handling a Scenario. */
public class Scene {

	/** Ponto central da janela no eixo X */
	protected static int centerWindowX;

	/** Ponto central da janela no eixo Y */
	protected static int centerWindowY;

	/** Guarda todos os dados do arquivo da cena */
	private SceneFile scene;

	/** Lista de objetos contidos na cena. */
	protected List<GameObject> listaObjetosCena;

	/** true para mapa deslocou no eixo x */
	protected boolean movedx;

	/** true para mapa deslocou no eixo y */
	protected boolean movedy;

	/** Valor do deslocamento do mapa no eixo x */
	protected double xOffset = 0;

	/** Valor do deslocamento do mapa no eixo y */
	protected double yOffset = 0;

	/** Valor do deslocamento do desenho da matriz do mapa no quadro no eixo x */
	protected int drawStartX = 0;

	/** Valor do deslocamento do desenho da matriz do mapa no quadro no eixo y */
	protected int drawStartY = 0;

	/***/
	protected int centerPositionX;
	/***/
	protected int centerPositionY;

	/** Cria o controle de cenas do jogo. */
	public Scene(String name) {
		centerWindowX = Window.getInstance().getWidth() / 2;
		centerWindowY = Window.getInstance().getHeight() / 2;
		listaObjetosCena = new ArrayList<>();
		scene = new SceneFile();
		loadScene(name);
	}

	/**
	 * Carrega uma nova cena.
	 * <p>
	 * PADRÃO E CONFIGURAÇÃO DO ARQUIVO CENA
	 * <ul>
	 * <li>Por convenção use a extenção '.scn' no nome do arquivo</li>
	 * <li>Use imagens no formato '.png'</li>
	 * <li>todas as tiles DEVEM possuir a mesma dimensão (sugestão 32x32)</li>
	 * <li>Ao projetar a matriz do mapa no arquivo, insira os 'zeros' a esquerda em
	 * todos os ids usados para manter um padrão, sendo assim se tiver 9 tiles
	 * definidos use de 0 a 9 se tiver 30 use 00 a 30, etc</li>
	 * <li>O id 0 no mapa refere a um ponto onde não possui tile</li>
	 * <li>não use ids de tiles não definidas</li>
	 * <li>O mapa suporta um máximo de 999.999.999 tiles</li>
	 * <li>A tile que ficará como pano de fundo deve existir</li>
	 * </ul>
	 * <p>
	 * ESTRUTURA DO ARQUIVO<br>
	 * <ul>
	 * <li>Na primeira linha deve conter a quantidade desejada de tiles que pretende
	 * usar no mapa.</li>
	 * <li>Nas proximas 'n' linhas ('n' = quantiade de tiles), cada linha deverá
	 * conter o nome da imagem desejada ou um '-' caso a imagem ainda não tenha cido
	 * adicionada ao projeto Obs.: O número da linha da imagem -1 representa o ID do
	 * tile no mapa/li>
	 * <li>As próximas linhas deve conter a estrutura do mapa onde cada tile deve
	 * ser indicada pelo seu id e separada pelo caracter ',' (de a preferencia em
	 * criar um mapa quadrado)</li>
	 * <li>Na linha abaixo da matriz do mapa adicione um '%' que indicara o fim
	 * dele</li>
	 * <li>A ultima linha deve conter o endereço da imagem que ficara como pano de
	 * fundo do cenario. Sugestão, escolha uma imagems com ao menos as dimensões do
	 * mapa, mas de sempre a preferencia ao tamanho da janela.</li>
	 * </ul>
	 * <p>
	 * Exemplo de um mapa<br>
	 * 
	 * +- arquivoCena01.scn --------------------+<br>
	 * 5<br>
	 * parede.png<br>
	 * grama.png<br>
	 * -<br>
	 * -<br>
	 * -<br>
	 * 01,01,01,01,01,01,01<br>
	 * 01,02,02,02,02,02,01<br>
	 * 01,02,02,02,02,02,01<br>
	 * 01,02,02,02,02,02,01<br>
	 * 01,02,02,02,02,02,01<br>
	 * 01,02,02,02,02,02,01<br>
	 * 01,01,01,01,01,01,01<br>
	 * %<br>
	 * backdrop.png<br>
	 * +----------------------------------------+<br>
	 * 
	 * @param name O nome do arquivo scn.
	 */
	public void loadScene(String name) {// ok
		centerPositionX = centerWindowX;
		centerPositionY = centerWindowY;
		listaObjetosCena.clear();
		scene.loadfile(name);
	}

	/**
	 * Salva o mapa atual em um novo arquivo.scn.
	 * 
	 * @param path O caminho com o nome do arquivo.
	 */
	public void saveToFile(String path) {// ok
		scene.saveFile(path);
	}

	/** Desenha a matriz do mapa no quadro. */
	public void draw() {// ok
		// fundo do quadro
		scene.backdrop.draw();
		// desenha as tiles
		int drawY = drawStartY;
		for (List<TileInfo> tileLine : scene.matrizMapa) {
			int drawX = drawStartX;
			for (TileInfo tile : tileLine) {
				if (tile.getId() > 0) {
					scene.tiles[tile.getId() - 1].x = drawX;
					scene.tiles[tile.getId() - 1].y = drawY;
					scene.tiles[tile.getId() - 1].draw();
				}
				drawX += scene.tileWidth;
			}
			drawY += scene.tileHeight;
		}
		// desenha os objetos adicionados no mapa
		for (int i = 0; i < listaObjetosCena.size(); i++) {
			GameImage element = (GameImage) listaObjetosCena.get(i);
			element.draw();
		}
	}

	/**
	 * Desenha a matriz do mapa no quadro.
	 * 
	 * 
	 * 
	 * Desenha a cena com um objeto do jogo em controle central, caso o mapa se
	 * desloque para cima, para baixo para direita ou para esquerda o objeto ficará
	 * centralizado e acompanhará o mapa.
	 * 
	 * @param obj Objeto de controle de movimento do mapa.
	 */
	public void drawMoveScene(GameObject obj) {
		centerPositionX += obj.x - centerWindowX;
		centerPositionY += obj.y - centerWindowY;
		movedx = movedy = true;
		// tamTileX * qtdColMtz - metPxlWinX = meio do mapa no eixo x em pixels
		int value = scene.tileWidth * scene.matrizMapa.get(0).size() - centerWindowX;
		if (centerPositionX > value) {// indo pra direita
			centerPositionX = value;
			movedx = false;// O mapa não se moveu no eixo X
		} else if (centerPositionX < centerWindowX) {// indo pra esquerda
			centerPositionX = centerWindowX;
			movedx = false;// O mapa não se moveu no eixo X
		}
		// tamTileY * qtdLinMtz - metPxlWinY = meio do mapa no eixo y em pixels
		value = scene.tileHeight * scene.matrizMapa.size() - centerWindowY;
		if (centerPositionY > value) {// indo pra baixo
			centerPositionY = value;
			movedy = false;// o mapa não se moveu no eixo y
		} else if (centerPositionY < centerWindowY) {// Indo pra cima
			centerPositionY = centerWindowY;
			movedy = false;// o mapa não se moveu no eixo y
		}
		// Desenha o pano de fundo
		scene.backdrop.draw();
		// desenha todas as tiles do mapa no ponto corrente
		int drawY = -(centerPositionY - centerWindowY);
		for (List<TileInfo> lista : scene.matrizMapa) {
			int drawX = -(centerPositionX - centerWindowX);
			for (TileInfo tile : lista) {
				if (tile.getId() != 0) {
					tile.x = drawX;
					tile.y = drawY;
					GameImage tgi = scene.tiles[tile.getId() - 1];
					tgi.x = drawX;
					tgi.y = drawY;
					tgi.draw();
				}
				drawX += scene.tileWidth;
			}
			drawY += scene.tileHeight;
		}
		// Desenha os objetos adicionados no mapa
		listaObjetosCena.forEach(n -> {
			((GameImage) n).draw();
		});
		xOffset = movedx ? centerWindowX - obj.x : 0;
		yOffset = movedy ? centerWindowY - obj.y : 0;
	}

	/**
	 * Retorna uma lista contendo os tiles do quadrante do parametro.
	 * 
	 * @param min Ponto superior esquerdo.
	 * @param max Ponto inferior direito.
	 * @return Uma lista contendo os tiles do quadrante.
	 */
	public List<TileInfo> getTilesFromPosition(Point min, Point max) {
		List<TileInfo> list = new ArrayList<TileInfo>();
		int drawY = -(centerPositionY - centerWindowY);
		for (List<TileInfo> line : scene.matrizMapa) {
			int drawX = -(centerPositionX - centerWindowX);
			for (TileInfo tile : line) {
				tile.width = scene.tileWidth;
				tile.height = scene.tileHeight;
				drawX += scene.tileWidth;
				if ((min.x > drawX + scene.tileWidth - 1) || (max.x < tile.x)) {
					continue;
				}
				if ((min.y > drawY + scene.tileHeight + 1) || (max.y < tile.y)) {
					continue;
				}
				list.add(tile);
			}
			drawY += scene.tileHeight;
		}
		return list;
	}

	/**
	 * Remove a tile da matriz (não do arquivo).
	 * 
	 * @param x Coluna da matriz (0 a n-1).
	 * @param y Linha da matriz (0 a n-1).
	 */
	public void removeTile(int x, int y) {// ok
		changeTile(x, y, 00);
	}

	/**
	 * Altera a id de uma tile na matriz do cena.
	 * 
	 * Obs.: A alteração não será feita no arquivo.
	 * 
	 * @param x  Coluna da matriz (0 a n-1).
	 * @param y  Linha da matriz (0 a n-1).
	 * @param id O id da tile no arquivo (1 a n), o id 0 (zero) é o indicador para
	 *           que apenas seja removido a tile.
	 * @return false Em caso de linha, coluna ou id inválido.
	 */
	public boolean changeTile(int x, int y, int id) {// ok
		try {
			scene.matrizMapa.get(y).get(x).setId(id);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	/**
	 * adiciona um objetos ao mapa.
	 * 
	 * @param obj o objeto.
	 */
	public void addOverlay(GameObject obj) {// ok
		listaObjetosCena.add(obj);
	}

	/**
	 * Define o ponto inicial no quadro de onde a matriz do mapa será desenhado.
	 * 
	 * @param x valor do eixo x.
	 * @param y Valor do eixo y.
	 */
	public void setDrawStartPos(int x, int y) {// ok
		this.drawStartX = x;
		this.drawStartY = y;
	}

	/**
	 * Returns the Tiles below the area bounded by max and min points.
	 * 
	 * implantar antes de alterar
	 * 
	 * @param min Upper left corner point of the area.
	 * @param max Lower right point of the area.
	 * @return Vector
	 */
	public Vector<TileInfo> getTilesFromRect(Point min, Point max) {
		Vector<TileInfo> v = new Vector<>();

		int startDrawX = drawStartX;
		int startDrawY = drawStartY;

		int tileWidth = scene.tileWidth;
		int tileHeight = scene.tileHeight;

		int minLine = max(0, (centerPositionY - centerWindowY) / tileHeight);
		int maxLine = min(scene.matrizMapa.size(),
				(int) Math.ceil(((double) centerPositionY + centerWindowY) / (double) tileHeight));

		int line = minLine;
		int drawY = startDrawY;

		do {
			List<TileInfo> tileLine = scene.matrizMapa.get(line);

			int drawX = startDrawX;

			int minColumn = max(0, (centerPositionX - centerWindowX) / tileWidth);
			int maxColumn = min(tileLine.size(),
					(int) Math.ceil(((double) centerPositionX + centerWindowX) / (double) tileWidth));

			for (int c = minColumn; c < maxColumn; c++) {
				TileInfo tile = (TileInfo) tileLine.get(c);

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
	 * Retorna O deslocamento em pixel do eixo x do mapa.
	 * 
	 * @return O deslocamento em pixel do eixo x do mapa.
	 */
	public double getXOffset() {
		return xOffset;
	}

	/**
	 * Retorna O deslocamento em pixel do eixo y do mapa.
	 * 
	 * @return O deslocamento em pixel do eixo y do mapa.
	 */
	public double getYOffset() {
		return yOffset;
	}

	/**
	 * Retorna o maior valor.
	 * 
	 * @param a valor de a.
	 * @param b valor de b
	 * @return O maior valor.
	 */
	private int max(int a, int b) {// ok
		return (a > b) ? a : b;
	}

	/**
	 * Retorna o menor valor.
	 * 
	 * @param a valor de a.
	 * @param b valor de b.
	 * @return O menor valor.
	 */
	private int min(int a, int b) {// ok
		return (a < b) ? a : b;
	}

	/**
	 * Retorna o tile referente as coordenadas dadas na matriz do mapa do arquivo.
	 * 
	 * @param x      Coordenada do eixo x na matriz do arquivo.
	 * @param coluna Coordenada do eixo y na matriz do arquivo.
	 * @return TileInfo O tile desejado.
	 */
	public TileInfo getTile(int x, int y) {
		return scene.matrizMapa.get(y).get(x);
	}

}
