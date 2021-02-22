package projetos.labirinto;

import java.awt.Dimension;

import br.com.wellington.jplay2D.oi.Keyboard;
import br.com.wellington.jplay2D.window.Window;

public class JogoBase implements Controle {

	/**
	 * O Frame do jogo.
	 */
	protected Window janela;

	/**
	 * Objeto de captura do teclado.
	 */
	protected Keyboard teclado;

	/**
	 * Manipulador do controle do teclado.
	 */
	protected Controle controle;

	/**
	 * Controle do laco de atualizacao do jogo.
	 */
	protected boolean loop;

	/**
	 * Contrutor base do jogo.
	 */
	public JogoBase() {
		janela = new Window(800, 600);
		teclado = janela.getKeyboard();
		controle = this;
	}

	/**
	 * Inicia o jogo.
	 */
	public void inicia() {
		loop = true;
		while (loop) {
			atualiza();// atualiza todo o cenario
			controleDigito(); // busca um evento do teclado
			janela.delay(10);// espera um tempo para atualizar
		}
		janela.exit();
	}

	/**
	 * Desenha todo o cenario e at aliza a janela.
	 */
	public void atualiza() {
		janela.update();
	}

	/**
	 * Finaliza o jogo.
	 */
	public void finaliza() {
		loop = false;
	}

	/**
	 * Retorna o teclado do jogo.
	 * 
	 * @return O teclado do jogo.
	 */
	public Keyboard getTeclado() {
		return teclado;
	}

	/**
	 * Controle da captura de digitos.
	 */
	public void controleDigito() {
		if (teclado.keyDown(Keyboard.ESCAPE_KEY)) { // botao esc
			controle.controleEsc();
			return;
		}
		if (teclado.keyDown(Keyboard.DOWN_KEY)) { // vira ou anda pra baixo
			controle.controleCetaBaixo();
			return;
		}
		if (teclado.keyDown(Keyboard.LEFT_KEY)) {// vira ou anda pra esquerda
			controle.controleCetaEsquerda();
			return;
		}
		if (teclado.keyDown(Keyboard.RIGHT_KEY)) {// vira ou anda pra direita
			controle.controleCetaDireita();
			return;
		}
		if (teclado.keyDown(Keyboard.UP_KEY)) { // vira ou anda pra cima
			controle.controleCetaCima();
		}
	}

	@Override
	public void controleEsc() {
		loop = false;
	}

	@Override
	public void controleCetaEsquerda() {
	}

	@Override
	public void controleCetaDireita() {
	}

	@Override
	public void controleCetaCima() {
	}

	@Override
	public void controleCetaBaixo() {
	}

	/**
	 * Retorna a janela do jogo.
	 * 
	 * @return A janela do jogo.
	 */
	public Window getJanela() {
		return janela;
	}

	/**
	 * Retorna a dimencao da janela.
	 * 
	 * @return A dimencao da janela.
	 */
	public Dimension getDimencao() {
		return new Dimension(janela.getJFrame().getWidth(), janela.getJFrame().getHeight());
	}

}
