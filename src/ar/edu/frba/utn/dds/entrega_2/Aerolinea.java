package ar.edu.frba.utn.dds.entrega_2;

import java.util.List;

import ar.edu.frba.utn.dds.entrega_1.ConversionException;

public interface Aerolinea {
	
	/**
	 * Busca asientos disponibles en una aerolinea determinada
	 * 
	 * @param unOrigen
	 * @param unDestino
	 * @param unaFecha
	 * @param unHorario
	 * @return Devuelve una lista de asientos
	 * @throws ConversionException
	 */
	public List<Asiento> asientosDisponibles(String unOrigen, String unDestino, String unaFecha, String unHorario, Usuario unUsuario);
	public float getImpuesto();
	public void comprar(Asiento unAsiento);
	public Integer popularidadDeUnVuelo(String unOrigen, String unDestino,String unaFecha,String unHorario);
	
}
