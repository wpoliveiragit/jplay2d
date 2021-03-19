package br.com.wellington.jplay2D.oi;

/** Classe de controle do comportamento. */
public class InputAction implements InputActionBehavior {

	/** Estado liberada */
	private static final int STATE_RELEASED = 0;
	/** Estado pressionado */
	private static final int STATE_PRESSED = 1;
	/** Estado esperando liberação */
	private static final int STATE_WAITING_FOR_RELEASE = 2;

	/** O Comportamento */
	int behavior;

	/** */
	private int quantity;

	/** O estado da ação (ver a area estatica) */
	private int state;

	/**
	 * Cria um novo controle de comportamento com {@link #state} = liberado e
	 * {@link #quantity} = 0.
	 * 
	 * @param behavior O comportamento da chave.
	 * @apiNote → Use {@link InputActionBehavior} para encontrar a lista de
	 *          comportamentos.
	 */
	public InputAction(int behavior) {
		this.behavior = behavior;
		state = STATE_RELEASED;
		quantity = 0;
	}

	/**
	 * Troca o comportamento do botão do mouse.
	 * {@link InputActionBehavior#ACTUATOR_REQUEST} ↔
	 * {@link InputActionBehavior#ACTUATOR_REQUEST_PRESS}.
	 * 
	 * @param button O botão a ser alterado o comportamento.
	 */
	public synchronized void changeBehavior() {
		if (behavior == ACTUATOR_REQUEST) {
			behavior = ACTUATOR_REQUEST_PRESS;
			return;
		}
		behavior = ACTUATOR_REQUEST;
	}

	/** Coloqua o estado como {@link #STATE_PRESSED}. */
	public synchronized void press() {
		if (state == STATE_WAITING_FOR_RELEASE) {
			return;
		}
		quantity++;
		state = STATE_PRESSED;
	}

	/** Coloqua o estado como {@link #STATE_RELEASED}. */
	public synchronized void release() {
		state = STATE_RELEASED;
	}

	/**
	 * Retorna true caso a ação tenha sido solicitada.
	 * 
	 * @return boolean true caso a ação tenha sido colicitada.
	 */
	public synchronized boolean isPressed() {
		if (state == STATE_RELEASED) {
			return false;
		}
		return true;
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
		if (quantity == 0) {
			return quantity;
		}

		int qtt = quantity;

		if (state == STATE_RELEASED || behavior == ACTUATOR_REQUEST) {
			quantity = 0;
		}

		return qtt;
	}
}
