package com.ceiba.induccion.utils.factura;

import java.util.Calendar;

import org.springframework.stereotype.Component;

@Component
public class FacturaCarroDTO extends Factura {
	
	private int valorHora = 1000;
	private int valorDia = 8000;		
	
	@Override
	public void generar(Calendar fechaDeIngreso) {
		super.generar(fechaDeIngreso);		
		this.precio = (valorHora * horas) + (valorDia * dias);
	}
}
