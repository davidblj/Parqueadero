package com.ceiba.induccion.vehiculos;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="vehiculo")
public class VehiculoEntidad {

	@Id
    private String id;	
	
	private String placa;
	private String tipo;
	
	public VehiculoEntidad() {}
	
	public VehiculoEntidad(String placa, String tipo) { 
		this.placa = placa;
		this.tipo = tipo;
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
		this.tipo = tipo;
	}
			
}
