package com.ceiba.induccion.vehiculos.unitarias;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import java.util.ArrayList;
import java.util.List;

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
import com.ceiba.induccion.testdatabuilder.ParqueaderoTestDataBuilder;
import com.ceiba.induccion.testdatabuilder.VehiculoTestDataBuilder;
import com.ceiba.induccion.utils.ApiBuilder;
import com.ceiba.induccion.vehiculos.VehiculoDTO;
import com.ceiba.induccion.vehiculos.VehiculoModelo;
import com.ceiba.induccion.vehiculos.VehiculoRepositorio;
import com.ceiba.induccion.vehiculos.servicios.AgregarVehiculo;
import com.ceiba.induccion.vehiculos.validaciones.agregarVehiculo.ReglaAgregarVehiculo;
import com.ceiba.induccion.vehiculos.validaciones.agregarVehiculo.ReglasAgregarVehiculo;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class AgregarVehiculoTest {	
	
	@Autowired
	AgregarVehiculo agregarVehiculo;	
	
	@MockBean
	ApiBuilder apiBuilder;
	
	@MockBean
	ReglasAgregarVehiculo reglas;
	
	@MockBean
	ParqueaderoRepositorio parqueaderoRepositorio;

	@MockBean
	VehiculoRepositorio vehiculoRepositorio;		
	
	
	@Test(expected = Test.None.class)
	public void testAgregarVehiculo() {
		
		// TODO: converter availability ?
		// TODO: mock the calendar getInstance implementation
		
		// arrange
		VehiculoModelo nuevoVehiculo = new VehiculoTestDataBuilder().build();
		VehiculoDTO nuevoVehiculoDTO= new VehiculoDTO(nuevoVehiculo.getPlaca(), nuevoVehiculo.getTipo(), nuevoVehiculo.getCilindraje());
		
		ParqueaderoModelo parqueadero = new ParqueaderoTestDataBuilder().build();
		ParqueaderoEntidad parqueaderoEntidad =  new ParqueaderoEntidad(parqueadero.getNombre(),parqueadero.getLimiteCarros(), parqueadero.getLimiteMotos());
		
		List<ReglaAgregarVehiculo> validaciones = new ArrayList<>();		
		ReglaAgregarVehiculo mockDeValidacion = mock(ReglaAgregarVehiculo.class);
		doNothing().when(mockDeValidacion).validate(any(VehiculoModelo.class));
		validaciones.add(mockDeValidacion);
		
		when(apiBuilder.vehiculoDTOToVehiculo(any(VehiculoDTO.class))).thenReturn(nuevoVehiculo);
		when(reglas.validacionesAgregarVehiculo()).thenReturn(validaciones);				
		when(parqueaderoRepositorio.findOneByNombre(anyString())).thenReturn(parqueaderoEntidad);		

		// act		
		agregarVehiculo.ejecutar(nuevoVehiculoDTO);
		
		// assert
		// no exception thrown expected (Test.none.class)
		
	}	
}
