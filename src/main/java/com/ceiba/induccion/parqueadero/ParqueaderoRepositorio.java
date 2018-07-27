package com.ceiba.induccion.parqueadero;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ParqueaderoRepositorio extends MongoRepository<Parqueadero, String>{ 
	public Parqueadero findOneByNombre(String nombre);
}
