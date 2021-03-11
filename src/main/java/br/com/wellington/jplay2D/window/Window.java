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

	private JFrame jframe = new JFrame();;
	private Graphics graphics;
	private Mouse mouse = new Mouse();
	private Keyboard keyboard = new Keyboard();
	private BufferStrategy buffer;
	private DisplayMode displayMode;
	private GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	private WindowGameTime gameTime = new WindowGameTime();

	/** Cria o controle do game. Não pode ser instanciado duas */
	private Window(int width, int height) {
		instance = this;
		setDisplayMode(new DisplayMode(width, height, 16, DisplayMode.REFRESH_RATE_UNKNOWN));
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
		this.jframe.dispose();
		jframe.setVisible(true);

		jframe.createBufferStrategy(2);
		buffer = jframe.getBufferStrategy();

		graphics = buffer.getDrawGraphics();

		jframe.addMouseListener(mouse);
		jframe.addMouseMotionListener(mouse);
		jframe.addKeyListener(keyboard);

		this.jframe = jframe;
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
	 * Retorna a instância de Graphics.
	 * 
	 * @return graphics A instância de Graphics.
	 */
	public Graphics getGameGraphics() {
		return graphics;
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

	/**
	 * Limpa a tela.
	 * 
	 * @param color A cor que irá sobrepor tudo.
	 */
	public void clear(Color color) {
		graphics.setColor(color);
		graphics.fillRect(0, 0, jframe.getWidth(), jframe.getHeight());
	}

	/**
	 * Retorna uma matriz com os modos de exibição validas.
	 * 
	 * @return DisplayMode[] Matriz com os modos de exibição validas.
	 * @see DisplayMode
	 */
	public DisplayMode[] getCompatibleDisplayMode() {
		return device.getDisplayModes();
	}

	/** Encerra o programa. */
	public void exit() {
		jframe.dispose();
		System.exit(0);
	}

	/**
	 * Retorna a instância da tela.
	 * 
	 * @return Window A instância da tela.
	 */
	public static Window getInstance() {
		if (instance == null) {
			throw new RuntimeException("[ERRO] a classe window não foi criada, use o método 'create'");
		}
		return instance;
	}

	public static Window create(int x, int y) {
		if (instance == null) {
			return instance = new Window(x, y);
		}
		throw new RuntimeException("[ERRO] A instância da window já foi criada");
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

}
