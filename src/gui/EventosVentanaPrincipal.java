package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventosVentanaPrincipal implements ActionListener {

	private VentanaPrincipal ventanaPrincipal;

	public EventosVentanaPrincipal(VentanaPrincipal ventanaPrincipal) {
		this.ventanaPrincipal = ventanaPrincipal;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(this.ventanaPrincipal.jMenuItemconfiguracion)) {
			this.ventanaPrincipal.ventanaConfiguracion();
		}
		if (e.getSource().equals(this.ventanaPrincipal.jMenuItemSalir)) {
			System.exit(1);
		}
	}

}
