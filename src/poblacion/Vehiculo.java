package poblacion;

import herramientas.Coordenada;
import herramientas.TipoVehiculo;

public class Vehiculo {
	/**
	 * Características de la población:  Los vehículos se desplazan sobre una
	 * red vial urbana, cada uno de ellos tiene: ◦ Tipo asociado al tamaño,
	 * longitud. ◦ Capacidades de aceleración/desaceleración/maniobrabilidad. ◦
	 * Origen y destino. ◦ Tiempo de viaje deseado. ◦ Visibilidad limitada. ◦
	 * Capacidad de adelantar. ◦ Cumplen con las leyes físicas de movimiento,
	 * para su desplazamiento sobre la red vial y las normas de prelación de
	 * tráfico. ◦ El movimiento de los vehículos está restringido a la malla
	 * vial.
	 */
	private TipoVehiculo tipoVehiculo;
	private int longitud;
	private Coordenada coordenadaOrigen;
	private Coordenada coordenadaDestino;

	public Vehiculo(TipoVehiculo tipo, Coordenada coordenadaOrigen, Coordenada coordenadaDestino) {
		this.coordenadaOrigen = coordenadaOrigen;
		this.coordenadaDestino = coordenadaDestino;
		this.init(tipo);
	}

	private void init(TipoVehiculo tipo) {
		switch (this.tipoVehiculo  = tipo) {
		case TURISMO:
			break;
		case VEHICULO_MIXTO:
			break;
		case MOTOCICLETA:
			break;
		case CAMION:
			break;
		case BUS:
			break;
		case TRACTOMULA:
			break;
		}
	}

	public TipoVehiculo getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}

	public int getLongitud() {
		return longitud;
	}

	public void setLongitud(int longitud) {
		this.longitud = longitud;
	}

	public Coordenada getCoordenadaOrigen() {
		return coordenadaOrigen;
	}

	public void setCoordenadaOrigen(Coordenada coordenadaOrigen) {
		this.coordenadaOrigen = coordenadaOrigen;
	}

	public Coordenada getCoordenadaDestino() {
		return coordenadaDestino;
	}

	public void setCoordenadaDestino(Coordenada coordenadaDestino) {
		this.coordenadaDestino = coordenadaDestino;
	}

}
