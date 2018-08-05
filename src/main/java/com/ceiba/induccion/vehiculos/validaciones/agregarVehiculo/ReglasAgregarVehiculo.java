package com.ceiba.induccion.vehiculos.validaciones.agregarVehiculo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ReglasAgregarVehiculo {
	
	@Autowired
	private DisponibilidadValidacion disponibilidadValidacion;
	
	@Autowired
	@Qualifier("ExistenciaValidacionAgregarVehiculo")
	private ExistenciaValidacion existenciaValidacion;
	
	@Autowired
	private PlacaValidacion placaValidacion;
	
	public List<ReglaAgregarVehiculo> validacionesAgregarVehiculo() {
		List<ReglaAgregarVehiculo> reglas = new ArrayList<>();		
		reglas.add(new TipoValidacion());
		reglas.add(new CilindrajeValidacion());
		reglas.add(placaValidacion);
		reglas.add(disponibilidadValidacion);
		reglas.add(existenciaValidacion);
		return reglas;
	}	
}
