package projetos.pacman;

import java.util.List;

public interface TecladoInteracao extends TecladoControle {

	/**
	 * Inicializa o teclado.
	 * 
	 * @param chavesDigito    As chaves dos botoes de digitos, null caso nao possua.
	 * @param chavesMovimento As chaves dos botoes de movimento, null caso n possua.
	 * @param controle        O controle das chaves.
	 */
	public void inicializa(List<Integer> chavesDigito, List<Integer> chavesMovimento, TecladoControle controle);

	/**
	 * Verifica se a tecla foi solicitada.
	 * 
	 * @param chave a tecla.
	 * @return true em caso de sucesso.
	 */
	public boolean isTecla(int chave);

}
