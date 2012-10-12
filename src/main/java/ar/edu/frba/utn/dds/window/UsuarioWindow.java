package ar.edu.frba.utn.dds.window;

import java.util.ArrayList;
import java.util.List;

import org.uqbar.arena.actions.MessageSend;
import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.MainWindow;
import org.uqbar.arena.windows.SimpleWindow;

import ar.edu.frba.utn.dds.ComprasWindow.Compra;
import ar.edu.frba.utn.dds.ComprasWindow.ComprasWindow;
import ar.edu.frba.utn.dds.aerolineasAdapters.Aerolinea;
import ar.edu.frba.utn.dds.aerolineasAdapters.Lanchita;
import ar.edu.frba.utn.dds.aerolineasAdapters.Oceanic;
import ar.edu.frba.utn.dds.fechas.Fecha;
import ar.edu.frba.utn.dds.fechas.Parser;
import ar.edu.frba.utn.dds.operaciones.Asiento;
import ar.edu.frba.utn.dds.operaciones.Aterrizar;
import ar.edu.frba.utn.dds.usuarios.Estandar;
import ar.edu.frba.utn.dds.usuarios.Usuario;

@SuppressWarnings("serial")
public class UsuarioWindow extends MainWindow<Usuario> {


	public UsuarioWindow() {
		super(getMarcelo());
		//FIXME hacer un home de algunas compras para este usuario ya que
		//por limitaciones del framework si el usuario no tiene compras 
		//no se carga la pantalla tirando un error de que no hay datos para
		//mostrar en la tabla
		///////////////////////////////
		Parser parser=new Parser();
		parser.agregarFormato("yyyy-MM-dd HH:mm");
		parser.agregarFormato("MM-dd-yyyy HH:mm");
		parser.agregarFormato("dd/MM/yyyy HH:mm");
		parser.agregarFormato("dd/MM/yyyy");
		Fecha unaFecha= parser.parsear("20/12/2012" + " " + "15:20");
		Asiento unAsiento = getMarcelo().buscarAsientoDispobibles("EZE", "PER",unaFecha).get(0);
		getMarcelo().comprarAsiento(unAsiento);
		///////////////////////////////////////////
	}
	
	private static List<Aerolinea> aerolineas=new ArrayList<Aerolinea>();
	private static List<Aerolinea> setAerolineas() {
		aerolineas.add(new Lanchita());
		aerolineas.add(new Oceanic());
		return aerolineas;
	}
	private static Usuario marcelo =new Usuario("Marcelo", "Lopez", "36747012", new Estandar(),new Aterrizar(setAerolineas()));
	
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
		new Button(panelBotones).setCaption("Ver Reservas").onClick(null);
		new Button(panelBotones).setCaption("Buscar Asientos").onClick(null);
	}

	public static Usuario getMarcelo() {
		return marcelo;
	}

	public static void setMarcelo(Usuario marcelo) {
		UsuarioWindow.marcelo = marcelo;
	}
	
	public void abrirCompras(){
		SimpleWindow<Compra> comprasWindows=new ComprasWindow(this,new Compra(getMarcelo()));
		comprasWindows.open();
	}


}