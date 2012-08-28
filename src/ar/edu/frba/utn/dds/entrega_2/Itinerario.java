package ar.edu.frba.utn.dds.entrega_2;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Itinerario {
	private List<Asiento> asientos = new ArrayList<Asiento>();
	private BigDecimal precioTotal=new BigDecimal(0);
	
	public List<Asiento> getAsientos() {
		return asientos;
	}

	public void setAsientos(List<Asiento> asientos) {
		this.asientos = asientos;
	}

	public BigDecimal getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(BigDecimal precioTotal) {
		this.precioTotal = precioTotal;
	}

}
