package _br.com.wellington.jplay2D.projetos.investigador;

import br.com.wellington.jplay2D.oi.Keyboard;
import br.com.wellington.jplay2D.scene.Scene;
import br.com.wellington.jplay2D.window.Window;

/**
 * Definição do que a classe representa ou administra
 *
 * @author Wellington Pires de Oliveira.
 * @date 04/05/2019
 * @path Jogo01.Jogo.Cenario01
 */
public class Cenario01 implements Constantes {

	private final Window janela;
	private final Scene cena; // define o arquivo que move o cenário
	private final Jogador jogador;
	private final Keyboard teclado;
	private final Zumbi zumbi;

	public Cenario01(Window janela) {
		this.janela = janela;
		cena = new Scene();
		cena.loadFromFile(SCN_CENARIO);
		jogador = new Jogador(640, 350, janela, cena);

		teclado = janela.getKeyboard();
		zumbi = new Zumbi(50, 400);

		run();
	}

	private void run() {

		while (true) {
			// cena.draw();//atualiza para o personagem andar livremente no mapa com a ideia
			// de camera parada
			jogador.controle();
			jogador.caminho(cena);
			zumbi.caminho(cena);
			zumbi.perseguir(jogador.x, jogador.y);
			cena.moveScene(jogador);// prende o personagem ao centro da tela ao se movimentar no mapa

			jogador.x += cena.getXOffset();
			jogador.y += cena.getYOffset();

			jogador.atirar();

			zumbi.x += cena.getXOffset();
			zumbi.y += cena.getYOffset();

			jogador.draw();
			zumbi.draw();
			janela.update();

			if (teclado.keyDown(Keyboard.ESCAPE_KEY)) {
				janela.exit();
			}
			janela.delay(4);
		}
	}

}
