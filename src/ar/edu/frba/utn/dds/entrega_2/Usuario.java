package ar.edu.frba.utn.dds.entrega_2;

import java.util.ArrayList;
import java.util.List;

import ar.edu.frba.utn.dds.entrega_1.Fecha;



public class Usuario {
	private String nombre;
	private String apellido;
	private String dni;
	private List<Busqueda> busqueda = new ArrayList<Busqueda>();
	private TipoUsuario tipo;
	private Aterrizar aterrizar;
	
	public Usuario(String unNombre, String unApellido, String unDni, TipoUsuario unTipo, Aterrizar aterrizar){
		this.nombre = unNombre;
		this.apellido = unApellido;
		this.dni = unDni;
		this.tipo = unTipo;
		this.aterrizar=aterrizar;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public List<Busqueda> getBusqueda() {
		return busqueda;
	}
	public void setBusqueda(List<Busqueda> busqueda) {
		this.busqueda = busqueda;
	}
	public TipoUsuario getTipo() {
		return tipo;
	}
	public void setTipo(TipoUsuario tipo) {
		this.tipo = tipo;
	}
	
	/**
	 * Busca asientos disponibles en una determinada aerolinea y 
	 * añade la busqueda realizada al perfil del usuario.
	 * 
	 * @param unOrigen
	 * @param unDestino
	 * @param unaFecha
	 * @param unHorario
	 * @param unaAerolinea
	 * @return List<Asiento> - Devuelve una lista de asientos
	 */
	public List<Asiento> buscarAsientoDispobibles(String unOrigen, String unDestino, Fecha unaFecha){
		this.getBusqueda().add(new Busqueda(unOrigen, unDestino,unaFecha));
		return this.getAterrizar().asientosDisponibles(unOrigen, unDestino, unaFecha, this);
	}

	/**
	 * Busca asientos disponibles en una determinada aerolinea 
	 * filtrando también según clase y ubicacion. 
	 * 
	 * @param unOrigen
	 * @param unDestino
	 * @param unaFecha
	 * @param unHorario
	 * @param unaClase
	 * @param unaUbicacion
	 * @param unaAerolinea
	 * @return List
	 * 
	 */
	public List<Asiento> buscarAsientoDispobibles(String unOrigen, String unDestino, Fecha unaFecha,String unaClase, String unaUbicacion){
		List<Asiento> asientosDisponibles = this.buscarAsientoDispobibles(unOrigen, unDestino, unaFecha);
		this.getBusqueda().get(this.getBusqueda().size()-1).setUbicacion(unaUbicacion);
		this.getBusqueda().get(this.getBusqueda().size() -1).setClase(unaClase);
		List<Asiento> asientosFiltrados = new ArrayList<Asiento>();
		for(Asiento unAsiento: asientosDisponibles){
			if(unAsiento.getClase().equals(unaClase) && unAsiento.getUbicacion().equals(unaUbicacion) ){
				asientosFiltrados.add(unAsiento);
			}
		}
		return asientosFiltrados;
	}
	
	public void comprarAsiento(Asiento unAsiento){
		this.getAterrizar().comprar(unAsiento);
	}

	public Aterrizar getAterrizar() {
		return aterrizar;
	}

	public void setAterrizar(Aterrizar aterrizar) {
		this.aterrizar = aterrizar;
	}
}
