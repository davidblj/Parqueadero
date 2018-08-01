package com.ceiba.induccion.utils;

import java.util.Calendar;

import org.springframework.stereotype.Component;

@Component
public class Calendario {
	
	public int obtenerDiaActual() {
		return obtenerFechaAcual().get(Calendar.DAY_OF_WEEK);
	}

	public Calendar obtenerFechaAcual() {
		return Calendar.getInstance();
	}
}
