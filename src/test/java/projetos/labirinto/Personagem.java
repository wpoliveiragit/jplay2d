package projetos.labirinto;

import java.awt.Dimension;

import br.com.wellington.jplay2D.imageProcessing.TileInfo;

public class Personagem extends PersonagemBase implements Constantes {

	/**
	 * Construtor de um novo personagem.
	 */
	public Personagem(Cenario cenario, String pSprite, int tempo, int nFrames, int x, int y) {
		super(cenario, pSprite, tempo, nFrames, x, y);
		move(x, y, POSE_BAIXO, 0, 4);
		controleCetaBaixo();
	}

	@Override
	protected void move(int x, int y, int pose, int iniSprite, int fimSprite) {
		super.move(x, y, pose, iniSprite, fimSprite);

		// Ajusta o personagem para que n passe dos limites da janela.
		Dimension dimencao = cenario.getDimencao();
		if (this.x < 0 || (this.x + this.width) > dimencao.getWidth() || this.y < 0
				|| (this.y + this.height) > dimencao.getHeight()) {
			this.x -= x;
			this.y -= y;
		}

		for (TileInfo tile : cenario.getTilesFromRect(getPontoMin(), getPontoMax())) {
			if (collided(tile)) {// ocorreu uma coliz�o
				switch (tile.id) {// Identifica qual � o tile de colizao

				case TILE_ID_PAREDE: {
					// Volta o passo dado
					switch (pose) {
					case POSE_BAIXO:
						this.y -= PASSO;
						break;
					case POSE_CIMA:
						this.y += PASSO;
						break;
					case POSE_DIREITA:
						this.x -= PASSO;
						break;
					case POSE_ESQUERDA:
						this.x += PASSO;
						break;
					}
					break;
				}
				case TILE_ID_GRAMA_EVENTO: {
					cenario.finaliza();
				}

				}
			}

		}

	}

	@Override
	public void controleCetaBaixo() {// move o personagem pra baixo
		move(0, PASSO, POSE_BAIXO, 0, 4);
	}

	@Override
	public void controleCetaEsquerda() {// move o personagem pra esquerda
		move(-PASSO, 0, POSE_ESQUERDA, 4, 8);
	}

	@Override
	public void controleCetaDireita() { // move o personagem pra direita
		move(PASSO, 0, POSE_DIREITA, 8, 12);
	}

	@Override
	public void controleCetaCima() {// move o personagem para cima
		move(0, -PASSO, POSE_CIMA, 12, 16);
	}

}