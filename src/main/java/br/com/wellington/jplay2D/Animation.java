package br.com.wellington.jplay2D;

import java.awt.geom.AffineTransform;

/**
 * Controle de troca de imagens de uma animação.
 * 
 * O loop da atualização vai do primeiro ao ultimo e volta para o primeiro.
 */
public class Animation extends GameImage implements DrawImage, UpdateImage {

	// Controle da animação ########################################################
	/** false, pausar o loop da animação */
	protected boolean loop;
	/** false, não faz o desenho da animação */
	protected boolean draw;
	/** false, a imagem da animação não será trocada */
	protected boolean update;
	/** Último momento em que o quadro foi atualizado */
	protected long lastUpdate;

	// Controle dos quadros ########################################################
	/** Primeiro quadro da sequencia corrente da animação */
	protected int first;
	/** Quadro corrente da animação */
	protected int current;
	/** Último quadro da sequencia corrente da animação */
	protected int last;
	/** Tempo de troca de cada frame */
	protected long frameDuration[];
	/**  */
	protected double rotation = 0;

	/**
	 * Cria uma animacão que será executada da primeira a última imagem e ela estará
	 * em loop.
	 * 
	 * @param name   O nome da imagem.
	 * @param frames Quantidade de imagens que a animação possui.
	 * @param loop   true para rodar indefinidamente (retorna ao inicio da sequencia
	 *               corrente assim que acabar o ciclo).
	 */
	public Animation(String name, int frames, boolean loop) {
		super(name);
		if (frames < 1) {
			throw new RuntimeException("Quantidade de quaImagem: " + name + " com frames {" + frames + "} frames");
		}
		update = true;
		draw = true;
		width = image.getWidth(null) / frames;
		height = image.getHeight(null);
		lastUpdate = System.currentTimeMillis();
		frameDuration = new long[frames];
		setGrab(0, frames, loop);
	}

	/** A atualiza a troca de imagem da animação. */
	@Override
	public void update() {// ok
		if (update) {
			long currentTime = System.currentTimeMillis();
			if (currentTime - lastUpdate > frameDuration[current]) {
				current++;
				lastUpdate = currentTime;
			}
			if (current == last && loop) {
				current = first;

			} else if ((!loop) && (current + 1 >= last)) {
				current = last - 1;
				update = false;
			}
		}
	}

	/** Desenha a animacao na janela. */
	@Override
	public void draw() {
		if (draw) {
			AffineTransform tx = new AffineTransform();
			tx.setToRotation(-rotation, width / 2, height / 2);
			int newy = (int) (x * Math.sin(rotation) + y * Math.cos(rotation));
			int newx = (int) (x * Math.cos(rotation) - y * Math.sin(rotation));
			Window.INSTANCE.getGameGraphics2D().setTransform(tx);
			Window.INSTANCE.getGameGraphics2D().drawImage(image, newx, newy, newx + width, newy + height,
					current * width, 0, (current + 1) * width, height, null);
		}
	}

	/**
	 * Define um padrao de tempo para uma sequencia de frames.
	 * 
	 * @param init Primeiro frame.
	 * @param end  Ultimo frame
	 * @param time O tempo em milisegundos
	 */
	private void setSequenceDuration(int init, int end, long time) {// ok
		end++;
		while (init < end) {
			frameDuration[init++] = time;
		}
	}

	/**
	 * Define o tempo total de duracao da animacao, esse tempo sera dividido entre
	 * os frames, sendo assim, cada frame de uma animacao de 4 poses e com 1000ms,
	 * tera 250ms.
	 * 
	 * @param time O tempo total em milisegundos.
	 */
	public void setTotalDuration(long time) {// ok
		time = time / frameDuration.length;
		setSequenceDuration(0, frameDuration.length - 1, time);
	}

	/**
	 * Configura a sequencia de quadros a ser executa e se ele será repetido
	 * indefinidamente.
	 * 
	 * @param first O indice do frame inicial.
	 * @param last  O indice do frame final.
	 */
	public void setGrab(int first, int last) {// ok
		if (first > last || first < 0 || last < 0) {
			throw new RuntimeException("Dados da animação '" + name + "' invalido f:" + first + " l:" + last);
		}
		this.first = first;
		this.current = first;
		this.last = last;
	}

