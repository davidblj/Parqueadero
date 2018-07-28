package com.ceiba.induccion.vehiculos;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ceiba.induccion.excepciones.Conflicto;
import com.ceiba.induccion.excepciones.ParametrosInvalidos;
import com.ceiba.induccion.parqueadero.ParqueaderoServicio;
import com.ceiba.induccion.utils.ApiBuilder;
import com.ceiba.induccion.utils.Constants;
import com.ceiba.induccion.utils.Reglas;
import com.ceiba.induccion.vehiculos.validaciones.ValidationRule;

@Component
public class VehiculoServicio implements ImpVehiculoServicio {
	
	@Autowired
	private VehiculoRepositorio vehiculoRepositorio;
	
	@Autowired
	private ParqueaderoServicio parqueaderoServicio;

	@Override
	public void agregarVehiculo(VehiculoDTO vehiculoDTO) throws ParametrosInvalidos {
		
		VehiculoModelo vehiculo = ApiBuilder.VehiculoDTOToVehiculo(vehiculoDTO);
		
		// TODO: check syntax
		// TODO: check existence	
		
		for (ValidationRule rule: Reglas.validacionesVehiculo()) {
			rule.validate(vehiculo);
		}
		
		if (!parqueaderoServicio.estaDisponible(vehiculo))
			throw new Conflicto("El parqueadero esta lleno");					
				
		vehiculoRepositorio.save(ApiBuilder.VehiculoToVehiculoEntidad(vehiculo));
		
		// TODO: modify parking lot
	}
}
