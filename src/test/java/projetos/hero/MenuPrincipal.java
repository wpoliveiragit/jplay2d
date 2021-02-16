package projetos.hero;

import java.awt.Color;
import java.awt.Point;

import jplay.GameImage;
import jplay.Keyboard;
import jplay.Sound;
import jplay.Window;

public class MenuPrincipal implements ControleJanela {

	private Window janela;

	private Keyboard teclado;
	private GameImage backGound;

	private MenuVertical selection;

	private Sound musica;

	public MenuPrincipal() {

		this.janela = Window.getInstance();
		backGound = new GameImage("backGround.png");

		teclado = janela.getKeyboard();
		teclado.clean();
		teclado.addKey(Keyboard.VK_ENTER);// entra na seleção
		teclado.addKey(Keyboard.VK_UP);// sobe uma seleção
		teclado.addKey(Keyboard.VK_DOWN);// desce uma seleção

		teclado.addKey(Keyboard.VK_1);// play
		teclado.addKey(Keyboard.VK_2);// pause
		teclado.addKey(Keyboard.VK_3);// stop
		teclado.addKey(Keyboard.VK_4);// vol +
		teclado.addKey(Keyboard.VK_5);// vol -

		String[] textos = new String[] { "Start Game", "Créditos", "Sair" };
		Point[] locais = new Point[] { new Point(500, 350), new Point(500, 400), new Point(500, 450) };

		selection = new MenuVertical(textos, locais);
		musica = new Sound("som.wav");
		musica.setLoop(true);
		musica.play();
	}

	@Override
	public void control() {

		// CONTROLE SOM

		// Play
		if (teclado.conpareKey(Keyboard.VK_1)) {
			musica.play();
			System.out.println("play");
		}
		// pause
		if (teclado.conpareKey(Keyboard.VK_2)) {
			musica.pause();
			System.out.println("pause");

		}
		// stop
		if (teclado.conpareKey(Keyboard.VK_3)) {// Diminue o volume
			musica.stop();
			System.out.println("stop");
		}
		// vol +
		if (teclado.conpareKey(Keyboard.VK_4)) {// Diminue o volume
			musica.decreaseVolume(1);
			System.out.println("volume -");
		}
		// vol -
		if (teclado.conpareKey(Keyboard.VK_5)) {// Aumenta o volume
			musica.increaseVolume(1);
			System.out.println("volume +");
		}

		// CONTROLE MENU
		if (teclado.conpareKey(Keyboard.VK_UP)) {// encerra o aplicativo
			selection.up();
		}
		if (teclado.conpareKey(Keyboard.VK_DOWN)) {// encerra o aplicativo
			selection.down();
		}
		if (teclado.conpareKey(Keyboard.VK_ENTER)) {// encerra o aplicativo
			switch (selection.currentSelection) {
			case 0: // iniciar jogo
				_MainGame.controleJanela = new Cena();
				break;
			case 1: // créditos
				_MainGame.controleJanela = new MenuCreditos();
				break;
			case 2: // sair
				_MainGame.loop = false;
				break;
			default:
				System.out.println("Escolha do menu inicial invalida: " + selection.currentSelection);
			}
		}
	}

	@Override
	public void draw() {
		int x = 15;
		int y = 25;
		int desloc = 15;
		backGound.draw();
		janela.write(x, y += desloc, Color.yellow, "* * * * * * CONTROLE MENU * * * * * *");
		janela.write(x, y += desloc, Color.yellow, " - Ceta para cima: Sobe a seleção");
		janela.write(x, y += desloc, Color.yellow, " - Ceta para baixo: Desce a seleção");
		janela.write(x, y += desloc, Color.yellow, " - Enter: Faz a escolha da seleção");
		y += desloc;
		janela.write(x, y += desloc, Color.yellow, "* * * * * * CONTROLE SOM * * * * * *");
		janela.write(x, y += desloc, Color.yellow, " - 1: Play");
		janela.write(x, y += desloc, Color.yellow, " - 2: Pause");
		janela.write(x, y += desloc, Color.yellow, " - 3: Stop");
		janela.write(x, y += desloc, Color.yellow, " - 4: Vol -");
		janela.write(x, y += desloc, Color.yellow, " - 5: Vol +");

		selection.draw();
	}

}
