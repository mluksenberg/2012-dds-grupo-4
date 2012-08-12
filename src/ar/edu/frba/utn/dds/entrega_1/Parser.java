package ar.edu.frba.utn.dds.entrega_1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;


public class Parser{
	private Collection<String> formatos = new ArrayList<String>();

	public Collection<String> getFormatos() {
		return this.formatos;
	}

	public void setFormatos(Collection<String> formatos) {
		this.formatos = formatos;
	}
	
	public Parser(){
		this.getFormatos().add("yyyy-MM-dd");
		this.getFormatos().add("dd/MM/yyyy");
		this.getFormatos().add("MM-dd-yyyy");
	}
	
	public void agregarFormato(String unFormato){
		this.getFormatos().add(unFormato);
	}
	
	/**
	 * Convierte un String en una Fecha
	 * @param String unString
	 * @param Formato unFormato
	 * @return Fecha unaFecha
	 * @throws ConversionException
	 * 
	 */
	public Fecha parsear(String unString) throws ConversionException{
		for(String unFormato : this.getFormatos()){
			Fecha unaFecha = new Fecha();
			SimpleDateFormat formatoFecha = new SimpleDateFormat(unFormato);
			try{
				unaFecha.setFecha(formatoFecha.parse(unString));
				if(!formatoFecha.format(unaFecha.getFecha()).equals(unString)){
					continue;
				}
				return unaFecha;
			}
			catch(ParseException exception){
				continue;
			}
		}
		throw new ConversionException();
	}
	
}
