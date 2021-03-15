package projetos.lancando;

import projetos.utils.Constantes;

public class Main {

	public static final String BACKDROP;
	public static final String TILE_BLOCO;
	public static final String GROUND;
	public static final String SPRITE;
	public static final String LIMITE;

	static {
		BACKDROP = Constantes.PATH_LANCANDO + "/fundo.png";
		TILE_BLOCO = Constantes.PATH_LANCANDO + "/square.png";
		GROUND = Constantes.PATH_LANCANDO + "/ground.png";
		SPRITE = Constantes.PATH_LANCANDO + "/sprite.png";
		LIMITE = Constantes.PATH_LANCANDO + "/limite.png";
	}

	public static void main(String[] args) {
		new Lancando(800, 600).start();
	}
}
