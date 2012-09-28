package ar.edu.frba.utn.dds.entrega_4;

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

}
