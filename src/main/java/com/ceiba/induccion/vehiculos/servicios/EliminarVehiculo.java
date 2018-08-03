package com.ceiba.induccion.vehiculos.servicios;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ceiba.induccion.utils.ApiBuilder;
import com.ceiba.induccion.utils.Calendario;
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

	public Factura ejecutar(String placa) {
		
		// TODO: check clean code
		
		for (ReglaEliminarVehiculo rule: reglas.validacionesEliminarVehiculo()) {
			rule.validate(placa);
		}
		
		VehiculoEntidad vehiculoEntidad = vehiculoRepositorio.findByPlaca(placa);	
		VehiculoModelo vehiculo = apiBuilder.vehiculoEntidadToVehiculo(vehiculoEntidad);
		
		Factura factura = facturaFactory.instanciarFactura(vehiculo);
		factura.generar(vehiculo.getFechaDeIngreso());
		
		vehiculoEntidad.setFechaSalida(factura.getFechaSalida());
		vehiculoRepositorio.save(vehiculoEntidad);
		return factura;
		
		// vehiculoRepositorio.updateFechaSalida(factura.getFechaSalida(), vehiculo.getPlaca());				
	}
}
