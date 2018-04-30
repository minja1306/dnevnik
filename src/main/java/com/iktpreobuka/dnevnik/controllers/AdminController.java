package com.iktpreobuka.dnevnik.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.dnevnik.controllers.util.RESTError;
import com.iktpreobuka.dnevnik.entities.AdminEntity;
import com.iktpreobuka.dnevnik.entities.dto.AdminDTO;
import com.iktpreobuka.dnevnik.repositories.AdminRepository;
import com.iktpreobuka.dnevnik.security.Views;

@RestController
@RequestMapping(path = "/api/v1/administrator")

public class AdminController {
	
	@Autowired

	private AdminRepository adminRepository;
	

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> createAdmin(@RequestBody AdminDTO administrator) {
	       AdminEntity admin = new AdminEntity();
			
	        admin.setIme(administrator.getIme());
			admin.setPrezime(administrator.getPrezime());
			admin.setJmbg(administrator.getJmbg());
			
			
			adminRepository.save(admin);

			return new ResponseEntity<AdminEntity>(admin, HttpStatus.OK);
		}

	@RequestMapping(method = RequestMethod.GET)
	@JsonView(Views.Public.class)
	@CrossOrigin(origins = "http://localhost:4200")
	public Iterable<AdminEntity> getAllAdmin() {
		return adminRepository.findAll();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")

	public ResponseEntity<?> getAdminByID(@PathVariable Integer id) {
		try {
			Iterable<AdminEntity> administrator = getAllAdmin();
			for (AdminEntity adminEntity : administrator) {
				if (adminEntity.getId().equals(id)) {

					return new ResponseEntity<AdminEntity>(adminEntity, HttpStatus.OK);
				}
			}

			return new ResponseEntity<RESTError>(new RESTError(1, "Administrator nije nadjen"), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Exception occurred: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	 @RequestMapping(method = RequestMethod.GET, value = "/jmbg/{jmbg}")
		
		@CrossOrigin(origins = "http://localhost:4200")
		public ResponseEntity<?> getAdminByJMBG(@PathVariable String jmbg) {
			try {
				Iterable<AdminEntity> administratori = getAllAdmin();
				for (AdminEntity adminEntity : administratori) {
					if (adminEntity.getJmbg().equals(jmbg)) {

						return new ResponseEntity<AdminEntity>(adminEntity, HttpStatus.OK);
					}
				}

				return new ResponseEntity<RESTError>(new RESTError(1, "Administrator nije pronadjen!"), HttpStatus.NOT_FOUND);
			} catch (Exception e) {
				return new ResponseEntity<RESTError>(new RESTError(2, "Exception occurred: " + e.getMessage()),
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<?> updateAdministrator(@PathVariable Integer id, @RequestParam String ime,
			@RequestParam String prezime, @RequestParam String jmbg) {
		try {
			Iterable<AdminEntity> administrator = getAllAdmin();
			for (AdminEntity adminEntity : administrator) {
				if (adminEntity.getId().equals(id)) {
					AdminEntity admin = adminRepository.findOne(id);
					if (ime != null) {
						admin.setIme(ime);
					}
					if (prezime != null) {
						admin.setPrezime(prezime);
					}
					
					if (jmbg!= null) {
						admin.setJmbg(jmbg);
					}
					

					adminRepository.save(admin);
					return new ResponseEntity<AdminEntity>(admin, HttpStatus.OK);
				}
			}
			return new ResponseEntity<RESTError>(new RESTError(1, "Administrator nije pronadjen!"), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Exception occurred: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<AdminEntity> deleteAdmin(@PathVariable Integer id) {

		AdminEntity admin = adminRepository.findOne(id);
		adminRepository.delete(id);
		return new ResponseEntity<AdminEntity>(admin, HttpStatus.OK);
	}
	
	
}



