package gui;

import java.awt.Color;

import javax.swing.JLabel;

import poblacion.Vehiculo;

@SuppressWarnings("serial")
public class JLabelVehiculo extends JLabel {

	private Vehiculo vehiculo;

	public JLabelVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
		this.setBackground(this.vehiculo.getColor());
		this.setOpaque(true);
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}
}
