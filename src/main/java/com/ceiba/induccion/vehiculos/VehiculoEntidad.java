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

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar fechaDeSalida = null;			

	private String placa;
	private String tipo;
	private int cilindraje;
	
	public VehiculoEntidad() {}
	
	public VehiculoEntidad(String placa, String tipo, int cilindraje, Calendar fechaDeIngreso) { 
		this.placa = placa;
		this.tipo = tipo;
		this.cilindraje = cilindraje;
		this.fechaDeIngreso = fechaDeIngreso;		
	}
	
	public VehiculoEntidad(String placa, String tipo, int cilindraje, Calendar fechaDeIngreso, Calendar fechaDeSalida) {		
		this.placa = placa;
		this.tipo = tipo;
		this.cilindraje = cilindraje;
		this.fechaDeIngreso = fechaDeIngreso;
		this.fechaDeSalida = fechaDeSalida;	
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
	
	public Calendar getFechaDeIngreso() {
		return fechaDeIngreso;
	}
	
	public void setFechaDeIngreso(Calendar fechaDeIngreso) {
		this.fechaDeIngreso = fechaDeIngreso;
	}
	
	public Calendar getFechaDeSalida() {
		return fechaDeSalida;
	}
	
	public void setFechaDeSalida(Calendar fechaDeSalida) {
		this.fechaDeSalida = fechaDeSalida;
	}	
}
