package com.ceiba.induccion.vehiculos.validaciones.agregar;

import com.ceiba.induccion.utils.excepciones.ParametrosInvalidos;
import com.ceiba.induccion.vehiculos.VehiculoModelo;

public class CilindrajeValidacion implements ReglaAgregarVehiculo {

	@Override
	public void validate(VehiculoModelo data) {
		
		boolean noEspecificaCilindraje = data.esMoto() && (data.getCilindraje() == 0);
		
		if (noEspecificaCilindraje) 
			throw new ParametrosInvalidos("Las motos tienen que especificar un cilindraje");
	}
}
