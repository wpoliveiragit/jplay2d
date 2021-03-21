package br.com.wellington.jplay2D.oi;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Hashtable;

/** Controle de entrada de digito do terclado */
public final class Keyboard implements InputActionBehavior {

	/** Lista de chaves com seus respectivos comportamentos */
	private Hashtable<Integer, InputAction> KeyList;

	private KeyListener keyListener;

	/** Cria o controle de teclas do keyboard. */
	public Keyboard() {
		KeyList = new Hashtable<Integer, InputAction>();
		keyListener = new KeyListener() {

			public void keyTyped(KeyEvent e) {
				// suprimido.
			}

			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (KeyList.containsKey(key)) {
					InputAction temp = (InputAction) KeyList.get(key);
					temp.press();
				}
			}

			public void keyReleased(KeyEvent e) {
				int key = e.getKeyCode();
				if (KeyList.containsKey(key)) {

					InputAction temp = (InputAction) KeyList.get(key);
					temp.release();

				}
			}
		};
	}

	/**
	 * Verifica se a chave foi solicitada.
	 * 
	 * @param key O código da chave.
	 * 
	 * @return true se a tecla foi pressionada e pertence a lista de chaves.
	 * 
	 * @apiNote → Apenas as chaves adicionadas a lista de chaves do keyboard serão
	 *          verificados. *
	 * @apiNote → Use o método {@link #addKeyBehaviorActuatorRequest(int)} ou
	 *          {@link #addKeyBehaviorActuatorRequestPress(int)} para adicionar uma
	 *          nova chave ao keyboard. *
	 * @apiNote → Use as constantes VK_'key' em {@link KeyEvent} como padrão de
	 *          velores.
	 */
	public boolean checkKey(int key) {
		if (KeyList.containsKey(key)) {
			return ((InputAction) KeyList.get(key)).isPressed();
		}
		return false;
	}

	/**
	 * Adiciona uma nova chave ao keyboard com o comportamento
	 * {@link InputActionBehavior#ACTUATOR_REQUEST}
	 * 
	 * @param key O código da chave.
	 * @apiNote → Para adicionar uma nova chave ao keyboard use as constantes
	 *          VK_'key' de {@link KeyEvent}.
	 */
	public void addKeyBehaviorActuatorRequest(int key) {
		addKeyBehavior(key, ACTUATOR_REQUEST);
	}

	/**
	 * Adiciona uma nova chave ao keyboard com o comportamento
	 * {@link InputActionBehavior#ACTUATOR_REQUEST_PRESS}
	 * 
	 * @param key O código da chave.
	 * @apiNote → Para adicionar uma nova chave ao keyboard use as constantes
	 *          VK_'key' de {@link KeyEvent}.
	 */
	public void addKeyBehaviorActuatorRequestPress(int key) {
		addKeyBehavior(key, ACTUATOR_REQUEST_PRESS);
	}

	/**
	 * Adiciona uma nova chave ao keyboard com um comportamento.
	 * 
	 * @param key      O código da chave. (encontre uma chave na classe 'KeyEvent').
	 * @param behavior O comportamento da tecla (pode ser encontrado em 'InputBase'.
	 */

	/**
	 * Adiciona uma nova chave de comportamento ao keyboard.
	 * 
	 * @param key      O código da chave.
	 * @param behavior O comportamento da chave.
	 * @apiNote → Para adicionar uma nova chave ao keyboard use as constantes
	 *          VK_'key' de {@link KeyEvent}.
	 * @apiNote → Use {@link InputActionBehavior} para encontrar a lista de
	 *          comportamentos.
	 */
	private void addKeyBehavior(int key, int behavior) {
		removeKey(key);
		KeyList.put(key, new InputAction(behavior));
	}

	/**
	 * Remova uma chave de comportamento do teclado.
	 * 
	 * @pram key O código da chave.
	 * @apiNote → Use {@link InputActionBehavior} para encontrar a lista de
	 *          comportamentos.
	 */
	public void removeKey(int key) {
		KeyList.remove(key);
	}

	/** Remove todas as chaves de comportamentos registradas */
	public void cleanKeys() {
		KeyList.clear();
	}

	/** Retorna o KeyListener */
	public KeyListener getKeyListener() {
		return keyListener;
	}

}
