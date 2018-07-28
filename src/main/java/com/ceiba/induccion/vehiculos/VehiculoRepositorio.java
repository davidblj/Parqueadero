package com.ceiba.induccion.vehiculos;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface VehiculoRepositorio extends MongoRepository<VehiculoEntidad, String> {
	public VehiculoEntidad findByPlaca(String placa);
}
