package com.ceiba.induccion.vehiculos;

import org.springframework.data.repository.CrudRepository;

public interface VehiculoRepositorio extends CrudRepository<VehiculoEntidad, Long> {
	public VehiculoEntidad findByPlaca(String placa);
}
