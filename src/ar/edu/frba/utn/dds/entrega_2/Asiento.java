package ar.edu.frba.utn.dds.entrega_2;

import java.math.BigDecimal;

import ar.edu.frba.utn.dds.entrega_1.ConversionException;
import ar.edu.frba.utn.dds.entrega_1.Fecha;
import ar.edu.frba.utn.dds.entrega_1.Parser;

public class Asiento implements Cloneable{
	private String origen;
	private String destino;
	private String asiento;
	private BigDecimal precio;;
	private String clase;
	private String ubicacion;
	private Boolean estaReservado;
	private Fecha fechaSalida;
	private Fecha fechaLlegada;
	private Parser parser = new Parser();
	private Integer popularidad;
	
	public Asiento(String unOrigen, String unDestino, String unAsiento, float unPrecio, String unaClase, String unaUbicacion, String unEstado, String unaFechaSalida, String unaHoraSalida, String unaFechaLlegada, String unaHoraLlegada,Integer popularidad) throws ConversionException{
		this.parser.agregarFormato("yyyy-MM-dd HH:mm");
		this.parser.agregarFormato("MM-dd-yyyy HH:mm");
		this.parser.agregarFormato("dd/MM/yyyy HH:mm");
		this.origen = unOrigen;
		this.destino = unDestino;
		this.asiento = unAsiento;
		this.precio = (new BigDecimal(Float.toString(unPrecio)));
		this.clase = unaClase;
		this.ubicacion = unaUbicacion;
		if(unEstado.equals("D")) this.estaReservado = false;
		else this.estaReservado = true;
		this.fechaSalida = this.parser.parsear(unaFechaSalida + " " + unaHoraSalida);
		this.fechaLlegada = this.parser.parsear(unaFechaLlegada + " " + unaHoraLlegada);
		this.popularidad=popularidad;
	}
	
	public String toString(){
		return this.getAsiento() + " " + this.getOrigen() + " " + this.getDestino() + " " + this.getFechaSalida() + " " + this.getFechaLlegada() + " " + this.getClase() + " " + this.getUbicacion() + " " + this.getPrecio() + " " + this.getEstaReservado();  
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

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
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

	public boolean tieneFechasEntre(String unaFecha, String unHorario) {
		if(unaFecha == null && unHorario == null) return true;
		Fecha otraFecha = this.getParser().parsear(unaFecha + " " + unHorario);
		return ( otraFecha.esPosteriorA(this.getFechaSalida()) && this.getFechaLlegada().esPosteriorA(otraFecha) ) || this.getFechaSalida().esLaMismaFechaQue(otraFecha) || this.getFechaLlegada().esLaMismaFechaQue(otraFecha);
	}

	public boolean esSuperOferta() {
		return (this.getClase().equals("P") && this.getPrecio().floatValue() <= 8000) || (this.getClase().equals("E") && this.getPrecio().floatValue() <= 4000);
	}
	//FIXME contemplar el tiempo de conexion si corresponde
	public Long obtenerDuracion(){
		return (this.getFechaLlegada().getFecha().getTime() - this.fechaSalida.getFecha().getTime());
	}

	public Integer getPopularidad() {
		return popularidad;
	}

	public void setPopularidad(Integer popularidad) {
		this.popularidad = popularidad;
	}
}
