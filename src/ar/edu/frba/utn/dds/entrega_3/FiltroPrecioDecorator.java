package ar.edu.frba.utn.dds.entrega_3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import ar.edu.frba.utn.dds.entrega_2.Itinerario;

public class FiltroPrecioDecorator extends DecoratorFiltro {
	private BigDecimal precioMinimo;
	private BigDecimal precioMaximo;
	
	public FiltroPrecioDecorator(BigDecimal unPrecioMinimo, BigDecimal unPrecioMaximo){
		this.precioMinimo = unPrecioMinimo;
		this.precioMaximo = unPrecioMaximo;
	}
	
	public List<Itinerario> filtrarItinerarios(List<Itinerario> itinerarios){
		List<Itinerario> itinerariosFiltradosAnteriormente = super.filtrarItinerarios(itinerarios);
		if( super.getFiltro() == null ) itinerariosFiltradosAnteriormente = itinerarios;
		List<Itinerario> itinerariosFiltrados = new ArrayList<Itinerario>();
		for(Itinerario unItinerario: itinerariosFiltradosAnteriormente){
			if( this.elItinerarioTieneUnPrecioEntre(this.getPrecioMinimo(), this.getPrecioMaximo(), unItinerario ) ){
				itinerariosFiltrados.add(unItinerario);
			}
		}
		return itinerariosFiltrados;
	}

	private boolean elItinerarioTieneUnPrecioEntre(BigDecimal unPrecioMinimo, BigDecimal precioMaximo, Itinerario unItinerario) {
		return this.getPrecioMinimo().min(unItinerario.precioTotal()) == this.getPrecioMinimo() && this.getPrecioMaximo().max(unItinerario.precioTotal()) == this.getPrecioMaximo(); 
	}

	public BigDecimal getPrecioMinimo() {
		return precioMinimo;
	}

	public void setPrecio(BigDecimal precioMinimo) {
		this.precioMinimo = precioMinimo;
	}

	public BigDecimal getPrecioMaximo() {
		return precioMaximo;
	}

	public void setPrecioMaximo(BigDecimal precioMaximo) {
		this.precioMaximo = precioMaximo;
	}
	
}
