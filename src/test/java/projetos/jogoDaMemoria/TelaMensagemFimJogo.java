package projetos.jogoDaMemoria;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;

import br.com.wellington.jplay2D.imageProcessing.Animation;
import br.com.wellington.jplay2D.imageProcessing.GameImage;
import br.com.wellington.jplay2D.oi.Keyboard;
import br.com.wellington.jplay2D.oi.Mouse;
import br.com.wellington.jplay2D.time.Time;
import br.com.wellington.jplay2D.window.Window;

public class TelaMensagemFimJogo {

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
		this.botao = new Animation(JogoDaMemoriaMain.JOGO_DA_MEMORIA_IMG_BOTAO, 5);
		this.backGround = new GameImage(JogoDaMemoriaMain.JOGO_DA_MEMORIA_IMG_BOTAO_FUNDO);
		this.fonteDaMensagemFinal = JogoDaMemoriaMain.FONTE_COMIC_SAMS_MS;
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
			if (mouse.isOverObject(botao) && mouse.isLeftButtonPressed()) {
				botao.play();
			}
			botao.update();
			if (keyboard.keyDown(KeyEvent.VK_ESCAPE) || botao.getCurrFrame() + 1 == botao.getFinalFrame()) {
				executando = false;
			}
			desenhar();
		}
	}

	private void desenhar() {
		window.clear(Color.black);
		backGround.draw();
		botao.draw();
		window.drawText("Você conseguiu em: " + tempo + "!", 250, 250, Color.YELLOW, fonteDaMensagemFinal);
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
