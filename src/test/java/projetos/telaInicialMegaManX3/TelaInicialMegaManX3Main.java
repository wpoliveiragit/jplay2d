package projetos.telaInicialMegaManX3;

import projetos.utils.Constantes;

/**
 * @author Gefersom Cardoso Lima Federal Fluminense University - UFF - Brazil
 *         Computer Science
 */
public class TelaInicialMegaManX3Main {
	public static final String PATH_RECURSOS = Constantes.ROOT + "/telaInicialMegaManX3/";
	public static final String PATH_MOUSE = PATH_RECURSOS + "mouse.png";
	public static final String PATH_MUSICA = PATH_RECURSOS + "musica.wav";
	public static final String PATH_SOM_TROCA_SELECAO = PATH_RECURSOS + "troca.wav";
	public static final String PATH_TELA_INICIAL = PATH_RECURSOS + "telaInicial.png";

	public static void main(String[] args) {
		new TelaInicialMegaManX3().start();
	}

}