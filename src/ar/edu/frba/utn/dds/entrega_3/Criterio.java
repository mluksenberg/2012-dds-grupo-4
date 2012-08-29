package ar.edu.frba.utn.dds.entrega_3;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ar.edu.frba.utn.dds.entrega_2.Itinerario;

public class Criterio {
	
	private Comparator<Itinerario> comparador;

	public Criterio(){
	}
	

	public void setComparador(Comparator<Itinerario> comparador) {
		this.comparador = comparador;
	}

	public Comparator<Itinerario> getComparador() {
		return comparador;
	}
	public List<Itinerario> ordenarSegunCriterio(List<Itinerario> itinerarios){
		Collections.sort(itinerarios, this.getComparador());
		return itinerarios;
	}
}
