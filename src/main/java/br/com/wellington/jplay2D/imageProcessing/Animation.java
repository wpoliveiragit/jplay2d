package br.com.wellington.jplay2D.imageProcessing;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import br.com.wellington.jplay2D.window.Window;

/**
 * Classe responsável por animar uma GameImage usando pedaços da imagem, como
 * frames.
 */
public class Animation extends GameImage {
	private int initialFrame;
	private int currFrame;
	private int finalFrame;

	private int totalFrames;

	private boolean playing;
	private boolean loop;
	private boolean drawable;

	// Each frame has its own time
	private long frameDuration[];
	private long totalDuration;

	// It keeps the time when a frame was changed
	private long lastTime;

	/**
	 * O construtor cria uma animação de objeto de classe. A sequência é configurada
	 * para começar no quadro um e vai até o último quadro que é igual a
	 * totalFrames. Por exemplo: setSequence (0, totalFrames). 0 = quadro inicial.
	 * lastFrame = totalFrames.
	 * 
	 * @param fileName    Caminho do nome e da imagem.
	 * @param totalFrames Número de quadros que formam a imagem.
	 * @param It          diz se a animação é executada repetidamente. Se o valor
	 *                    for verdadeiro quando o último quadro for mostrado, o
	 *                    próximo quadro será o primeiro. No entanto, se o valor for
	 *                    falso quando o último quadro for mostrado, a animação
	 *                    permanecerá mostrando o último quadro indefinidamente.
	 */
	public Animation(String fileName, int totalFrames, boolean loop) {
		super(fileName);
		this.totalFrames = totalFrames;
		this.width = super.image.getWidth(null) / totalFrames;
		this.height = super.image.getHeight(null);
		this.lastTime = System.currentTimeMillis();
		this.playing = true;
		this.drawable = true;
		this.frameDuration = new long[totalFrames];
		setSequence(0, totalFrames, loop);
	}

	/**
	 * Sobrecarga do construtor public Animation (String fileName, int totalFrames,
	 * boolean loop). O parâmetro 'loop booleano' tem o valor true.
	 */
	public Animation(String fileName, int totalFrames) {
		this(fileName, totalFrames, true);
	}

	/**
	 * Sobrecarga do construtor public Animation (String fileName, int totalFrames,
	 * boolean loop). O parâmetro 'loop booleano' tem o valor true. O parâmetro 'int
	 * totalFrames' é igual a 1.
	 */
	public Animation(String fileName) {
		this(fileName, 1, true);
	}

	/**
	 * Configure a hora em que o quadro será mostrado na tela.
	 * 
	 * @param frame Número do quadro.
	 * @param time  Tempo em milissegundos em que o quadro será mostrado na tela.
	 */
	public void setDuration(int frame, long time) {
		frameDuration[frame] = time;
	}

	/**
	 * Retorne a hora em que o quadro é mostrado na tela.
	 * 
	 * @param frame número de quadros.
	 * @return long o tempo em milissegundos.
	 */
	public long getDuration(int frame) {
		return frameDuration[frame];
	}

	/**
	 * Defina o quadro inicial e final na sequência de animação. A sequência será
	 * executada indefinidamente.
	 * 
	 * @param initialFrame
	 * @param finalFrame
	 */
	public void setSequence(int initialFrame, int finalFrame) {
		setSequence(initialFrame, finalFrame, true);
	}

	/**
	 * Defina o quadro inicial e final na sequência de animação. E se a animação vai
	 * rodar indefinidamente.
	 * 
	 * @param initialFrame
	 * @param finalFrame
	 * @param loop
	 */
	public void setSequence(int initialFrame, int finalFrame, boolean loop) {
		setInitialFrame(initialFrame);
		setCurrFrame(initialFrame);
		setFinalFrame(finalFrame);
		setLoop(loop);
	}

	/**
	 * Defina o quadro inicial e final na sequência de animação e o tempo de
	 * execução.
	 * 
	 * @param initialFrame
	 * @param finalFrame
	 * @param time
	 */
	public void setSequenceTime(int initialFrame, int finalFrame, long time) {
		setSequenceTime(initialFrame, finalFrame, true, time);
	}

	/**
	 * Defina o quadro inicial e final na sequência de animação, o tempo de execução
	 * e se ela será executada indefinidamente.
	 * 
	 * @param initialFrame
	 * @param finalFrame
	 * @param time
	 * @param loop         True para indefinidamente, falso caso contrário.
	 */

	public void setSequenceTime(int initialFrame, int finalFrame, boolean loop, long time) {
		setSequence(initialFrame, finalFrame, loop);
		time = time / (finalFrame - initialFrame + 1);
		for (int i = initialFrame; i <= finalFrame; i++)
			this.frameDuration[i] = time;
	}

