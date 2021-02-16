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

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;

import jplay.url.Url;

/**
 * Esta é a classe principal do framework jplay, só pode ser instanciada uma vez
 * por execução da aplicação. Ela controla todo o tratamento de imagem da tela.
 */
public class Window extends WindowBase implements UpdateImage {

	/** Instancia corrente do objeto Window. */
	protected static Window INSTANCE = null;

	/** Tempo de atrado do sistema */
	protected long delay;

	/** Controle do mouse da janela */
	protected Mouse mouse;

	/** Controle do teclado da janela */
	protected Keyboard keyboard;

	/** proximo momento de atualizacao em milisegundos */
	protected long currTime;

	/** Ultimo momento de atualizacao em milisegundos */
	protected long lastTime;

	/** Tempo total em que o jogo esta ativo em misisegundos */
	protected long totalTime;

	/** Controle grafico da janela. */
	protected Graphics graphics;

	/**
	 * Cria a janela de execucao do jogo. Esta classe só pode ser instanciada apenas
	 * uma vez pro projeto, pois ela comandará dota a area gráfia do aplicativo.
	 * 
	 * Obs.: Antes de usar qualquer classe do pacote jplay, essa classe tem que ser
	 * instanciada.
	 * 
	 * @param width  A largura da janela.
	 * @param height A altura da janela.
	 */
	public Window(int width, int height) {// ok
		if (INSTANCE == null) {
			init(width, height);
		}
	}

	@Override
	protected void init(int width, int height) {// ok
		super.init(width, height);
		graphics = buffer.getDrawGraphics();
		mouse = new Mouse();
		jFrame.addMouseListener(mouse.getMouseListener());
		jFrame.addMouseMotionListener(mouse.getMouseMotionListener());
		keyboard = new Keyboard(jFrame);
		currTime = System.currentTimeMillis();
		lastTime = 0;
		totalTime = 0;
		delay = 0;
		INSTANCE = this;
	}

	/** Atualiza a janela com a nova informacao do desenho no buffer. */
	@Override
	public void update() {// ok
		graphics.dispose();
		buffer.show();
		Toolkit.getDefaultToolkit().sync();
		graphics = buffer.getDrawGraphics();
		lastTime = currTime;
		currTime = System.currentTimeMillis();
		totalTime += currTime - lastTime;
	}

	/**
	 * Tempo de atraso de execucao. Este método tem um desempenho melhor sendo
	 * invocado após a invocação do método update()
	 */
	public void delay() {// ok
		try {
			Thread.sleep(delay);
		} catch (InterruptedException ex) {
			throw new RuntimeException("Erro de atualização da janela: " + ex.getMessage());
		}
	}

	/**
	 * Escreve um texto na janela na cor preta.
	 * 
	 * @param txt O texto.
	 * @param x   O valor do ponto no eixo x.
	 * @param y   O valor do ponto no eixo y.
	 */
	public void write(int x, int y, String txt) {// ok
		write(x, y, Color.black, txt);
	}

	/**
	 * Escreve um texto na janela.
	 * 
	 * @param txt   A mensagem a ser escrita.
	 * @param x     Valor do eixo x.
	 * @param y     Valor do eixo y.
	 * @param color A cor da letra.
	 */
	public void write(int x, int y, Color color, String txt) {// ok
		graphics.setColor(color);
		graphics.drawString(txt, x, y);
	}

	/**
	 * Escreve um texto na janela.
	 * 
	 * @param txt   A mensagem a ser escrita.
	 * @param x     Valor do eixo x.
	 * @param y     Valor do eixo y.
	 * @param color A cor da letra.
	 * @param font  A fonte da letra.
	 */
	public void write(int x, int y, Color color, Font font, String txt) {// ok
		Graphics2D g2 = (Graphics2D) graphics;
		g2.setFont(font);
		g2.setColor(color);
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2.drawString(txt, x, y);
	}

	/**
	 * Altera a imagem do cursor do mouse.
	 * 
	 * @param name O caminho da imagem.
	 * @return Cursor O cursor com a alteracao.
	 */
	public Cursor changeCursosImage(String name) {// ok
		Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(
				Toolkit.getDefaultToolkit().getImage(Url.getURL(name)), new java.awt.Point(), "cursor");
		jFrame.setCursor(cursor);
		return cursor;
	}

	/**
	 * Pinta toda a janela com a cor definida.
	 * 
	 * @param color a cor.
	 */
	public void clear(Color color) {// ok
		graphics.setColor(color);
		graphics.fillRect(0, 0, jFrame.getWidth(), jFrame.getHeight());
	}

	/**
	 * Configura um novo tempo de atualização da janela.
	 * 
	 * @param delay O tempo em milisegundos (este valor não pode ser negativo).
	 */
	public void setDelay(long delay) {// ok
		if (delay < 0) {
			throw new WindowSetDelayRuntimeException(delay);
		}
		this.delay = delay;
	}

	/**
	 * Retorna a instancia do jogo.
	 * 
	 * @return Window null se nao estiver inicializado.
	 */
	public static Window getInstance() {// ok
		return INSTANCE;
	}

	/**
	 * Retorna a diferenca de tempo entre as duas ultimas atualizacoes.
	 * 
	 * @return long o tempo em milisegundos.
	 */
	public long getDeltaTime() {
		return currTime - lastTime;
	}

	/**
	 * Retorna o tempo total desde a criacao do frame.
	 * 
	 * @return long em milisegundos.
	 */
	public long getRunTimeAplication() {
		return totalTime;
	}

	/** Retorna a instancia da area grafica do frame. */
	public Graphics2D getGameGraphics2D() {
		return (Graphics2D) graphics;
	}

	/** Retorna a instancia do teclado. */
	public Keyboard getKeyboard() {
		return keyboard;
	}

	/** Retorna a instancia do mouse. */
	public Mouse getMouse() {
		return mouse;
	}

}
