package ar.edu.frba.utn.dds.entrega_4;

import java.util.Calendar;

//import ar.edu.frba.utn.dds.entrega_1.Fecha;
//import ar.edu.frba.utn.dds.entrega_2.Asiento;

public class Reserva {
	
	private Fecha fechaDeVencimiento=new Fecha();
	private Asiento asiento;
	private String dni;

	public Fecha getFechaDeVencimiento() {
		return fechaDeVencimiento;
	}

	public void setFechaDeVencimiento(Fecha fechaDeVencimiento) {
		this.fechaDeVencimiento = fechaDeVencimiento;
	}
	
	public Reserva(Asiento asiento,String dni){
		//TODO ¿Se puede hacer mas feliz?
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, (asiento.getAerolinea()).getMaximaDuracionDeReserva());
		this.fechaDeVencimiento.setFecha(cal.getTime());
		this.setAsiento(asiento);
		this.setDni(dni);
	}

	public Asiento getAsiento() {
		return asiento;
	}

	public void setAsiento(Asiento asiento) {
		this.asiento = asiento;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}
	
}
