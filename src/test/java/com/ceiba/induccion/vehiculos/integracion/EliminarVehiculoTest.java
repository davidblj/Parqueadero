package com.ceiba.induccion.vehiculos.integracion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.ceiba.induccion.parqueadero.ParqueaderoEntidad;
import com.ceiba.induccion.parqueadero.ParqueaderoRepositorio;
import com.ceiba.induccion.testdatabuilder.VehiculoTestDataBuilder;
import com.ceiba.induccion.utils.Calendario;
import com.ceiba.induccion.utils.Constants;
import com.ceiba.induccion.utils.excepciones.ParametrosInvalidos;
import com.ceiba.induccion.utils.factura.Factura;
import com.ceiba.induccion.vehiculos.VehiculoEntidad;
import com.ceiba.induccion.vehiculos.VehiculoModelo;
import com.ceiba.induccion.vehiculos.VehiculoRepositorio;
import com.ceiba.induccion.vehiculos.VehiculoServicio;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class EliminarVehiculoTest {

	@Autowired
	private ParqueaderoRepositorio parqueaderoRepositorio;
	
	@Autowired
	private VehiculoRepositorio vehiculoRepositorio;	
	
	@Autowired
	private VehiculoServicio servicio;
	
	@MockBean
	Calendario calendario;
	
	String placaCarro = "WMQ999";
	String placaMoto = "TC999";
	int horaDeIngreso = 0;
	int diaDeIngreso = 1;
	int minutoDeIngreso = 0;	
	
	// TODO: code duplication and simplicity. do use parqueaderoTestDataBuilder()
	
	@Before
	public void setUp() {
		
		parqueaderoRepositorio.deleteAll();
		vehiculoRepositorio.deleteAll();
		
		parqueaderoRepositorio.save(new ParqueaderoEntidad(
				Constants.PARQUEADERO_CEIBA, 
				Constants.PARQUEADERO_CEIBA_LIMITE_CARROS, 
				Constants.PARQUEADERO_CEIBA_LIMITE_MOTOS));
		
		VehiculoModelo carro = new VehiculoTestDataBuilder()				
				.conFechaDeIngreso(obtenerFechaDeIngreso())
				.conPlaca(placaCarro)
				.build();		
		
		VehiculoModelo moto = new VehiculoTestDataBuilder()	
				.conTipo(Constants.VEHICULO_MOTO)
				.conFechaDeIngreso(obtenerFechaDeIngreso())
				.conPlaca(placaMoto)
				.build();
		
		vehiculoRepositorio.save(new VehiculoEntidad(
				carro.getPlaca(), 
				carro.getTipo(), 
				carro.getCilindraje(),
				carro.getFechaDeIngreso()));
		
		vehiculoRepositorio.save(new VehiculoEntidad(
				moto.getPlaca(), 
				moto.getTipo(), 
				moto.getCilindraje(),
				moto.getFechaDeIngreso()));
	}
	
	@Test
	public void registrarSalidaConVehiculoSinRegistro() {
		
		try {
			// act
			servicio.eliminar("TCB724");
			
		} catch (ParametrosInvalidos e) {
			// assert
			assertEquals("El vehiculo actualmente no se encuentra al interior del parqueadero", e.getMessage());
		}
	}			
		
	@Test
	public void registrarSalidaConVehiculoFacturado() {
		
		// arrange
		String carroFacturado = "TCB427";
		Calendar placeholder = Calendar.getInstance();
		VehiculoModelo vehiculo = new VehiculoTestDataBuilder()				
				.conFechaDeSalida(placeholder)
				.conPlaca(carroFacturado)
				.build();						
		
		vehiculoRepositorio.save(new VehiculoEntidad(
				vehiculo.getPlaca(), 
				vehiculo.getTipo(), 
				vehiculo.getCilindraje(), 
				vehiculo.getFechaDeIngreso(),
				vehiculo.getFechaDeSalida()));
				
		try {
			// act
			servicio.eliminar(carroFacturado);
			
		} catch (ParametrosInvalidos e) {
			// assert
			assertEquals("El vehiculo actualmente no se encuentra al interior del parqueadero", e.getMessage());
		}
	}
				
	@Test
	public void registrarSalidaEnMenosDeUnaHora() {
		
		// arrange
		Calendar fechaDeSalida = Calendar.getInstance();
		fechaDeSalida.clear();
		fechaDeSalida.set(
				2018, 
				Calendar.JANUARY, 
				diaDeIngreso, 
				horaDeIngreso, 
				minutoDeIngreso + 59);
		
		when(calendario.obtenerFechaActual()).thenReturn(fechaDeSalida);
		
		// act
		Factura factura = servicio.eliminar(placaCarro);
		
		// assert		
		boolean registroExitoso = factura.getHoras() == 1 &&
								  factura.getDias() == 0;
				
		assertTrue(registroExitoso);
	} 
	
	@Test
	public void registrarSalidaEnUnaHora() {
		
		// arrange
		Calendar fechaDeSalida = Calendar.getInstance();
		fechaDeSalida.clear();
		fechaDeSalida.set(
				2018, 
				Calendar.JANUARY, 
				diaDeIngreso, 
				horaDeIngreso + 1, 
				minutoDeIngreso);
		
		when(calendario.obtenerFechaActual()).thenReturn(fechaDeSalida);
		
		// act
		Factura factura = servicio.eliminar(placaCarro);
		
		// assert		
		boolean registroExitoso = factura.getHoras() == 1 &&
				  				  factura.getDias() == 0;		
						
		assertTrue(registroExitoso);
	}
	
	@Test
	public void registrarSalidaEnMenosDeNueveHoras() {
		
		// arrange
		Calendar fechaDeSalida = Calendar.getInstance();
		fechaDeSalida.clear();
		fechaDeSalida.set(
				2018, 
				Calendar.JANUARY, 
				diaDeIngreso, 
				horaDeIngreso + 8, 
				minutoDeIngreso + 59);
		
		when(calendario.obtenerFechaActual()).thenReturn(fechaDeSalida);
		
		// act
		Factura factura = servicio.eliminar(placaCarro);
		
		// assert
		boolean registroExitoso = factura.getHoras() == 9 &&
								  factura.getDias() == 0;		
						
		assertTrue(registroExitoso);
	}
	
	@Test
	public void registrarSalidaEnNueveHoras() {
		
		// arrange
		Calendar fechaDeSalida = Calendar.getInstance();
		fechaDeSalida.clear();
		fechaDeSalida.set(
				2018, 
				Calendar.JANUARY, 
				diaDeIngreso, 
				horaDeIngreso + 9, 
				minutoDeIngreso);
		
		when(calendario.obtenerFechaActual()).thenReturn(fechaDeSalida);
		
		// act
		Factura factura = servicio.eliminar(placaCarro);
		
		// assert
		boolean registroExitoso = factura.getHoras() == 9 &&
				  				  factura.getDias() == 0;		
						
		assertTrue(registroExitoso);
	}
	
	@Test
	public void registrarSalidaEnMenosDeUnDia() {
		
		// arrange
		Calendar fechaDeSalida = Calendar.getInstance();
		fechaDeSalida.clear();
		fechaDeSalida.set(
				2018, 
				Calendar.JANUARY, 
				diaDeIngreso, 
				horaDeIngreso + 12, 
				minutoDeIngreso);
		
		when(calendario.obtenerFechaActual()).thenReturn(fechaDeSalida);
		
		// act
		Factura factura = servicio.eliminar(placaCarro);
		
		// assert
		boolean registroExitoso = factura.getHoras() == 0 &&
				  				  factura.getDias() == 1;		
						
		assertTrue(registroExitoso);
	}
	
	@Test
	public void registrarSalidaEnUnDia() {
		
		// arrange
		Calendar fechaDeSalida = Calendar.getInstance();
		fechaDeSalida.clear();
		fechaDeSalida.set(
				2018, 
				Calendar.JANUARY, 
				diaDeIngreso + 1, 
				horaDeIngreso, 
				minutoDeIngreso);
		
		when(calendario.obtenerFechaActual()).thenReturn(fechaDeSalida);
		
		// act
		Factura factura = servicio.eliminar(placaCarro);
		
		// assert
		boolean registroExitoso = factura.getHoras() == 0 &&
				  				  factura.getDias() == 1;		
						
		assertTrue(registroExitoso);
	}
	
	@Test
	public void registrarSalidaEnUnDiaYMenosDeNueveHoras() {
		
		// arrange
		Calendar fechaDeSalida = Calendar.getInstance();
		fechaDeSalida.clear();
		fechaDeSalida.set(
				2018, 
				Calendar.JANUARY, 
				diaDeIngreso + 1, 
				horaDeIngreso + 1, 
				minutoDeIngreso);
		
		when(calendario.obtenerFechaActual()).thenReturn(fechaDeSalida);
		
		// act
		Factura factura = servicio.eliminar(placaCarro);
		
		// assert
		boolean registroExitoso = factura.getHoras() == 1 &&
				  				  factura.getDias() == 1;		
						
		assertTrue(registroExitoso);
	}
	
	@Test
	public void registrarSalidaEnUnDiaYMasDeNueveHoras() {
		
		// arrange
		Calendar fechaDeSalida = Calendar.getInstance();
		fechaDeSalida.clear();
		fechaDeSalida.set(
				2018, 
				Calendar.JANUARY, 
				diaDeIngreso + 1, 
				horaDeIngreso + 10, 
				minutoDeIngreso);
		
		when(calendario.obtenerFechaActual()).thenReturn(fechaDeSalida);
		
		// act
		Factura factura = servicio.eliminar(placaCarro);
		
		// assert
		boolean registroExitoso = factura.getHoras() == 0 &&
				  				  factura.getDias() == 2;
						
		assertTrue(registroExitoso);
	}
	
	@Test
	public void registrarSalidaDeCarrosEnHoras() {
		
		// arrange
		Calendar fechaDeSalida = Calendar.getInstance();
		fechaDeSalida.clear();
		fechaDeSalida.set(
				2018, 
				Calendar.JANUARY, 
				diaDeIngreso, 
				horaDeIngreso + 5, 
				minutoDeIngreso);
		
		when(calendario.obtenerFechaActual()).thenReturn(fechaDeSalida);
		
		// act
		Factura factura = servicio.eliminar(placaCarro);
		
		// assert
		VehiculoEntidad vehiculo = vehiculoRepositorio.findByPlaca(placaCarro);		
						
		boolean registroExitoso = vehiculo.getFechaDeSalida() != null &&
								  factura.getPrecioFactura() == 5000;
		
		assertTrue(registroExitoso);		
	}
	
	@Test
	public void registrarSalidaDeCarrosEnHorasYDias() {
		
		// arrange
		Calendar fechaDeSalida = Calendar.getInstance();
		fechaDeSalida.clear();
		fechaDeSalida.set(
				2018, 
				Calendar.JANUARY, 
				diaDeIngreso + 1, 
				horaDeIngreso + 5, 
				minutoDeIngreso);
		
		when(calendario.obtenerFechaActual()).thenReturn(fechaDeSalida);
		
		// act
		Factura factura = servicio.eliminar(placaCarro);
		
		// assert
		VehiculoEntidad vehiculo = vehiculoRepositorio.findByPlaca(placaCarro);		
						
		boolean registroExitoso = vehiculo.getFechaDeSalida() != null &&
								  factura.getPrecioFactura() == 13000;
		
		assertTrue(registroExitoso);		
	}
	
	@Test
	public void registrarSalidaDeCarrosEnDias() {
		
		// arrange
		Calendar fechaDeSalida = Calendar.getInstance();
		fechaDeSalida.clear();
		fechaDeSalida.set(
				2018, 
				Calendar.JANUARY, 
				diaDeIngreso + 2, 
				horaDeIngreso, 
				minutoDeIngreso);
		
		when(calendario.obtenerFechaActual()).thenReturn(fechaDeSalida);
		
		// act
		Factura factura = servicio.eliminar(placaCarro);
		
		// assert
		VehiculoEntidad vehiculo = vehiculoRepositorio.findByPlaca(placaCarro);		
						
		boolean registroExitoso = vehiculo.getFechaDeSalida() != null &&
								  factura.getPrecioFactura() == 16000;
		
		assertTrue(registroExitoso);		
	}
	
	@Test
	public void registrarSalidaDeMotosEnHoras() {
		
		// arrange
		Calendar fechaDeSalida = Calendar.getInstance();
		fechaDeSalida.clear();
		fechaDeSalida.set(
				2018, 
				Calendar.JANUARY, 
				diaDeIngreso, 
				horaDeIngreso + 5, 
				minutoDeIngreso);
		
		when(calendario.obtenerFechaActual()).thenReturn(fechaDeSalida);
		
		// act
		Factura factura = servicio.eliminar(placaMoto);
		
		// assert
		VehiculoEntidad vehiculo = vehiculoRepositorio.findByPlaca(placaMoto);		
						
		boolean registroExitoso = vehiculo.getFechaDeSalida() != null &&
								  factura.getPrecioFactura() == 2500;
		
		assertTrue(registroExitoso);		
	}
	
	@Test
	public void registrarSalidaDeMotosEnHorasYDias() {
		
		// arrange
		Calendar fechaDeSalida = Calendar.getInstance();
		fechaDeSalida.clear();
		fechaDeSalida.set(
				2018, 
				Calendar.JANUARY, 
				diaDeIngreso + 1, 
				horaDeIngreso + 5, 
				minutoDeIngreso);
		
		when(calendario.obtenerFechaActual()).thenReturn(fechaDeSalida);
		
		// act
		Factura factura = servicio.eliminar(placaMoto);
		
		// assert
		VehiculoEntidad vehiculo = vehiculoRepositorio.findByPlaca(placaMoto);		
						
		boolean registroExitoso = vehiculo.getFechaDeSalida() != null &&
								  factura.getPrecioFactura() == 6500;
		
		assertTrue(registroExitoso);		
	}
	
	@Test
	public void registrarSalidaDeMotosEnDias() {
		
		// arrange
		Calendar fechaDeSalida = Calendar.getInstance();
		fechaDeSalida.clear();
		fechaDeSalida.set(
				2018, 
				Calendar.JANUARY, 
				diaDeIngreso + 1, 
				horaDeIngreso + 5, 
				minutoDeIngreso);
		
		when(calendario.obtenerFechaActual()).thenReturn(fechaDeSalida);
		
		// act		
		Factura factura = servicio.eliminar(placaMoto);
		
		// assert
		VehiculoEntidad vehiculo = vehiculoRepositorio.findByPlaca(placaMoto);		
						
		boolean registroExitoso = vehiculo.getFechaDeSalida() != null &&
								  factura.getPrecioFactura() == 6500;
		
		assertTrue(registroExitoso);		
	}
						
	private Calendar obtenerFechaDeIngreso() {
		
		Calendar fechaDeIngreso = Calendar.getInstance();
		fechaDeIngreso.clear();		
		fechaDeIngreso.set(
				2018, 
				Calendar.JANUARY, 
				diaDeIngreso, 
				horaDeIngreso, 
				minutoDeIngreso);
		
		return fechaDeIngreso;
	}		
}
