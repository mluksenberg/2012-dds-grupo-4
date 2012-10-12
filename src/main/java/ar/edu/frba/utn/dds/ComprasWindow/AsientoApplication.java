package ar.edu.frba.utn.dds.ComprasWindow;

import java.util.ArrayList;
import java.util.List;

import org.uqbar.commons.utils.Observable;

import ar.edu.frba.utn.dds.operaciones.Asiento;
import ar.edu.frba.utn.dds.usuarios.Usuario;


/*
 * APPLICATION MODEL DE LA PANTALLA COMPRAS
 */
@Observable
public class AsientoApplication {
	
	private Asiento asientoSeleccionado;
	private List<Asiento> asientos=new ArrayList<Asiento>();
	private Usuario user;
	
	public AsientoApplication(Usuario usr){
		this.setUser(usr);
	}
	
	public Asiento getAsientoSeleccionado() {
		return asientoSeleccionado;
	}

	public void setAsientoSeleccionado(Asiento asientoSeleccionado) {
		this.asientoSeleccionado = asientoSeleccionado;
	}

	public List<Asiento> getAsientos() {
		return asientos;
	}

	public void setAsientos(List<Asiento> asientos) {
		this.asientos = asientos;
	}
	
	public void search(){
		this.asientos=getUser().getAsientosComprados();
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public String getNombre() {
		return this.getUser().getNombre();
	}

	

	

	

}
