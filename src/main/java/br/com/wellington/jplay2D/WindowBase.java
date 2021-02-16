package br.com.wellington.jplay2D;

import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class WindowBase {

	/** Controle do frame */
	protected JFrame jFrame;

	/** Controle de estado da janela */
	protected GraphicsDevice device;

	/** Guarda o Displaymodo corrente */
	protected DisplayMode displayMode;

	/** Controle do buffer do frame para a manipulacao do desenho grafico. */
	protected BufferStrategy buffer;

	/** Inicializa toda a area do frame do jogo */
	protected void init(int width, int height) {// ok
		device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		displayMode = new DisplayMode(width, height, 16, DisplayMode.REFRESH_RATE_UNKNOWN);
		jFrame = new JFrame();
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setLocationRelativeTo(null);
		jFrame.setUndecorated(true);
		jFrame.setVisible(true);
		setSize(width, height);
		jFrame.createBufferStrategy(2);
		buffer = jFrame.getBufferStrategy();
	}

	/** Coloca em modo janela cheia. */
	public void fullScreen() {// ok
		DisplayMode oldMode = device.getDisplayMode();
		jFrame.setIgnoreRepaint(true);
		jFrame.setResizable(false);
		device.setFullScreenWindow(jFrame);
		try {
			device.setDisplayMode(displayMode);
		} catch (IllegalArgumentException ex) {
			device.setDisplayMode(oldMode);
		}
	}

	/** Desativa o modo de janela cheia. */
	public void restoreScreen() {// ok
		device.setFullScreenWindow(null);
		jFrame.setLocationRelativeTo(null);
	}

	/** Finaliza o processo do frame e encerra o sistema. */
	public void exit() {// ok
		jFrame.dispose();
		System.exit(0);
	}

	/**
	 * Configura um novo tamanho pra janela.
	 * 
	 * @param width  Largura da janela.
	 * @param height Altura da janela.
	 */
	public void setSize(int width, int height) {// ok
		setDisplayMode(new DisplayMode(width, height, 16, DisplayMode.REFRESH_RATE_UNKNOWN));
		jFrame.setResizable(true);
		jFrame.setSize(width, height);
		jFrame.setLocationRelativeTo(null);
		jFrame.setResizable(false);
	}

	/**
	 * Configura um novo display.
	 * 
	 * @param mode O novo display
	 * @see DisplayMode
	 */
	public void setDisplayMode(DisplayMode mode) {// ok
		if (isDisplayModeCompatible(mode)) {
			displayMode = mode;
			return;
		}
		throw new WindowBaseSetDisplayModeRuntimeException(mode.getWidth(), mode.getHeight());
	}

	/**
	 * Verifica se o jogo � capaz de funcionar completamente com este modo de
	 * exibi��o.
	 * 
	 * @param mode O modo de exibicao a ser verificado.
	 * @return boolean true em caso de sucesso.
	 */
	public boolean isDisplayModeCompatible(DisplayMode mode) {// ok
		for (DisplayMode gm : device.getDisplayModes()) {
			if (gm.getWidth() == mode.getWidth() && gm.getHeight() == mode.getHeight()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Retorna todos os modos de displays que a janela pode trabalhar.
	 * 
	 * @return DisplayMode[] Todos os displays.
	 * @see DisplayMode
	 */
	public DisplayMode[] getCompatibleDisplayMode() {// testado em isDisplayModeCompatible(DisplayMode):boolean
		return device.getDisplayModes();
	}

	/**
	 * Retorna a largura da janela.
	 * 
	 * @return A largura da janela.
	 */
	public int getWidth() {
		return jFrame.getWidth();
	}

	/**
	 * Retorna a altura da janela.
	 * 
	 * @return A altura da janela.
	 */
	public int getHeight() {
		return jFrame.getHeight();
	}

}
