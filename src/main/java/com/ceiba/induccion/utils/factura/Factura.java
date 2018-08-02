package com.ceiba.induccion.utils.factura;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;

import com.ceiba.induccion.utils.Calendario;

public class Factura {	
	
	@Autowired
	Calendario calendario;
	
	private int horas = 0;		
	private int dias = 0;
	private float precio;	
	
	public void generar(Calendar fechaDeIngreso) {
		
		float horasTranscurridas = calcularHorasTranscurridas(fechaDeIngreso);
		calcularTiempoEstimado(horasTranscurridas);				
		// TODO: generate the total price (on each subclass)
	}
	
	// utils
	
	private void calcularTiempoEstimado(float horasTranscurridas) {
		
		facturarDiasEstimados(horasTranscurridas);
		facturarHorasEstimadas(horasTranscurridas);						
	}
	
	private float calcularHorasTranscurridas(Calendar fechaDeIngreso) {
		
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
	
	private void facturarDiasEstimados(float horasTranscurridas) {
		
		float diasTranscurridos = horasTranscurridas/24;
		if (diasTranscurridos > 0) {
			this.dias = (int) Math.floor(diasTranscurridos);
		}		
	}
	
	private void facturarHorasEstimadas(float horasTranscurridas) {
		
		float horasDelUltimoDia = horasTranscurridas % 24; 
		int horasAproximadas = (int) Math.ceil(horasDelUltimoDia);
				
		boolean seCuentaComoUnDia = horasAproximadas > 9;
		
		if (seCuentaComoUnDia) {
			dias += 1;
		} else {
			horas = horasAproximadas;
		}
	}
	
	public float getPrecio() {
		return precio;
	}	
}
