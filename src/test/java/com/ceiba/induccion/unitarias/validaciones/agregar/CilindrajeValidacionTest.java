package com.ceiba.induccion.unitarias.validaciones.agregar;

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
import com.ceiba.induccion.utils.Constants;
import com.ceiba.induccion.utils.validaciones.agregar.CilindrajeValidacion;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CilindrajeValidacionTest {	
	
	@Test(expected = Test.None.class)
	public void validarMotosConCilindraje() {
		// arrange
		VehiculoModelo vehiculo = new VehiculoTestDataBuilder()
				.conTipo(Constants.VEHICULO_MOTO)
				.conCilindraje(550)
				.build();
		
		// act
		new CilindrajeValidacion().validate(vehiculo);
	}
	
	@Test 
	public void validarMotosSinCilindraje() {
		
		// arrange
		VehiculoModelo vehiculo = new VehiculoTestDataBuilder()
				.conTipo(Constants.VEHICULO_MOTO)
				.conCilindraje(0)
				.build();
		
		try {
			// act
			new CilindrajeValidacion().validate(vehiculo);
			fail("Se esperaba una excepsion");
			
		} catch (ParametrosInvalidos e) {
			// assert
			assertThat(e.getMessage(), is("Las motos tienen que especificar un cilindraje"));
		}
	}
}
