package com.ceiba.induccion.utils.validaciones.eliminar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ceiba.induccion.entidades.VehiculoEntidad;
import com.ceiba.induccion.excepciones.ParametrosInvalidos;
import com.ceiba.induccion.repositorios.VehiculoRepositorio;

@Component("ExistenciaValidacionEliminarVehiculo")
public class ExistenciaValidacion implements ReglaEliminarVehiculo {
			
	@Autowired
	VehiculoRepositorio vehiculoRepositorio;

	@Override
	public void validate(String placa) {		
		
		VehiculoEntidad vehiculo = vehiculoRepositorio.findByPlaca(placa);
		boolean vehiculoNoExiste = vehiculo == null ||
								   vehiculo.getFechaDeSalida() != null;
		
		if (vehiculoNoExiste)
			throw new ParametrosInvalidos("El vehiculo actualmente no se encuentra al interior del parqueadero");
	}
}
