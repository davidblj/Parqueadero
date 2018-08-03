package com.ceiba.induccion.utils.factura;

import java.util.Calendar;

import org.springframework.stereotype.Component;

@Component
public class FacturaMotoDTO	extends Factura {

	private int valorHora = 5000;
	private int valorDia = 4000;
	private int cilindraje;	
	
	@Override
	public void generar(Calendar fechaDeIngreso) {
		super.generar(fechaDeIngreso);
		
		this.precio = valorHora * horas + valorDia * dias;
		boolean esDeAltoCilindraje = cilindraje > 500;
		
		if (esDeAltoCilindraje) {
			this.precio += 2000;
		}
	}
	 
	public void setCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
	}
}
