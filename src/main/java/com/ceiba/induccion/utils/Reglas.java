package com.ceiba.induccion.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ceiba.induccion.vehiculos.validaciones.PlacaValidacion;
import com.ceiba.induccion.vehiculos.validaciones.TipoValidacion;
import com.ceiba.induccion.vehiculos.validaciones.ValidationRule;
import com.ceiba.induccion.vehiculos.validaciones.DisponibilidadValidacion;

@Component
public class Reglas {
	
	@Autowired
	private DisponibilidadValidacion disponibilidadValidacion;
	
	public List<ValidationRule> validacionesVehiculo() {
		List<ValidationRule> reglas = new ArrayList<>();
		reglas.add(new PlacaValidacion());
		reglas.add(new TipoValidacion());
		reglas.add(disponibilidadValidacion);
		return reglas;
	}
}
