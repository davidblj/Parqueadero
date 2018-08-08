package com.ceiba.induccion.parqueadero.unitarias;

import static org.mockito.Mockito.when;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.ceiba.induccion.parqueadero.ParqueaderoEntidad;
import com.ceiba.induccion.parqueadero.ParqueaderoModelo;
import com.ceiba.induccion.parqueadero.ParqueaderoRepositorio;
import com.ceiba.induccion.parqueadero.ParqueaderoServicio;
import com.ceiba.induccion.testdatabuilder.ParqueaderoTestDataBuilder;
import com.ceiba.induccion.testdatabuilder.VehiculoTestDataBuilder;
import com.ceiba.induccion.utils.Constants;
import com.ceiba.induccion.vehiculos.VehiculoModelo;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ConsultarDisponibilidadTest {

	@Autowired
	ParqueaderoServicio parqueaderoServicio;
	
	@MockBean
	ParqueaderoRepositorio parqueaderoRepositorio;
	
	@Test
	public void validarCeldasLibresParaMotos() {
		
		// arrange
		VehiculoModelo moto = new VehiculoTestDataBuilder()
				.conTipo(Constants.VEHICULO_MOTO)
				.build();
		ParqueaderoModelo parqueadero = new ParqueaderoTestDataBuilder()
				.conLimiteDeMotos(1)
				.build();
		ParqueaderoEntidad parqueaderoEntidad = new ParqueaderoEntidad(
				parqueadero.getNombre(), 
				parqueadero.getLimiteCarros(), 
				parqueadero.getLimiteMotos()); 
		
		when(parqueaderoRepositorio.findOneByNombre(anyString())).thenReturn(parqueaderoEntidad);
		
		// act
		boolean estaDisponible = parqueaderoServicio.estaDisponible(moto);
		
		// assert
		assertTrue(estaDisponible);
	}
	
	@Test
	public void validarCeldasLibresParaCarros() {
		
		// arrange
		VehiculoModelo carro = new VehiculoTestDataBuilder()
				.conTipo(Constants.VEHICULO_CARRO)
				.build();
		ParqueaderoModelo parqueadero = new ParqueaderoTestDataBuilder()
				.conLimiteDeCarros(1)
				.build();
		ParqueaderoEntidad parqueaderoEntidad = new ParqueaderoEntidad(
				parqueadero.getNombre(), 
				parqueadero.getLimiteCarros(), 
				parqueadero.getLimiteMotos()); 
		
		when(parqueaderoRepositorio.findOneByNombre(anyString())).thenReturn(parqueaderoEntidad);
		
		// act
		boolean estaDisponible = parqueaderoServicio.estaDisponible(carro);
		
		// assert
		assertTrue(estaDisponible);
	}
	
	@Test
	public void validarCeldasOcupadasParaMotos() {
		
		// arrange
		VehiculoModelo moto = new VehiculoTestDataBuilder()
				.conTipo(Constants.VEHICULO_MOTO)
				.build();
		ParqueaderoModelo parqueadero = new ParqueaderoTestDataBuilder()
				.conLimiteDeMotos(0)
				.build();
		ParqueaderoEntidad parqueaderoEntidad = new ParqueaderoEntidad(
				parqueadero.getNombre(), 
				parqueadero.getLimiteCarros(), 
				parqueadero.getLimiteMotos()); 
		
		when(parqueaderoRepositorio.findOneByNombre(anyString())).thenReturn(parqueaderoEntidad);
		
		// act
		boolean estaDisponible = parqueaderoServicio.estaDisponible(moto);
		
		// assert
		assertFalse(estaDisponible);
	}
	
	@Test
	public void validarCeldasOcupadasParaCarros() {
		
		// arrange
		VehiculoModelo carro = new VehiculoTestDataBuilder()
				.conTipo(Constants.VEHICULO_CARRO)
				.build();
		ParqueaderoModelo parqueadero = new ParqueaderoTestDataBuilder()
				.conLimiteDeCarros(0)
				.build();
		ParqueaderoEntidad parqueaderoEntidad = new ParqueaderoEntidad(
				parqueadero.getNombre(), 
				parqueadero.getLimiteCarros(), 
				parqueadero.getLimiteMotos()); 
		
		when(parqueaderoRepositorio.findOneByNombre(anyString())).thenReturn(parqueaderoEntidad);
		
		// act
		boolean estaDisponible = parqueaderoServicio.estaDisponible(carro);
		
		// assert
		assertFalse(estaDisponible);
	}
}
