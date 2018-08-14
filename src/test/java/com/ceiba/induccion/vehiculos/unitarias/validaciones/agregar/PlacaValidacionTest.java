package com.ceiba.induccion.vehiculos.unitarias.validaciones.agregar;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.ceiba.induccion.excepciones.Conflicto;
import com.ceiba.induccion.modelos.VehiculoModelo;
import com.ceiba.induccion.testdatabuilder.VehiculoTestDataBuilder;
import com.ceiba.induccion.utils.Calendario;
import com.ceiba.induccion.utils.validaciones.agregar.PlacaValidacion;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class PlacaValidacionTest {
	
	@Autowired
	PlacaValidacion placaValidacion;

	@MockBean
	Calendario calendario;
			
	@Test
	public void validarPlacaEnDiaInhabilitado() {
		
		// arrange
		VehiculoModelo vehiculo = new VehiculoTestDataBuilder().conPlaca("ABC211").build();
		
		int diaMartes = 2;
		when(calendario.obtenerDiaActual()).thenReturn(diaMartes);
		
		try {			
			// act
			placaValidacion.validate(vehiculo);
			
		} catch(Conflicto e) {
			
			// assert
			assertThat(e.getMessage(), is("El vehiculo solamente puede ingresar los lunes y domingos"));
		}			
	}
	
	@Test(expected = Test.None.class)
	public void validarPlacaEnDiaHabilitado() {
		
		// arrange
		VehiculoModelo vehiculo = new VehiculoTestDataBuilder().conPlaca("ABC211").build();
		
		int diaLunes = 1;
		when(calendario.obtenerDiaActual()).thenReturn(diaLunes);
		
		// act
		placaValidacion.validate(vehiculo);
	}
	
	@Test(expected = Test.None.class)
	public void validarPlacaSinRestriccionesHorarias() {
		
		// arrange
		VehiculoModelo vehiculo = new VehiculoTestDataBuilder().build();
		
		int diaLunes= 2;
		when(calendario.obtenerDiaActual()).thenReturn(diaLunes);
		
		// act
		placaValidacion.validate(vehiculo);
	}
}
