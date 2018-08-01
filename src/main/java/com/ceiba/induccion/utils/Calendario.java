package com.ceiba.induccion.utils;

import java.util.Calendar;

import org.springframework.stereotype.Component;

@Component
public class Calendario {
	
	// TODO: how can i mock this class 
	
	public int obtenerDiaActual() {
		return obtenerFechaAcual().get(Calendar.DAY_OF_WEEK);
	}

	public Calendar obtenerFechaAcual() {
		return Calendar.getInstance();
	}
	
	public float calcularHorasTranscurridas(Calendar fechaDeIngreso) {
		
		long fechaDeIngresoEnMillis = fechaDeIngreso.getTimeInMillis();
		
		Calendar tiempoActual = obtenerFechaAcual();
		long tiempoActualEnMillis = tiempoActual.getTimeInMillis();

		long tiempoTranscurridoEnMilis = tiempoActualEnMillis - fechaDeIngresoEnMillis;
		return convertirMiliEnHoras(tiempoTranscurridoEnMilis);
	}
	
	private float convertirMiliEnHoras(long milisegundos) {		
		float equivalencia = 1000 * 60 * 60;  
		return milisegundos/equivalencia;
	}
}
