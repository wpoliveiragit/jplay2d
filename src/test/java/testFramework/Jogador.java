package testFramework;

import br.com.wellington.jplay2D.image.Sprite;

public class Jogador extends Sprite {

	Sequencia drop = new Sequencia(0, 1);
	Sequencia materializando = new Sequencia(1, 7);
	Sequencia parado = new Sequencia(7, 10);
	Sequencia andandoDireita = new Sequencia(11, 21);

	public Jogador() {
		super(TestMain.SPT_MEGAMAN, 90);
		setTotalDuration(3200);
		setSequence(andandoDireita.a, andandoDireita.b);
		setGravity(0.2);
	}

}

class Sequencia {
	final int a;
	final int b;

	Sequencia(int a, int b) {
		this.a = a;
		this.b = b;
	}
}