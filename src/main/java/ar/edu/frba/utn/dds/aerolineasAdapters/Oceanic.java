package ar.edu.frba.utn.dds.aerolineasAdapters;

import java.util.ArrayList;
import java.util.List;

import com.oceanic.AerolineaOceanic;
import com.oceanic.AsientoDTO;


import ar.edu.frba.utn.dds.exeptions.NoSePudoComprarExeption;
import ar.edu.frba.utn.dds.fechas.Fecha;
import ar.edu.frba.utn.dds.operaciones.Asiento;
import ar.edu.frba.utn.dds.operaciones.Itinerario;
import ar.edu.frba.utn.dds.operaciones.Vuelo;
import ar.edu.frba.utn.dds.usuarios.Usuario;

public class Oceanic implements Aerolinea {

	private AerolineaOceanic oceanicPosta= AerolineaOceanic.getInstance();
	private Boolean admiteReserva = true;
	private Integer maximaDuracionDeReserva=10;
	private List<Vuelo> vuelos=new ArrayList<Vuelo>();
	private List<Asiento> asientosReservados = new ArrayList<Asiento>();
	private List<Itinerario> itinerariosReservados = new ArrayList<Itinerario>();
	private String nombreAerolinea="Oceanic";
	@Override
	public List<Asiento> asientosDisponibles(String unOrigen, String unDestino, Fecha fecha) {
		List<AsientoDTO> asientosDTO;
		
			if(unDestino!=null){
				asientosDTO=this.getOceanicPosta().asientosDisponiblesParaOrigenYDestino(unOrigen, unDestino, fecha.getFechaString());
			}else{
				asientosDTO=this.getOceanicPosta().asientosDisponiblesParaOrigen(unOrigen, fecha.getFechaString());
			}
			List<Asiento> asientosDisponibles=new ArrayList<Asiento>();
			for(AsientoDTO unAsientoDTO: asientosDTO){
				Asiento unAsiento=new Asiento(unAsientoDTO.getOrigen(),unAsientoDTO.getDestino(),unAsientoDTO.getCodigoDeVuelo(),unAsientoDTO.getPrecio().toString(),unAsientoDTO.getClase(),unAsientoDTO.getUbicacion(),oceanicPosta.estaReservado(unAsientoDTO.getCodigoDeVuelo(), unAsientoDTO.getNumeroDeAsiento()), unAsientoDTO.getFechaDeSalida(), unAsientoDTO.getHoraDeSalida(), unAsientoDTO.getFechaDeLlegada(), unAsientoDTO.getHoraDeLlegada(),this);
				unAsiento.setNumeroDeAsiento(unAsientoDTO.getNumeroDeAsiento());
				asientosDisponibles.add(unAsiento);
			}
			return asientosDisponibles;

				
	}

	@Override
	public float getImpuesto() {
		return 0;
	}

	private void incremetarPopularidad(String nroVuelo){
		Vuelo unVuelo = null;
		for(Vuelo otroVuelo:this.getVuelos()){
			if(otroVuelo.getNroDeVuelo().equals(nroVuelo)){
				unVuelo=otroVuelo;
				break;
			}
		}
		if(unVuelo!=null){
			unVuelo.setPopularidad(unVuelo.getPopularidad()+1);
			this.getVuelos().add(unVuelo);

		}else{
			Vuelo vuelo = new Vuelo();
			vuelo.setNroDeVuelo(nroVuelo);
			Integer popularidaNueva=vuelo.getPopularidad()+1;
			vuelo.setPopularidad(popularidaNueva);
			this.getVuelos().add(vuelo);
		}
	}
	
	@Override
	public void comprar(Asiento unAsiento, String unDni) {
		Boolean resultado=this.getOceanicPosta().comprarSiHayDisponibilidad(unDni, unAsiento.getAsiento(), unAsiento.getNumeroDeAsiento());
		if(resultado){
			this.incremetarPopularidad(unAsiento.getAsiento());
		}else{
			throw new NoSePudoComprarExeption();
		}
	}


	@Override
	public void reservarAsiento(Asiento asiento, Usuario usuario) {
		this.getOceanicPosta().reservar(usuario.getDni(), asiento.getAsiento(), asiento.getNumeroDeAsiento());
		this.getAsientosReservados().add(asiento);
	}

	@Override
	public Integer getMaximaDuracionDeReserva() {
		return this.maximaDuracionDeReserva;
	}

	@Override
	public boolean admiteReserva() {
		return this.getAdmiteReserva();
	}

	public AerolineaOceanic getOceanicPosta() {
		return oceanicPosta;
	}

	public void setOceanicPosta(AerolineaOceanic oceanicPosta) {
		this.oceanicPosta = oceanicPosta;
	}

	public Boolean getAdmiteReserva() {
		return admiteReserva;
	}

	public void setAdmiteReserva(Boolean admiteReserva) {
		this.admiteReserva = admiteReserva;
	}

	public void setMaximaDuracionDeReserva(Integer maximaDuracionDeReserva) {
		this.maximaDuracionDeReserva = maximaDuracionDeReserva;
	}

	@Override
	public Integer popularidadDeUnVuelo(String codigoAsientoDeUnVuelo) {
		for(Vuelo unVuelo:this.getVuelos()){
			if(unVuelo.getNroDeVuelo().equals(codigoAsientoDeUnVuelo)){
				return unVuelo.getPopularidad();
			}
		}
		return 0;
	}

	public List<Vuelo> getVuelos() {
		return vuelos;
	}

	public void setVuelos(List<Vuelo> vuelos) {
		this.vuelos = vuelos;
	}

	public List<Asiento> getAsientosReservados() {
		return asientosReservados;
	}

	public void setAsientosReservados(List<Asiento> asientosReservados) {
		this.asientosReservados = asientosReservados;
	}

	public List<Itinerario> getItinerariosReservados() {
		return itinerariosReservados;
	}

	public void setItinerariosReservados(List<Itinerario> itinerariosReservados) {
		this.itinerariosReservados = itinerariosReservados;
	}

	@Override
	public void chequearExpiracionAsientos() {
		List<Asiento> asientosAEliminar = new ArrayList<Asiento>();
		for (Asiento unAsiento : this.getAsientosReservados()) {
			asientosAEliminar.add(unAsiento.actualizarReservas());
		}
		this.getAsientosReservados().removeAll(asientosAEliminar);
	}

	public String getNombreAerolinea() {
		return nombreAerolinea;
	}

	public void setNombreAerolinea(String nombreAerolinea) {
		this.nombreAerolinea = nombreAerolinea;
	}

}
