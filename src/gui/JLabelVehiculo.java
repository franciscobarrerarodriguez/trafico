package gui;

import java.awt.Component;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import herramientas.Constantes;
import poblacion.Estado;
import poblacion.Vehiculo;

@SuppressWarnings("serial")
public class JLabelVehiculo extends JLabel implements Runnable {

	private Vehiculo vehiculo;

	private PanelRedVial panelRedVial;

	private EventosJLabelVehiculo eventosJLabelVehiculo;

	private Thread thread;

	public JLabelVehiculo(Vehiculo vehiculo, JLayeredPane jLayeredPane) {

		this.vehiculo = vehiculo;
		this.panelRedVial = (PanelRedVial) jLayeredPane;

		this.setBackground(this.vehiculo.getColor());
		this.setOpaque(true);

		this.eventosJLabelVehiculo = new EventosJLabelVehiculo(this);
		this.addMouseListener(this.eventosJLabelVehiculo);

		this.thread = new Thread(this);

	}

	@Override
	public void run() {
		while (this.vehiculo.getEstado().equals(Estado.TRANSITANDO)) {
			this.mover();
		}
	}

	private void mover() {
		try {
			switch (this.vehiculo.getUbicacion()) {
			case ARRIBA:
				this.moverArribaAbajo();
				this.visibilidadArribaAbajo();
				break;
			case DERECHA:
				this.moverDerechaIzquierda();
				this.visibilidadDerechaIzquierda();
				break;
			case ABAJO:
				this.moverAbajoArriba();
				this.visibilidadAbajoArriba();
				break;
			case IZQUIERDA:
				this.moverIzquierdaDerecha();
				this.visibilidadIzquierdaDerecha();
				break;
			}
			Toolkit.getDefaultToolkit().sync();
			this.repaint();
			Thread.sleep(this.vehiculo.getVelocidad());
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	private void moverArribaAbajo() {
		int x = this.getBounds().x;
		int y = this.getBounds().y + Constantes.CAMBIO_PIXELES;
		this.setBounds(x, y, this.getWidth(), this.getHeight());
		if (this.getBounds().y > Constantes.ALTO_VENTANA) {
			this.vehiculo.setEstado(Estado.FINALIZADO);
		}
	}

	private void visibilidadArribaAbajo() {
		int y = this.getBounds().y + this.getHeight();
		int contador = 0;
		boolean encontrado = false;
		while ((contador < this.vehiculo.getVisibilidad()) && (!encontrado)) {
			Component component = this.panelRedVial
					.getComponentAt(this.getBounds().x + (this.panelRedVial.getTamanoCarrilX() / 2), (y + contador));
			if (component instanceof JLabelVehiculo) {
				JLabelVehiculo aux = (JLabelVehiculo) component;
				this.bajarVelocidad(aux.getVehiculo());
				encontrado = true;
			}
			contador++;
		}
	}

	private void moverDerechaIzquierda() {
		int x = this.getBounds().x + Constantes.CAMBIO_PIXELES;
		int y = this.getBounds().y;
		this.setBounds(x, y, this.getWidth(), this.getHeight());
		if (this.getBounds().x > Constantes.ANCHO_VENTANA) {
			this.vehiculo.setEstado(Estado.FINALIZADO);
		}
	}

	private void visibilidadDerechaIzquierda() {
		int x = this.getBounds().x + this.getWidth();
		int contador = 0;
		boolean encontrado = false;
		while ((contador < this.vehiculo.getVisibilidad()) && (!encontrado)) {
			Component component = this.panelRedVial.getComponentAt(x + contador,
					this.getBounds().y + (this.panelRedVial.getTamanoCarrilY() / 2));
			if (component instanceof JLabelVehiculo) {
				JLabelVehiculo aux = (JLabelVehiculo) component;
				this.bajarVelocidad(aux.getVehiculo());
				encontrado = true;
			}
			contador++;
		}
	}

	private void moverAbajoArriba() {
		int x = this.getBounds().x;
		int y = this.getBounds().y - Constantes.CAMBIO_PIXELES;
		this.setBounds(x, y, this.getWidth(), this.getHeight());
		if (this.getBounds().y < (0 - this.vehiculo.getLongitud())) {
			this.vehiculo.setEstado(Estado.FINALIZADO);
		}
	}

	private void visibilidadAbajoArriba() {
		int y = this.getBounds().y;
		int contador = 0;
		boolean encontrado = false;
		while ((contador < this.vehiculo.getVisibilidad()) && (!encontrado)) {
			Component component = this.panelRedVial
					.getComponentAt(this.getBounds().x + (this.panelRedVial.getTamanoCarrilX() / 2), (y - contador));
			if (component instanceof JLabelVehiculo) {
				JLabelVehiculo aux = (JLabelVehiculo) component;
				this.bajarVelocidad(aux.getVehiculo());
				encontrado = true;
			}
			contador++;
		}
	}

	private void moverIzquierdaDerecha() {
		int x = this.getBounds().x - Constantes.CAMBIO_PIXELES;
		int y = this.getBounds().y;
		this.setBounds(x, y, this.getWidth(), this.getHeight());
		if (this.getBounds().x < (0 - this.vehiculo.getLongitud())) {
			this.vehiculo.setEstado(Estado.FINALIZADO);
		}
	}

	private void visibilidadIzquierdaDerecha() {
		int x = this.getBounds().x;
		int contador = 0;
		boolean encontrado = false;
		while ((contador < this.vehiculo.getVisibilidad()) && (!encontrado)) {
			Component component = this.panelRedVial.getComponentAt(x - contador,
					this.getBounds().y + (this.panelRedVial.getTamanoCarrilY() / 2));
			if (component instanceof JLabelVehiculo) {
				JLabelVehiculo aux = (JLabelVehiculo) component;
				this.bajarVelocidad(aux.getVehiculo());
				encontrado = true;
			}
			contador++;
		}
	}

	private void bajarVelocidad(Vehiculo vehiculoAdelante) {
		if (vehiculoAdelante.getVelocidad() > this.vehiculo.getVelocidad()) {
			System.out.println(
					"Bajando velocidad de " + this.vehiculo.getVelocidad() + " a " + vehiculoAdelante.getVelocidad());
			this.vehiculo.setVelocidad(vehiculoAdelante.getVelocidad());
		}
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public PanelRedVial getPanelRedVial() {
		return panelRedVial;
	}

	public void setPanelRedVial(PanelRedVial panelRedVial) {
		this.panelRedVial = panelRedVial;
	}

	public Thread getThread() {
		return thread;
	}

	public void setThread(Thread thread) {
		this.thread = thread;
	}
}
