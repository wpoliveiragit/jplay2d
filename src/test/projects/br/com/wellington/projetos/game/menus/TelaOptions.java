package br.com.wellington.projetos.game.menus;

import java.awt.Color;
import java.awt.event.KeyEvent;

import br.com.wellington.jplay2D.audio.Audio;
import br.com.wellington.jplay2D.image.GameImage;
import br.com.wellington.jplay2D.window.TextWindow;
import br.com.wellington.projetos.game.controles.ControleVolume;
import br.com.wellington.projetos.game.utils.Constants;
import br.com.wellington.projetos.game.utils.VerticalOptionsMenu;
import projetos.jogoDaMemoria.GameControl;

// musica
// Barulho de troca de escolha
// barulho de escolha
// botão '+' Aumenta o volume
// botão '-' baixa o volume
// Barulho de alteração de volume
// voltar as configurações padroes
// som de volta as configura ções padroes
// volta para o menu anterior
// persistir as informações
public class TelaOptions extends GameControl {

	private VerticalOptionsMenu menu;

	private GameImage logoJogo;
	private Audio musica;

	private TextWindow volume;
	private TextWindow valorVolume;
	private TextWindow voltar;

	public TelaOptions() {
		super(30);

	}

	@Override
	protected void update() {

	}

	@Override
	protected void beforeStart() {

	}

	@Override
	protected void load() {
		MOUSE.setCursorImage("");

		KEYBOARD.cleanKeys();
		KEYBOARD.addKeyBehaviorActuatorRequest(KeyEvent.VK_ENTER);
		KEYBOARD.addKeyBehaviorActuatorRequest(KeyEvent.VK_UP);
		KEYBOARD.addKeyBehaviorActuatorRequest(KeyEvent.VK_DOWN);
		KEYBOARD.addKeyBehaviorActuatorRequest(KeyEvent.VK_LEFT);
		KEYBOARD.addKeyBehaviorActuatorRequest(KeyEvent.VK_RIGHT);

		logoJogo = new GameImage(Constants.IMG_LOGO_JOGO);
		logoJogo.x = WINDOW.getJFrame().getWidth() - logoJogo.width - 20;
		;
		logoJogo.y = 20;

		int x = WINDOW.getJFrame().getWidth() / 2;
		int y = WINDOW.getJFrame().getHeight() / 2;

		volume = new TextWindow(Color.yellow, Constants.FIXEDSYS_40, "VOLUME");
		volume.x = x - volume.getWidth();
		volume.y = y;

		voltar = new TextWindow(Color.white, Constants.FIXEDSYS_40, "VOLTAR");
		voltar.x = x - voltar.getWidth();
		voltar.y = y + 2 * voltar.getHeight();

		valorVolume = new TextWindow(Color.yellow, Constants.FIXEDSYS_40, "" + ControleVolume.getVolumeCorrente());
		valorVolume.x = x + 20;
		valorVolume.y = y;

		// [GERAL]
		menu = new VerticalOptionsMenu(2);
		musica = new Audio(Constants.SND_ENTRADA_MEGAMAN);
		musica.setLoop(true);
		ControleVolume.setMaxVolume((int) -musica.getMinimumVolume());
		musica.setVolume(musica.getMinimumVolume() + ControleVolume.getVolume());
		musica.play();
	}

	@Override
	protected void draw() {
		WINDOW.clear(Color.black);
		logoJogo.draw();
		volume.draw();
		valorVolume.draw();
		voltar.draw();
	}

	@Override
	protected void control() {
		if (KEYBOARD.checkKey(KeyEvent.VK_ENTER)) {// [EXECUTAR SELEÇÃO]
			switch (menu.getSelecao()) {
			case 1:// [voltar]
				super.exist();
				break;
			}
			return;
		}
		if (KEYBOARD.checkKey(KeyEvent.VK_UP)) {// [SOBE]
			menu.sobe();
			ajustaSelecao();
			return;
		} else if (KEYBOARD.checkKey(KeyEvent.VK_DOWN)) {// [DESCE]
			menu.desce();
			ajustaSelecao();
			return;
		} else if (KEYBOARD.checkKey(KeyEvent.VK_LEFT)) {// [altera opção]
			switch (menu.getSelecao()) {
			case 0:// volume
				musica.setVolume(musica.getMinimumVolume() + ControleVolume.diminueVolume());
				valorVolume.setTxt("" + ControleVolume.getVolumeCorrente());
				break;
			}
			return;
		} else if (KEYBOARD.checkKey(KeyEvent.VK_RIGHT)) {// [altera opção]
			switch (menu.getSelecao()) {
			case 0:// volume
				musica.setVolume(musica.getMinimumVolume() + ControleVolume.aumentaVolume());
				valorVolume.setTxt("" + ControleVolume.getVolumeCorrente());
				break;
			}
			return;
		}

	}

	@Override
	protected void afterStart() {
		musica.stop();
	}

	private void ajustaSelecao() {
		switch (menu.getSelecao()) {
		case 0:
			volume.setColor(Constants.COR_ON);
			voltar.setColor(Constants.COR_OFF);
			break;
		case 1:
			volume.setColor(Constants.COR_OFF);
			voltar.setColor(Constants.COR_ON);
			break;
		}
		new Audio(Constants.SND_SELECAO).play();
	}

}
