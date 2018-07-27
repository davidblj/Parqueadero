package com.ceiba.induccion.vehiculos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ceiba.induccion.excepciones.ParametrosInvalidos;
import com.ceiba.induccion.utils.ApiDTOBuilder;

@Component
public class VehiculoServicio implements ImpVehiculoServicio {
	
	@Autowired
	private VehiculoRepository repositorio;

	@Override
	public void agregarVehiculo(VehiculoDTO vehiculoDTO) throws RuntimeException {
		
		Vehiculo vehiculo = ApiDTOBuilder.VehiculoDTOToVehiculo(vehiculoDTO);
		
		// validar placa
		
		if (vehiculo.tipoEsValido()) {
			repositorio.save(vehiculo);
		} else {
			throw new ParametrosInvalidos("El tipo del vehiculo deberia ser tipo CARRO o MOTO");
		}		
	}
}
