package com.ceiba.induccion.vehiculos.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ceiba.induccion.utils.ApiBuilder;
import com.ceiba.induccion.utils.factura.Factura;
import com.ceiba.induccion.utils.factura.FacturaFactory;
import com.ceiba.induccion.vehiculos.VehiculoEntidad;
import com.ceiba.induccion.vehiculos.VehiculoModelo;
import com.ceiba.induccion.vehiculos.VehiculoRepositorio;
import com.ceiba.induccion.vehiculos.validaciones.eliminarVehiculo.ReglaEliminarVehiculo;
import com.ceiba.induccion.vehiculos.validaciones.eliminarVehiculo.ReglasEliminarVehiculo;


@Component
public class EliminarVehiculo {
	
	@Autowired
	private VehiculoRepositorio vehiculoRepositorio;
	
	@Autowired
	private ReglasEliminarVehiculo reglas;	
	
	@Autowired
	private ApiBuilder apiBuilder;
	
	@Autowired
	private FacturaFactory facturaFactory;

	// @Transactional
	public Factura ejecutar(String placa) {
				
		for (ReglaEliminarVehiculo rule: reglas.validacionesEliminarVehiculo()) {
			rule.validate(placa);
		}		
		
		/* VehiculoModelo vehiculo = encontrarVehiculo(placa);
		Factura factura = generarFactura(vehiculo);
		vehiculoRepositorio.updateFechaSalida(factura.getFechaDeSalida(), vehiculo.getPlaca());	*/	
		
		VehiculoEntidad vehiculoEntidad = vehiculoRepositorio.findByPlaca(placa);	
		VehiculoModelo vehiculoModelo = apiBuilder.vehiculoEntidadToVehiculo(vehiculoEntidad);
		
		Factura factura = facturaFactory.instanciarFactura(vehiculoModelo.getTipo());
		factura.generar(vehiculoModelo);
		
		vehiculoRepositorio.save(vehiculoEntidad);
		
		return factura;	
	}
	
	// utils
	
	private VehiculoModelo encontrarVehiculo(String placa) {	
		
		VehiculoEntidad vehiculoEntidad = vehiculoRepositorio.findByPlaca(placa);	
		return apiBuilder.vehiculoEntidadToVehiculo(vehiculoEntidad);
	}
	
	private Factura generarFactura(VehiculoModelo vehiculo) {
		
		Factura factura = facturaFactory.instanciarFactura(vehiculo.getTipo());
		factura.generar(vehiculo);
		return factura;
	}			
}
