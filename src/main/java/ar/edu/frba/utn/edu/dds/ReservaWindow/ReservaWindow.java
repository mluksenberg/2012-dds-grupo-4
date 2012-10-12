package ar.edu.frba.utn.edu.dds.ReservaWindow;

import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.WindowOwner;

import ar.edu.frba.utn.dds.ComprasWindow.ComprasWindow;

@SuppressWarnings("serial")
public class ReservaWindow extends ComprasWindow {

	public ReservaWindow(WindowOwner parent,  ReservaApplication model) {
		super(parent, model);
		this.getModelObject().search();
	}
	
	@Override
	protected void setTitleLabel(Panel panel){
		new Label(panel).setText("Reservas de");

	}
	

}
