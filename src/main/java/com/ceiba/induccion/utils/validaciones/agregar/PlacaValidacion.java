package com.ceiba.induccion.utils.validaciones.agregar;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ceiba.induccion.excepciones.Conflicto;
import com.ceiba.induccion.modelos.VehiculoModelo;
import com.ceiba.induccion.utils.Calendario;

@Component
public class PlacaValidacion implements ReglaAgregarVehiculo {
	
	@Autowired
	Calendario calendario;
	
	@Override
	public void validate(VehiculoModelo data) {
		
		String placa = data.getPlaca();
		
		char primeraLetra = placa.charAt(0);				
		boolean esLetraA = primeraLetra == 'A';				
		
		boolean placaEsValida = true;
		if (esLetraA) {
			placaEsValida = esUnDiaValido();
		}
		
		if (!placaEsValida)
			throw new Conflicto("El vehiculo solamente puede ingresar los lunes y domingos");
	}
	
	private boolean esUnDiaValido() {		
		int diaActual = calendario.obtenerDiaActual();							
		return diaActual == Calendar.MONDAY || diaActual == Calendar.SUNDAY;				
	}
}
