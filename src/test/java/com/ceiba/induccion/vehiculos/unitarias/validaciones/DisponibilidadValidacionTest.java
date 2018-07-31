package com.ceiba.induccion.vehiculos.unitarias.validaciones;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
// import static org.hamcrest.CoreMatchers.any;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.ceiba.induccion.excepciones.Conflicto;
import com.ceiba.induccion.parqueadero.ParqueaderoServicio;
import com.ceiba.induccion.testdatabuilder.VehiculoTestDataBuilder;
import com.ceiba.induccion.vehiculos.VehiculoModelo;
import com.ceiba.induccion.vehiculos.validaciones.DisponibilidadValidacion;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class DisponibilidadValidacionTest {
	
	@Autowired
	DisponibilidadValidacion disponibilidadValidacion;
	
	@MockBean
	ParqueaderoServicio parqueaderoServicio;	
	
	@Test
	public void validarParqueaderoNoDisponible() {
		
		// arrange
		when(parqueaderoServicio.estaDisponible(any(VehiculoModelo.class))).thenReturn(false);
		VehiculoModelo vehiculo = new VehiculoTestDataBuilder().build();		
		
		try {
			// act
			disponibilidadValidacion.validate(vehiculo);
			
		} catch (Conflicto e) {
			// assert
			assertThat(e.getMessage(), is("El parqueadero esta lleno"));
		}						
	}
	
	@Test(expected = Test.None.class)
	public void validarParqueaderoDisponible() {
		
		// arrange
		when(parqueaderoServicio.estaDisponible(any(VehiculoModelo.class))).thenReturn(true);
		VehiculoModelo vehiculo = new VehiculoTestDataBuilder().build();		
								
		// act
		disponibilidadValidacion.validate(vehiculo);		
	}
}
