package com.ceiba.induccion.parqueadero;

import org.springframework.data.annotation.Id;

public class Parqueadero {

	@Id
    private String id;	
	
	private String nombre;
	private int carros = 0;	
	private int motos = 0;
	private int limiteCarros;
	private int limiteMotos;
	
	public Parqueadero() {}
	
	public Parqueadero(String nombre, int limiteCarros, int limiteMotos) {
		this.nombre = nombre;
		this.limiteCarros = limiteCarros;
		this.limiteMotos = limiteMotos;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}	
			
	public int getCarros() {
		return carros;
	}
	
	public void setCarros(int carros) {
		this.carros = carros;
	}
	
	public int getMotos() {
		return motos;
	}
	
	public void setMotos(int motos) {
		this.motos = motos;
	}

	public int getLimiteCarros() {
		return limiteCarros;
	}

	public void setLimiteCarros(int limiteCarros) {
		this.limiteCarros = limiteCarros;
	}

	public int getLimiteMotos() {
		return limiteMotos;
	}

	public void setLimiteMotos(int limiteMotos) {
		this.limiteMotos = limiteMotos;
	}
	
	// utils
	
}
