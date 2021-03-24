package br.com.wellington.jplay2D.image;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import br.com.wellington.jplay2D.window.Window;

/**
 * Classe responsável por animar uma GameImage usando pedaços da imagem, como
 * frames.
 */
public class Animation extends GameImage {

	/** Ponto inicial da sequencia corrente dos quadros da animação. */
	private int initialFrame;
	/** Ponto atual da sequencia corrente dos quadros da animação. */
	private int currFrame;
	/** Ponto final da sequencia corrente dos quadros da animação. */
	private int finalFrame;
	/** Quantidade total de frames que a imagem possui. */
	private int totalFrames;
	/** Define se a imagem esta em execução. */
	private boolean playing;
	/**
	 * Indica se a sequencia de quadros esta em loop ou não. true: Quando o último
	 * quadro for apresentado, o próximo será o primeiro. false: Quando o último
	 * quadro for apresentado, a animação permanecerá mostrando o último quadro.
	 */
	private boolean loop;
	/** Define se a imagem estará visivel ou não. */
	private boolean visible;
	/** array de tempo de cada quadro. */
	private long frameDuration[];
	/** Duração total da animação */
	private long totalDuration;
	/** Define o momento em que o quadro foi alterado. */
	private long lastTime;

	/**
	 * Instancia uma animação. Configurado para executar todos os quadros.
	 * 
	 * @param fileName    Caminho absoluto do arquivo.
	 * @param totalFrames Número de quadros da animação.
	 * @param loop        Indica se a sequencia de quadros esta em loop ou não.
	 *                    true: Quando o último quadro for apresentado, o próximo
	 *                    será o primeiro. false: Quando o último quadro for
	 *                    apresentado, a animação permanecerá mostrando o último
	 *                    quadro.
	 */
	public Animation(String fileName, int totalFrames, boolean loop) {
		super(fileName);
		this.totalFrames = totalFrames;
		super.width = super.image.getWidth(null) / totalFrames;
		super.height = super.image.getHeight(null);
		lastTime = System.currentTimeMillis();
		playing = true;
		visible = true;
		frameDuration = new long[totalFrames];
		setSequence(0, totalFrames, loop);
	}

	/**
	 * Instancia uma animacão a deixando em loop (Quando o último quadro for
	 * apresentado, o próximo será o primeiro).
	 * 
	 * @param fileName    Caminho absoluto do arquivo.
	 * @param totalFrames Número de quadros da animação.
	 */
	public Animation(String fileName, int totalFrames) {
		this(fileName, totalFrames, true);
	}

	/**
	 * Sobrecarga do construtor public Animation (String fileName, int totalFrames,
	 * boolean loop). O parâmetro 'loop booleano' tem o valor true. O parâmetro 'int
	 * totalFrames' é igual a 1.
	 */

	/**
	 * Instancia uma animacão indicando que ela tem apenas uma imagem.
	 * 
	 * @param fileName Caminho absoluto do arquivo.
	 */
	public Animation(String fileName) {
		this(fileName, 1, true);
	}

	/**
	 * Define o tempo de duração de um frame em específico.
	 * 
	 * @param index O índice do frame.
	 * @param time  Tempo (em mili) de duração do frame.
	 */
	public void setFrameDuration(int index, long time) {
		frameDuration[index] = time;
	}

	/**
	 * Retorne o tempo de duração de um frame em específico.
	 * 
	 * @param index O índice do frame.
	 * @return long Tempo (em mili) de duração do frame.
	 */
	public long getFrameDuration(int index) {
		return frameDuration[index];
	}

	/**
	 * Defina a sequenciados de quadros desejados da animação a ser executado.
	 * 
	 * @param initFrame  Quadro inicial.
	 * @param finalFrame Quadro final.
	 * @param loop       Indica se a sequencia de quadros esta em loop ou não. true:
	 *                   Quando o último quadro for apresentado, o próximo será o
	 *                   primeiro. false: Quando o último quadro for apresentado, a
	 *                   animação permanecerá mostrando o último quadro.
	 */
	public void setSequence(int initFrame, int finalFrame, boolean loop) {
		this.initialFrame = initFrame;
		this.currFrame = initFrame;
		this.finalFrame = finalFrame;
		this.loop = loop;
	}

	/**
	 * Defina a sequenciados de quadros desejados da animação a ser executado (em
	 * loop).
	 * 
	 * @param initFrame  Quadro inicial.
	 * @param finalFrame Quadro final.
	 */
	public void setSequence(int initFrame, int finalFrame) {
		setSequence(initFrame, finalFrame, true);
	}

	/**
	 * Defina a sequenciados de quadros desejados da animação a ser executado.
	 * 
	 * @param initFrame  Quadro inicial.
	 * @param finalFrame Quadro final.
	 * @param time       tempo total da execução desta sequencia.
	 * @param loop       Indica se a sequencia de quadros esta em loop ou não. true:
	 *                   Quando o último quadro for apresentado, o próximo será o
	 *                   primeiro. false: Quando o último quadro for apresentado, a
	 *                   animação permanecerá mostrando o último quadro.
	 */
	public void setSequenceTime(int initFrame, int finalFrame, boolean loop, long time) {
		setSequence(initFrame, finalFrame, loop);
		time = time / (finalFrame - initFrame + 1);
		for (int i = initFrame; i <= finalFrame; i++) {
			this.frameDuration[i] = time;
		}
	}

