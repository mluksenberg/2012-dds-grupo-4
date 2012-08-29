package ar.edu.frba.utn.dds.entrega_3;

/*
 * Clase que uso como valueObjet por el tema de la popularidad
 * 
 */
public class Vuelo {
	private String nroDeVuelo;
	
	private Integer popularidad=0;
	
	public String getNroDeVuelo() {
		return nroDeVuelo;
	}

	public void setNroDeVuelo(String nroDeVuelo) {
		this.nroDeVuelo = nroDeVuelo;
	}

	public Integer getPopularidad() {
		return popularidad;
	}

	public void setPopularidad(Integer popularidad) {
		this.popularidad = popularidad;
	}
	
	
	
}
