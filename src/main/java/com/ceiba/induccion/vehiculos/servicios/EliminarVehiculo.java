package com.ceiba.induccion.vehiculos.servicios;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ceiba.induccion.utils.Calendario;
import com.ceiba.induccion.utils.factura.FacturaDTO;
import com.ceiba.induccion.vehiculos.VehiculoEntidad;
import com.ceiba.induccion.vehiculos.VehiculoRepositorio;
import com.ceiba.induccion.vehiculos.validaciones.eliminarVehiculo.ReglaEliminarVehiculo;
import com.ceiba.induccion.vehiculos.validaciones.eliminarVehiculo.ReglasEliminarVehiculo;


@Component
public class EliminarVehiculo {
	
	@Autowired
	private VehiculoRepositorio vehiculoRepositorio;
	
	@Autowired
	private ReglasEliminarVehiculo reglas;
	
	@Autowired
	private Calendario calendario;

	public FacturaDTO ejecutar(String placa) {
		
		for (ReglaEliminarVehiculo rule: reglas.validacionesEliminarVehiculo()) {
			rule.validate(placa);
		}
		
		VehiculoEntidad vehiculo = vehiculoRepositorio.findByPlaca(placa);
		float horasTranscurridas = calendario.calcularHorasTranscurridas(vehiculo.getFechaDeIngreso());				
				
		// TODO: get price (using polymorphism)
		// TODO: prepare response object
		
		return new FacturaDTO(0, 0, horasTranscurridas);
	}	
}
