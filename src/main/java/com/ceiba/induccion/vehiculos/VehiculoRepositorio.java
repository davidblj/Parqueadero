package com.ceiba.induccion.vehiculos;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface VehiculoRepositorio extends MongoRepository<Vehiculo, String> {
	public Vehiculo findByPlaca(String placa);
}
