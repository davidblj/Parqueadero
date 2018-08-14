package com.ceiba.induccion.utils.factura;

import org.springframework.stereotype.Component;

import com.ceiba.induccion.modelos.VehiculoModelo;

@Component
public class FacturaCarro extends Factura {
	
	private int valorHora = 1000;
	private int valorDia = 8000;		
	
	@Override
	public void generar(VehiculoModelo vehiculo) {
		super.generar(vehiculo);		
		this.precioFactura = (valorHora * horas) + (valorDia * dias);
	}
}
