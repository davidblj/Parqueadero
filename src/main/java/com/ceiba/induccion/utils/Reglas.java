package com.ceiba.induccion.utils;

import java.util.ArrayList;
import java.util.List;

import com.ceiba.induccion.vehiculos.validaciones.PlacaValidacion;
import com.ceiba.induccion.vehiculos.validaciones.TipoValidacion;
import com.ceiba.induccion.vehiculos.validaciones.ValidationRule;

public class Reglas {
	
	public static List<ValidationRule> validacionesVehiculo() {
		List<ValidationRule> reglas = new ArrayList<>();
		reglas.add(new PlacaValidacion());
		reglas.add(new TipoValidacion());
		return reglas;
	}
}
