package gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import ambiente.RedVial;
import herramientas.Constantes;

public class PanelPrincipal extends JPanel {

	private int tamanoViaX;
	private int tamanoViaY;

	private RedVial redVial;

	public PanelPrincipal(int tamanoViaX, int tamanoViaY, RedVial redVial) {

		this.tamanoViaX = tamanoViaX;
		this.tamanoViaY = tamanoViaY;

		this.redVial = redVial;

		System.out.println("Ancho " + this.tamanoViaX + "px, Alto" + this.tamanoViaY + "px");
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
				g.fillRect((i * this.tamanoViaX) + (this.tamanoViaX / 2), 0, 1, this.getHeight());
			}
		}
		// Dibujar viasY.
		for (int i = 0; i < this.redVial.getAlto(); i++) {
			if ((this.redVial.getMallaVial()[0][i] == 1)
					&& (this.redVial.getMallaVial()[this.redVial.getAncho() - 1][i] == 1)) {
				g.setColor(Color.GRAY);
				g.fillRect(0, i * this.tamanoViaY, this.getWidth(), this.tamanoViaY);
				g.setColor(Color.YELLOW);
				g.fillRect(0, (i * this.tamanoViaY) + (this.tamanoViaY / 2), this.getWidth(), 1);
			}
		}
	}
}
