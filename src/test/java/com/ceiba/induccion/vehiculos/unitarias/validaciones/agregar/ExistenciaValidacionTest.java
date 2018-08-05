package com.ceiba.induccion.vehiculos.unitarias.validaciones.agregar;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.ceiba.induccion.testdatabuilder.VehiculoTestDataBuilder;
import com.ceiba.induccion.utils.excepciones.ParametrosInvalidos;
import com.ceiba.induccion.vehiculos.VehiculoDTO;
import com.ceiba.induccion.vehiculos.VehiculoModelo;
import com.ceiba.induccion.vehiculos.servicios.ObtenerVehiculo;
import com.ceiba.induccion.vehiculos.validaciones.agregar.ExistenciaValidacion;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ExistenciaValidacionTest {
	
	@Autowired
	ExistenciaValidacion existenciaValidacion;	
	
	@MockBean
	ObtenerVehiculo obtenerVehiculo;
	
	@Test
	public void validarVehiculoEnExistencia() {
	
		// arrange
		VehiculoModelo vehiculo = new VehiculoTestDataBuilder().build();
		VehiculoDTO nuevoVehiculoDTO= new VehiculoDTO(vehiculo.getPlaca(), vehiculo.getTipo(), vehiculo.getCilindraje());
		when(obtenerVehiculo.ejecutar(anyString())).thenReturn(nuevoVehiculoDTO);
		
		try {
			// act
			existenciaValidacion.validate(vehiculo);
			
		} catch(ParametrosInvalidos e) {
			// assert
			assertThat(e.getMessage(), is("El vehiculo que quieres ingresar ya se encuentra al interior del parqueadero"));
		}		
	}
	
	@Test(expected = Test.None.class)
	public void validarVehiculoNuevo() {
	
		// arrange
		VehiculoModelo vehiculo = new VehiculoTestDataBuilder().build();		
		when(obtenerVehiculo.ejecutar(anyString())).thenReturn(null);
			
		// act
		existenciaValidacion.validate(vehiculo);			
	}
}
