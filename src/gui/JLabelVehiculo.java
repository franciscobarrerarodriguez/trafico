package gui;

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
			try {
				Thread.sleep(vehiculo.getVelocidad());
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			}
		}
	}

	private void moverArribaAbajo() {
		int x = this.getBounds().x;
		int y = this.getBounds().y + Constantes.CAMBIO_PIXELES;
		this.setBounds(x, y, this.getWidth(), this.getHeight());
		this.repaint();
	}

	private void moverDerechaIzquierda() {
		int x = this.getBounds().x + Constantes.CAMBIO_PIXELES;
		int y = this.getBounds().y;
		this.setBounds(x, y, this.getWidth(), this.getHeight());
		this.repaint();
	}

	private void moverAbajoArriba() {
		int x = this.getBounds().x;
		int y = this.getBounds().y - Constantes.CAMBIO_PIXELES;
		this.setBounds(x, y, this.getWidth(), this.getHeight());
		this.repaint();
	}

	private void moverIzquierdaDerecha() {
		int x = this.getBounds().x - Constantes.CAMBIO_PIXELES;
		int y = this.getBounds().y;
		this.setBounds(x, y, this.getWidth(), this.getHeight());
		this.repaint();
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
