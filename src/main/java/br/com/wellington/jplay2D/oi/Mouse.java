package br.com.wellington.jplay2D.oi;

import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import br.com.wellington.jplay2D.imageProcessing.GameObject;
import br.com.wellington.jplay2D.window.Window;

/** Classe responsável por controlar as ações do mouse. */
public class Mouse implements InputActionBehavior {

	private MouseMotionListener mouseMotionListener;
	private MouseListener mouseListener;

	/** Local do da tela do jogo onde o mouse se encontra. */
	private Point position;
	/** Botão esquerdo do mouse */
	private InputAction leftButton;
	/** Botão central do mouse */
	private InputAction middleButton;
	/** Botão direitodo mouse */
	private InputAction rightButton;

	/**
	 * Cria uma instância do mouse no ponto(0,0). Todos os botões estão configurados
	 * com o comportamento "apenas ao clicar".
	 */
	public Mouse() {
		position = new Point(0, 0);
		leftButton = new InputAction(ACTUATOR_REQUEST);
		middleButton = new InputAction(ACTUATOR_REQUEST);
		rightButton = new InputAction(ACTUATOR_REQUEST);

		mouseMotionListener = new MouseMotionListener() {
			@Override
			public void mouseDragged(MouseEvent e) {
				position = e.getPoint();
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				position = e.getPoint();
			}
		};

		mouseListener = new MouseListener() {
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
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				switch (e.getButton()) {
				case MouseEvent.BUTTON1:
					leftButton.press();
					break;
				case MouseEvent.BUTTON2:
					middleButton.press();
					break;
				case MouseEvent.BUTTON3:
					rightButton.press();
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
			}

		};
	}

	/**
	 * Define uma nova imagem ao cursor do mouse.
	 * 
	 * @param filePath O caminho do arquivo da nova imagem.
	 * @apiNoteSe Se o parâmetro {@link filePath} estiver em branco ("") o mouse
	 *            ficará sem imagem.
	 */
	public void setCursorImage(String filePath) {
		Window.getInstance().getJFrame().setCursor(Toolkit.getDefaultToolkit()
				.createCustomCursor(Toolkit.getDefaultToolkit().getImage(filePath), new Point(), "cursor"));
	}

	/**
	 * Retorna true se o mouse estiver sobre a área do objeto.
	 * 
	 * @param obj O objeto a ser verificado.
	 * @return boolean true se o mouse estiver sobre a área do objeto.
	 */
	public boolean isOverObject(GameObject obj) {
		return isOverArea(new Point((int) obj.x, (int) obj.y),
				new Point((int) (obj.x + obj.width), (int) (obj.y + obj.height)));
	}

	/**
	 * Retorna true se o mouse estiver sobre a área do quadrante.
	 * 
	 * @param upperLeftPoint  ponto inicial da área.
	 * @param lowerRightPoint ponto final da área.
	 * @return boolean true se o mouse estiver sobre a área do objeto.
	 */
	public boolean isOverArea(Point upperLeftPoint, Point lowerRightPoint) {
		// ifs criado dessa maneira para melhor visualização
		if (position.x > upperLeftPoint.x && position.x < lowerRightPoint.x) {// entre o eixo x
			if (position.y > upperLeftPoint.y && position.y < lowerRightPoint.y) {// entre o eixo y
				return true;
			}
		}
		return false;
	}

	// SAMPLE GETTERS ////////////////////////////////////////////////
	/** Obtem a instancia MotionListener. */
	public MouseMotionListener getMouseMotionListener() {
		return mouseMotionListener;
	}

	/** Obtem a instancia MouseListener. */
	public MouseListener getMouseListener() {
		return mouseListener;
	}

	/** Retorna a posição do mouse na tela do jogo. */
	public Point getPosition() {
		return position;
	}

	/** Retorna o botão esquerdo do mouse. */
	public InputAction getLeftButton() {
		return leftButton;
	}

	/** Retorna o botão central do mouse. */
	public InputAction getMiddleButton() {
		return middleButton;
	}

	/** Retorna o botão direito do mouse. */
	public InputAction getRightButton() {
		return rightButton;
	}

}
