package br.com.wellington.jplay2D.oi;

/**
 * Classe usada para manipular ações: soltar, pressionar ou manter pressionado
 * um botão ou tecla. Esta classe não está associada a uma tecla ou botão.
 * 
 * @see Keyboard and a Mouse.
 */
class InputAction {

	private static final int STATE_RELEASED = 0;
	private static final int STATE_PRESSED = 1;
	private static final int STATE_WAITING_FOR_RELEASE = 2;
	private int behavior;
	private int quantity;
	private int state;

	/**
	 * Crie uma nova Ação de entrada com comportamento específico, com o estado =
	 * liberado e a quantidade de cliques igual a 0.
	 * 
	 * @param behavior - pode ser DETECT_EVERY_PRESS ou DETECT_INITIAL_PRESS_ONLY
	 * @version 1.0
	 */
	public InputAction(int behavior) {
		this.behavior = behavior;
		state = STATE_RELEASED;
		quantity = 0;
	}

	/**
	 * Defina o comportamento da tecla ou do botão.
	 * 
	 * @param behavior - pode ser DETECT_EVERY_PRESS ou DETECT_INITIAL_PRESS_ONLY.
	 * @see InputActionBehavior
	 * @version 1.0
	 */
	public synchronized void setBehavior(int behavior) {
		this.behavior = behavior;
	}

	/**
	 * É um método sobrecarregado do método press (int amount). Seu parâmetro é
	 * quantidade = 1.
	 * 
	 * @see InputActionBehavior
	 * @version 1.0
	 */
	public synchronized void press() {
		press(1);
	}

	/**
	 * Coloque o estado do botão ou da tecla como pressionada e associe a eles uma
	 * quantidade de pressionada.
	 * 
	 * @param amout - Quantas vezes a tecla foi pressionada.
	 * @version 1.0
	 */
	public synchronized void press(int amount) {
		if (state != STATE_WAITING_FOR_RELEASE) {
			this.quantity += amount;
			state = STATE_PRESSED;
		}
	}

	/**
	 * Coloque o estado do botão ou da chave como solto.
	 * 
	 * @version 1.0
	 */
	public synchronized void release() {
		state = STATE_RELEASED;
	}

	/**
	 * Método usado para saber se uma tecla foi pressionada.
	 * 
	 * @return boolean - verdadeiro quando uma tecla é pressionada, falso caso
	 *         contrário.
	 * @version 1.0
	 */
	public synchronized boolean isPressed() {
		return (getAmount() != 0);
	}

	/**
	 * Retorna uma quantidade de cliques para o mouse e para o teclado a quantidade
	 * de vezes que o botão foi pressionado. Se o comportamento for
	 * DETECT_INITAL_PRESS_ONLY, este método retornará apenas o clique inicial. Para
	 * retornar que o mouse clicou novamente o usuário precisa liberar Se o
	 * comportamento for DETECT_EVERY_PRESS, este método retornará a quantidade do
	 * clique ou de quantas vezes a tecla foi pressionada.
	 * 
	 * @return int - quantidade de pressionado.
	 * @see void press(int amount);
	 * @version 1.0
	 */
	public synchronized int getAmount() {
		int quant = quantity;
		if (quant != 0) {
			if (state == STATE_RELEASED) {
				quantity = 0;
			} else if (behavior == InputActionBehavior.BEHAVIOR_KEY_PRESSED) {
				state = STATE_WAITING_FOR_RELEASE;
				quantity = 0;
			}
		}
		return quant;
	}
}
