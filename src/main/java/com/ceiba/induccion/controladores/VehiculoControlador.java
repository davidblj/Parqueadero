package com.ceiba.induccion.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.induccion.dto.VehiculoDTO;
import com.ceiba.induccion.dto.VehiculoIngresadoDTO;
import com.ceiba.induccion.servicios.VehiculoServicio;
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
