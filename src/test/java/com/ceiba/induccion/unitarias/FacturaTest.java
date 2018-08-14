package com.ceiba.induccion.unitarias;

import static org.hamcrest.CoreMatchers.*;
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

import com.ceiba.induccion.modelos.VehiculoModelo;
import com.ceiba.induccion.testdatabuilder.VehiculoTestDataBuilder;
import com.ceiba.induccion.utils.Calendario;
import com.ceiba.induccion.utils.Constants;
import com.ceiba.induccion.utils.factura.Factura;
import com.ceiba.induccion.utils.factura.FacturaFactory;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class FacturaTest {
	
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
	int segundosDeIngreso = 0;
	
	@Before
	public void setUp() {
		factura.reset();
	}
	
	// TODO: check code duplication
	// TODO: delete the reset invocation

	@Test
	public void facturarMenosDeUnaHora() {
		
		// arrange
		VehiculoModelo vehiculo = obtenerVehiculoConFechaDeIngreso();
		Calendar fechaActual = Calendar.getInstance();
		fechaActual.clear();
		fechaActual.set(
				2018, 
				Calendar.JANUARY, 
				diaDeIngreso, 
				horaDeIngreso, 
				minutoDeIngreso + 59,
				segundosDeIngreso);
		
		when(calendario.obtenerFechaActual()).thenReturn(fechaActual);
		
		// act
		factura.generar(vehiculo);
		
		// assert
		boolean noHayDesfase = factura.getHoras() == 1 &&
							   factura.getDias() == 0;
		assertTrue(noHayDesfase);
	}
	
	
	@Test
	public void facturarUnaHora() {
		
		// arrange
		VehiculoModelo vehiculo = obtenerVehiculoConFechaDeIngreso();
		Calendar fechaActual = Calendar.getInstance();
		fechaActual.clear();
		fechaActual.set(
				2018, 
				Calendar.JANUARY, 
				diaDeIngreso, 
				horaDeIngreso + 1, 
				minutoDeIngreso);
		
		when(calendario.obtenerFechaActual()).thenReturn(fechaActual);
		
		// act
		factura.generar(vehiculo);
		
		// assert
		boolean noHayDesfase = factura.getHoras() == 1 &&
				   			   factura.getDias() == 0;
		assertTrue(noHayDesfase);
	}
	
	
	@Test
	public void facturarMenosDeNueveHoras() {
				
		// arrange
		VehiculoModelo vehiculo = obtenerVehiculoConFechaDeIngreso();
		Calendar fechaActual = Calendar.getInstance();
		fechaActual.clear();
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
		boolean noHayDesfase = factura.getHoras() == 9 &&
			   				   factura.getDias() == 0;
		assertTrue(noHayDesfase);		
	}
	
	@Test
	public void facturarNueveHoras() {
		
		// arrange
		VehiculoModelo vehiculo = obtenerVehiculoConFechaDeIngreso();
		Calendar fechaActual = Calendar.getInstance();
		fechaActual.clear();
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
		fechaActual.clear();
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
		fechaActual.clear();
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
		fechaActual.clear();
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
		fechaActual.clear();
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
	public void facturarCarrosEnHoras() {
		
		// arrange
		VehiculoModelo vehiculo = obtenerVehiculoConFechaDeIngreso();
		Factura facturaCarro = facturaFactory.instanciarFactura(vehiculo.getTipo());
		facturaCarro.reset();
				
		Calendar fechaActual = Calendar.getInstance();
		fechaActual.clear();
		fechaActual.set(
				2018, 
				Calendar.JANUARY, 
				diaDeIngreso, 
				horaDeIngreso + 5, 
				minutoDeIngreso);
		
		when(calendario.obtenerFechaActual()).thenReturn(fechaActual);
				
		// act
		facturaCarro.generar(vehiculo);
		
		// assert		
		assertThat(facturaCarro.getPrecioFactura(), is(5000));		
	}
	
	
	@Test
	public void facturarCarrosEnHorasYDias() {
		
		// arrange
		VehiculoModelo vehiculo = obtenerVehiculoConFechaDeIngreso();
		Factura facturaCarro = facturaFactory.instanciarFactura(vehiculo.getTipo());
		facturaCarro.reset();
				
		Calendar fechaActual = Calendar.getInstance();
		fechaActual.clear();
		fechaActual.set(
				2018, 
				Calendar.JANUARY, 
				diaDeIngreso + 1, 
				horaDeIngreso + 5, 
				minutoDeIngreso);
		
		when(calendario.obtenerFechaActual()).thenReturn(fechaActual);
				
		// act
		facturaCarro.generar(vehiculo);
		
		// assert		
		assertThat(facturaCarro.getPrecioFactura(), is(13000));		
	}
	
	@Test
	public void facturarCarrosEnDias() {
		
		// arrange
		VehiculoModelo vehiculo = obtenerVehiculoConFechaDeIngreso();
		Factura facturaCarro = facturaFactory.instanciarFactura(vehiculo.getTipo());
		facturaCarro.reset();
				
		Calendar fechaActual = Calendar.getInstance();
		fechaActual.clear();
		fechaActual.set(
				2018, 
				Calendar.JANUARY, 
				diaDeIngreso + 2, 
				horaDeIngreso, 
				minutoDeIngreso);
		
		when(calendario.obtenerFechaActual()).thenReturn(fechaActual);
				
		// act
		facturaCarro.generar(vehiculo);
		
		// assert		
		assertThat(facturaCarro.getPrecioFactura(), is(16000));		
	}	
	
	@Test
	public void facturarMotosEnHoras() {
		
		// arrange
		VehiculoModelo vehiculo = new VehiculoTestDataBuilder()
				.conTipo(Constants.VEHICULO_MOTO)
				.conFechaDeIngreso(obtenerFechaDeIngreso())
				.build();				
		Factura facturaMoto = facturaFactory.instanciarFactura(vehiculo.getTipo());
		facturaMoto.reset();
				
		Calendar fechaActual = Calendar.getInstance();
		fechaActual.clear();
		fechaActual.set(
				2018, 
				Calendar.JANUARY, 
				diaDeIngreso, 
				horaDeIngreso + 5, 
				minutoDeIngreso);
		
		when(calendario.obtenerFechaActual()).thenReturn(fechaActual);
				
		// act
		facturaMoto.generar(vehiculo);
		
		// assert
		assertThat(facturaMoto.getPrecioFactura(), is(2500));
	}
	
	@Test
	public void facturarMotosEnHorasYDias() {
		
		// arrange
		VehiculoModelo vehiculo = new VehiculoTestDataBuilder()
				.conTipo(Constants.VEHICULO_MOTO)
				.conFechaDeIngreso(obtenerFechaDeIngreso())
				.build();				
		Factura facturaMoto = facturaFactory.instanciarFactura(vehiculo.getTipo());
		facturaMoto.reset();
				
		Calendar fechaActual = Calendar.getInstance();
		fechaActual.clear();
		fechaActual.set(
				2018, 
				Calendar.JANUARY, 
				diaDeIngreso + 1, 
				horaDeIngreso + 5, 
				minutoDeIngreso);
		
		when(calendario.obtenerFechaActual()).thenReturn(fechaActual);
				
		// act
		facturaMoto.generar(vehiculo);
		
		// assert
		assertThat(facturaMoto.getPrecioFactura(), is(6500));
	}
	
	@Test
	public void facturarMotosEnDias() {
		
		// arrange
		VehiculoModelo vehiculo = new VehiculoTestDataBuilder()
				.conTipo(Constants.VEHICULO_MOTO)
				.conFechaDeIngreso(obtenerFechaDeIngreso())
				.build();				
		Factura facturaMoto = facturaFactory.instanciarFactura(vehiculo.getTipo());
		facturaMoto.reset();
				
		Calendar fechaActual = Calendar.getInstance();
		fechaActual.clear();
		fechaActual.set(
				2018, 
				Calendar.JANUARY, 
				diaDeIngreso + 2, 
				horaDeIngreso, 
				minutoDeIngreso);
		
		when(calendario.obtenerFechaActual()).thenReturn(fechaActual);
				
		// act
		facturaMoto.generar(vehiculo);
		
		// assert
		assertThat(facturaMoto.getPrecioFactura(), is(8000));
	}
		
	@Test
	public void facturarMotosConAltoCilindraje() {
		
		// arrange
		VehiculoModelo vehiculo = new VehiculoTestDataBuilder()
				.conTipo(Constants.VEHICULO_MOTO)
				.conFechaDeIngreso(obtenerFechaDeIngreso())
				.conCilindraje(600)
				.build();				
		Factura facturaMoro = facturaFactory.instanciarFactura(vehiculo.getTipo());
		facturaMoro.reset();
		
		Calendar fechaActual = Calendar.getInstance();
		fechaActual.clear();
		fechaActual.set(
				2018, 
				Calendar.JANUARY, 
				diaDeIngreso + 1, 
				horaDeIngreso + 10, 
				minutoDeIngreso);
		
		when(calendario.obtenerFechaActual()).thenReturn(fechaActual);
				
		// act
		facturaMoro.generar(vehiculo);
		
		// assert
		assertThat(facturaMoro.getPrecioFactura(), is(10000));
	}
	
	// utils
		
	private VehiculoModelo obtenerVehiculoConFechaDeIngreso() {
				
		return new VehiculoTestDataBuilder()
				.conFechaDeIngreso(obtenerFechaDeIngreso())
				.build();
	}
	
	private Calendar obtenerFechaDeIngreso() {
		
		Calendar fechaDeIngreso = Calendar.getInstance();
		fechaDeIngreso.clear();
		fechaDeIngreso.set(
				2018, 
				Calendar.JANUARY, 
				diaDeIngreso, 
				horaDeIngreso, 
				minutoDeIngreso);
		
		return fechaDeIngreso;
	}
}
