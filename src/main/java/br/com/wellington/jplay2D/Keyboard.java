/*
 * The use this code commercially must only be done with permission of the author.
 * Any modification shall be advised and sent to the author.
 * The author is not responsible for any problem therefrom the use of this frameWork.
 *
 * @author Gefersom Cardoso Lima
 * Federal Fluminense University
 * Computer Science
 */

package br.com.wellington.jplay2D;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Hashtable;

import javax.swing.JFrame;

/**
 * Controlador dos eventos do teclado.
 * 
 * @see KeyEvent Use as constantes VK_ desta classe para definir o controle das
 *      chaves do teclado.
 */
public class Keyboard implements ConstantsIO {

	/** Guarda as teclas adicionadas. */
	protected Hashtable<Integer, InputAction> keysPressed;

	/** Controle de captura de teclas. */
	private KeyListener keyListener;

	/**
	 * Cria o controle de chaves do teclado do jogo. Ao ser criado, o teclado nao
	 * possuira nenhuma chave adicionada.
	 * 
	 * @param jframe o jframe que irá comportar este listener.
	 */
	public Keyboard(JFrame jframe) {
		keysPressed = new Hashtable<Integer, InputAction>();
		keyListener = new KeyListener() {

			@Override
			public final void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (keysPressed.containsKey(key)) {
					InputAction temp = (InputAction) keysPressed.get(key);
					temp.active();
				}
			}

			@Override
			public final void keyReleased(KeyEvent e) {
				int key = e.getKeyCode();
				if (keysPressed.containsKey(key)) {
					InputAction temp = (InputAction) keysPressed.get(key);
					temp.release();
				}
			}

			@Override
			public final void keyTyped(KeyEvent e) {// despresado
			}
		};
		jframe.addKeyListener(keyListener);

	}

	/**
	 * Verifica se uma chave foi solicitada.
	 * 
	 * @param key A chave a ser verificada.
	 * @return boolean true se a chave foi solicitada.
	 * @see KeyEvent Use as constantes VK_ desta classe para definir o controle das
	 *      chaves do teclado.
	 */
	public boolean conpareKey(int key) {// ok
		if (keysPressed.containsKey(key)) {
			InputAction temp = (InputAction) keysPressed.get(key);
			return temp.isActive();
		}
		return false;
	}

	/**
	 * Adiciona uma tecla com o comportamento "ao teclar" na lista.
	 * 
	 * @param key A chave a ser adicionada.
	 * @see KeyEvent Use as constantes VK_ desta classe para definir o controle das
	 *      chaves do teclado.
	 */
	public void addKey(int key) {// ok
		removeKey(key);
		keysPressed.put(key, new InputAction());
	}

	/**
	 * Adiciona uma chave com o comportamente "ao teclas e/ou manter pressionado" na
	 * lista.
	 * 
	 * @param key A chave a ser adicionada.
	 * 
	 * @see KeyEvent KeyEvent Use as constantes VK_ desta classe para definir o
	 *      controle das chaves do teclado.
	 */
	public void addKeyPress(int key) {//ok
		removeKey(key);
		InputAction ia = new InputAction();
		ia.setBehavior(BEHAVIOR_EVERY_PRESS);
		keysPressed.put(key, ia);
	}

	/**
	 * Remove uma chave do controle.
	 * 
	 * @param A chave a ser removida.
	 * @see KeyEvent KeyEvent Use as constantes VK_ desta classe para definir o
	 *      controle das chaves do teclado.
	 */
	public void removeKey(int key) {// ok
		keysPressed.remove(key);
	}

	/** Apaga toda as chavez adicionadas no teclado. */
	public void clean() {
		keysPressed.clear();
	}

}
