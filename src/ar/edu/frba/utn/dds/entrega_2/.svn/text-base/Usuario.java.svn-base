package ar.edu.frba.utn.dds.entrega_2;

import java.util.ArrayList;
import java.util.List;

import ar.edu.frba.utn.dds.entrega_1.ConversionException;

public class Usuario {
	private String nombre;
	private String apellido;
	private String dni;
	private List<Busqueda> busqueda = new ArrayList<Busqueda>();
	private TipoUsuario tipo;
	
	public Usuario(String unNombre, String unApellido, String unDni, TipoUsuario unTipo){
		this.nombre = unNombre;
		this.apellido = unApellido;
		this.dni = unDni;
		this.tipo = unTipo;
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
	 * @throws ConversionException
	 */
	public List<Asiento> buscarAsientoDispobibles(String unOrigen, String unDestino, String unaFecha,String unHorario, Aerolinea unaAerolinea) throws ConversionException{
		this.getBusqueda().add(new Busqueda(unOrigen, unDestino, unaFecha, unHorario));
		return unaAerolinea.asientosDisponibles(unOrigen, unDestino, unaFecha, unHorario, this);
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
	 * @throws ConversionException
	 */
	public List<Asiento> buscarAsientoDispobibles(String unOrigen, String unDestino, String unaFecha,String unHorario, String unaClase, String unaUbicacion, Aerolinea unaAerolinea) throws ConversionException{
		List<Asiento> asientosDisponibles = this.buscarAsientoDispobibles(unOrigen, unDestino, unaFecha, unHorario, unaAerolinea);
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
	
	public void comprarAsiento(Asiento unAsiento, Aerolinea unaAerolinea){
		unaAerolinea.comprar(unAsiento);
	}
}
