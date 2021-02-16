package projetos.pacman;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;

import jplay.Window;

public final class Jogo implements Constantes, JogoInteracao {

	/**
	 * O Frame do jogo.
	 */
	private Window janela;

	/**
	 * Desenho da janela.
	 */
	private Desenho desenho;

	/**
	 * Objeto de captura do teclado.
	 */
	private Teclado teclado;

	/**
	 * Controle do laco de atualizacao do jogo.
	 */
	private boolean loop;

	/**
	 * CONSTRUTOR base do jogo.
	 */
	public Jogo() {
		janela = new Window(JANELA_LARGURA, JANELA_ALTURA);
		teclado = new Teclado();
		new MenuJogoInicial(this);
		janela.setDelay(JANELA_DELAY_ATUALIZACAO);
		inicia();
	}

	/**
	 * Inicia o jogo.
	 */
	private void inicia() {
		loop = true;
		while (loop) {
			desenho.atualiza();
			desenho.desenha();

			janela.update();

			teclado.controle();
			janela.delay();
		}
		janela.exit();
	}

	@Override
	public void finaliza() {
		loop = false;
	}

	@Override
	public Teclado getTeclado() {
		return teclado;
	}

	@Override
	public void setDesenho(Desenho desenho) {
		this.desenho = desenho;
	}

	@Override
	public void escreve(Point ponto, Font fonte, Color cor, String txt) {
		janela.write(ponto.x, ponto.y, cor, fonte, txt);
	}

}
