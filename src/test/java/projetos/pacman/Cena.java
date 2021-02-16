package projetos.pacman;

import java.util.ArrayList;
import java.util.List;

import jplay.Scene;
import jplay.SpritePlatform;

public abstract class Cena implements Desenho, TecladoControle {

	/**
	 * O cenario do jogo.
	 */
	protected Scene cena;

	/**
	 * 
	 */
	protected List<Personagem> listaNpc;

	/**
	 * CONSTRUTOR base de um cenario.
	 * 
	 * @param pathScn       O caminho do arquivo do cenario
	 * @param jogoInteracao O controle do jogo.
	 */
	public Cena(String pathScn, JogoInteracao jogoInteracao, List<Integer> chavesDigito,
			List<Integer> chavesMovimento) {
		cena = new Scene(pathScn);
		cena.changeTile(0, 0, 2);
		cena.saveToFile("teste.scn");// grava o arquivo carregado no rais do projeto
		cena.loadScene("teste.scn");// carrega o arquivo gravado

		jogoInteracao.setDesenho(this);
		jogoInteracao.getTeclado().inicializa(chavesDigito, chavesMovimento, this);
		listaNpc = new ArrayList<>();
	}

	protected final void addPersonagemMapa(Personagem personagem) {
		/**
		 * Criar uma fila de adi��o de personagem.
		 */

		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(500);
					listaNpc.add(personagem);
					cena.addOverlay(personagem.getSprite());
				} catch (Exception ex) {

				}
			}
		}).start();

	}

	@Override
	public void atualiza() {

	}

	@Override
	public void desenha() {

	}

	@Override
	public void controle() {

	}

	/**
	 * Corrige a sprite na janela caso o cenario se desloque.
	 * 
	 * @param sprite a sprite.
	 */
	protected void corrigeDeslocamentoCenario(SpritePlatform sprite) {
		sprite.setX(sprite.getX() + cena.getXOffset());
		sprite.setY(sprite.getY()+cena.getYOffset()); 
	}

}
