package projetos.labirinto;

import java.awt.Dimension;
import java.awt.event.KeyEvent;

import br.com.wellington.jplay2D.oi.Keyboard;
import br.com.wellington.jplay2D.window.Window;

public class JogoBase implements Controle {

	/** O Frame do jogo. */
	protected Window janela;

	/** Objeto de captura do teclado. */
	protected Keyboard keyboard;

	/** Manipulador do controle do teclado. */
	protected Controle controle;

	/** Controle do laco de atualizacao do jogo. */
	protected boolean loop;

	/** Contrutor base do jogo. */
	public JogoBase() {
		janela = Window.create(800, 600);
		keyboard = janela.getKeyboard();
		controle = this;
		configuration();
	}

	private void configuration() {
		// [TECLADO - add botão de evento por tecla]
		keyboard.addKeyPressed(KeyEvent.VK_ESCAPE);
		keyboard.addKeyPressed(KeyEvent.VK_ENTER);
		keyboard.addKeyPressed(KeyEvent.VK_SPACE);
		// [TECLADO - add botão de evento por pressão]
		keyboard.addKeyHeldDown(KeyEvent.VK_UP);
		keyboard.addKeyHeldDown(KeyEvent.VK_DOWN);
		keyboard.addKeyHeldDown(KeyEvent.VK_LEFT);
		keyboard.addKeyHeldDown(KeyEvent.VK_RIGHT);
	}

	/** Inicia o jogo. */
	public void start() {
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
		return keyboard;
	}

	/**
	 * Controle da captura de digitos.
	 */
	public void controleDigito() {
		if (keyboard.keyDown(KeyEvent.VK_ESCAPE)) { // botao esc
			controle.controleEsc();
			return;
		}
		if (keyboard.keyDown(KeyEvent.VK_DOWN)) { // vira ou anda pra baixo
			controle.controleCetaBaixo();
			return;
		}
		if (keyboard.keyDown(KeyEvent.VK_LEFT)) {// vira ou anda pra esquerda
			controle.controleCetaEsquerda();
			return;
		}
		if (keyboard.keyDown(KeyEvent.VK_RIGHT)) {// vira ou anda pra direita
			controle.controleCetaDireita();
			return;
		}
		if (keyboard.keyDown(KeyEvent.VK_UP)) { // vira ou anda pra cima
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
