package com.ceiba.induccion.vehiculos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ceiba.induccion.utils.ApiDTOBuilder;

@Component
public class VehiculoServicio implements ImpVehiculoServicio {
	
	@Autowired
	private VehiculoRepository repositorio;

	@Override
	public void agregarVehiculo(VehiculoDTO vehiculo) {
					
		try {
			repositorio.save(ApiDTOBuilder.VehiculoDTOToVehiculo(vehiculo));
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
			
		// add specific implementation logic
	}
}
