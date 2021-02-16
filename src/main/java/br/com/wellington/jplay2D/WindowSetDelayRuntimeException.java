package br.com.wellington.jplay2D;

public class WindowSetDelayRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public WindowSetDelayRuntimeException(long delay) {
		super("O valor de atualização da janela não pode ser negativo {" + delay + "}");
	}

}
