package br.com.wellington.projetos.game.menus;

import java.awt.Color;
import java.awt.event.KeyEvent;

import br.com.wellington.jplay2D.window.TextWindow;
import br.com.wellington.projetos.game.utils.Constants;
import projetos.jogoDaMemoria.GameControl;

// Musica
// voltar para menu anterior
// e-mail
// nome completo
// agradecimentos
// proposta do projeto
public class TelaCredits extends GameControl {

	private TextWindow voltar;

	public TelaCredits() {
		super(30);

	}

	@Override
	protected void update() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void beforeStart() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void load() {
		KEYBOARD.cleanKeys();
		KEYBOARD.addKeyBehaviorActuatorRequest(KeyEvent.VK_ENTER);
		int x = WINDOW.getJFrame().getWidth() / 2;
		int y = WINDOW.getJFrame().getHeight() / 2;
		voltar = new TextWindow(Color.white, Constants.FIXEDSYS_40, "VOLTAR");
		voltar.x = x - voltar.getWidth();
		voltar.y = y + 2 * voltar.getHeight();
	}

	@Override
	protected void draw() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void control() {
		if (KEYBOARD.checkKey(KeyEvent.VK_ENTER)) {// [VOLTA AO MENU ANTERIOR]
			super.exist();
		}
	}

	@Override
	protected void afterStart() {
		// TODO Auto-generated method stub

	}

}
