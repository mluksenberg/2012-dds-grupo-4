package ar.edu.utn.frba.dss.test;





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
		unAsiento = lanchita.asientosDisponibles("EZE", "USA", "20/12/2012", "15:20", usuarioVip).get(0);
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
		Assert.assertTrue(unAsiento.tieneFechasEntre("20/12/2012", "20:00"));
	}
	
	@Test
	public void testUnaFechaEsLaMismaQueOtraFecha() throws ConversionException{
		Assert.assertTrue(unAsiento.tieneFechasEntre("20/12/2012", "14:00"));
	}
	
	@Test
	public void testUnaFechaNoEstaEntreLasDosFechasDadas() throws ConversionException{
		Assert.assertFalse(unAsiento.tieneFechasEntre("20/12/2012", "13:59"));
	}
	
	@Test
	public void testObtenerImpuestoDeUnaAerolinea(){
		Assert.assertTrue(lanchita.getImpuesto() == 15);
	}
	
	@Test
	public void testComprarUnAsientoVistoDesdeUnaAerolineaParaUnUsuarioNoPago() throws ConversionException{
		int cantidadAsientos = lanchita.getLanchita().asientosDisponibles(null, null, null, null, null, null).length;
		lanchita.comprar(unAsiento);
		Assert.assertTrue(lanchita.getLanchita().asientosDisponibles(null, null, null, null, null, null).length < cantidadAsientos);
	}
	@Test
	public void testUnAsientoEsSuperOferta() throws ConversionException{
		Asiento asiento = usuarioVip.buscarAsientoDispobibles("EZE","USA", null, null,"E","P", lanchita).get(0);
		Assert.assertTrue(asiento.esSuperOferta() && asiento.getPrecio().floatValue() <= 4000);
	}
	
	

}
