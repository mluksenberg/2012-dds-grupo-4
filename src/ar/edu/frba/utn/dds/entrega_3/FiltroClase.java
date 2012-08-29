package ar.edu.frba.utn.dds.entrega_3;

import java.util.ArrayList;
import java.util.List;

import ar.edu.frba.utn.dds.entrega_2.Asiento;
import ar.edu.frba.utn.dds.entrega_2.Itinerario;

public class FiltroClase extends Filtro {
	private String clase;
	
	public FiltroClase(String unaClase){
		this.clase = unaClase;
	}
	
	public List<Itinerario> filtrarItinerarios(List<Itinerario> itinerarios){
		List<Itinerario> itinerariosFiltrados = new ArrayList<Itinerario>();
		for(Itinerario unItinerario: itinerarios){
			if( this.losAsientosSonDeLaClase(this.getClase(), unItinerario ) ){
				itinerariosFiltrados.add(unItinerario);
			}
		}
		return itinerariosFiltrados;
	}

	private boolean losAsientosSonDeLaClase(String unaClase, Itinerario unItinerario) {
		for(Asiento unAsiento : unItinerario.getAsientos()){
			if( unAsiento.getClase() != unaClase ) return false;
		}
		return true;
	}

	public String getClase() {
		return clase;
	}

	public void setClase(String clase) {
		this.clase = clase;
	}
}
