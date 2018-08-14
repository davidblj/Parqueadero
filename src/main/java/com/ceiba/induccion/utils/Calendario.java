package com.ceiba.induccion.utils;

import java.util.Calendar;

import org.springframework.stereotype.Component;

@Component
public class Calendario {
		
	public float calcularHorasTranscurridas(Calendar fechaDeIngreso, Calendar fechaDeSalida) {
		
		long fechaDeIngresoEnMillis = fechaDeIngreso.getTimeInMillis();
		long fechaActualEnMillis = fechaDeSalida.getTimeInMillis();
		
		long tiempoTranscurridoEnMilis = fechaActualEnMillis - fechaDeIngresoEnMillis;
		return convertirMiliEnHoras(tiempoTranscurridoEnMilis);
	}
	
	public int obtenerDiaActual() {
		return obtenerFechaActual().get(Calendar.DAY_OF_WEEK);
	}

	public Calendar obtenerFechaActual() {		
		return Calendar.getInstance();		
	}
	
	// utilities
	
	private float convertirMiliEnHoras(long milisegundos) {		
		
		float equivalencia = ((float) 1000) * 60 * 60;  
		return milisegundos/equivalencia;		
	}
}
