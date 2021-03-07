package projetos.pong;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;

import br.com.wellington.jplay2D.imageProcessing.GameImage;
import br.com.wellington.jplay2D.oi.Keyboard;
import br.com.wellington.jplay2D.sound.Sound;
import br.com.wellington.jplay2D.time.Time;
import br.com.wellington.jplay2D.window.Window;
import projetos.utils.Constantes;

public class Pong {

	public Pong(int width, int height) {
		Window janela = new Window(width, height);
		janela.setCursorImage("");
		Keyboard keyboard = janela.getKeyboard();

		keyboard.addKey(KeyEvent.VK_S, Keyboard.DETECT_EVERY_PRESS);
		keyboard.addKey(KeyEvent.VK_W, Keyboard.DETECT_EVERY_PRESS);

		GameImage fundo = new GameImage(Constantes.PONG_IMG_FUNDO);

		Bola bola = new Bola();
		bola.x = 300;
		bola.y = 300;

		Barra barraVerde = new Barra(Constantes.PONG_IMG_BARRA_VERDE);
		Barra barraRoxa = new Barra(Constantes.PONG_IMG_BARRA_ROXA);

		barraVerde.x = 40;
		barraVerde.y = 300;
		barraRoxa.x = 745;
		barraRoxa.y = 300;

		barraVerde.centralizarNaTela();
		barraRoxa.centralizarNaTela();

		Time tempo = new Time(0, 10, 35, 400, 588, false);
		tempo.setColor(Color.WHITE);
		tempo.setFont(Constantes.FONTE_COMIC_SANS_MS_16);
		Font fonte = Constantes.FONTE_COMIC_SANS_MS_24;

		Sound musica = new Sound(Constantes.PONG_SOM_MUSICA);
		musica.setRepeat(true);// faz a música ser tocada continuamente.
		musica.play();

		int pontuacaoRoxo = 0;
		int pontuacaoVerde = 0;

		boolean executando = true;
		while (executando) {
			fundo.draw();
			bola.draw();
			barraVerde.draw();
			barraRoxa.draw();
			tempo.draw();

			if (tempo.getTotalSecond() < 30)
				janela.drawText("O tempo está terminando!", 310, 105, Color.CYAN);

			janela.drawText(Integer.toString(pontuacaoVerde), 295, 70, Color.BLACK, fonte);
			janela.drawText(Integer.toString(pontuacaoRoxo), 475, 70, Color.BLACK, fonte);

			janela.update();

			boolean colidiu = true;
			if (bola.collided(barraVerde)) {
				bola.setSentidoX(Constantes.PONG_SENTIDO_DIREITA);

				bola.setSentidoY(barraVerde.getSentido());
			} else if (bola.collided(barraRoxa)) {
				bola.setSentidoX(Constantes.PONG_SENTIDO_ESQUERDA);

				bola.setSentidoY(barraRoxa.getSentido());
			} else
				colidiu = false;

			if (colidiu) {
				new Sound(Constantes.PONG_SOM_BATEU).play();
			}

			boolean marcouPonto = true;
			if (bola.x < Constantes.PONG_LIMITE_ESQUERDA_X + 1)
				pontuacaoRoxo++;
			else if (bola.x + bola.width > Constantes.PONG_LIMITE_DIREITA_X - 1)
				pontuacaoVerde++;
			else
				marcouPonto = false;

			if (marcouPonto) {
				bola.centralizarNaTela();
				barraVerde.centralizarNaTela();
				barraRoxa.centralizarNaTela();
				new Sound(Constantes.PONG_SOM_PONTO).play();
			}

			bola.moveX();
			bola.moveY();

			barraVerde.moveY(keyboard, KeyEvent.VK_W, KeyEvent.VK_S);
			barraRoxa.moveY(keyboard, KeyEvent.VK_UP, KeyEvent.VK_DOWN);

			janela.delay(10);

			if (keyboard.keyDown(Keyboard.ESCAPE_KEY) || tempo.timeEnded())
				executando = false;
		}

		janela.delay(500);

		janela.exit();
	}
}