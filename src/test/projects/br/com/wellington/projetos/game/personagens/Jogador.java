package br.com.wellington.projetos.game.personagens;

import br.com.wellington.jplay2D.image.Sprite;
import br.com.wellington.jplay2D.image.TileInfo;
import br.com.wellington.projetos.game.utils.Constants;

public class Jogador extends Sprite {

	Sequencia drop = new Sequencia(0, 1);
	Sequencia materializando = new Sequencia(1, 7);
	Sequencia parado = new Sequencia(7, 10);
	Sequencia andandoDireita = new Sequencia(11, 21);

	public Jogador() {
		super(Constants.SPT_MEGAMAN, 90);
		setTotalDuration(3200);
		setSequence(andandoDireita.a, andandoDireita.b);
		setGravity(0.2);
	}

	public TileInfo getHitBox() {
		TileInfo hb = new TileInfo();
		hb.x = x + width / 4;
		hb.y = y;
		hb.width = width / 2;
		hb.height = height;
		return hb;
	}

	public TileInfo getHitBoxHight() {
		TileInfo hb = new TileInfo();
		hb.x = x + width;
		hb.y = y;
		hb.width = 4;
		hb.height = height + 2;
		return hb;
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