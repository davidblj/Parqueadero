package com.ceiba.induccion.vehiculos;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="vehiculo")
public class VehiculoEntidad {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar fechaDeIngreso;
	
	private String placa;
	private String tipo;
	private int cilindraje;	 
	
	// private Date fechaDeIngreso		

	public VehiculoEntidad() {}
	
	public VehiculoEntidad(String placa, String tipo, int cilindraje, Calendar fechaDeIngreso) { 
		this.placa = placa;
		this.tipo = tipo;
		this.cilindraje = cilindraje;
		this.fechaDeIngreso = fechaDeIngreso;
	}
	
	public Long getId() {
		return id;
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
	
	public int getCilindraje() {
		return cilindraje;
	}

	public void setCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
	}
}
