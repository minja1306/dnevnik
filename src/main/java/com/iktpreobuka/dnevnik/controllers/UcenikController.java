package com.iktpreobuka.dnevnik.controllers;

import java.util.ArrayList;
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
import com.iktpreobuka.dnevnik.entities.RoditeljEntity;
import com.iktpreobuka.dnevnik.entities.UcenikEntity;
import com.iktpreobuka.dnevnik.entities.dto.RoditeljDTO;
import com.iktpreobuka.dnevnik.entities.dto.UcenikDTO;
import com.iktpreobuka.dnevnik.entities.dto.UcenikRoditeljDTO;
import com.iktpreobuka.dnevnik.repositories.OcenaRepository;
import com.iktpreobuka.dnevnik.repositories.OdeljenjeRepository;
import com.iktpreobuka.dnevnik.repositories.RazredRepository;
import com.iktpreobuka.dnevnik.repositories.RoditeljRepository;
import com.iktpreobuka.dnevnik.repositories.UcenikRepository;
import com.iktpreobuka.dnevnik.security.Views;
import com.iktpreobuka.dnevnik.services.UcenikDao;

@RestController
@RequestMapping(path="/api/v1/ucenik")
public class UcenikController {
	
	
	@Autowired
	private UcenikRepository ucenikRepository;
	@Autowired
	private RoditeljRepository roditeljRepository;

	@Autowired
	private RazredRepository razredRepository;
 
	@Autowired
	private OdeljenjeRepository odeljenjeRepository;



	
	@Autowired
	private UcenikDao ucenikdao;
	
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> createUcenik(@RequestBody UcenikRoditeljDTO ucenik_roditelj, @RequestParam Integer razred, @RequestParam String odeljenje) {
		
		RazredEntity re=razredRepository.findByGodina(razred);
		OdeljenjeEntity oe= odeljenjeRepository.findByOznakaAndRazred( odeljenje,re);
		
		UcenikDTO ucenik = ucenik_roditelj.getUcenik();
		RoditeljDTO roditelj = ucenik_roditelj.getRoditelj();
		
		RoditeljEntity rod = new RoditeljEntity();
		rod.setIme(roditelj.getIme());
		rod.setPrezime(roditelj.getPrezime());
		rod.setJmbg(roditelj.getJmbg());
		
		rod = roditeljRepository.save(rod);

		List<RoditeljEntity> roditelji = new ArrayList<RoditeljEntity>();
		roditelji.add(rod);
		
		UcenikEntity ue = new UcenikEntity();
		
		ue.setIme(ucenik.getIme());
		ue.setPrezime(ucenik.getPrezime());
		ue.setJmbg(ucenik.getJmbg());
		ue.setOdeljenje(oe);
		ue.setRoditelj(roditelji);

		List<UcenikEntity> ucenici = new ArrayList<UcenikEntity>();
		ucenici.add(ue);
		rod.setUcenik(ucenici);
		ucenikRepository.save(ue);



		return new ResponseEntity<UcenikEntity>(ue, HttpStatus.OK);
	
	}

	
	

	
	@JsonView(Views.Admin.class)
	@RequestMapping(method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:4200")
	public Iterable<UcenikEntity> getAllUcenici(){ 
		return ucenikRepository.findAll();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	//@JsonView(Views.Public.class)
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> getUcenikByID(@PathVariable Integer id) {
		try {
			Iterable<UcenikEntity> ucenici = getAllUcenici();
			for (UcenikEntity ucenikEntity : ucenici) {
				if (ucenikEntity.getId().equals(id)) {

					return new ResponseEntity<UcenikEntity>(ucenikEntity, HttpStatus.OK);
				}
			}

			return new ResponseEntity<RESTError>(new RESTError(1, "Ucenik nije pronadjen!"), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Exception occurred: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/jmbg/{jmbg}")
	
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> getUcenikByJmbg(@PathVariable String jmbg) {
		try {
			Iterable<UcenikEntity> ucenici = getAllUcenici();
			for (UcenikEntity ucenikEntity : ucenici) {
				if (ucenikEntity.getJmbg().equals(jmbg)) {

					return new ResponseEntity<UcenikEntity>(ucenikEntity, HttpStatus.OK);
				}
			}

			return new ResponseEntity<RESTError>(new RESTError(1, "Ucenik nije pronadjen!"), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Exception occurred: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	@JsonView(Views.Public.class)
	public ResponseEntity<?> updateUcenik(@PathVariable Integer id, @RequestParam String ime,
			@RequestParam String prezime, @RequestParam String jmbg) {
		try {
			Iterable<UcenikEntity>ucenik = getAllUcenici();

			for (UcenikEntity ucenikEntity : ucenik) {
				if (ucenikEntity.getId().equals(id)) {
					UcenikEntity ue = ucenikRepository.findOne(id);
					if (ime != null) {
						ue.setIme(ime);
						;
					}
					if (prezime != null) {
						ue.setPrezime(prezime);
					}

					if (jmbg != null) {
						ue.setJmbg(jmbg);
					}
					
					ucenikRepository.save(ucenik);
					return new ResponseEntity<UcenikEntity>(ue, HttpStatus.OK);
				}
			}
			return new ResponseEntity<RESTError>(new RESTError(1, "Ucenik nije pronadjen!"), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Exception occurred: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<UcenikEntity> deleteUcenik(@PathVariable Integer id) {

		UcenikEntity ue = ucenikRepository.findOne(id);
		ucenikRepository.delete(id);
		return new ResponseEntity<UcenikEntity>(ue, HttpStatus.OK);
	}
	
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}/odeljenje")
	public ResponseEntity<?> addOdeljenjeToAUcenik(@PathVariable Integer id, @RequestParam Integer odeljenje) {
		try {
			Iterable<UcenikEntity> ucenik = getAllUcenici();

			for (UcenikEntity ucenikEntity : ucenik) {
				if (ucenikEntity.getId().equals(id)) {

					UcenikEntity ue = ucenikRepository.findOne(id);
					OdeljenjeEntity oe = odeljenjeRepository.findOne(odeljenje);
					ue.setOdeljenje(oe);
					ucenikRepository.save(ue);
					return new ResponseEntity<UcenikEntity>(ue, HttpStatus.OK);
			
				}
			}
			return new ResponseEntity<RESTError>(new RESTError(1, "Ucenik nije pronadjen!"), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Exception occurred: " + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@JsonView(Views.Public.class)
	@RequestMapping(method = RequestMethod.GET, value = "/odeljenje/{id}")
	public List<UcenikEntity> addUcenikToOdeljenje(@PathVariable Integer id) {
	return ucenikdao.findUcenikByOdeljenje(id);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(method = RequestMethod.GET, value = "/roditelj/{id}")
	public List<UcenikEntity> addUcenikByroditelj(@PathVariable Integer id) {
	return ucenikdao.findUcenikByRoditelj(id);
	}
	
	@JsonView(Views.Public.class)
	@RequestMapping(method = RequestMethod.GET, value = "/odeljenje/sort/{id}")
	public List<UcenikEntity> addUcenikToOdeljenjeSort(@PathVariable Integer id) {
	return ucenikdao.findUcenikByOdeljenjeSort(id);
	}
}
	
	

	
	
	

	

	
	
		
	
	

