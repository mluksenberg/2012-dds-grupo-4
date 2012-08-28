package ar.edu.frba.utn.dds.entrega_3;

import java.util.Comparator;

import ar.edu.frba.utn.dds.entrega_2.Itinerario;

public class CriterioPrecioAscendente implements Comparator<Itinerario> {
	
	@Override
	public int compare(Itinerario unItinerario, Itinerario otroItinerario){
		return unItinerario.precioTotal().compareTo(otroItinerario.precioTotal());
	}
}
