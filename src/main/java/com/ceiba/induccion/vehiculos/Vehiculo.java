package com.ceiba.induccion.vehiculos;

import org.springframework.data.annotation.Id;

public class Vehiculo {

	@Id
    private String id;	
	
	private String placa;
	private String tipo;
	
	public Vehiculo() {}
	
	public Vehiculo(String placa) { 
		this.placa = placa;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}	
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) throws Exception {
		
		boolean tipoEsValido = tipo.equals("CARRO") || tipo.equals("MOTO");
		
		if (tipoEsValido) {
			this.tipo = tipo;
		} else {
			throw new Exception("tipo invalido");
		}		
	}

}
