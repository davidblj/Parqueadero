package com.ceiba.induccion.vehiculos.validaciones.eliminarVehiculo;

import org.springframework.beans.factory.annotation.Autowired;

import com.ceiba.induccion.excepciones.ParametrosInvalidos;
import com.ceiba.induccion.vehiculos.VehiculoDTO;
import com.ceiba.induccion.vehiculos.servicios.ObtenerVehiculo;

public class ExistenciaValidacion implements ReglaEliminarVehiculo {
	
	@Autowired
	ObtenerVehiculo obtenerVehiculo;

	@Override
	public void validate(String placa) {		
		
		VehiculoDTO vehiculo = obtenerVehiculo.ejecutar(placa);
		boolean vehiculoNoExiste = vehiculo != null;
		
		if (vehiculoNoExiste)
			throw new ParametrosInvalidos("El vehiculo actualmente no se encuentra al interior del parqueadero");
	}
}
