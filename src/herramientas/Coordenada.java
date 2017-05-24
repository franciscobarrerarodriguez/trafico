package herramientas;

public class Coordenada {

	private int posX;
	private int posY;

	/**
	 * 
	 * @param posX
	 *            Posicion en X en la red vial
	 * @param posY
	 *            Posicion en Y en la red vial
	 */
	public Coordenada(int posX, int posY) {
		
		this.posX = posX;
		this.posY = posY;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
	
}
