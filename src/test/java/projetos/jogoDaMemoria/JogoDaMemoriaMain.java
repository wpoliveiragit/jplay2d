
package projetos.jogoDaMemoria;

import java.awt.Font;

import projetos.utils.Constantes;

public class JogoDaMemoriaMain {

	public static final String PATH = Constantes.ROOT + "/jogoDaMemoria/";
	public static final String JOGO_DA_MEMORIA_IMG_MOUSE = PATH + "mouse.png";
	public static final String JOGO_DA_MEMORIA_IMG_FUNDO = PATH + "fundo.png";

	public static final Font FONTE_SANSSERIF = new Font("sansserif", Font.TRUETYPE_FONT, 12);

	public static final String SOM_SOM1 = PATH + "som1.wav";
	public static final String SOM_SOM2 = PATH + "som2.wav";
	public static final String SOM_SOM3 = PATH + "som3.wav";

	public static final String IMG_PECA_01 = PATH + "peca01.png";
	public static final String IMG_PECA_02 = PATH + "peca02.png";
	public static final String IMG_PECA_03 = PATH + "peca03.png";
	public static final String IMG_PECA_04 = PATH + "peca04.png";
	public static final String IMG_PECA_05 = PATH + "peca05.png";
	public static final String IMG_PECA_06 = PATH + "peca06.png";
	public static final String IMG_PECA_07 = PATH + "peca07.png";
	public static final String IMG_PECA_08 = PATH + "peca08.png";
	public static final String IMG_PECA_09 = PATH + "peca09.png";
	public static final String IMG_PECA_10 = PATH + "peca10.png";

	public static final String JOGO_DA_MEMORIA_IMG_BOTAO = PATH + "botao.png";
	public static final String JOGO_DA_MEMORIA_IMG_BOTAO_FUNDO = PATH + "fundoBotao.png";

	public static final Font FONTE_COMIC_SAMS_MS = new Font("Comic Sans MS", Font.TRUETYPE_FONT, 20);

	public static void main(String[] args) {
		new JogoMemoria().start();
	}

}
