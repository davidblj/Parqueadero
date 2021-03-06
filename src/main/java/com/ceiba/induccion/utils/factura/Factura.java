package com.ceiba.induccion.utils.factura;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ceiba.induccion.modelos.VehiculoModelo;
import com.ceiba.induccion.utils.Calendario;

@Component
public class Factura {	
	
	@Autowired
	Calendario calendario;
	
	protected int horas = 0;			
	protected int dias = 0;
	protected int precioFactura;
	private Calendar fechaDeIngreso;
	private Calendar fechaDeSalida;	
	
	public void generar(VehiculoModelo vehiculo) {
		
		fechaDeIngreso = vehiculo.getFechaDeIngreso();
		fechaDeSalida = calendario.obtenerFechaActual();
		
		float horasTranscurridas = calendario.calcularHorasTranscurridas(fechaDeIngreso, fechaDeSalida);
		calcularTiempoEstimado(horasTranscurridas);				
	}
	
	public void reset() {
		this.horas = 0;
		this.dias = 0;
	}
	
	// utils
	
	private void calcularTiempoEstimado(float horasTranscurridas) {
		
		reset();
		facturarDiasEstimados(horasTranscurridas);
		facturarHorasEstimadas(horasTranscurridas);						
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
	
	public int getPrecioFactura() {
		return precioFactura;
	}
	
	public int getHoras() {
		return horas;
	}

	public int getDias() {
		return dias;
	}
	
	public Calendar getFechaDeSalida() {
		return fechaDeSalida;
	}
	
	public Calendar getFechaDeIngreso() {
		return fechaDeIngreso;
	}
}
