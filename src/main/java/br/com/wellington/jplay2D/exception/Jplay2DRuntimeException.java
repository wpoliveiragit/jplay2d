package br.com.wellington.jplay2D.exception;

/**
 * 
 * @author w.b.oliveira
 *
 */
public class Jplay2DRuntimeException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	/**
	 * [VERIFICADO] Cria uma instâmciade um erro sistêmico.
	 * 
	 * @param msg A mensagem do erro.
	 */
	public Jplay2DRuntimeException(String msg) {
		super(msg);
	}

	/**
	 * [VERIFICADO] Cria uma instâmciade um erro sistêmico.
	 * 
	 * @param msg A mensagem do erro.
	 * @param ex  Erro que antecede este erro sistêmico.
	 */
	public Jplay2DRuntimeException(String msg, Throwable ex) {
		super(msg, ex);
	}

}
