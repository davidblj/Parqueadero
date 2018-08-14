package com.ceiba.induccion.servicios;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ceiba.induccion.dto.VehiculoDTO;
import com.ceiba.induccion.dto.VehiculoIngresadoDTO;
import com.ceiba.induccion.entidades.VehiculoEntidad;
import com.ceiba.induccion.repositorios.VehiculoRepositorio;
import com.ceiba.induccion.utils.ApiBuilder;
import com.ceiba.induccion.utils.Vigilante;
import com.ceiba.induccion.utils.factura.Factura;

@Component
public class VehiculoServicio implements ImpVehiculoServicio {

	@Autowired
	private VehiculoRepositorio vehiculoRepositorio;		
	
	@Autowired
	private ApiBuilder apiBuilder;		
	
	@Autowired
	private Vigilante vigilante;
	
		
	public void agregar(VehiculoDTO vehiculoDTO) {		
		vigilante.registrarIngreso(vehiculoDTO);
	}
	
	public Factura eliminar(String placa) {		
		return vigilante.registrarSalida(placa);
	}
	
	public List<VehiculoIngresadoDTO> listar() {
		
		List<VehiculoEntidad> vehiculos = vehiculoRepositorio.findByFechaDeSalidaIsNull();
		return apiBuilder.listaVehiculoEntidadToVehiculoDTO(vehiculos);
	}
	
	public VehiculoIngresadoDTO consultar(String placa) {
		
		VehiculoEntidad vehiculo = vehiculoRepositorio.findByPlaca(placa);
		return apiBuilder.vehiculoEntidadToVehiculoIngresadoDTO(vehiculo);
	}		
}
