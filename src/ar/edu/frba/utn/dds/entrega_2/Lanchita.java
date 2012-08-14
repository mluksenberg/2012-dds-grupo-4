package ar.edu.frba.utn.dds.entrega_2;

import java.util.ArrayList;
import java.util.List;

import ar.edu.frba.utn.dds.entrega_1.ConversionException;

import com.lanchita.AerolineaLanchita;

public class Lanchita implements Aerolinea {
	private AerolineaLanchita lanchita = AerolineaLanchita.getInstance();
	private static float impuesto = 15;
	private String[][] asientos;

	public Lanchita() {

		this.setAllAsientos(this.getLanchita().asientosDisponibles(null, null,
				null, null, null, null));

	}

	public List<Asiento> asientosDisponibles(String unOrigen, String unDestino,
			String unaFecha, String unHorario, Usuario unUsuario)
			throws ConversionException {
		List<Asiento> asientosDisponibles = new ArrayList<Asiento>();
		for (String[] unStringAsiento : this.getLanchita().asientosDisponibles(
				unOrigen, unDestino, null, null, null, null)) {
			if (unStringAsiento[8] == unOrigen
					&& unStringAsiento[9] == unDestino) {
				Asiento unAsiento = new Asiento(unStringAsiento[8],
						unStringAsiento[9], unStringAsiento[0],
						this.calcularPrecio(unStringAsiento[1], unUsuario),
						unStringAsiento[2], unStringAsiento[3],
						unStringAsiento[4], unStringAsiento[10],
						unStringAsiento[6], unStringAsiento[11],
						unStringAsiento[7], this.popularidadDeUnVuelo(unOrigen,
								unDestino, unStringAsiento[10],
								unStringAsiento[11]));
				if (unAsiento.tieneFechasEntre(unaFecha, unHorario)) {
					asientosDisponibles.add(unAsiento);
					if (unAsiento.esSuperOferta()
							&& !(unUsuario.getTipo() instanceof Vip)) {
						asientosDisponibles.remove(unAsiento);
					}
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

	public List<Asiento> obtenerAsientosComprados(String unOrigen,
			String unDestino, String unaFechaDeSalida, String unaFechaDeLlegada)
			throws ConversionException {
		List<Asiento> asientosComprados = new ArrayList<Asiento>();
		for (String[] unStringAsiento : this.getAllAsientos()) {
			if (unStringAsiento[4] == "C" && unStringAsiento[8] == unOrigen
					&& unStringAsiento[9] == unDestino) {
				Asiento unAsiento = new Asiento(unStringAsiento[8],
						unStringAsiento[9], unStringAsiento[0], (float) 0,
						unStringAsiento[2], unStringAsiento[3],
						unStringAsiento[4], unStringAsiento[10],
						unStringAsiento[6], unStringAsiento[11],
						unStringAsiento[7], 0);
				if (unStringAsiento[10] == unaFechaDeSalida
						&& unStringAsiento[11] == unaFechaDeLlegada) {
					asientosComprados.add(unAsiento);
				}

			}
		}
		return asientosComprados;
	}

	@Override
	public Integer popularidadDeUnVuelo(String unOrigen, String unDestino,
			String unaFecha, String unHorario) throws ConversionException {
		List<Asiento> asientosCompradosDelVuelo = obtenerAsientosComprados(
				unOrigen, unDestino, unaFecha, unHorario);
		return asientosCompradosDelVuelo.size();
	}

	public String[][] getAllAsientos() {
		return asientos;
	}

	public void setAllAsientos(String[][] asientos) {
		this.asientos = asientos;
	}
}
