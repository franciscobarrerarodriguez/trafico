package gui;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

import ambiente.RedVial;

public class VentanaPrincipal extends JFrame {

	private RedVial redVial;
	
	// Componentes
	public JMenuItem jMenuItemSalir;
	public JMenuItem jMenuItemconfiguracion;

	private EventosVentanaPrincipal eventosVentanaPrincipal;

	public VentanaPrincipal() {

		this.setTitle("Simulador de tráfico");
		this.setSize(1000, 650);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLayout(null);

		this.init();
	}

	private void init() {

		this.eventosVentanaPrincipal = new EventosVentanaPrincipal(this);

		JMenuBar jMenuBar = new JMenuBar();

		JMenu jMenuArchivo = new JMenu("Archivo");

		this.jMenuItemconfiguracion = new JMenuItem("Configuracion");
		this.jMenuItemSalir = new JMenuItem("Salir");
		this.jMenuItemSalir.addActionListener(this.eventosVentanaPrincipal);

		jMenuArchivo.add(this.jMenuItemconfiguracion);
		jMenuArchivo.add(new JSeparator());
		jMenuArchivo.add(this.jMenuItemSalir);

		jMenuBar.add(jMenuArchivo);

		this.setJMenuBar(jMenuBar);
	}

	public static void main(String[] args) {

		VentanaPrincipal principal = new VentanaPrincipal();
		principal.setVisible(true);
	}
}
