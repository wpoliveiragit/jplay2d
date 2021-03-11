package br.com.wellington.jplay2D.oi;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Hashtable;

/**
 * Classe responsável por manipular as teclas do teclado e seu comportamento.
 */
public final class Keyboard extends InputBase implements KeyListener {
	/** A tecla de direção acima do teclado. */
	public static final int UP_KEY = 38;

	/** A tecla de direção à esquerda do teclado. */
	public static final int LEFT_KEY = 37;

	/** A tecla de direção à direita do teclado. */
	public static final int RIGHT_KEY = 39;

	/** A tecla de direção para a parte inferior do teclado. */
	public static final int DOWN_KEY = 40;

	/** A tecla ESC do teclado. */
	public static final int ESCAPE_KEY = 27;

	/** A barra de espaço do teclado. */
	public static final int SPACE_KEY = 32;

	/** A tecla enter do teclado. */
	public static final int ENTER_KEY = 10;

	private Hashtable keysPressed;

	/**
	 * Crie uma instância da classe Keyboard com as seguintes teclas e seu
	 * comportamento: UP_KEY, LEFT_KEY, RIGHT_KEY, DOWN_KEY têm o comportamento
	 * DETECT_EVERY_PRESS, e as teclas ESCAPE_KEY, SPACE_KEY, ENTER_KEY têm o
	 * comportamento DETECT_INITIAL_PRESS_ONLY.
	 * 
	 * @version 1.0
	 */
	public Keyboard() {
		keysPressed = new Hashtable<Integer, InputAction>();

		// Adicione as chaves padrão (retirar todos os addKey)
		addKey(UP_KEY, Keyboard.DETECT_EVERY_PRESS);
		addKey(LEFT_KEY, Keyboard.DETECT_EVERY_PRESS);
		addKey(RIGHT_KEY, Keyboard.DETECT_EVERY_PRESS);
		addKey(DOWN_KEY, Keyboard.DETECT_EVERY_PRESS);
		addKey(ESCAPE_KEY, Keyboard.DETECT_INITIAL_PRESS_ONLY);
		addKey(SPACE_KEY, Keyboard.DETECT_INITIAL_PRESS_ONLY);
		addKey(ENTER_KEY, Keyboard.DETECT_INITIAL_PRESS_ONLY);
	}

	/**
	 * Método usado para saber se uma tecla foi pressionada.
	 * 
	 * @param key O código-chave é apresentado em 'Teclado'. ou em 'KeyEvent.'
	 * @return boolean - Verdadeiro quando pressionado, caso contrário, falso.
	 * @see KeyEvent
	 * @see Keyboard
	 * @version 1.0
	 */
	public boolean keyDown(int key) {
		if (keysPressed.containsKey(key)) {
			InputAction temp = (InputAction) keysPressed.get(key);
			return temp.isPressed();
		}
		return false;
	}

	/**
	 * Sobrecarregando o método void addKey (int key, int behavior), o comportamento
	 * será DETECT_INITAL_PRESS_ONLY.
	 * 
	 * @param key O código da chave. O código da chave pode ser encontrado em
	 *            'KeyEvent.'
	 * @see KeyEvent
	 * @version 1.0
	 */
	public void addKey(int key) {
		addKey(key, Keyboard.DETECT_INITIAL_PRESS_ONLY);
	}

	/**
	 * Método usado para adicionar uma tecla a uma instância do teclado e seu
	 * comportamento.
	 * 
	 * @param key      O código da chave. O código da chave pode ser encontrado em
	 *                 'KeyEvent.'
	 * @param behavior O comportamento da tecla pode ser encontrado em 'Teclado'. ou
	 *                 em 'Base de entrada.'.
	 * @see KeyEvent
	 * @see InputBase
	 * @version 1.0
	 */
	public void addKey(int key, int behavior) {
		removeKey(key);
		keysPressed.put(key, new InputAction(behavior));
	}

	/**
	 * Remova uma chave da instância do teclado. Se a chave não existe na instância
	 * do teclado não gere nenhum erro.
	 * 
	 * @pram key O código da chave. Ele pode ser encontrado em 'KeyEvent'. ou em
	 *       'Teclado'.
	 * @see KeyEvent
	 * @see Keyboard
	 * @version 1.0
	 */
	public void removeKey(int key) {
		keysPressed.remove(key);
	}

	/**
	 * Defina um novo comportamento para uma chave. Se a chave não existe na
	 * instância do teclado não gera nenhum erro.
	 * 
	 * @param key O código da chave. Ele pode ser encontrado em 'KeyEvent'. ou em
	 *            'Teclado'.
	 * @see KeyEvent
	 * @see Keyboard
	 * @version 1.0
	 */
	public void setBehavior(int key, int behavior) {
		if (keysPressed.containsKey(key))
			addKey(key, behavior);
	}

	/** Remove todas as keys registradas */
	public void cleanKeys() {
		keysPressed.clear();
	}

	@Deprecated
	public void keyTyped(KeyEvent e) {
		// e.consume();
	}

	@Deprecated
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (keysPressed.containsKey(key)) {
			InputAction temp = (InputAction) keysPressed.get(key);
			temp.press();
		}
		// e.consume();
	}

	@Deprecated
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (keysPressed.containsKey(key)) {
			InputAction temp = (InputAction) keysPressed.get(key);
			temp.release();
		}
		// e.consume();
	}

}
