package projetos.jogoDaMemoria;

import java.awt.Color;
import java.awt.event.KeyEvent;

import br.com.wellington.jplay2D.audio.Audio;
import br.com.wellington.jplay2D.image.GameImage;
import br.com.wellington.jplay2D.oi.Keyboard;
import br.com.wellington.jplay2D.oi.Mouse;
import br.com.wellington.jplay2D.time.Time;
import br.com.wellington.jplay2D.window.Window;

public class JogoMemoria {

	private Window window;
	private Mouse mouse;
	private Keyboard keyboard;
	private MatrizPecas pecas;
	private Peca pecaUmEscolhida;
	private Peca pecaDoisEscolhida;
	private Time tempo;
	private GameImage fundo;
	private int numPecasCombinadas;
	private int pontuacao;
	private int NUMERO_TOTAL_COMBINACOES;// É o número de peças diferentes

	public JogoMemoria() {
		configuration();
	}

	private void configuration() {
		// A windows SEMPRE deve ser a primeira a ser CARREGADA
		window = Window.getInstance(800, 600);

		mouse = window.getMouse();
		mouse.setCursorImage(JogoDaMemoriaMain.JOGO_DA_MEMORIA_IMG_MOUSE);
		keyboard = window.getKeyboard();

		pecas = new MatrizPecas();
		pecas.setDeslocamento(150, 130);
		pecas.gerarPosicoesAleatorias();

		numPecasCombinadas = 0;
		fundo = new GameImage(JogoDaMemoriaMain.JOGO_DA_MEMORIA_IMG_FUNDO);

		tempo = new Time(660, 140, true);
		tempo.setColor(Color.GRAY);
		tempo.setFont(JogoDaMemoriaMain.FONTE_SANSSERIF);

		NUMERO_TOTAL_COMBINACOES = 10;

		keyboard.addKeyBehaviorActuatorRequest(KeyEvent.VK_ESCAPE);//
	}

	public void start() {
		boolean executanto = true;
		long tempoPassado = 0;

		numPecasCombinadas = NUMERO_TOTAL_COMBINACOES;
		while (executanto) {
			if (numPecasCombinadas != NUMERO_TOTAL_COMBINACOES) {
				desenhar();
				escolherPecas();
				tempoPassado = verificarPecas(tempoPassado);
			} else {
				new TelaMensagemFimJogo(window, tempo);

				// Reseta o tempo para zero
				// tempo.setTime(int hour, int minute, int seconds)
				// hora = 0; minuto = 0; segundos = 0
				tempo.setTime(0, 0, 0);

				pecas.esconderTodasPecas();
				pecas.gerarPosicoesAleatorias();
				numPecasCombinadas = 0;
				pontuacao = 0;
			}

			if (keyboard.checkKey(KeyEvent.VK_ESCAPE))
				executanto = false;
		}
		// Fecha a janela de jogo
		window.exit();
	}

	// Verifica quais peças o jogador escolheu
	private void escolherPecas() {
		// O valor null significa que o jogador está com o ponteiro do mouse
		// fora da área onde existem peças, ou seja, não está selecionando
		// peça alguma.
		Peca peca = pecas.getPecaSobMouse(mouse.getPosition());
		if ((peca != null) && (peca.foiEscolhida() == false) && mouse.getLeftButton().isPressed()) {
			// As variáveis 'pecaUmEscolhida' e 'pecaDoisEscolhida'
			// apontam para peças existentes na matriz de peças, isso se
			// o jogador escolher alguma peça.
			if (pecaUmEscolhida == null) {
				pecaUmEscolhida = peca;
				pecaUmEscolhida.mostrar();
				new Audio(JogoDaMemoriaMain.SOM_SOM1).play();
			} else { // A segunda condição do if previne que a
						// primeiraPecaEscolhida e a SegundaPecaEscolhida
						// sejam a mesma peça
				if (pecaDoisEscolhida == null && peca != pecaUmEscolhida) {
					pecaDoisEscolhida = peca;
					pecaDoisEscolhida.mostrar();
				}
			}
		}
	}

	// Verifica se as pecas escolhidas são iguais
	private long verificarPecas(long tempoPassado) {
		// Se as duas pecas forem diferentes de null, significa que o jogador
		// escolheu duas peças
		if (pecaDoisEscolhida != null && pecaUmEscolhida != null) {
			// Antes de fazer a verificação se a duas peças escolhidas
			// são iguais ou não, esperamos 0.3 segundos, esse tempo é
			// necessário para que o usuário veja as peças que ele
			// escolheu. O tempo só pode comecar a ser contado a partir
			// do momento em que duas peças são escolhidas.
			boolean anularPecasEscolhidas = true;
			if (pecaUmEscolhida.getID() == pecaDoisEscolhida.getID()) {
				numPecasCombinadas++;
				pontuacao += 5;
				new Audio(JogoDaMemoriaMain.SOM_SOM3).play();
			} else {
				if (tempoPassado > 500) {
					pontuacao -= 5;
					pecaUmEscolhida.esconder();
					pecaDoisEscolhida.esconder();
					new Audio(JogoDaMemoriaMain.SOM_SOM2).play();
					tempoPassado = 0;
				} else {
					tempoPassado += window.getGameTime().latecy();
					anularPecasEscolhidas = false;
				}
			}

			if (anularPecasEscolhidas) {
				pecaUmEscolhida = null;
				pecaDoisEscolhida = null;
			}
		}

		return tempoPassado;
	}

	private void desenhar() {
		fundo.draw();
		pecas.desenharPecas();
		window.drawText("Score: " + pontuacao, 90, 140, Color.GRAY);
		window.drawText("Não beba e dirija!", 100, 580, Color.gray);
		tempo.draw("Time: ");

		// esse método SEMPRE deve ser chamado por último
		window.update();
	}

}
