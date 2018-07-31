package com.ceiba.induccion.vehiculos.unitarias.validaciones;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.ceiba.induccion.excepciones.Conflicto;
import com.ceiba.induccion.testdatabuilder.VehiculoTestDataBuilder;
import com.ceiba.induccion.utils.Calendario;
import com.ceiba.induccion.vehiculos.VehiculoModelo;
import com.ceiba.induccion.vehiculos.validaciones.PlacaValidacion;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PlacaValidacionTest {
	
	@Autowired
	PlacaValidacion placaValidacion;

	@MockBean
	Calendario calendario;
	
	@Before
	public void setUp() {
		
	}
	
	@Test
	public void validarPlacaEnDiaInhabilitado() {
		
		// arrange
		VehiculoModelo vehiculo = new VehiculoTestDataBuilder().conPlaca("ABC211").build();
		
		int diaLunes = 2;
		when(calendario.obtenerDiaActual()).thenReturn(diaLunes);
		
		try {			
			// act
			placaValidacion.validate(vehiculo);
			
		} catch(Conflicto e) {
						
			assertThat(e.getMessage(), is("El vehiculo no puede ingresar los lunes y domingos"));
		}			
	}
	
	@Test(expected = Test.None.class)
	public void validarPlacaEnDiaHabilitado() {
		
		// arrange
		VehiculoModelo vehiculo = new VehiculoTestDataBuilder().conPlaca("ABC211").build();
		
		int diaMartes= 2;
		when(calendario.obtenerDiaActual()).thenReturn(diaMartes);
		
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
