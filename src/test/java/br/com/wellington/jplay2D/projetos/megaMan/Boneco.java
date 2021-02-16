package br.com.wellington.jplay2D.projetos.megaMan;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import br.com.wellington.jplay2D.Keyboard;
import br.com.wellington.jplay2D.Sound;
import br.com.wellington.jplay2D.Sprite;
import br.com.wellington.jplay2D.Window;
import br.com.wellington.jplay2D.utils.Constantes;

public class Boneco {

	private static final int SENTIDO_PARADO = 0;

	// ## CONTROLE DO QUADRO #######################################################
	private Window quadro;
	private Keyboard teclado;
	private List<Tiro> balas = new ArrayList<Tiro>();

	/** Sprite do personagem */
	private Sprite sprite;
	/** Lado em que o boneco esta se locomovendo */
	private int ladoMovimento;
	/** Lado em que o boneco esta virado */
	private int lado;
	/** Altura do solo onde o personagem irá andar */
	private int solo;

	/**
	 * Cria o boneco do megaman.
	 * 
	 * @param quadro O quadro da janela.
	 * @param solo   a altura do solo.
	 */
	public Boneco(Window quadro, int solo) {
		this.solo = solo;
		this.quadro = quadro;
		teclado = quadro.getKeyboard();
		sprite = new Sprite(Utils.MEGAMAN_SPRITE_MEGAMAN, 28);
		sprite.setTotalDuration(1960);
		sprite.setFloor(solo);
		move(Keyboard.RIGHT_KEY, 16, 17, 2, 0);
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
	public void comando() {
		sprite.jump();

		// atualiza os tiros
		for (int i = 0; i < balas.size(); i++) {
			Tiro tiro = balas.get(i);
			if (tiro.isOnFloor() || tiro.x < 1 || tiro.x + tiro.width > quadro.getWidth()) {
				balas.remove(i--);
				new Sound(Utils.MEGAMAN_SOM_EXPLOSAO).play();
				continue;
			}
			tiro.fall();
			tiro.move();
			tiro.update();
		}

		// atirar
		if (teclado.keyDown(KeyEvent.VK_CONTROL)) {
			balas.add(new Tiro());
			new Sound(Utils.MEGAMAN_SOM_MUZZLESHOT).play();
		}
		// vai pra esquerda
		if (teclado.keyDown(Keyboard.LEFT_KEY) && sprite.x > 1) {
			move(Keyboard.LEFT_KEY, 0, 13, -2, 0);
			return;
		}
		// vai pra direita
		if (teclado.keyDown(Keyboard.RIGHT_KEY) && sprite.x + sprite.width < quadro.getWidth()) {
			move(Keyboard.RIGHT_KEY, 14, 27, 2, 0);
			return;
		}
		// esta parado
		if (ladoMovimento != SENTIDO_PARADO) {
			if (ladoMovimento == Keyboard.RIGHT_KEY) {
				sprite.setCurrFrame(17);
			} else {
				sprite.setCurrFrame(3);
			}
			ladoMovimento = SENTIDO_PARADO;
			sprite.update();
		}
	}

	/** Desenha no quadro as balas e o personagem. */
	public void draw() {
		sprite.draw();
		for (Tiro t : balas) {
			t.draw();
			t.update();
		}
	}

	private class Tiro extends Sprite {

		private int passo = -6;// true = direita

		/** Cria um objeto de tiro. */
		public Tiro() {
			super(Utils.MEGAMAN_SPRITE_TIRO, 7);
			setTotalDuration(200);
			setGravity(0.0098);
			setFloor(solo);
			y = sprite.y + (sprite.height / 3);
			x = sprite.x;
			if (lado == Keyboard.RIGHT_KEY) {
				x = sprite.x + 20;
				passo = 6;
			}
		}

		private void move() {
			x += passo;
		}

	}

}
