package ar.edu.frba.utn.dds.ComprarReservarWindow;

import org.uqbar.arena.actions.MessageSend;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.Window;
import org.uqbar.arena.windows.WindowOwner;


public class ComprarReservarWindow extends Window <ComprarReservarApplication> {
	

	public ComprarReservarWindow(WindowOwner owner,
			ComprarReservarApplication model) {
		super(owner, model);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void createContents(Panel panel) {
		this.setTitle("Aterrizar.com");
		panel.setLayout(new VerticalLayout());
		Panel messagePanel = new Panel(panel)
				.setLayout(new HorizontalLayout());
		new Label(messagePanel).bindValueToProperty("mensaje");
		new Button(panel).setCaption("seguir buscando").onClick(new MessageSend(this, "close"));
	}
	
	public void cerrarVentana(){
		new MessageSend(this, "close");
	}
}
