package br.com.wellington.jplay2D;

/** Usado para manipular acoes de pressionar, soltar e manter pressionado. */
class InputAction implements ConstantsIO {

	/** Estado livre da acao. */
	protected static final int STATE_RELEASED = 0;
	/** Estado pressionado da acao. */
	protected static final int STATE_PRESSED = 1;
	/** aguardando a liberacao da acao. */
	protected static final int STATE_WAITING_FOR_RELEASE = 2;
	/** Comportamento da acao. */
	protected int behavior;
	/** Indica se a acao esta ativa (true para ativa) */
	protected boolean active;
	/**
	 * o estado corrente da acao (pode ser STATE_PRESSED, STATE_RELEASED ou
	 * STATE_WAITING_FOR_RELEASE.
	 */
	protected int state;

	/**
	 * Cria uma acao de comportamento BEHAVIOR_DETECT_INITIAL_PRESS_ONLY (apenas ao
	 * pressionar).
	 */
	public InputAction() {
		behavior = BEHAVIOR_INITIAL_PRESS_ONLY;
		state = STATE_RELEASED;
		active = false;
	}

	/**
	 * Configura o novo comportamento.
	 * 
	 * @param behavior O comportamento da acao.
	 * @see ConstantsIO
	 */
	public synchronized void setBehavior(int behavior) {
		this.behavior = behavior;
	}

	/** Ativa a acao. */
	public synchronized void active() {
		if (state == STATE_WAITING_FOR_RELEASE) {
			return;
		}
		state = STATE_PRESSED;
		active = true;
	}

	/**
	 * Deixa o estado da acao como liberada.
	 * 
	 * @version 1.0
	 */
	public synchronized void release() {
		state = STATE_RELEASED;
		active = false;
	}

	/** Verifica se a acao esta ativa. */
	public synchronized boolean isActive() {
		boolean active = this.active;
		if (active && behavior == BEHAVIOR_INITIAL_PRESS_ONLY) {// nao deve estar em acao
			state = STATE_WAITING_FOR_RELEASE;
			this.active = false;
		}
		return active;
	}

}
