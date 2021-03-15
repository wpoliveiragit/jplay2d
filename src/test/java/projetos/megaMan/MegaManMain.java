package projetos.megaMan;

import java.awt.event.KeyEvent;

import projetos.utils.Constantes;

public class MegaManMain {

	// MEGAMAN
	public static final String ROOT = Constantes.ROOT + "/megaMan/";;

	public static final String SPT_TIRO = ROOT + "tiro.png";
	public static final String SPT_MEGAMAN = ROOT + "boneco.png";
	public static final String IMG_BD = ROOT + "fundo.png";
	public static final String SOM_EXPLOSAO = ROOT + "explosao.wav";
	public static final String SOM_MUZZLESHOT = ROOT + "muzzleshot.wav";

	// keyboard
	public static final int LADO_ESQUERDO = KeyEvent.VK_LEFT;
	public static final int LADO_DIREITO = KeyEvent.VK_RIGHT;

	// Escolha de lado parado
	public static final int LADO_PARADO = 0;

	public static void main(String[] args) {
		new Janela(800, 600).start();
	}
}
