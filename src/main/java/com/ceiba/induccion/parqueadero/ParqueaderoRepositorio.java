package com.ceiba.induccion.parqueadero;

import org.springframework.data.repository.CrudRepository;

public interface ParqueaderoRepositorio extends CrudRepository<ParqueaderoEntidad, Long> {
	public ParqueaderoEntidad findOneByNombre(String nombre);
}

