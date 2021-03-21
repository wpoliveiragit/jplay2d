package br.com.wellington.jplay2D.window;

import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import br.com.wellington.jplay2D.oi.Keyboard;
import br.com.wellington.jplay2D.oi.Mouse;

/** Classe inicial do framework. Responsavel pelo desenho de imagens na tela. */
public class Window {

	private static Window instance = null;

	private JFrame jframe = new JFrame();
	private Graphics graphics;
	private BufferStrategy buffer;
	private DisplayMode displayMode;
	private GraphicsDevice device;

	private WindowGameTime gameTime = new WindowGameTime();
	private Mouse mouse = new Mouse();
	private Keyboard keyboard = new Keyboard();

	/** Cria o controle do game. Não pode ser instanciado duas */
	private Window(int width, int height) {
		device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		setDisplayMode(new DisplayMode(width, height, 16, DisplayMode.REFRESH_RATE_UNKNOWN));
		instance = this;
	}

	/**
	 * Cria e retorma a instância da tela do jogo. Caso a instancia já exista, os
	 * parâmetros serão ignorados e a instancia existente será retornada.
	 * 
	 * @param width  A altura da tela.
	 * @param height O comprimento da tela
	 * @return A instância da tela.
	 */
	public static Window getInstance(int width, int height) {
		if (instance == null) {
			return instance = new Window(width, height);
		}
		return instance;
	}

	/**
	 * Retorna a instância da tela.
	 * 
	 * @return Window A instância da tela.
	 * @throws Caso a instancia não seja previamente criada peo metodo
	 *              'getInstance(int width, int height)'.
	 */
	public static Window getInstance() {
		if (instance == null) {
			throw new RuntimeException(
					"[ERRO] Não foi definido a altura e a largura da tela do jogo, use o método 'getInstance(int width, int height)' antes de usar o métodos 'getInstance()'");
		}
		return instance;
	}

	/**
	 * Configura um novo display ao jogo. Caso tenha guardado a instancia 'graphics'
	 * utilize o método 'getGameGraphics()' para atualizar a insrtância.
	 */
	public void setDisplayMode(DisplayMode dm) {
		JFrame jframe = new JFrame();
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		jframe.setResizable(true);
		jframe.setSize(dm.getWidth(), dm.getHeight());

		DisplayMode displayMode = new DisplayMode(dm.getWidth(), dm.getHeight(), 16, DisplayMode.REFRESH_RATE_UNKNOWN);
		if (isDisplayModeCompatible(displayMode)) {
			this.displayMode = displayMode;
		} else {
			throw new RuntimeException("A resolução não é compatível com este monitor.");
		}

		jframe.setLocationRelativeTo(null);
		jframe.setResizable(false);

		jframe.setLocationRelativeTo(null);
		jframe.setUndecorated(true);

		jframe.setVisible(true);

		jframe.createBufferStrategy(2);
		buffer = jframe.getBufferStrategy();

		graphics = buffer.getDrawGraphics();

		jframe.addMouseListener(mouse.getMouseListener());
		jframe.addMouseMotionListener(mouse.getMouseMotionListener());
		jframe.addKeyListener(keyboard.getKeyListener());

		this.jframe.dispose();
		this.jframe = jframe;
	}

	public void setMouse(Mouse mouse) {
		jframe.addMouseListener(mouse.getMouseListener());
		jframe.addMouseMotionListener(mouse.getMouseMotionListener());
	}

	public void setKeyboard(Keyboard keyboard) {
		jframe.addKeyListener(keyboard.getKeyListener());
	}

	/** Atualiza a tela do jogo. */
	public void update() {
		graphics.dispose();
		buffer.show();
		Toolkit.getDefaultToolkit().sync();
		graphics = buffer.getDrawGraphics();
		gameTime.update();
	}

	/**
	 * Retorna verdadeiro se o display é capaz de funcionar com este modo de
	 * exibição, falso caso contrário.
	 * 
	 * @param dm
	 * @return boolean
	 * @see DisplayMode
	 */
	public final boolean isDisplayModeCompatible(DisplayMode dm) {
		for (DisplayMode gmd : device.getDisplayModes()) {
			if (gmd.getWidth() == dm.getWidth() && gmd.getHeight() == dm.getHeight()) {
				return true;
			}
		}
		return false;
	}

	/** Abilita o modo de tela cheia. */
	public void setFullScreen() {
		DisplayMode old = device.getDisplayMode();
		jframe.setIgnoreRepaint(true);
		jframe.setResizable(false);
		device.setFullScreenWindow(jframe);
		try {
			device.setDisplayMode(displayMode);
		} catch (IllegalArgumentException ex) {
			device.setDisplayMode(old);
		}
	}

	/** Desabilita o modo tela cheia. */
	public void restoreScreen() {
		device.setFullScreenWindow(null);
		jframe.setLocationRelativeTo(null);
	}

	/**
	 * Controla o tempode execução do programa.
	 * 
	 * @param time Tempo em milisegundos.
	 */
	public void delay(long time) {
		try {
			Thread.sleep(time);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	/**
	 * Limpa a tela.
	 * 
	 * @param color A cor que irá sobrepor tudo.
	 */
	public void clear(Color color) {
		graphics.setColor(color);
		graphics.fillRect(0, 0, jframe.getWidth(), jframe.getHeight());
	}

	/** Encerra o programa. */
	public void exit() {
		jframe.dispose();
		System.exit(0);
	}

	/**
	 * Desenha uma mensagem na tela.
	 * 
	 * @param message a mensagem a ser desenhada na tela.
	 * @param x       Ponto do eixo x.
	 * @param y       Ponto do eixo y.
	 * @param color   A cor da mensagem.
	 */
	public void drawText(String message, int x, int y, Color color) {
		graphics.setColor(color);
		graphics.drawString(message, x, y);
	}

	/**
	 * Desenha uma mensagem na tela.
	 * 
	 * @param message a mensagem a ser desenhada na tela.
	 * @param x       Ponto do eixo x.
	 * @param y       Ponto do eixo y.
	 * @param color   A cor da mensagem.
	 * @param font    A fonte da mensagem.
	 */
	public void drawText(String message, int x, int y, Color color, Font font) {
		Graphics2D g2 = (Graphics2D) graphics;
		g2.setFont(font);
		g2.setColor(color);
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2.drawString(message, x, y);
	}

	// GETTERS /////////////////////////////////////////////////////////////////

	/**
	 * Retorna uma matriz com os modos de exibição validas.
	 * 
	 * @return DisplayMode[] Matriz com os modos de exibição validas.
	 * @see DisplayMode
	 */
	public DisplayMode[] getCompatibleDisplayMode() {
		return device.getDisplayModes();
	}

	/** Retorna o frame da tela. */
	public JFrame getJFrame() {
		return jframe;
	}

	/**
	 * Retorna a instância do teclado.
	 * 
	 * @return Keyboard A instancia do teclado.
	 */
	public Keyboard getKeyboard() {
		return keyboard;
	}

	/**
	 * Retorna a instância do teclado.
	 * 
	 * @return Mouse A instância do mouse.
	 */
	public Mouse getMouse() {
		return mouse;
	}

	/**
	 * Retorna a base do tempo do jogo.
	 * 
	 * @return A base do tempo do jogo.
	 */
	public WindowGameTime getGameTime() {
		return gameTime;
	}

	/**
	 * Retorna a instância de Graphics.
	 * 
	 * @return graphics A instância de Graphics.
	 * @deprecated Deve ser usado Apenas pelo framework.
	 */
	public Graphics getGameGraphics() {
		return graphics;
	}

}
