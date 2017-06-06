package gui;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import ambiente.RedVial;
import herramientas.Constantes;

import herramientas.General;
import poblacion.Estado;
import poblacion.Vehiculo;

@SuppressWarnings("serial")
public class PanelRedVial extends JLayeredPane {

	// Ancho de calzada
	private int tamanoViaX;
	// Ancho de carril
	private int tamanoCarrilX;
	// Ancho de calzada
	private int tamanoViaY;
	// Ancho de carril
	private int tamanoCarrilY;

	private RedVial redVial;

	private ArrayList<JLabelVia> arrayListViasX;
	private ArrayList<JLabelVia> arrayListViasY;

	private Thread threadVerificarVehiculos;

	private ArrayList<JLabel> vehiculosEnTransito;

	public PanelRedVial(int tamanoViaX, int tamanoViaY, RedVial redVial) {

		this.setLayout(null);

		this.setBackground(Constantes.COLOR_VERDE);
		this.setOpaque(true);

		this.tamanoViaX = tamanoViaX;
		this.tamanoCarrilX = tamanoViaX / 2;

		this.tamanoViaY = tamanoViaY;
		this.tamanoCarrilY = tamanoViaY / 2;

		this.redVial = redVial;

		this.renderVias();

		System.out.println("Ancho vias x: " + this.tamanoViaX + "px, Alto vias y: " + this.tamanoViaY + "px");
		System.out.println("Ancho carril x: " + this.tamanoCarrilX + "px, Ancho carril y: " + this.tamanoCarrilY);

		verificarVehiculos();
	}

	/**
	 * Renderiza toda la red vial; Middle = new Integer(2)). top = new
	 * Integer(3)); bottom = new Integer(1));
	 */
	private void renderVias() {
		// Dibujar viasX.
		this.arrayListViasX = new ArrayList<JLabelVia>();
		for (int i = 0; i < this.redVial.getAncho(); i++) {
			if ((this.redVial.getMallaVial()[i][0] == 1)
					&& (this.redVial.getMallaVial()[i][this.redVial.getAlto() - 1] == 1)) {
				JLabelVia jLabelVia = new JLabelVia((i * this.tamanoViaX), 0, this.tamanoViaX, Constantes.ALTO_VENTANA);
				this.arrayListViasX.add(jLabelVia);
				this.add(jLabelVia, new Integer(1));
			}
		}
		// Dibujar viasY
		this.arrayListViasY = new ArrayList<JLabelVia>();
		for (int i = 0; i < this.redVial.getAlto(); i++) {
			if ((this.redVial.getMallaVial()[0][i] == 1)
					&& (this.redVial.getMallaVial()[this.redVial.getAncho() - 1][i] == 1)) {
				JLabelVia jLabelVia = new JLabelVia(0, (i * this.tamanoViaY), Constantes.ANCHO_VENTANA,
						this.tamanoViaY);
				this.arrayListViasY.add(jLabelVia);
				this.add(jLabelVia, new Integer(1));
			}
		}

		this.repaint();
	}

	private void verificarVehiculos() {
		this.vehiculosEnTransito = new ArrayList<JLabel>();

		this.threadVerificarVehiculos = new Thread(new Runnable() {
			ArrayList<Vehiculo> vehiculos = redVial.getVehiculos();

			@Override
			public void run() {
				while (true) {
					if (!vehiculos.isEmpty()) {
						for (int i = 0; i < vehiculos.size(); i++) {
							if (vehiculos.get(i).getEstado().equals(Estado.ESPERANDO)) {
								renderVehiculo(vehiculos.get(i));
							}
						}
					}
					General.wait(1);// Disminuir tiempo para la distribucion
				}
			}
		});
		this.threadVerificarVehiculos.start();
	}

