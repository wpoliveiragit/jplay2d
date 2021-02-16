/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.wellington.jplay2D.projetos.telaInicialMegaManX3;

import br.com.wellington.jplay2D.Keyboard;
import br.com.wellington.jplay2D.Sound;
import br.com.wellington.jplay2D.Sprite;
import br.com.wellington.jplay2D.Window;
import br.com.wellington.jplay2D.utils.Constantes;

/**
 * @author Gefersom Cardoso Lima Federal Fluminense University - UFF - Brazil
 *         Computer Science
 */
public class TelaInicial implements Constantes{

	private Window window;
	private Sprite backGround;
	private Keyboard keyboard;
	private int opcaoEscolhida = 0;
	private Sound musica;

	public TelaInicial() {
		carregarObjetos();
		loop();
		descarregarObjetos();
	}

	private void carregarObjetos() {
		// Cria uma janela com dimensão de 800 x 600.
		window = new Window(800, 600);

		// Muda a imagem do cursor do mouse.
		window.setCursor(window.createCustomCursor(MEGAMAN_X3_IMG_MOUSE));

		// Carrega o sprite a ser usado, o valor 3 corresponde ao número de frames
		// usados.
		backGround = new Sprite(MEGAMAN_X3_IMG_TELA_INICIAL, 3);

		// Captura uma instância de teclado presetente pela janela que criamos.
		keyboard = window.getKeyboard();

		// Como as teclas padrões do teclado, up, down, left, right
		// são adiconadas com o comportamento Keyboard.DETECT_EVERY_PRESS,
		// mudamos o coportamento para DETECT_INITIAL_PRESS_ONLY. Para entender
		// o que aconteceria sem não fizessemos isso, apague os comandos abaixo e
		// execute o jogo:
		keyboard.setBehavior(Keyboard.UP_KEY, Keyboard.DETECT_INITIAL_PRESS_ONLY);
		keyboard.setBehavior(Keyboard.DOWN_KEY, Keyboard.DETECT_INITIAL_PRESS_ONLY);

		musica = new Sound(MEGAMAN_X3_SOM_MUSICA);
		musica.setRepeat(true);// faz a música se repetir continuamente.
	}

	private void desenhar() {
		// Desenha a imagem usada como fundo da tela.
		backGround.draw();

		// Mostra as atualizações - esse método não pode faltar e tem
		// que ser o último a ser chamado.
		window.update();
	}

	// Escolha de opções do menu...
	private void verificarOpcaoEscolhida() {

		boolean escolheuUmaOpcao = true;

		// Se a tecla foi pressionada...
		if (keyboard.keyDown(Keyboard.UP_KEY)) {
			// O if serve para não deixar que a opção seja menor que zero.
			if (opcaoEscolhida > 0)
				opcaoEscolhida--;
		} else {
			// Se a tecla para baixo foi pressionada...
			if (keyboard.keyDown(Keyboard.DOWN_KEY)) {
				// O if serve para não deixar que a opção seja maior que dois.
				if (opcaoEscolhida < 2)
					opcaoEscolhida++;
			} else {
				escolheuUmaOpcao = false;
			}
		}

		if (escolheuUmaOpcao) {
			new Sound(MEGAMAN_X3_SOM_TROCA).play();
		}

		// Seta a opção do menu escolhida pelo usuário.
		backGround.setCurrFrame(opcaoEscolhida);
	}

	private void descarregarObjetos() {
		window.exit();
		window = null;
		backGround = null;
		keyboard = null;
	}

	private void loop() {
		musica.play();
		boolean sair = false;
		do {
			desenhar();
			verificarOpcaoEscolhida();

			// Se apertar a tecla ESC, sai da tela inicial.
			if (keyboard.keyDown(Keyboard.ESCAPE_KEY))
				sair = true;

		} while (sair == false);
	}
}
