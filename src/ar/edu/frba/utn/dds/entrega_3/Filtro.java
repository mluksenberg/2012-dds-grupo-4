package ar.edu.frba.utn.dds.entrega_3;

import java.util.List;

import ar.edu.frba.utn.dds.entrega_2.Itinerario;

public abstract class Filtro {
	public abstract List<Itinerario> filtrarItinerarios(List<Itinerario> itinerarios);
}