	/**
	 * Configura a sequencia de frames a ser executa e se ele será repetido
	 * indefinidamente.
	 * 
	 * @param first O indice do frame inicial.
	 * @param last  O indice do frame final.
	 * @param loop  True para definir se a imagem esta em constante movimento.
	 */
	public void setGrab(int first, int last, boolean loop) {// ok
		if (first > last || first < 0 || last < 0) {
			throw new RuntimeException("Dados da animação '" + name + "' invalido f:" + first + " l:" + last);
		}
		this.first = first;
		this.current = first;
		this.last = last;
		this.loop = loop;
	}

	/**
	 * Configura a sequencia de quadros da anima��o a ser executada, podendo a
	 * deixar
	 * 
	 * @param init Quadro inicial.
	 * @param end  Frame final.
	 * @param time O tempo que esse intervalo vai levar para ser passado.
	 * @param loop True para definir se a imagem esta em constante movimento.
	 */
	public void setGrab(int init, int end, boolean loop, long time) {// ok
		setGrab(init, end, loop);
		setSequenceDuration(init, end, time / (end - init + 1));
	}

	/**
	 * Interrompe a execucao e coloca o frame inicial como corrente.
	 */
	public void stop() {
		current = first;
		update = false;
	}

	/** Inicia a execucao da animacao. */
	public void play() {
		update = true;
	}

	/** Apenas paraliza a imagem. */
	public void pause() {
		update = false;
	}

	/**
	 * Define o frame inicial corrente.
	 * 
	 * @param index O indice do frame.
	 */
	public void setInitialFrame(int index) {
		this.first = index;
	}

	/**
	 * Define o tempo quem de exposicao de um frame.
	 * 
	 * @param index Indice do frame.
	 * @param time  O tempo em milissegundos.
	 */
	public void setDuration(int index, long time) {
		frameDuration[index] = time;
	}

	/**
	 * Define se a imagem esta em constante movimento.
	 * 
	 * @param loop true para sim.
	 */
	public void setLoop(boolean loop) {
		this.loop = loop;
	}

	/**
	 * Oculpa ou expoe a imagem na janela. Por padrao esta configurada como exposta.
	 * 
	 * @param drawable true para expor a imagem.
	 */
	public void setUnhide(boolean drawable) {
		this.draw = drawable;
	}

	/**
	 * Configura o frame corrente..
	 * 
	 * @param index O indice do frame.
	 */
	public void setCurrentFrame(int index) {
		current = index;
	}

	/**
	 * Retorna o ultimo frame da animacao.
	 * 
	 * @return O ultimo frame da animacao.
	 */
	public int getFinalFrame() {
		return last;
	}

	/**
	 * Retorna o tempo de exposicao de um quadro.
	 * 
	 * @param index O indice do frame.
	 * @return O tempo de exposicao de um quadro em milisegundos
	 */
	public long getDuration(int index) {
		return frameDuration[index];
	}

	/**
	 * Retorna o numero corrente do frame.
	 * 
	 * @return O numero corrente do frame.
	 */
	public int getCurrFrame() {
		return current;
	}

	/**
	 * Retorna o frame inicial corrente.
	 * 
	 * @return O frame inicial corrente.
	 */
	public int getInitialFrame() {
		return first;
	}

	/**
	 * Retorna o tempo total da animacao.
	 * 
	 * @return O tempo em milisegundos.
	 */
	public long getTotalDuration() {
		int duration = 0;
		for (long t : frameDuration) {
			duration += t;
		}
		return duration;
	}

	/**
	 * Informa se a animacao esta sendo executada.
	 * 
	 * @return boolean true para sim.
	 */
	public boolean isPlaying() {
		return update;
	}

	/**
	 * Informa se a animacao esta em loop.
	 * 
	 * @return true caso a animassao esteja em loop.
	 */
	public boolean isLooping() {
		return loop;
	}

}
