package projetos.pacman;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import jplay.Keyboard;
import jplay.Window;

/**
 * Classe de controle do teclado.
 * 
 * @author Wellington Pires de Oliveira
 */
public final class Teclado implements TecladoInteracao {

	public static final int DGT_1 = KeyEvent.VK_1;
	public static final int DGT_2 = KeyEvent.VK_2;
	public static final int DGT_3 = KeyEvent.VK_3;
	public static final int DGT_4 = KeyEvent.VK_4;
	public static final int DGT_5 = KeyEvent.VK_5;
	public static final int DGT_6 = KeyEvent.VK_6;

	/**
	 * Entrada dos eventos do jogo via keyboard.
	 */
	private final Keyboard teclado;

	/**
	 * Lista das chaves adicionadas no teclado.
	 */
	private List<Integer> listaChave;

	/**
	 * Controle dos eventos do teclado.
	 */
	private TecladoControle controle;

	/**
	 * CONSTRUTOR do controle dos eventos do teclado
	 * 
	 * @param teclado objeto de acao do teclado.
	 */
	public Teclado() {
		teclado = Window.getInstance().getKeyboard();
		listaChave = new ArrayList<Integer>();
	}

	/**
	 * limpa todas as chaves adicionadas.
	 */
	private void limpaChaves() {
		listaChave.forEach(chave -> {
			teclado.removeKey(chave);
		});
		listaChave.clear();
	}

	/**
	 * Adiciona uma lista de chaves de evento digito.
	 * 
	 * @param chaves Lista de chaves.
	 */
	private void addChavesDigito(List<Integer> chaves) {
		if (chaves == null) {
			return;
		}
		chaves.forEach(chave -> {
			teclado.addKey(chave);
			listaChave.add(chave);
		});
	}

	/**
	 * Adiciona uma chave de evento de movimento.
	 * 
	 * @param key o codigo.
	 */
	private void addChavesMovimento(List<Integer> chaves) {
		if (chaves == null) {
			return;
		}
		chaves.forEach(chave -> {
			teclado.addKeyPress(chave);
			listaChave.add(chave);
		});
	}

	@Override
	public void inicializa(List<Integer> chavesDigito, List<Integer> chavesMovimento, TecladoControle controle) {
		limpaChaves();
		addChavesDigito(chavesDigito);
		addChavesMovimento(chavesMovimento);
		this.controle = controle;
	}

	@Override
	public final boolean isTecla(int chave) {
		return teclado.conpareKey(chave);
	}

	@Override
	public void controle() {
		controle.controle();
	}

}
