package com.ceiba.induccion.integracion;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.ceiba.induccion.dto.VehiculoDTO;
import com.ceiba.induccion.entidades.ParqueaderoEntidad;
import com.ceiba.induccion.entidades.VehiculoEntidad;
import com.ceiba.induccion.excepciones.Conflicto;
import com.ceiba.induccion.excepciones.ParametrosInvalidos;
import com.ceiba.induccion.modelos.VehiculoModelo;
import com.ceiba.induccion.repositorios.ParqueaderoRepositorio;
import com.ceiba.induccion.repositorios.VehiculoRepositorio;
import com.ceiba.induccion.servicios.VehiculoServicio;
import com.ceiba.induccion.testdatabuilder.VehiculoTestDataBuilder;
import com.ceiba.induccion.utils.Calendario;
import com.ceiba.induccion.utils.Constants;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class AgregarVehiculoTest {		
	
	@Autowired
	private ParqueaderoRepositorio parqueaderoRepositorio;
	
	@Autowired
	private VehiculoRepositorio vehiculoRepositorio;
	
	@Autowired
	private VehiculoServicio servicio;
	
	@MockBean
	private Calendario calendario;
	
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
	public void agregarVehiculoConTipoInvalido() {		
		
		// arrange
		VehiculoModelo nuevoVehiculo = new VehiculoTestDataBuilder().conTipo("desconocido").build();
		VehiculoDTO nuevoVehiculoDTO= new VehiculoDTO(nuevoVehiculo.getPlaca(), nuevoVehiculo.getTipo(), nuevoVehiculo.getCilindraje());
				
		try {
			// act
			servicio.agregar(nuevoVehiculoDTO);
			fail("Se esperaba una excepcion (ParametrosInvalidos)");
			
		} catch (ParametrosInvalidos e) {			
			// assert
			assertThat(e.getMessage(), is("El tipo del vehiculo deberia ser CARRO o MOTO"));
		}								
	}	
		
	@Test
	public void agregarVehiculoConParqueaderoSinEspacio() {
		
		// arrange
		ParqueaderoEntidad parqueadero = parqueaderoRepositorio.findOneByNombre(Constants.PARQUEADERO_CEIBA);
		parqueadero.setCarros(parqueadero.getLimiteCarros());
		parqueaderoRepositorio.save(parqueadero);
				
		VehiculoModelo nuevoVehiculo = new VehiculoTestDataBuilder().build();
		VehiculoDTO nuevoVehiculoDTO= new VehiculoDTO(nuevoVehiculo.getPlaca(), nuevoVehiculo.getTipo(), nuevoVehiculo.getCilindraje());
				
		try {
			// act
			servicio.agregar(nuevoVehiculoDTO);
			fail("Se esperaba una excepcion (Conflicto)");
			
		} catch (Conflicto e) {						
			// assert
			assertThat(e.getMessage(), is("El parqueadero esta lleno"));			
		}								
	}
	
	@Test
	public void agregarVehiculoRepetido() {
		
		// arrange
		VehiculoModelo vehiculoRepetido = new VehiculoTestDataBuilder().build();
		VehiculoEntidad vehiculoRepetidoEntidad = new VehiculoEntidad(
				vehiculoRepetido.getPlaca(), 
				vehiculoRepetido.getTipo(), 
				vehiculoRepetido.getCilindraje(), 
				vehiculoRepetido.getFechaDeIngreso()); 
		vehiculoRepositorio.save(vehiculoRepetidoEntidad);
						
		VehiculoDTO vehiculoRepetidoDTO = new VehiculoDTO(vehiculoRepetido.getPlaca(), vehiculoRepetido.getTipo(), vehiculoRepetido.getCilindraje());
				
		try {
			// act			
			servicio.agregar(vehiculoRepetidoDTO);
			fail("Se esperaba una excepcion (ParametrosInvalidoss)");
			
		} catch (ParametrosInvalidos e) {						
			// assert
			assertThat(e.getMessage(), is("El vehiculo que quieres ingresar ya se encuentra al interior del parqueadero"));
		}								
	}
	
	@Test
	public void agregarVehiculoEnDiaInhabilitado() {
		
		// arrange
		VehiculoModelo nuevoVehiculo = new VehiculoTestDataBuilder().conPlaca("ABC211").build();
		VehiculoDTO nuevoVehiculoDTO = new VehiculoDTO(nuevoVehiculo.getPlaca(), nuevoVehiculo.getTipo(), nuevoVehiculo.getCilindraje());
		
		int diaMartes = 3;
		when(calendario.obtenerDiaActual()).thenReturn(diaMartes);
		
		try {
			// act			
			servicio.agregar(nuevoVehiculoDTO);
			fail("Se esperaba una excepcion (Conflicto)");
			
		} catch (Conflicto e) {
			// assert
			assertThat(e.getMessage(), is("El vehiculo solamente puede ingresar los lunes y domingos"));
		}
	}
	
	@Test
	public void agregarVehiculoEnDiaHabilitado() {
		
		// arrange
		VehiculoModelo nuevoVehiculo = new VehiculoTestDataBuilder().conPlaca("ABC211").build();
		VehiculoDTO nuevoVehiculoDTO = new VehiculoDTO(nuevoVehiculo.getPlaca(), nuevoVehiculo.getTipo(), nuevoVehiculo.getCilindraje());
		
		int diaLunes = 1;
		when(calendario.obtenerDiaActual()).thenReturn(diaLunes);
				
		// act
		servicio.agregar(nuevoVehiculoDTO);
			
		// assert
		VehiculoEntidad vehiculoIngresado = vehiculoRepositorio.findByPlaca(nuevoVehiculo.getPlaca());
		boolean insercionExitosa = vehiculoIngresado.getPlaca() == nuevoVehiculo.getPlaca();
		
		assertTrue(insercionExitosa);
	}			
		
	@Test
	public void agregarVehiculo() {
		
		// arrange
		VehiculoModelo nuevoVehiculo = new VehiculoTestDataBuilder().build();
		VehiculoDTO nuevoVehiculoDTO = new VehiculoDTO(nuevoVehiculo.getPlaca(), nuevoVehiculo.getTipo(), nuevoVehiculo.getCilindraje());
		
		// act
		servicio.agregar(nuevoVehiculoDTO);

		// assert		
		VehiculoEntidad vehiculoIngresado = vehiculoRepositorio.findByPlaca(nuevoVehiculo.getPlaca());
		ParqueaderoEntidad parqueaderoModificado = parqueaderoRepositorio.findOneByNombre(Constants.PARQUEADERO_CEIBA); 
		
		boolean insercionExitosa = parqueaderoModificado.getCarros() == 1 && 
								   vehiculoIngresado != null;		
		assertTrue(insercionExitosa);
	}
}
