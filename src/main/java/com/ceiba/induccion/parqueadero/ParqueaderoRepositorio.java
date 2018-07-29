package com.ceiba.induccion.parqueadero;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

//public interface ParqueaderoRepositorio extends MongoRepository<ParqueaderoEntidad, String>{ 
//	public ParqueaderoEntidad findOneByNombre(String nombre);
//}

public interface ParqueaderoRepositorio extends CrudRepository<ParqueaderoEntidad, Long> {
	public ParqueaderoEntidad findOneByNombre(String nombre);
}

