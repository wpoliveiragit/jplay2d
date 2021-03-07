package br.com.wellington.jplay2D.oi;

/**
 * Classe usada para manipular ações para botões ou teclas.
 */
public class InputBase {
	/**
	 * Este comportamento é responsável por detectar o pressionamento do botão ou da
	 * tecla durante o pressionamento.
	 * 
	 * 1ͦ : DETECT_EVERY_PRESS - o método keyDown presente no teclado retornará true
	 * enquanto a tecla estiver pressionada.
	 * 
	 */
	public static final int DETECT_EVERY_PRESS = 0;

	/**
	 * Comportamento responsável por permitir apenas a detecção do primeiro toque do
	 * botão ou do clique, após isso, é necessário soltar o botão ou a tecla para a
	 * próxima detecção.
	 */
	public static final int DETECT_INITIAL_PRESS_ONLY = 1;

}
