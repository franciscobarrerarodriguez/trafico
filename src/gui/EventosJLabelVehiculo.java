package gui;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.MouseInfo;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class EventosJLabelVehiculo implements MouseListener {

	private Font fontLabel;

	private JLabelVehiculo jLabelVehiculo;

	private JPanel jPanelInfo;

	public EventosJLabelVehiculo(JLabelVehiculo jLabelVehiculo) {
		this.jLabelVehiculo = jLabelVehiculo;
		this.fontLabel = new Font(Font.SANS_SERIF, Font.PLAIN, 10);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		this.jPanelInfo = new JPanel(new GridLayout(3, 1));

		JLabel jLabel1 = new JLabel(
				String.format(" Tipo %s", String.valueOf(this.jLabelVehiculo.getVehiculo().getTipoVehiculo())));
		jLabel1.setFont(this.fontLabel);

		JLabel jLabel2 = new JLabel(String.format(" Velocidad %1$d", this.jLabelVehiculo.getVehiculo().getVelocidad()));
		jLabel2.setFont(this.fontLabel);

		JLabel jLabel3 = new JLabel(
				String.format(" Estado %s", String.valueOf(this.jLabelVehiculo.getVehiculo().getEstado())));
		jLabel3.setFont(this.fontLabel);

		this.jPanelInfo.add(jLabel1);
		this.jPanelInfo.add(jLabel2);
		this.jPanelInfo.add(jLabel3);

		this.jPanelInfo.setBounds(5, 5, 115, 40);
		this.jLabelVehiculo.getPanelRedVial().add(this.jPanelInfo, new Integer(4));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		this.jLabelVehiculo.getPanelRedVial().remove(this.jPanelInfo);
		this.jLabelVehiculo.getPanelRedVial().repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
