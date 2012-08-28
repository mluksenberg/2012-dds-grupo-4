package ar.edu.frba.utn.dds.entrega_2;

import java.util.ArrayList;
import java.util.List;

public class FiltroAsientosReservados {
	private boolean mostrarReservados;
	
	public FiltroAsientosReservados(boolean mostrarReservados){
		this.setMostrarReservados(mostrarReservados);
	}
	
	public boolean getMostrarReservados() {
		return mostrarReservados;
	}
	
	public void setMostrarReservados(boolean mostrarReservados) {
		this.mostrarReservados = mostrarReservados;
	}

}
