package ambiente;

import java.util.ArrayList;

import herramientas.Coordenada;
import herramientas.General;
import herramientas.TipoVehiculo;
import poblacion.Vehiculo;

public class RedVial {

	private int[][] mallaVial;

	private int ancho;
	private int alto;

	private int viasX;
	private int viasY;

	private TipoVias tipoVias;

	private ArrayList<Vehiculo> vehiculos;

	private ThreadGenerarVehiculos threadGenerarVehiculos;

	/**
	 * 
	 * @param ancho
	 *            numero de casillas que tiene la redVial en X
	 * @param alto
	 *            numero de casillas que tiene la redVial en Y
	 */
	public RedVial(int ancho, int alto) {

		this.ancho = ancho;
		this.alto = alto;

		this.viasX = 0;
		this.viasY = 0;

		this.vehiculos = new ArrayList<Vehiculo>();

		this.mallaVial = new int[ancho][alto];

		this.tipoVias = TipoVias.MAPA_1;

		this.generarVias();

		this.contarViasX();
		this.contarViasY();

		this.imprimirMalla();

		this.threadGenerarVehiculos = new ThreadGenerarVehiculos(this);
		this.threadGenerarVehiculos.start();
	}

	/**
	 * Genera el numero de vias que tendra la simulacion, como minimo se trendra
	 * una via en cada sentido.
	 */
	public void generarVias() {
		switch (this.tipoVias) {
		case MAPA_1:
			this.mapa1();
			break;

		case ALEATORIAS:
			this.aleatorias();
			break;
		}
	}

	public void mapa1() {
		// Vias Y
		for (int i = 0; i < this.ancho; i++) {
			this.mallaVial[i][8] = 1;
			this.mallaVial[i][16] = 1;
		}
		// Vias X
		for (int i = 0; i < this.alto; i++) {
			this.mallaVial[13][i] = 1;
			this.mallaVial[26][i] = 1;
		}
	}

	/**
	 * Genera vias completamente aleatorias dentro de la red vial.
	 */
	public void aleatorias() {
		int aleatorioX = General.aleatorioEnRango(this.alto, 1);
		for (int i = 0; i < aleatorioX; i++) {
			int posicion = General.aleatorioEnRango((this.alto - 1), 0);
			for (int j = 0; j < this.ancho; j++) {
				this.mallaVial[j][posicion] = 1;
			}
		}
		int aleatorioY = General.aleatorioEnRango(this.ancho, 1);
		for (int i = 0; i < aleatorioY; i++) {
			int posicion = General.aleatorioEnRango((this.ancho - 1), 0);
			for (int j = 0; j < this.alto; j++) {
				this.mallaVial[posicion][j] = 1;
			}
		}
	}

	/**
	 * Cuenta las vias totales que existen en la malla vial en X.
	 */
	private void contarViasX() {
		for (int i = 0; i < this.ancho; i++) {
			if ((this.mallaVial[i][0] == 1) && (this.mallaVial[i][(this.alto - 1)] == 1)) {
				int contador = 0;
				for (int j = 0; j < this.alto; j++) {
					if (this.mallaVial[i][j] == 1) {
						contador++;
					}
				}
				if (contador == this.alto) {
					this.viasX++;
				}
			}
		}
	}

	/**
	 * Cuenta las vias totales que existen en la malla vial en Y.
	 */
	private void contarViasY() {
		for (int i = 0; i < this.alto; i++) {
			if ((this.mallaVial[0][i] == 1) && (this.mallaVial[(this.ancho - 1)][i] == 1)) {
				int contador = 0;
				for (int j = 0; j < this.ancho; j++) {
					if (this.mallaVial[j][i] == 1) {
						contador++;
					}
				}
				if (contador == this.ancho) {
					this.viasY++;
				}
			}
		}
	}

	/**
	 * Imprime en consola la redVial generada.
	 */
	public void imprimirMalla() {
		for (int i = 0; i < this.alto; i++) {
			for (int j = 0; j < this.ancho; j++) {
				System.out.print(this.mallaVial[j][i]);
			}
			System.out.println();
		}
	}

	@Override
	public String toString() {
		return "Red vial, columnas: " + this.ancho + ", filas: " + this.alto + ", numero vias X: " + this.viasX
				+ ", numero vias Y: " + this.viasY;
	}

	public int[][] getMallaVial() {
		return mallaVial;
	}

	public void setMallaVial(int[][] mallaVial) {
		this.mallaVial = mallaVial;
	}

	public int getAncho() {
		return ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	public int getAlto() {
		return alto;
	}

	public void setAlto(int alto) {
		this.alto = alto;
	}

	public int getViasX() {
		return viasX;
	}

	public void setViasX(int viasX) {
		this.viasX = viasX;
	}

	public int getViasY() {
		return viasY;
	}

	public void setViasY(int viasY) {
		this.viasY = viasY;
	}

	public ArrayList<Vehiculo> getVehiculos() {
		return vehiculos;
	}

	public void setVehiculos(ArrayList<Vehiculo> vehiculos) {
		this.vehiculos = vehiculos;
	}
}
