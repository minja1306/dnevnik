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
import com.iktpreobuka.dnevnik.entities.RoditeljEntity;
import com.iktpreobuka.dnevnik.repositories.RoditeljRepository;
import com.iktpreobuka.dnevnik.controllers.util.RESTError;
import com.iktpreobuka.dnevnik.entities.UcenikEntity;
import com.iktpreobuka.dnevnik.entities.dto.RoditeljDTO;

import com.iktpreobuka.dnevnik.repositories.UcenikRepository;
import com.iktpreobuka.dnevnik.security.Views;

@RestController
@RequestMapping(path="/api/v1/roditelj")

public class RoditeljController{

	@Autowired
	private RoditeljRepository roditeljRepository;
	
	
	@Autowired
	private UcenikRepository ucenikRepository;
	
	
	
	
	@RequestMapping(method = RequestMethod.POST)
	public  ResponseEntity<?> createNewRod(@RequestBody RoditeljDTO roditelj) {
        RoditeljEntity re = new RoditeljEntity();
		
        re.setIme(roditelj.getIme());
        re.setPrezime(roditelj.getPrezime());
        re.setJmbg(roditelj.getJmbg());
		
		roditeljRepository.save(re);

		return new ResponseEntity<RoditeljEntity>(re, HttpStatus.OK);
	}

	
	@RequestMapping(method = RequestMethod.GET)
	@JsonView(Views.Public.class)
	@CrossOrigin(origins = "http://localhost:4200")
	public Iterable<RoditeljEntity> getAllRoditelji() {
		return roditeljRepository.findAll();
	}

	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")

	public ResponseEntity<?> getRoditeljByID(@PathVariable Integer id) {
		try {
			Iterable<RoditeljEntity> roditelj = getAllRoditelji();
			for (RoditeljEntity roditeljEntity : roditelj) {
				if (roditeljEntity.getId().equals(id)) {

					return new ResponseEntity<RoditeljEntity>(roditeljEntity, HttpStatus.OK);
				}
			}

			return new ResponseEntity<RESTError>(new RESTError(1, "Roditelj nije pronadjen!"), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Exception occurred: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
   @RequestMapping(method = RequestMethod.GET, value = "/jmbg/{jmbg}")
	
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> getRoditeljByJmbg(@PathVariable String jmbg) {
		try {
			Iterable<RoditeljEntity> roditelji = getAllRoditelji();
			for (RoditeljEntity roditeljEntity : roditelji) {
				if (roditeljEntity.getJmbg().equals(jmbg)) {

					return new ResponseEntity<RoditeljEntity>(roditeljEntity, HttpStatus.OK);
				}
			}

			return new ResponseEntity<RESTError>(new RESTError(1, "Roditelj nije pronadjen!"), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Exception occurred: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<?> updateRoditelj(@PathVariable Integer id, @RequestParam String ime,
			@RequestParam String prezime, @RequestParam String jmbg) {
		try {
			Iterable<RoditeljEntity> roditelj = getAllRoditelji();
			for (RoditeljEntity roditeljEntity : roditelj) {
				if (roditeljEntity.getId().equals(id)) {
					RoditeljEntity re = roditeljRepository.findOne(id);
					if (ime != null) {
						re.setIme(ime);
					}
					if (prezime != null) {
						re.setPrezime(prezime);
					}
					if (jmbg!= null) {
						re.setJmbg(jmbg);
					}
					
					roditeljRepository.save(re);
					return new ResponseEntity<RoditeljEntity>(re, HttpStatus.OK);
				}
			}
			return new ResponseEntity<RESTError>(new RESTError(1, "Roditelj nije pronadjen!"), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Exception occurred: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<RoditeljEntity> deleteRoditelj(@PathVariable Integer id) {

		RoditeljEntity re = roditeljRepository.findOne(id);
		roditeljRepository.delete(id);
		return new ResponseEntity<RoditeljEntity>(re, HttpStatus.OK);
	}
	

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}/ucenik")
	public ResponseEntity<?> addUcenikToRoditelj(@PathVariable Integer id, @RequestParam Integer ucenik) {
		try {
			Iterable<RoditeljEntity> roditelj = getAllRoditelji();
			for (RoditeljEntity roditeljEntity : roditelj) {
				if (roditeljEntity.getId().equals(id)) {
					RoditeljEntity re = roditeljRepository.findOne(id);

					UcenikEntity ue = ucenikRepository.findOne(ucenik);

					List<UcenikEntity> ucenici = re.getUcenik();
					ucenici.add(ue);
					re.setUcenik(ucenici);
					roditeljRepository.save(re);
					return new ResponseEntity<RoditeljEntity>(re, HttpStatus.OK);
				}
			}
			return new ResponseEntity<RESTError>(new RESTError(1, "Roditelj nije pronadjen!"), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Exception occurred: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	
	
	
	
	

}
