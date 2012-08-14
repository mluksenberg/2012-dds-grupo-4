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
	Usuario usuarioVip;
	Usuario usuarioEstandar;
	Usuario usuarioNoPago;
	Asiento unAsiento;
	@Before
	public void setUp() throws Exception {
		lanchita = new Lanchita();
		usuarioVip = new Usuario("Federico Gabriel", "Lopez Luksenberg", "36747013", new Vip());
		usuarioEstandar = new Usuario("Marcelo Javier", "Lopez Luksenberg", "36747012", new Estandar());
		usuarioNoPago = new Usuario("Andres Francisco", "Lopez Luksenberg", "33783548", new NoPaga());
		unAsiento = lanchita.asientosDisponibles(null, null, null, null, usuarioNoPago).get(0);
	}

	@Test
	public void testParsearAsientosDeLanchita() throws ConversionException{
		for(Asiento unAsiento : lanchita.asientosDisponibles(null, null, null, null, usuarioVip)){
			if(!(unAsiento instanceof Asiento)){
				Assert.assertTrue(false);
			}
		}
		Assert.assertTrue(true);
	}
	
	@Test
	public void testUnaFechaEstaEntreDosFechas() throws ConversionException{
		Assert.assertTrue(unAsiento.tieneFechasEntre("20/12/2012", "12:00"));
	}
	
	@Test
	public void testUnaFechaEsLaMismaQueOtraFecha() throws ConversionException{
		Assert.assertTrue(unAsiento.tieneFechasEntre("20/12/2012", "14:00"));
	}
	
	@Test
	public void testUnaFechaNoEstaEntreLasDosFechasDadas() throws ConversionException{
		Assert.assertFalse(unAsiento.tieneFechasEntre("20/12/2012", "14:01"));
	}
	
	@Test
	public void testObtenerImpuestoDeUnaAerolinea(){
		Assert.assertTrue(lanchita.getImpuesto() == 15);
	}
	
	@Test
	public void testComprarUnAsientoVistoDesdeUnaAerolineaParaUnUsuarioNoPago() throws ConversionException{
		int cantidadAsientos = lanchita.asientosDisponibles(null,null,null,null,usuarioNoPago).size();
		lanchita.comprar(unAsiento);
		Assert.assertTrue(lanchita.asientosDisponibles(null,null,null,null,usuarioNoPago).size() < cantidadAsientos);
	}
	
	@Test
	public void testAsientosDisponiblesVistoDesdeUnaAerolineaConTodosLosParametrosEnNullParaUnUsuarioVip() throws ConversionException{
		List<Asiento> asientos = lanchita.asientosDisponibles(null, null, null, null, usuarioVip);
		Assert.assertTrue(asientos.size() == 9);
	}
	
	@Test
	public void testAsientosDisponiblesVistoDesdeUnaAerolineaConFechaYHoraNull() throws ConversionException{
		List<Asiento> asientos = lanchita.asientosDisponibles("EZE", "USA", null, null, usuarioVip);
		for(Asiento unAsiento : asientos){
			System.out.println(unAsiento);
		}
		Assert.assertEquals(asientos.size(), 3);
	}

}
