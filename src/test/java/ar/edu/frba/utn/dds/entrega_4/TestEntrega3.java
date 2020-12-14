package ar.edu.frba.utn.dds.entrega_4;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;



import ar.edu.frba.utn.dds.aerolineasAdapters.Aerolinea;
import ar.edu.frba.utn.dds.aerolineasAdapters.Lanchita;
import ar.edu.frba.utn.dds.aerolineasAdapters.Oceanic;
import ar.edu.frba.utn.dds.exeptions.LaReservaNoCorrespondeAlUsuarioExeption;
import ar.edu.frba.utn.dds.exeptions.NoAdmiteReservaExeption;
import ar.edu.frba.utn.dds.exeptions.NoSePudoComprarExeption;
import ar.edu.frba.utn.dds.exeptions.UsuarioInvalidoParaReservaExeption;
import ar.edu.frba.utn.dds.fechas.Fecha;
import ar.edu.frba.utn.dds.fechas.Parser;
import ar.edu.frba.utn.dds.operaciones.Asiento;
import ar.edu.frba.utn.dds.operaciones.Aterrizar;
import ar.edu.frba.utn.dds.operaciones.CriterioPrecioAscendente;
import ar.edu.frba.utn.dds.operaciones.CriterioPrecioDescendente;
import ar.edu.frba.utn.dds.operaciones.CriterioTiempoVuelo;
import ar.edu.frba.utn.dds.operaciones.Filtro;
import ar.edu.frba.utn.dds.operaciones.FiltroClase;
import ar.edu.frba.utn.dds.operaciones.FiltroMostrarAsientosReservadosDecorator;
import ar.edu.frba.utn.dds.operaciones.FiltroPrecioDecorator;
import ar.edu.frba.utn.dds.operaciones.FiltroUbicacionDecorator;
import ar.edu.frba.utn.dds.operaciones.Itinerario;
import ar.edu.frba.utn.dds.usuarios.Estandar;
import ar.edu.frba.utn.dds.usuarios.NoPaga;
import ar.edu.frba.utn.dds.usuarios.Usuario;
import ar.edu.frba.utn.dds.usuarios.Vip;

import com.lanchita.AerolineaLanchita;
import com.oceanic.AerolineaOceanic;
import com.oceanic.AsientoDTO;

