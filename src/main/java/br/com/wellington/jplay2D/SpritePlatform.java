package br.com.wellington.jplay2D;

/** Similar a classe {@link SpriteBase} porem para jogos de plataforma. */
public class SpritePlatform extends SpriteBase {

	/** Aumenta a altura e velocidade do pulo. */
	protected double jumpHeight;

	/** Aumenta a velocidade da queda e diminue a altura do pulo. */
	protected double gravity;

	/** Altura do piso da plataforma. */
	protected int platformHeight;

	/** Velocidade de queda corrente */
	protected double fallingSpeed;

	/** Define se a sprite esta sobre o piso da plataforma. true para sim */
	protected boolean platformFloor;

	/**
	 * Cria o controle da sprite, definindo o primeiro quadro como inicio e o último
	 * como o fim do loop da animação.
	 * 
	 * @param name   O nome da sprite.
	 * @param frames a quantidade de quadros que a sprite tem.
	 * @param loop   trua deixa a imagem em estado de loop do frame.
	 */
	public SpritePlatform(String name, int frames, boolean loop) {
		super(name, frames, loop);
		jumpHeight = 14.99;
		gravity = 1.99;
		fallingSpeed = 0;
		platformHeight = Integer.MAX_VALUE;
	}

	public void jump() {// ok
		if (platformFloor == true) {
			platformFloor = false;
			fallingSpeed = -jumpHeight;
		}
	}

	/** Atrai a sprite para baixo simulando uma gravidade. */
	public void gravityFloor() {// ok
		if (platformFloor) {
			return;
		}
		fallingSpeed += gravity;
		y += fallingSpeed;
		if ((y + height - platformHeight > 0.0001)) {
			y = platformHeight - height;
			fallingSpeed = 0;
			platformFloor = true;
		}
	}

	/**
	 * Configura uma nova altura do pulo consequentimente a velocidade será alterada
	 * também.
	 * 
	 * @param jumpHeight Quanto maior o valor, mais alto e rápido será o pulo.
	 */
	public void setJumpHeight(double jumpHeight) {
		this.jumpHeight = jumpHeight;
	}

	/**
	 * Define onde esta a alturar da superficie do piso da plataforma onde a sprite
	 * esta no momento.
	 * 
	 * @param platformHeight apenas valores positivos.
	 */
	public void setPlatformHeight(int platformHeight) {
		this.platformHeight = platformHeight;
	}

	/**
	 * Configurauma nova velocidade da queda e consequentimente altera a altura do
	 * pulo.
	 * 
	 * @param gravity Quanto maior o valor, mais rápido será a queda e menor será o
	 *                pulo.
	 */
	public void setGravity(double gravity) {
		this.gravity = gravity;
	}

	/**
	 * Retorna a velocidade da queda da sprite no mapa corrente.
	 * 
	 * @return double A velocidade da queda corrente.
	 */
	public double getFallingSpeed() {
		return fallingSpeed;
	}

	/**
	 * Returns the value used for gravity.
	 * 
	 * @return double
	 */
	public double getGravity() {
		return gravity;
	}

	/**
	 * retorna a altura do piso
	 * 
	 * @return int O ponto atual do piso no eixo x.
	 */
	public int getPlatformHeight() {
		return platformHeight;
	}

	/**
	 * Retorna a velocidade do pulo.
	 * 
	 * @return double A velocidade do pulo.
	 */
	public double getJumpHeight() {
		return jumpHeight;
	}

	/**
	 * Define se está ou não sobre o piso da plataforma.
	 * 
	 * @return true se estiver sobre a plataforma.
	 */
	public boolean isPlatformFloor() {
		return platformFloor;
	}

}
