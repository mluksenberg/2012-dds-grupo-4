package ar.edu.frba.utn.dds.BuscadorWindow;

import java.util.ArrayList;
import java.util.List;

import org.uqbar.commons.model.UserException;
import org.uqbar.commons.utils.Observable;

import ar.edu.frba.utn.dds.exeptions.LaReservaNoCorrespondeAlUsuarioExeption;
import ar.edu.frba.utn.dds.exeptions.NoAdmiteReservaExeption;
import ar.edu.frba.utn.dds.exeptions.NoSePudoComprarExeption;
import ar.edu.frba.utn.dds.exeptions.UsuarioInvalidoParaReservaExeption;
import ar.edu.frba.utn.dds.fechas.Fecha;
import ar.edu.frba.utn.dds.fechas.Parser;
import ar.edu.frba.utn.dds.operaciones.Asiento;
import ar.edu.frba.utn.dds.usuarios.Usuario;

@Observable
public class BuscadorApplication {

	public BuscadorApplication(Usuario usr) {
		this.setUser(usr);
	}
	private String origen;
	private String destino;
	private String fecha;
	private Usuario user;
	private List<Asiento> asientos=new ArrayList<Asiento>();
	private Asiento asientoSeleccionado;
	private String mensaje;
	
	public void validar(){
		//TODO validacion del formato de la fecha
		if(origen==null || origen.compareToIgnoreCase("")==0){
			throw new UserException("Debe ingresar el origen");
		}
		
		if(destino==null || destino.compareToIgnoreCase("")==0){
			throw new UserException("Debe ingresar el destino");
		}
		
		if(fecha==null || fecha.compareToIgnoreCase("")==0){
			throw new UserException("Debe ingresar la fecha");
		}
	}
	
	public void search(){
		this.validar();
		
		Parser parser = new Parser();
		parser.agregarFormato("yyyy-MM-dd HH:mm");
		parser.agregarFormato("MM-dd-yyyy HH:mm");
		parser.agregarFormato("dd/MM/yyyy HH:mm");
		parser.agregarFormato("dd/MM/yyyy");
		Fecha unaFecha=parser.parsear(getFecha());
		this.setAsientos(this.getUser().buscarAsientoDispobibles(origen, destino, unaFecha));
		
	}
	
	public void buy(){
		this.search();
	}
	
	public void book(){
		this.search();
	}
	
	
	public String getOrigen() {
		return origen;
	}
	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	


	public List<Asiento> getAsientos() {
		return asientos;
	}


	public void setAsientos(List<Asiento> asientos) {
		this.asientos = asientos;
	}


	public Asiento getAsientoSeleccionado() {
		return asientoSeleccionado;
	}


	public void setAsientoSeleccionado(Asiento asientoSeleccionado) {
		this.asientoSeleccionado = asientoSeleccionado;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getMensaje() {
		return mensaje;
	}


}
