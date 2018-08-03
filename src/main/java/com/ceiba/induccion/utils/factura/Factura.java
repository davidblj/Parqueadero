package com.ceiba.induccion.utils.factura;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ceiba.induccion.utils.Calendario;

@Component
public class Factura {	
	
	@Autowired
	Calendario calendario;
	
	protected int horas = 0;			
	protected int dias = 0;	
	protected int precio;	 
	
	public void generar(Calendar fechaDeIngreso) {
		
		float horasTranscurridas = calcularHorasTranscurridas(fechaDeIngreso);
		calcularTiempoEstimado(horasTranscurridas);				
	}
	
	public void reset() {
		this.horas = 0;
		this.dias = 0;
	}
	
	// utils
	
	private void calcularTiempoEstimado(float horasTranscurridas) {
		
		facturarDiasEstimados(horasTranscurridas);
		facturarHorasEstimadas(horasTranscurridas);						
	}
	
	private float calcularHorasTranscurridas(Calendar fechaDeIngreso) {
		
		long fechaDeIngresoEnMillis = fechaDeIngreso.getTimeInMillis();
		
		Calendar tiempoActual = calendario.obtenerFechaActual();
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
	
	public int getPrecio() {
		return precio;
	}
	
	public int getHoras() {
		return horas;
	}

	public int getDias() {
		return dias;
	}
}
