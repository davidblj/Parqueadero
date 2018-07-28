package com.ceiba.induccion.vehiculos.validaciones;

import com.ceiba.induccion.excepciones.ParametrosInvalidos;
import com.ceiba.induccion.utils.Constants;
import com.ceiba.induccion.vehiculos.VehiculoModelo;

public class TipoValidacion implements ValidationRule {

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
