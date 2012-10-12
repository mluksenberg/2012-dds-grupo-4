package ar.edu.frba.utn.edu.dds.ReservaWindow;


import ar.edu.frba.utn.dds.ComprasWindow.AsientoApplication;
import ar.edu.frba.utn.dds.usuarios.Usuario;

public class ReservaApplication extends AsientoApplication {

	public ReservaApplication(Usuario usr) {
		super(usr);
		this.search();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void search(){
		this.setAsientos(getUser().getAsientosReservados());
	}
	
	

}
