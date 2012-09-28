package ar.edu.frba.utn.dds.exeptions;


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
