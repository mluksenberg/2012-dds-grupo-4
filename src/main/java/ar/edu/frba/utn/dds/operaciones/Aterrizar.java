package ar.edu.frba.utn.dds.operaciones;

import java.util.ArrayList;
import java.util.List;

import ar.edu.frba.utn.dds.exeptions.LaReservaNoCorrespondeAlUsuarioExeption;
import ar.edu.frba.utn.dds.exeptions.NoAdmiteReservaExeption;
import ar.edu.frba.utn.dds.exeptions.ParametrosErroneosExeption;
import ar.edu.frba.utn.dds.exeptions.UsuarioInvalidoParaReservaExeption;
import ar.edu.frba.utn.dds.fechas.Fecha;
import ar.edu.frba.utn.dds.usuarios.Estandar;
import ar.edu.frba.utn.dds.usuarios.Usuario;
import ar.edu.frba.utn.dds.usuarios.Vip;




public class Aterrizar{

	private List<Aerolinea> aerolineas;
	
	

	public Aterrizar(List<Aerolinea> aerolineas) {
		this.setAerolineas(aerolineas);

	}

	public List<Aerolinea> getAerolineas() {
		return aerolineas;
	}

	public void setAerolineas(List<Aerolinea> aerolineas) {
		this.aerolineas = aerolineas;
	}

	public List<Asiento> asientosDisponiblesDeUnaAerolinea(String unOrigen,
			String unDestino, Fecha fecha, Usuario user, Aerolinea unaAerolinea) {
		List<Asiento> asientos = new ArrayList<Asiento>();
		asientos.addAll(unaAerolinea.asientosDisponibles(unOrigen, unDestino,
				fecha));
		for (Asiento unAsiento : asientos) {
			unAsiento.setPrecio(this.calcularPrecioDeUnAsiento(
					unAsiento.getPrecioOriginal(), user,
					unAsiento.getAerolinea()));
			if (unAsiento.esSuperOferta() && !(user.getTipo() instanceof Vip)) {
				asientos.remove(unAsiento);
			}
		}
		return asientos;
	}

	public List<Asiento> asientosDisponibles(String unOrigen, String unDestino,
			Fecha fecha, Usuario user) {
		if (unOrigen == null || unDestino == null) {
			throw new ParametrosErroneosExeption();
		}
		List<Asiento> asientos = new ArrayList<Asiento>();
		for (Aerolinea aerolinea : this.getAerolineas()) {
			asientos.addAll(this.asientosDisponiblesDeUnaAerolinea(unOrigen, unDestino,fecha,user, aerolinea));
		}
		return asientos;
	}
	
	/*
	 * Metodo privado que se encarga de la validacion de la primera reserva. 
	 */
	protected boolean esElUsuarioQueReservoOriginalmente(Asiento unAsiento,Usuario unUsuario) {
		return unAsiento.getReservaPosta().getDni().equals(unUsuario.getDni());
	}
	
	public void comprar(Asiento unAsiento,Usuario user) {
		if(unAsiento.getEstado().equals("R") && !this.esElUsuarioQueReservoOriginalmente(unAsiento, user)){
			throw new LaReservaNoCorrespondeAlUsuarioExeption();
		}
		unAsiento.getAerolinea().comprar(unAsiento);
		unAsiento.getReservas().clear();
	}
	
	/*
	 * Este metodo reserva un asiento que se le pasa por parametro, si no tiene reservas le avisa a la aerolinea que
	 * lo reserve, de lo contrario registra una nueva sobrereserva
	 * En el asiento guardamos dichas reservas y la del index 0 corresponde a la reserva posta (la oriyinal)
	 */
	
	public void reservar(Asiento unAsiento,Usuario usuario){
		if(!(usuario.getTipo() instanceof Estandar)){
			throw new UsuarioInvalidoParaReservaExeption();
		}
		if(!unAsiento.getAerolinea().admiteReserva()){
			throw new NoAdmiteReservaExeption();
		}
		//discutimos 2 opciones de diseño.. 1.Usando exepcion que tira el metodo reserva de lanchitaPosta
		//2. usando este if
		if(!(unAsiento.getEstado().equals("R"))){
			unAsiento.getAerolinea().reservarAsiento(unAsiento, usuario);
		}
		unAsiento.guardarReserva(new Reserva(unAsiento,usuario.getDni()));
	}

	private float calcularPrecioDeUnAsiento(String precio, Usuario unUsuario,
			Aerolinea unaAerolinea) {
		float precioOriginal = new Float(precio);
		return precioOriginal + precioOriginal * unaAerolinea.getImpuesto()
				+ unUsuario.getTipo().getRecargo();
	}

	public void obtenerItinerariosDeViaje(String unOrigen, String unDestino, Fecha fecha, Usuario user, Aerolinea unaAerolinea) {
		// Tengo que obtener todos los asientos disponibles con ese origen y
		// destino null
		// Tengo que obtener todos los asientos disponibles con ese destino y
		// origen null
		// tengo que fijarme por cada asiento de los origenes y por cada asiento
		// de los destinos si son o no iguales
		// en caso que sean iguales tengo que meterlos en el itinerario (no se
		// como hacerlos)
		// en caso quie NO sean iguales tengo que volver a llamar el metodo pero
		// con los destinos de los origenes y los origenes de los destinos.
		// por ahora pensamos en terminarlo con un contador. Sin embargo hay que
		// pensar como devolver la lista de itinerarios
		List<Asiento> asientosOrigenes = this.asientosDisponiblesDeUnaAerolinea(unOrigen,null, fecha, user, unaAerolinea);
		List<Asiento> asientosDestinos = this.asientosDisponiblesDeUnaAerolinea(null, unDestino, fecha, user, unaAerolinea);
		Itinerario unItinerario = new Itinerario();
		
		for(Asiento unAsientoOrigen: asientosOrigenes){
			for(Asiento unAsientoDestino: asientosDestinos){
				if(unAsientoOrigen.getDestino() != unAsientoDestino.getOrigen()){
					obtenerItinerariosDeViaje(unAsientoOrigen.getDestino(), unAsientoDestino.getOrigen(), fecha, user, unaAerolinea);
				}
				unItinerario.getAsientos().add(unAsientoOrigen);
				unItinerario.getAsientos().add(unAsientoDestino);
			}
			//Aca tengo que cargar la lista de itinerarios.
		}
	}
}
