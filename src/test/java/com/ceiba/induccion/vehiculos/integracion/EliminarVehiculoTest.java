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
import com.ceiba.induccion.utils.factura.Factura;
import com.ceiba.induccion.vehiculos.VehiculoEntidad;
import com.ceiba.induccion.vehiculos.VehiculoModelo;
import com.ceiba.induccion.vehiculos.VehiculoRepositorio;
import com.ceiba.induccion.vehiculos.servicios.EliminarVehiculo;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class EliminarVehiculoTest {

	@Autowired
	private ParqueaderoRepositorio parqueaderoRepositorio;
	
	@Autowired
	private VehiculoRepositorio vehiculoRepositorio;
	
	@Autowired
	private EliminarVehiculo eliminarVehiculo;
	
	@MockBean
	Calendario calendario;
	
	String placa = "WMQ999";
	int horaDeIngreso = 0;
	int diaDeIngreso = 1;
	int minutoDeIngreso = 0;
	int segundoDeIngreso = 0;
	
	// TODO: code duplication and simplicity. do use parqueaderoTestDataBuilder()
	
	@Before
	public void setUp() {
		
		parqueaderoRepositorio.deleteAll();
		vehiculoRepositorio.deleteAll();
		
		parqueaderoRepositorio.save(new ParqueaderoEntidad(
				Constants.PARQUEADERO_CEIBA, 
				Constants.PARQUEADERO_CEIBA_LIMITE_CARROS, 
				Constants.PARQUEADERO_CEIBA_LIMITE_MOTOS));	
		
		VehiculoModelo vehiculoModelo = new VehiculoTestDataBuilder()				
				.conFechaDeIngreso(obtenerFechaDeIngreso())
				.conPlaca(placa)
				.build();		
		
		vehiculoRepositorio.save(new VehiculoEntidad(
				vehiculoModelo.getPlaca(), 
				vehiculoModelo.getTipo(), 
				vehiculoModelo.getCilindraje(),
				vehiculoModelo.getFechaDeIngreso()));
	}
	
	@Test
	public void pruebaHumilda() {
		assertTrue(true);
	}
	
	/*@Test
	public void registrarSalidaEnMenosDeUnaHora() {
		
		// arrange
		Calendar fechaDeSalida = Calendar.getInstance();
		fechaDeSalida.set(
				2018, 
				Calendar.JANUARY, 
				diaDeIngreso, 
				horaDeIngreso, 
				minutoDeIngreso + 59);
		
		when(calendario.obtenerFechaActual()).thenReturn(fechaDeSalida);
		
		// act
		Factura factura = eliminarVehiculo.ejecutar(placa);
		
		// assert
		VehiculoEntidad vehiculo = vehiculoRepositorio.findByPlaca(placa);
		boolean registroExitoso = fechaDeSalida.compareTo(vehiculo.getFechaDeSalida()) == 0 &&
								  factura.getHoras() == 1;
		
		assertTrue(registroExitoso);
	}*/  
	
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
				minutoDeIngreso,
				segundoDeIngreso);
		
		when(calendario.obtenerFechaActual()).thenReturn(fechaDeSalida);
		
		// act
		Factura factura = eliminarVehiculo.ejecutar(placa);
		
//		long millis = fechaDeSalida.getTimeInMillis() - obtenerFechaDeIngreso().getTimeInMillis();
//		float hours = (millis / (1000 * 60 * 60)); 
		
		// assert
		VehiculoEntidad vehiculo = vehiculoRepositorio.findByPlaca(placa);
		/* boolean registroExitoso = fechaDeSalida.compareTo(vehiculo.getFechaDeSalida()) == 0 &&
								  factura.getHoras() == 1;*/
		
		boolean registroExitoso = factura.getHoras() == 1;
		assertEquals(factura.getHoras(), 1);
		// assertTrue(registroExitoso);
	}
		
	
	private Calendar obtenerFechaDeIngreso() {
		
		Calendar fechaDeIngreso = Calendar.getInstance();
		fechaDeIngreso.clear();		
		fechaDeIngreso.set(
				2018, 
				Calendar.JANUARY, 
				diaDeIngreso, 
				horaDeIngreso, 
				minutoDeIngreso,
				segundoDeIngreso);
		
		return fechaDeIngreso;
	}		
}
