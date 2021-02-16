/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.wellington.jplay2D.projetos.jogoDaMemoria;

import java.awt.Color;
import java.awt.Font;

import br.com.wellington.jplay2D.Animation;
import br.com.wellington.jplay2D.GameImage;
import br.com.wellington.jplay2D.Keyboard;
import br.com.wellington.jplay2D.Mouse;
import br.com.wellington.jplay2D.Time;
import br.com.wellington.jplay2D.Window;
import br.com.wellington.jplay2D.utils.Constantes;

/**
 * @author Gefersom Cardoso Lima Federal Fluminense University Computer Science
 */

public class TelaMensagemFimJogo implements Constantes {

	private Window window;
	private String tempo;
	private Keyboard keyboard;
	private Animation botao;
	private GameImage backGround;
	private Mouse mouse;
	private Font fonteDaMensagemFinal;

	public TelaMensagemFimJogo(Window window, Time tempo) {
		this.window = window;
		this.tempo = tempo.toString();
		this.keyboard = window.getKeyboard();
		this.mouse = window.getMouse();
		this.botao = new Animation(JOGO_DA_MEMORIA_IMG_BOTAO, 5);
		this.backGround = new GameImage(JOGO_DA_MEMORIA_IMG_BOTAO_FUNDO);
		this.fonteDaMensagemFinal = FONTE_COMIC_SAMS_MS;

		setarConfiguracoes();
		loop();
		descarregarObjetos();
	}

	private void setarConfiguracoes() {
		botao.setTotalDuration(900);
		botao.x = 350;
		botao.y = 250;
		botao.setSequence(0, 4);
	}

	private void loop() {
		boolean executando = true;
		botao.pause();
		while (executando) {
			if (mouse.isOverObject(botao) && mouse.isLeftButtonPressed())
				botao.play();

			botao.update();

			if (keyboard.keyDown(Keyboard.ESCAPE_KEY) || botao.getCurrFrame() + 1 == botao.getFinalFrame())
				executando = false;

			desenhar();
		}
	}

	private void desenhar() {
		window.clear(Color.black);
		backGround.draw();
		botao.draw();
		window.drawText("Você conseguiu em: " + tempo + "!", 250, 250, Color.YELLOW, fonteDaMensagemFinal);

		// Esse método SEMPRE deve ser chamado por último.
		window.update();
	}

	private void descarregarObjetos() {
		backGround = null;
		botao = null;
		tempo = null;
		keyboard = null;
		mouse = null;
	}
}
