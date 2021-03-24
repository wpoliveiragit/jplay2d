package projetos.jogoDaMemoria;

import br.com.wellington.jplay2D.oi.Keyboard;
import br.com.wellington.jplay2D.oi.Mouse;
import br.com.wellington.jplay2D.window.Window;

public abstract class GameControl {

	/**
	 * Referencia do controle da tela do game.
	 * 
	 * @see Window
	 */
	protected static Window WINDOW = null;
	protected static Keyboard KEYBOARD;
	protected static Mouse MOUSE;

	private boolean loop;
	private int delay;

	static {
		WINDOW = Window.getInstance();
		KEYBOARD = WINDOW.getKeyboard();
		MOUSE = WINDOW.getMouse();
	}

	public GameControl(int delay) {
		this.delay = delay;
		loop = false;
		loadResources();

	}

	/** Método de atualização da cena */
	abstract protected void updateScene();

	/** Executado antes de iniciar o loop do método start. */
	abstract protected void beforeStart();

	abstract protected void loadResources();

	/** Define a sequencia de atualização dos objetos. */
	abstract protected void draw();

	/** guarda o controle da captura do keyboar e do mouse. */
	abstract protected void control();

	/** Executado após ser chamado o método exit. */
	abstract protected void afterStart();

	public final void start() {
		if (loop) {
			return;
		}
		beforeStart();
		loop = true;
		while (loop) {
			updateScene();
			draw();
			WINDOW.update();
			control();
			WINDOW.delay(delay);
		}
		afterStart();
	}

	public final void exist() {
		loop = false;
	}

}
