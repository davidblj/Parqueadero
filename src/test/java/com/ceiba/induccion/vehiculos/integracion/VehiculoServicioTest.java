package com.ceiba.induccion.vehiculos.integracion;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.ceiba.induccion.parqueadero.ParqueaderoEntidad;
import com.ceiba.induccion.parqueadero.ParqueaderoModelo;
import com.ceiba.induccion.parqueadero.ParqueaderoRepositorio;
import com.ceiba.induccion.testdatabuilder.ParqueaderoTestDataBuilder;
import com.ceiba.induccion.testdatabuilder.VehiculoTestDataBuilder;
import com.ceiba.induccion.utils.ApiBuilder;
import com.ceiba.induccion.utils.Reglas;
import com.ceiba.induccion.vehiculos.VehiculoDTO;
import com.ceiba.induccion.vehiculos.VehiculoModelo;
import com.ceiba.induccion.vehiculos.VehiculoRepositorio;
import com.ceiba.induccion.vehiculos.VehiculoServicio;
import com.ceiba.induccion.vehiculos.validaciones.ValidationRule;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VehiculoServicioTest {

	@Autowired
	VehiculoServicio servicio;	 
	
	@MockBean
	ApiBuilder apiBuilder;
	
	@MockBean
	Reglas reglas;
	
	@MockBean
	ParqueaderoRepositorio parqueaderoRepositorio;

	@MockBean
	VehiculoRepositorio vehiculoRepositorio;		
	
	// TODO: check type
	// TODO: check available size 
	// TODO: check repeated car
	// TODO: check id and day
	
	@Test(expected = Test.None.class)
	public void testAgregarVehiculoConTipoInvalido() {
		
		// TODO: converter availability ?
		
		// arrange
		VehiculoModelo nuevoVehiculo = new VehiculoTestDataBuilder().build();
		VehiculoDTO nuevoVehiculoDTO= new VehiculoDTO(nuevoVehiculo.getPlaca(), nuevoVehiculo.getTipo());
		
		ParqueaderoModelo parqueadero = new ParqueaderoTestDataBuilder().build();
		ParqueaderoEntidad parqueaderoEntidad =  new ParqueaderoEntidad(parqueadero.getNombre(),parqueadero.getLimiteCarros(), parqueadero.getLimiteMotos());
		
		when(apiBuilder.vehiculoDTOToVehiculo(any(VehiculoDTO.class))).thenReturn(nuevoVehiculo);					
		when(parqueaderoRepositorio.findOneByNombre(anyString())).thenReturn(parqueaderoEntidad);		

		// act
		servicio.agregarVehiculo(nuevoVehiculoDTO);
		
		// assert
		// no exception thrown expected (Test.none.class)
	}	
}
