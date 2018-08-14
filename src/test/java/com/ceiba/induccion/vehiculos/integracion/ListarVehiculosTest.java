package com.ceiba.induccion.vehiculos.integracion;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ceiba.induccion.dto.VehiculoIngresadoDTO;
import com.ceiba.induccion.entidades.VehiculoEntidad;
import com.ceiba.induccion.entidades.VehiculoRepositorio;
import com.ceiba.induccion.modelos.VehiculoModelo;
import com.ceiba.induccion.services.VehiculoServicio;
import com.ceiba.induccion.testdatabuilder.VehiculoTestDataBuilder;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ListarVehiculosTest {
	
	@Autowired
	private VehiculoServicio servicio;
	
	@Autowired
	private VehiculoRepositorio vehiculoRepositorio;
	
	@Test
	public void listarVehiculosFacturados() {
		
		// arrange
		Calendar placeholder = Calendar.getInstance();
		VehiculoModelo vehiculoFacturado = new VehiculoTestDataBuilder()
				.conFechaDeIngreso(placeholder)
				.conFechaDeSalida(placeholder)				
				.build();
		vehiculoRepositorio.save(new VehiculoEntidad(
				vehiculoFacturado.getPlaca(),
				vehiculoFacturado.getTipo(),
				vehiculoFacturado.getCilindraje(),
				vehiculoFacturado.getFechaDeIngreso(), 
				vehiculoFacturado.getFechaDeSalida()));
		
		// act
		List<VehiculoIngresadoDTO> vehiculos = servicio.listar();
		
		// assert
		assertEquals(0, vehiculos.size());
	}
	
	@Test
	public void listarVehiculosNoFacturados() {
		
		// arrange
		Calendar placeholder = Calendar.getInstance();
		VehiculoModelo vehiculoNoFacturado = new VehiculoTestDataBuilder()
				.conFechaDeIngreso(placeholder)
				.conFechaDeSalida(null)				
				.build();
		vehiculoRepositorio.save(new VehiculoEntidad(
				vehiculoNoFacturado.getPlaca(),
				vehiculoNoFacturado.getTipo(),
				vehiculoNoFacturado.getCilindraje(),
				vehiculoNoFacturado.getFechaDeIngreso(), 
				vehiculoNoFacturado.getFechaDeSalida()));
		
		// act
		List<VehiculoIngresadoDTO> vehiculos = servicio.listar();
		
		// assert
		assertEquals(1, vehiculos.size());
	}
}
