package ar.edu.frba.utn.dds.operaciones;

import java.util.ArrayList;
import java.util.List;



public class FiltroClase extends Filtro {
	private List<String> clases = new ArrayList<String>();
	
	
	public FiltroClase(String unaClase){
		this.clases.add(unaClase);
	}
	
	public List<Itinerario> filtrarItinerarios(List<Itinerario> itinerarios){
		List<Itinerario> itinerariosFiltrados = new ArrayList<Itinerario>();
		for(Itinerario unItinerario: itinerarios){
			if( this.losAsientosSonDeLaClase(this.getClases(), unItinerario ) ){
				itinerariosFiltrados.add(unItinerario);
			}
		}
		return itinerariosFiltrados;
	}

	private boolean losAsientosSonDeLaClase(List<String> clases, Itinerario unItinerario) {
		for(Asiento unAsiento : unItinerario.getAsientos()){
			if( !clases.contains(unAsiento.getClase()) ) return false;
		}
		return true;
	}

	public void addClase(String unaClase){
		this.getClases().add(unaClase);
	}
	
	public List<String> getClases() {
		return clases;
	}

	public void setClases(List<String> clases) {
		this.clases = clases;
	}
}
