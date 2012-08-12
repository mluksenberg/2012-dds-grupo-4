package ar.edu.frba.utn.dds.entrega_2;

import java.math.BigDecimal;

import ar.edu.frba.utn.dds.entrega_1.ConversionException;
import ar.edu.frba.utn.dds.entrega_1.Fecha;
import ar.edu.frba.utn.dds.entrega_1.Parser;

public class Asiento {
	private String origen;
	private String destino;
	private String asiento;
	private BigDecimal precio;
	private String clase;
	private String ubicacion;
	private Boolean estaReservado;
	private Fecha fechaSalida;
	private Fecha fechaLlegada;
	private Parser parser = new Parser();
	
	public Asiento(String unOrigen, String unDestino, String unAsiento, String unPrecio, String unaClase, String unaUbicacion, String unEstado, String unaFechaSalida, String unaHoraSalida, String unaFechaLlegada, String unaHoraLlegada) throws ConversionException{
		this.parser.agregarFormato("yyyy-MM-dd HH:mm");
		this.parser.agregarFormato("MM-dd-yyyy HH:mm");
		this.parser.agregarFormato("dd/MM/yyyy HH:mm");
		this.origen = unOrigen;
		this.destino = unDestino;
		this.asiento = unAsiento;
		this.precio = new BigDecimal(unPrecio);
		this.clase = unaClase;
		this.ubicacion = unaUbicacion;
		if(unEstado.equals("D")) this.estaReservado = false;
		else this.estaReservado = true;
		this.fechaSalida = this.parser.parsear(unaFechaSalida + " " + unaHoraSalida);
		this.fechaLlegada = this.parser.parsear(unaFechaLlegada + " " + unaHoraLlegada);
	}
	
	public String getAsiento() {
		return asiento;
	}
	
	public void setAsiento(String asiento) {
		this.asiento = asiento;
	}
	
	public BigDecimal getPrecio() {
		return precio;
	}
	
	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
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

	public boolean tieneFechasEntre(String unaFecha, String unHorario) throws ConversionException {
		Fecha otraFecha = this.getParser().parsear(unaFecha + " " + unHorario);
		return ( otraFecha.esPosteriorA(this.getFechaSalida()) && this.getFechaLlegada().esPosteriorA(otraFecha) ) || this.getFechaSalida().esLaMismaFechaQue(otraFecha) || this.getFechaLlegada().esLaMismaFechaQue(otraFecha);
	}
}
