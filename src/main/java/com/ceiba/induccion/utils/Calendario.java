package com.ceiba.induccion.utils;

import java.util.Calendar;

import org.springframework.stereotype.Component;

@Component
public class Calendario {
	
	// TODO: how can i mock this class 
	
	public int obtenerDiaActual() {
		return obtenerFechaActual().get(Calendar.DAY_OF_WEEK);
	}

	public Calendar obtenerFechaActual() {
		
		Calendar calendario = Calendar.getInstance();
		calendario.clear();
		return calendario;
	}		
}
