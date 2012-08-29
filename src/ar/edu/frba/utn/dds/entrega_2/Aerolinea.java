package ar.edu.frba.utn.dds.entrega_2;

import java.util.List;

import ar.edu.frba.utn.dds.entrega_1.ConversionException;
import ar.edu.frba.utn.dds.entrega_1.Fecha;

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
	public void comprar(Asiento unAsiento, String unDni);
	public void reservarAsiento(Asiento asiento,Usuario usuario);
	public Integer getMaximaDuracionDeReserva();
	public boolean admiteReserva();
	public Integer popularidadDeUnVuelo(String codigoAsientoDeUnVuelo); 
}
