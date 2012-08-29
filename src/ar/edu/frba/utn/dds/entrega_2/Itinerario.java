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

		for(int i=1;i<this.getAsientos().size();i++){
			tiempo+=this.getAsientos().get(i).getFechaLlegada().getFecha().getTime()-this.getAsientos().get(i).getFechaSalida().getFecha().getTime();
			tiempo+=(this.getAsientos().get(i).getFechaSalida().getFecha().getTime()-this.getAsientos().get(i-1).getFechaLlegada().getFecha().getTime());
		}
		
		return tiempo;
	}
	
	public Integer popularidad(){
		Integer popu= new Integer(0);
			for(Asiento unAsiento: this.getAsientos()){
				popu+=unAsiento.obtenerPopularidadDelVuelo();
			}
		return popu;
	}
	
}
