package com.ceiba.induccion.utils.validaciones.agregar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ceiba.induccion.excepciones.Conflicto;
import com.ceiba.induccion.modelos.VehiculoModelo;
import com.ceiba.induccion.servicios.ParqueaderoServicio;

@Component
public class DisponibilidadValidacion implements ReglaAgregarVehiculo {

	@Autowired
	private ParqueaderoServicio parqueaderoServicio;
	
	@Override
	public void validate(VehiculoModelo data) {
		
		if (!parqueaderoServicio.estaDisponible(data))
			throw new Conflicto("El parqueadero esta lleno");					
	}
}
