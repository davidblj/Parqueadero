package com.ceiba.induccion.repositorios;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ceiba.induccion.entidades.ParqueaderoEntidad;

public interface ParqueaderoRepositorio extends CrudRepository<ParqueaderoEntidad, Long> {
	public ParqueaderoEntidad findOneByNombre(String nombre);
	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE ParqueaderoEntidad p SET p.motos = :motos WHERE p.nombre = :nombre")
	public int updateLimiteMotos(@Param("motos") int motos, @Param("nombre") String nombre);
	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE ParqueaderoEntidad p SET p.carros = :carros WHERE p.nombre = :nombre")
	public int updateLimiteCarros(@Param("carros") int carros, @Param("nombre") String nombre);
}

