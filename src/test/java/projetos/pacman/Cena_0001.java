package projetos.pacman;

import java.util.Arrays;
import java.util.List;

import jplay.ConstantsIO;
import jplay.Mouse;
import jplay.Sound_Original;
import jplay.Window;

/**
 * 
 * @author Wellington Pires de Oliveira.
 * @date 05/24/2019
 */
public class Cena_0001 extends Cena implements ConstantsIO {

	/** Lista de digitos da cena */
	private static final List<Integer> LISTA_DIGITOS;
	/** Lista de digitos de pressão */
	private static final List<Integer> LISTA_PRESS;

	static {// inicializa os digitos que o cenário irá utilizar
		LISTA_DIGITOS = Arrays.asList(VK_ESCAPE, VK_SPACE);
		LISTA_PRESS = Arrays.asList(VK_UP, VK_DOWN, VK_LEFT, VK_RIGHT, VK_ADD, VK_SUBTRACT);
	}

	/** O personagem que o jogador ira controlar */
	private Personagem jogador;

	/** Iteracao com o jogo. */
	private JogoInteracao jogoInteracao;

	/** O controle de som do jogo */
	private Sound_Original som;

	/**
	 * CONSTRUTOR do cenario.
	 * 
	 * @param jogoInteracao o controle do jogo
	 */
	public Cena_0001(JogoInteracao jogoInteracao) {
		super(ConstantesPath.PATH_SCN_CENARIO_0001, jogoInteracao, LISTA_DIGITOS, LISTA_PRESS);

		this.jogoInteracao = jogoInteracao;

		// adiciona o personagens ao desenho do mapa
		cena.addOverlay((jogador = new Jogador(cena, 2, 2)).getSprite());

		// adiciona um fantasma pra teste no cenario
		addPersonagemMapa(new FantasmaLaranja(cena, 7, 5));

		som = new Sound_Original("som.wav");
		som.setRepeat(true);
		som.play();
	}

	@Override
	public void atualiza() {
		for (Personagem p : listaNpc) {
			p.atualiza();
		}
	}

	@Override
	public void desenha() {
		cena.drawMoveScene(jogador.getSprite());
		corrigeDeslocamentoCenario(jogador.getSprite());
		for (Personagem p : listaNpc) {
			corrigeDeslocamentoCenario(p.getSprite());
		}

	}

	@Override
	public void controle() {
		{// mouse
			Mouse mouse = Window.getInstance().getMouse();
			if (mouse.isOverObject(jogador.getSprite())) {
				System.out.println("mouse no pacman");
			}

			if (mouse.isLeftButtonPressed()) {
				som.play();
				System.out.println("botao mouse esquerdo");
			}

			if (mouse.isMiddleButtonPressed()) {
				System.out.println("Botao mouse meio");
			}

			if (mouse.isRightButtonPressed()) {
				System.out.println("botao mouse direito");
				som.pause();
			}
		}

		{// TECLADO
			if (jogoInteracao.getTeclado().isTecla(VK_ESCAPE)) {// voltar ao menu inicial
				//Sound.startEffect("effect02.wav");
				som.stop();
				new MenuJogoInicial(jogoInteracao);
				return;
			}
			if (jogoInteracao.getTeclado().isTecla(VK_UP)) {// andar pra cima
				jogador.passoCima();
				jogador.atualiza();
			} else if (jogoInteracao.getTeclado().isTecla(VK_DOWN)) {// andar pra baixo
				jogador.passoBaixo();
				jogador.atualiza();
			} else if (jogoInteracao.getTeclado().isTecla(VK_LEFT)) {// andar pra esquerda
				jogador.passoEsquerda();
				jogador.atualiza();
			} else if (jogoInteracao.getTeclado().isTecla(VK_RIGHT)) {// andar pra direita
				jogador.passoDireita();
				jogador.atualiza();
			} else if (jogoInteracao.getTeclado().isTecla(VK_ADD)) {// aumentar volume
				///som.increasesVolume();

			} else if (jogoInteracao.getTeclado().isTecla(VK_SUBTRACT)) {// baixar volume
				//som.decreaseVolume();

			} else if (jogoInteracao.getTeclado().isTecla(VK_SPACE)) {

			}
		}
	}

}
