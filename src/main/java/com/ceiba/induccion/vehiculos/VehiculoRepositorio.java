package com.ceiba.induccion.vehiculos;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

//public interface VehiculoRepositorio extends MongoRepository<VehiculoEntidad, String> {
//	public VehiculoEntidad findByPlaca(String placa);
//}

public interface VehiculoRepositorio extends CrudRepository<VehiculoEntidad, Long> {
	public VehiculoEntidad findByPlaca(String placa);
}
