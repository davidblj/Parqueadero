package com.ceiba.induccion.vehiculos.unitarias.validaciones.eliminar;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.ceiba.induccion.entidades.VehiculoEntidad;
import com.ceiba.induccion.entidades.VehiculoRepositorio;
import com.ceiba.induccion.excepciones.ParametrosInvalidos;
import com.ceiba.induccion.modelos.VehiculoModelo;
import com.ceiba.induccion.testdatabuilder.VehiculoTestDataBuilder;
import com.ceiba.induccion.utils.validaciones.eliminar.ExistenciaValidacion;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ExistenciaValidacionTest {
	
	@Autowired
	@Qualifier("ExistenciaValidacionEliminarVehiculo")
	ExistenciaValidacion existenciaValidacion;
	
	@MockBean
	VehiculoRepositorio vehiculoRepositorio;
	
	String placa = "WMQ999";
	
	@Test
	public void validarVehiculoSinRegistro() {
		
		// arrange
		when(vehiculoRepositorio.findByPlaca(anyString())).thenReturn(null);
		
		try {
			// act
			existenciaValidacion.validate(placa);
			
		} catch (ParametrosInvalidos e) {
			// assert
			assertEquals("El vehiculo actualmente no se encuentra al interior del parqueadero", e.getMessage());
		}				
	}
			
	@Test
	public void validarVehiculoConRegistroYFacturado() {
		
		// arrange
		Calendar fechaDeSalida = Calendar.getInstance();		
		VehiculoModelo vehiculo = new VehiculoTestDataBuilder()
				.conFechaDeSalida(fechaDeSalida)
				.build();
		VehiculoEntidad vehiculoEntidad = new VehiculoEntidad(
				vehiculo.getPlaca(), 
				vehiculo.getTipo(), 
				vehiculo.getCilindraje(), 
				vehiculo.getFechaDeIngreso(),
				vehiculo.getFechaDeSalida());
		
		when(vehiculoRepositorio.findByPlaca(anyString())).thenReturn(vehiculoEntidad);
		
		try {
			// act
			existenciaValidacion.validate(placa);
			
		} catch (ParametrosInvalidos e) {
			// assert
			assertEquals("El vehiculo actualmente no se encuentra al interior del parqueadero", e.getMessage());
		}					
	}
	
	@Test(expected = Test.None.class)
	public void validarVehiculoConRegistroSinFacturar() {
		
		// arrange
		VehiculoModelo vehiculo = new VehiculoTestDataBuilder().build();
		VehiculoEntidad vehiculoEntidad = new VehiculoEntidad(
				vehiculo.getPlaca(), 
				vehiculo.getTipo(), 
				vehiculo.getCilindraje(), 
				vehiculo.getFechaDeIngreso());
		when(vehiculoRepositorio.findByPlaca(anyString())).thenReturn(vehiculoEntidad);
		
		// act
		existenciaValidacion.validate(placa);		
	}
}
