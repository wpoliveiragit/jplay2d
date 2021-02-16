package _br.com.wellington.jplay2D.projetos.labirinto;

public class Cenario extends CenarioBase implements Controle, Constantes {

	/**
	 * O personagem de controle.
	 */
	private Personagem player;

	/**
	 * Objeto
	 */
	private ObjetoBase moeda;

	/**
	 * Controutor base
	 * 
	 * @param jogo O controle do jogo.
	 */
	public Cenario(Jogo jogo) {
		super(jogo, SCN_CENARIO01);
		player = new Personagem(this, SPRITE_PLAYER, 1500, 16, 303, 64);
		moeda = new ObjetoBase(this, SPRITE_MOEDA, 6, 1000, 23, 14);
	}

	@Override
	public void controleEsc() {
		jogo.controleEsc();
	}

	@Override
	public void controleCetaEsquerda() {
		player.controleCetaEsquerda();
	}

	@Override
	public void controleCetaDireita() {
		player.controleCetaDireita();
	}

	@Override
	public void controleCetaCima() {
		player.controleCetaCima();
	}

	@Override
	public void controleCetaBaixo() {
		player.controleCetaBaixo();
	}

	/**
	 * Solicita que o jogo desenhe o cenario.
	 */
	public void draw() {
		super.draw();
		moeda.update();
	}

}
