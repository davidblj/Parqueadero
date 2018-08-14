package com.ceiba.induccion.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ceiba.induccion.dto.VehiculoDTO;
import com.ceiba.induccion.entidades.ParqueaderoEntidad;
import com.ceiba.induccion.entidades.VehiculoEntidad;
import com.ceiba.induccion.modelos.VehiculoModelo;
import com.ceiba.induccion.repositorios.ParqueaderoRepositorio;
import com.ceiba.induccion.repositorios.VehiculoRepositorio;
import com.ceiba.induccion.utils.factura.Factura;
import com.ceiba.induccion.utils.factura.FacturaFactory;
import com.ceiba.induccion.utils.validaciones.agregar.ReglaAgregarVehiculo;
import com.ceiba.induccion.utils.validaciones.agregar.ReglasAgregarVehiculo;
import com.ceiba.induccion.utils.validaciones.eliminar.ReglaEliminarVehiculo;
import com.ceiba.induccion.utils.validaciones.eliminar.ReglasEliminarVehiculo;

@Component
public class Vigilante {
	
	@Autowired
	private VehiculoRepositorio vehiculoRepositorio;
	
	@Autowired
	private ParqueaderoRepositorio parqueaderoRepositorio;

	@Autowired
	private ApiBuilder apiBuilder;
	
	@Autowired
	private ReglasAgregarVehiculo reglasAgregarVehiculo;
	
	@Autowired
	private Calendario calendario;
	
	@Autowired
	private FacturaFactory facturaFactory;
	
	@Autowired
	private ReglasEliminarVehiculo reglasEliminarVehiculo;
	
	
	public void registrarIngreso(VehiculoDTO vehiculoDTO) {
		
		VehiculoModelo vehiculo = apiBuilder.vehiculoDTOToVehiculoModelo(vehiculoDTO);			
		
		for (ReglaAgregarVehiculo rule: reglasAgregarVehiculo.validacionesAgregarVehiculo()) {
			rule.validate(vehiculo);
		}
		
		agregarFechaDeIngreso(vehiculo);
		ocuparCeldaSegunVehiculo(vehiculo);
		vehiculoRepositorio.save(apiBuilder.vehiculoModeloToVehiculoEntidad(vehiculo));
	}
	
	@Transactional
	public Factura registrarSalida(String placa) {
		
		for (ReglaEliminarVehiculo rule: reglasEliminarVehiculo.validacionesEliminarVehiculo()) {
			rule.validate(placa);
		}		
		
		VehiculoModelo vehiculo = encontrarVehiculo(placa);
		Factura factura = generarFactura(vehiculo);
		vehiculoRepositorio.updateFechaSalida(factura.getFechaDeSalida(), vehiculo.getPlaca());					
		
		return factura;
	}
		
	// utilities 
	
	private void agregarFechaDeIngreso(VehiculoModelo vehiculo) {
		
		vehiculo.setFechaDeIngreso(calendario.obtenerFechaActual());
	}
	
	private void ocuparCeldaSegunVehiculo(VehiculoModelo vehiculo) {		
				
		ParqueaderoEntidad parqueadero = parqueaderoRepositorio.findOneByNombre(Constants.PARQUEADERO_CEIBA);	
		modificarParqueaderoSegunVehiculo(parqueadero, vehiculo);		
	}
	
	private void modificarParqueaderoSegunVehiculo(ParqueaderoEntidad parqueadero, VehiculoModelo vehiculo) {
		
		if (vehiculo.esCarro())
			parqueadero.setCarros(parqueadero.getCarros() + 1);
		
		if (vehiculo.esMoto())
			parqueadero.setMotos(parqueadero.getMotos() + 1);
		
		parqueaderoRepositorio.save(parqueadero);
	}
	
	private VehiculoModelo encontrarVehiculo(String placa) {	
		
		VehiculoEntidad vehiculoEntidad = vehiculoRepositorio.findByPlaca(placa);	
		return apiBuilder.vehiculoEntidadToVehiculoModelo(vehiculoEntidad);
	}
	
	private Factura generarFactura(VehiculoModelo vehiculo) {
		
		Factura factura = facturaFactory.instanciarFactura(vehiculo.getTipo());
		factura.generar(vehiculo);
		return factura;
	}
}
