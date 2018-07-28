package com.ceiba.induccion.parqueadero;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ParqueaderoRepositorio extends MongoRepository<ParqueaderoEntidad, String>{ 
	public ParqueaderoEntidad findOneByNombre(String nombre);
}
