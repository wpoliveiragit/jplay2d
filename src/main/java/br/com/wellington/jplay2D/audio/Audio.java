package br.com.wellington.jplay2D.audio;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine.Info;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

import br.com.wellington.jplay2D.exception.Jplay2DRuntimeException;

/** Controle da execução de som. */
public class Audio {

	/** Indica o formato do audio */
	private AudioFormat audioFormat;
	/** Acesso ao arquivo do audio. */
	private AudioInputStream audioIn;
	/** Controle de execução do audio */
	private SourceDataLine sourceDataLine;
	/** Indica se o audio foi encerrado ou não. */
	private boolean stop;
	/** Indica se o audio esta congelado. */
	private boolean pause;
	/** Volume corrente do audio. */
	private float volume;
	/** Indicka se o volume foi alterado. */
	private boolean volumeChanged;
	/** O som que será executado. */
	private Song song;
	/** Indica se o audio ficará se repetindo. */
	private boolean loop;
	/** Caminho absoluto do arquivo. */
	private String pathName;
	/** Controle de volume. */
	private FloatControl volumeCtrl;

	/**
	 * Cria um executor de sons.
	 * 
	 * @param pathName Caminho absoluto do arquivo.
	 */
	public Audio(String pathName) {
		load(pathName);
	}

	/**
	 * Carrega um novo audio onde ele será executado apenasuma vez.
	 * 
	 * @param pathName Caminho absoluto do arquivo.
	 */
	private void load(String pathName) {
		loop = false;
		this.pathName = pathName;
		try {
			audioIn = AudioSystem.getAudioInputStream(new File(pathName));
			audioFormat = audioIn.getFormat();
			Info dataLineInfo = new Info(SourceDataLine.class, audioFormat);
			sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
			sourceDataLine.open(audioFormat);
			volumeCtrl = (FloatControl) sourceDataLine.getControl(FloatControl.Type.MASTER_GAIN);
		} catch (UnsupportedAudioFileException ex) {
			throw new Jplay2DRuntimeException(//
					new StringBuilder("[ERRO] {br.com.wellington.jplay2D.audio.load(String)}")
							.append(" -Arquivo corrompido {").append(pathName).append("}").toString(),
					ex);
		} catch (IOException ex) {
			throw new Jplay2DRuntimeException(//
					new StringBuilder("[ERRO] {br.com.wellington.jplay2D.audio.load(String)}")
							.append(" -Problema na I/O. Não foi possivel carregar o arquivo {").append(pathName)
							.append("}").toString(),
					ex);
		} catch (LineUnavailableException ex) {
			throw new Jplay2DRuntimeException(//
					new StringBuilder("[ERRO] {br.com.wellington.jplay2D.audio.load(String)}")
							.append(" -O arquivo não tem permição de leitura {").append(pathName).append("}")
							.toString(),
					ex);
		}

	}

	/** Inicia a execução do audio. */
	public void play() {
		if (pause) {
			pause = false;
		} else {
			song = new Song();
			song.start();
		}
	}

	/** Para a execução do audio. */
	public void stop() {
		stop = true;
		song = null;
	}

	/** Pausa a execução do audio. */
	public void pause() {
		pause = true;
	}

	/**
	 * Aumenta o volume.
	 * 
	 * @param value Adiciona o valor ao volume atual.
	 */
	public void increaseVolume(float value) {
		volume += value;
		volumeChanged = true;
	}

	/**
	 * Reduz o volume.
	 * 
	 * @param value Reduz o valor do volume atual.
	 */
	public void decreaseVolume(float value) {
		volume -= value;
		volumeChanged = true;
	}

	/**
	 * Define um novo valor ao volume.
	 * 
	 * @param value O novo valor a ser definido ao volume.
	 */
	public void setVolume(float value) {
		volume = value;
		volumeChanged = true;
	}

	/**
	 * Define se o audio ficará se repetindo ou não.
	 * 
	 * @param loop true: O audio ficará repetindo.
	 */
	public void setLoop(boolean loop) {
		this.loop = loop;
	}

	/**
	 * Retorna true se o audio estiver em execução.
	 * 
	 * @return true se o audio estiver em execução.
	 */
	public boolean isExecuting() {
		return song != null;
	}

	/**
	 * Retorna o valor mínimo que o volume pode assumir.
	 * 
	 * @return O valor mínimo que o volume pode assumir.
	 */
	public float getMinimumVolume() {
		return volumeCtrl.getMinimum();
	}

	/**
	 * Retorna o valor corrente do volume.
	 * 
	 * @return O valor corrente do volume.
	 */
	public float getVolumeCurrent() {
		return volumeCtrl.getValue();
	}

	/**
	 * Retorna o valor máximo que o volume pode assumir.
	 * 
	 * @return O valor máximo que o volume pode assumir.
	 */
	public float getVolumeMax() {
		return volumeCtrl.getMaximum();
	}

	/** Classe responsavel pelo controle da execução do audio. */
	private class Song extends Thread {
		static final int LENGTH = 1000;

		@Override
		public void run() {
			try {
				byte bfr[] = new byte[LENGTH];
				sourceDataLine.start();
				int count = 0;
				do {
					if (stop) {// [ENCERRA O AUDIO]
						break;
					}
					if (pause) {// [CONGELA O AUDIO]
						continue;
					}
					count = audioIn.read(bfr, 0, LENGTH);
					if (count > 0) {
						if (volumeChanged) {// [VERIFICAÇÃO DE ALTERAÇÃO DE VOLUME]
							volumeCtrl.setValue(volume);
							volumeChanged = false;
						}
						sourceDataLine.write(bfr, 0, count);// [executa o treixo do audio coletado]
					}
				} while (count > -1);
				sourceDataLine.drain();
				sourceDataLine.close();
				if (loop && !stop) {// arrumar este stop
					load(pathName);
					setVolume(volume);
					song = new Song();
					song.start();
				}
				stop = false;
				song = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
