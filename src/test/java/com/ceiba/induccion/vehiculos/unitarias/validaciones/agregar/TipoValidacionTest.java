package com.ceiba.induccion.vehiculos.unitarias.validaciones.agregar;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ceiba.induccion.testdatabuilder.VehiculoTestDataBuilder;
import com.ceiba.induccion.utils.excepciones.ParametrosInvalidos;
import com.ceiba.induccion.vehiculos.VehiculoModelo;
import com.ceiba.induccion.vehiculos.validaciones.agregar.TipoValidacion;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class TipoValidacionTest {
		
	static TipoValidacion validacion;

	@BeforeClass
	public static void setUp() {		
		validacion = new TipoValidacion();
	}
	
	@Test
	public void validarVehiculoDeTipoInvalido() {
		
		// arrange
		String tipoInvalido = "desconocido";
		VehiculoModelo vehiculo = new VehiculoTestDataBuilder().conTipo(tipoInvalido).build();
		
		try {
			// act
			validacion.validate(vehiculo);
			fail("Se esperaba una excepcion (ParametrosInvalidos)");
			
		} catch (ParametrosInvalidos e) {
			// assert
			assertThat(e.getMessage(), is("El tipo del vehiculo deberia ser CARRO o MOTO"));
		}				
	}
	
	@Test
	public void validarVehiculoDeTipoValido() {
		
		// arrange		
		VehiculoModelo vehiculo = new VehiculoTestDataBuilder().build();		
		
		try {
			// act
			validacion.validate(vehiculo);
					
		} catch (ParametrosInvalidos e) {
			
			fail("No se esparaba ningua excepcion");			
		}
	}
}
