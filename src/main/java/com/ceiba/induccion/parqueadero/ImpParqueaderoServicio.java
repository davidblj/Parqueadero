package com.ceiba.induccion.parqueadero;

import com.ceiba.induccion.vehiculos.Vehiculo;

public interface ImpParqueaderoServicio {
	
	public void agregarParqueadero(String nombre, int carros, int motos);
	public boolean estaDisponible(Vehiculo vehiculo);
}
