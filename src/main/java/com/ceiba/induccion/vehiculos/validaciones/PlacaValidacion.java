package com.ceiba.induccion.vehiculos.validaciones;

import java.util.Calendar;

import com.ceiba.induccion.excepciones.Conflicto;
import com.ceiba.induccion.vehiculos.VehiculoModelo;

public class PlacaValidacion implements ValidationRule {

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
		Calendar calendar = Calendar.getInstance();
		int day = calendar.get(Calendar.DAY_OF_WEEK);
		boolean esUnDiaHabil = 	day == Calendar.MONDAY || 
								day == Calendar.SUNDAY ;
		return esUnDiaHabil;
	}
}
