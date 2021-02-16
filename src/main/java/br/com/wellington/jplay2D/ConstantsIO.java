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

/**
 * Contem as constantes to mouse e do teclado.
 */
public interface ConstantsIO {

	/* TECLADO E MOUSE */
	/** Define o comportamento como "ao pressionar" e/ou "manter precionadodo". */
	public static final int BEHAVIOR_EVERY_PRESS = 0;
	/** Define o comportamento como apenas "ao pressionar". */
	public static final int BEHAVIOR_INITIAL_PRESS_ONLY = 1;

	/* TECLADO ******************************************************************/

	// SETAS
	/** Seta pra esquerda */
	public static final int VK_LEFT = KeyEvent.VK_LEFT;
	/** Seta para direita. */
	public static final int VK_RIGHT = KeyEvent.VK_RIGHT;
	/** Seta para baixo. */
	public static final int VK_DOWN = KeyEvent.VK_DOWN;
	/** seta para cima. */
	public static final int VK_UP = KeyEvent.VK_UP;

	// TECLAS ESPECIAIS
	/** Tecla enter. */
	public static final int VK_ENTER = KeyEvent.VK_ENTER;
	/** tecla esc. */
	public static final int VK_ESCAPE = KeyEvent.VK_ESCAPE;
	/** Tecla '*'. */
	public static final int VK_MULTIPLY = KeyEvent.VK_MULTIPLY;
	/** Tecla de adicao '+'. */
	public static final int VK_ADD = KeyEvent.VK_ADD;
	/** Tecla de subtracao '-'. */
	public static final int VK_SUBTRACT = KeyEvent.VK_SUBTRACT;

	// DIGITOS
	/** tecla espaco. */
	public static final int VK_SPACE = KeyEvent.VK_SPACE;

	/** tecla. */
	public static final int VK_0 = KeyEvent.VK_0;

	/** tecla. */
	public static final int VK_1 = KeyEvent.VK_1;

	/** tecla. */
	public static final int VK_2 = KeyEvent.VK_2;

	/** tecla. */
	public static final int VK_3 = KeyEvent.VK_3;

	/** tecla. */
	public static final int VK_4 = KeyEvent.VK_4;

	/** tecla. */
	public static final int VK_5 = KeyEvent.VK_5;

	/** tecla. */
	public static final int VK_6 = KeyEvent.VK_6;

	/** tecla. */
	public static final int VK_7 = KeyEvent.VK_7;

	/** tecla. */
	public static final int VK_8 = KeyEvent.VK_8;

	/** tecla. */
	public static final int VK_9 = KeyEvent.VK_9;

}
