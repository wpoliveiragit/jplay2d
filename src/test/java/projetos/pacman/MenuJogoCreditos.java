package projetos.pacman;

import java.awt.Color;
import java.awt.Point;
import java.util.Arrays;

import jplay.ConstantsIO;
import jplay.Sound_Original;

public class MenuJogoCreditos extends MenuJogo implements ConstantsIO {

	private static final String nome = "Wellington Pires de Oliveira";

	private static final String email = "wp_oliveira@yahoo.com.br";

	private static final String txtVoltar = "VOLTAR (Esc ou Enter)";

	private Sound_Original musica;

	public MenuJogoCreditos(JogoInteracao jogoInteracao) {
		super(1, jogoInteracao);
		jogoInteracao.getTeclado().inicializa(Arrays.asList(VK_RIGHT, VK_LEFT, VK_ENTER, VK_ESCAPE), null, this);
		setMenuImagem(PATH_IMAGEM_JOGO_CREDITOS);
		musica = new Sound_Original("musica.wav");
		musica.play();
	}

	@Override
	public void controle() {
		if (jogoInteracao.getTeclado().isTecla(VK_ESCAPE) || jogoInteracao.getTeclado().isTecla(VK_ENTER)) {
			//Sound.startEffect("effect02.wav");
			MenuJogoInicial menu = new MenuJogoInicial(jogoInteracao);
			musica.stop();
			menu.avanca();
			menu.avanca();
			return;
		}
	}

	@Override
	public void desenha() {
		super.desenha();
		jogoInteracao.escreve(new Point(50, 50), FONTE_COMIC, Color.yellow, nome);
		jogoInteracao.escreve(new Point(50, 100), FONTE_COMIC, Color.yellow, email);
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
