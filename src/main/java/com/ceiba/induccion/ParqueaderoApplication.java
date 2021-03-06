package com.ceiba.induccion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ceiba.induccion.entidades.ParqueaderoEntidad;
import com.ceiba.induccion.repositorios.ParqueaderoRepositorio;
import com.ceiba.induccion.repositorios.VehiculoRepositorio;
import com.ceiba.induccion.utils.Constants;

@SpringBootApplication
public class ParqueaderoApplication implements CommandLineRunner {		
	
	@Autowired
	private ParqueaderoRepositorio parqueaderoRepositorio;
	
	@Autowired
	private VehiculoRepositorio vehiculoRepositorio;

	public static void main(String[] args) {
		SpringApplication.run(ParqueaderoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {				
				
		parqueaderoRepositorio.deleteAll();
		vehiculoRepositorio.deleteAll();
		
		parqueaderoRepositorio.save(new ParqueaderoEntidad(
					Constants.PARQUEADERO_CEIBA,
					Constants.PARQUEADERO_CEIBA_LIMITE_CARROS,
					Constants.PARQUEADERO_CEIBA_LIMITE_MOTOS));			
	}	
}
