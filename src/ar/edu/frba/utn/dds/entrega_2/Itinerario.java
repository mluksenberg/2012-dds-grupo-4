package ar.edu.frba.utn.dds.entrega_2;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Itinerario {
	private List<Asiento> asientos = new ArrayList<Asiento>();
		
	public List<Asiento> getAsientos() {
		return asientos;
	}

	public void setAsientos(List<Asiento> asientos) {
		this.asientos = asientos;
	}
	
	public BigDecimal precioTotal(){
		BigDecimal precioTotal=new BigDecimal(0);
		for(Asiento unAsiento: this.getAsientos()){
			precioTotal = precioTotal.add(unAsiento.getPrecio());
		}
		return precioTotal;
	}
	
	public Long tiempoDeVuelo(){
		Long tiempo=new Long(0);
		Long tiempoFechaAnt= new Long(0);
		for(Asiento unAsiento:this.getAsientos()){
			tiempo+=unAsiento.getFechaLlegada().getFecha().getTime()-unAsiento.getFechaSalida().getFecha().getTime();
			tiempo+=(unAsiento.getFechaSalida().getFecha().getTime()-tiempoFechaAnt);
			tiempoFechaAnt=unAsiento.getFechaLlegada().getFecha().getTime();
		}
		return tiempo;
	}
	
	//FIXME esto esta mal
	public Integer popularidad(){
		Integer popu= new Integer(0);
			for(Asiento unAsiento: this.getAsientos()){
				if(unAsiento.getEstado().equals("C")){
					popu+=1;
			}
		}
		return popu;
	}
	
}
