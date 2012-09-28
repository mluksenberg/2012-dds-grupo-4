package ar.edu.frba.utn.dds.entrega_4;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class FiltroPrecio {
	private BigDecimal precioMinimo;
	private BigDecimal precioMaximo;
	
	public FiltroPrecio(BigDecimal precioMinimo, BigDecimal precioMaximo){
		this.setPrecioMinimo(precioMinimo);
		this.setPrecioMaximo(precioMaximo);
	}
	
	public List<Asiento> filtrarAsientos(List<Asiento> asientosSinFiltro){
		List<Asiento> asientosFiltrados = new ArrayList<Asiento>();
		for(Asiento unAsiento : asientosSinFiltro){
			if (unAsiento.getPrecio().max(this.getPrecioMaximo()) == this.getPrecioMaximo() && unAsiento.getPrecio().min(this.getPrecioMinimo()) == this.getPrecioMinimo()){
				asientosFiltrados.add(unAsiento);
			}
		}
		return asientosFiltrados;
	}

	public BigDecimal getPrecioMinimo() {
		return precioMinimo;
	}

	public void setPrecioMinimo(BigDecimal precioMinimo) {
		this.precioMinimo = precioMinimo;
	}

	public BigDecimal getPrecioMaximo() {
		return precioMaximo;
	}

	public void setPrecioMaximo(BigDecimal precioMaximo) {
		this.precioMaximo = precioMaximo;
	}
}
