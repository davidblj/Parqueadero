package com.ceiba.induccion.vehiculos;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ceiba.induccion.parqueadero.ParqueaderoEntidad;
import com.ceiba.induccion.parqueadero.ParqueaderoRepositorio;
import com.ceiba.induccion.utils.ApiBuilder;
import com.ceiba.induccion.utils.Calendario;
import com.ceiba.induccion.utils.Constants;
import com.ceiba.induccion.utils.factura.Factura;
import com.ceiba.induccion.utils.factura.FacturaFactory;
import com.ceiba.induccion.vehiculos.validaciones.agregar.ReglaAgregarVehiculo;
import com.ceiba.induccion.vehiculos.validaciones.agregar.ReglasAgregarVehiculo;
import com.ceiba.induccion.vehiculos.validaciones.eliminar.ReglaEliminarVehiculo;
import com.ceiba.induccion.vehiculos.validaciones.eliminar.ReglasEliminarVehiculo;

@Component
public class VehiculoServicio {

	@Autowired
	private VehiculoRepositorio vehiculoRepositorio;
	
	@Autowired
	private ParqueaderoRepositorio parqueaderoRepositorio;
	
	@Autowired
	private FacturaFactory facturaFactory;
	
	@Autowired
	private ApiBuilder apiBuilder;
	
	@Autowired
	private Calendario calendario;
	
	@Autowired
	private ReglasAgregarVehiculo reglasAgregarVehiculo;
	
	@Autowired
	private ReglasEliminarVehiculo reglasEliminarVehiculo;
	
		
	public void agregar(VehiculoDTO vehiculoDTO) {
		
		VehiculoModelo vehiculo = apiBuilder.vehiculoDTOToVehiculoModelo(vehiculoDTO);			
		
		for (ReglaAgregarVehiculo rule: reglasAgregarVehiculo.validacionesAgregarVehiculo()) {
			rule.validate(vehiculo);
		}
		
		agregarFechaDeIngreso(vehiculo);
		ocuparCeldaSegunVehiculo(vehiculo);
		vehiculoRepositorio.save(apiBuilder.vehiculoModeloToVehiculoEntidad(vehiculo));
	}
	
	@Transactional
	public Factura eliminar(String placa) {
		
		for (ReglaEliminarVehiculo rule: reglasEliminarVehiculo.validacionesEliminarVehiculo()) {
			rule.validate(placa);
		}		
		
		VehiculoModelo vehiculo = encontrarVehiculo(placa);
		Factura factura = generarFactura(vehiculo);
		vehiculoRepositorio.updateFechaSalida(factura.getFechaDeSalida(), vehiculo.getPlaca());					
		
		return factura;
	}
	
	public List<VehiculoIngresadoDTO> listar() {
		
		List<VehiculoEntidad> vehiculos = vehiculoRepositorio.findByFechaDeSalidaIsNull();
		return conversor(vehiculos);
	}
	
	public VehiculoIngresadoDTO consultar(String placa) {
		
		VehiculoEntidad vehiculo = vehiculoRepositorio.findByPlaca(placa);
		return apiBuilder.vehiculoEntidadToVehiculoIngresadoDT(vehiculo);
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
	
	private List<VehiculoIngresadoDTO> conversor(List<VehiculoEntidad> vehiculos) {
		
		List<VehiculoIngresadoDTO> listaDeVehiculos = new ArrayList<>();
		
		for (VehiculoEntidad vehiculoEntidad: vehiculos) {
			VehiculoIngresadoDTO vehiculoModelo = apiBuilder.vehiculoEntidadToVehiculoIngresadoDT(vehiculoEntidad);
			listaDeVehiculos.add(vehiculoModelo);
		}
		
		return listaDeVehiculos;
	}
}
