package ar.edu.frba.utn.dds.entrega_2;

public class Busqueda {
	private String origen;
	private String destino;
	private String fecha;
	private String horario;
	private String clase;
	private String ubicacion;

	public Busqueda(String unOrigen, String unDestino, String unaFecha, String unHorario){
		this.origen = unOrigen;
		this.destino = unDestino;
		this.setFecha(unaFecha);
		this.setHorario(unHorario);
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
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getHorario() {
		return horario;
	}
	public void setHorario(String horario) {
		this.horario = horario;
	}
}
