package ar.edu.frba.utn.dds.operaciones;

import java.util.Comparator;


public class CriterioPopularidad implements Comparator<Itinerario>{

	@Override
	public int compare(Itinerario unItinerario, Itinerario otroItinerario) {
		return otroItinerario.popularidad().compareTo(unItinerario.popularidad());
	}
	
	
	
}
