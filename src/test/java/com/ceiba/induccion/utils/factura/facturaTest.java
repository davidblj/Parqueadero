package com.ceiba.induccion.utils.factura;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.ceiba.induccion.testdatabuilder.VehiculoTestDataBuilder;
import com.ceiba.induccion.utils.Calendario;
import com.ceiba.induccion.utils.Constants;
import com.ceiba.induccion.vehiculos.VehiculoModelo;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class facturaTest {
	
	@MockBean
	Calendario calendario;
	
	@Autowired
	Factura factura;
	
	@Autowired
	FacturaFactory facturaFactory;
	
	static Calendario fechaDeIngreso;
	
	int horaDeIngreso = 0;
	int diaDeIngreso = 1;
	int minutoDeIngreso = 0;	
	
	@Before
	public void setUp() {
		factura.reset();
	}
	
	// TODO: check code duplication

	@Test
	public void facturarMenosDeUnaHora() {
		
		// arrange
		VehiculoModelo vehiculo = obtenerVehiculoConFechaDeIngreso();
		Calendar fechaActual = Calendar.getInstance();
		fechaActual.set(
				2018, 
				Calendar.JANUARY, 
				diaDeIngreso, 
				horaDeIngreso, 
				minutoDeIngreso + 59);
		
		when(calendario.obtenerFechaActual()).thenReturn(fechaActual);
		
		// act
		factura.generar(vehiculo);
		
		// assert
		assertEquals(factura.getHoras(), 1);
	}
	
	
	@Test
	public void facturarUnaHora() {
		
		// arrange
		VehiculoModelo vehiculo = obtenerVehiculoConFechaDeIngreso();
		Calendar fechaActual = Calendar.getInstance();
		fechaActual.set(
				2018, 
				Calendar.JANUARY, 
				diaDeIngreso, 
				horaDeIngreso, 
				minutoDeIngreso + 20);
		
		when(calendario.obtenerFechaActual()).thenReturn(fechaActual);
		
		// act
		factura.generar(vehiculo);
		
		// assert
		assertEquals(factura.getHoras(), 1);
	}
	
	
	@Test
	public void facturarMenosDeNueveHoras() {
				
		// arrange
		VehiculoModelo vehiculo = obtenerVehiculoConFechaDeIngreso();
		Calendar fechaActual = Calendar.getInstance();
		fechaActual.set(
				2018, 
				Calendar.JANUARY, 
				diaDeIngreso, 
				horaDeIngreso + 8, 
				minutoDeIngreso + 59);
		
		when(calendario.obtenerFechaActual()).thenReturn(fechaActual);
		
		// act
		factura.generar(vehiculo);
		
		// assert
		assertEquals(factura.getHoras(), 9);
	}
	
	@Test
	public void facturarNueveHoras() {
		
		// arrange
		VehiculoModelo vehiculo = obtenerVehiculoConFechaDeIngreso();
		Calendar fechaActual = Calendar.getInstance();
		fechaActual.set(
				2018, 
				Calendar.JANUARY, 
				diaDeIngreso, 
				horaDeIngreso + 9, 
				minutoDeIngreso);
		
		when(calendario.obtenerFechaActual()).thenReturn(fechaActual);
		
		// act
		factura.generar(vehiculo);
		
		// assert
		boolean noHayDesfase = 	factura.getHoras() == 9 &&
								factura.getDias() == 0;
		assertTrue(noHayDesfase);		
	}
	
	@Test
	public void facturarMenosDeUnDia() {
		
		// arrange
		VehiculoModelo vehiculo = obtenerVehiculoConFechaDeIngreso();
		Calendar fechaActual = Calendar.getInstance();
		fechaActual.set(
				2018, 
				Calendar.JANUARY, 
				diaDeIngreso, 
				horaDeIngreso + 12, 
				minutoDeIngreso);
		
		when(calendario.obtenerFechaActual()).thenReturn(fechaActual);
		
		// act
		factura.generar(vehiculo);
		
		// assert
		boolean noHayDesfase = 	factura.getHoras() == 0 &&
								factura.getDias() == 1;
		assertTrue(noHayDesfase);
	}
	
	@Test
	public void facturarUnDia() {
		
		// arrange
		VehiculoModelo vehiculo = obtenerVehiculoConFechaDeIngreso();
		Calendar fechaActual = Calendar.getInstance();
		fechaActual.set(
				2018, 
				Calendar.JANUARY, 
				diaDeIngreso + 1, 
				horaDeIngreso, 
				minutoDeIngreso);
		
		when(calendario.obtenerFechaActual()).thenReturn(fechaActual);
		
		// act
		factura.generar(vehiculo);
		
		// assert
		boolean noHayDesfase = 	factura.getHoras() == 0 &&
								factura.getDias() == 1;
		assertTrue(noHayDesfase);		
	}
	
	@Test
	public void facturarUnDiaYMenosDeNueveHoras() {
		
		// arrange
		VehiculoModelo vehiculo = obtenerVehiculoConFechaDeIngreso();
		Calendar fechaActual = Calendar.getInstance();
		fechaActual.set(
				2018, 
				Calendar.JANUARY, 
				diaDeIngreso + 1, 
				horaDeIngreso + 1, 
				minutoDeIngreso);
		
		when(calendario.obtenerFechaActual()).thenReturn(fechaActual);
		
		// act
		factura.generar(vehiculo);
		
		// assert
		boolean esPreciso = factura.getHoras() == 1 &&
							factura.getDias() == 1;
 		assertTrue(esPreciso);		
	}
	
	@Test
	public void facturarUnDiaYMasDeNueveHoras() {
		
		// arrange
		VehiculoModelo vehiculo = obtenerVehiculoConFechaDeIngreso();
		Calendar fechaActual = Calendar.getInstance();
		fechaActual.set(
				2018, 
				Calendar.JANUARY, 
				diaDeIngreso + 1, 
				horaDeIngreso + 10, 
				minutoDeIngreso);
		
		when(calendario.obtenerFechaActual()).thenReturn(fechaActual);
		
		// act
		factura.generar(vehiculo);
		
		// assert
		boolean esPreciso = factura.getHoras() == 0 &&
							factura.getDias() == 2;
 		assertTrue(esPreciso);		
	}
	
	
	@Test
	public void facturarCarro() {
		
		// arrange
		VehiculoModelo vehiculo = obtenerVehiculoConFechaDeIngreso();
		Factura facturaCarro = facturaFactory.instanciarFactura(vehiculo.getTipo());
				
		Calendar fechaActual = Calendar.getInstance();
		fechaActual.set(
				2018, 
				Calendar.JANUARY, 
				diaDeIngreso + 1, 
				horaDeIngreso + 10, 
				minutoDeIngreso);
		
		when(calendario.obtenerFechaActual()).thenReturn(fechaActual);
				
		// act
		facturaCarro.generar(vehiculo);
		
		// assert
		assertThat(facturaCarro.getPrecioFactura(), is(16000));		
	}
	
	@Test
	public void facturarMotos() {
		
		// arrange
		VehiculoModelo vehiculo = new VehiculoTestDataBuilder()
				.conTipo(Constants.VEHICULO_MOTO)
				.conFechaDeIngreso(obtenerFechaDeIngreso())
				.build();				
		Factura facturaCarro = facturaFactory.instanciarFactura(vehiculo.getTipo());
				
		Calendar fechaActual = Calendar.getInstance();
		fechaActual.set(
				2018, 
				Calendar.JANUARY, 
				diaDeIngreso + 1, 
				horaDeIngreso + 10, 
				minutoDeIngreso);
		
		when(calendario.obtenerFechaActual()).thenReturn(fechaActual);
				
		// act
		facturaCarro.generar(vehiculo);
		
		// assert
		assertThat(facturaCarro.getPrecioFactura(), is(8000));
	}
		
	@Test
	public void facturarMotosConAltoCilindraje() {
		
		// arrange
		VehiculoModelo vehiculo = new VehiculoTestDataBuilder()
				.conTipo(Constants.VEHICULO_MOTO)
				.conFechaDeIngreso(obtenerFechaDeIngreso())
				.conCilindraje(600)
				.build();				
		Factura facturaCarro = facturaFactory.instanciarFactura(vehiculo.getTipo());
		
		Calendar fechaActual = Calendar.getInstance();
		fechaActual.set(
				2018, 
				Calendar.JANUARY, 
				diaDeIngreso + 1, 
				horaDeIngreso + 10, 
				minutoDeIngreso);
		
		when(calendario.obtenerFechaActual()).thenReturn(fechaActual);
				
		// act
		facturaCarro.generar(vehiculo);
		
		// assert
		assertThat(facturaCarro.getPrecioFactura(), is(10000));
	}
	
	// utils
		
	private VehiculoModelo obtenerVehiculoConFechaDeIngreso() {
				
		return new VehiculoTestDataBuilder()
				.conFechaDeIngreso(obtenerFechaDeIngreso())
				.build();
	}
	
	private Calendar obtenerFechaDeIngreso() {
		
		Calendar fechaDeIngreso = Calendar.getInstance();
		fechaDeIngreso.set(
				2018, 
				Calendar.JANUARY, 
				diaDeIngreso, 
				horaDeIngreso, 
				minutoDeIngreso);
		
		return fechaDeIngreso;
	}
}
