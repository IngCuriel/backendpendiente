package com.rest.Controller;

 import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
 import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.rest.Entity.Pendiente;
import com.rest.Service.PendienteService;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
public class PendienteController {
 //ejemplo
	
	@Autowired
	private PendienteService pendienteService;
	
	public void setPendienteService(PendienteService pendienteService) {
		this.pendienteService=pendienteService;
	}
	@RequestMapping(value="/pendiente/new",method=RequestMethod.POST)
	public ResponseEntity<?> addPendiente(@RequestBody Pendiente pendiente,UriComponentsBuilder ucb){
 	    LinkedHashMap<String, Object> response = new LinkedHashMap<String, Object>();
		try {
			pendienteService.savePendiente(pendiente);

		}
		 catch(DataAccessException e) {
         	response.put("mensaje", "error al realizar insert a la base de datos");
         	response.put("error",e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<LinkedHashMap<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);

         }
		response.put("mensaje", "Pendiente creado exitosamente!");
     	response.put("pendiente",pendiente);
  	return new ResponseEntity<LinkedHashMap<String, Object>>(response,HttpStatus.CREATED);

	}
	// Get All Pendientes
	@RequestMapping(value = "/pendiente", method = RequestMethod.GET)
	public ResponseEntity<List<Pendiente>> listAllCustomers() {
		List<Pendiente> pendientes = pendienteService.findAllPendiente();
		if (pendientes.isEmpty()) {
			return new ResponseEntity<List<Pendiente>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Pendiente>>(pendientes, HttpStatus.OK);
	}
	
	// Get pendiente
		@RequestMapping(value = "/pendiente/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<?> getPendiente(@PathVariable("id") int id) {
			Pendiente pendiente=null;
		    LinkedHashMap<String, Object> response = new LinkedHashMap<String, Object>();
			try {
				pendiente = pendienteService.findById(id);
            }
            catch(DataAccessException e) {
            	response.put("mensaje", "error al consultar a la base de datos");
            	response.put("error",e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<LinkedHashMap<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);

            }
 			if (pendiente == null) {
            	response.put("mensaje", "No se encontro ningun pendiente con el id:"+id);
				return new ResponseEntity<LinkedHashMap<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<Pendiente>(pendiente, HttpStatus.OK);
		}
		
		// Update Pendiente
		@RequestMapping(value = "/pendiente/{id}", method = RequestMethod.PUT)
		public ResponseEntity<?> updatePendiente(@PathVariable("id") int id, @RequestBody Pendiente cus) {

			Pendiente pendiente = pendienteService.findById(id);
			Pendiente pendienteActualizado=null;
		    LinkedHashMap<String, Object> response = new LinkedHashMap<String, Object>();

			if (pendiente == null) {
            	response.put("mensaje", "Error,No existe pendiente con id:"+id);
				return new ResponseEntity<LinkedHashMap<String, Object>>(response,HttpStatus.NOT_FOUND);
 			}
            try {
    			pendiente.setNombre(cus.getNombre()); 
    			pendiente.setDescripcion(cus.getDescripcion());  
    			pendienteActualizado=pendienteService.updatePendiente(pendiente);	
            }
            catch(DataAccessException e) {
            	response.put("mensaje", "Error al actualizar en la base de datos");
            	response.put("error",e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<LinkedHashMap<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);

            }
            response.put("mensaje", "Pendiente Actualizado Exitosamente");
        	response.put("pendiente",pendienteActualizado);
			return new ResponseEntity<LinkedHashMap<String, Object>>(response, HttpStatus.OK);
		}
		// Delete Pendiente
		@RequestMapping(value = "/pendiente/{id}", method = RequestMethod.DELETE)
		public ResponseEntity<?> deletePendiente(@PathVariable("id") long id) {
		    LinkedHashMap<String, Object> response = new LinkedHashMap<String, Object>();

			Pendiente pendiente = pendienteService.findById(id);
			if (pendiente == null) {
            	response.put("mensaje", "Error,No existe pendiente con id:"+id);
				return new ResponseEntity<LinkedHashMap<String, Object>>(response,HttpStatus.NOT_FOUND);
 			}
            try {
			pendienteService.deletePendienteById(id);
            }
            catch(DataAccessException e) {
            	response.put("mensaje", "Error al eliminar en la base de datos");
            	response.put("error",e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<LinkedHashMap<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);

            }
			response.put("mensaje", "Pendiente Eliminado Exitosamente");
	        response.put("pendiente",pendiente);
			return new ResponseEntity<LinkedHashMap<String, Object>>(response,HttpStatus.OK);
		}
}
