package com.ceiba.induccion.vehiculos.validaciones;

import com.ceiba.induccion.vehiculos.VehiculoModelo;

public interface ValidationRule {
	void validate(VehiculoModelo data);
}
