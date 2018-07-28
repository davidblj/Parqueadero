package com.ceiba.induccion.parqueadero;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ceiba.induccion.excepciones.ParametrosInvalidos;
import com.ceiba.induccion.utils.Constants;
import com.ceiba.induccion.vehiculos.VehiculoModelo;

@Component
public class ParqueaderoServicio implements ImpParqueaderoServicio  {

	@Autowired
	private ParqueaderoRepositorio repositorio;
	
	@Override
	public void agregarParqueadero(String nombre, int carros, int motos) 
			throws ParametrosInvalidos {
		
		// TODO: check syntax
		// TODO: check length (clean code)
		
		Parqueadero parqueadero = repositorio.findOneByNombre(nombre);
		boolean parqueaderoEnExistencia = parqueadero != null;
		
		if (!parqueaderoEnExistencia) {			
			parqueadero = new Parqueadero(nombre, carros, motos);		
			repositorio.save(parqueadero);
		} else {
			throw new ParametrosInvalidos("El parqueadero ya existe");
		}
	}

	@Override
	public boolean estaDisponible(VehiculoModelo vehiculo) {				
		
		Parqueadero parqueadero = repositorio.findOneByNombre(Constants.PARQUEADERO_CEIBA);
		return parqueaderoEstaDisponible(parqueadero, vehiculo.getTipo());			
	}
	
	
	private boolean parqueaderoEstaDisponible(Parqueadero parqueadero, String tipo) {
		
		// TODO: check types
		// TODO: simplify (factory ?)
		
		if (tipo.equals(Constants.VEHICULO_MOTO))
			return parqueadero.getMotos() < parqueadero.getLimiteMotos(); 
		
		if (tipo.equals(Constants.VEHICULO_CARRO))
			return parqueadero.getCarros() < parqueadero.getLimiteCarros(); 
		
		return false;		
	}
}
