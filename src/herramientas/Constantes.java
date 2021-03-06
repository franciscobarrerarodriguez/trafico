package herramientas;

import java.awt.Color;

public class Constantes {

	public static final int DEFAULT_ANCHO = 40;
	public static final int DEFAULT_ALTO = 26;
	public static final int ANCHO_VENTANA = 1000;
	public static final int ALTO_VENTANA = 650;

	public static final int ANCHO_VEHICULO = (((Constantes.ANCHO_VENTANA / Constantes.DEFAULT_ANCHO))/2) - 5;

	public static final int LARGO_SEPARADOR = 20;

	public static final Color COLOR_VERDE = new Color(69, 237, 80);

	public static final Color COLOR_TURISMO = Color.BLUE;
	public static final Color COLOR_MOTOCICLETA = Color.RED;
	public static final Color COLOR_CAMION = Color.PINK;
	public static final Color COLOR_BUS = Color.YELLOW;
	public static final Color COLOR_TRACTOMULA = Color.CYAN;

	public static final int CAMBIO_PIXELES = 1;
	
	public static final int VELOCIDAD_MAX = 5;
	public static final int VELOCIDAD_MIN = 50;
	
}
