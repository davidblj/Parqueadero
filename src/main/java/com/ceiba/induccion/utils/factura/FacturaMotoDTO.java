package com.ceiba.induccion.utils.factura;

import java.util.Calendar;

import org.springframework.stereotype.Component;

import com.ceiba.induccion.vehiculos.VehiculoModelo;

@Component
public class FacturaMotoDTO	extends Factura {

	private int valorHora = 5000;
	private int valorDia = 4000;	
	
	@Override
	public void generar(VehiculoModelo vehiculo) {
		super.generar(vehiculo);
		
		this.precio = valorHora * horas + valorDia * dias;
		boolean esDeAltoCilindraje = vehiculo.getCilindraje() > 500;
		
		if (esDeAltoCilindraje) {
			this.precio += 2000;
		}
	}	 
}
