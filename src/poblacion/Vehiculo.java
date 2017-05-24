package poblacion;

import java.awt.Color;

import herramientas.Constantes;
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
	private int ancho;

	private Color color;

	private Coordenada coordenadaOrigen;
	private Coordenada coordenadaDestino;

	/**
	 * 
	 * @param tipo
	 *            Define el tipo de vehiculo para la clase
	 * @param ancho
	 *            Define el ancho del vehiculo en pixeles
	 * @param redVial
	 *            Red vial para saber las coordenadas de origen y destino
	 */
	public Vehiculo(TipoVehiculo tipo, Coordenada coordenadaOrigen, Coordenada coordenadaDestino) {

		this.ancho = Constantes.ANCHO_VEHICULO;

		this.coordenadaOrigen = coordenadaOrigen;
		this.coordenadaDestino = coordenadaDestino;

		this.init(tipo);
	}

	/**
	 * Inicializa todas las carecteristicas propias del vehiculo creado.
	 * 
	 * @param tipo
	 *            Usa el tipo de vehiculo que tiene definido la clase para
	 *            inicializar el vehiculo.
	 */
	private void init(TipoVehiculo tipo) {
		switch (this.tipoVehiculo = tipo) {
		case TURISMO:
			this.longitud = this.ancho;
			this.color = Constantes.COLOR_TURISMO;
			break;
		case MOTOCICLETA:
			this.ancho -= 2;
			this.longitud = this.ancho * 3;
			this.color = Constantes.COLOR_MOTOCICLETA;
			break;
		case CAMION:
			this.longitud = this.ancho * 5;
			this.color = Constantes.COLOR_CAMION;
			break;
		case BUS:
			this.longitud = this.ancho * 5;
			this.color = Constantes.COLOR_BUS;
			break;
		case TRACTOMULA:
			this.longitud = this.ancho * 10;
			this.color = Constantes.COLOR_TRACTOMULA;
			break;
		}
	}

	@Override
	public String toString() {
		return "Tipo: " + this.tipoVehiculo + ", ancho: " + this.ancho + "px, largo: " + this.longitud + "px, color:"
				+ this.color + ", Coordenada origen: " + this.coordenadaOrigen.toString() + ", Coordenada destino: "
				+ this.coordenadaDestino.toString();
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

	public int getAncho() {
		return ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}
