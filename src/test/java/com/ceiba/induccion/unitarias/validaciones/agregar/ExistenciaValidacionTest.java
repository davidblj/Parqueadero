package com.ceiba.induccion.unitarias.validaciones.agregar;

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

import com.ceiba.induccion.dto.VehiculoDTO;
import com.ceiba.induccion.entidades.VehiculoEntidad;
import com.ceiba.induccion.excepciones.ParametrosInvalidos;
import com.ceiba.induccion.modelos.VehiculoModelo;
import com.ceiba.induccion.repositorios.VehiculoRepositorio;
import com.ceiba.induccion.testdatabuilder.VehiculoTestDataBuilder;
import com.ceiba.induccion.utils.validaciones.agregar.ExistenciaValidacion;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ExistenciaValidacionTest {
	
	@Autowired
	ExistenciaValidacion existenciaValidacion;	
	
	@MockBean
	VehiculoRepositorio vehiculoRepositorio;
	
	@Test
	public void validarVehiculoEnExistencia() {
	
		// arrange
		VehiculoModelo vehiculo = new VehiculoTestDataBuilder().build();
		VehiculoDTO nuevoVehiculoDTO = new VehiculoDTO(vehiculo.getPlaca(), vehiculo.getTipo(), vehiculo.getCilindraje());
		VehiculoEntidad vehiculoIngresado = new VehiculoEntidad(
				nuevoVehiculoDTO.getPlaca(), 
				nuevoVehiculoDTO.getTipo(), 
				nuevoVehiculoDTO.getCilindraje(),
				null);
		when(vehiculoRepositorio.findByFechaDeSalidaIsNullAndPlaca(anyString())).thenReturn(vehiculoIngresado);
		
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
		when(vehiculoRepositorio.findByFechaDeSalidaIsNullAndPlaca(anyString())).thenReturn(null);
			
		// act
		existenciaValidacion.validate(vehiculo);			
	}
}
