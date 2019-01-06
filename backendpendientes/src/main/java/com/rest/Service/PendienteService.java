package com.rest.Service;

import java.util.List;

import com.rest.Entity.Pendiente;

public interface PendienteService {
	
	Pendiente findById(long id);
	 
	 Pendiente findByName(String  nombre);
	 
	 void savePendiente(Pendiente pendiente);
	 
	 Pendiente updatePendiente(Pendiente pendiente);

	 void deletePendienteById(long id);
	 
	 List<Pendiente> findAllPendiente();
	 
 }
