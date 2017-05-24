package herramientas;

public enum TipoVehiculo {
	
	TURISMO, MOTOCICLETA, CAMION, BUS, TRACTOMULA;
	
	public static TipoVehiculo getTipoAleatorio() {
        return values()[(int) (Math.random() * values().length)];
    }
}
