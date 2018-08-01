package com.ceiba.induccion.vehiculos.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ceiba.induccion.utils.ApiBuilder;
import com.ceiba.induccion.vehiculos.VehiculoDTO;
import com.ceiba.induccion.vehiculos.VehiculoEntidad;
import com.ceiba.induccion.vehiculos.VehiculoRepositorio;

@Component
public class ObtenerVehiculo {	
	
	@Autowired
	private VehiculoRepositorio vehiculoRepositorio;
	
	@Autowired
	private ApiBuilder apiBuilder;

	public VehiculoDTO ejecutar(String placa) {
		
		VehiculoEntidad vehiculo = vehiculoRepositorio.findByPlaca(placa);
		boolean vehiculoExiste = vehiculo != null;
		
		return vehiculoExiste ? apiBuilder.vehiculoEntidadToVehiculoDTO(vehiculo): 
								null;
	}
}
