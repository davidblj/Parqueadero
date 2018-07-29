package com.ceiba.induccion.vehiculos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.mongodb.core.mapping.Document;

// @Document(collection="vehiculo")
@Entity
@Table(name="vehiculo")
public class VehiculoEntidad {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	
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
