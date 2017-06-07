package gui;

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

	private Thread thread;

	public JLabelVehiculo(Vehiculo vehiculo, JLayeredPane jLayeredPane) {

		this.vehiculo = vehiculo;
		this.panelRedVial = (PanelRedVial) jLayeredPane;

		this.setBackground(this.vehiculo.getColor());
		this.setOpaque(true);

		this.thread = new Thread(this);
	}

	@Override
	public void run() {
		while (this.vehiculo.getEstado().equals(Estado.TRANSITANDO)) {
			try {
				switch (this.vehiculo.getUbicacion()) {
				case ARRIBA:
					this.moverArribaAbajo();
					break;
				case DERECHA:
					this.moverDerechaIzquierda();
					break;
				case ABAJO:
					this.moverAbajoArriba();
					break;
				case IZQUIERDA:
					this.moverIzquierdaDerecha();
					break;
				}
				Toolkit.getDefaultToolkit().sync();
				this.repaint();
				Thread.sleep(vehiculo.getVelocidad());
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
	}

	private void moverArribaAbajo() {
		int x = this.getBounds().x;
		int y = this.getBounds().y + Constantes.CAMBIO_PIXELES;
		this.setBounds(x, y, this.getWidth(), this.getHeight());
		if (this.getBounds().y > Constantes.ALTO_VENTANA) {
			this.vehiculo.setEstado(Estado.FINALIZADO);
			System.out.println("Arriba abajo muerto");
		}
	}

	private void moverDerechaIzquierda() {
		int x = this.getBounds().x + Constantes.CAMBIO_PIXELES;
		int y = this.getBounds().y;
		this.setBounds(x, y, this.getWidth(), this.getHeight());
		if (this.getBounds().x > Constantes.ANCHO_VENTANA) {
			this.vehiculo.setEstado(Estado.FINALIZADO);
			System.out.println("Derecha izquierda muerto");
		}
	}

	private void moverAbajoArriba() {
		int x = this.getBounds().x;
		int y = this.getBounds().y - Constantes.CAMBIO_PIXELES;
		this.setBounds(x, y, this.getWidth(), this.getHeight());
		if (this.getBounds().y < (0 - this.vehiculo.getLongitud())) {
			this.vehiculo.setEstado(Estado.FINALIZADO);
			System.out.println("Abajo arriba muerto");
		}
	}

	private void moverIzquierdaDerecha() {
		int x = this.getBounds().x - Constantes.CAMBIO_PIXELES;
		int y = this.getBounds().y;
		this.setBounds(x, y, this.getWidth(), this.getHeight());
		if (this.getBounds().x < (0 - this.vehiculo.getLongitud())) {
			this.vehiculo.setEstado(Estado.FINALIZADO);
			System.out.println("Izquierda derecha muerto");
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
