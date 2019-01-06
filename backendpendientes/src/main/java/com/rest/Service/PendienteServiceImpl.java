package com.rest.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.rest.Dao.IPendienteDao;
import com.rest.Entity.Pendiente;

public class PendienteServiceImpl  implements PendienteService{
    
	@Autowired
	private IPendienteDao ipendienteDao;
	
	public void setIPendienteDao(IPendienteDao ipendientedao) {
		this.ipendienteDao=ipendientedao;
	}
	
	
   	public Pendiente findById(long id) {
 		return ipendienteDao.findById(id);
	}
	
	 
	public Pendiente findByName(String nombre) {
 		return ipendienteDao.findByName(nombre);
	}
	
  	public void savePendiente(Pendiente pendiente) {
      ipendienteDao.savePendiente(pendiente);		
	}
	
	public Pendiente updatePendiente(Pendiente pendiente) {
	     return ipendienteDao.updatePendiente(pendiente);				
	}
	
	 
	public void deletePendienteById(long id) {
		ipendienteDao.deletePendienteById(id);		
	}
	 
	public List<Pendiente> findAllPendiente() {
 		return ipendienteDao.findAllPendiente();
	}

	 
	 

}
