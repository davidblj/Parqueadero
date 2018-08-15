package com.ceiba.induccion.utils.validaciones.agregar;

import com.ceiba.induccion.excepciones.ParametrosInvalidos;
import com.ceiba.induccion.modelos.VehiculoModelo;
import com.ceiba.induccion.utils.Constants;

public class TipoValidacion implements ReglaAgregarVehiculo {

	@Override
	public void validate(VehiculoModelo data) {
				
		String tipo = data.getTipo();
		boolean esValido =	tipo.equals(Constants.VEHICULO_CARRO) || 
							tipo.equals(Constants.VEHICULO_MOTO);
		
		if (!esValido) {
			throw new ParametrosInvalidos("El tipo del vehiculo deberia ser CARRO o MOTO");
		}
	}
}
