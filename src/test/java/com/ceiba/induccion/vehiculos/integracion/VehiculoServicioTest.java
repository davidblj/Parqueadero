package com.ceiba.induccion.vehiculos.integracion;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.ceiba.induccion.excepciones.Conflicto;
import com.ceiba.induccion.excepciones.ParametrosInvalidos;
import com.ceiba.induccion.parqueadero.ParqueaderoEntidad;
import com.ceiba.induccion.parqueadero.ParqueaderoRepositorio;
import com.ceiba.induccion.testdatabuilder.VehiculoTestDataBuilder;
import com.ceiba.induccion.utils.Calendario;
import com.ceiba.induccion.utils.Constants;
import com.ceiba.induccion.vehiculos.VehiculoDTO;
import com.ceiba.induccion.vehiculos.VehiculoEntidad;
import com.ceiba.induccion.vehiculos.VehiculoModelo;
import com.ceiba.induccion.vehiculos.VehiculoRepositorio;
import com.ceiba.induccion.vehiculos.VehiculoServicio;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class VehiculoServicioTest {
	
	@Autowired
	VehiculoServicio servicio;
	
	@Autowired
	private ParqueaderoRepositorio parqueaderoRepositorio;
	
	@Autowired
	private VehiculoRepositorio vehiculoRepositorio;
	
	@MockBean
	private Calendario calendario;
	
	// TODO: how can i mock without spring mockBean?	
	// TODO: code duplication 
	
	@Before
	public void SetUp() {
		parqueaderoRepositorio.deleteAll();
		vehiculoRepositorio.deleteAll();
		
		parqueaderoRepositorio.save(new ParqueaderoEntidad(
				Constants.PARQUEADERO_CEIBA, 
				Constants.PARQUEADERO_CEIBA_LIMITE_CARROS, 
				Constants.PARQUEADERO_CEIBA_LIMITE_MOTOS));
	}
			
	
	@Test
	public void testAgregarVehiculoConTipoInvalido() {		
		
		// arrange
		VehiculoModelo nuevoVehiculo = new VehiculoTestDataBuilder().conTipo("desconocido").build();
		VehiculoDTO nuevoVehiculoDTO= new VehiculoDTO(nuevoVehiculo.getPlaca(), nuevoVehiculo.getTipo());
				
		try {
			// act
			servicio.agregarVehiculo(nuevoVehiculoDTO);
			fail("Se esperaba una excepcion (ParametrosInvalidos)");
			
		} catch (ParametrosInvalidos e) {			
			// assert
			assertThat(e.getMessage(), is("El tipo del vehiculo deberia ser CARRO o MOTO"));
		}								
	}
	
		
	@Test
	public void testAgregarVehiculoConParqueaderoSinEspacio() {
		
		// arrange
		ParqueaderoEntidad parqueadero = parqueaderoRepositorio.findOneByNombre(Constants.PARQUEADERO_CEIBA);
		parqueadero.setCarros(parqueadero.getLimiteCarros());
		parqueaderoRepositorio.save(parqueadero);
				
		VehiculoModelo nuevoVehiculo = new VehiculoTestDataBuilder().build();
		VehiculoDTO nuevoVehiculoDTO= new VehiculoDTO(nuevoVehiculo.getPlaca(), nuevoVehiculo.getTipo());
				
		try {
			// act
			servicio.agregarVehiculo(nuevoVehiculoDTO);
			fail("Se esperaba una excepcion (Conflicto)");
			
		} catch (Conflicto e) {						
			// assert
			assertThat(e.getMessage(), is("El parqueadero esta lleno"));			
		}								
	}
	
	@Test
	public void testAgregarVehiculoRepetido() {
		
		// arrange
		VehiculoModelo vehiculoRepetido = new VehiculoTestDataBuilder().build();
		VehiculoEntidad vehiculoRepetidoEntidad = new VehiculoEntidad(vehiculoRepetido.getPlaca(), vehiculoRepetido.getTipo()); 
		vehiculoRepositorio.save(vehiculoRepetidoEntidad);
						
		VehiculoDTO vehiculoRepetidoDTO = new VehiculoDTO(vehiculoRepetido.getPlaca(), vehiculoRepetido.getTipo());
				
		try {
			// act
			servicio.agregarVehiculo(vehiculoRepetidoDTO);
			fail("Se esperaba una excepcion (ParametrosInvalidoss)");
			
		} catch (ParametrosInvalidos e) {						
			// assert
			assertThat(e.getMessage(), is("El vehiculo que quieres ingresar ya se encuentra al interior del parqueadero"));
		}								
	}
	
	@Test
	public void testAgregarVehiculoEnDiaInhabilitado() {
		
		// arrange
		VehiculoModelo nuevoVehiculo = new VehiculoTestDataBuilder().conPlaca("ABC211").build();
		VehiculoDTO nuevoVehiculoDTO = new VehiculoDTO(nuevoVehiculo.getPlaca(), nuevoVehiculo.getTipo());
		
		int diaMartes= 3;
		when(calendario.obtenerDiaActual()).thenReturn(diaMartes);
		
		try {
			// act
			servicio.agregarVehiculo(nuevoVehiculoDTO);
			fail("Se esperaba una excepcion (Conflicto)");
			
		} catch (Conflicto e) {
			// assert
			assertThat(e.getMessage(), is("El vehiculo no puede ingresar los lunes y domingos"));
		}
	}
	
	/*
	@Test
	public void agregarVehiculo() {
		
		// arrange
		VehiculoModelo nuevoVehiculo = new VehiculoTestDataBuilder().conTipo("desconocido").build();
		VehiculoDTO nuevoVehiculoDTO = new VehiculoDTO(nuevoVehiculo.getPlaca(), nuevoVehiculo.getTipo());
		
		// act		
		servicio.agregarVehiculo(nuevoVehiculoDTO);

		// assert
		// get vehicle
	}*/
}