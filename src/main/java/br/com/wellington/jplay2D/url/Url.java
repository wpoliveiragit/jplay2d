package br.com.wellington.jplay2D.url;

import java.io.File;

/**
 * Controle de caminhos de arquivos.
 * 
 * @author Wellington Pires de Olieira
 */
public class Url {

	/**
	 * Retorna a url do arquivo.
	 * 
	 * @param name o nome do arquivo.
	 * @return A url do arquivo.
	 * @exception RuntimeException caso o caminho não exista.
	 */
	public static final String getURL(String name) {// ok
		String path = procuraArq(new File(new File("").getAbsolutePath()), name);
		if (path == null) {
			throw new RuntimeException("Arquivo nao encontrado: '" + name + "'");
		}
		return path;
	}

	/**
	 * Retorna a url do arquivo.
	 * 
	 * @param name o nome do arquivo.
	 * @return A url do arquivo.
	 * @exception RuntimeException caso o caminho não exista.
	 */
	public static final String getURL(String root, String name) {
		String path = procuraArq(new File(new File(root).getAbsolutePath()), name);
		if (path == null) {
			throw new RuntimeException("Arquivo nao encontrado: '" + name + "'");
		}
		return path;
	}

	/**
	 * Busca o arquivo desejado de modo recursivo.
	 * 
	 * @param path O caminho a ser verificado.
	 * @param arq  O nome do arquivo.
	 * @return true se e somente se encontrar o arquivo.
	 */
	private static String procuraArq(File path, String arq) {// ok
		if (path.isDirectory()) {
			String[] listaItens = path.list();
			String url;
			for (String item : listaItens) {
				url = procuraArq(new File(path, item), arq);
				if (url != null) {
					return url;
				}
			}
		}
		return path.getName().equalsIgnoreCase(arq) ? path.getAbsolutePath() : null;
	}

}
