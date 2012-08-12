package ar.edu.utn.frba.dss.test;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import ar.edu.frba.utn.dds.entrega_1.ConversionException;
import ar.edu.frba.utn.dds.entrega_2.Asiento;
import ar.edu.frba.utn.dds.entrega_2.Lanchita;

public class TestEntrega2 {
	Lanchita lanchita;
	@Before
	public void setUp() throws Exception {
		lanchita = new Lanchita();
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

}
