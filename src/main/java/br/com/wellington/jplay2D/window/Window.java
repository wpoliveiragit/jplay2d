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
import javax.swing.JOptionPane;

import br.com.wellington.jplay2D.oi.Keyboard;
import br.com.wellington.jplay2D.oi.Mouse;

/** Classe inicial do framework. Responsavel pelo desenho de imagens na tela. */
public class Window {

	private JFrame jframe = new JFrame();
	private static Window instance = null;
	private Graphics graphics;
	private Mouse mouse;
	private Keyboard keyboard;
	private BufferStrategy buffer;	
	private DisplayMode displayMode;
	private GraphicsDevice device;
	
	private long currTime;
	private long lastTime;
	private long totalTime;

	/** Cria o controle do game. Não pode ser instanciado duas */
	public Window(int width, int height) {
		if (instance == null) {

			device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
			displayMode = new DisplayMode(width, height, 16, DisplayMode.REFRESH_RATE_UNKNOWN);

			mouse = new Mouse();
			keyboard = new Keyboard();

			jframe.addMouseListener(mouse);
			jframe.addMouseMotionListener(mouse);
			jframe.addKeyListener(keyboard);

			jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setSize(width, height);
			jframe.setLocationRelativeTo(null);
			jframe.setUndecorated(true);
			jframe.setVisible(true);

			jframe.createBufferStrategy(2);
			buffer = jframe.getBufferStrategy();

			graphics = buffer.getDrawGraphics();
			currTime = System.currentTimeMillis();
			lastTime = 0;
			totalTime = 0;
			instance = this;
			return;
		}
		throw new RuntimeException("[ERRO] ");
	}

	/** Atualiza o buffer da janela. */
	public void update() {
		graphics.dispose();
		buffer.show();
		Toolkit.getDefaultToolkit().sync();
		graphics = buffer.getDrawGraphics();

		updateTotalTime();
	}

	/** Atualiza o tempo total de execução do aplicativo. */
	private void updateTotalTime() {
		lastTime = currTime;
		currTime = System.currentTimeMillis();
		totalTime += currTime - lastTime;
	}

	/**
	 * Defina um modo de exibição.
	 * 
	 * @param displayMode
	 * @see DisplayMode
	 */
	public final void setDisplayMode(DisplayMode displayMode) {
		if (isDisplayModeCompatible(displayMode)) {
			this.displayMode = displayMode;
			return;
		}
		JOptionPane.showMessageDialog(null, "A resolução não é compatível com este monitor.");

	}

	/**
	 * Retorna verdadeiro se o display é capaz de funcionar com este modo de
	 * exibição, falso caso contrário.
	 * 
	 * @param displayMode
	 * @return boolean
	 * @see DisplayMode
	 */
	public final boolean isDisplayModeCompatible(DisplayMode displayMode) {
		DisplayMode goodModes[] = device.getDisplayModes();
		int i = 0;
		boolean compatible = false;
		while (!compatible && i < goodModes.length) {
			if (goodModes[i].getWidth() == displayMode.getWidth()
					&& goodModes[i].getHeight() == displayMode.getHeight())
				compatible = true;
			i++;
		}
		return compatible;
	}

	/** Abilita o modo de tela cheia. */
	public void setFullScreen() {
		DisplayMode old = device.getDisplayMode();
		jframe.setIgnoreRepaint(true);
		jframe.setResizable(false);
		device.setFullScreenWindow(instance.jframe);
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
	 * Define o tamanho da tela.
	 * 
	 * @param width  Largura da tela.
	 * @param height Altura da tela.
	 */
	public final void setSize(int width, int height) {
		jframe.setResizable(true);
		jframe.setSize(width, height);
		setDisplayMode(new DisplayMode(width, height, 16, DisplayMode.REFRESH_RATE_UNKNOWN));
		jframe.setLocationRelativeTo(null);
		jframe.setResizable(false);
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
	 * Retorna o tempo decorrido entre o quadro anterior e o atual.
	 * 
	 * @return long Tempo em milisegundos.
	 */
	public long latecy() {
		return currTime - lastTime;
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
		return instance;
	}

	/**
	 * Configura uma nova imagem para o mouse.
	 * 
	 * @param filePath O caminho do arquivo.
	 */
	public void setCursorImage(String filePath) {
		jframe.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(Toolkit.getDefaultToolkit().getImage(filePath),
				new java.awt.Point(), "cursor"));
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
	 * Retorna o tempo total de execução do aplicativo em milisegundos.
	 * 
	 * @return O tempo total de execução do aplicativo em milisegundos.
	 */
	public long getTotalTime() {
		return totalTime;
	}

}
