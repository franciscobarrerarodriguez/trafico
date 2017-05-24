package herramientas;

import java.util.Random;

public class General {

	/**
	 * (int) (rnd.nextDouble() * cantidad_números_rango + término_inicial_rango)
	 * 
	 * @return
	 */
	public static int aleatorioEnRango(int limiteSuperior, int limiteInferior) {
		Random random = new Random();
		return (int) (random.nextDouble() * limiteSuperior + limiteInferior);
	}

	public static void wait(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
	}

	/**
	 * Pruebas
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(General.aleatorioEnRango(2, 0));
	}
}
