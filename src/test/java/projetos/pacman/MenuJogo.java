package projetos.pacman;

import static _mainPacMan.ConfiguracaoSprite.MOEDA_OURO;

import jplay.GameImage;

public abstract class MenuJogo extends Menu implements AtualizarMenuDesenho, Desenho, TecladoControle {

	/**
	 * Interacao do jogo com o menu.
	 */
	protected JogoInteracao jogoInteracao;

	/**
	 * Imagem dos menus do jogo.
	 */
	private GameImage imagemMenu;

	/**
	 * O icone da moeda no menu.
	 */
	protected ObjetoSprite iconeMenu;

	public MenuJogo(int tamanho, JogoInteracao jogoInteracao) {
		super(tamanho);
		this.jogoInteracao = jogoInteracao;
		jogoInteracao.getTeclado().inicializa(null, null, this);
		jogoInteracao.setDesenho(this);
		iconeMenu = new ObjetoSprite(MOEDA_OURO);
		imagemMenu = new GameImage(PATH_IMAGEM_JOGO_INICIAL);
		setAtualizaSprite(this);
		avanca();
	}

	/**
	 * 
	 * @param path
	 */
	protected void setMenuImagem(String path) {
		imagemMenu.loadImage(path);
	}

	@Override
	public void atualiza() {
		iconeMenu.atualiza();
	}

	@Override
	public void desenha() {
		imagemMenu.draw();
		iconeMenu.desenha();
	}

}