public class TestEntrega3 {
	Lanchita lanchita;
	AerolineaLanchita lanchitaPostaMock;
	AerolineaOceanic oceanicPosta;
	Usuario usuarioVip;
	Usuario usuarioEstandar;
	Usuario usuarioNoPago;
	Usuario otroUsuarioEstandar;
	Asiento unAsiento;
	Asiento otroAsiento;
	Asiento otroAsientoMas;
	Asiento otroAsientoOceanic;
	Asiento otroAsientoOceanicMas;
	Asiento unAsientoOceanic;
	Asiento unAsientoReservado;
	Parser parser;
	Aterrizar aterrizar;
	Fecha unaFecha;
	Fecha otraFechaParaOceanic;
	Oceanic oceanic;
	Fecha otraFecha;
	Fecha unaFechaParaReservarItinerarios;
	Fecha unaOtraFecha;
	Itinerario unItinerario;
	Fecha fechaParaReservar;
	@Before
	public void setUp() throws Exception {
		parser=new Parser();
		parser.agregarFormato("yyyy-MM-dd HH:mm");
		parser.agregarFormato("MM-dd-yyyy HH:mm");
		parser.agregarFormato("dd/MM/yyyy HH:mm");
		parser.agregarFormato("dd/MM/yyyy");

		
		otraFechaParaOceanic=parser.parsear("15/08/2012");
		////////////////////////////////////////////////////This is mock baby?////////////////////
		lanchitaPostaMock = mock(AerolineaLanchita.class);
		oceanicPosta= mock(AerolineaOceanic.class);
		String[][] asientos ={
				{ "01202022220202-3", "159.90", "P", "V", "D", "", "14:00","03:00","EZE","USA","20/12/2012","21/12/2012" },
				{ "01202022220202-3", "205.10", "E", "P", "D", "", "14:00","02:25","EZE","USA","20/12/2012","21/12/2012" },
				{ "01202022220203-8", "154.08", "E", "P", "D", "", "14:00","06:25","EZE","USA","20/12/2012","21/12/2012" },
				{ "01202022267867-7", "255.98", "E", "P", "D", "", "05:20","14:00","EZE","PER","20/12/2012","20/12/2012" },
				{ "01202022227897-3", "236.10", "P", "C", "D", "", "05:20","14:00","EZE","PER","20/12/2012","20/12/2012" },
				{ "01202022998988-6", "148.23", "P", "V", "D", "", "05:20","14:00","EZE","PER","20/12/2012","20/12/2012" },
				{ "01202022220008-3", "383.22", "T", "V", "D", "", "20:00","08:00","PER","UDA","20/12/2012","21/12/2012" },
				{ "01202022256565-3", "282.19", "T", "C", "D", "", "20:00","08:00","PER","UDA","20/12/2012","21/12/2012" },
				{ "01202022323423-5", "431.28", "T", "C", "D", "", "20:00","08:00","PER","UDA","20/12/2012","21/12/2012" },
				{ "01202022220298-2", "528.81", "P", "V", "D", "", "07:00","08:00","USA","USH","21/12/2012","21/12/2012" }, };
		
		
		///////////////////////////////////////////////////////////////////
		
		lanchita = new Lanchita();
		lanchita.setLanchita(lanchitaPostaMock);
		
		List<Aerolinea> aerolineas=new ArrayList<Aerolinea>();
		
		aterrizar=new Aterrizar(aerolineas);
	
		unaFecha= parser.parsear("20/12/2012" + " " + "15:20");
		otraFecha= parser.parsear("21/12/2012" + " " + "07:00");
		unaFechaParaReservarItinerarios = parser.parsear("21/12/2012 07:40");
		unaOtraFecha = parser.parsear("20/12/2012" + " " + "05:40");
		fechaParaReservar = parser.parsear("20/11/2009 05:40");
		lanchita.setMaximaDuracionDeReserva(10);
		
		when(lanchitaPostaMock.asientosDisponibles(anyString(),anyString(),anyString(),anyString(),anyString(), anyString())).thenReturn(asientos);
		

		oceanic = new Oceanic();
		oceanic.setOceanicPosta(oceanicPosta);
		oceanic.setMaximaDuracionDeReserva(10);
		aerolineas.add(lanchita);
		aerolineas.add(oceanic);
		List<AsientoDTO> asientosDTO=new ArrayList<AsientoDTO>();
		asientosDTO.add(new AsientoDTO("OC100",10,"15/08/2012","17/08/2012","10:35","05:35",new BigDecimal("3150.98"),"Ejecutiva","Pasillo",false,"_BS","SLA"));
		asientosDTO.add(new AsientoDTO("OC100",11,"15/08/2012","17/08/2012","10:35","05:35",new BigDecimal("3150.98"),"Ejecutiva","Centro",false,"_BS","SLA"));		
		asientosDTO.add(new AsientoDTO("OC100",12,"15/08/2012","17/08/2012","10:35","05:35",new BigDecimal("3150.98"),"Ejecutiva","Ventana",false,"_BS","SLA"));		
		asientosDTO.add(new AsientoDTO("OC100",30,"15/08/2012","17/08/2012","10:35","05:35",new BigDecimal("6150.98"),"Primera","Pasillo",true,"_BS","SLA"));
		when(oceanicPosta.asientosDisponiblesParaOrigenYDestino("_BS", "SLA", "15/08/2012")).thenReturn(asientosDTO);
		unAsiento = lanchita.asientosDisponibles("EZE", "USA",unaFecha).get(0);
		otroAsiento = lanchita.asientosDisponibles("EZE", "USA",unaFecha).get(1);
		otroAsientoMas = lanchita.asientosDisponibles("EZE", "USA",unaFecha).get(2);
		otroAsientoOceanicMas=usuarioVip.buscarAsientoDispobibles("_BS", "SLA", otraFechaParaOceanic).get(2);
		unAsientoOceanic=usuarioVip.buscarAsientoDispobibles("_BS", "SLA", otraFechaParaOceanic).get(1);
		
		otroAsientoOceanic=usuarioVip.buscarAsientoDispobibles("_BS", "SLA", otraFechaParaOceanic).get(0);
		when(oceanicPosta.comprarSiHayDisponibilidad(usuarioVip.getDni(), otroAsientoOceanic.getAsiento(), otroAsientoOceanic.getNumeroDeAsiento())).thenReturn(true);
		when(oceanicPosta.comprarSiHayDisponibilidad(usuarioVip.getDni(), unAsientoOceanic.getAsiento(), unAsientoOceanic.getNumeroDeAsiento())).thenReturn(true);
		when(oceanicPosta.comprarSiHayDisponibilidad(usuarioVip.getDni(), otroAsientoOceanicMas.getAsiento(), otroAsientoOceanicMas.getNumeroDeAsiento())).thenReturn(true);
		unItinerario = usuarioVip.buscarItinerarios("EZE", "USH", unaFecha).get(0);
	}
	
