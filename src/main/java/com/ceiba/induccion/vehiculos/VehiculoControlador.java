package com.ceiba.induccion.vehiculos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.induccion.excepciones.Conflicto;
import com.ceiba.induccion.excepciones.ErrorInternoDelServidor;
import com.ceiba.induccion.excepciones.ParametrosInvalidos;

@RestController
@RequestMapping("/api/1.0/parqueadero/vehiculos")
@CrossOrigin
public class VehiculoControlador {
	
	@Autowired
	VehiculoServicio servicio;

	@RequestMapping(value="", method=RequestMethod.POST)
	public ResponseEntity<?> crear(@RequestBody VehiculoDTO vehiculo) {	
		
		try {			
			servicio.agregarVehiculo(vehiculo);
			return ResponseEntity.accepted().body("");	
			
		} catch (ParametrosInvalidos e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());	
			
		} catch (Conflicto e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}
}
