package com.ceiba.induccion.parqueadero;

import com.ceiba.induccion.vehiculos.VehiculoModelo;

public interface ImpParqueaderoServicio {
	
	public boolean estaDisponible(VehiculoModelo vehiculo);
}
