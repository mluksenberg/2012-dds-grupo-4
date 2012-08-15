package ar.edu.utn.frba.dss.test;

import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import ar.edu.frba.utn.dds.entrega_1.ConversionException;
import ar.edu.frba.utn.dds.entrega_2.Asiento;
import ar.edu.frba.utn.dds.entrega_2.Estandar;
import ar.edu.frba.utn.dds.entrega_2.Lanchita;
import ar.edu.frba.utn.dds.entrega_2.NoPaga;
import ar.edu.frba.utn.dds.entrega_2.Usuario;
import ar.edu.frba.utn.dds.entrega_2.Vip;
import ar.edu.frba.utn.dds.entrega_3.Criterio;
import ar.edu.frba.utn.dds.entrega_3.CriterioPopularidad;
import ar.edu.frba.utn.dds.entrega_3.CriterioPrecioAscendente;
import ar.edu.frba.utn.dds.entrega_3.CriterioPrecioDescendente;
import ar.edu.frba.utn.dds.entrega_3.CriterioTiempoVuelo;

public class TestEntrega3 {
	Lanchita lanchita;
	Usuario usuarioFederico;
	Usuario usuarioMarcelo;
	Usuario usuarioAndres;

	@Before
	public void setUp() throws Exception {
		lanchita = new Lanchita();
		String[][] asientos = lanchita.getAllAsientos();
		asientos[3][9] = "USA";
		asientos[2][11] = "20/12/2012";
		lanchita.setAllAsientos(asientos);
		usuarioFederico = new Usuario("Federico Gabriel", "Lopez Luksenberg",
				"36747013", new Vip());
		usuarioMarcelo = new Usuario("Marcelo Javier", "Lopez Luksenberg",
				"36747012", new Estandar());
		usuarioAndres = new Usuario("Andres Francisco", "Lopez Luksenberg",
				"33783548", new NoPaga());
	}
	
	@Test
	public void testCriterioPopularidad() throws ConversionException {
		//hay 2 vuelos que van de ezeiza a usa.. uno sale el 20 y llega el 21 y otro sale el
		//20 y llega el 20.. compro un pasaje para el q sale y llega el 20 y ese tiene mas popu
		//que el otro
		Asiento unAsiento = lanchita.asientosDisponibles("EZE", "USA",
				"20/12/2012", "05:20", usuarioFederico).get(0);
		lanchita.comprar(unAsiento);
		List<Asiento> asientos = usuarioFederico.buscarAsientoDispobibles(
				"EZE", "USA", null, null, lanchita);
		Criterio popu = new Criterio(new CriterioPopularidad());
		popu.ordenar(asientos);
		Assert.assertTrue(asientos.get(0).getPopularidad() == 1
				&& asientos.get(1).getPopularidad() == 0);

	}
	
	@Test
	public void testCriterioPrecioAscendente() throws ConversionException{
		List<Asiento> asientosOrdenados=usuarioFederico.buscarAsientoDispobibles("EZE", "USA", null, null, lanchita);
		Criterio precioAscendente=new Criterio(new CriterioPrecioAscendente());
		precioAscendente.ordenar(asientosOrdenados);
		for(int i=0;i+1<asientosOrdenados.size();i++){
			int comparacion=asientosOrdenados.get(i+1).getPrecio().compareTo(asientosOrdenados.get(i).getPrecio());
			Assert.assertTrue(comparacion>=0);
//			System.out.println(asientosOrdenados.get(i).getPrecio());

		}
	}
	
	@Test
	public void testCriterioPrecioDescendente() throws ConversionException{
		List<Asiento> asientosOrdenados=usuarioFederico.buscarAsientoDispobibles("EZE", "USA", null, null, lanchita);
		Criterio precioAscendente=new Criterio(new CriterioPrecioDescendente());
		precioAscendente.ordenar(asientosOrdenados);
		for(int i=0;i+1<asientosOrdenados.size();i++){
			int comparacion=asientosOrdenados.get(i).getPrecio().compareTo(asientosOrdenados.get(i+1).getPrecio());
			Assert.assertTrue(comparacion>=0);
//			System.out.println(asientosOrdenados.get(i).getPrecio());
		}
	}
	
	@Test
	public void testCriterioTiempoVuelo() throws ConversionException{
		List<Asiento> asientosOrdenados=usuarioFederico.buscarAsientoDispobibles("EZE", "USA", null, null, lanchita);
		Criterio precioAscendente=new Criterio(new CriterioTiempoVuelo());
		precioAscendente.ordenar(asientosOrdenados);
		for(int i=0;i+1<asientosOrdenados.size();i++){
//			System.out.println(asientosOrdenados.get(i).obtenerDuracion());
			int comparacion=asientosOrdenados.get(i+1).obtenerDuracion().compareTo(asientosOrdenados.get(i).obtenerDuracion());
			Assert.assertTrue(comparacion>=0);

		}
	}


}



