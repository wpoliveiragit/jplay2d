package br.com.wellington.jplay2D.projetos.megaMan;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import br.com.wellington.jplay2D.Keyboard;
import br.com.wellington.jplay2D.Sound;
import br.com.wellington.jplay2D.Sprite;
import br.com.wellington.jplay2D.Window;

public class Boneco {

	public static final int ACAO_PARADO = 0;

	private Window quadro;
	private Keyboard keyboard;

	private List<Tiro> balas = new ArrayList<Tiro>();
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
		this.solo = solo;
		this.quadro = quadro;
		keyboard = quadro.getKeyboard();
		sprite = new Sprite(Main.MEGAMAN_SPRITE_MEGAMAN, 28);
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

		// atirar
		if (keyboard.keyDown(KeyEvent.VK_CONTROL)) {
			balas.add(new Tiro(sprite, lado, solo));
			new Sound(Main.MEGAMAN_SOM_MUZZLESHOT).play();
		}
		// vai pra esquerda
		if (keyboard.keyDown(Keyboard.LEFT_KEY) && sprite.x > 1) {
			move(Keyboard.LEFT_KEY, 0, 13, -2, 0);
			return;
		}
		// vai pra direita
		if (keyboard.keyDown(Keyboard.RIGHT_KEY) && sprite.x + sprite.width < quadro.getWidth()) {
			move(Keyboard.RIGHT_KEY, 14, 27, 2, 0);
			return;
		}
		// esta parado
		if (ladoMovimento != ACAO_PARADO) {
			if (ladoMovimento == Keyboard.RIGHT_KEY) {
				sprite.setCurrFrame(17);
			} else {
				sprite.setCurrFrame(3);
			}
			ladoMovimento = ACAO_PARADO;
			sprite.update();
		}
	}

	/** Desenha no quadro as balas e o personagem. */
	public void draw() {
		sprite.draw();
		// atualiza os tiros
		for (int i = 0; i < balas.size(); i++) {
			Tiro tiro = balas.get(i);
			if (tiro.isOnFloor() || tiro.x < 1 || tiro.x + tiro.width > quadro.getWidth()) {
				balas.remove(i--);
				new Sound(Main.MEGAMAN_SOM_EXPLOSAO).play();
				continue;
			}
			tiro.move();
			tiro.draw();
			tiro.update();
		}
	}

}