	/**
	 * Defina a sequenciados de quadros desejados da animação a ser executado (em
	 * loop).
	 * 
	 * @param initFrame  Quadro inicial.
	 * @param finalFrame Quadro final.
	 * @param time       tempo total da execução desta sequencia.
	 */
	public void setSequenceTime(int initFrame, int finalFrame, long time) {
		setSequenceTime(initFrame, finalFrame, true, time);
	}

	/**
	 * Informa se a animação está em loop.
	 * 
	 * @return boolean true: esta em loop.
	 */
	public boolean isLooping() {
		return loop;
	}

	/**
	 * Define o tempo total da execução desta animação. Este tempo será dividido
	 * entre os quadros.
	 * 
	 * @param time O tempo (mili).
	 */
	public void setTotalDuration(long time) {
		long timeFrame = time / totalFrames;
		totalDuration = timeFrame * totalFrames;
		for (int i = 0; i < frameDuration.length; i++)
			frameDuration[i] = timeFrame;
	}

	/**
	 * Retorna o tempo total da animação.
	 * 
	 * @return long O tempo total da animação (mili).
	 */
	public long getTotalDuration() {
		return totalDuration;
	}

	/** Método responsável por realizar a troca de frames. */
	public void update() {
		if (playing) {// troca do quadro
			long time = System.currentTimeMillis();
			if (time - lastTime > frameDuration[currFrame] && finalFrame != 0) {
				currFrame++;
				lastTime = time;
			}
			if (loop && currFrame == finalFrame) {// loop
				currFrame = initialFrame;
			} else if ((!loop) && (currFrame + 1 >= finalFrame)) {
				currFrame = finalFrame - 1;
				playing = false;
			}
		}
	}

	/** Desabilita a troca de quadros. */
	public void play() {
		this.playing = true;
	}

	/** Desabilita a troca de quadros. */
	public void pause() {
		this.playing = false;
	}

	/** Desabilita a troca de quadros e volta o frame corrente para o inicial. */
	public void stop() {
		this.currFrame = initialFrame;
		this.playing = false;
	}

	/**
	 * Define o quadro inicial.
	 * 
	 * @param index O indice do quadros.
	 */
	public void setInitialFrame(int index) {
		this.initialFrame = index;
	}

	/**
	 * Retorna o indice do quadro inicial.
	 * 
	 * @return int O indice do quadro inicial.
	 */
	public int getInitialFrame() {
		return initialFrame;
	}

	/**
	 * Define o quadro final.
	 * 
	 * @param frame Indice do quadro.
	 */
	public void setFinalFrame(int frame) {
		this.finalFrame = frame;
	}

	/**
	 * Retorna indice do quadro final.
	 * 
	 * @return int Indice do quadro final.
	 */
	public int getFinalFrame() {
		return finalFrame;
	}

	/**
	 * Define o indice do quadra corrente.
	 * 
	 * @param frame Indice desejado do quadro corrente.
	 */
	public void setCurrFrame(int frame) {
		currFrame = frame;
	}

	/**
	 * Retorna indice do quadro corrente.
	 * 
	 * @return int Indice do quadro corrente.
	 */
	public int getCurrFrame() {
		return currFrame;
	}

	/**
	 * Returns informa se a animação esta em execução ou não.
	 * 
	 * @return boolean true: esta em execução.
	 */
	public boolean isPlaying() {
		return playing;
	}

	/**
	 * Define se a imagem esta visivel ou não.
	 * 
	 * @param visible true para visivel.
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	/**
	 * Indica se a sequencia de quadros esta em loop ou não.
	 * 
	 * @param loop true: Quando o último quadro for apresentado, o próximo será o
	 *             primeiro. false: Quando o último quadro for apresentado, a
	 *             animação permanecerá mostrando o último quadro.
	 */
	public void setLoop(boolean loop) {
		this.loop = loop;
	}

	/** Desenha a animação na tela. */
	@Override
	public void draw() {
		if (visible) {
			@SuppressWarnings("deprecation")
			Graphics2D g2d = (Graphics2D) Window.getInstance().getGameGraphics();
			AffineTransform tx = new AffineTransform();

			tx.setToRotation(-super.rotation, super.width / 2, super.height / 2);

			int newy = (int) (super.x * Math.sin(super.rotation) + super.y * Math.cos(super.rotation));
			int newx = (int) (super.x * Math.cos(super.rotation) - super.y * Math.sin(super.rotation));

			g2d.setTransform(tx);
			g2d.drawImage(super.image, newx, newy, newx + super.width, newy + super.height, currFrame * super.width, 0,
					(currFrame + 1) * super.width, super.height, null);
		}
	}

}
