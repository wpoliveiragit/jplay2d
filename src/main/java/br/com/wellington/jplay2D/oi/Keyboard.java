package br.com.wellington.jplay2D.oi;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Hashtable;

/** Controle de entrada de digito do terclado */
public final class Keyboard implements InputActionBehavior {

	/** Lista de chaves com seus respectivos comportamentos */
	private Hashtable<Integer, InputAction> KeyList;

	/** Cria o controle de teclas do keyboard. */
	public Keyboard() {
		KeyList = new Hashtable<Integer, InputAction>();
	}

	/**
	 * Verifica se a tecla foi pressionada e ou se ela esta sendo mantida em
	 * pressão.
	 * 
	 * Obs.: A chave deve ser adicionada na lista de chaves deste objeto antes da
	 * verificação usando os metodos 'addKeyPressed(int key)' ou 'addKeyHeldDown(int
	 * key)'.
	 * 
	 * @param key O código da chave. (encontre uma chave na classe 'KeyEvent').
	 * @see KeyEvent
	 * @return true se a tecla foi pressionada e pertence a lista de chaves.
	 */
	public boolean keyDown(int key) {
		if (KeyList.containsKey(key)) {
			InputAction temp = (InputAction) KeyList.get(key);
			return temp.isPressed();
		}
		return false;
	}

	/**
	 * Adiciona uma nova chave ao keyboard com o comportamento de apenas ao
	 * pressionar.
	 * 
	 * @param key O código da chave. (encontre uma chave na classe 'KeyEvent').
	 * @see KeyEvent
	 */
	public void addKeyPressed(int key) {
		addKeyBehavior(key, InputActionBehavior.BEHAVIOR_KEY_PRESSED);
	}

	/**
	 * Adiciona uma nova chave ao keyboard com o comportamento de ao pressionar e/ou
	 * manter pressionado.
	 * 
	 * @param key O código da chave. (encontre uma chave na classe 'KeyEvent').
	 * @see KeyEvent
	 */
	public void addKeyHeldDown(int key) {
		addKeyBehavior(key, InputActionBehavior.BEHAVIOR_KEY_HELD_DOWN);
	}

	/**
	 * Adiciona uma nova chave ao keyboard com um comportamento.
	 * 
	 * @param key      O código da chave. (encontre uma chave na classe 'KeyEvent').
	 * @param behavior O comportamento da tecla (pode ser encontrado em 'InputBase'.
	 * @see KeyEvent
	 * @see InputActionBehavior
	 */
	private void addKeyBehavior(int key, int behavior) {
		removeKey(key);
		KeyList.put(key, new InputAction(behavior));
	}

	/**
	 * Remova uma chave do teclado.
	 * 
	 * @pram key O código da chave. (encontre uma chave na classe 'KeyEvent').
	 * @see KeyEvent
	 */
	public void removeKey(int key) {
		KeyList.remove(key);
	}

	/** Remove todas as keys registradas */
	public void cleanKeys() {
		KeyList.clear();
	}

	public KeyListener getKeyListener() {
		return new KeyListener() {

			public void keyTyped(KeyEvent e) {
				// suprimido.
			}

			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (KeyList.containsKey(key)) {
					((InputAction) KeyList.get(key)).press();
				}
			}

			public void keyReleased(KeyEvent e) {
				int key = e.getKeyCode();
				if (KeyList.containsKey(key)) {
					((InputAction) KeyList.get(key)).release();
					;

				}
			}
		};
	}

}
