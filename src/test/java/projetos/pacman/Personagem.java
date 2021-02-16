package projetos.pacman;

import jplay.Scene;
import jplay.TileInfo;

import java.awt.Point;
import java.util.List;

/**
 * 
 * @author Wellington Pires de Oliveira.
 */
public abstract class Personagem extends ObjetoSprite {

	/**
	 * Pose corrente do personagem.
	 */
	protected int poseAtual;

	/**
	 * A base do cenario.
	 */
	protected Scene cena;
	/**
	 * A velocidade do personagem atual.
	 */
	protected double velocidadeAtual;

	/**
	 * CONSTRUTOR do personagem.
	 * 
	 * @param config As configuracoes bases do personagem.
	 * @param cena   A base do cenario.
	 */
	public Personagem(Scene cena, ConfiguracaoSprite config, double x, double y) {
		super(config);
		velocidadeAtual = TAMANHO_PASSO;
		move((TILE_LARGURA * x) - sprite.getWidth() / 2, (TILE_ALTURA * y) - sprite.getHeight() / 2);
		this.cena = cena;
		poseAtual = POSE_SEM_POSE;
	}

	/**
	 * Retorna dois pontos que representa o quadrante onde o personagem esta no
	 * mapa.
	 * <UL TYPE="disc">
	 * <LI>ponto[0] Guarda o ponto superior esquerdo do quadrante</LI>
	 * <LI>ponto[0] Guarda o ponto inferior direito do quadrante</LI>
	 * </UL>
	 * 
	 * 
	 * @return Dois ponto.
	 */
	protected List<TileInfo> getQuadrante() {
		return cena.getTilesFromPosition(//
				new Point((int) sprite.getX(), (int) sprite.getY()), // superior esquerdo
				new Point((int) (sprite.getX() + sprite.getWidth()), (int) (sprite.getY() + sprite.getHeight())));// inferior direito

	}

	/**
	 * Adiciona ao ponto atual do personagem o valores do parametro indicando um
	 * passo a direcao desejada.
	 * 
	 * @param x tamanho do passo no eixo x.
	 * @param y tamanho do passo no eixo y.
	 */
	protected void passo(double x, double y) {
		move(sprite.getX() + x, sprite.getY() + y);
		getQuadrante().forEach((tile) -> {
			if (tile.collided(sprite)) {
				switch (tile.getId()) {
				case TILE_PAREDE_ID:
					voltaPasso();
					return;
				}
			}
		});
	}

	/**
	 * Move o persoangem para esquerda.
	 */
	public void passoEsquerda() {
		if (poseAtual != POSE_ESQUERDA) {
			sprite.setGrab(config.getFramesEsquerda().x, config.getFramesEsquerda().y, true);
			poseAtual = POSE_ESQUERDA;
		}
		passo(-velocidadeAtual, 0);
	}

	/**
	 * Move o personagem para direita.
	 */
	public void passoDireita() {
		if (poseAtual != POSE_DIREITA) {
			sprite.setGrab(config.getFramesDireita().x, config.getFramesDireita().y, true);
			poseAtual = POSE_DIREITA;
		}
		passo(velocidadeAtual, 0);
	}

	/**
	 * Move o personagem para cima.
	 */
	public final void passoCima() {
		if (poseAtual != POSE_CIMA) {
			sprite.setGrab(config.getFramesCima().x, config.getFramesCima().y, true);
			poseAtual = POSE_CIMA;
		}
		passo(0, -velocidadeAtual);
	}

	/**
	 * Move o personagem para baixo.
	 */
	public final void passoBaixo() {
		if (poseAtual != POSE_BAIXO) {
			sprite.setGrab(config.getFramesBaixo().x, config.getFramesBaixo().y, true);
			poseAtual = POSE_BAIXO;
		}
		passo(0, velocidadeAtual);
	}

	/**
	 * Volta o passo pela posicao corrente.
	 */
	protected final void voltaPasso() {
		switch (poseAtual) {
		case POSE_DIREITA:
			passo(-velocidadeAtual, 0);
			break;
		case POSE_ESQUERDA:
			passo(velocidadeAtual, 0);
			break;
		case POSE_CIMA:
			passo(0, velocidadeAtual);
			break;
		case POSE_BAIXO:
			passo(0, -velocidadeAtual);
			break;
		}
	}

}
