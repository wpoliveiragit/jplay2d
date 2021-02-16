package projetos.pacman;

public class Menu implements Constantes {

	/**
	 * tamanho de opcoes.
	 */
	private int tamanho;

	/**
	 * Posicao corrente do marcador.
	 */
	private int posicao;

	/**
	 * Atualizador do movimento da sprite.
	 */
	private AtualizarMenuDesenho atualizarSprite;

	/**
	 * CONSTRUTOR padao do menu que administra a posicao do ponto da escolha entre 1
	 * a 'tamanho'.
	 * 
	 * @param marcador        sprite do objeto de marcacao da opcao atual.
	 * @param atualizarSprite atualizacao do marcador para a opcao corrente.
	 * @param tamanho         quantidade de opcoes do menu.
	 */
	public Menu(int tamanho) {
		this.tamanho = tamanho;
		posicao = 0;
	}

	public final void setAtualizaSprite(AtualizarMenuDesenho atualizarSprite) {
		this.atualizarSprite = atualizarSprite;
	}

	/**
	 * Avanca uma posicao.
	 */
	public final void avanca() {
		if (++posicao > tamanho) {
			posicao = 1;
		}
		atualizarSprite.atualizaMenuDesenho(posicao);
	}

	/**
	 * Volta uma posicao.
	 */
	public final void volta() {
		if (--posicao < 1) {
			posicao = tamanho;
		}
		atualizarSprite.atualizaMenuDesenho(posicao);
	}

	/**
	 * Retorna a posicao atual da escolha.
	 * 
	 * @return A posicao atual da escolha.
	 */
	public final int getPosicao() {
		return posicao;
	}

}
