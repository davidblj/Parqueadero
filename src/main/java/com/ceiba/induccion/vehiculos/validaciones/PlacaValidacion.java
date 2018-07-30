package com.ceiba.induccion.vehiculos.validaciones;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ceiba.induccion.excepciones.Conflicto;
import com.ceiba.induccion.utils.Calendario;
import com.ceiba.induccion.vehiculos.VehiculoModelo;

@Component
public class PlacaValidacion implements ValidationRule {
	
	@Autowired
	Calendario calendario;
	
	@Override
	public void validate(VehiculoModelo data) {
		
		String placa = data.getPlaca();
		
		char primeraLetra = placa.charAt(0);				
		boolean esLetra_A = primeraLetra == 'A';				
		boolean placaEsValida = esLetra_A ? esUnDiaValido() : true;
		
		if (!placaEsValida)
			throw new Conflicto("El vehiculo no puede ingresar los lunes y domingos");
	}
	
	private boolean esUnDiaValido() {		
		int diaActual = calendario.obtenerDiaActual();							
		boolean esUnDiaHabil = 	diaActual == Calendar.MONDAY || 
								diaActual == Calendar.SUNDAY;		
		return esUnDiaHabil;
	}
}
