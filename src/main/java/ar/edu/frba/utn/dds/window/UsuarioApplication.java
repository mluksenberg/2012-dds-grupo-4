package ar.edu.frba.utn.dds.window;

import java.util.ArrayList;
import java.util.List;

import org.uqbar.commons.utils.Observable;

import ar.edu.frba.utn.dds.aerolineasAdapters.Aerolinea;
import ar.edu.frba.utn.dds.aerolineasAdapters.Lanchita;
import ar.edu.frba.utn.dds.aerolineasAdapters.Oceanic;
import ar.edu.frba.utn.dds.operaciones.Aterrizar;
import ar.edu.frba.utn.dds.usuarios.Estandar;
import ar.edu.frba.utn.dds.usuarios.Usuario;

@Observable
public class UsuarioApplication {

	public UsuarioApplication(){
		this.getAerolineas().add(new Lanchita());
		this.getAerolineas().add(new Oceanic());
		this.setUsuario(new Usuario("Marcelo", "Lopez", "36747012", new Estandar(),new Aterrizar(this.getAerolineas())));
	}
	
	private Usuario usuario;
	private List<Aerolinea> aerolineas=new ArrayList<Aerolinea>();

	public String getNombre(){
		return this.getUsuario().getNombre();
	}
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Aerolinea> getAerolineas() {
		return aerolineas;
	}

	public void setAerolineas(List<Aerolinea> aerolineas) {
		this.aerolineas = aerolineas;
	}
}
