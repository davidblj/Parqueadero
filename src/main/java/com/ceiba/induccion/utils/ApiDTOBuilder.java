package com.ceiba.induccion.utils;

import com.ceiba.induccion.vehiculos.Vehiculo;
import com.ceiba.induccion.vehiculos.VehiculoDTO;

public class ApiDTOBuilder {
	
//	public static VehiculoDTO vehiculoToVehiculoDTO(Vehiculo vehiculo) {
//		
//	}
	
	public static Vehiculo VehiculoDTOToVehiculo(VehiculoDTO vehiculo) {
		return new Vehiculo(vehiculo.getPlaca(), vehiculo.getTipo());
	}
}
