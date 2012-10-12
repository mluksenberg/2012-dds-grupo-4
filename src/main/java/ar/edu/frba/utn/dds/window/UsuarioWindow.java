package ar.edu.frba.utn.dds.window;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.MainWindow;
import ar.edu.frba.utn.dds.operaciones.Aterrizar;
import ar.edu.frba.utn.dds.usuarios.Estandar;
import ar.edu.frba.utn.dds.usuarios.Usuario;

@SuppressWarnings("serial")
public class UsuarioWindow extends MainWindow<Usuario> {

	public UsuarioWindow() {
		super(new Usuario("Marcelo", "Lopez", "36747012", new Estandar(),
				new Aterrizar(null)));
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
		new Button(panelBotones).setCaption("Ver Compras").onClick(null);
		new Button(panelBotones).setCaption("Ver Reservas").onClick(null);
		new Button(panelBotones).setCaption("Buscar Asientos").onClick(null);
	}

}