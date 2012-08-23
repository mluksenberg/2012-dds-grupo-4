package ar.edu.utn.frba.dss.test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.lanchita.AerolineaLanchita;

import static org.mockito.Mockito.*;

import ar.edu.frba.utn.dds.entrega_1.Parser;
import ar.edu.frba.utn.dds.entrega_2.Asiento;
import ar.edu.frba.utn.dds.entrega_2.Estandar;
import ar.edu.frba.utn.dds.entrega_2.Lanchita;
import ar.edu.frba.utn.dds.entrega_2.NoPaga;
import ar.edu.frba.utn.dds.entrega_2.ParametrosErroneosExeption;
import ar.edu.frba.utn.dds.entrega_2.Usuario;
import ar.edu.frba.utn.dds.entrega_2.Vip;

public class TestEntrega2 {
	Lanchita lanchita;
	AerolineaLanchita lanchitaPostaMock;
	Usuario usuarioVip;
	Usuario usuarioEstandar;
	Usuario usuarioNoPago;
	Asiento unAsiento;
	Parser parser;
	@Before
	public void setUp() throws Exception {
		////////////////////////////////////////////////////This is mock baby?////////////////////
		lanchitaPostaMock = mock(AerolineaLanchita.class);
		
		String[][] asientos ={
				{ "01202022220202-3", "159.90", "P", "V", "D", "", "14:00","02:25","EZE","USA","20/12/2012","21/12/2012" },
				{ "01202022220123-3", "205.10", "E", "P", "D", "", "14:00","02:25","EZE","USA","20/12/2012","21/12/2012" },
				{ "01202012315523-8", "154.08", "E", "P", "D", "", "14:00","02:25","EZE","USA","20/12/2012","21/12/2012" },
				{ "01202022267867-7", "255.98", "E", "P", "D", "", "05:20","14:00","EZE","PER","20/12/2012","20/12/2012" },
				{ "01202022227897-3", "236.10", "P", "C", "D", "", "05:20","14:00","EZE","PER","20/12/2012","20/12/2012" },
				{ "01202022998988-6", "148.23", "P", "V", "D", "", "05:20","14:00","EZE","PER","20/12/2012","20/12/2012" },
				{ "01202022220008-3", "383.22", "T", "V", "D", "", "20:00","08:00","PER","USA","20/12/2012","21/12/2012" },
				{ "01202022256565-3", "282.19", "T", "C", "D", "", "20:00","08:00","PER","USA","20/12/2012","21/12/2012" },
				{ "01202022323423-5", "431.28", "T", "C", "D", "", "20:00","08:00","PER","USA","20/12/2012","21/12/2012" },
				{ "01202022220298-2", "528.81", "P", "V", "D", "", "07:00","08:00","AEO","USH","20/12/2012","21/12/2012"} };
		
		when(lanchitaPostaMock.asientosDisponibles(anyString(),anyString(),anyString(),anyString(),anyString(), anyString())).thenReturn(asientos);
		///////////////////////////////////////////////////////////////////
		
		lanchita = new Lanchita(asientos);
		lanchita.setLanchita(lanchitaPostaMock);
		
		parser=new Parser();
		parser.agregarFormato("yyyy-MM-dd HH:mm");
		parser.agregarFormato("MM-dd-yyyy HH:mm");
		parser.agregarFormato("dd/MM/yyyy HH:mm");
		
		
		usuarioVip = new Usuario("Federico Gabriel", "Lopez Luksenberg", "36747013", new Vip());
		usuarioEstandar = new Usuario("Marcelo Javier", "Lopez Luksenberg", "36747012", new Estandar());
		usuarioNoPago = new Usuario("Andres Francisco", "Lopez Luksenberg", "33783548", new NoPaga());
		unAsiento = lanchita.asientosDisponibles("EZE", "USA", "20/12/2012", "15:20", usuarioVip).get(0);
	}

//TODO ¿se puede refactorizar para que quede mas feliz?
	@Test
	public void testParsearAsientosDeLanchita(){
		List<String[]> asientosString=Arrays.asList(lanchitaPostaMock.asientosDisponibles(null,null,null,null,null,null));
		Iterator<String[]> iterator = asientosString.iterator();
		for(int i=0;i<lanchita.asientosDisponibles(null, null, null, null, usuarioVip).size() && iterator.hasNext();i++){
			String[] unAsientoString = iterator.next();
			Asiento unAsiento = lanchita.asientosDisponibles(null, null, null, null, usuarioVip).get(0);
			
			Assert.assertEquals(unAsiento.getAsiento(),unAsientoString[0]);
			Assert.assertEquals(unAsiento.getClase(),unAsientoString[2]);
			Assert.assertEquals(unAsiento.getUbicacion(),unAsientoString[3]);
			Assert.assertEquals(unAsiento.getPrecio(),unAsientoString[1]);
			Assert.assertEquals(unAsiento.getEstado(),unAsientoString[4]);
			Assert.assertEquals(unAsiento.getFechaSalida(),parser.parsear(unAsientoString[10] + " " + unAsientoString[6]));
			Assert.assertEquals(unAsiento.getFechaLlegada(),parser.parsear(unAsientoString[11] + " " + unAsientoString[7]));
			Assert.assertEquals(unAsiento.getOrigen(),unAsientoString[8]);
			Assert.assertEquals(unAsiento.getDestino(),unAsientoString[9]);

		}
			
	}
	