	@Test (expected=UsuarioInvalidoParaReservaExeption.class)
	public void testReservarConUnUsuarioVIP() throws UsuarioInvalidoParaReservaExeption, NoAdmiteReservaExeption{
		usuarioVip.reservarAsiento(unAsiento);
	}
	

	@Test (expected = LaReservaNoCorrespondeAlUsuarioExeption.class)
	public void testUsuarioCompreUnAsientoYaReservadoPorOtro() throws LaReservaNoCorrespondeAlUsuarioExeption, NoSePudoComprarExeption, UsuarioInvalidoParaReservaExeption, NoAdmiteReservaExeption{
		usuarioEstandar.reservarAsiento(unAsiento);
		otroUsuarioEstandar.comprarAsiento(unAsiento);
	}
	
	@Test
	public void testUsuarioQueReservoPuedeComprarAsientoYLasSobreReservasSeEliminan() throws LaReservaNoCorrespondeAlUsuarioExeption, NoSePudoComprarExeption, UsuarioInvalidoParaReservaExeption, NoAdmiteReservaExeption{
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
	public void testUnUsuarioBuscaUnItinerarioDesdeEZEaUSHfiltradoPorPrimeraClaseYEjecutivo(){
		FiltroClase filtroClase = new FiltroClase("P");
		filtroClase.addClase("E");
		Assert.assertEquals(usuarioVip.buscarItinerarios("EZE", "USH", unaFecha, filtroClase).size(), 3);
	}
	
	@Test
	public void testUnUsuarioBuscaUnItinerarioDesdeEZEaUSHfiltradoPorPrimeraClaseYTurista(){
		FiltroClase filtroClase = new FiltroClase("P");
		filtroClase.addClase("T");
		Assert.assertEquals(usuarioVip.buscarItinerarios("EZE", "USH", unaFecha, filtroClase).size(), 1);
	}
	
	@Test
	public void testUnUsuarioBuscaUnItinerarioDesdeEZEaUSHfiltrandoPorUbicacionEnVentana(){
		FiltroUbicacionDecorator filtroUbicacion = new FiltroUbicacionDecorator("V");
		Assert.assertEquals(usuarioVip.buscarItinerarios("EZE", "USH", unaFecha, filtroUbicacion).size(), 1);
	}
	
	@Test
	public void testUnUsuarioBuscaUnItinerarioDesdeEZEaUSHfiltrandoPorPrecioDe10000A11500(){
		FiltroPrecioDecorator filtroPrecioDe10000A11500 = new FiltroPrecioDecorator(new BigDecimal(10000), new BigDecimal(11500) );
		Assert.assertEquals(usuarioVip.buscarItinerarios("EZE", "USH", unaFecha, filtroPrecioDe10000A11500).size(), 2);
	}
	
	@Test
	public void testUnUsuarioBuscaUnItinerarioDesdeEZEaUSHfiltrandoPorPrecioDe11000A11500(){
		FiltroPrecioDecorator filtroPrecioDe11000A11500 = new FiltroPrecioDecorator(new BigDecimal(11000), new BigDecimal(11500) );
		Assert.assertEquals(usuarioVip.buscarItinerarios("EZE", "USH", unaFecha, filtroPrecioDe11000A11500).size(), 1);
	}
	
	@Test
	public void testUnUsuarioBuscaUnItinerarioDesdeEZEaUSHfiltrandoPorUbicacionEnVentanaYPrimeraClase(){
		FiltroClase filtroClase = new FiltroClase("P");
		FiltroUbicacionDecorator filtroUbicacionYClase = new FiltroUbicacionDecorator("V");
		filtroUbicacionYClase.setFiltro(filtroClase);
		Assert.assertEquals(usuarioVip.buscarItinerarios("EZE", "USH", unaFecha, filtroUbicacionYClase).size(), 1);		
	}
	
	@Test
	public void testComprarUnAsientoDeOceanic() throws LaReservaNoCorrespondeAlUsuarioExeption, NoSePudoComprarExeption{
		usuarioVip.comprarAsiento(otroAsientoOceanic);
		verify(oceanicPosta).comprarSiHayDisponibilidad(usuarioVip.getDni(), otroAsientoOceanic.getAsiento(), otroAsientoOceanic.getNumeroDeAsiento());
	}
	
	@Test
	public void testReservarUnAsientoDeOceanic() throws UsuarioInvalidoParaReservaExeption, NoAdmiteReservaExeption{
		usuarioEstandar.reservarAsiento(otroAsientoOceanic);
		verify(oceanicPosta).reservar(usuarioEstandar.getDni(), otroAsientoOceanic.getAsiento(),otroAsientoOceanic.getNumeroDeAsiento());
	}
	
	@Test
	public void testSobreReservaEnLanchita() throws UsuarioInvalidoParaReservaExeption, NoAdmiteReservaExeption{
		usuarioEstandar.reservarAsiento(unAsiento);
		otroUsuarioEstandar.reservarAsiento(unAsiento);
		Assert.assertEquals(unAsiento.getReservas().size(), 2);
	}
	
	@Test
	public void testSobreReservaEnOceanic() throws UsuarioInvalidoParaReservaExeption, NoAdmiteReservaExeption{
		usuarioEstandar.reservarAsiento(otroAsientoOceanic);
		otroUsuarioEstandar.reservarAsiento(otroAsientoOceanic);
		Assert.assertEquals(otroAsientoOceanic.getReservas().size(), 2);
	}
	
	@Test
	public void testPopularidadDeUnVueloDeLanchita() throws LaReservaNoCorrespondeAlUsuarioExeption, NoSePudoComprarExeption{
		usuarioVip.comprarAsiento(unAsiento);
		usuarioVip.comprarAsiento(otroAsiento);
		Assert.assertTrue(lanchita.popularidadDeUnVuelo(unAsiento.getAsiento()).equals(2));
	}
	
	@Test
	public void testPopularidadDeUnVueloDeOceanic() throws LaReservaNoCorrespondeAlUsuarioExeption, NoSePudoComprarExeption{
		usuarioVip.comprarAsiento(unAsientoOceanic);
		usuarioVip.comprarAsiento(otroAsientoOceanic);
		usuarioVip.comprarAsiento(otroAsientoOceanicMas);
		Assert.assertTrue(oceanic.popularidadDeUnVuelo(unAsientoOceanic.getAsiento()).equals(3));
	}
	/*
	 * PARA FEDE LUKSENBERG: el assert no corta el flujo de ejecucion
	 */
	@Test
	public void testBuscarItinerariosOrdenadosPorPrecioAscendente(){
		List<Itinerario> itinerarios=usuarioVip.buscarItinerarios("EZE", "USH", unaFecha,new CriterioPrecioAscendente());
		float precioAnt=0;
		for(Itinerario unItinerario: itinerarios){
			Assert.assertTrue(unItinerario.precioTotal().floatValue()>precioAnt);
			precioAnt=unItinerario.precioTotal().floatValue();
		}
	}
	
	@Test
	public void testBuscarItinerariosOrdenadosPorPrecioDescendente(){
		List<Itinerario> itinerarios=usuarioVip.buscarItinerarios("EZE", "USH", unaFecha,new CriterioPrecioDescendente());
		float precioAnt=itinerarios.get(0).precioTotal().floatValue()+1;
		for(Itinerario unItinerario: itinerarios){
			Assert.assertTrue(unItinerario.precioTotal().floatValue()<precioAnt);
			precioAnt=unItinerario.precioTotal().floatValue();
		}
	}
	
	@Test
	public void testBuscarItinerariosOrdenadosPorTiempoVuelo(){
		List<Itinerario> itinerarios=usuarioVip.buscarItinerarios("EZE", "USH", otraFecha,new CriterioTiempoVuelo());
		long tiempoDeVueloAnt=0;
		for(Itinerario unItinerario: itinerarios){
			Assert.assertTrue(unItinerario.tiempoDeVuelo()>tiempoDeVueloAnt);
			tiempoDeVueloAnt=unItinerario.tiempoDeVuelo();
		}
	}
	
	@Test
	public void testBuscarItinerariosOrdenadosPorPopularidad() throws LaReservaNoCorrespondeAlUsuarioExeption, NoSePudoComprarExeption{
		usuarioVip.comprarAsiento(unAsiento);
		usuarioVip.comprarAsiento(otroAsiento);
		usuarioVip.comprarAsiento(otroAsientoMas);
		List<Itinerario> itinerarios=usuarioVip.buscarItinerarios("EZE", "USH", unaFecha,new CriterioPrecioDescendente());
		int popularidadAnt=itinerarios.get(0).popularidad()+1;
		for(Itinerario unItinerario: itinerarios){
			Assert.assertTrue(unItinerario.popularidad()<=popularidadAnt);
			popularidadAnt=unItinerario.popularidad();
		}
	}
	
	@Test
	public void testReservarItinerario() throws UsuarioInvalidoParaReservaExeption, NoAdmiteReservaExeption{
		usuarioEstandar.reservarItinerario(unItinerario);
		Assert.assertTrue(unItinerario.estaReservado());
	}
	
	@Test
	public void testComprarItinerario() throws NoSePudoComprarExeption, LaReservaNoCorrespondeAlUsuarioExeption{
		usuarioVip.comprarItinerario(unItinerario);
		for(Asiento unAsiento : unItinerario.getAsientos()){
			verify(lanchitaPostaMock).comprar(unAsiento.getAsiento());
		}
	}
	
	@Test
	public void testFiltroMostrarItinerariosReservados() throws UsuarioInvalidoParaReservaExeption, NoAdmiteReservaExeption{
		Filtro filtroMostrarReservados = new FiltroMostrarAsientosReservadosDecorator(true);
		usuarioEstandar.reservarItinerario(unItinerario);
		int sizeOriginal = usuarioVip.buscarItinerarios("EZE", "USH", unaFecha).size();
		int sizeDespues = usuarioVip.buscarItinerarios("EZE", "USH", unaFecha, filtroMostrarReservados).size();
		Assert.assertTrue(sizeOriginal < sizeDespues);
	}
	
	@Test
	public void testEliminarReservasExpiradas() throws UsuarioInvalidoParaReservaExeption, NoAdmiteReservaExeption{
		usuarioEstandar.reservarAsiento(unAsiento);
		unAsiento.getReservas().get(0).setFechaDeVencimiento(fechaParaReservar);
		int asientosReservadosOriginal = lanchita.getAsientosReservados().size();
		aterrizar.chequearExpiracionReservas();
		int asientosReservadosPosterior = lanchita.getAsientosReservados().size();
		Assert.assertTrue(asientosReservadosOriginal>asientosReservadosPosterior);
	}
	
}

