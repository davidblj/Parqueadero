package com.ceiba.induccion.utils.factura;

import org.springframework.stereotype.Component;

@Component
public class FacturaCarroDTO extends Factura {
	
	private int valorHora;
	private int valorDia;
	
	public FacturaCarroDTO() {
		super();
	}
}
