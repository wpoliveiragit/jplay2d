/*
 * The use this code commercially must only be done with permission of the author.
 * Any modification shall be advised and sent to the author.
 * The author is not responsible for any problem therefrom the use of this frameWork.
 *
 * @author Gefersom Cardoso Lima
 * Federal Fluminense University
 * Computer Science
 */

package br.com.wellington.jplay2D.sound;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.SourceDataLine;

import jplay.url.Url;

/** Class responsible for controlling the execution of sounds. */
public class Sound {

	/** Caminho do arquivo */
	private String path;

	private boolean loop;
	private boolean stop;
	private boolean pause;

	private float volume;

	/** Marcador de mudança de valor no volume */
	private boolean volChng;// informa se o
	private Song song;

	/**
	 * Class Constructor. Create an instance of this class. The sound will be
	 * executed only once.
	 * 
	 * @param name Nome do arquivo com a extensão.
	 */
	public Sound(String name) {
		path = Url.getURL(name);
		loop = false;
		stop = true;
		pause = false;
	}

	private class Song extends Thread {

		private byte tempBuffer[] = new byte[1000];
		private AudioInputStream audioIn;// Entrada de dados
		private SourceDataLine audCtrl;// Controle do audio

		private int vlm;
		private boolean vlmChng;

		public Song() {
			vlm = 2;
			vlmChng = true;
		}

		@Override
		public void run() {
			try {
				do {
					stop = false;
					pause = false;
					setVolume(volume);

					audioIn = AudioSystem.getAudioInputStream(new File(path));
					AudioFormat audioFrmt = audioIn.getFormat();
					audCtrl = (SourceDataLine) AudioSystem.getLine(new DataLine.Info(SourceDataLine.class, audioFrmt));
					audCtrl.open(audioFrmt);
					audCtrl.start();
					FloatControl vol = (FloatControl) audCtrl.getControl(FloatControl.Type.MASTER_GAIN);
					int buffer = 0;
					while (true) {
						if (volChng) {
							vol.setValue(volume);
							volChng = false;
						}
						if (pause)
							continue;
						if (stop)
							break;
						if ((buffer = audioIn.read(tempBuffer, 0, tempBuffer.length)) == -1)
							break;
						audCtrl.write(tempBuffer, 0, buffer);// emite o treixo do som coletado
					}
					audCtrl.drain();// esvazia o buffer
					audCtrl.close();
				} while (loop && !stop);
				stop = true;
				pause = false;
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	/** Starts the execution of the sound. */
	public void play() {
		if (pause) {
			pause = false;
			return;
		}

		if (stop) {
			song = new Song();
			song.start();
			pause = false;
		}
	}

	/** Pauses the execution of the sound. */
	public void pause() {
		pause = true;
	}

	/** Stops the execution of the sound. */
	public void stop() {
		stop = true;
	}

	/**
	 * Method responsible for increasing the volume.
	 * 
	 * @param value This value will be added to the current volume value.
	 */
	public void increaseVolume(float value) {
		setVolume(volume + value);
	}

	/**
	 * Method responsible for decrease the volume.
	 * 
	 * @param value This value will be subtracted from the current volume value.
	 */
	public void decreaseVolume(float value) {
		setVolume(volume - value);
	}

	/**
	 * Sets the current volume.
	 * 
	 * @param value
	 */
	public void setVolume(float value) {
		volume = value;
		volChng = true;
	}

	/**
	 * Deixa o som repetindo indefinidamente.
	 * 
	 * @param loop true para deixar em repetição indefinida
	 */
	public void setLoop(boolean loop) {
		this.loop = loop;
	}

	/**
	 * Returns true if the sound is running, false otherwise.
	 * 
	 * @return boolean
	 */
	public boolean isRunning() {
		return !stop;
	}

}
