package ar.edu.frba.utn.dds.entrega_3;

import java.util.ArrayList;
import java.util.List;

import ar.edu.frba.utn.dds.entrega_2.Asiento;
import ar.edu.frba.utn.dds.entrega_2.Itinerario;

public class FiltroUbicacionDecorator extends DecoratorFiltro {
	private String ubicacion;

	public FiltroUbicacionDecorator(String unaUbicacion){
		this.ubicacion = unaUbicacion;
	}
	
	public List<Itinerario> filtrarItinerarios(List<Itinerario> itinerarios){
		List<Itinerario> itinerariosFiltradosAnteriormente = super.filtrarItinerarios(itinerarios);
		if( super.getFiltro() == null ) itinerariosFiltradosAnteriormente = itinerarios;
		List<Itinerario> itinerariosFiltrados = new ArrayList<Itinerario>();
		for(Itinerario unItinerario: itinerariosFiltradosAnteriormente){
			if( this.losAsientosEstanEnLaUbicacion(this.getUbicacion(), unItinerario ) ){
				itinerariosFiltrados.add(unItinerario);
			}
		}
		return itinerariosFiltrados;
	}
	
	private boolean losAsientosEstanEnLaUbicacion(String unaUbicacion, Itinerario unItinerario) {
		for(Asiento unAsiento: unItinerario.getAsientos()){
			if( unAsiento.getUbicacion() != this.getUbicacion() ) return false;
		}
		return true;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
}
