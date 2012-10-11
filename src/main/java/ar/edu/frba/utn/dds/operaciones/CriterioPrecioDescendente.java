package ar.edu.frba.utn.dds.operaciones;

import java.util.Comparator;


public class CriterioPrecioDescendente implements Comparator<Itinerario> {
	
	@Override
	public int compare(Itinerario unItinerario, Itinerario otroItinerario){
		return otroItinerario.precioTotal().compareTo(unItinerario.precioTotal());
	}
}
