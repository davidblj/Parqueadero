package com.ceiba.induccion.utils.validaciones.eliminar;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ReglasEliminarVehiculo {
	
	@Autowired
	@Qualifier("ExistenciaValidacionEliminarVehiculo")
	ExistenciaValidacion existenciaValidacion;
	
	public List<ReglaEliminarVehiculo> validacionesEliminarVehiculo() {
		List<ReglaEliminarVehiculo> reglas = new ArrayList<>();
		reglas.add(existenciaValidacion);
		return reglas;
	}		
}
