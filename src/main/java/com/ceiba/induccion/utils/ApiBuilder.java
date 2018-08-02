package com.ceiba.induccion.utils;

import com.ceiba.induccion.vehiculos.VehiculoEntidad;
import com.ceiba.induccion.vehiculos.VehiculoModelo;

import org.springframework.stereotype.Component;

import com.ceiba.induccion.vehiculos.VehiculoDTO;

@Component
public class ApiBuilder {
	
	public VehiculoEntidad vehiculoToVehiculoEntidad(VehiculoModelo vehiculo) {
		return new VehiculoEntidad(vehiculo.getPlaca(), vehiculo.getTipo(), vehiculo.getCilindraje(), vehiculo.getFechaDeIngreso());
	}
	
	public VehiculoModelo vehiculoEntidadToVehiculo(VehiculoEntidad vehiculo) {
		return new VehiculoModelo(vehiculo.getPlaca(), vehiculo.getTipo(), vehiculo.getCilindraje());
	}
	
	// TODO: marked for deletion
	public VehiculoModelo vehiculoDTOToVehiculo(VehiculoDTO vehiculo) {		
		return new VehiculoModelo(vehiculo.getPlaca(), vehiculo.getTipo(), vehiculo.getCilindraje());
	}
	
	public VehiculoDTO vehiculoEntidadToVehiculoDTO(VehiculoEntidad vehiculo) {
		return new VehiculoDTO(vehiculo.getPlaca(), vehiculo.getTipo(), vehiculo.getCilindraje());
	}			
}
