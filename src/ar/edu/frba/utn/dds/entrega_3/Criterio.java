package ar.edu.frba.utn.dds.entrega_3;

import ar.edu.frba.utn.dds.entrega_2.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Criterio {
	private Comparator<Asiento> comparador;

	public Criterio(Comparator<Asiento> unComparador){
		this.setComparador(unComparador);
	}
	

	public void setComparador(Comparator<Asiento> comparador) {
		this.comparador = comparador;
	}

	public Comparator<Asiento> getComparador() {
		return comparador;
	}
	public List<Asiento> ordenar(List<Asiento> asientos){
		Collections.sort(asientos, this.getComparador());
		return asientos;
	}
}
