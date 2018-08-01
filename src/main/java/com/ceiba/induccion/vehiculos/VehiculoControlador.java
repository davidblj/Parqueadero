package com.ceiba.induccion.vehiculos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.induccion.excepciones.Conflicto;
import com.ceiba.induccion.excepciones.ErrorInternoDelServidor;
import com.ceiba.induccion.excepciones.ParametrosInvalidos;
import com.ceiba.induccion.vehiculos.servicios.AgregarVehiculo;
import com.ceiba.induccion.vehiculos.servicios.EliminarVehiculo;
import com.ceiba.induccion.vehiculos.servicios.ObtenerVehiculo;

@RestController
@RequestMapping("/api/1.0/parqueadero/vehiculos")
@CrossOrigin
public class VehiculoControlador {	
	
	@Autowired
	AgregarVehiculo agregarVehiculo;
	
	@Autowired
	ObtenerVehiculo obtenerVehiculo;
	
	@Autowired
	EliminarVehiculo eliminarVehiculo;

	@RequestMapping(value="", method=RequestMethod.POST)
	public ResponseEntity<?> crear(@RequestBody VehiculoDTO vehiculo) {	
		
		try {			
			agregarVehiculo.ejecutar(vehiculo);
			return ResponseEntity.accepted().body("");	
			
		} catch (ParametrosInvalidos e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());	
			
		} catch (Conflicto e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}
	
	@RequestMapping(value="{placa}", method=RequestMethod.GET, produces="application/json")
	public ResponseEntity<?> consultar(@PathVariable String placa) {
		
		VehiculoDTO vehiculo = obtenerVehiculo.ejecutar(placa);
		return ResponseEntity.status(HttpStatus.OK).body(vehiculo);
	}	
	
	@RequestMapping(value="{placa}", method=RequestMethod.DELETE, produces="application/json")
	public ResponseEntity<?> eliminar(@PathVariable String placa) {
		
		FacturaDTO factura = eliminarVehiculo.ejecutar(placa);
		return ResponseEntity.status(HttpStatus.OK).body(factura);
	}
}
