package projetos.megaMan;

import br.com.wellington.jplay2D.oi.Keyboard;
import projetos.utils.Constantes;

public class MegaManMain {

	// MEGAMAN
	public static final String SPRITE_TIRO = Constantes.PATH_MEGAMAN + "tiro.png";
	public static final String SPRITE_MEGAMAN = Constantes.PATH_MEGAMAN + "boneco.png";
	public static final String BACKDROP = Constantes.PATH_MEGAMAN + "fundo.png";
	public static final String SOM_EXPLOSAO = Constantes.PATH_MEGAMAN + "explosao.wav";
	public static final String SOM_MUZZLESHOT = Constantes.PATH_MEGAMAN + "muzzleshot.wav";

	public static final int LADO_ESQUERDO = Keyboard.LEFT_KEY;
	public static final int LADO_DIREITO = Keyboard.RIGHT_KEY;
	
	public static final int LADO_PARADO = 0;

	public static void main(String[] args) {
		new Janela(800, 600).start();
	}
}
