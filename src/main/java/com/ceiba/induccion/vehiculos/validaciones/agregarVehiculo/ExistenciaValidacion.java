package com.ceiba.induccion.vehiculos.validaciones.agregarVehiculo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ceiba.induccion.excepciones.ParametrosInvalidos;
import com.ceiba.induccion.vehiculos.VehiculoDTO;
import com.ceiba.induccion.vehiculos.VehiculoModelo;
import com.ceiba.induccion.vehiculos.servicios.ObtenerVehiculo;

@Component
public class ExistenciaValidacion implements ReglaAgregarVehiculo {	
		
	@Autowired
	ObtenerVehiculo obtenerVehiculo;
	
	@Override
	public void validate(VehiculoModelo data) {
		
		VehiculoDTO vehiculo = obtenerVehiculo.ejecutar(data.getPlaca());
		boolean vehiculoExiste = vehiculo != null;
		
		if (vehiculoExiste)
			throw new ParametrosInvalidos("El vehiculo que quieres ingresar ya se encuentra al interior del parqueadero");
	}	
}
