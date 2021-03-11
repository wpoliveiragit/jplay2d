package br.com.wellington.jplay2D.window;

public class WindowGameTime {
	private long currTime;
	private long lastTime;
	private long totalTime;

	public WindowGameTime() {
		currTime = System.currentTimeMillis();
		lastTime = 0;
		totalTime = 0;
	}

	/** Atualiza o tempo total de execução do aplicativo. */
	void update() {
		lastTime = currTime;
		currTime = System.currentTimeMillis();
		totalTime += currTime - lastTime;
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
	 * Retorna o tempo total de execução do aplicativo em milisegundos.
	 * 
	 * @return O tempo total de execução do aplicativo em milisegundos.
	 */
	public long getTotalTime() {
		return totalTime;
	}

}
