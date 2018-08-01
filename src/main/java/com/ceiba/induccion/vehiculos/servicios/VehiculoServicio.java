package com.ceiba.induccion.vehiculos.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ceiba.induccion.excepciones.Conflicto;
import com.ceiba.induccion.excepciones.ParametrosInvalidos;
import com.ceiba.induccion.parqueadero.ParqueaderoEntidad;
import com.ceiba.induccion.parqueadero.ParqueaderoRepositorio;
import com.ceiba.induccion.utils.ApiBuilder;
import com.ceiba.induccion.utils.Constants;
import com.ceiba.induccion.utils.Reglas;
import com.ceiba.induccion.vehiculos.ImpVehiculoServicio;
import com.ceiba.induccion.vehiculos.VehiculoDTO;
import com.ceiba.induccion.vehiculos.VehiculoEntidad;
import com.ceiba.induccion.vehiculos.VehiculoModelo;
import com.ceiba.induccion.vehiculos.VehiculoRepositorio;
import com.ceiba.induccion.vehiculos.validaciones.ValidationRule;

@Component
public class VehiculoServicio implements ImpVehiculoServicio {
	
	@Autowired
	private VehiculoRepositorio vehiculoRepositorio;
	
	@Autowired
	private ParqueaderoRepositorio parqueaderoRepositorio;
	
	@Autowired
	private Reglas reglas;
	
	@Autowired
	private ApiBuilder apiBuilder;

	@Override
	public void agregarVehiculo(VehiculoDTO vehiculoDTO) throws ParametrosInvalidos, Conflicto {
		
		// TODO: check syntax
		// TODO: check clean code
		
		VehiculoModelo vehiculo = apiBuilder.vehiculoDTOToVehiculo(vehiculoDTO);			
		
		for (ValidationRule rule: reglas.validacionesVehiculo()) {
			rule.validate(vehiculo);
		}		

		liberarCeldaSegunVehiculo(vehiculo);
		vehiculoRepositorio.save(apiBuilder.vehiculoToVehiculoEntidad(vehiculo));
	}
	
	@Override
	public VehiculoDTO consultarExistencia(String placa) {
		
		VehiculoEntidad vehiculo = vehiculoRepositorio.findByPlaca(placa);
		boolean vehiculoExiste = vehiculo != null;
		
		return vehiculoExiste ? apiBuilder.vehiculoEntidadToVehiculoDTO(vehiculo): 
								null;
	}
	
	// utils
	
	private void liberarCeldaSegunVehiculo(VehiculoModelo vehiculo) {
		
		// TODO: research the entity class management
				
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
