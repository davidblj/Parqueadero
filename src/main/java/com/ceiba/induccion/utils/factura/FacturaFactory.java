package com.ceiba.induccion.utils.factura;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ceiba.induccion.utils.Constants;
import com.ceiba.induccion.vehiculos.VehiculoModelo;

@Component
public class FacturaFactory extends Factura {
	
	@Autowired
	FacturaMotoDTO facturaMoto;
	
	@Autowired
	FacturaCarroDTO facturaCarro;

	public Factura instanciarFactura(String tipo) {
		
		if (tipo.equals(Constants.VEHICULO_CARRO)) {
			
			return facturaCarro;
		}
		
		if (tipo.equals(Constants.VEHICULO_MOTO)) {
									
			return facturaMoto;
		}
		
		// TODO: throw exception
		
		return null;
	}
}
