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

public class TestEntrega2 {
	Lanchita lanchita;
	Usuario usuarioFederico;
	Usuario usuarioMarcelo;
	Usuario usuarioAndres;
	@Before
	public void setUp() throws Exception {
		lanchita = new Lanchita();
		usuarioFederico = new Usuario("Federico Gabriel", "Lopez Luksenberg", "36747013", new Vip());
		usuarioMarcelo = new Usuario("Marcelo Javier", "Lopez Luksenberg", "36747012", new Estandar());
		usuarioAndres = new Usuario("Andres Francisco", "Lopez Luksenberg", "33783548", new NoPaga());
	}

	@Test
	public void testParsearAsientosDeLanchita(){
		for(Asiento unAsiento : lanchita.getAsientos()){
			if(!(unAsiento instanceof Asiento)){
				Assert.assertTrue(false);
			}
		}
		Assert.assertTrue(true);
	}
	
	@Test
	public void testUnaFechaEstaEntreDosFechas() throws ConversionException{
		Asiento unAsiento = lanchita.getAsientos().get(0);
		Assert.assertTrue(unAsiento.tieneFechasEntre("20/12/2012", "23:00"));
	}
	
	@Test
	public void testUnaFechaEsLaMismaQueOtraFecha() throws ConversionException{
		Asiento unAsiento = lanchita.getAsientos().get(0);
		Assert.assertTrue(unAsiento.tieneFechasEntre("20/12/2012", "14:00"));
	}
	
	@Test
	public void testUnaFechaNoEstaEntreLasDosFechasDadas() throws ConversionException{
		Asiento unAsiento = lanchita.getAsientos().get(0);
		Assert.assertFalse(unAsiento.tieneFechasEntre("20/12/2012", "13:59"));
	}
	
	@Test
	public void testObtenerImpuestoDeUnaAerolinea(){
		Assert.assertTrue(lanchita.getImpuesto() == 15);
	}
	
	@Test
	public void testComprarUnAsientoVistoDesdeUnaAerolinea(){
		Asiento unAsiento = lanchita.getAsientos().get(0);
		int cantidadAsientos = lanchita.getAsientos().size();
		lanchita.comprar(unAsiento);
		Assert.assertTrue(lanchita.getAsientos().size() < cantidadAsientos);
	}
	
	@Test
	public void testAsientosDisponiblesVistoDesdeUnaAerolineaConTodosLosParametrosEnNull() throws ConversionException{
		List<Asiento> asientos = lanchita.asientosDisponibles(null, null, null, null);
		System.out.println(asientos.size());
		Assert.assertTrue(asientos.size() == 10);
	}

}
