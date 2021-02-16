package projetos.pacman;

import java.util.Arrays;

import jplay.ConstantsIO;
import jplay.Sound_Original;

public class MenuJogoInicial extends MenuJogo implements ConstantsIO {

	/**
	 * CONSTRUTOR do menu inicial.
	 * 
	 * @param jogoInteracao interacao do jogo.
	 */
	public MenuJogoInicial(JogoInteracao jogoInteracao) {
		super(3, jogoInteracao);
		jogoInteracao.getTeclado().inicializa(Arrays.asList(VK_RIGHT, VK_LEFT, VK_ENTER, VK_ESCAPE), null, this);
	}

	@Override
	public void controle() {
		if (jogoInteracao.getTeclado().isTecla(VK_ESCAPE)) { // botao esc
			jogoInteracao.finaliza();
			return;
		}
		if (jogoInteracao.getTeclado().isTecla(VK_ENTER)) { // Escolha da selecao
			//Sound.startEffect("effect01.wav");
			if (getPosicao() == 1) {// inicia jogo
				new Cena_0001(jogoInteracao);
				return;
			}
			if (getPosicao() == 2) {// carrega o menu de configuracoes do jogo
				new MenuJogoConfiguracoes(jogoInteracao);
				// criar bot�o voltar
				return;
			}
			if (getPosicao() == 3) {// carrega os creditos do jogo
				new MenuJogoCreditos(jogoInteracao);
				// criar bot�o voltar
				return;
			}
			return;
		}
		if (jogoInteracao.getTeclado().isTecla(VK_LEFT)) {// vira ou anda pra esquerda
			volta();
			return;
		}
		if (jogoInteracao.getTeclado().isTecla(VK_RIGHT)) {// vira ou anda pra direita
			avanca();
			return;
		}
	}

	@Override
	public void atualizaMenuDesenho(int index) {
		switch (index) {
		case 1:
			iconeMenu.move(JANELA_MENU_INICIAL_PONTO_OP_A.x, JANELA_MENU_INICIAL_PONTO_OP_A.y);
			break;
		case 2:
			iconeMenu.move(JANELA_MENU_INICIAL_PONTO_OP_B.x, JANELA_MENU_INICIAL_PONTO_OP_B.y);
			break;
		case 3:
			iconeMenu.move(JANELA_MENU_INICIAL_PONTO_OP_C.x, JANELA_MENU_INICIAL_PONTO_OP_C.y);
			break;
		}
	}

}
