package br.com.wellington.jplay2D.sound;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

/** controla a execução dos sons. */
public class Sound {
	private AudioFormat audioFormat;
	private AudioInputStream audioInputStream;
	private SourceDataLine sourceDataLine;
	private boolean stop;
	private boolean pause;
	private float volume;
	private boolean volumeChanged;
	private Song song;
	private boolean loop;
	private String fileName;

	/**
	 * Crie uma instância desta classe. O som será executado apenas uma vez.
	 * 
	 * @param fileName
	 */
	public Sound(String fileName) {
		loop = false;
		this.fileName = fileName;
		load(fileName);
	}

	/**
	 * Carrega um som.
	 * 
	 * @param fileName File path.
	 */
	public void load(String fileName) {
		try {
			audioInputStream = AudioSystem.getAudioInputStream(new File(fileName));
		} catch (UnsupportedAudioFileException ex) {
			Logger.getLogger(Sound.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(Sound.class.getName()).log(Level.SEVERE, null, ex);
		}

		audioFormat = audioInputStream.getFormat();
		DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, audioFormat);

		try {
			sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
		} catch (LineUnavailableException ex) {
			Logger.getLogger(Sound.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	/**
	 * Aumenta o volume.
	 * 
	 * @param value Este valor será adicionado ao valor do volume atual.
	 */
	public void increaseVolume(float value) {
		volume += value;
		volumeChanged = true;
	}

	/**
	 * diminui o volume.
	 * 
	 * @param value Este valor será subtraído do valor de volume atual.
	 */
	public void decreaseVolume(float value) {
		volume -= value;
		volumeChanged = true;
	}

	/**
	 * Define o volume atual.
	 * 
	 * @param value
	 */
	public void setVolume(float value) {
		volume = value;
		volumeChanged = true;
	}

	/**
	 * Inicia a execução do som.
	 */
	public void play() {
		if (pause == false) {
			song = new Song();
			song.start();
		} else
			// if (pause == true)
			pause = false;
	}

	/**
	 * Pára a execução do som.
	 */
	public void stop() {
		stop = true;
		if (song == null)
			song = null;
	}

	/**
	 * Pausa a execução do som.
	 */
	public void pause() {
		pause = true;
	}

	private class Song extends Thread {
		byte tempBuffer[] = new byte[1000];

		@Override
		public void run() {
			try {
				sourceDataLine.open(audioFormat);
				sourceDataLine.start();

				// enquanto houver datas para executar e parar == false
				int count = 0;
				while (count != -1 && stop == false) {
					if (pause == false)
						count = audioInputStream.read(tempBuffer, 0, tempBuffer.length);
					else
						count = 0;

					// Se houver datas para executar
					if (count > 0) {
						if (volumeChanged) {
							FloatControl volControl = (FloatControl) sourceDataLine
									.getControl(FloatControl.Type.MASTER_GAIN);
							volControl.setValue(volume);
							volumeChanged = false;
						}
						sourceDataLine.write(tempBuffer, 0, count);
					}
				}

				sourceDataLine.drain();
				sourceDataLine.close();

				if (loop && stop == false) {
					load(fileName);
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

	/**
	 * Este método é responsável por fazer com que o som seja executado indefinidamente.
	 * 
	 * @param value
	 */
	public void setRepeat(boolean value) {
		loop = value;
	}

	/**
	 * Retorna verdadeiro se o som está rodando, falso caso contrário.
	 * 
	 * @return boolean
	 */
	public boolean isExecuting() {
		return song != null;
	}

}
