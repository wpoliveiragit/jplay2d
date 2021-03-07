package projetos.megaMan;

import br.com.wellington.jplay2D.oi.Keyboard;
import projetos.utils.Constantes;

public class MegaManApplication {

	// MEGAMAN
	public static final String ROOT = Constantes.ROOT + "/megaMan/";;

	public static final String SPT_TIRO = ROOT + "tiro.png";
	public static final String SPT_MEGAMAN = ROOT + "boneco.png";
	public static final String IMG_BD = ROOT + "fundo.png";
	public static final String SOM_EXPLOSAO = ROOT + "explosao.wav";
	public static final String SOM_MUZZLESHOT = ROOT + "muzzleshot.wav";

	//keyboard
	public static final int LADO_ESQUERDO = Keyboard.LEFT_KEY;
	public static final int LADO_DIREITO = Keyboard.RIGHT_KEY;

	//Escolha de lado parado
	public static final int LADO_PARADO = 0;

	public static void main(String[] args) {
		new Janela(800, 600).start();
	}
}
