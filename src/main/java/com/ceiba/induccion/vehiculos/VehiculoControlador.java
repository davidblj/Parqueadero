package com.ceiba.induccion.vehiculos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/1.0/parqueadero/vehiculos")
@CrossOrigin
public class VehiculoControlador {
	
	@Autowired
	VehiculoServicio servicio;

	@RequestMapping(value="", method=RequestMethod.POST, produces="application/json" )
	public ResponseEntity<VehiculoDTO> crear(@RequestBody VehiculoDTO vehiculo) {
		servicio.agregarVehiculo(vehiculo);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
