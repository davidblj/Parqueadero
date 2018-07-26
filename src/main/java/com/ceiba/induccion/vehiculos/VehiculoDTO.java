package com.ceiba.induccion.vehiculos;

public class VehiculoDTO {

	private String placa;
	private String tipo;
	
	public VehiculoDTO() {}	

	public VehiculoDTO(String placa) {
		super();
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

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
