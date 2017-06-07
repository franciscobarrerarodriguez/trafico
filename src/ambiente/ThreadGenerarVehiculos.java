package ambiente;

import herramientas.Coordenada;
import herramientas.General;
import herramientas.TipoVehiculo;
import poblacion.Vehiculo;

/**
 * Genera un medio de transporte cada 5 segundos desde el inicio de la
 * simulacion falta la distribucion con la cual son generados los vehiculos.
 * 
 * @author rocinante
 *
 */
public class ThreadGenerarVehiculos extends Thread {

	private RedVial redVial;

	public ThreadGenerarVehiculos(RedVial redVial) {
		this.redVial = redVial;
	}

	/**
	 * Genera una coordenada aleatoria (sobre una via) que se puede utilizar
	 * como origen o destino, si el resultado generado es 0(X), genera una
	 * posicion valida entre las vias generadas posX y para posY 0 o 1, para
	 * determinar su origen al pricipio o al final de la via. 0 para generarlo
	 * en la parte superior y 1 para generarlo en la parte inferior de la
	 * ventana
	 */
	private Coordenada generarCoordenada() {
		int posX = 0;
		int posY = 0;
		switch (General.aleatorioEnRango(2, 0)) {
		case 0: // X
			posX = General.aleatorioEnRango(this.redVial.getViasX(), 1);
			posY = General.aleatorioEnRango(2, 0);
			break;
		case 1: // Y
			posX = General.aleatorioEnRango(2, 0);
			posY = General.aleatorioEnRango(this.redVial.getViasY(), 1);
			break;
		}
		return new Coordenada(posX, posY);
	}

	@Override
	public void run() {
		while (true) {
			Vehiculo vehiculo = new Vehiculo(TipoVehiculo.getTipoAleatorio(), this.generarCoordenada(),
					this.generarCoordenada());
			this.redVial.getVehiculos().add(vehiculo);
			System.out.println(vehiculo.toString());
			General.wait(7);
		}
	}
}
