package com.ceiba.induccion.utils;

import org.springframework.stereotype.Component;

import com.ceiba.induccion.vehiculos.VehiculoDTO;
import com.ceiba.induccion.vehiculos.VehiculoEntidad;
import com.ceiba.induccion.vehiculos.VehiculoIngresadoDTO;
import com.ceiba.induccion.vehiculos.VehiculoModelo;

@Component
public class ApiBuilder {
	
	public VehiculoEntidad vehiculoModeloToVehiculoEntidad(VehiculoModelo vehiculo) {
		return new VehiculoEntidad(vehiculo.getPlaca(), vehiculo.getTipo(), vehiculo.getCilindraje(), vehiculo.getFechaDeIngreso());
	}
	
	public VehiculoModelo vehiculoEntidadToVehiculoModelo(VehiculoEntidad vehiculo) {
		return new VehiculoModelo(vehiculo.getPlaca(), vehiculo.getTipo(), vehiculo.getCilindraje(), vehiculo.getFechaDeIngreso());
	}
	
	public VehiculoModelo vehiculoDTOToVehiculoModelo(VehiculoDTO vehiculo) {		
		return new VehiculoModelo(vehiculo.getPlaca(), vehiculo.getTipo(), vehiculo.getCilindraje());
	}
	
	public VehiculoIngresadoDTO vehiculoEntidadToVehiculoIngresadoDT(VehiculoEntidad vehiculo) {
		return new VehiculoIngresadoDTO(vehiculo.getPlaca(), vehiculo.getTipo(), vehiculo.getFechaDeIngreso());
	}
}
