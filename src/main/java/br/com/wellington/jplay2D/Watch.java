/*
 * The use this code commercially must only be done with permission of the author.
 * Any modification shall be advised and sent to the author.
 * The author is not responsible for any problem therefrom the use of this frameWork.
 *
 * @author Gefersom Cardoso Lima
 * Universidade Federal Fluminense - UFF - Brasil - 2010
 * Ciência da Computação
 */

package br.com.wellington.jplay2D;

import javax.swing.Timer;

/** Manipulador de tempo. */
public class Watch {

	protected final static String FORMAT = "%02d";

	/** Controle do tempo */
	protected Timer timer;
	/** Marcador das horas */
	protected int hour;
	/** Marcador dos minutos */
	protected int minute;
	/** Marcador dos segundor */
	protected int second;
	/** Adiciona uma unidade de tempo */
	protected int add;
	/** Quantidade de tempo percorrido */
	protected int currentSecond;

	/**
	 * Instancia um cronometro.
	 * 
	 * @param hour   hora inicial.
	 * @param minute Minuto inicial.
	 * @param second Segundo inicial.
	 * @param sense  false para tempo decrescente.
	 */
	public Watch(boolean sense, int hour, int minute, int second) {
		setTime(sense, hour, minute, second);
		timer = new Timer(1000, (e) -> {
			currentSecond += add;
			if (currentSecond == 0) {
				stop();
			}
			this.hour = currentSecond / 3600;
			this.minute = (currentSecond - this.hour * 3600) / 60;
			this.second = currentSecond - this.hour * 3600 - this.minute * 60;
		});
	}

	/**
	 * Configura um novo tempo.
	 * 
	 * @param hour    hora.
	 * @param minute  minuto.
	 * @param seconds segundo.
	 */
	public void setTime(boolean sense, int hour, int minute, int seconds) {// ok
		add = sense ? 1 : -1;
		currentSecond = hour * 3600 + minute * 60 + seconds;
	}

	/** Liga o cronometro do tempo. */
	public void start() {
		timer.start();
	}

	/** Desliga o cronometro do tempo. */
	public void stop() {
		timer.stop();
	}

	/** Retorna true se o relógio estiver em execução. */
	public boolean isRunning() {
		return timer.isRunning();
	}

	/**
	 * Retorna no sentido do relogio.
	 * 
	 * @return true para crescente.
	 */
	public boolean isSense() {
		return add == 1 ? true : false;
	}

	/**
	 * Retorna o tempo do relógio.
	 * 
	 * @return O tempo do relógiono formato "hh:mm:ss".
	 */
	public String getTime() {// ok
		return String.format(FORMAT, hour) + ":" + String.format(FORMAT, minute) + ":" + String.format(FORMAT, second);
	}

	/**
	 * retorna o tempo corrente em unidade de segundos.
	 * 
	 * @return O tempo corrente em unidade de segundos.
	 */
	public long getTotalSecond() {
		return currentSecond;
	}

	public int getHour() {
		return hour;
	}

	public int getMinute() {
		return minute;
	}

	public int getSecond() {
		return second;
	}

}
