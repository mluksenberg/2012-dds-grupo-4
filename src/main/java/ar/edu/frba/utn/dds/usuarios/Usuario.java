package ar.edu.frba.utn.dds.usuarios;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.uqbar.commons.model.ObservableObject;

import ar.edu.frba.utn.dds.exeptions.LaReservaNoCorrespondeAlUsuarioExeption;
import ar.edu.frba.utn.dds.exeptions.NoAdmiteReservaExeption;
import ar.edu.frba.utn.dds.exeptions.NoSePudoComprarExeption;
import ar.edu.frba.utn.dds.exeptions.UsuarioInvalidoParaReservaExeption;
import ar.edu.frba.utn.dds.fechas.Fecha;
import ar.edu.frba.utn.dds.operaciones.Asiento;
import ar.edu.frba.utn.dds.operaciones.Aterrizar;
import ar.edu.frba.utn.dds.operaciones.Busqueda;
import ar.edu.frba.utn.dds.operaciones.Filtro;
import ar.edu.frba.utn.dds.operaciones.Itinerario;



public class Usuario extends ObservableObject{
	private String nombre;
	private String apellido;
	private String dni;
	private List<Busqueda> busqueda = new ArrayList<Busqueda>();
	private TipoUsuario tipo;
	private Aterrizar aterrizar;
	private List<Asiento> asientosComprados=new ArrayList<Asiento>();
	private List<Asiento> asientosReservados=new ArrayList<Asiento>();

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
	
	public List<Itinerario> buscarItinerarios(String unOrigen, String unDestino, Fecha unaFecha){
		return this.getAterrizar().itinerariosDisponibles(unOrigen, unDestino, unaFecha, this);
	}
	
	public List<Itinerario> buscarItinerarios(String unOrigen, String unDestino, Fecha unaFecha, Filtro unFiltro){
		return unFiltro.filtrarItinerarios(this.buscarItinerarios(unOrigen, unDestino, unaFecha));
	}
	
	public List<Itinerario> buscarItinerarios(String unOrigen, String unDestino, Fecha unaFecha,Comparator<Itinerario> criterio){
		return this.getAterrizar().itinerariosDisponibles(unOrigen, unDestino, unaFecha, this,criterio);
	}
	
	public List<Itinerario> buscarItinerarios(String unOrigen, String unDestino, Fecha unaFecha, Filtro unFiltro,Comparator<Itinerario> criterio){
		List<Itinerario> itinerarios=unFiltro.filtrarItinerarios(this.buscarItinerarios(unOrigen, unDestino, unaFecha));
		this.getAterrizar().getOrdenador().setComparador(criterio);
		this.getAterrizar().getOrdenador().ordenarSegunCriterio(itinerarios);
		return itinerarios;
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
	
	public void comprarItinerario(Itinerario unItinerario) throws NoSePudoComprarExeption, LaReservaNoCorrespondeAlUsuarioExeption{
		this.getAterrizar().comprar(unItinerario,this);
		this.getAsientosComprados().addAll(unItinerario.getAsientos());
	}
	
	public void comprarAsiento(Asiento unAsiento) throws LaReservaNoCorrespondeAlUsuarioExeption, NoSePudoComprarExeption{
		this.getAterrizar().comprar(unAsiento,this);
		this.getAsientosComprados().add(unAsiento);
	}
	
	public void reservarItinerario(Itinerario unItinerario) throws UsuarioInvalidoParaReservaExeption, NoAdmiteReservaExeption{
		this.getAterrizar().reservar(unItinerario, this);
		this.getAsientosReservados().addAll(unItinerario.getAsientos());
	}
	
	public void reservarAsiento(Asiento unAsiento) throws UsuarioInvalidoParaReservaExeption, NoAdmiteReservaExeption{
		this.getAterrizar().reservar(unAsiento, this);
		this.getAsientosReservados().add(unAsiento);
	}
	
	public Aterrizar getAterrizar() {
		return aterrizar;
	}

	public void setAterrizar(Aterrizar aterrizar) {
		this.aterrizar = aterrizar;
	}

	public List<Asiento> getAsientosComprados() {
		return asientosComprados;
	}

	public void setAsientosComprados(List<Asiento> asientosComprados) {
		this.asientosComprados = asientosComprados;
	}

	public List<Asiento> getAsientosReservados() {
		return asientosReservados;
	}

	public void setAsientosReservados(List<Asiento> asientosReservados) {
		this.asientosReservados = asientosReservados;
	}
}
