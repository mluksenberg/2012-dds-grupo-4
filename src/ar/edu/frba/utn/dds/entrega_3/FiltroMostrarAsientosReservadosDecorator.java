package ar.edu.frba.utn.dds.entrega_3;

import java.util.ArrayList;
import java.util.List;

import ar.edu.frba.utn.dds.entrega_2.Aerolinea;
import ar.edu.frba.utn.dds.entrega_2.Itinerario;

public class FiltroMostrarAsientosReservadosDecorator extends DecoratorFiltro{
	private boolean mostrarReservados;
	
	public FiltroMostrarAsientosReservadosDecorator(boolean mostrarReservados){
		this.mostrarReservados = mostrarReservados;
	}

	public List<Itinerario> filtrarItinerarios(List<Itinerario> itinerarios){
		List<Itinerario> itinerariosFiltradosAnteriormente = super.filtrarItinerarios(itinerarios);
		if( super.getFiltro() == null ) itinerariosFiltradosAnteriormente = itinerarios;
		List<Itinerario> itinerariosFiltrados = new ArrayList<Itinerario>();
		for(Itinerario unItinerario: itinerariosFiltradosAnteriormente){
			if( this.getMostrarReservados() ){
				Aerolinea unaAerolinea = unItinerario.getAsientos().get(0).getAerolinea();
				itinerariosFiltrados.addAll(unaAerolinea.getItinerariosReservados());
			}
		}
		itinerariosFiltrados.addAll(itinerariosFiltradosAnteriormente);
		return itinerariosFiltrados;
	}
	
	public boolean getMostrarReservados() {
		return mostrarReservados;
	}

	public void setMostrarReservados(boolean mostrarReservados) {
		this.mostrarReservados = mostrarReservados;
	}
	
}
