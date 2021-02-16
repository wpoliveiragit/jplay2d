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

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/** Controle de eventos do mouse. */
public class Mouse implements ConstantsIO {

	// #############################################################################
	/** Codigo do botao esquerdo do mouse */
	public static final int MOUSE_COD_BUTTON_LEFT = MouseEvent.BUTTON1;
	/** Codigo do botao central do mouse */
	public static final int MOUSE_COD_BUTTON_MIDDLE = MouseEvent.BUTTON2_DOWN_MASK;
	/** Codigo do botao direito do mouse */
	public static final int MOUSE_COD_BUTTON_RIGHT = MouseEvent.BUTTON3;
	// #############################################################################

	/** Ponto corrente do mouse na janela. */
	protected Point pnt;
	/** Acao do botao esquerdo. */
	protected InputAction leftButton;
	/** Acao do botao central. */
	protected InputAction middleButton;
	/** Acao do botao direito. */
	protected InputAction rightButton;
	/** Controle do evento de movimento. */
	protected MouseMotionListener mouseMotionListener;
	/** Controle de acoes do mouse */
	protected MouseListener mouseListener;

	/**
	 * Cria o controle de acoes do mouse onde, os o comportamento todos os 3 botoes
	 * sao de "apenas ao teclar" com as coordenadas (0,0).
	 */
	public Mouse() {
		pnt = new Point();
		leftButton = new InputAction();
		middleButton = new InputAction();
		rightButton = new InputAction();

		mouseMotionListener = new MouseMotionListener() {
			@Override
			public void mouseDragged(MouseEvent e) {
				pnt = e.getPoint();
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				pnt = e.getPoint();
			}
		};

		mouseListener = new MouseListener() {
			@Override
			public void mousePressed(MouseEvent e) {
				switch (e.getButton()) {
				case MouseEvent.BUTTON1:
					leftButton.active();
					break;
				case MouseEvent.BUTTON2:
					middleButton.active();
					break;
				case MouseEvent.BUTTON3:
					rightButton.active();
					break;
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				switch (e.getButton()) {
				case MouseEvent.BUTTON1:
					leftButton.release();
					break;
				case MouseEvent.BUTTON2:
					middleButton.release();
					break;
				case MouseEvent.BUTTON3:
					rightButton.release();
					break;
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {// despresado
			}

			@Override
			public void mouseExited(MouseEvent e) {// despresado
			}

			@Override
			public void mouseClicked(MouseEvent e) {// despresado
			}
		};
	}

	/**
	 * Define o comportamento do botao do mouse em expecifico como apenas "ao
	 * pressionar".
	 * 
	 * @param codBtn O codigo do botao do mouse que deseja definir o comportamento.
	 */
	public final void setButton(int codBtn) {
		switch (codBtn) {
		case MOUSE_COD_BUTTON_LEFT:
			leftButton.setBehavior(BEHAVIOR_INITIAL_PRESS_ONLY);
			break;
		case MOUSE_COD_BUTTON_MIDDLE:
			middleButton.setBehavior(BEHAVIOR_INITIAL_PRESS_ONLY);
			break;
		case MOUSE_COD_BUTTON_RIGHT:
			rightButton.setBehavior(BEHAVIOR_INITIAL_PRESS_ONLY);
			break;
		}
	}

	/**
	 * Define o comportamento do botao do mouse em expecifico como "ao pressionar" e
	 * "manter pressionado".
	 * 
	 * @param codBtn O codigo do botao do mouse que deseja definir o comportamento.
	 */
	public void setButtonPress(int codBtn) {
		switch (codBtn) {
		case MOUSE_COD_BUTTON_LEFT:
			leftButton.setBehavior(BEHAVIOR_EVERY_PRESS);
			break;
		case MOUSE_COD_BUTTON_MIDDLE:
			middleButton.setBehavior(BEHAVIOR_EVERY_PRESS);
			break;
		case MOUSE_COD_BUTTON_RIGHT:
			rightButton.setBehavior(BEHAVIOR_EVERY_PRESS);
			break;
		}
	}

	/**
	 * Verifica se o ponteiro esta sobre um objeto.
	 * 
	 * @param obj O objeto a ser verificado.
	 * @return boolean true em caso de sucesso.
	 */
	public boolean isOverObject(GameObject obj) {
		return isOverArea((int) obj.x, (int) obj.y, (int) (obj.x + obj.width), (int) (obj.y + obj.height));
	}

	/**
	 * Verifica se o ponteiro esta sobre a area.
	 * 
	 * @param p1X coordenada do eixo x do ponto superior esquerdo.
	 * @param p1Y coordenada do eixo y do ponto superior esquerdo.
	 * @param p2X coordenada do eixo x do ponto inferior direito.
	 * @param p2Y coordenada do eixo y do ponto inferior direito..
	 * 
	 * @return true em caso de sucesso.
	 */
	public boolean isOverArea(int p1X, int p1Y, int p2X, int p2Y) {
		return !((pnt.x < p1X) || (pnt.x > p2X) || (pnt.y < p1Y) || (pnt.y > p2Y));

		// return ((point.x < p1X) || (point.x > p2X) || (point.y < p1Y) || (point.y >
		// p2Y)) ? false : true;
	}

	// #############################################################################
	/**
	 * Retorna o controle de movimento.
	 * 
	 * @return O Controle de movimento.
	 */
	public final MouseMotionListener getMouseMotionListener() {
		return mouseMotionListener;
	}

	/**
	 * Retorna o controle de acoes.
	 * 
	 * @return O controle de acoes.
	 */
	public final MouseListener getMouseListener() {// ok
		return mouseListener;
	}

	/**
	 * Retorna o ponto corrente na janela..
	 * 
	 * @return Point O ponto corrente.
	 */
	public Point getPosition() {
		return pnt;
	}

	/**
	 * Verifica se o botao esquerdo foi solicitado.
	 * 
	 * @return boolean true em caso de sucesso.
	 */
	public boolean isLeftButtonPressed() {
		return leftButton.isActive();
	}

	/**
	 * Verifica se o botao central foi solicitado.
	 * 
	 * @return boolean true em caso de sucesso.
	 */
	public boolean isMiddleButtonPressed() {
		return middleButton.isActive();
	}

	/**
	 * Verifica se o botao direito foi solicitado.
	 * 
	 * @return boolean true em caso de sucesso.
	 */
	public boolean isRightButtonPressed() {
		return rightButton.isActive();
	}

}
