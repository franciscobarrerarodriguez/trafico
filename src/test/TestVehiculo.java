package test;

import herramientas.Coordenada;
import herramientas.TipoVehiculo;
import poblacion.Vehiculo;

public class TestVehiculo {

	public static void main(String[] args) {

		Vehiculo vehiculo1 = new Vehiculo(TipoVehiculo.MOTOCICLETA, new Coordenada(0, 0), new Coordenada(1, 1));
		System.out.println(vehiculo1.toString());
//
//		Vehiculo vehiculo2 = new Vehiculo(TipoVehiculo.MOTOCICLETA, 3, redVial);
//		System.out.println(vehiculo2.toString());
	}
}
