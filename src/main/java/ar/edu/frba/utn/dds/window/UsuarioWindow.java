package ar.edu.frba.utn.dds.window;


import org.uqbar.arena.actions.MessageSend;
import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.MainWindow;
import org.uqbar.arena.windows.SimpleWindow;

import ar.edu.frba.utn.dds.ComprasWindow.AsientoApplication;
import ar.edu.frba.utn.dds.ComprasWindow.ComprasWindow;
import ar.edu.frba.utn.edu.dds.ReservaWindow.ReservaApplication;
import ar.edu.frba.utn.edu.dds.ReservaWindow.ReservaWindow;

@SuppressWarnings("serial")
public class UsuarioWindow extends MainWindow<UsuarioApplication> {


	public UsuarioWindow() {
		super(new UsuarioApplication());
		
	}
	
	
	
	@Override
	public void createContents(Panel panelPrincipal) {
		this.setTitle("Aterrizar.com");
		panelPrincipal.setLayout(new VerticalLayout());
		Panel panelSaludo = new Panel(panelPrincipal)
				.setLayout(new HorizontalLayout());
		new Label(panelSaludo).setText("Hola ");
		new Label(panelSaludo).bindValueToProperty("nombre");
		Panel panelPregunta = new Panel(panelPrincipal)
				.setLayout(new HorizontalLayout());
		new Label(panelPregunta).setText("¿Qué desea hacer?");
		Panel panelBotones = new Panel(panelPrincipal)
				.setLayout(new ColumnLayout(3));
		new Button(panelBotones).setCaption("Ver Compras").onClick(new MessageSend(this, "abrirCompras"));
		new Button(panelBotones).setCaption("Ver Reservas").onClick(new MessageSend(this, "abrirReservas"));
		new Button(panelBotones).setCaption("Buscar Asientos").onClick(null);
	}

	
	public void abrirCompras(){
		SimpleWindow<AsientoApplication> comprasWindows=new ComprasWindow(this,new AsientoApplication(this.getModelObject().getUsuario()));
		comprasWindows.open();
	}
	
	public void abrirReservas(){
		SimpleWindow<AsientoApplication> reservasWindows=new ReservaWindow(this,new ReservaApplication(this.getModelObject().getUsuario()));
		reservasWindows.open();
	}

}