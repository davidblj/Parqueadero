package com.ceiba.induccion.vehiculos.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ceiba.induccion.parqueadero.ParqueaderoEntidad;
import com.ceiba.induccion.parqueadero.ParqueaderoRepositorio;
import com.ceiba.induccion.utils.ApiBuilder;
import com.ceiba.induccion.utils.Calendario;
import com.ceiba.induccion.utils.Constants;
import com.ceiba.induccion.vehiculos.VehiculoDTO;
import com.ceiba.induccion.vehiculos.VehiculoModelo;
import com.ceiba.induccion.vehiculos.VehiculoRepositorio;
import com.ceiba.induccion.vehiculos.validaciones.agregar.ReglaAgregarVehiculo;
import com.ceiba.induccion.vehiculos.validaciones.agregar.ReglasAgregarVehiculo;


@Component
public class AgregarVehiculo {
	
	@Autowired
	private VehiculoRepositorio vehiculoRepositorio;

	@Autowired
	private ParqueaderoRepositorio parqueaderoRepositorio;
	
	@Autowired
	private ReglasAgregarVehiculo reglas;
	
	@Autowired
	private ApiBuilder apiBuilder;
	
	@Autowired
	private Calendario calendario;
		
	public void ejecutar(VehiculoDTO vehiculoDTO) {
		
		// TODO: check syntax
		// TODO: check clean code		
				
		VehiculoModelo vehiculo = apiBuilder.vehiculoDTOToVehiculo(vehiculoDTO);			
		
		for (ReglaAgregarVehiculo rule: reglas.validacionesAgregarVehiculo()) {
			rule.validate(vehiculo);
		}
		
		agregarFechaDeIngreso(vehiculo);
		liberarCeldaSegunVehiculo(vehiculo);
		vehiculoRepositorio.save(apiBuilder.vehiculoToVehiculoEntidad(vehiculo));
	}
	
	// utils
	
	private void agregarFechaDeIngreso(VehiculoModelo vehiculo) {
		
		vehiculo.setFechaDeIngreso(calendario.obtenerFechaActual());
	}
	
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
