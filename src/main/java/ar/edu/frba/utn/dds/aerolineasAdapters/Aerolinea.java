package ar.edu.frba.utn.dds.aerolineasAdapters;

import java.util.List;

import ar.edu.frba.utn.dds.fechas.Fecha;
import ar.edu.frba.utn.dds.operaciones.Asiento;
import ar.edu.frba.utn.dds.operaciones.Itinerario;
import ar.edu.frba.utn.dds.usuarios.Usuario;

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
	public List<Itinerario> getItinerariosReservados();
	public void chequearExpiracionAsientos();
	public List<Asiento> getAsientosReservados();
}
