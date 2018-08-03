package com.ceiba.induccion.vehiculos;

import java.util.Calendar;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface VehiculoRepositorio extends CrudRepository<VehiculoEntidad, Long> {
	public VehiculoEntidad findByPlaca(String placa);
	
	//	@Modifying
	//	@Query("update vehiculo v set v.fechaSalida = ?1 where v.placa = ?2")
	//	int updateFechaSalida(Calendar fechaSalida, String placa);
}
