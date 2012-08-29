package ar.edu.frba.utn.dds.entrega_3;

import java.util.ArrayList;
import java.util.List;

import ar.edu.frba.utn.dds.entrega_2.Itinerario;

public abstract class DecoratorFiltro extends Filtro{
	private Filtro filtro;

	public List<Itinerario> filtrarItinerarios(List<Itinerario> itinerarios){
		if( this.getFiltro() != null ){
			return this.getFiltro().filtrarItinerarios(itinerarios);
		}
		return new ArrayList<Itinerario>();
	}
	
	public Filtro getFiltro() {
		return filtro;
	}

	public void setFiltro(Filtro filtro) {
		this.filtro = filtro;
	}
	
}
