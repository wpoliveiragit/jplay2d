package projetos.jogoDaMemoria;

import br.com.wellington.jplay2D.window.Window;

public abstract class GameControl {

	/**
	 * Referencia do controle da tela do game.
	 * 
	 * @see Window
	 */
	protected static Window WINDOW = null;

	private boolean loop;
	private int delay;

	static {
		WINDOW = Window.getInstance();
	}

	public GameControl(int delay) {
		this.delay = delay;
		loop = false;

	}

	abstract protected void collisionCheck();

	/** Executado antes de iniciar o loop do método start. */
	abstract protected void init();

	/** Define a sequencia de atualização dos objetos. */
	abstract protected void draw();

	/** guarda o controle da captura do keyboar e do mouse. */
	abstract protected void control();

	/** Executado após ser chamado o método exit. */
	abstract protected void end();

	public final void start() {
		if (loop) {
			return;
		}
		init();
		loop = true;
		while (loop) {
			collisionCheck();
			draw();
			WINDOW.update();
			control();
			WINDOW.delay(delay);
		}
		end();
	}

	public final void exist() {
		loop = false;
	}

}
