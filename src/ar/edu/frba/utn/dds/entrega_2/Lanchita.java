package ar.edu.frba.utn.dds.entrega_2;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;


import ar.edu.frba.utn.dds.entrega_1.Fecha;
import ar.edu.frba.utn.dds.entrega_3.Vuelo;

import com.lanchita.AerolineaLanchita;

public class Lanchita implements Aerolinea {
	private static final boolean admiteReserva = true;
	private AerolineaLanchita lanchita = AerolineaLanchita.getInstance();
	private static float impuesto = 15;
	private Integer maximaDuracionDeReserva;
	private TreeSet<Vuelo> vuelos = new TreeSet<Vuelo>();

	public Lanchita() {

		
	}

	public List<Asiento> asientosDisponibles(String unOrigen, String unDestino,Fecha fecha){
		List<Asiento> asientosDisponibles = new ArrayList<Asiento>();
		for (String[] unStringAsiento : this.getLanchita().asientosDisponibles(
				unOrigen, unDestino, null, null, null, null)) {
			//FIXME ¿como hacer este if mas feliz?
			if ((unStringAsiento[8] == unOrigen
					&& unStringAsiento[9] == unDestino) || (unOrigen==null && unDestino==null)
					|| (unStringAsiento[8] == unOrigen && unDestino == null)
					|| (unStringAsiento[9] == unDestino && unOrigen == null)) {
				Asiento unAsiento = new Asiento(unStringAsiento[8],
						unStringAsiento[9], unStringAsiento[0],
						unStringAsiento[1], 
						unStringAsiento[2], unStringAsiento[3],
						unStringAsiento[4], unStringAsiento[10],
						unStringAsiento[6], unStringAsiento[11],
						unStringAsiento[7],this);
				if (unAsiento.tieneFechasEntre(fecha)) {
					asientosDisponibles.add(unAsiento);
				}
			}
		}

		return asientosDisponibles;
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

	//TODO a modificar para meter lo de las reservas
	@Override
	public void comprar(Asiento unAsiento, String unDni) {
		this.getLanchita().comprar(unAsiento.getAsiento());
		unAsiento.setEstado("C");
		//TODO hacer una funcion de add or replace
		Vuelo vuelo=new Vuelo();
		vuelo.setNroDeVuelo(unAsiento.getAsiento().split("-")[0]);
		vuelo.setPopularidad(vuelo.getPopularidad()+1);
		this.getVuelos().add(vuelo);
	}
	
	public void reservarAsiento(Asiento unAsiento, Usuario unUsuario){
		this.getLanchita().reservar(unAsiento.getAsiento(), unUsuario.getDni());
		unAsiento.setEstado("R");
		//TODO ¿que es esto? ¿lo puedo romper?
		unAsiento.setEstaReservado(true);
		
	}
	
	public boolean admiteReserva(){
		return Lanchita.admiteReserva;
	}
	

	public Integer getMaximaDuracionDeReserva() {
		return maximaDuracionDeReserva;
	}

	public void setMaximaDuracionDeReserva(Integer maximaDuracionDeReserva) {
		this.maximaDuracionDeReserva = maximaDuracionDeReserva;
	}

	@Override
	public Integer popularidadDeUnVuelo(String codigoAsientoDeUnVuelo) {
		// TODO Auto-generated method stub
		return null;
	}

	public TreeSet<Vuelo> getVuelos() {
		return vuelos;
	}

	public void setVuelos(TreeSet<Vuelo> vuelos) {
		this.vuelos = vuelos;
	}
}
