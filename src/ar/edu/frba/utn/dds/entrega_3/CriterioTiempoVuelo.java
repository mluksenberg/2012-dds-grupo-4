package ar.edu.frba.utn.dds.entrega_3;

import java.util.Comparator;

import ar.edu.frba.utn.dds.entrega_2.Asiento;

public class CriterioTiempoVuelo implements Comparator<Asiento>{
	//TODO contemplar el tiempo de conexion
	@Override
	public int compare(Asiento unAsiento, Asiento otroAsiento){
		return unAsiento.obtenerDuracion().compareTo(otroAsiento.obtenerDuracion());
	}
}