	@Test (expected = ParametrosErroneosExeption.class)
	public void testValidacionDeParametrosObligatoriosEnLaBusqueda(){
		@SuppressWarnings("unused")
		Asiento asiento = usuarioVip.buscarAsientoDispobibles(null,null, null, null,"E","P", lanchita).get(0);

	}
	
	@Test
	public void testUnaFechaEstaEntreDosFechas(){
		Assert.assertTrue(unAsiento.tieneFechasEntre("20/12/2012", "20:00"));
	}
	
	@Test
	public void testUnaFechaEsLaMismaQueOtraFecha(){
		Assert.assertTrue(unAsiento.tieneFechasEntre("20/12/2012", "14:00"));
	}
	
	@Test
	public void testUnaFechaNoEstaEntreLasDosFechasDadas(){
		Assert.assertFalse(unAsiento.tieneFechasEntre("20/12/2012", "13:59"));
	}
	
	@Test
	public void testObtenerImpuestoDeUnaAerolinea(){
		Assert.assertTrue(lanchita.getImpuesto() == 15);
	}
	
	
	//FIXME junit.framework.AssertionFailedError: null (linea 126 y linea 128)
	//	@Test
//	public void testComprarUnAsiento(){
//		Assert.assertEquals(unAsiento.getAsiento(),Arrays.asList(lanchitaPostaMock.asientosDisponibles("EZE", "USA",null,null,null,null)).get(0)[0]);
//		int cantidadDeAsientosIniciales=Arrays.asList(lanchitaPostaMock.asientosDisponibles("EZE", "USA",null,null,null,null)).size();
//		usuarioVip.comprarAsiento(unAsiento,lanchita);
//		for(String[] asientoDelLanchitaPosta: Arrays.asList(lanchitaPostaMock.asientosDisponibles("EZE", "USA",null,null,null,null))){
//			Assert.assertTrue(asientoDelLanchitaPosta[0]==(unAsiento.getAsiento()));
//		}
//		Assert.assertTrue(cantidadDeAsientosIniciales > Arrays.asList(lanchitaPostaMock.asientosDisponibles("EZE", "USA",null,null,null,null)).size());
//		
//	}
	
	@Test
	public void testUnAsientoEsSuperOferta(){
		Asiento asiento = usuarioVip.buscarAsientoDispobibles("EZE","USA", null, null,"E","P", lanchita).get(0);
		Assert.assertTrue(asiento.esSuperOferta() && asiento.getPrecio().floatValue() <= 4000);
	}
	
	@Test
	public void testBuscarAsientosDisponiblesParaElVip(){
		List<Asiento> asientosDisponibles=usuarioVip.buscarAsientoDispobibles("EZE", "USA", null, null, lanchita);
		Assert.assertEquals(asientosDisponibles.size(),3);
	}
	@Test
	public void testBuscarAsientosDisponiblesParaElEstandar(){
		List<Asiento> asientosDisponibles=usuarioEstandar.buscarAsientoDispobibles("PER", "USA",null,null, lanchita);
		Assert.assertEquals(asientosDisponibles.size(),3);
	}
	@Test
	public void testBuscarAsientosDisponiblesParaElQueNoGarpa(){
		List<Asiento> asientosDisponibles=usuarioNoPago.buscarAsientoDispobibles("PER", "USA", null, null, lanchita);
		Assert.assertEquals(asientosDisponibles.size(),3);
	}
	
	

}
