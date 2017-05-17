package ambiente;

import herramientas.General;

public class RedVial {

	private int[][] mallaVial;
	private int ancho;
	private int alto;

	public RedVial(int ancho, int alto) {
		this.ancho = ancho;
		this.alto = alto;
		this.mallaVial = new int[ancho][alto];
	}

	/**
	 * Genera el numero de vias que tendra la simulacion, como minimo se trendra
	 * una via en cada sentido.
	 */
	public void generarVias() {
		int viasX = General.aleatorioEnRango(this.alto, 1);
		for (int i = 0; i < viasX; i++) {
			int posicion = General.aleatorioEnRango((this.alto - 1), 0);
			for (int j = 0; j < this.ancho; j++) {
				this.mallaVial[j][posicion] = 1;
			}
		}
		int viasY = General.aleatorioEnRango(this.ancho, 1);
		for (int i = 0; i < viasY; i++) {
			int posicion = General.aleatorioEnRango((this.ancho - 1), 0);
			for (int j = 0; j < this.alto; j++) {
				this.mallaVial[posicion][j] = 1;
			}
		}

	}
	
	public void imprimirMalla() {
		for (int i = 0; i < this.alto; i++) {
			for (int j = 0; j < this.ancho; j++) {
				System.out.print(this.mallaVial[j][i]);
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		RedVial redVial = new RedVial(100, 100);
		redVial.generarVias();
		redVial.imprimirMalla();
	}

}
