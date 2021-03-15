package br.com.wellington.jplay2D.scene;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.wellington.jplay2D.imageProcessing.GameImage;
import br.com.wellington.jplay2D.imageProcessing.Sprite;
import br.com.wellington.jplay2D.imageProcessing.TileInfo;

public class FileScene {

	private static final String END_TILE_SET = "%";

	/** lista das imagens das tiles */
	private GameImage[] tiles;

	/***/
	private ArrayList<ArrayList<TileInfo>> tileLayer;

	/***/
	private String nomeTiles[];// É usado quando queremos salvar o estado da cena

	/***/
	private GameImage backDrop;

	/**
	 * * Carrega um arquivo de cena.
	 * 
	 * @param sceneFilePath Caminho até o arquivo. (Exemplo:
	 *                      "src/java/resources/scenes/scene.scn").
	 * @param tilesPath     diretorio onde fica todos os tiles definidos no arquivo.
	 *                      (Exemplo: "src/java/resources/tiles").
	 */
	public void loadFromFile(String sceneFilePath, String tilesPath) {
		tileLayer = new ArrayList<>();
		// overlays = new ArrayList();

		StringBuilder linha = new StringBuilder();// controle de linha
		try {
			// [acesso ao arquivo]
			BufferedReader input = new BufferedReader(new FileReader(new File(sceneFilePath)));

			// [leitura da 1a linha]
			linha.append(input.readLine());// resgate da primeira linha

			int qtdTiles = Integer.parseInt(linha.toString(), 10);// converte para número

			// preparação do resgate dos arquivos das tiles
			tiles = new GameImage[qtdTiles];
			nomeTiles = new String[qtdTiles + 1];

			// [Leitura do bloco de imagens]
			for (int i = 0; i < qtdTiles; i++) {
				linha.setLength(0);// limpa a instancia
				linha.append(tilesPath).append("/").append(input.readLine());// prepara a próxima linha
				tiles[i] = new Sprite(linha.toString());// guarda a imagem da tile
				nomeTiles[i] = linha.toString(); // guarda o caminho da tile
			}

			linha.setLength(0);// limpa a instancia
			// [leitura do mapa]
			while (!END_TILE_SET.equals(linha.append(input.readLine()).toString().trim())) {// Lê todo do mapa
				ArrayList<TileInfo> tileLine = new ArrayList<>();
				for (String sTile : linha.toString().trim().split(",")) {// instancia o mapa linha a linha
					tileLine.add(new TileInfo(Integer.parseInt(sTile)));
				}
				tileLayer.add(tileLine);
				linha.setLength(0);// limpa a instancia
			}

			// agora leia o arquivo do pano de fundo
			linha.setLength(0);// limpa a instancia
			linha.append(input.readLine());
			backDrop = new GameImage(linha.toString());
			nomeTiles[qtdTiles] = linha.toString();

		} catch (IOException e) {
			linha.setLength(0);// limpa a instancia
			linha.append("\n [ERRO] Problema ao carregar um arquivo '.scn', lista de possíveis problemas:");
			linha.append("\n [1] Parâmetro 'sceneFilePath' incocrreto {").append(sceneFilePath).append("}.");
			linha.append("\n [2] Parâmetro 'tilesPath' incorreto {").append(tilesPath).append("}.");
			linha.append("\n [3] Nome de tile incorreto.");
			linha.append("\n [4] Estrutura do arquivo incorreta.");
			throw new RuntimeException("[ERRO] arquivo");
		}
	}

	/**
	 * Remove um ladrilho da matriz.
	 * 
	 * @param row    Eixo x da posição do tile
	 * @param colunm Eixo y da posição do tile
	 */
	public void removeTile(int row, int colunm) {
		ArrayList<TileInfo> tileLine = (ArrayList<TileInfo>) tileLayer.get(row);
		if (colunm < tileLine.size()) {
			tileLine.remove(colunm);
		}
	}

	/**
	 * Altera o armazenamento do bloco de id na matriz.
	 * 
	 * @param row       Linha da matriz.
	 * @param colunm    Coluna da matriz.
	 * @param newTileId Novo código que irá substituir o antigo Id representado pela
	 *                  linha e coluna.
	 */
	public void changeTile(int row, int colunm, int newTileId) {
		ArrayList<TileInfo> tileLine = (ArrayList<TileInfo>) tileLayer.get(row);
		tileLine.get(colunm).id = newTileId;
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
				out.write(nomeTiles[i] + "\n");

			for (int i = 0; i < tileLayer.size(); i++) {
				ArrayList<TileInfo> tileLine = (ArrayList<TileInfo>) tileLayer.get(i);
				int j = 0;
				for (j = 0; j < tileLine.size() - 1; j++)
					out.write(tileLine.get(j).id + ",");

				out.write(tileLine.get(j).id + "\n");
			}

			out.write(END_TILE_SET + "\n");
			out.write(this.nomeTiles[tiles.length]);

			out.close();

		} catch (IOException ex) {
			Logger.getLogger(Scene.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

}
