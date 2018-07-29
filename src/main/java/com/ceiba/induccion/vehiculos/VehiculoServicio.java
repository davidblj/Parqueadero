package com.ceiba.induccion.vehiculos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ceiba.induccion.excepciones.ParametrosInvalidos;
import com.ceiba.induccion.parqueadero.ParqueaderoEntidad;
import com.ceiba.induccion.parqueadero.ParqueaderoRepositorio;
import com.ceiba.induccion.utils.ApiBuilder;
import com.ceiba.induccion.utils.Constants;
import com.ceiba.induccion.utils.Reglas;
import com.ceiba.induccion.vehiculos.validaciones.ValidationRule;

@Component
public class VehiculoServicio implements ImpVehiculoServicio {
	
	@Autowired
	private VehiculoRepositorio vehiculoRepositorio;
	
	@Autowired
	private ParqueaderoRepositorio parqueaderoRepositorio;
	
	@Autowired
	private Reglas reglas;	

	@Override
	public void agregarVehiculo(VehiculoDTO vehiculoDTO) throws ParametrosInvalidos {
		
		// TODO: check syntax
		// TODO: check clean code
		
		VehiculoModelo vehiculo = ApiBuilder.vehiculoDTOToVehiculo(vehiculoDTO);			
		
		for (ValidationRule rule: reglas.validacionesVehiculo()) {
			rule.validate(vehiculo);
		}		
						
		vehiculoRepositorio.save(ApiBuilder.vehiculoToVehiculoEntidad(vehiculo));
		liberarCeldaSegunVehiculo(vehiculo);						
	}
	
	@Override
	public VehiculoDTO consultarExistencia(String placa) {
		
		VehiculoEntidad vehiculo = vehiculoRepositorio.findByPlaca(placa);
		boolean vehiculoExiste = vehiculo != null;
		
		return vehiculoExiste ? ApiBuilder.vehiculoEntidadToVehiculoDTO(vehiculo): 
								null;		
	}
	
	// utils
	
	private void liberarCeldaSegunVehiculo(VehiculoModelo vehiculo) {
				
		ParqueaderoEntidad parqueadero = parqueaderoRepositorio.findOneByNombre(Constants.PARQUEADERO_CEIBA);	
		modificarParqueaderoSegunVehiculo(parqueadero, vehiculo);		
	}
	
	private void modificarParqueaderoSegunVehiculo(ParqueaderoEntidad parqueadero, VehiculoModelo vehiculo) {
		
		if (vehiculo.esCarro())
			parqueadero.setCarros(parqueadero.getCarros() + 1);
		
		if (vehiculo.esMoto())
			parqueadero.setMotos(parqueadero.getMotos() + 1);
		
		parqueaderoRepositorio.save(parqueadero);
	}	
}
