package ar.edu.frba.utn.dds.ComprarReservarWindow;

import org.uqbar.arena.actions.MessageSend;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.WindowOwner;

@SuppressWarnings("serial")
public class ReservarAsientoWindow extends ComprarReservarWindow{

	public ReservarAsientoWindow(WindowOwner owner, ComprarReservarApplication model) {
		super(owner, model);
	}

	@Override
	public void createContents(Panel panel) {
		super.createContents(panel);
		if (this.getModelObject().getAsientoSeleccionado().getEstaReservado()){
			this.getModelObject().setMensaje("El asiento ya se encuentra reservado, Que desea hacer?");
			new Button(panel).setCaption("sobre reservar").onClick(new MessageSend(this, "overBook"));
		}
	}
	
	public void overBook(){
		this.getModelObject().reservar();
	}
}
