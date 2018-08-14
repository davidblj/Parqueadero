package com.ceiba.induccion.parqueadero;

import com.ceiba.induccion.modelos.VehiculoModelo;

public interface ImpParqueaderoServicio {
	
	public boolean estaDisponible(VehiculoModelo vehiculo);
}
