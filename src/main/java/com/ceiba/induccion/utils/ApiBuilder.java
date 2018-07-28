package com.ceiba.induccion.utils;

import com.ceiba.induccion.vehiculos.VehiculoEntidad;
import com.ceiba.induccion.vehiculos.VehiculoModelo;
import com.ceiba.induccion.vehiculos.VehiculoDTO;

public class ApiBuilder {
	
//	public static VehiculoDTO vehiculoToVehiculoDTO(Vehiculo vehiculo) {
//		
//	}
	
	public static VehiculoModelo VehiculoDTOToVehiculo(VehiculoDTO vehiculo) {
		return new VehiculoModelo(vehiculo.getPlaca(), vehiculo.getTipo());
	}
	
	public static VehiculoEntidad VehiculoToVehiculoEntidad(VehiculoModelo vehiculo) {
		return new VehiculoEntidad(vehiculo.getPlaca(), vehiculo.getTipo());
	}		
}
