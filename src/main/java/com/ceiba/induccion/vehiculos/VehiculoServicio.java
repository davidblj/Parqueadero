package com.ceiba.induccion.vehiculos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ceiba.induccion.excepciones.ParametrosInvalidos;
import com.ceiba.induccion.parqueadero.ParqueaderoServicio;
import com.ceiba.induccion.utils.ApiBuilder;
import com.ceiba.induccion.utils.Reglas;
import com.ceiba.induccion.vehiculos.validaciones.ValidationRule;

@Component
public class VehiculoServicio implements ImpVehiculoServicio {
	
	@Autowired
	private VehiculoRepositorio vehiculoRepositorio;
	
	@Autowired
	private ParqueaderoServicio parqueaderoServicio;
	
	@Autowired
	private Reglas reglas;

	@Override
	public void agregarVehiculo(VehiculoDTO vehiculoDTO) throws ParametrosInvalidos {
		
		VehiculoModelo vehiculo = ApiBuilder.VehiculoDTOToVehiculo(vehiculoDTO);
		
		// TODO: check existence
		// TODO: check syntax		
		
		for (ValidationRule rule: reglas.validacionesVehiculo()) {
			rule.validate(vehiculo);
		}		
						
		vehiculoRepositorio.save(ApiBuilder.VehiculoToVehiculoEntidad(vehiculo));
				
		// TODO: modify parking lot
	}
}
