package com.ceiba.induccion.vehiculos;

import java.util.List;
import java.util.logging.Level;
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

import com.ceiba.induccion.utils.excepciones.Conflicto;
import com.ceiba.induccion.utils.excepciones.ParametrosInvalidos;
import com.ceiba.induccion.utils.factura.Factura;

@RestController
@RequestMapping("/api/1.0/parqueadero/vehiculos")
@CrossOrigin
public class VehiculoControlador {		
	
	private static final Logger LOG = Logger.getLogger(VehiculoControlador.class.getName());	 	
	
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
		
		try {			
			servicio.agregar(vehiculo);
			return ResponseEntity.status(HttpStatus.CREATED).body("recurso creado");	
			
		} catch (ParametrosInvalidos e) {
			
			LOG.log(Level.WARNING, e.toString(), e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());	
			
		} catch (Conflicto e) {
			
			LOG.log(Level.WARNING, e.toString(), e);
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}			
	
	@RequestMapping(value="{placa}", method=RequestMethod.DELETE, produces="application/json")
	public ResponseEntity<Object> eliminar(@PathVariable String placa) {
		
		try {
			Factura factura = servicio.eliminar(placa);
			return ResponseEntity.status(HttpStatus.OK).body(factura);
			
		} catch (ParametrosInvalidos e) {
			
			LOG.log(Level.WARNING, e.toString(), e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}		
	}	
}
