package com.iktpreobuka.dnevnik.controllers;

import java.util.List;

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
import com.iktpreobuka.dnevnik.entities.OdeljenjeEntity;
import com.iktpreobuka.dnevnik.entities.RazredEntity;
import com.iktpreobuka.dnevnik.entities.dto.OdeljenjeDTO;

import com.iktpreobuka.dnevnik.repositories.OdeljenjeRepository;
import com.iktpreobuka.dnevnik.repositories.RazredRepository;

import com.iktpreobuka.dnevnik.security.Views;
import com.iktpreobuka.dnevnik.services.OdeljenjeDao;

@RestController
@RequestMapping(path = "/api/v1/odeljenje")

public class OdeljenjeController {
	
	@Autowired
	private OdeljenjeRepository odeljenjeRepository;

	
	@Autowired
	private RazredRepository razredRepository;

	
	@Autowired
	private OdeljenjeDao odeljenjedao;
	
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> createNewOdeljenje(@RequestBody OdeljenjeDTO odeljenje) {
       OdeljenjeEntity od = new OdeljenjeEntity();
		
		od.setOznaka(odeljenje.getOznaka());
		odeljenjeRepository.save(od);

		return new ResponseEntity<OdeljenjeEntity>(od, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	@JsonView(Views.Public.class)
	public Iterable<OdeljenjeEntity> getAllOdeljenja() {
		return odeljenjeRepository.findAll();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")

	public ResponseEntity<?> getOdeljenjeByID(@PathVariable Integer id) {
		try {
			Iterable<OdeljenjeEntity> odeljenje = getAllOdeljenja();
			for (OdeljenjeEntity odeljenjeEntity : odeljenje) {
				if (odeljenjeEntity.getId().equals(id)) {

					return new ResponseEntity<OdeljenjeEntity>(odeljenjeEntity, HttpStatus.OK);
				}
			}

			return new ResponseEntity<RESTError>(new RESTError(1, "Odeljenje nije pronadjeno!"), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Exception occurred: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<?> updateOdeljenje(@PathVariable Integer id, @RequestParam String oznaka) {
		try {
			Iterable<OdeljenjeEntity> odeljenje = getAllOdeljenja();
			for (OdeljenjeEntity odeljenjeEntity : odeljenje) {
				if (odeljenjeEntity.getId().equals(id)) {

					OdeljenjeEntity od = odeljenjeRepository.findOne(id);
					if (oznaka != null) {
						od.setOznaka(oznaka);
					}

					odeljenjeRepository.save(od);
					return new ResponseEntity<OdeljenjeEntity>(od, HttpStatus.OK);
				}
			}
			return new ResponseEntity<RESTError>(new RESTError(1, "Odeljenje nije pronadjeno!"), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Exception occurred: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<OdeljenjeEntity> deleteOdeljenje(@PathVariable Integer id) {

		OdeljenjeEntity od = odeljenjeRepository.findOne(id);
		odeljenjeRepository.delete(id);
		return new ResponseEntity<OdeljenjeEntity>(od, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}/razred")
	public ResponseEntity<?> addRazredToOdeljenje(@PathVariable Integer id, @RequestParam Integer razred) {
		try {
			Iterable<OdeljenjeEntity> odeljenje = getAllOdeljenja();
			for (OdeljenjeEntity odeljenjeEntity : odeljenje) {
				if (odeljenjeEntity.getId().equals(id)) {
					OdeljenjeEntity od = odeljenjeRepository.findOne(id);
					RazredEntity raz = razredRepository.findOne(razred);
					od.setRazred(raz);
					odeljenjeRepository.save(od);
					return new ResponseEntity<OdeljenjeEntity>(od, HttpStatus.OK);
				}
			}
			return new ResponseEntity<RESTError>(new RESTError(1, "Odeljenje nije pronadjeno!"), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Exception occurred: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@JsonView(Views.Public.class)
	@RequestMapping(method = RequestMethod.GET, value = "/nastavnik/{id}")
	public List<OdeljenjeEntity> addOdeljenjeByNastavnik(@PathVariable Integer id) {
	return odeljenjedao. findOdeljenjeByNastavnik(id);
	}



}



