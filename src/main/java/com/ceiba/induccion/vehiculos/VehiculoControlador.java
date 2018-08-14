package com.ceiba.induccion.vehiculos;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.induccion.utils.factura.Factura;

@RestController
@RequestMapping("/api/1.0/parqueadero/vehiculos")
@CrossOrigin(origins = "http://localhost:4200")
public class VehiculoControlador {		
	
	@Autowired
	VehiculoServicio servicio;
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public ResponseEntity<List<VehiculoIngresadoDTO>> listar() {	
		
		List<VehiculoIngresadoDTO> vehiculos = servicio.listar();
		return ResponseEntity.status(HttpStatus.OK).body(vehiculos);	
	}
	
	@RequestMapping(value="{placa}", method=RequestMethod.GET, produces="application/json")
	public ResponseEntity<VehiculoIngresadoDTO> consultar(@PathVariable String placa) {
		
		VehiculoIngresadoDTO vehiculo = servicio.consultar(placa);
		return ResponseEntity.status(HttpStatus.OK).body(vehiculo);
	}

	@RequestMapping(value="", method=RequestMethod.POST)
	public ResponseEntity<String> crear(@RequestBody VehiculoDTO vehiculo) {			
		
		servicio.agregar(vehiculo);		
		return ResponseEntity.status(HttpStatus.CREATED).body("Recurso creado exitosamente");		
	}			
	
	@RequestMapping(value="{placa}", method=RequestMethod.DELETE, produces="application/json")
	public ResponseEntity<Factura> eliminar(@PathVariable String placa) {
		
		Factura factura = servicio.eliminar(placa);
		return ResponseEntity.status(HttpStatus.OK).body(factura);			
	}	
}
