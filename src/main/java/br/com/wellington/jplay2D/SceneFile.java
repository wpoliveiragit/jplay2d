package br.com.wellington.jplay2D;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import jplay.url.Url;

class SceneFile {

	/** Simbolo do fim da matriz do mapa no arquivo scn. */
	protected static final String FIM_MATRIZ_MAPA = "%";

	/** Simbolo de separacao das tiles na matriz do arquivo scn. */
	protected static final String SEPARADOR = ",";

	/** Conjunto das tiles existentes no arquivo */
	protected GameImage[] tiles;

	/** Matriz do mapa */
	protected List<List<TileInfo>> matrizMapa;

	/** Imagem de fundo da cena. */
	protected GameImage backdrop;

	int tileWidth;
	int tileHeight;

	public SceneFile() {
		matrizMapa = new ArrayList<List<TileInfo>>();
		tileWidth = 0;
		tileHeight = 0;
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
	public void loadfile(String value) {
		matrizMapa.clear();
		List<String> txtArq = new ArrayList<>();

		try {
			BufferedReader ler = new BufferedReader(new FileReader(new File(Url.getURL(value))));
			while (ler.ready()) {
				txtArq.add(ler.readLine());
			}
			ler.close();
		} catch (IOException e) {
			System.out.println("erro ao carregar o arquivo\n");
			e.printStackTrace();
		}

		// resgata quantidade de tiles da lista
		tiles = new GameImage[Integer.parseInt(txtArq.remove(0), 10)];

		// resgata a quantidade a lista de tiles
		for (int i = 0; i < tiles.length; i++) {
			value = txtArq.remove(0);
			if (value.contentEquals("-")) {
				tiles[i] = null;// linha com notação "-"
				continue;
			}
			tiles[i] = new SpritePlatform(value, 1, false);// cria a imagem
		}

		// resgata a matriz do mapa
		while (true) {
			value = txtArq.remove(0);
			if (value.equals(FIM_MATRIZ_MAPA)) {
				break;
			}
			List<TileInfo> tileLine = new ArrayList<>();
			for (String txt_id : value.split(SEPARADOR)) {
				TileInfo tile = new TileInfo(Integer.parseInt(txt_id, 10));
				tileLine.add(tile);
			}
			matrizMapa.add(tileLine);

		}
		// resgata o backend
		backdrop = new GameImage(txtArq.remove(0));
		// registra a dimeção padrão das tiles
		tileWidth = tiles[0].width;
		tileHeight = tiles[0].height;

	}

	/**
	 * Salva o mapa atual em um novo arquivo.scn.
	 * 
	 * @param path O caminho com o nome do arquivo.
	 */
	public void saveFile(String path) {// ok
		String txt = "" + tiles.length;// primeira linha
		String format = "%0" + txt.length() + "d";
		for (GameImage t : tiles) {// add linha nome tiles
			if (t == null) {
				txt += "\n-";
				continue;
			}
			txt += "\n" + t.getName();
		}
		for (List<TileInfo> linha : matrizMapa) {// add matriz cena
			txt += "\n";
			int i = 0;
			while (i < linha.size() - 1) {
				txt += String.format(format, linha.get(i).getId()) + SEPARADOR;
				i++;
			}
			txt += String.format(format, linha.get(i).getId());
		}
		txt += "\n" + FIM_MATRIZ_MAPA + "\n" + backdrop.getName();// add marcador de fim de mapa e backdrop
		try {
			BufferedWriter file = new BufferedWriter(new FileWriter(path));
			file.write(txt);
			file.close();
		} catch (IOException ex) {
			System.out.println("probremas em salvar o arquivo '" + path + "'");
			Logger.getLogger(Scene.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}
