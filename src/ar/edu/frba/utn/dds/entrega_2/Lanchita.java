package ar.edu.frba.utn.dds.entrega_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ar.edu.frba.utn.dds.entrega_1.ConversionException;

import com.lanchita.AerolineaLanchita;

public class Lanchita implements Aerolinea{
	private AerolineaLanchita lanchita = AerolineaLanchita.getInstance();
	private List<Asiento> asientos = new ArrayList<Asiento>();
	private static float porcentaje;
	
	public Lanchita() throws ConversionException{
		List<String[]> stringsAsientos = Arrays.asList(lanchita.asientosDisponibles(null, null, null, null, null, null));
		for(String[] unAsiento : stringsAsientos){
			this.asientos.add(new Asiento(unAsiento[8], unAsiento[9], unAsiento[0], unAsiento[1], unAsiento[2], unAsiento[3], unAsiento[4], unAsiento[10], unAsiento[6], unAsiento[11], unAsiento[7]));
		}
	}
	
	public List<Asiento> asientosDisponibles(String unOrigen, String unDestino, String unaFecha, String unHorario) throws ConversionException{
		List<Asiento> asientosDisponibles = new ArrayList<Asiento>();
		for(Asiento unAsiento : this.getAsientos()){
			if( unAsiento.tieneFechasEntre(unaFecha, unHorario) ){
				asientosDisponibles.add(unAsiento);
			}
		}
		return asientosDisponibles;
	}
	
	public List<Asiento> getAsientos() {
		return asientos;
	}

	public void setAsientos(List<Asiento> asientos) {
		this.asientos = asientos;
	}

	public static float getPorcentaje() {
		return porcentaje;
	}

	public static void setPorcentaje(float porcentaje) {
		Lanchita.porcentaje = porcentaje;
	}
}
