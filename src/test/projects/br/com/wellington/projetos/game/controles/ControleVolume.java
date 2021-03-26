package br.com.wellington.projetos.game.controles;

public class ControleVolume {

	private static final int VOLUME_MAX = 5;
	private static final int VOLUME_MIN = 1;
	private static final int VOLUME_INI = 4;

	private static int volumeLimiteMax = 0;
	private static int volumeCorrente = VOLUME_INI;
	private static int volumeBase = 0;

	public static void setMaxVolume(int volume) {
		volumeLimiteMax = volume;
		volumeBase = volumeLimiteMax / VOLUME_MAX;
	}

	public static int getVolume() {
		return volumeBase * volumeCorrente;
	}

	public static int aumentaVolume() {
		if (volumeCorrente < VOLUME_MAX) {
			volumeCorrente++;
		}
		return volumeBase * volumeCorrente;
	}

	public static int diminueVolume() {
		if (volumeCorrente > VOLUME_MIN) {
			volumeCorrente--;
		}
		return volumeBase * volumeCorrente;
	}

	public static int getVolumeCorrente() {
		return volumeCorrente;
	}

}
