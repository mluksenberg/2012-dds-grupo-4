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
public class Compra {
	
	private Asiento asientoSeleccionado;
	private List<Asiento> asientosComprados=new ArrayList<Asiento>();
	private Usuario user;
	
	public Compra(Usuario usr){
		this.setUser(usr);
	}
	
	public Asiento getAsientoSeleccionado() {
		return asientoSeleccionado;
	}

	public void setAsientoSeleccionado(Asiento asientoSeleccionado) {
		this.asientoSeleccionado = asientoSeleccionado;
	}

	public List<Asiento> getAsientosComprados() {
		return asientosComprados;
	}

	public void setAsientosComprados(List<Asiento> asientosComprados) {
		this.asientosComprados = asientosComprados;
	}
	
	public void search(){
		this.asientosComprados=getUser().getAsientosComprados();
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
