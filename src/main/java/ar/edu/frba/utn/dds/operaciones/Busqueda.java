package ar.edu.frba.utn.dds.operaciones;

import ar.edu.frba.utn.dds.fechas.Fecha;


public class Busqueda {
	private String origen;
	private String destino;
	private Fecha fecha;
	private String clase;
	private String ubicacion;

	public Busqueda(String unOrigen, String unDestino, Fecha unaFecha){
		this.origen = unOrigen;
		this.destino = unDestino;
		this.setFecha(unaFecha);
		this.clase = null;
		this.ubicacion = null;
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
	public String getClase() {
		return clase;
	}
	public void setClase(String clase) {
		this.clase = clase;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	public Fecha getFecha() {
		return fecha;
	}
	public void setFecha(Fecha fecha) {
		this.fecha = fecha;
	}
	
}
