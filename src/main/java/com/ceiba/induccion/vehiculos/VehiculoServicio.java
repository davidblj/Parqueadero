package com.ceiba.induccion.vehiculos;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ceiba.induccion.excepciones.ErrorInterno;
import com.ceiba.induccion.excepciones.ParametrosInvalidos;
import com.ceiba.induccion.parqueadero.ParqueaderoServicio;
import com.ceiba.induccion.utils.ApiDTOBuilder;
import com.ceiba.induccion.utils.Constants;

@Component
public class VehiculoServicio implements ImpVehiculoServicio {
	
	@Autowired
	private VehiculoRepositorio vehiculoRepositorio;
	
	@Autowired
	private ParqueaderoServicio parqueaderoServicio;

	@Override
	public void agregarVehiculo(VehiculoDTO vehiculoDTO) throws ParametrosInvalidos {
		
		Vehiculo vehiculo = ApiDTOBuilder.VehiculoDTOToVehiculo(vehiculoDTO);
		
		// TODO: check syntax
		// TODO: check existence
		String tipo = vehiculo.getTipo();
		String placa = vehiculo.getPlaca();
		
		if (!tipoEsValido(tipo)) 
			throw new ParametrosInvalidos("El tipo del vehiculo deberia ser CARRO o MOTO");
		
		if (!parqueaderoServicio.estaDisponible(vehiculo))
			throw new ErrorInterno("El parqueadero esta lleno");
		
		if (!placaEsValida(placa)) {
			
		}
				
		vehiculoRepositorio.save(vehiculo);
		
		// TODO: modify parking lot
	}
	
	private boolean tipoEsValido(String tipo) {
		return 	tipo.equals(Constants.VEHICULO_CARRO) || 
				tipo.equals(Constants.VEHICULO_MOTO);
	}
	
	private boolean placaEsValida(String placa) {				
		char primeraLetra = placa.charAt(0);
		boolean esLetra_A = primeraLetra == 'A';
		return esLetra_A ? diaEsValido() : false;		
	}
	
	private boolean diaEsValido() {
		Calendar calendar = Calendar.getInstance();
		int day = calendar.get(Calendar.DAY_OF_WEEK);
		boolean esUnDiaHabil = 	day == Calendar.MONDAY || 
								day == Calendar.SUNDAY ;
		return esUnDiaHabil;
	}
}
