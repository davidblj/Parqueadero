package com.ceiba.induccion.parqueadero.integracion;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.ceiba.induccion.entidades.VehiculoEntidad;
import com.ceiba.induccion.entidades.VehiculoRepositorio;
import com.ceiba.induccion.modelos.VehiculoModelo;
import com.ceiba.induccion.parqueadero.ParqueaderoEntidad;
import com.ceiba.induccion.parqueadero.ParqueaderoRepositorio;
import com.ceiba.induccion.parqueadero.ParqueaderoServicio;
import com.ceiba.induccion.testdatabuilder.VehiculoTestDataBuilder;
import com.ceiba.induccion.utils.Constants;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ConsultarDisponibilidadTest {
	
	@Autowired
	private ParqueaderoRepositorio parqueaderoRepositorio;
	
	@Autowired
	private VehiculoRepositorio vehiculoRepositorio;
	
	@Autowired
	private ParqueaderoServicio parqueaderoServicio;
	
	@Before
	public void setUp() {
		
		parqueaderoRepositorio.deleteAll();
		vehiculoRepositorio.deleteAll();
		
		parqueaderoRepositorio.save(new ParqueaderoEntidad(
				Constants.PARQUEADERO_CEIBA, 
				Constants.PARQUEADERO_CEIBA_LIMITE_CARROS, 
				Constants.PARQUEADERO_CEIBA_LIMITE_MOTOS));
	}
	
	@Test
	public void validarCeldasLibresParaMotos() {
		
		// arrange
		VehiculoModelo moto = new VehiculoTestDataBuilder()
				.conTipo(Constants.VEHICULO_MOTO)
				.build();								
		
		// act
		boolean estaDisponible = parqueaderoServicio.estaDisponible(moto);		
		
		// assert
		assertTrue(estaDisponible);
	}
	
	@Test
	public void validarCeldasLibresParaCarros() {
		
		// arrange
		VehiculoModelo carros = new VehiculoTestDataBuilder()
				.conTipo(Constants.VEHICULO_CARRO)
				.build();							
		
		// act
		boolean estaDisponible = parqueaderoServicio.estaDisponible(carros);		
		
		// assert
		assertTrue(estaDisponible);
	}
	
	@Test
	@Transactional
	public void validarCeldasOcupadasParaMotos() {
		
		// arrange
		VehiculoModelo moto = new VehiculoTestDataBuilder()
				.conTipo(Constants.VEHICULO_MOTO)
				.build();		
		
		this.ocuparCeldasTipoMoto();		
		
		// act
		boolean estaDisponible = parqueaderoServicio.estaDisponible(moto);		
		
		// assert
		assertFalse(estaDisponible);
	}
	
	@Test
	@Transactional
	public void validarCeldasOcupadasParaCarros() {
		
		// arrange
		VehiculoModelo carros = new VehiculoTestDataBuilder()
				.conTipo(Constants.VEHICULO_CARRO)
				.build();		
		
		this.ocuparCeldasTipoCarro();		
		
		// act
		boolean estaDisponible = parqueaderoServicio.estaDisponible(carros);		
		
		// assert
		assertFalse(estaDisponible);
	}
		
	public void ocuparCeldasTipoMoto() {
		this.parqueaderoRepositorio.updateLimiteMotos(Constants.PARQUEADERO_CEIBA_LIMITE_MOTOS, Constants.PARQUEADERO_CEIBA);
	}
	
	private void ocuparCeldasTipoCarro() {
		this.parqueaderoRepositorio.updateLimiteCarros(Constants.PARQUEADERO_CEIBA_LIMITE_CARROS, Constants.PARQUEADERO_CEIBA);

	}
}
