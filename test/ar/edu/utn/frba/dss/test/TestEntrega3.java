package ar.edu.utn.frba.dss.test;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import ar.edu.frba.utn.dds.entrega_1.Fecha;
import ar.edu.frba.utn.dds.entrega_1.Parser;
import ar.edu.frba.utn.dds.entrega_2.Aerolinea;
import ar.edu.frba.utn.dds.entrega_2.Asiento;
import ar.edu.frba.utn.dds.entrega_2.Aterrizar;
import ar.edu.frba.utn.dds.entrega_2.Estandar;
import ar.edu.frba.utn.dds.entrega_2.Itinerario;
import ar.edu.frba.utn.dds.entrega_2.Lanchita;
import ar.edu.frba.utn.dds.entrega_2.NoPaga;
import ar.edu.frba.utn.dds.entrega_2.Usuario;
import ar.edu.frba.utn.dds.entrega_2.Vip;
import ar.edu.frba.utn.dds.entrega_3.FiltroClase;
import ar.edu.frba.utn.dds.entrega_3.FiltroUbicacionDecorator;
import ar.edu.frba.utn.dds.entrega_3.LaReservaNoCorrespondeAlUsuarioExeption;
import ar.edu.frba.utn.dds.entrega_3.NoAdmiteReservaExeption;
import ar.edu.frba.utn.dds.entrega_3.UsuarioInvalidoParaReservaExeption;

import com.lanchita.AerolineaLanchita;

public class TestEntrega3 {
	Lanchita lanchita;
	AerolineaLanchita lanchitaPostaMock;
	Usuario usuarioVip;
	Usuario usuarioEstandar;
	Usuario usuarioNoPago;
	Usuario otroUsuarioEstandar;
	Asiento unAsiento;
	Parser parser;
	Aterrizar aterrizar;
	Fecha unaFecha;
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
				{ "01202022220008-3", "383.22", "T", "V", "D", "", "20:00","08:00","PER","UDA","20/12/2012","21/12/2012" },
				{ "01202022256565-3", "282.19", "T", "C", "D", "", "20:00","08:00","PER","UDA","20/12/2012","21/12/2012" },
				{ "01202022323423-5", "431.28", "T", "C", "D", "", "20:00","08:00","PER","UDA","20/12/2012","21/12/2012" },
				{ "01202022220298-2", "528.81", "P", "V", "D", "", "07:00","08:00","USA","USH","20/12/2012","21/12/2012"} };
		
		when(lanchitaPostaMock.asientosDisponibles(anyString(),anyString(),anyString(),anyString(),anyString(), anyString())).thenReturn(asientos);
		
		///////////////////////////////////////////////////////////////////
		
		lanchita = new Lanchita();
		lanchita.setLanchita(lanchitaPostaMock);
		
		parser=new Parser();
		parser.agregarFormato("yyyy-MM-dd HH:mm");
		parser.agregarFormato("MM-dd-yyyy HH:mm");
		parser.agregarFormato("dd/MM/yyyy HH:mm");
		
		List<Aerolinea> aerolineas=new ArrayList<Aerolinea>();
		aerolineas.add(lanchita);
		
		aterrizar=new Aterrizar(aerolineas);
		
		usuarioVip = new Usuario("Federico Gabriel", "Lopez Luksenberg", "36747013", new Vip(),aterrizar);
		usuarioEstandar = new Usuario("Marcelo Javier", "Lopez Luksenberg", "36747012", new Estandar(),aterrizar);
		otroUsuarioEstandar = new Usuario("Eugenio", "Lopez Luksenberg", "28543567", new Estandar(), aterrizar);
		usuarioNoPago = new Usuario("Andres Francisco", "Lopez Luksenberg", "33783548", new NoPaga(),aterrizar);
		unaFecha= parser.parsear("20/12/2012" + " " + "15:20");
		unAsiento = lanchita.asientosDisponibles("EZE", "USA",unaFecha).get(0);
		lanchita.setMaximaDuracionDeReserva(10);
	}
	
	@Test (expected=UsuarioInvalidoParaReservaExeption.class)
	public void testReservarConUnUsuarioVIP(){
		usuarioVip.reservarAsiento(unAsiento);
	}
	
//	@Test (expected = NoAdmiteReservaExeption.class)
//	public void testAerolineaNoAdmiteReserva(){
//		//TODO Probar con OCEANICS
//	}
	
	@Test (expected = LaReservaNoCorrespondeAlUsuarioExeption.class)
	public void testUsuarioCompreUnAsientoYaReservadoPorOtro(){
		usuarioEstandar.reservarAsiento(unAsiento);
		otroUsuarioEstandar.comprarAsiento(unAsiento);
	}
	
	@Test
	public void testUsuarioQueReservoPuedeComprarAsientoYLasSobreReservasSeEliminan(){
		usuarioEstandar.reservarAsiento(unAsiento);
		otroUsuarioEstandar.reservarAsiento(unAsiento);
		usuarioEstandar.comprarAsiento(unAsiento);
		Assert.assertEquals(unAsiento.getReservas().size(), 0);
	}
	
	@Test
	public void testUnUsuarioBuscaUnItinerarioDesdeEZEaUSH(){
		Assert.assertEquals(usuarioVip.buscarItinerarios("EZE", "USH", unaFecha).size(), 3);
	}
	
	@Test
	public void testUnUsuarioBuscaUnItinerarioDesdeEZEaUSHfiltradoPorPrimeraClase(){
		FiltroClase filtroClase = new FiltroClase("P");
		Assert.assertEquals(usuarioVip.buscarItinerarios("EZE", "USH", unaFecha, filtroClase).size(), 1);
	}
	
	@Test
	public void testUnUsuarioBuscaUnItinerarioDesdeEZEaUSHfiltrandoPorUbicacionEnVentana(){
		FiltroUbicacionDecorator filtroUbicacion = new FiltroUbicacionDecorator("V");
		Assert.assertEquals(usuarioVip.buscarItinerarios("EZE", "USH", unaFecha, filtroUbicacion).size(), 1);
	}
	
	@Test
	public void testUnUsuarioBuscaUnItinerarioDesdeEZEaUSHfiltrandoPorUbicacionEnVentanaYPrimeraClase(){
		FiltroClase filtroClase = new FiltroClase("P");
		FiltroUbicacionDecorator filtroUbicacionYClase = new FiltroUbicacionDecorator("V");
		filtroUbicacionYClase.setFiltro(filtroClase);
		Assert.assertEquals(usuarioVip.buscarItinerarios("EZE", "USH", unaFecha, filtroUbicacionYClase).size(), 1);		
	}
}
