package br.com.wellington.jplay2D.scene;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import br.com.wellington.jplay2D.exception.Jplay2DRuntimeException;
import br.com.wellington.jplay2D.image.GameImage;
import br.com.wellington.jplay2D.image.Sprite;
import br.com.wellington.jplay2D.image.TileInfo;

class FileScene {

	private static final String END_MAP_MATRIZ = "%";
	private static final String TILES_SEPARATOR = "-";
	private static final String NONE = "-";

	/** Matriz do mapa */
	protected ArrayList<ArrayList<TileInfo>> mapMatriz;
	/** Caminho de todas as tiles do arquivo */
	protected String nameTiles[];
	/** Lista referente ao bloco de tiles do arquivo */
	protected GameImage[] tileList;
	/** Desenho de plano de fundo do cenário */
	protected GameImage backDrop;
	/** Largura de um tile. */
	protected int tileWidth;
	/** Altura de um tile. */
	protected int tileHeight;

	/**
	 * Carrega uma cena de um arquivo.
	 * 
	 * @param sceneFile Caminho de arquivo.
	 */
	public void loadFromFile(String sceneFilePath, String tilesPath) {
		mapMatriz = new ArrayList<>();

		List<String> fileLines = new ArrayList<>();

		try {// [CARREGA O ARQUILO .scn]
			BufferedReader input = new BufferedReader(new FileReader(new File(sceneFilePath)));
			String linha;
			while (true) {
				if ((linha = input.readLine()) == null) {
					break;
				}
				fileLines.add(linha);
			}
		} catch (Exception ex) {
			throw new Jplay2DRuntimeException(new StringBuilder("[ERRO] {Scene.loadFromFile}")//
					.append("\n -Problemas ao carregar o arquivo {")//
					.append(sceneFilePath).append("}").toString(), ex);
		}

		try {
			int indexLine = -1;// indice da linha do arquivo carregado

			// [1A LINHA]
			int numberTiles = Integer.parseInt(fileLines.get(++indexLine), 10);

			tileList = new GameImage[numberTiles];
			nameTiles = new String[numberTiles + 1];

			// [LEITURA DO BLOCO DE IMAGENS DAS TILES]
			for (int i = 0; i < numberTiles; i++) {
				String nameImage = fileLines.get(++indexLine);
				if (NONE.equals(nameImage)) {
					nameTiles[i] = NONE;
					continue;
				}
				tileList[i] = new Sprite(new StringBuilder(tilesPath).append(nameImage).toString());
				nameTiles[i] = nameImage;
			}

			// [LEITURA DA MATRIZ DO MAPA]
			StringBuilder linha = new StringBuilder();
			while (!END_MAP_MATRIZ.equals(linha.append(fileLines.get(++indexLine)).toString().trim())) {
				ArrayList<TileInfo> tileLine = new ArrayList<>();
				for (String sTile : linha.toString().trim().split(TILES_SEPARATOR)) {
					tileLine.add(new TileInfo(Integer.parseInt(sTile)));
				}
				mapMatriz.add(tileLine);
				linha.setLength(0);
			}

			// [CARREGA O BACKDROP]
			nameTiles[numberTiles] = fileLines.get(++indexLine);
			backDrop = new GameImage(new StringBuilder(tilesPath).append(nameTiles[numberTiles]).toString());

			// [CARREGA AS DIMENÇÕES DAS TILES]
			tileWidth = tileList[0].width;
			tileHeight = tileList[0].height;
		} catch (Exception ex) {
			throw new RuntimeException("[ERRO] Inesperado ", ex);
		}
	}

	/**
	 * Salve o estado atual da cena em um novo arquivo.
	 * 
	 * @param fileName Caminho do arquivo para salvar a cena.
	 */
	public void save(String fileName) {
		StringBuilder rows = new StringBuilder();
		rows.append(tileList.length);// [1a linha]
		for (int i = 0; i < nameTiles.length - 1; i++) {// [BLOCO DE IMAGENS DAS TILES]
			rows.append("\n").append(nameTiles[i]);
		}
		for (ArrayList<TileInfo> tileLine : mapMatriz) {// [MATRIZ DO MAPA]
			rows.append("\n");
			for (TileInfo tile : tileLine) {
				rows.append(tile.id).append(TILES_SEPARATOR);
			}
			rows.deleteCharAt(rows.length() - 1);
		}
		rows.append("\n").append(END_MAP_MATRIZ) // [TERMINAL DA MATRIZ]
				.append("\n").append(nameTiles[nameTiles.length - 1]);// [BACKDROP]
		try {// [GRAVA NO ARQUIVO]
			BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
			out.write(rows.toString());
			out.close();
		} catch (Exception ex) {
			throw new Jplay2DRuntimeException("Erro ao gravar o arquivo", ex);
		}
	}
}
