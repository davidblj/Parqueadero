package com.ceiba.induccion.vehiculos.validaciones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ceiba.induccion.excepciones.Conflicto;
import com.ceiba.induccion.parqueadero.ParqueaderoServicio;
import com.ceiba.induccion.vehiculos.VehiculoModelo;

@Component
public class DisponibilidadValidacion implements ValidationRule {

	@Autowired
	private ParqueaderoServicio parqueaderoServicio;
	
	@Override
	public void validate(VehiculoModelo data) {
		
		if (!parqueaderoServicio.estaDisponible(data))
			throw new Conflicto("El parqueadero esta lleno");					
	}
}
