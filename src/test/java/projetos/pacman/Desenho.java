package projetos.pacman;

public interface Desenho {

	/**
	 * Desenha o objeto na janela.
	 */
	public void desenha();

	/**
	 * Atualiza a sprite do desenho, mas nao as desenha na janela.
	 */
	public void atualiza();
}
