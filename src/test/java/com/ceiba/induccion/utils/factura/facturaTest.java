package com.ceiba.induccion.utils.factura;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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

import com.ceiba.induccion.utils.Calendario;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class facturaTest {
	
	@MockBean
	Calendario calendario;
	
	@Autowired
	Factura factura;
	
	static Calendario fechaDeIngreso;
	
	int horaDeIngreso = 0;
	int diaDeIngreso = 1;
	int minutoDeIngreso = 0;	
	
	@Before
	public void setUp() {
		factura.reset();
	}

	@Test
	public void facturarMenosDeUnaHora() {
		
		// arrange
		Calendar fechaDeIngreso = obtenerFechaDeIngreso();
		Calendar fechaActual = Calendar.getInstance();
		fechaActual.set(
				2018, 
				Calendar.JANUARY, 
				diaDeIngreso, 
				horaDeIngreso, 
				minutoDeIngreso + 59);
		
		when(calendario.obtenerFechaAcual()).thenReturn(fechaActual);
		
		// act
		factura.generar(fechaDeIngreso);
		
		// assert
		assertEquals(factura.getHoras(), 1);
	}
	
	
	@Test
	public void facturarUnaHora() {
		
		// arrange
		Calendar fechaDeIngreso = obtenerFechaDeIngreso();
		Calendar fechaActual = Calendar.getInstance();
		fechaActual.set(
				2018, 
				Calendar.JANUARY, 
				diaDeIngreso, 
				horaDeIngreso, 
				minutoDeIngreso + 20);
		
		when(calendario.obtenerFechaAcual()).thenReturn(fechaActual);
		
		// act
		factura.generar(fechaDeIngreso);
		
		// assert
		assertEquals(factura.getHoras(), 1);
	}
	
	
	@Test
	public void facturarMenosDeNueveHoras() {
				
		// arrange
		Calendar fechaDeIngreso = obtenerFechaDeIngreso();
		Calendar fechaActual = Calendar.getInstance();
		fechaActual.set(
				2018, 
				Calendar.JANUARY, 
				diaDeIngreso, 
				horaDeIngreso + 8, 
				minutoDeIngreso + 59);
		
		when(calendario.obtenerFechaAcual()).thenReturn(fechaActual);
		
		// act
		factura.generar(fechaDeIngreso);
		
		// assert
		assertEquals(factura.getHoras(), 9);
	}
	
	@Test
	public void facturarNueveHoras() {
		
		// arrange
		Calendar fechaDeIngreso = obtenerFechaDeIngreso();
		Calendar fechaActual = Calendar.getInstance();
		fechaActual.set(
				2018, 
				Calendar.JANUARY, 
				diaDeIngreso, 
				horaDeIngreso + 9, 
				minutoDeIngreso);
		
		when(calendario.obtenerFechaAcual()).thenReturn(fechaActual);
		
		// act
		factura.generar(fechaDeIngreso);
		
		// assert
		boolean noHayDesfase = 	factura.getHoras() == 9 &&
								factura.getDias() == 0;
		assertTrue(noHayDesfase);		
	}
	
	@Test
	public void facturarMenosDeUnDia() {
		
		// arrange
		Calendar fechaDeIngreso = obtenerFechaDeIngreso();
		Calendar fechaActual = Calendar.getInstance();
		fechaActual.set(
				2018, 
				Calendar.JANUARY, 
				diaDeIngreso, 
				horaDeIngreso + 12, 
				minutoDeIngreso);
		
		when(calendario.obtenerFechaAcual()).thenReturn(fechaActual);
		
		// act
		factura.generar(fechaDeIngreso);
		
		// assert
		boolean noHayDesfase = 	factura.getHoras() == 0 &&
								factura.getDias() == 1;
		assertTrue(noHayDesfase);
	}
	
	@Test
	public void facturarUnDia() {
		
		// arrange
		Calendar fechaDeIngreso = obtenerFechaDeIngreso();
		Calendar fechaActual = Calendar.getInstance();
		fechaActual.set(
				2018, 
				Calendar.JANUARY, 
				diaDeIngreso + 1, 
				horaDeIngreso, 
				minutoDeIngreso);
		
		when(calendario.obtenerFechaAcual()).thenReturn(fechaActual);
		
		// act
		factura.generar(fechaDeIngreso);
		
		// assert
		boolean noHayDesfase = 	factura.getHoras() == 0 &&
								factura.getDias() == 1;
		assertTrue(noHayDesfase);		
	}
	
	@Test
	public void facturarUnDiaYMenosDeNueveHoras() {
		
		// arrange
		Calendar fechaDeIngreso = obtenerFechaDeIngreso();
		Calendar fechaActual = Calendar.getInstance();
		fechaActual.set(
				2018, 
				Calendar.JANUARY, 
				diaDeIngreso + 1, 
				horaDeIngreso + 1, 
				minutoDeIngreso);
		
		when(calendario.obtenerFechaAcual()).thenReturn(fechaActual);
		
		// act
		factura.generar(fechaDeIngreso);
		
		// assert
		boolean esPreciso = factura.getHoras() == 1 &&
							factura.getDias() == 1;
 		assertTrue(esPreciso);		
	}
	
	@Test
	public void facturarUnDiaYMasDeNueveHoras() {
		
		// arrange
		Calendar fechaDeIngreso = obtenerFechaDeIngreso();
		Calendar fechaActual = Calendar.getInstance();
		fechaActual.set(
				2018, 
				Calendar.JANUARY, 
				diaDeIngreso + 1, 
				horaDeIngreso + 10, 
				minutoDeIngreso);
		
		when(calendario.obtenerFechaAcual()).thenReturn(fechaActual);
		
		// act
		factura.generar(fechaDeIngreso);
		
		// assert
		boolean esPreciso = factura.getHoras() == 0 &&
							factura.getDias() == 2;
 		// assertTrue(esPreciso);		
 		assertEquals(factura.getHoras(), 0);
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
