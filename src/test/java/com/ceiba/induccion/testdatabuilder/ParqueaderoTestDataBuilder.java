package com.ceiba.induccion.testdatabuilder;

import com.ceiba.induccion.modelos.ParqueaderoModelo;
import com.ceiba.induccion.utils.Constants;

public class ParqueaderoTestDataBuilder {
	
	private static final String NOMBRE = Constants.PARQUEADERO_CEIBA;	
	private static final int LIMITE_CARROS = Constants.PARQUEADERO_CEIBA_LIMITE_CARROS;
	private static final int LIMITE_MOTOS = Constants.PARQUEADERO_CEIBA_LIMITE_MOTOS;
	
	private String nombre;
	private int limite_carros;
	private int limite_motos;
	
	public ParqueaderoTestDataBuilder() {		
		this.nombre = NOMBRE;
		this.limite_carros = LIMITE_CARROS;
		this.limite_motos = LIMITE_MOTOS;
	}
	
	public ParqueaderoTestDataBuilder conLimiteDeCarros(int limite_carros) {
		this.limite_carros = limite_carros;
		return this;
	}
	
	public ParqueaderoTestDataBuilder conLimiteDeMotos(int limite_motos) {
		this.limite_motos = limite_motos;
		return this;
	}
	
	public ParqueaderoModelo build() {
		return new ParqueaderoModelo(nombre, limite_carros, limite_motos);
	}	
}
