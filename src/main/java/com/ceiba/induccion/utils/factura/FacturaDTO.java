package com.ceiba.induccion.utils.factura;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;

import com.ceiba.induccion.utils.Calendario;

public class FacturaDTO {	
	
	@Autowired
	Calendario calendario;
	
	int horas;
	int dias;
	float precio;		
	
	public int getHoras() {
		return horas;
	}
	
	public void setHoras(int horas) {
		this.horas = horas;
	}
	
	public int getDias() {
		return dias;
	}
	
	public void setDias(int dias) {
		this.dias = dias;
	}
	
	public float getPrecio() {
		return precio;
	}
	
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	
	public float calcularHorasTranscurridas(Calendar fechaDeIngreso) {
		
		long fechaDeIngresoEnMillis = fechaDeIngreso.getTimeInMillis();
		
		Calendar tiempoActual = calendario.obtenerFechaAcual();
		long tiempoActualEnMillis = tiempoActual.getTimeInMillis();

		long tiempoTranscurridoEnMilis = tiempoActualEnMillis - fechaDeIngresoEnMillis;
		return convertirMiliEnHoras(tiempoTranscurridoEnMilis);
	}
	
	private float convertirMiliEnHoras(long milisegundos) {		
		
		float equivalencia = 1000 * 60 * 60;  
		return milisegundos/equivalencia;
	}
}
