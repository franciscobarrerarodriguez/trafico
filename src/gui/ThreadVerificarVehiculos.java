package gui;

import java.util.ArrayList;

import herramientas.General;
import poblacion.Estado;
import poblacion.Vehiculo;

public class ThreadVerificarVehiculos extends Thread {

	private PanelRedVial panelRedVial;

	private ArrayList<Vehiculo> vehiculos;

	public ThreadVerificarVehiculos(PanelRedVial panelRedVial) {

		this.panelRedVial = panelRedVial;

		this.vehiculos = panelRedVial.getRedVial().getVehiculos();
	}

	@Override
	public void run() {
		while (true) {
			if (!this.vehiculos.isEmpty()) {
				for (int i = 0; i < this.vehiculos.size(); i++) {
					if (this.vehiculos.get(i).getEstado().equals(Estado.ESPERANDO)) {
						this.panelRedVial.renderVehiculo(vehiculos.get(i));
					}
				}
			}
			General.wait(1);// Disminuir tiempo para la distribucion
		}
	}

	public PanelRedVial getPanelRedVial() {
		return panelRedVial;
	}

	public void setPanelRedVial(PanelRedVial panelRedVial) {
		this.panelRedVial = panelRedVial;
	}

	public ArrayList<Vehiculo> getVehiculos() {
		return vehiculos;
	}

	public void setVehiculos(ArrayList<Vehiculo> vehiculos) {
		this.vehiculos = vehiculos;
	}
}
