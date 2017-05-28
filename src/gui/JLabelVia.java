package gui;

import java.awt.Color;

import javax.swing.JLabel;

import herramientas.Constantes;

@SuppressWarnings("serial")
public class JLabelVia extends JLabel {

	public JLabelVia(int x, int y, int width, int height) {
		this.setBackground(Color.GRAY);
		this.setOpaque(true);
		this.setLayout(null);
		this.setBounds(x, y, width, height);
		
		this.agregarSeparadores();
	}

	/**
	 * Dibuja los separadores de la via.
	 */
	private void agregarSeparadores() {
		if (this.getWidth() > this.getHeight()) {
			int flag = 0;
			while (flag < this.getWidth()) {
				JLabel jLabel = new JLabel();
				jLabel.setBounds(flag * 2, this.getHeight() / 2, Constantes.LARGO_SEPARADOR, 1);
				jLabel.setBackground(Color.YELLOW);
				jLabel.setOpaque(true);
				this.add(jLabel);
				flag += 20;
			}
		} else {
			int flag = 0;
			while (flag < this.getHeight()) {
				JLabel jLabel = new JLabel();
				jLabel.setBounds(this.getWidth() / 2, flag * 2, 1, Constantes.LARGO_SEPARADOR);
				jLabel.setBackground(Color.YELLOW);
				jLabel.setOpaque(true);
				this.add(jLabel);
				flag += 20;
			}
		}
	}
}