	private void renderVehiculo(Vehiculo vehiculo) {

		int posX = vehiculo.getCoordenadaOrigen().getPosX();
		int posY = vehiculo.getCoordenadaOrigen().getPosY();

		JLabelVehiculo jLabelVehiculo = new JLabelVehiculo(vehiculo, this);

		int x = 0;
		int y = 0;
		int ancho = 0;
		int alto = 0;

		if (posX >= posY) {
			if (posY == 1) {// Abajo
				vehiculo.setUbicacion(Ubicacion.ABAJO);

				x = (int) this.arrayListViasX.get(posX - 1).getBounds().getX() + this.tamanoCarrilX
						+ ((this.tamanoCarrilX - vehiculo.getAncho()) / 2) + 1;
				y = Constantes.ALTO_VENTANA - 50;
			} else if (posY == 0) {// Arriba
				vehiculo.setUbicacion(Ubicacion.ARRIBA);

				x = (int) this.arrayListViasX.get(posX - 1).getBounds().getX()
						+ ((this.tamanoCarrilX - vehiculo.getAncho()) / 2) + 1;
				y = 50;
			}

			alto = vehiculo.getLongitud();
			ancho = vehiculo.getAncho();

		} else if (posY > posX) {
			if (posX == 1) {// Izquierda
				vehiculo.setUbicacion(Ubicacion.IZQUIERDA);

				y = (int) this.arrayListViasY.get(posY - 1).getBounds().getY()
						+ ((this.tamanoCarrilY - vehiculo.getAncho()) / 2) + 1;
				x = Constantes.ANCHO_VENTANA - 50;
			} else {// Derecha
				vehiculo.setUbicacion(Ubicacion.DERECHA);

				y = (int) this.arrayListViasY.get(posY - 1).getBounds().getY() + this.tamanoCarrilY
						+ ((this.tamanoCarrilY - vehiculo.getAncho()) / 2) + 1;
				x = 50;
			}
			alto = vehiculo.getAncho();
			ancho = vehiculo.getLongitud();
		} // else if (posX == posY) {

		// }
		System.out.println("Vehiculo " + vehiculo.getTipoVehiculo() + ", X:" + x + ", Y: " + y);

		jLabelVehiculo.setBounds(x, y, ancho, alto);
		this.add(jLabelVehiculo, new Integer(3));
		vehiculo.setEstado(Estado.TRANSITANDO);
		jLabelVehiculo.getThread().start();
		this.repaint();

	}

	public int getTamanoViaX() {
		return tamanoViaX;
	}

	public void setTamanoViaX(int tamanoViaX) {
		this.tamanoViaX = tamanoViaX;
	}

	public int getTamanoCarrilX() {
		return tamanoCarrilX;
	}

	public void setTamanoCarrilX(int tamanoCarrilX) {
		this.tamanoCarrilX = tamanoCarrilX;
	}

	public int getTamanoViaY() {
		return tamanoViaY;
	}

	public void setTamanoViaY(int tamanoViaY) {
		this.tamanoViaY = tamanoViaY;
	}

	public int getTamanoCarrilY() {
		return tamanoCarrilY;
	}

	public void setTamanoCarrilY(int tamanoCarrilY) {
		this.tamanoCarrilY = tamanoCarrilY;
	}

	public RedVial getRedVial() {
		return redVial;
	}

	public void setRedVial(RedVial redVial) {
		this.redVial = redVial;
	}

	public ArrayList<JLabelVia> getArrayListViasX() {
		return arrayListViasX;
	}

	public void setArrayListViasX(ArrayList<JLabelVia> arrayListViasX) {
		this.arrayListViasX = arrayListViasX;
	}

	public ArrayList<JLabelVia> getArrayListViasY() {
		return arrayListViasY;
	}

	public void setArrayListViasY(ArrayList<JLabelVia> arrayListViasY) {
		this.arrayListViasY = arrayListViasY;
	}

	public Thread getThreadVerificarVehiculos() {
		return threadVerificarVehiculos;
	}

	public void setThreadVerificarVehiculos(Thread threadVerificarVehiculos) {
		this.threadVerificarVehiculos = threadVerificarVehiculos;
	}

	public ArrayList<JLabel> getVehiculosEnTransito() {
		return vehiculosEnTransito;
	}

	public void setVehiculosEnTransito(ArrayList<JLabel> vehiculosEnTransito) {
		this.vehiculosEnTransito = vehiculosEnTransito;
	}
}
