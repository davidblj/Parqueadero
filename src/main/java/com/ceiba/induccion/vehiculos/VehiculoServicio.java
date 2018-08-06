package com.ceiba.induccion.vehiculos;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ceiba.induccion.utils.ApiBuilder;

@Component
public class VehiculoServicio {

	@Autowired
	private VehiculoRepositorio vehiculoRepositorio;
	
	@Autowired
	private ApiBuilder apiBuilder;
	
	public List<VehiculoModelo> listar() {
		
		List<VehiculoEntidad> vehiculos = vehiculoRepositorio.findByFechaDeSalidaIsNull();
		return conversorEntidadModelo(vehiculos);
	}
	
	private List<VehiculoModelo> conversorEntidadModelo(List<VehiculoEntidad> vehiculos) {
		
		List<VehiculoModelo> listaDeVehiculos = new ArrayList<>();
		
		for (VehiculoEntidad vehiculoEntidad: vehiculos) {
			VehiculoModelo vehiculoModelo = apiBuilder.vehiculoEntidadToVehiculo(vehiculoEntidad);
			listaDeVehiculos.add(vehiculoModelo);
		}
		
		return listaDeVehiculos;
	}
}
