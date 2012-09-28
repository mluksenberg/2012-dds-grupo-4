package ar.edu.frba.utn.dds.entrega_4;

import java.util.List;

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
	public List<Asiento> asientosDisponibles(String unOrigen, String unDestino,Fecha fecha);
	public float getImpuesto();
	public void comprar(Asiento unAsiento);
	public Integer popularidadDeUnVuelo(String unOrigen, String unDestino,Fecha unaFecha);
	public void reservarAsiento(Asiento asiento,Usuario usuario);
	public Integer getMaximaDuracionDeReserva();
	public boolean admiteReserva();
}