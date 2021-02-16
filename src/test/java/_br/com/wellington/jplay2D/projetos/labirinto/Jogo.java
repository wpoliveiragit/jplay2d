package _br.com.wellington.jplay2D.projetos.labirinto;

/**
 * O jogo.
 * 
 * @author Wellington Pires de Oliveira
 * @data 02-11-2019
 */
public class Jogo extends JogoBase {

	/**
	 * O cenario corrente
	 */
	private Cenario cenario;

	/**
	 * Construtor do jogo.
	 */
	public Jogo() {
		super();
		cenario = new Cenario(this);
		controle = cenario;
	}

	/**
	 * Atualiza todo o cenario.
	 */
	@Override
	public void atualiza() {
		cenario.draw();
		super.atualiza();
	}

	public Cenario getCenario() {
		return cenario;
	}

}
