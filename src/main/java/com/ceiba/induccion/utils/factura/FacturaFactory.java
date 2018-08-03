package com.ceiba.induccion.utils.factura;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ceiba.induccion.vehiculos.VehiculoModelo;

@Component
public class FacturaFactory extends Factura {
	
	@Autowired
	FacturaMotoDTO facturaMoto;
	
	@Autowired
	FacturaCarroDTO facturaCarro;

	public Factura instanciarFactura(VehiculoModelo vehiculo) {
		
		if (vehiculo.esCarro()) {
			
			return facturaCarro;
		}
		
		if (vehiculo.esMoto()) {
						
			facturaMoto.setCilindraje(vehiculo.getCilindraje());
			return facturaMoto;
		}
		
		return null;
	}
}