	/**
	 * Este método informa se a animação está em loop.
	 * 
	 * @return boolean
	 */
	public boolean isLooping() {
		return loop;
	}

	/**
	 * Define a hora para todos os quadros. Quando o tempo passa, a divisão entre
	 * totalDuration e totalFrames deixará algum resto: Exemplo: totalDuration = 100
	 * totalFrames = 11 timeFrame = 100/11 = 9 rest = 100 - 11 * 9 = 1
	 *
	 * So, the real totalDuration is (time / numberFrames) * numberFrames
	 * 
	 * @param time milissegundo.
	 */
	public void setTotalDuration(long time) {
		long timeFrame = time / totalFrames;
		totalDuration = timeFrame * totalFrames;
		for (int i = 0; i < frameDuration.length; i++)
			frameDuration[i] = timeFrame;
	}

	/**
	 * Retorna a soma de todos os intervalos de tempo.
	 * 
	 * @return long
	 */

	public long getTotalDuration() {
		return totalDuration;
	}

	/**
	 * Método responsável por realizar a troca de frames.
	 */
	public void update() {
		if (playing) {
			long time = System.currentTimeMillis();
			if (time - lastTime > frameDuration[currFrame] && finalFrame != 0) {
				currFrame++;
				lastTime = time;
			}

			if (currFrame == finalFrame && loop) {
				currFrame = initialFrame;
			} else if ((!loop) && (currFrame + 1 >= finalFrame)) {
				currFrame = finalFrame - 1;
				playing = false;
			}
		}
	}

	/**
	 * Para a execução e coloca o quadro inicial como o quadro atual.
	 */
	public void stop() {
		this.currFrame = initialFrame;
		this.playing = false;
	}

	/**
	 * Método responsável por iniciar a execução da animação.
	 */
	public void play() {
		this.playing = true;
	}

	/**
	 * Método responsável por pausar a animação.
	 */
	public void pause() {
		this.playing = false;
	}

	/**
	 * Define o quadro inicial da seqüência de quadros.
	 * 
	 * @param frame número de quadros.
	 */
	public void setInitialFrame(int frame) {
		this.initialFrame = frame;
	}

	/**
	 * Retorna o número do quadro inicial.
	 * 
	 * @return int
	 */
	public int getInitialFrame() {
		return initialFrame;
	}

	/**
	 * Define o quadro final da sequência de quadros.
	 * 
	 * @param frame número de quadros.
	 */
	public void setFinalFrame(int frame) {
		this.finalFrame = frame;
	}

	/**
	 * Retorna o número do quadro final da sequência de quadros.
	 * 
	 * @return int
	 */
	public int getFinalFrame() {
		return finalFrame;
	}

	/**
	 * Define o quadro atual que será desenhado.
	 * 
	 * @param frame número de quadros.
	 */
	public void setCurrFrame(int frame) {
		currFrame = frame;
	}

	/**
	 * Retorna o número do quadro atual.
	 * 
	 * @return int
	 */
	public int getCurrFrame() {
		return currFrame;
	}

	/**
	 * Returns verdadeiro se a animação estiver sendo executada, falso caso
	 * contrário.
	 * 
	 * @return boolean
	 */
	public boolean isPlaying() {
		return playing;
	}

	/**
	 * Este método é responsável por não permitir o desenho da animação na tela.
	 */
	public void hide() {
		this.drawable = false;
	}

	/**
	 * Método responsável por permitir que a animação seja desenhada na tela.
	 */
	public void unhide() {
		this.drawable = true;
	}

	/**
	 * Método responsável por informar a classe que a animação não será executada
	 * indefinidamente. Verdadeiro para executar indefinidamente, falso caso
	 * contrário.
	 */

	public void setLoop(boolean value) {
		this.loop = value;
	}

	/**
	 * Desenha a animação na tela.
	 */
	@Override
	public void draw() {
		if (drawable) {
			// Window.instance.getGameGraphics()
			// .drawImage(image, (int)x, (int)y, (int)x + width, (int)y + height,
			// currFrame * width, 0, (currFrame +1) * width, height, null);
			double rot = rotation;

			Graphics2D g2d = (Graphics2D) Window.getInstance().getGameGraphics();
			AffineTransform tx = new AffineTransform();

			tx.setToRotation(-rot, width / 2, height / 2);

			int newy = (int) (x * Math.sin(rot) + y * Math.cos(rot));
			int newx = (int) (x * Math.cos(rot) - y * Math.sin(rot));

			g2d.setTransform(tx);
			g2d.drawImage(image, newx, newy, newx + width, newy + height, currFrame * width, 0, (currFrame + 1) * width,
					height, null);
		}
	}

}
