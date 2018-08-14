package com.ceiba.induccion.utils.validaciones.agregar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ceiba.induccion.entidades.VehiculoEntidad;
import com.ceiba.induccion.excepciones.ParametrosInvalidos;
import com.ceiba.induccion.modelos.VehiculoModelo;
import com.ceiba.induccion.repositorios.VehiculoRepositorio;

@Component("ExistenciaValidacionAgregarVehiculo")
public class ExistenciaValidacion implements ReglaAgregarVehiculo {	
		
	@Autowired
	private VehiculoRepositorio vehiculoRepositorio;
	
	@Override
	public void validate(VehiculoModelo data) {
		
		VehiculoEntidad vehiculo = vehiculoRepositorio.findByPlaca(data.getPlaca());
		boolean vehiculoExiste = vehiculo != null;
		
		if (vehiculoExiste)
			throw new ParametrosInvalidos("El vehiculo que quieres ingresar ya se encuentra al interior del parqueadero");
	}	
}
