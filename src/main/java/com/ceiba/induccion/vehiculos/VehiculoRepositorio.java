package com.ceiba.induccion.vehiculos;

import java.util.Calendar;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface VehiculoRepositorio extends CrudRepository<VehiculoEntidad, Long> {
	public VehiculoEntidad findByPlaca(String placa);
	
	@Modifying
	@Query("UPDATE VehiculoEntidad v SET v.fechaDeSalida = :fechaDeSalida WHERE v.placa = :placa")
	public int updateFechaSalida(@Param("fechaDeSalida") Calendar fechaDeSalida, @Param("placa") String placa);	
}
