package test;

import ambiente.RedVial;
import herramientas.Constantes;

public class TestRedVial {

	public static void main(String[] args) {
		RedVial redVial = new RedVial(Constantes.DEFAULT_ANCHO, Constantes.DEFAULT_ALTO);
		System.out.println(redVial.toString());
	}
}
