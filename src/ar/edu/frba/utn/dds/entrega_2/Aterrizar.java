package ar.edu.frba.utn.dds.entrega_2;

import java.util.ArrayList;
import java.util.List;

import ar.edu.frba.utn.dds.entrega_1.Fecha;

public class Aterrizar {
	
	private List<Aerolinea> aerolineas;
	
	
	public Aterrizar(List<Aerolinea> aerolineas){
		this.setAerolineas(aerolineas);

	}

	public List<Aerolinea> getAerolineas() {
		return aerolineas;
	}

	public void setAerolineas(List<Aerolinea> aerolineas) {
		this.aerolineas = aerolineas;
	}
	
	public List<Asiento> asientosDisponibles(String unOrigen, String unDestino,Fecha fecha,Usuario user){
		if(unOrigen==null||unDestino==null){
			throw new ParametrosErroneosExeption();
		}
		List<Asiento> asientos=new ArrayList<Asiento>();
		for(Aerolinea aerolinea: this.getAerolineas()){
			asientos.addAll(aerolinea.asientosDisponibles(unOrigen, unDestino,fecha));
		}
		for(Asiento unAsiento: asientos){
			unAsiento.setPrecio(this.calcularPrecioDeUnAsiento(unAsiento.getPrecioOriginal(), user, unAsiento.getAerolinea()));
			if (unAsiento.esSuperOferta()
					&& !(user.getTipo() instanceof Vip)) {
				asientos.remove(unAsiento);
			}
		}
		return asientos;
	}
	
	public void comprar(Asiento unAsiento){
		unAsiento.getAerolinea().comprar(unAsiento);
	}
	
	private float calcularPrecioDeUnAsiento(String precio, Usuario unUsuario, Aerolinea unaAerolinea) {
		float precioOriginal = new Float(precio);
		return precioOriginal + precioOriginal * unaAerolinea.getImpuesto()
				+ unUsuario.getTipo().getRecargo();
	}
}
