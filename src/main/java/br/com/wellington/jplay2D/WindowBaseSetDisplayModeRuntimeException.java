package br.com.wellington.jplay2D;

public class WindowBaseSetDisplayModeRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public WindowBaseSetDisplayModeRuntimeException(int width, int height) {
		super("A resolucao (" + width + "x" + height + " nao e compatível com o display.");
	}

}
