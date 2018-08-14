package com.ceiba.induccion.servicios;

import java.util.List;

import com.ceiba.induccion.dto.VehiculoDTO;
import com.ceiba.induccion.dto.VehiculoIngresadoDTO;
import com.ceiba.induccion.utils.factura.Factura;

public interface ImpVehiculoServicio {
	
	public void agregar(VehiculoDTO vehiculoDTO);
	
	public Factura eliminar(String placa);
	
	public List<VehiculoIngresadoDTO> listar();
	
	public VehiculoIngresadoDTO consultar(String placa);
}
