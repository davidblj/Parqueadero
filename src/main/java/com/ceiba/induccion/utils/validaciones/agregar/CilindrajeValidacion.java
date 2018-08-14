package com.ceiba.induccion.utils.validaciones.agregar;

import com.ceiba.induccion.excepciones.ParametrosInvalidos;
import com.ceiba.induccion.modelos.VehiculoModelo;

public class CilindrajeValidacion implements ReglaAgregarVehiculo {

	@Override
	public void validate(VehiculoModelo data) {
		
		boolean noEspecificaCilindraje = data.esMoto() && (data.getCilindraje() == 0);
		
		if (noEspecificaCilindraje) 
			throw new ParametrosInvalidos("Las motos tienen que especificar un cilindraje");
	}
}
