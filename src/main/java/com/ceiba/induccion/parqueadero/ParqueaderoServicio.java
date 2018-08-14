package com.ceiba.induccion.parqueadero;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ceiba.induccion.modelos.VehiculoModelo;
import com.ceiba.induccion.utils.Constants;

@Component
public class ParqueaderoServicio implements ImpParqueaderoServicio  {

	@Autowired
	private ParqueaderoRepositorio repositorio;
		
	@Override
	public boolean estaDisponible(VehiculoModelo vehiculo) {				
		
		ParqueaderoEntidad parqueadero = repositorio.findOneByNombre(Constants.PARQUEADERO_CEIBA);
		return parqueaderoEstaDisponible(parqueadero, vehiculo.getTipo());			
	}
		
	private boolean parqueaderoEstaDisponible(ParqueaderoEntidad parqueadero, String tipo) {	
		
		if (tipo.equals(Constants.VEHICULO_MOTO))
			return parqueadero.getMotos() < parqueadero.getLimiteMotos(); 
		
		if (tipo.equals(Constants.VEHICULO_CARRO))
			return parqueadero.getCarros() < parqueadero.getLimiteCarros(); 
		
		return false;		
	}
}
