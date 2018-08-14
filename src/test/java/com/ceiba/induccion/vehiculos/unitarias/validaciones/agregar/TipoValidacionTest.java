package com.ceiba.induccion.vehiculos.unitarias.validaciones.agregar;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ceiba.induccion.excepciones.ParametrosInvalidos;
import com.ceiba.induccion.modelos.VehiculoModelo;
import com.ceiba.induccion.testdatabuilder.VehiculoTestDataBuilder;
import com.ceiba.induccion.utils.validaciones.agregar.TipoValidacion;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class TipoValidacionTest {
			
	@Test
	public void validarVehiculoDeTipoInvalido() {
		
		// arrange
		String tipoInvalido = "desconocido";
		VehiculoModelo vehiculo = new VehiculoTestDataBuilder().conTipo(tipoInvalido).build();
		
		try {
			// act
			new TipoValidacion().validate(vehiculo);
			fail("Se esperaba una excepcion (ParametrosInvalidos)");
			
		} catch (ParametrosInvalidos e) {
			// assert
			assertThat(e.getMessage(), is("El tipo del vehiculo deberia ser CARRO o MOTO"));
		}				
	}
	
	@Test(expected = Test.None.class)
	public void validarVehiculoDeTipoValido() {
		
		// arrange		
		VehiculoModelo vehiculo = new VehiculoTestDataBuilder().build();		
				
		// act
		new TipoValidacion().validate(vehiculo);							
	}
}
