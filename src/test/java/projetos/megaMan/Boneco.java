package projetos.megaMan;

import br.com.wellington.jplay2D.imageProcessing.Sprite;
import br.com.wellington.jplay2D.window.Window;

public class Boneco {

	private Window quadro;

	private Sprite sprite;// Sprite do personagem
	private int ladoMovimento; // Lado em que o boneco esta se locomovendo
	private int lado;// Lado em que o boneco esta virado
	private int solo; // Altura do solo onde o personagem irá andar

	/**
	 * Cria o boneco do megaman.
	 * 
	 * @param quadro O quadro da janela.
	 * @param solo   a altura do solo.
	 */
	public Boneco(Window quadro, int solo) {
		this.quadro = quadro;
		this.solo = solo;

		sprite = new Sprite(MegaManMain.SPT_MEGAMAN, 28);
		sprite.setTotalDuration(1960);
		sprite.setFloor(solo);
		move(MegaManMain.LADO_DIREITO, 16, 17, 2, 0);
	}

	/**
	 * Move o personagem para um lado.
	 * 
	 * @param lado       O lado em que o personagem irá se mover.
	 * @param imgInicial Quadro inicialdo movimendo
	 * @param imgFinal   Quadro final do movimento
	 * @param x          Quantidade do deslocamento no pixels no eixo x.
	 * @param y          Quantidade do deslocamento no pixels no eixo y.
	 */
	private void move(int lado, int imgInicial, int imgFinal, int x, int y) {
		if (ladoMovimento != lado) {
			sprite.setSequence(imgInicial, imgFinal);
			this.lado = lado;
			ladoMovimento = lado;
		}
		sprite.x += x;
		sprite.y += y;
		sprite.update();
	}

	/** Captura de comandos da ação do boneco. */
	public void jump() {
		sprite.jump();
	}

	/** Faz o personagem atirar */
	public Tiro atira() {
		return new Tiro(quadro.getJFrame(), sprite, lado, solo);
	}

	/** Anda pra esquerda */
	public void andaEsquerda() {
		if (sprite.x > 1) {
			move(MegaManMain.LADO_ESQUERDO, 0, 13, -2, 0);
		}
	}

	/** Anda pra direita */
	public void andaDireita() {
		if (sprite.x + sprite.width < quadro.getJFrame().getWidth()) {
			move(MegaManMain.LADO_DIREITO, 14, 27, 2, 0);
		}
	}

	/** Mantem o personagem parado */
	public void ficaParado() {
		if (ladoMovimento == MegaManMain.LADO_PARADO) {
			return;
		}
		if (ladoMovimento == MegaManMain.LADO_DIREITO) {
			sprite.setCurrFrame(17);
		} else {
			sprite.setCurrFrame(3);
		}

		ladoMovimento = MegaManMain.LADO_PARADO;
		sprite.update();

	}

	/** Desenha no quadro as balas e o personagem. */
	public void draw() {
		sprite.draw();
	}

}
