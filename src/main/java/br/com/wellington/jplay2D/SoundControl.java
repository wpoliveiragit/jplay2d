package br.com.wellington.jplay2D;

import java.util.List;

public class SoundControl {

	/** Quantidade de barras de volume. */
	private static final int VOLUME_BAR = 5;
	/** Volume corrente. */
	private static int VOLUME = 2;

	private static List<String> LIST_MUSICS;

	/** Aumenta a barra de volume em 1. */
	public static void addVolume() {
		if (VOLUME < VOLUME_BAR)
			VOLUME += 1;
	}

	/** Diminue a barra de volume em 1 */
	public static void subVolume() {
		if (VOLUME > 0)

			VOLUME -= 1;
	}

	/**
	 * Retorna o volume geral do jogo.
	 * 
	 * @return O volume geral do jogo.
	 */
	public static int getCurrentVolume() {
		return VOLUME;
	}

	/**
	 * Retorna o tamanho do volume máximo.
	 * 
	 * @return O tamanho do volume máximo.
	 */
	public static final int getVolumeBar() {
		return VOLUME_BAR;
	}

}
