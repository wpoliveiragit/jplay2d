package br.com.wellington.jplay2D.projetos.megaMan;

import br.com.wellington.jplay2D.Keyboard;
import br.com.wellington.jplay2D.Sprite;

public class Tiro extends Sprite {

	private int passo = -6;// true = direita

	/** Cria um objeto de tiro. */
	public Tiro(Sprite spriteChar, int lado, int solo) {
		super(Main.MEGAMAN_SPRITE_TIRO, 7);
		setTotalDuration(200);
		setGravity(0.0098);
		setFloor(solo);
		y = spriteChar.y + (spriteChar.height / 3);
		x = spriteChar.x;
		if (lado == Keyboard.RIGHT_KEY) {
			x = spriteChar.x + 20;
			passo = 6;
		}
	}

	public void move() {
		fall();
		x += passo;
	}

}
