package com.ceiba.induccion.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ceiba.induccion.dto.VehiculoDTO;
import com.ceiba.induccion.dto.VehiculoIngresadoDTO;
import com.ceiba.induccion.entidades.VehiculoEntidad;
import com.ceiba.induccion.modelos.VehiculoModelo;

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
	
	public VehiculoIngresadoDTO vehiculoEntidadToVehiculoIngresadoDTO(VehiculoEntidad vehiculo) {
		return new VehiculoIngresadoDTO(vehiculo.getPlaca(), vehiculo.getTipo(), vehiculo.getFechaDeIngreso());
	}
	
	public List<VehiculoIngresadoDTO> listaVehiculoEntidadToVehiculoDTO(List<VehiculoEntidad> vehiculos) {
		
		List<VehiculoIngresadoDTO> listaDeVehiculos = new ArrayList<>();
		
		for (VehiculoEntidad vehiculoEntidad: vehiculos) {
			VehiculoIngresadoDTO vehiculoModelo = new ApiBuilder().vehiculoEntidadToVehiculoIngresadoDTO(vehiculoEntidad);
			listaDeVehiculos.add(vehiculoModelo);
		}
		
		return listaDeVehiculos;
	}
}
