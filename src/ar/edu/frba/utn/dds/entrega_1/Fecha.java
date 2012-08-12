package ar.edu.frba.utn.dds.entrega_1;

import java.util.Date;

public class Fecha {
	private Date fecha;
	final int MILISEC_PER_DAYS= 1000*60*60*24;
	
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public int diferenciaDeDias(Fecha otraFecha){
		return ((int)(Math.abs(otraFecha.getFecha().getTime() - this.getFecha().getTime()))/MILISEC_PER_DAYS);
	}
	
	public boolean esPosteriorA(Fecha otraFecha){
		return this.getFecha().after(otraFecha.getFecha());
	}
	
}
