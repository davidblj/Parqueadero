package com.ceiba.induccion.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ceiba.induccion.vehiculos.validaciones.PlacaValidacion;
import com.ceiba.induccion.vehiculos.validaciones.TipoValidacion;
import com.ceiba.induccion.vehiculos.validaciones.ValidationRule;
import com.ceiba.induccion.vehiculos.validaciones.CilindrajeValidacion;
import com.ceiba.induccion.vehiculos.validaciones.DisponibilidadValidacion;
import com.ceiba.induccion.vehiculos.validaciones.ExistenciaValidacion;

@Component
public class Reglas {
	
	@Autowired
	private DisponibilidadValidacion disponibilidadValidacion;
	
	@Autowired
	private ExistenciaValidacion existenciaValidacion;
	
	@Autowired
	private PlacaValidacion placaValidacion;
	
	public List<ValidationRule> validacionesVehiculo() {
		List<ValidationRule> reglas = new ArrayList<>();		
		reglas.add(new TipoValidacion());
		reglas.add(new CilindrajeValidacion());
		reglas.add(placaValidacion);
		reglas.add(disponibilidadValidacion);
		reglas.add(existenciaValidacion);
		return reglas;
	}
}
