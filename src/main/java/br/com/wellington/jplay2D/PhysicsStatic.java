package br.com.wellington.jplay2D;

public class PhysicsStatic {

	protected static double PIXELS_PER_METER = 30;

	protected static double WIDTH = 0;

	protected static double HEIGHT = 0;

	protected static float TIMESTEP = 1f / 60f;

	protected static float GRAVITY = -9.8f; // Negative value implies downward gravitational force.

	protected static float WILD = 0f;

	/**
	 * Transforma o valor (de pixels) em metros.
	 * 
	 * @param pixels O n�mero em pixel.
	 * @return um valo
	 */
	protected static double toMeterX(double pixels) {
		return (pixels - (WIDTH / 2)) / PIXELS_PER_METER;
	}

	protected static double toPixelsX(double meters) {
		return ((WIDTH / 2) + (meters * PIXELS_PER_METER));
	}

	protected static double toMeterY(double pixels) {
		return -(pixels - (HEIGHT / 2)) / PIXELS_PER_METER;
	}

	protected static double toPixelsY(double meters) {
		return ((HEIGHT / 2) - (meters * PIXELS_PER_METER));
	}

}
