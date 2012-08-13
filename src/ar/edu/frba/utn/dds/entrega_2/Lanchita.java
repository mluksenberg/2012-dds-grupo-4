package ar.edu.frba.utn.dds.entrega_2;

import java.util.ArrayList;
import java.util.List;

import ar.edu.frba.utn.dds.entrega_1.ConversionException;

import com.lanchita.AerolineaLanchita;

public class Lanchita implements Aerolinea{
	private AerolineaLanchita lanchita = AerolineaLanchita.getInstance();
	private static float impuesto = 15;
	
	public List<Asiento> asientosDisponibles(String unOrigen, String unDestino, String unaFecha, String unHorario, Usuario unUsuario) throws ConversionException{
		List<Asiento> asientosDisponibles = new ArrayList<Asiento>();
		for(  String[] unStringAsiento : this.getLanchita().asientosDisponibles(unOrigen, unDestino, null, null, null, null)  ){
			Asiento unAsiento = new Asiento(unStringAsiento[8], unStringAsiento[9], unStringAsiento[0], this.calcularPrecio(unStringAsiento[1], unUsuario), unStringAsiento[2], unStringAsiento[3], unStringAsiento[4], unStringAsiento[10], unStringAsiento[6], unStringAsiento[11], unStringAsiento[7]);
			if( unAsiento.tieneFechasEntre(unaFecha, unHorario) ){
				asientosDisponibles.add(unAsiento);
				if( unAsiento.esSuperOferta() && !(unUsuario.getTipo() instanceof Vip) ){
					asientosDisponibles.remove(unAsiento);
				}
			}
		}
		return asientosDisponibles;
	}

	private float calcularPrecio(String precio, Usuario unUsuario) {
		float precioOriginal = new Float(precio);
		return precioOriginal + precioOriginal*this.getImpuesto() + unUsuario.getTipo().getRecargo(); 
	}

	public AerolineaLanchita getLanchita() {
		return lanchita;
	}

	public void setLanchita(AerolineaLanchita lanchita) {
		this.lanchita = lanchita;
	}
	
	@Override
	public float getImpuesto() {
		return Lanchita.impuesto;
	}

	public static void setImpuesto(float impuesto) {
		Lanchita.impuesto = impuesto;
	}

	@Override
	public void comprar(Asiento unAsiento) {
		this.getLanchita().comprar(unAsiento.getAsiento());
	}

}
