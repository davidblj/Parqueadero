package com.ceiba.induccion.utils.factura;

import org.springframework.stereotype.Component;

import com.ceiba.induccion.vehiculos.VehiculoModelo;

@Component
public class FacturaMoto	extends Factura {

	private int valorHora = 500;
	private int valorDia = 4000;	
	
	@Override
	public void generar(VehiculoModelo vehiculo) {
		super.generar(vehiculo);
		
		this.precioFactura = valorHora * horas + valorDia * dias;
		boolean esDeAltoCilindraje = vehiculo.getCilindraje() > 500;
		
		if (esDeAltoCilindraje) {
			this.precioFactura += 2000;
		}
	}	 
}
