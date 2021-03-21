package br.com.wellington.jplay2D.oi;

/** Controle de comportamento de chave. */
public class InputAction implements InputActionBehavior {

	/** Chave solta. */
	private static final int STATE_RELEASED = 0;
	/** chave pressionada. */
	private static final int STATE_PRESSED = 1;
	/** Chave mantida pressionada. */
	private static final int STATE_WAITING_FOR_RELEASE = 2;
	/** Comportamento da chave. */
	private int behavior;
	/** Quantidade de vezes que a chave foi precionada. */
	private int quantity;
	/** status da ação da chave. */
	private int state;

	/**
	 * Cria um novo controle de comportamento de chave.
	 * 
	 * @param behavior Comportamento da chave.
	 * @apiNote Use os comportamentos definidos em {@link InputActionBehavior}
	 */
	public InputAction(int behavior) {
		this.behavior = behavior;
		state = STATE_RELEASED;
		quantity = 0;
	}

	/**
	 * Defina o comportamento da chave.
	 * 
	 * @param behavior Comportamento da chave.
	 * @apiNote Use os comportamentos definidos em {@link InputActionBehavior}
	 */
	public synchronized void setBehavior(int behavior) {
		this.behavior = behavior;
	}

	/**
	 * Define o estado da chave como pressionada e a associa uma quantidade
	 * pressionada de 1.
	 */
	public synchronized void press() {
		press(1);
	}

	/**
	 * Define o estado da chave como pressionada e a associa uma quantidade
	 * pressionada.
	 * 
	 * @param amount Quantas vezes a tecla foi pressionada.
	 */
	public synchronized void press(int amount) {
		if (state != STATE_WAITING_FOR_RELEASE) {
			this.quantity += amount;
			state = STATE_PRESSED;
		}
	}

	/** Coloque o estado do botão ou da chave como solto. */
	public synchronized void release() {
		state = STATE_RELEASED;
	}

	/**
	 * retorna true caso a chave for pressionada.
	 * 
	 * @return boolean true caso a chave for pressionada.
	 */
	public synchronized boolean isPressed() {
		return (getAmount() != 0);
	}

	/**
	 * [ARRUMAR] - Retorna uma quantidade de cliques para o mouse e para o teclado a
	 * quantidade de vezes que o botão foi pressionado. Se o comportamento for
	 * DETECT_INITAL_PRESS_ONLY, este método retornará apenas o clique inicial. Para
	 * retornar que o mouse clicou novamente o usuário precisa liberar. Se o
	 * comportamento for DETECT_EVERY_PRESS, este método retornará a quantidade do
	 * clique ou de quantas vezes a tecla foi pressionada.
	 * 
	 * @return int - quantidade de pressionado.
	 */
	public synchronized int getAmount() {
		int quant = quantity;
		if (quant != 0) {
			if (state == STATE_RELEASED) {
				quantity = 0;
			} else if (behavior == ACTUATOR_REQUEST) {
				state = STATE_WAITING_FOR_RELEASE;
				quantity = 0;
			}
		}
		return quant;
	}
}
