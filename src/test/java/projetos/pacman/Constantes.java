package projetos.pacman;

import java.awt.Font;
import java.awt.Point;

public interface Constantes extends ConstantesPersonagem, ConstantesCenario, ConstantesPath {

	/**
	 * Largura da janela do jogo.
	 */
	public final int JANELA_LARGURA = 800;

	/**
	 * Altura da janela do jogo.
	 */
	public final int JANELA_ALTURA = 600;

	/**
	 * delay da atualizacao da janela do jogo.
	 */
	public final long JANELA_DELAY_ATUALIZACAO = 10;

	/**
	 * Ponto da 1a opcao do menu inicial na janela.
	 */
	public final Point JANELA_MENU_INICIAL_PONTO_OP_A = new Point(412, 325);

	/**
	 * Ponto da 2a opcao do menu inicial na janela.
	 */
	public final Point JANELA_MENU_INICIAL_PONTO_OP_B = new Point(501, 325);

	/**
	 * Ponto da 3a opcao do menu inicial na janela.
	 */
	public final Point JANELA_MENU_INICIAL_PONTO_OP_C = new Point(588, 325);

	/**
	 * Fonte Comic Sans Ms.
	 */
	public final static Font FONTE_COMIC = new Font("Comic Sans MS", Font.TRUETYPE_FONT, 40);

}
