package br.com.wellington.jplay2D.imageProcessing;

import java.awt.Point;

/**
 * A classe costumava saber se dois GameObjects colidiam.
 */
public class Collision {

	/**
	 * Método usado para saber se duas áreas na tela estão se tocando.
	 * 
	 * @param min1 ponto do canto superior esquerdo da primeira área.
	 * @param max1 ponto de baixo da direita da primeira imagem.
	 * @param min2 ponto do canto superior esquerdo da segunda área.
	 * @param max3 ponto de baixo da direita da segunda imagem.
	 * @return boolean retorna verdadeiro se eles estiverem se tocando, falso caso
	 *         contrário.
	 */
	public static boolean collided(Point min1, Point max1, Point min2, Point max2) {
		if (min1.x >= max2.x || max1.x <= min2.x) {
			return false;
		}
		if (min1.y >= max2.y || max1.y <= min2.y) {
			return false;
		}
		return true;
	}

	/**
	 * Método estático usado para saber se dois GameObjects estão se tocando.
	 * 
	 * @param obj1 origin GameObject
	 * @param obj2 target GameObject
	 * @return boolean - retorna verdadeiro quando eles estão se tocando, falso caso
	 *         contrário.
	 */
	public static boolean collided(GameObject obj1, GameObject obj2) {
		Point minObj1 = new Point((int) obj1.x, (int) obj1.y);
		Point maxObj1 = new Point((int) (obj1.x + obj1.width), (int) (obj1.y + obj1.height));

		Point minObj2 = new Point((int) obj2.x, (int) obj2.y);
		Point maxObj2 = new Point((int) (obj2.x + obj2.width), (int) (obj2.y + obj2.height));

		return (Collision.collided(minObj1, maxObj1, minObj2, maxObj2));
	}
}
