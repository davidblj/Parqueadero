package com.ceiba.induccion.vehiculos.validaciones;

import com.ceiba.induccion.excepciones.Conflicto;
import com.ceiba.induccion.vehiculos.VehiculoModelo;

public class CilindrajeValidacion implements ValidationRule {

	@Override
	public void validate(VehiculoModelo data) {
		
		boolean noEspecificaCilindraje = data.esMoto() && (data.getCilindraje() == 0);
		
		if (noEspecificaCilindraje) 
			throw new Conflicto("Las motos tienen que especificar un cilindraje");
	}
}
