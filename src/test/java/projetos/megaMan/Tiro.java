package projetos.megaMan;

import javax.swing.JFrame;

import br.com.wellington.jplay2D.image.Sprite;
import br.com.wellington.jplay2D.sound.Sound;

public class Tiro {

	private int passo = -6;

	private Sprite spriteTiro;

	private JFrame quadro;

	/** Cria um objeto de tiro. */
	public Tiro(JFrame quadro, Sprite spriteBoneco, int lado, int solo) {
		this.quadro = quadro;
		spriteTiro = new Sprite(MegaManMain.SPT_TIRO, 7);
		spriteTiro.setTotalDuration(200);
		spriteTiro.setGravity(0.0098);
		spriteTiro.setFloor(solo);
		spriteTiro.y = spriteBoneco.y + (spriteBoneco.height / 3);
		spriteTiro.x = spriteBoneco.x;

		if (lado == MegaManMain.LADO_DIREITO) {
			spriteTiro.x = spriteBoneco.x + 20;
			passo = 6;
		}
		new Sound(MegaManMain.SOM_MUZZLESHOT).play();
	}

	/**
	 * Retorna true caso o tiro encoste no solo.
	 * 
	 * @return true caso o tiro encoste no solo.
	 */
	public boolean move() {

		if (spriteTiro.isOnFloor() || spriteTiro.x < 1 || spriteTiro.x + spriteTiro.width > quadro.getWidth()) {
			new Sound(MegaManMain.SOM_EXPLOSAO).play();
			return true;
		}
		spriteTiro.fall();
		spriteTiro.x += passo;
		spriteTiro.draw();
		spriteTiro.update();
		return false;
	}

}
