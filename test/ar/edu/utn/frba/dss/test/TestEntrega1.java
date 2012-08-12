package ar.edu.utn.frba.dss.test;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import ar.edu.frba.utn.dds.entrega_1.ConversionException;
import ar.edu.frba.utn.dds.entrega_1.Fecha;
import ar.edu.frba.utn.dds.entrega_1.Parser;

public class TestEntrega1 {
	Parser unParser;
	@Before
	public void setUp() throws Exception {
		unParser = new Parser();
	}

	@Test
	public void testFechaEnFormatoIso8601() throws ConversionException {
		try{
			unParser.parsear("1999-10-10");
			Assert.assertTrue(true);
		}
		catch(ConversionException e){
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testFechaEnFormatoLatinoamericano() throws ConversionException {
		try{
			unParser.parsear("10/10/1990");
			Assert.assertTrue(true);
		}
		catch(ConversionException e){
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testFechaEnFormatoNorteamericano() throws ConversionException {
		try{
			unParser.parsear("10-20-1990");
			Assert.assertTrue(true);
		}
		catch(ConversionException e){
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void testFechaEnFormatoIso8601ConMesMayorADoce() throws ConversionException {
		try{
			unParser.parsear("1990-16-20");
			Assert.assertTrue(false);
		}
		catch(ConversionException e){
			Assert.assertTrue(true);
		}
	}
	
	@Test
	public void testFechaEnFormatoLatinoamericanoConMesMayorADoce() throws ConversionException {
		try{
			unParser.parsear("10/20/1990");
			Assert.assertTrue(false);
		}
		catch(ConversionException e){
			Assert.assertTrue(true);
		}
	}
	
	@Test
	public void testFechaEnFormatoNorteamericanoConMesMayorADoce() throws ConversionException {
		try{
			unParser.parsear("16-20-1990");
			Assert.assertTrue(false);
		}
		catch(ConversionException e){
			Assert.assertTrue(true);
		}
	}
	
	@Test
	public void testFechaInvalida() throws ConversionException {
		try{
			unParser.parsear("1990/16/20");
			Assert.assertTrue(false);
		}
		catch(ConversionException e){
			Assert.assertTrue(true);
		}
	}
	
	@Test
	public void testDiferenciaDeDiasEntreDosFechasDistintas() throws ConversionException {
			Fecha unaFecha = unParser.parsear("1990-09-11");
			Fecha otraFecha = unParser.parsear("17/09/1990");
			Assert.assertEquals(unaFecha.diferenciaDeDias(otraFecha), 6);
	}
	
	@Test
	public void testDiferenciaDeDiasEntreDosFechasIguales() throws ConversionException {
			Fecha unaFecha = unParser.parsear("1990-09-11");
			Fecha otraFecha = unParser.parsear("11/09/1990");
			Assert.assertEquals(unaFecha.diferenciaDeDias(otraFecha), 0);
	}
	
	@Test
	public void testDiferenciaDeDiasEntreDosFechasEsUnSoloDia() throws ConversionException {
			Fecha unaFecha = unParser.parsear("1990-09-16");
			Fecha otraFecha = unParser.parsear("17/09/1990");
			Assert.assertEquals(unaFecha.diferenciaDeDias(otraFecha), 1);
	}
	
	@Test
	public void testLaPrimerFechaEsPosteriorALaSegundaFecha() throws ConversionException {
			Fecha unaFecha = unParser.parsear("1990-09-17");
			Fecha otraFecha = unParser.parsear("11/09/1990");
			Assert.assertTrue(unaFecha.esPosteriorA(otraFecha));
	}
	
	@Test
	public void testLasFechasSonLasMismas() throws ConversionException {
			Fecha unaFecha = unParser.parsear("1990-09-17");
			Fecha otraFecha = unParser.parsear("17/09/1990");
			Assert.assertFalse(unaFecha.esPosteriorA(otraFecha));
	}

}
