package ar.edu.frba.utn.dds.ComprasWindow;

import org.uqbar.arena.actions.MessageSend;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import ar.edu.frba.utn.dds.operaciones.Asiento;

@SuppressWarnings("serial")
public class ComprasWindow extends SimpleWindow<Compra> {

	public ComprasWindow(WindowOwner parent, Compra model) {
		super(parent, model);
		this.getModelObject().search();
		
	}

	@Override
	protected void addActions(Panel panelBotones) {
		new Button(panelBotones).setCaption("Cerrar").onClick(new MessageSend(this,"close"));
		
	}

	@Override
	protected void createFormPanel(Panel panel) {
		this.setTitle("Aterrizar.com");
		panel.setLayout(new VerticalLayout());
		Panel panelCompras = new Panel(panel)
				.setLayout(new HorizontalLayout());
		new Label(panelCompras).setText("Compras de");
		new Label(panelCompras).bindValueToProperty("nombre");
		Panel panelGrid = new Panel(panel)
				.setLayout(new VerticalLayout());
		Table<Asiento> table = new Table<Asiento>(panelGrid, Asiento.class);
		table.setHeigth(300);
		table.setWidth(550);
		table.bindItemsToProperty("asientosComprados");
		table.bindValueToProperty("asientoSeleccionado");
		this.setearColumnas(table);
		
		
	}

	private void setearColumnas(Table<Asiento> table) {
		new Column<Asiento>(table).setTitle("Salida").setFixedSize(120).bindContentsToProperty("fechaSalida");
		new Column<Asiento>(table).setTitle("Aerolinea").setFixedSize(120).bindContentsToProperty("nombreAerolinea");
		new Column<Asiento>(table).setTitle("Vuelo").setFixedSize(120).bindContentsToProperty("nroVuelo");
		new Column<Asiento>(table).setTitle("Asiento").setFixedSize(120).bindContentsToProperty("nroAsiento");
		new Column<Asiento>(table).setTitle("Precio").setFixedSize(120).bindContentsToProperty("precio");

	}

}
