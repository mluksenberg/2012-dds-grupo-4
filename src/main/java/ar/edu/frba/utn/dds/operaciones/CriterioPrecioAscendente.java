package ar.edu.frba.utn.dds.operaciones;

import java.util.Comparator;


public class CriterioPrecioAscendente implements Comparator<Itinerario> {
	
	@Override
	public int compare(Itinerario unItinerario, Itinerario otroItinerario){
		return unItinerario.precioTotal().compareTo(otroItinerario.precioTotal());
	}
}
