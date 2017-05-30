package gui;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import ambiente.RedVial;
import herramientas.Constantes;

import herramientas.General;
import poblacion.Estado;
import poblacion.Vehiculo;

@SuppressWarnings("serial")
public class PanelRedVial extends JPanel {

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
	 * Renderiza toda la red vial.
	 */
	private void renderVias() {
		// Dibujar viasX.
		this.arrayListViasX = new ArrayList<JLabelVia>();
		for (int i = 0; i < this.redVial.getAncho(); i++) {
			if ((this.redVial.getMallaVial()[i][0] == 1)
					&& (this.redVial.getMallaVial()[i][this.redVial.getAlto() - 1] == 1)) {
				JLabelVia jLabelVia = new JLabelVia((i * this.tamanoViaX), 0, this.tamanoViaX, Constantes.ALTO_VENTANA);
				this.arrayListViasX.add(jLabelVia);
				this.add(jLabelVia);
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
				this.add(jLabelVia);
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

		JLabelVehiculo jLabelVehiculo = new JLabelVehiculo(vehiculo);

		int x = 0;
		int y = 0;
		if (posX >= posY) {
			x = (int) this.arrayListViasX.get(posX - 1).getBounds().getX()+5;
			if (posY == 1) {
				y = Constantes.ALTO_VENTANA - 50;
			} else {
				y = 50;
			}
		} else if (posY > posX) {
			y = (int) this.arrayListViasY.get(posY - 1).getBounds().getY()+5;
			if (posX == 1) {
				x = Constantes.ANCHO_VENTANA - 50;
			} else {
				x = 50;
			}
		}
		System.out.println("Vehiculo "+vehiculo.getTipoVehiculo()+", X:" + x + ", Y: " + y);
		
		

		jLabelVehiculo.setBounds(x, y, vehiculo.getAncho(), vehiculo.getLongitud());
		this.add(jLabelVehiculo);
		vehiculo.setEstado(Estado.TRANSITANDO);
		this.repaint();

	}
}
