package com.ceiba.induccion.vehiculos;

public class VehiculoDTO {

	private String placa;
	
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
}
