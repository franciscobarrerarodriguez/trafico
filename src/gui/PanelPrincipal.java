package gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

import ambiente.RedVial;
import herramientas.Constantes;
import herramientas.General;
import herramientas.TipoVehiculo;
import poblacion.Vehiculo;

@SuppressWarnings("serial")
public class PanelPrincipal extends JPanel {

	// Ancho de calzada
	private int tamanoViaX;
	// Ancho de carril
	private int tamanoCarrilX;
	// Ancho de calzada
	private int tamanoViaY;
	// Ancho de carril
	private int tamanoCarrilY;

	private RedVial redVial;

	private Thread threadGenerarTrasporte;

	public PanelPrincipal(int tamanoViaX, int tamanoViaY, RedVial redVial) {

		this.setLayout(null);

		this.tamanoViaX = tamanoViaX;
		this.tamanoCarrilX = tamanoViaX / 2;

		this.tamanoViaY = tamanoViaY;
		this.tamanoCarrilY = tamanoViaY / 2;

		this.redVial = redVial;

		System.out.println("Ancho vias x: " + this.tamanoViaX + "px, Alto vias y: " + this.tamanoViaY + "px");
		System.out.println("Ancho carril x: " + this.tamanoCarrilX + "px, Ancho carril y: " + this.tamanoCarrilY);

//		generarTransporte();
		// JLabel jLabel = new JLabel("hola");
		// jLabel.setBounds(30, 40, 40, 40);
		// this.add(jLabel);
		// this.add(new JLabel("fdgdfgdf"));
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(Constantes.COLOR_VERDE);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		// Dibujar viasX.
		for (int i = 0; i < this.redVial.getAncho(); i++) {
			if ((this.redVial.getMallaVial()[i][0] == 1)
					&& (this.redVial.getMallaVial()[i][this.redVial.getAlto() - 1] == 1)) {
				g.setColor(Color.GRAY);
				g.fillRect(i * this.tamanoViaX, 0, this.tamanoViaX, this.getHeight());
				g.setColor(Color.YELLOW);
				int flag = 0;
				while (flag < this.getHeight()) {
					g.fillRect((i * this.tamanoViaX) + this.tamanoCarrilX, flag * 2, 1, Constantes.LARGO_SEPARADOR);
					flag += 20;
				}
			}
		}
		// Dibujar viasY.
		for (int i = 0; i < this.redVial.getAlto(); i++) {
			if ((this.redVial.getMallaVial()[0][i] == 1)
					&& (this.redVial.getMallaVial()[this.redVial.getAncho() - 1][i] == 1)) {
				g.setColor(Color.GRAY);
				g.fillRect(0, i * this.tamanoViaY, this.getWidth(), this.tamanoViaY);
				g.setColor(Color.YELLOW);
				int flag = 0;
				while (flag < this.getWidth()) {
					g.fillRect(flag * 2, (i * this.tamanoViaY) + this.tamanoCarrilY, Constantes.LARGO_SEPARADOR, 1);
					flag += 20;
				}
			}
		}
	}

}
