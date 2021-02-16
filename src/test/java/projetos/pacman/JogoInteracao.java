package projetos.pacman;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;

public interface JogoInteracao {

	/**
	 * Retorna o controle do teclado do jogo.
	 * 
	 * @returnO controle do teclado do jogo.
	 */
	public Teclado getTeclado();

	/**
	 * Finaliza o jogo.
	 */
	public void finaliza();

	/**
	 * Configura um novo controle e um novo desenho ao jogo.
	 * 
	 * @param controle Controle de degito do desenho da janela.
	 * @param desenho  O novo desenho da janela.
	 */
	public void setDesenho(Desenho desenho);

	/**
	 * Escreve um texto na janela
	 * 
	 * @param txt   O texto.
	 * @param cor   a cor do texto.
	 * @param ponto Ponto de inicio do texto.
	 */
	public void escreve(Point ponto, Font fonte, Color cor, String txt);

}
