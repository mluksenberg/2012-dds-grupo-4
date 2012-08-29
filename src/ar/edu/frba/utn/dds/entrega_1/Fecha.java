package ar.edu.frba.utn.dds.entrega_1;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Fecha {
	private Date fecha;
	final int MILISEC_PER_DAYS= 1000*60*60*24;
	private String fechaString;
	@Override
	public String toString(){
		return new SimpleDateFormat().format(this.getFecha());
	}
	
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	/**
	 * Obtiene la diferencia de dias que posee dicha fecha respecto de otra
	 * @param otraFecha
	 * @return int - Diferencia de dias
	 */
	public int diferenciaDeDias(Fecha otraFecha){
		return ((int)(Math.abs(otraFecha.getFecha().getTime() - this.getFecha().getTime()))/MILISEC_PER_DAYS);
	}
	
	/**
	 * Indica si la fecha es posterior o no a otra fecha
	 * 
	 * @param otraFecha
	 * @return true si es una fecha posterior a otra fecha
	 */
	public boolean esPosteriorA(Fecha otraFecha){
		return this.getFecha().after(otraFecha.getFecha());
	}
	
	
	/**
	 * Indica si una fecha es exactamente la misma que otra fecha
	 * 
	 * @param otraFecha
	 * @return true si son las mismas fechas
	 */
	public boolean esLaMismaFechaQue(Fecha otraFecha){
		return (this.getFecha().getTime() - otraFecha.getFecha().getTime() == 0);
	}

	public String getFechaString() {
		return fechaString;
	}

	public void setFechaString(String fechaString) {
		this.fechaString = fechaString;
	}
	
}
