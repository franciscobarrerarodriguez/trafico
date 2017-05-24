package gui;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

import ambiente.RedVial;
import herramientas.Constantes;

public class VentanaPrincipal extends JFrame {

	private RedVial redVial;

	// Componentes
	public PanelPrincipal panelPrincipal;

	public JMenuItem jMenuItemSalir;
	public JMenuItem jMenuItemconfiguracion;

	private EventosVentanaPrincipal eventosVentanaPrincipal;

	public VentanaPrincipal() {

		this.setTitle("Simulador de tráfico");
		this.setSize(Constantes.ANCHO_VENTANA, Constantes.ALTO_VENTANA);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLayout(new GridLayout(1, 1));

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

		// Se define un ancho y alto maximo para la red vial.
		this.redVial = new RedVial(Constantes.DEFAULT_ANCHO, Constantes.DEFAULT_ALTO);

		// Se crea el panel en donde sera dibujada la red vial, y se definen sus
		// medidas de ancho y alto dependiendo del tamano de la ventana.
		this.add(this.panelPrincipal = new PanelPrincipal(this.getWidth() / this.redVial.getAncho(),
				this.getHeight() / this.redVial.getAlto(), this.redVial));
	}

	public void ventanaConfiguracion() {

	}

	public static void main(String[] args) {

		VentanaPrincipal principal = new VentanaPrincipal();
		principal.setVisible(true);
	}
}
