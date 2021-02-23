package br.com.wellington.jplay2D.time;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import br.com.wellington.jplay2D.window.Window;

/** Classe usada para manipular o tempo. */
public class Time {
	private Timer timer;
	private Font font;
	private Color color;
	private int currentSecond;
	private int hour;
	private int minute;
	private int second;
	private int x;
	private int y;
	private boolean crescentTime;

	/**
	 * Cria uma instância da classe Time. O tempo terá casa = 0, minuto = 0,
	 * segundos = 0. Será desenhado na tela com a fonte = Arial, tamanho = 20 e a
	 * cor = amarelo.
	 * 
	 * @param x            valor da coordenada X.
	 * @param y            valor da coordenada y.
	 * @param crescentTime verdadeiro se o for crescente, falso caso contrário.
	 */
	public Time(int x, int y, boolean crescentTime) {
		this(0, 0, 0, x, y, crescentTime);
	}

	/**
	 * Cria uma instância da classe Time.
	 * 
	 * @param hour
	 * @param minute
	 * @param second
	 * @param x
	 * @param y
	 * @param crescentTime true se for crescente, falso caso contrário.
	 */
	public Time(int hour, int minute, int second, int x, int y, boolean crescentTime) {
		this(hour, minute, second, x, y, new Font("Arial", Font.TRUETYPE_FONT, 20), Color.YELLOW, crescentTime);
	}

	/**
	 * Cria uma instância da classe Time.
	 * 
	 * @param hour
	 * @param minute
	 * @param second
	 * @param x
	 * @param y
	 * @param font
	 * @param color
	 * @param crescentTime True se o tempo for crescente, caso contrário, false.
	 */
	public Time(int hour, int minute, int second, int x, int y, Font font, Color color, boolean crescentTime) {
		this.x = x;
		this.y = y;
		this.hour = hour;
		this.minute = minute;
		this.second = second;
		this.color = color;
		this.font = font;
		this.crescentTime = crescentTime;
		this.crescentTime = crescentTime;
		calculateSeconds();
		createAction();
	}

	private void createAction() {
		ActionListener action = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentSecond != 0 && (crescentTime == false))
					currentSecond--;
				else {
					if (crescentTime)
						currentSecond++;
				}
				hour = currentSecond / 3600;
				minute = (currentSecond - hour * 3600) / 60;
				second = currentSecond - hour * 3600 - minute * 60;
			}
		};
		this.timer = new javax.swing.Timer(1000, action);
		this.timer.start();
	}

	/**
	 * Retorna uma string com o tempo. O formato da hora é hora / min / segundo
	 * (00/00/00).
	 * 
	 * @return String
	 */
	@Override
	public String toString() {
		String str = "";

		if (hour < 10)
			str = "0" + Integer.toString(hour) + ":";
		else
			str = Integer.toString(hour) + ":";

		if (minute < 10)
			str += "0" + Integer.toString(minute) + ":";
		else
			str += Integer.toString(minute) + ":";

		if (second < 10)
			str += "0" + Integer.toString(second);
		else
			str += Integer.toString(second);

		return str;
	}

	/**
	 * Desenha uma mensagem antes da hora na tela.
	 * 
	 * @param message
	 */
	public void draw(String message) {
		Window.getInstance().drawText(message + toString(), x, y, color, font);
	}

	/**
	 * Desenha a hora na tela.
	 */
	public void draw() {
		Window.getInstance().drawText(toString(), x, y, color, font);
	}

	/**
	 * Define a cor usada para desenhar o tempo na tela.
	 * 
	 * @param color
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * Define a fonte usada para desenhar a hora na tela.
	 * 
	 * @param color
	 */
	public void setFont(Font font) {
		this.font = font;
	}

	private void calculateSeconds() {
		currentSecond = hour * 3600 + minute * 60 + second;
	}

	/**
	 * Retorna verdadeiro quando chega a hora zero, falso caso contrário. Só dá
	 * certo se o tempo for crescente.
	 * 
	 * @param boolean
	 */
	public boolean timeEnded() {
		return (currentSecond == 0);
	}

	/**
	 * Define a hora.
	 * 
	 * @param hour
	 */
	public void setHour(int hour) {
		setTime(hour, minute, this.second);
	}

	/**
	 * Define o minudo.
	 * 
	 * @param minute
	 */
	public void setMinute(int minute) {
		setTime(this.hour, minute, this.second);
	}

	/**
	 * Define o segundo
	 * 
	 * @param second
	 */
	public void setSecond(int second) {
		setTime(this.hour, this.minute, second);
	}

	/**
	 * Retorna a hora.
	 * 
	 * @return int
	 */
	public int getHour() {
		return this.hour;
	}

	/**
	 * retorna o minuto.
	 * 
	 * @return int
	 */
	public int getMinute() {
		return this.minute;
	}

	/**
	 * retorna o segundo.
	 * 
	 * @return int
	 */
	public int getSecond() {
		return this.second;
	}

	/**
	 * Retorna o tempo em segundos. Exemplo: 1h 29 min e 45 segundos será retornado
	 * como 1 * 60 * 60 + 29 * 60 + 45 = 217785 segundos.
	 * 
	 * @return long
	 */
	public long getTotalSecond() {
		return this.currentSecond;
	}

	/**
	 * Define o tempo atual.
	 * 
	 * @param hour
	 * @param minute
	 * @param seconds
	 */
	public void setTime(int hour, int minute, int seconds) {
		this.hour = hour;
		this.minute = minute;
		this.second = seconds;
		calculateSeconds();
	}
}
