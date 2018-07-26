package com.ceiba.induccion.vehiculos;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface VehiculoRepository extends MongoRepository<Vehiculo, String> {
	
}
