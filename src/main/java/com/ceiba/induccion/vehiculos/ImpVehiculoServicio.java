package com.ceiba.induccion.vehiculos;

public interface ImpVehiculoServicio {
			
	public void agregarVehiculo(VehiculoDTO vehiculo);
	public VehiculoDTO consultarExistencia(String placa);
}
