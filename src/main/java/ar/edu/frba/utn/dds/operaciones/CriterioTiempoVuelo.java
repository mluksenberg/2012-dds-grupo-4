package ar.edu.frba.utn.dds.operaciones;

import java.util.Comparator;


public class CriterioTiempoVuelo implements Comparator<Itinerario> {

	@Override
	public int compare(Itinerario unItinerario, Itinerario otroItinerario){
		return unItinerario.tiempoDeVuelo().compareTo(otroItinerario.tiempoDeVuelo());
	}
}
