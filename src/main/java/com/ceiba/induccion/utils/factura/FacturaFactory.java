package com.ceiba.induccion.utils.factura;

import org.springframework.stereotype.Component;

import com.ceiba.induccion.vehiculos.VehiculoModelo;

@Component
public class FacturaFactory {

	public Factura instanciarFactura(VehiculoModelo vehiculo) {
		
		if (vehiculo.esCarro()) {
			return new FacturaCarroDTO();
		}
		
		if (vehiculo.esMoto()) {
			return new FacturaMotoDTO();
		}
		
		return null;
	}
}
