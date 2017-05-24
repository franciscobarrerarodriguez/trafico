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

	private ArrayList<Vehiculo> vehiculos;

	private Thread threadGenerarVehiculos;

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

		this.generarVias();

		this.contarViasX();
		this.contarViasY();

		this.imprimirMalla();

		this.generarVehiculos();
	}

	/**
	 * Genera el numero de vias que tendra la simulacion, como minimo se trendra
	 * una via en cada sentido.
	 */
	public void generarVias() {
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
	 * Genera un medio de transporte cada 5 segundos desde el inicio de la
	 * simulacion falta la distribucion con la cual son generados los vehiculos.
	 */
	private void generarVehiculos() {
		this.threadGenerarVehiculos = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					Vehiculo vehiculo = new Vehiculo(TipoVehiculo.getTipoAleatorio(), generarCoordenada(), generarCoordenada());
					vehiculos.add(vehiculo);
					 System.out.println(vehiculo.toString());
					 General.wait(7);
				}
			}
		});
		this.threadGenerarVehiculos.start();
	}

	/**
	 * Genera una coordenada aleatoria (sobre una via) que se puede utilizar
	 * como origen o destino, si el resultado generado es 0(X), genera una
	 * posicion valida entre las vias generadas posX y para posY 0 o 1, para
	 * determinar su origen al pricipio o al final de la via.
	 */
	private Coordenada generarCoordenada() {
		int posX = 0;
		int posY = 0;
		switch (General.aleatorioEnRango(2, 0)) {
		case 0: // X
			posX = General.aleatorioEnRango(this.viasX, 1);
			posY = General.aleatorioEnRango(2, 0);
			break;
		case 1: // Y
			posX = General.aleatorioEnRango(2, 0);
			posY = General.aleatorioEnRango(this.viasY, 1);
			break;
		}
		return new Coordenada(posX, posY);
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
}
