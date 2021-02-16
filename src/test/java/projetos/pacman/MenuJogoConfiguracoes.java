package projetos.pacman;

import java.awt.Color;
import java.awt.Point;
import java.util.Arrays;

import jplay.ConstantsIO;
import jplay.Sound_Original;

public class MenuJogoConfiguracoes extends MenuJogo implements ConstantsIO {

	private static String txtVoltar = "VOLTAR (Esc ou Enter)";

	public MenuJogoConfiguracoes(JogoInteracao jogoInteracao) {
		super(3, jogoInteracao);
		jogoInteracao.getTeclado().inicializa(Arrays.asList(VK_ENTER, VK_ESCAPE), null, this);
		setMenuImagem(PATH_IMAGEM_JOGO_CONFIGURACOES);
	}

	@Override
	public void controle() {
		if (jogoInteracao.getTeclado().isTecla(VK_ESCAPE) || jogoInteracao.getTeclado().isTecla(VK_ENTER)) {
			//Sound.startEffect("effect02.wav");
			MenuJogoInicial menu = new MenuJogoInicial(jogoInteracao);
			menu.avanca();
			return;
		}
	}

	@Override
	public void desenha() {
		super.desenha();
		jogoInteracao.escreve(new Point(180, 400), FONTE_COMIC, Color.blue, txtVoltar);
	}

	@Override
	public void atualizaMenuDesenho(int index) {
		switch (index) {
		case 1:
			super.iconeMenu.move(180 - iconeMenu.getSprite().getWidth(), 400 - iconeMenu.getSprite().getHeight());
			break;
		}
	}

}
