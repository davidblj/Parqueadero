package com.ceiba.induccion.utils;

import com.ceiba.induccion.vehiculos.VehiculoEntidad;
import com.ceiba.induccion.vehiculos.VehiculoModelo;
import com.ceiba.induccion.vehiculos.VehiculoDTO;

public class ApiBuilder {
	
	public static VehiculoModelo vehiculoDTOToVehiculo(VehiculoDTO vehiculo) {
		return new VehiculoModelo(vehiculo.getPlaca(), vehiculo.getTipo());
	}
	
	public static VehiculoEntidad vehiculoToVehiculoEntidad(VehiculoModelo vehiculo) {
		return new VehiculoEntidad(vehiculo.getPlaca(), vehiculo.getTipo());
	}
	
	public static VehiculoDTO vehiculoEntidadToVehiculoDTO(VehiculoEntidad vehiculo) {
		return new VehiculoDTO(vehiculo.getPlaca(), vehiculo.getTipo());
	}
}
