package ar.edu.frba.utn.dds.entrega_2;

import java.util.ArrayList;
import java.util.List;


import ar.edu.frba.utn.dds.entrega_1.Fecha;

import com.lanchita.AerolineaLanchita;

public class Lanchita implements Aerolinea {
	private AerolineaLanchita lanchita = AerolineaLanchita.getInstance();
	private static float impuesto = 15;
	private String[][] asientos;

	public Lanchita(String[][] asientos2) {

		if(asientos2==null){
		this.setAllAsientos(this.getLanchita().asientosDisponibles(null, null,
				null, null, null, null));
		}else{
			this.setAllAsientos(asientos2);
		}
		
	}

	public List<Asiento> asientosDisponibles(String unOrigen, String unDestino,Fecha fecha){
		List<Asiento> asientosDisponibles = new ArrayList<Asiento>();
		for (String[] unStringAsiento : this.getLanchita().asientosDisponibles(
				unOrigen, unDestino, null, null, null, null)) {
			if (unStringAsiento[8] == unOrigen
					&& unStringAsiento[9] == unDestino) {
				Asiento unAsiento = new Asiento(unStringAsiento[8],
						unStringAsiento[9], unStringAsiento[0],
						unStringAsiento[1], 
						unStringAsiento[2], unStringAsiento[3],
						unStringAsiento[4], unStringAsiento[10],
						unStringAsiento[6], unStringAsiento[11],
						unStringAsiento[7], this.popularidadDeUnVuelo(unOrigen,
								unDestino, fecha),this);
				if (unAsiento.tieneFechasEntre(fecha)) {
					asientosDisponibles.add(unAsiento);
				}
			}
		}

		return asientosDisponibles;
	}

	private float calcularPrecio(String precio, Usuario unUsuario) {
		float precioOriginal = new Float(precio);
		return precioOriginal + precioOriginal * this.getImpuesto()
				+ unUsuario.getTipo().getRecargo();
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

	//FIXME Esto va aca?
	public List<Asiento> obtenerAsientosComprados(String unOrigen,
			String unDestino, Fecha unaFecha){
		List<Asiento> asientosComprados = new ArrayList<Asiento>();
		for (String[] unStringAsiento : this.getAllAsientos()) {
			if (unStringAsiento[4] == "C" && unStringAsiento[8] == unOrigen
					&& unStringAsiento[9] == unDestino) {
				Asiento unAsiento = new Asiento(unStringAsiento[8],
						unStringAsiento[9], unStringAsiento[0],
						unStringAsiento[1],
						unStringAsiento[2], unStringAsiento[3],
						unStringAsiento[4], unStringAsiento[10],
						unStringAsiento[6], unStringAsiento[11],
						unStringAsiento[7], 0,this);
				if (unAsiento.tieneFechasEntre(unaFecha)) {
					asientosComprados.add(unAsiento);
				}

			}
		}
		return asientosComprados;
	}
//TODO ¿va aca o en otra clase.. en el futuro?
	@Override
	public Integer popularidadDeUnVuelo(String unOrigen, String unDestino,
			Fecha unaFecha){
		List<Asiento> asientosCompradosDelVuelo = obtenerAsientosComprados(
				unOrigen, unDestino, unaFecha);
		return asientosCompradosDelVuelo.size();
	}

	public String[][] getAllAsientos() {
		return asientos;
	}

	public void setAllAsientos(String[][] asientos) {
		this.asientos = asientos;
	}
}
