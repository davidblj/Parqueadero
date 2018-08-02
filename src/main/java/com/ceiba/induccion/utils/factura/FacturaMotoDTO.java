package com.ceiba.induccion.utils.factura;

import org.springframework.stereotype.Component;

@Component
public class FacturaMotoDTO	 extends Factura {

	private int valorHora;
	private int valorDia;
	
	public FacturaMotoDTO() {
		super();
	}
}
