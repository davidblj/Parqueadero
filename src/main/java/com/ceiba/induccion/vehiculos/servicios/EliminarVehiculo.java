package com.ceiba.induccion.vehiculos.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ceiba.induccion.vehiculos.validaciones.eliminarVehiculo.Reglas;
import com.ceiba.induccion.vehiculos.validaciones.eliminarVehiculo.ReglaEliminarVehiculo;

@Component
public class EliminarVehiculo {
	
	@Autowired
	private Reglas reglas;

	public void ejecutar(String placa) {
		
		for (ReglaEliminarVehiculo rule: reglas.validacionesEliminarVehiculo()) {
			rule.validate(placa);
		}
				
		// TODO: get vehicle
		// TODO: get hours 
			// TODO: return the total of milliseconds 
			// TODO: get current time
			// TODO: return the total of milliseconds of the current time
			// TODO: substract the total of milliseconds
			// TODO: set those milliseconds into hours
		// TODO: get price
		// TODO: prepare response object
	}
}
