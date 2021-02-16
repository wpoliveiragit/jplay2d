package _br.com.wellington.jplay2D.projetos.labirinto;

public abstract interface Controle {

	/**
	 * Acao botao esc
	 */
	public void controleEsc();

	/**
	 * Acao botao ceta pra esquerda.
	 */
	public void controleCetaEsquerda();

	/**
	 * acao botao ceta para direita
	 */
	public void controleCetaDireita();

	/**
	 * Acao botao ceta pra cima.
	 */
	public void controleCetaCima();

	/**
	 * acao jbotao ceta pra baixo.
	 */
	public void controleCetaBaixo();

}
