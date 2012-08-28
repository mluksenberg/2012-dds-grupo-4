package ar.edu.frba.utn.dds.entrega_2;

import java.util.ArrayList;
import java.util.List;

import ar.edu.frba.utn.dds.entrega_1.Fecha;
import ar.edu.frba.utn.dds.entrega_3.LaReservaNoCorrespondeAlUsuarioExeption;
import ar.edu.frba.utn.dds.entrega_3.NoAdmiteReservaExeption;
import ar.edu.frba.utn.dds.entrega_3.Reserva;
import ar.edu.frba.utn.dds.entrega_3.UsuarioInvalidoParaReservaExeption;

public class Aterrizar {

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
			asientos.addAll(this.asientosDisponiblesDeUnaAerolinea(unOrigen,
					unDestino, fecha, user, aerolinea));
		}
		return asientos;
	}

	/*
	 * Metodo privado que se encarga de la validacion de la primera reserva.
	 */
	protected boolean esElUsuarioQueReservoOriginalmente(Asiento unAsiento,
			Usuario unUsuario) {
		return unAsiento.getReservaPosta().getDni().equals(unUsuario.getDni());
	}

	public void comprar(Asiento unAsiento, Usuario user) {
		if (unAsiento.getEstado().equals("R")
				&& !this.esElUsuarioQueReservoOriginalmente(unAsiento, user)) {
			throw new LaReservaNoCorrespondeAlUsuarioExeption();
		}
		unAsiento.getAerolinea().comprar(unAsiento);
		unAsiento.getReservas().clear();
	}

	/*
	 * Este metodo reserva un asiento que se le pasa por parametro, si no tiene
	 * reservas le avisa a la aerolinea que lo reserve, de lo contrario registra
	 * una nueva sobrereserva En el asiento guardamos dichas reservas y la del
	 * index 0 corresponde a la reserva posta (la oriyinal)
	 */

	public void reservar(Asiento unAsiento, Usuario usuario) {
		if (!(usuario.getTipo() instanceof Estandar)) {
			throw new UsuarioInvalidoParaReservaExeption();
		}
		if (!unAsiento.getAerolinea().admiteReserva()) {
			throw new NoAdmiteReservaExeption();
		}
		// discutimos 2 opciones de diseño.. 1.Usando exepcion que tira el
		// metodo reserva de lanchitaPosta
		// 2. usando este if
		if (!(unAsiento.getEstado().equals("R"))) {
			unAsiento.getAerolinea().reservarAsiento(unAsiento, usuario);
		}
		unAsiento.guardarReserva(new Reserva(unAsiento, usuario.getDni()));
	}

	private float calcularPrecioDeUnAsiento(String precio, Usuario unUsuario,
			Aerolinea unaAerolinea) {
		float precioOriginal = new Float(precio);
		return precioOriginal + precioOriginal * unaAerolinea.getImpuesto()
				+ unUsuario.getTipo().getRecargo();
	}

	// public void obtenerItinerariosDeViaje(String unOrigen, String unDestino,
	// Fecha fecha, Usuario user, Aerolinea unaAerolinea) {
	// // Tengo que obtener todos los asientos disponibles con ese origen y
	// // destino null
	// // Tengo que obtener todos los asientos disponibles con ese destino y
	// // origen null
	// // tengo que fijarme por cada asiento de los origenes y por cada asiento
	// // de los destinos si son o no iguales
	// // en caso que sean iguales tengo que meterlos en el itinerario (no se
	// // como hacerlos)
	// // en caso quie NO sean iguales tengo que volver a llamar el metodo pero
	// // con los destinos de los origenes y los origenes de los destinos.
	// // por ahora pensamos en terminarlo con un contador. Sin embargo hay que
	// // pensar como devolver la lista de itinerarios
	// List<Asiento> asientosOrigenes =
	// this.asientosDisponiblesDeUnaAerolinea(unOrigen,null, fecha, user,
	// unaAerolinea);
	// List<Asiento> asientosDestinos =
	// this.asientosDisponiblesDeUnaAerolinea(null, unDestino, fecha, user,
	// unaAerolinea);
	// Itinerario unItinerario = new Itinerario();
	//
	// for(Asiento unAsientoOrigen: asientosOrigenes){
	// for(Asiento unAsientoDestino: asientosDestinos){
	// if(unAsientoOrigen.getDestino() != unAsientoDestino.getOrigen()){
	// obtenerItinerariosDeViaje(unAsientoOrigen.getDestino(),
	// unAsientoDestino.getOrigen(), fecha, user,
	// unaAerolinea,asientosResultado);
	// }
	// unItinerario.getAsientos().add(unAsientoOrigen);
	// unItinerario.getAsientos().add(unAsientoDestino);
	// }
	// //Aca tengo que cargar la lista de itinerarios.
	// }
	// }

	public List<Itinerario> obtenerItinerariosQueSatisfaganUnViajeDeUnaEscala(
			String unOrigen, String unDestino, Fecha fecha, Usuario user,
			Aerolinea unaAerolinea) {
		List<Itinerario> itinerarios = new ArrayList<Itinerario>();

		List<Asiento> asientosOrigenes = this
				.asientosDisponiblesDeUnaAerolinea(unOrigen, null, fecha, user,
						unaAerolinea);
		List<Asiento> asientosDestinos = this
				.asientosDisponiblesDeUnaAerolinea(null, unDestino, fecha,
						user, unaAerolinea);

		for (Asiento unAsientoOrigen : asientosOrigenes) {
			for (Asiento unAsientoDestino : asientosDestinos) {
				if (unAsientoOrigen.getDestino().equals(
						unAsientoDestino.getOrigen())) {
					Itinerario unItinerario = new Itinerario();
					unItinerario.getAsientos().add(unAsientoOrigen);
					unItinerario.getAsientos().add(unAsientoDestino);
					itinerarios.add(unItinerario);
				}
			}
		}

		// if(itinerarios.size()==0){
		// throw new NoExistenEscalasPosiblesExeption();
		// }

		return itinerarios;

	}

	public List<Itinerario> obtenerItinerariosQueSatisfaganUnViajeDeDosEscalas(
			String unOrigen, String unDestino, Fecha fecha, Usuario user,
			Aerolinea unaAerolinea) {
		List<Itinerario> itinerarios = new ArrayList<Itinerario>();

		List<Asiento> asientosOrigenes = this
				.asientosDisponiblesDeUnaAerolinea(unOrigen, null, fecha, user,
						unaAerolinea);
		List<Asiento> asientosDestinos = this
				.asientosDisponiblesDeUnaAerolinea(null, unDestino, fecha,
						user, unaAerolinea);

		// FIXME ¿3 for anidados? Dale que va!
		for (Asiento unAsientoOrigen : asientosOrigenes) {
			for (Asiento unAsientoDestino : asientosDestinos) {
				List<Itinerario> itinerariosIntermedios = obtenerItinerariosQueSatisfaganUnViajeDeUnaEscala(
						unAsientoOrigen.getOrigen(),
						unAsientoDestino.getOrigen(), fecha, user, unaAerolinea);
				for (Itinerario unItinerarioIntermedio : itinerariosIntermedios) {
					if (unItinerarioIntermedio.getAsientos().get(1)
							.getDestino().equals(unAsientoDestino.getOrigen())) {
						Itinerario unItinerario = new Itinerario();
						unItinerario.getAsientos().addAll(
								unItinerarioIntermedio.getAsientos());
						unItinerario.getAsientos().add(unAsientoDestino);
						itinerarios.add(unItinerario);
					}
				}
			}
		}
		return itinerarios;

	}
	
	public List<Itinerario> obtenerItinerariosDirectos(String unOrigen,
			String unDestino, Fecha fecha, Usuario user, Aerolinea unaAerolinea){
		List<Itinerario> itinerariosToReturn = new ArrayList<Itinerario>();
		List<Asiento> asientosDirectos = this
				.asientosDisponiblesDeUnaAerolinea(unOrigen, unDestino, fecha,
						user, unaAerolinea);
		if (asientosDirectos.size() != 0) {
			for (Asiento unAsientoDirecto : asientosDirectos) {
				Itinerario unItinerario = new Itinerario();
				unItinerario.getAsientos().add(unAsientoDirecto);
				itinerariosToReturn.add(unItinerario);
			}
		}
		return itinerariosToReturn;
	}
	
	public List<Itinerario> obtenerItinerarios(String unOrigen,
			String unDestino, Fecha fecha, Usuario user, Aerolinea unaAerolinea) {
		List<Itinerario> itinerariosToReturn = new ArrayList<Itinerario>();
		
		//Busca en los niveles de escala y se trae todos los itinerarios que encuentre
		itinerariosToReturn.addAll(this.obtenerItinerariosDirectos(unOrigen, unDestino, fecha, user, unaAerolinea));
		
		itinerariosToReturn
				.addAll(this.obtenerItinerariosQueSatisfaganUnViajeDeUnaEscala(
						unOrigen, unDestino, fecha, user, unaAerolinea));
		itinerariosToReturn
				.addAll(this.obtenerItinerariosQueSatisfaganUnViajeDeDosEscalas(
						unOrigen, unDestino, fecha, user, unaAerolinea));

		return itinerariosToReturn;
	}

}
