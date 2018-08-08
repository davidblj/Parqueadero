package com.ceiba.induccion.vehiculos.validaciones.agregar;

import com.ceiba.induccion.utils.Constants;
import com.ceiba.induccion.utils.excepciones.ParametrosInvalidos;
import com.ceiba.induccion.vehiculos.VehiculoModelo;

public class TipoValidacion implements ReglaAgregarVehiculo {

	@Override
	public void validate(VehiculoModelo data) {
		
		// TODO: validate no type input
		
		String tipo = data.getTipo();
		boolean esValido =	tipo.equals(Constants.VEHICULO_CARRO) || 
							tipo.equals(Constants.VEHICULO_MOTO);
		
		if (!esValido) {
			throw new ParametrosInvalidos("El tipo del vehiculo deberia ser CARRO o MOTO");
		}
	}
}
