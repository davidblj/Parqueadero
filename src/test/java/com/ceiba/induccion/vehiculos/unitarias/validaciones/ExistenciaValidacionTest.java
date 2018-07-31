package com.ceiba.induccion.vehiculos.unitarias.validaciones;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.ceiba.induccion.excepciones.ParametrosInvalidos;
import com.ceiba.induccion.testdatabuilder.VehiculoTestDataBuilder;
import com.ceiba.induccion.vehiculos.VehiculoDTO;
import com.ceiba.induccion.vehiculos.VehiculoModelo;
import com.ceiba.induccion.vehiculos.VehiculoServicio;
import com.ceiba.induccion.vehiculos.validaciones.ExistenciaValidacion;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ExistenciaValidacionTest {
	
	@Autowired
	ExistenciaValidacion existenciaValidacion;
	
	@MockBean
	VehiculoServicio vehiculoServicio;
	
	@Test
	public void validarVehiculoEnExistencia() {
	
		// arrange
		VehiculoModelo vehiculo = new VehiculoTestDataBuilder().build();
		VehiculoDTO nuevoVehiculoDTO= new VehiculoDTO(vehiculo.getPlaca(), vehiculo.getTipo());
		when(vehiculoServicio.consultarExistencia(anyString())).thenReturn(nuevoVehiculoDTO);
		
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
		when(vehiculoServicio.consultarExistencia(anyString())).thenReturn(null);
			
		// act
		existenciaValidacion.validate(vehiculo);			
	}
}
