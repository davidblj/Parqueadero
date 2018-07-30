package com.ceiba.induccion.utils;

import java.util.Calendar;

import org.springframework.stereotype.Component;

@Component
public class Calendario {
	
	public int obtenerDiaActual() {
		return obtenerCalendario().get(Calendar.DAY_OF_WEEK);
	}

	public Calendar obtenerCalendario() {
		return Calendar.getInstance();
	}
}
