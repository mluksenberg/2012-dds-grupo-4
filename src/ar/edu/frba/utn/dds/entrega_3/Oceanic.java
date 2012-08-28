package ar.edu.frba.utn.dds.entrega_3;

import java.util.ArrayList;
import java.util.List;

import com.oceanic.AerolineaOceanic;
import com.oceanic.AsientoDTO;

import ar.edu.frba.utn.dds.entrega_1.Fecha;
import ar.edu.frba.utn.dds.entrega_2.Aerolinea;
import ar.edu.frba.utn.dds.entrega_2.Asiento;
import ar.edu.frba.utn.dds.entrega_2.Usuario;

public class Oceanic implements Aerolinea {

	private AerolineaOceanic oceanicPosta= AerolineaOceanic.getInstance();
	private Boolean admiteReserva;
	private Integer maximaDuracionDeReserva;
	
	@Override
	public List<Asiento> asientosDisponibles(String unOrigen, String unDestino, Fecha fecha) {
		List<AsientoDTO> asientosDTO;
		if(unDestino!=null){
			asientosDTO=this.getOceanicPosta().asientosDisponiblesParaOrigenYDestino(unOrigen, unDestino, fecha.getFecha().toString());
		}else{
			asientosDTO=this.getOceanicPosta().asientosDisponiblesParaOrigen(unOrigen, fecha.getFecha().toString());
		}
		List<Asiento> asientosDisponibles=new ArrayList<Asiento>();
		for(AsientoDTO unAsientoDTO: asientosDTO){
			Asiento unAsiento=new Asiento(unAsientoDTO.getOrigen(),unAsientoDTO.getDestino(),unAsientoDTO.getCodigoDeVuelo(),unAsientoDTO.getPrecio().toString(),unAsientoDTO.getClase(),unAsientoDTO.getUbicacion(),"D", unAsientoDTO.getFechaDeSalida(), unAsientoDTO.getHoraDeSalida(), unAsientoDTO.getFechaDeLlegada(), unAsientoDTO.getHoraDeLlegada(),this);
			unAsiento.setNumeroDeAsiento(unAsientoDTO.getNumeroDeAsiento());
			asientosDisponibles.add(unAsiento);
		}
		return asientosDisponibles;
		
	}

	@Override
	public float getImpuesto() {
		return 0;
	}

	@Override
	public void comprar(Asiento unAsiento, String unDni) {
		this.getOceanicPosta().comprarSiHayDisponibilidad(unDni, unAsiento.getAsiento(), unAsiento.getNumeroDeAsiento());		
	}


	@Override
	public void reservarAsiento(Asiento asiento, Usuario usuario) {
		this.getOceanicPosta().reservar(usuario.getDni(), asiento.getAsiento(), asiento.getNumeroDeAsiento());
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

}
