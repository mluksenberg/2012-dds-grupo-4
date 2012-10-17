package ar.edu.frba.utn.dds.BuscadorWindow;

import org.uqbar.arena.actions.MessageSend;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import ar.edu.frba.utn.dds.operaciones.Asiento;

@SuppressWarnings("serial")
public class BuscadorWindow extends SimpleWindow<BuscadorApplication> {

	public BuscadorWindow(WindowOwner parent, BuscadorApplication model) {
		super(parent, model);
	}

	@Override
	protected void createMainTemplate(Panel mainPanel) {
		this.setTitle("Buscador de Asientos");
		this.setTaskDescription("Ingrese los parametros de busqueda");

		super.createMainTemplate(mainPanel);
	}
	
	@Override
	protected void addActions(Panel panel) {
		new Button(panel).setCaption("Comprar").onClick(new MessageSend(this.getModelObject(), "buy"));
		new Button(panel).setCaption("Reservar").onClick(new MessageSend(this.getModelObject(), "book"));
		new Button(panel).setCaption("Cerrar").onClick(new MessageSend(this, "close"));
	}

	
	@Override
	protected void createFormPanel(Panel mainPanel) {
		Panel searchFormPanel = new Panel(mainPanel);
		searchFormPanel.setLayout(new HorizontalLayout());

		new Label(searchFormPanel).setText("Origen ");
		new TextBox(searchFormPanel).bindValueToProperty("origen");

		new Label(searchFormPanel).setText(" Destino");
		new TextBox(searchFormPanel).bindValueToProperty("destino");
		Panel searchFormPanel2 = new Panel(mainPanel).setLayout(new HorizontalLayout());
		new Label(searchFormPanel2).setText("Fecha   ");
		new TextBox(searchFormPanel2).bindValueToProperty("fecha");
		
		new Button(mainPanel).setCaption("Buscar").onClick(new MessageSend(this.getModelObject(), "search"));
		
		Table<Asiento> table = new Table<Asiento>(mainPanel, Asiento.class);
		table.setHeigth(400);
		table.setWidth(600);
		table.bindItemsToProperty("asientos");
		table.bindValueToProperty("asientoSeleccionado");
		this.setearColumnas(table);
		
	
	}

	protected void setearColumnas(Table<Asiento> table){
		new Column<Asiento>(table) //
		.setTitle("Aerolinea")
		.setFixedSize(100)
		.bindContentsToProperty("nombreAerolinea");
		
		
		new Column<Asiento>(table) //
		.setTitle("Vuelo")
		.setFixedSize(100)
		.bindContentsToProperty("nroVuelo");
		
		new Column<Asiento>(table) //
		.setTitle("Asiento")
		.setFixedSize(100)
		.bindContentsToProperty("numeroDeAsiento");
		
		new Column<Asiento>(table) //
		.setTitle("Precio")
		.setFixedSize(100)
		.bindContentsToProperty("precio");
		
		new Column<Asiento>(table) //
		.setTitle("Ubicacion")
		.setFixedSize(100)
		.bindContentsToProperty("ubicacion");
		
		new Column<Asiento>(table) //
		.setTitle("Clase")
		.setFixedSize(100)
		.bindContentsToProperty("clase");
		
	}
	
}
