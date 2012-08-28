package ar.edu.utn.frba.dss.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.lanchita.AerolineaLanchita;

import static org.mockito.Mockito.*;

import ar.edu.frba.utn.dds.entrega_1.Fecha;
import ar.edu.frba.utn.dds.entrega_1.Parser;
import ar.edu.frba.utn.dds.entrega_2.Aerolinea;
import ar.edu.frba.utn.dds.entrega_2.Asiento;
import ar.edu.frba.utn.dds.entrega_2.Aterrizar;
import ar.edu.frba.utn.dds.entrega_2.Estandar;
import ar.edu.frba.utn.dds.entrega_2.Lanchita;
import ar.edu.frba.utn.dds.entrega_2.NoPaga;
import ar.edu.frba.utn.dds.entrega_2.ParametrosErroneosExeption;
import ar.edu.frba.utn.dds.entrega_2.Usuario;
import ar.edu.frba.utn.dds.entrega_2.Vip;
import ar.edu.frba.utn.dds.entrega_3.Oceanic;

public class TestEntrega2 {
	Lanchita lanchita;
	AerolineaLanchita lanchitaPostaMock;
	Usuario usuarioVip;
	Usuario usuarioEstandar;
	Usuario usuarioNoPago;
	Asiento unAsiento;
	Parser parser;
	Aterrizar aterrizar;
	Fecha unaFecha;
	Fecha otraFecha;
	Oceanic oceanic;
	
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
		
		List<Aerolinea> aerolineas=new ArrayList<Aerolinea>();
		aerolineas.add(lanchita);
		//FIXME poner oceanic rompe todo
		//aerolineas.add(oceanic);
		
		aterrizar=new Aterrizar(aerolineas);
		
		usuarioVip = new Usuario("Federico Gabriel", "Lopez Luksenberg", "36747013", new Vip(),aterrizar);
		usuarioEstandar = new Usuario("Marcelo Javier", "Lopez Luksenberg", "36747012", new Estandar(),aterrizar);
		usuarioNoPago = new Usuario("Andres Francisco", "Lopez Luksenberg", "33783548", new NoPaga(),aterrizar);
		unaFecha= parser.parsear("20/12/2012" + " " + "15:20");
		otraFecha=parser.parsear("20/12/2012" + " " + "21:00");
		unAsiento = lanchita.asientosDisponibles("EZE", "USA",unaFecha).get(0);
	}


	
	@Test (expected = ParametrosErroneosExeption.class)
	public void testValidacionDeParametrosObligatoriosEnLaBusqueda(){
		@SuppressWarnings("unused")
		Asiento asiento = usuarioVip.buscarAsientoDispobibles(null,null, null, null,"E").get(0);

	}
	
	@Test
	public void testUnaFechaEstaEntreDosFechas(){
		Fecha otraFecha=parser.parsear("20/12/2012"+ " " +"20:00");
		Assert.assertTrue(unAsiento.tieneFechasEntre(otraFecha));
	}
	
	@Test
	public void testUnaFechaEsLaMismaQueOtraFecha(){
		Fecha otraFecha=parser.parsear("20/12/2012"+ " " +"14:00");
		Assert.assertTrue(unAsiento.tieneFechasEntre(otraFecha));
	}
	
	@Test
	public void testUnaFechaNoEstaEntreLasDosFechasDadas(){
		Fecha otraFecha=parser.parsear("20/12/2012"+ " " +"13:59");
		Assert.assertFalse(unAsiento.tieneFechasEntre(otraFecha));
	}
	
	@Test
	public void testObtenerImpuestoDeUnaAerolinea(){
		Assert.assertTrue(lanchita.getImpuesto() == 15);
	}
	
	/*
	 * el usuario compra y yo verifico que se haya invocado comprar en el lanchita
	 * posta con el codigo de asiento que compre.. si da verde compro bien
	 * pues no tiro exeption alguna
	 * el veryfy funciona de assert segun la especificacion
	 */
	@Test
	public void testComprarUnAsiento(){
		usuarioVip.comprarAsiento(unAsiento);
		verify(lanchitaPostaMock).comprar(unAsiento.getAsiento());
	}
	
	@Test
	public void testUnAsientoEsSuperOferta(){
		Asiento asiento = usuarioVip.buscarAsientoDispobibles("EZE","USA", unaFecha,"E","P").get(0);
		Assert.assertTrue(asiento.esSuperOferta() && asiento.getPrecio().floatValue() <= 4000);
	}
	
	@Test
	public void testBuscarAsientosDisponiblesParaElVip(){
		List<Asiento> asientosDisponibles=usuarioVip.buscarAsientoDispobibles("EZE", "USA", unaFecha);
		Assert.assertEquals(asientosDisponibles.size(),3);
	}
	@Test
	public void testBuscarAsientosDisponiblesParaElEstandar(){
		List<Asiento> asientosDisponibles=usuarioEstandar.buscarAsientoDispobibles("PER", "USA", otraFecha);
		Assert.assertEquals(asientosDisponibles.size(),3);
	}
	@Test
	public void testBuscarAsientosDisponiblesParaElQueNoGarpa(){
		List<Asiento> asientosDisponibles=usuarioNoPago.buscarAsientoDispobibles("PER", "USA", otraFecha);
		Assert.assertEquals(asientosDisponibles.size(),3);
	}
	
	@Test
	public void testAsientosDisponiblesConTodosLosParametrosEnNull(){
		List<Asiento> asientos=lanchita.asientosDisponibles(null, null, null);
		Assert.assertTrue(asientos.size()==10);
	}
	
	@Test
	public void testAsientosDisponilesSoloConDestinoNull(){
		List<Asiento> asientos=lanchita.asientosDisponibles("EZE", null, null);
		Assert.assertTrue(asientos.size()==6);
	}
	
	@Test
	public void testAsientosDisponiblesSoloConOrigenNull(){
		List<Asiento> asientos=lanchita.asientosDisponibles(null, "PER", null);
		Assert.assertTrue(asientos.size()==3);
	}
	
	@Test
	public void testetowadjvajverdu(){
		DateFormat dateF=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date=new Date();
		System.out.println(dateF.format(date));
	}
}
