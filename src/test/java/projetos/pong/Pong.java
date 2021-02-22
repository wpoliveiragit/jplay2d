/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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

/**
 * @author Gefersom Cardoso Lima Federal Fluminense University - UFF - Brazil
 *         Computer Science
 */
public class Pong implements Constantes {

	public Pong() {
		Window janela = new Window(800, 600);

		// Faz o cursor do mouse desaparecer.
		// O parâmetro é o nome da imagem a ser usada no lugar do cursor do mouse.
		// janela.setCursor( janela.createCustomCursor("mouse.png") );
		// Mas como não foi colocada nenhuma imagem, o cursor irá desaparecer
		// somente dentro da tela do jogo.
		janela.setCursorImage("");

		Keyboard keyboard = janela.getKeyboard();
		// No teclado do pacote jplay há as seguintes teclas adicionadas por default:
		// UP_KEY = Seta para cima
		// DOWN_KEY = Seta para baixo
		// LEFT_KEY = Seta para a esquerda
		// RIGHT_KEY = Seta para baixo
		// ESCAPE_KEY = Tecla ESC
		// SPACE_KEY = Barra de Espaço
		// ENTER_KEY = Tecla Enter

		// Para adicionar uma tecla que não se encontra adicionada por default
		// usamos o método addKey(Codigo da Tecla, Comportamento da tecla).

		// O código de cada tecla pode ser descoberto usando-se a classe KeyEvent
		// do próprio Java.
		// Escrevendo KeyEvent (com 'K' maiúsculo) e depois 'VK_', a IDE
		// (NetBeans ou Eclipse) irá listar todas as teclas passiveis de uso
		// na máquina corrente.

		// Cada tecla no Keyboard do jplay pode ter somente dois comportamentos:

		// 1ͦ : DETECT_EVERY_PRESS - o método keyDown presente no teclado
		// retornará true enquanto a tecla estiver pressionada.

		// 2ͦ : DETECT_INITAL_PRESS_ONLY - o método keyDown presente no teclado
		// retornará true somente no momento que a tecla for pressionada.
		// Se o usuário pressionar a tecla durante cinco horas, o método
		// keydown() só retornará true UMA VEZ. Para retornar novamente true
		// o usuário terá que liberar a tecla pressionada.
		//
		// O modo DETECT_INITAL_PRESS_ONLY pode ser utilizado quando queremos fazer
		// um boneco ou uma nave atirar. Se for escolhido o primeiro modo
		// DETECT_EVERY_PRESS o boneco dará uma quantidade excessiva de tiros.

		// O dois comportamentos do teclado citados acima, também servem para o mouse.
		// Os botões adicionados por default no Mouse do pacote jplay são:
		// BUTTON_LEFT = botão esquerdo
		// BUTTON_MIDDLE = Scroll do mouse
		// BUTTON_RIGHT = botão direito do mouse

		keyboard.addKey(KeyEvent.VK_S, Keyboard.DETECT_EVERY_PRESS);
		keyboard.addKey(KeyEvent.VK_W, Keyboard.DETECT_EVERY_PRESS);

		// "fundo01.png" é o nome da imagem que está na mesma pasta do projeto.
		// Não se esqueça que com Strings em Java devemos usar o ' "" '.
		// É obrigatório colocar a extensão da imagem, ao contrário, o arquivo não será
		// reconhecido.
		// Nesse caso a extensão é PNG.
		GameImage fundo = new GameImage(PONG_IMG_FUNDO);

		Bola bola = new Bola();
		// bola.setPosition(coordenadaX, coordenadaY);
		bola.x = 300;
		bola.y = 300;

		Barra barraVerde = new Barra(PONG_IMG_BARRA_VERDE);
		Barra barraRoxa = new Barra(PONG_IMG_BARRA_ROXA);

		barraVerde.x = 40;
		barraVerde.y = 300;
		barraRoxa.x = 745;
		barraRoxa.y = 300;

		// Coloca as barras na suas posições iniciais.
		barraVerde.centralizarNaTela();
		barraRoxa.centralizarNaTela();

		// Time tempo = new Time(horas, minutos, segundos, x, y)
		// horas = 0
		// minutos = 5
		// segundos = 0
		// x = 450
		// y = 100
		Time tempo = new Time(0, 10, 35, 400, 588, false);
		tempo.setColor(Color.WHITE);
		tempo.setFont(FONTE_COMIC_SANS_MS_16);
		Font fonte = FONTE_COMIC_SANS_MS_24;

		// Carrega uma música do tipo wav.
		// Por enquanto o jplay só aceita sons com a extensão wav.
		// É obrigatório colocar a extensão do som.
		// Aqui a extensão é 'wav'.
		Sound musica = new Sound(PONG_SOM_MUSICA);
		musica.setRepeat(true);// faz a música ser tocada continuamente.
		musica.play();

		int pontuacaoRoxo = 0;
		int pontuacaoVerde = 0;

		boolean executando = true;
		while (executando) {
			// A ordem em que os objetos são desenhados na tela é importante.
			// Para ter uma idéia do que estou falando tente mudar a ordem com os objetos
			// são desenhados na tela.
			// Por exemplo:
			// bola.draw();
			// fundo.draw();
			fundo.draw();
			bola.draw();
			barraVerde.draw();
			barraRoxa.draw();
			tempo.draw();

			// Se só restar 30 segundos para o tempo acabar imprime mensagem na tela.
			// tempo.getTotalSecond() - retorna, o tempo total, convertido em segundos.
			if (tempo.getTotalSecond() < 30)
				janela.drawText("O tempo está terminando!", 310, 105, Color.CYAN);

			// Desenha texto na tela
			// janela.drawText(string, x, y, cor, fonte);
			janela.drawText(Integer.toString(pontuacaoVerde), 295, 70, Color.BLACK, fonte);
			janela.drawText(Integer.toString(pontuacaoRoxo), 475, 70, Color.BLACK, fonte);

			// window.display() SEMPRE deve ser chamado por último, no quesito desenhar na
			// tela,
			// pois, é esse método que mostra as atualizações feitas dutante o jogo.
			janela.update();

			// O método collided retorna true se a bola colidiu com a barra azul
			// ao contrário, retorna falso.
			boolean colidiu = true;
			if (bola.collided(barraVerde)) {
				// Faz a bola andar no sentido contrário.
				bola.setSentidoX(PONG_RIGHT);

				// Seta o mesmo sentido que a barra estava se deslocando.
				// Se a barra estava subindo a bola irá subir também, e vice-versa.
				bola.setSentidoY(barraVerde.getSentido());
			} else if (bola.collided(barraRoxa)) {
				// Faz a bola andar no sentido contrário.
				bola.setSentidoX(PONG_LEFT);

				// Seta o mesmo sentido que a barra estava indo
				// Se a barra estava indo para a esquerda irá para a direita
				// e vice-versa.
				bola.setSentidoY(barraRoxa.getSentido());
			} else
				colidiu = false;

			if (colidiu) {
				// Reproduz um som.
				new Sound(PONG_SOM_BATEU).play();
			}

			boolean marcouPonto = true;
			if (bola.x < PONG_LIMITE_ESQUERDA_X + 1)
				pontuacaoRoxo++;
			else if (bola.x + bola.width > PONG_LIMITE_DIREITA_X - 1)
				pontuacaoVerde++;
			else
				marcouPonto = false;

			if (marcouPonto) {
				// Coloca as barras e a bola para em suas posições iniciais
				bola.centralizarNaTela();
				barraVerde.centralizarNaTela();
				barraRoxa.centralizarNaTela();
				new Sound(PONG_SOM_PONTO).play();
			}

			// Move a bola.
			bola.moveX();
			bola.moveY();

			// move as barras
			barraVerde.moveY(keyboard, KeyEvent.VK_W, KeyEvent.VK_S);
			barraRoxa.moveY(keyboard, KeyEvent.VK_UP, KeyEvent.VK_DOWN);

			// Atrasa a execução do jogo em 10ms.
			// Esse método existe pois em algumas máquinas o jogo pode
			// ser executado muito rapidamente.
			janela.delay(10);

			// if (Se a tecla ESC for pressionada) sai do jogo.
			// teclado.keyDown(tecla) - retorna true se a tecla está
			// pressionada, ao contrário, retorna falso.
			// Para acessar as teclas defaults do teclado basta escrever
			// Keyboard seguido de um ponto final.
			// Seria assim: Keyboard.
			// Obs.: Não se esqueça de escrever Keyboard com o 'K' maiúsculo.

			// tempo.timeEnded() = retorna true se o tempo acabou,
			// caso contrário, retorna falso.
			if (keyboard.keyDown(Keyboard.ESCAPE_KEY) || tempo.timeEnded())
				executando = false;
		}

		// Aqui o delay serve para atrasar o encerramento do jogo em 500ms = 0.5s.
		janela.delay(500);

		// Fecha a janela e sai do jogo.
		janela.exit();
	}
}