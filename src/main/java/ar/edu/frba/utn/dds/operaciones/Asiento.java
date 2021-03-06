package ar.edu.frba.utn.dds.operaciones;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.uqbar.commons.model.ObservableObject;

import ar.edu.frba.utn.dds.aerolineasAdapters.Aerolinea;
import ar.edu.frba.utn.dds.fechas.Fecha;
import ar.edu.frba.utn.dds.fechas.Parser;

public class Asiento extends ObservableObject {
	private String origen;
	private String destino;
	private String asiento;
	private Integer numeroDeAsiento;
	private String precioOriginal;
	private BigDecimal precio;
	private String clase;
	private String ubicacion;
	private Boolean estaReservado;
	private Fecha fechaSalida;
	private Fecha fechaLlegada;
	private Parser parser = new Parser();
	private String estado;
	private Aerolinea aerolinea;
	private List<Reserva> reservas = new ArrayList<Reserva>();

	public Asiento(String unOrigen, String unDestino, String unAsiento,
			String precioOriginal, String unaClase, String unaUbicacion,
			Boolean unEstado, String unaFechaSalida, String unaHoraSalida,
			String unaFechaLlegada, String unaHoraLlegada,
			Aerolinea unaAerolinea) {
		this.parser.agregarFormato("yyyy-MM-dd HH:mm");
		this.parser.agregarFormato("MM-dd-yyyy HH:mm");
		this.parser.agregarFormato("dd/MM/yyyy HH:mm");
		this.origen = unOrigen;
		this.destino = unDestino;
		this.asiento = unAsiento;
		this.precioOriginal = precioOriginal;
		this.clase = unaClase;
		this.ubicacion = unaUbicacion;
		this.estaReservado = !unEstado;
		if (unEstado)
			this.setEstado("D");
		else
			this.setEstado("R");
		this.fechaSalida = this.parser.parsear(unaFechaSalida + " "
				+ unaHoraSalida);
		this.fechaLlegada = this.parser.parsear(unaFechaLlegada + " "
				+ unaHoraLlegada);
		this.setAerolinea(unaAerolinea);
	}

	public String toString() {
		return this.getAsiento() + " " + this.getOrigen() + " "
				+ this.getDestino() + " " + this.getFechaSalida() + " "
				+ this.getFechaLlegada() + " " + this.getClase() + " "
				+ this.getUbicacion() + " " + this.getPrecio() + " "
				+ this.getEstaReservado();
	}

	public String getAsiento() {
		return asiento;
	}

	public void setAsiento(String asiento) {
		this.asiento = asiento;
	}

	public String getClase() {
		return clase;
	}

	public void setClase(String clase) {
		this.clase = clase;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(float unPrecio) {
		this.precio = (new BigDecimal(Float.toString(unPrecio)));
	}

	public Parser getParser() {
		return parser;
	}

	public void setParser(Parser parser) {
		this.parser = parser;
	}

	public Fecha getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Fecha fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public Fecha getFechaLlegada() {
		return fechaLlegada;
	}

	public void setFechaLlegada(Fecha fechaLlegada) {
		this.fechaLlegada = fechaLlegada;
	}

	public Boolean getEstaReservado() {
		return estaReservado;
	}

	public void setEstaReservado(Boolean estaReservado) {
		this.estaReservado = estaReservado;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public boolean tieneFechasEntre(Fecha otraFecha) {

		if (otraFecha == null)
			return true;

		return (otraFecha.esPosteriorA(this.getFechaSalida()) && this
				.getFechaLlegada().esPosteriorA(otraFecha))
				|| this.getFechaSalida().esLaMismaFechaQue(otraFecha)
				|| this.getFechaLlegada().esLaMismaFechaQue(otraFecha);
	}

	public boolean esSuperOferta() {
		return (this.getClase().equals("P") && this.getPrecio().floatValue() <= 8000)
				|| (this.getClase().equals("E") && this.getPrecio()
						.floatValue() <= 4000);
	}

	// FIXME1 contemplar el tiempo de conexion si corresponde
	// public Long obtenerDuracion(){
	// return (this.getFechaLlegada().getFecha().getTime() -
	// this.fechaSalida.getFecha().getTime());
	// }

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Aerolinea getAerolinea() {
		return aerolinea;
	}

	public void setAerolinea(Aerolinea aerolinea) {
		this.aerolinea = aerolinea;
	}

	public String getPrecioOriginal() {
		return precioOriginal;
	}

	public void setPrecioOriginal(String precioOriginal) {
		this.precioOriginal = precioOriginal;
	}

	public void guardarReserva(Reserva reserva) {
		this.getReservas().add(reserva);
	}

	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

	public Reserva getReservaPosta() {
		if (this.getReservas().isEmpty()){
			return null;
		}
		return this.getReservas().get(0);
	}

	public int getNumeroDeAsiento() {
		return numeroDeAsiento;
	}

	public void setNumeroDeAsiento(int numeroDeAsiento) {
		this.numeroDeAsiento = numeroDeAsiento;
	}

	public Integer obtenerPopularidadDelVuelo() {
		return this.getAerolinea().popularidadDeUnVuelo(this.getAsiento());
	}

	public Asiento actualizarReservas() {
		List<Reserva> reservasAEliminar = new ArrayList<Reserva>();
		Asiento toReturn = null;
		for (Reserva unaReserva : this.getReservas()) {
			if (this.expiroReserva(unaReserva.getFechaDeVencimiento())) {
				reservasAEliminar.add(unaReserva);
				toReturn = this;
			}
		}
		this.getReservas().removeAll(reservasAEliminar);
		return toReturn;
	}

	protected boolean expiroReserva(Fecha unaFecha) {
		Fecha fecha = new Fecha();
		fecha.setFecha(new Date());
		return fecha.esPosteriorA(unaFecha);
	}

	public String getNombreAerolinea() {
		return this.getAerolinea().getNombreAerolinea();
	}

	public String getNroVuelo() {
		return this.getAsiento().split("-")[0];
	}
	public String getNroAsiento(){
		return this.getAsiento().split("-")[1];
	}
}
