package projetos.labirinto;

import java.awt.Point;

import br.com.wellington.jplay2D.image.Sprite;

public abstract class PersonagemBase extends Sprite implements Controle {

	/**
	 * O cenario corrente em que o personagem esta.
	 */
	protected Cenario cenario;

	/**
	 * A pose corrente do personagem.
	 */
	protected int pose;

	/**
	 * Cria um novo personagem.
	 */
	public PersonagemBase(Cenario cenario, String pSprite, int tempo, int nFrames, int x, int y) {
		super(pSprite, nFrames);
		setTotalDuration(tempo);
		pose = -1;
		this.cenario = cenario;
		cenario.getCenario().addOverlay(this);
	}

	/**
	 * Movimenta o personagem e configura a sprite para a pose adequada.
	 * 
	 * @param x         O deslocamento no eixo do x.
	 * @param y         O deslocamento no eixo do y
	 * @param pose      a posicao desejada
	 * @param iniSprite Inicio da fatia da sprite desejada.
	 * @param fimSprite Fim da fatia da sprite desejada.
	 */
	protected void move(int x, int y, int pose, int iniSprite, int fimSprite) {
		this.x += x;
		this.y += y;
		if (this.pose != pose) {
			setSequence(iniSprite, fimSprite, true);
			this.pose = pose;
		}
		update();
	}

	/**
	 * Retorna o ponto superior esquerdo do local onde a imagem do personagem se
	 * encontra na janela.
	 * 
	 * @return O ponto superior esquerdo do local onde a imagem do personagem se
	 *         encontra na janela.
	 */
	public Point getPontoMin() {
		return new Point((int) this.x, (int) (this.y + this.height / 1.25));
	}

	/**
	 * Retorna o ponto inferior direito do local onde a imagem do personagem se
	 * encontra na janela.
	 * 
	 * @return O ponto inferior direito do local onde a imagem do personagem se
	 *         encontra na janela.
	 */
	public Point getPontoMax() {
		return new Point((int) (this.x + this.width), (int) (this.y + this.height));
	}

	@Override
	public void controleEsc() {
		// OMITIDO
	}

}