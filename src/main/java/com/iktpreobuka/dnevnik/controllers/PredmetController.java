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
import com.iktpreobuka.dnevnik.entities.NastavnikEntity;
import com.iktpreobuka.dnevnik.entities.PredmetEntity;
import com.iktpreobuka.dnevnik.entities.RazredEntity;
import com.iktpreobuka.dnevnik.entities.UcenikEntity;
import com.iktpreobuka.dnevnik.entities.dto.PredmetDTO;
import com.iktpreobuka.dnevnik.repositories.NastavnikRepository;

import com.iktpreobuka.dnevnik.repositories.PredmetRepository;
import com.iktpreobuka.dnevnik.repositories.RazredRepository;
import com.iktpreobuka.dnevnik.repositories.UcenikRepository;
import com.iktpreobuka.dnevnik.security.Views;



		@RestController
		@RequestMapping(path="api/v1/predmet")
		public class PredmetController {
			
			
				@Autowired
				private PredmetRepository predmetRepository;
				
				@Autowired
				private RazredRepository razredRepository;
				
				@Autowired
				private NastavnikRepository nastavnikRepository;
				
				@Autowired
				private UcenikRepository ucenikRepository;
				
				@RequestMapping(method = RequestMethod.POST)
				public ResponseEntity<?> createNewPredmet(@RequestBody PredmetDTO predmet) {
				      PredmetEntity pe = new PredmetEntity();
				      pe.setIme(predmet.getIme());
				      pe.setFond(predmet.getFond());
						predmetRepository.save(pe);

						return new ResponseEntity<PredmetEntity>(pe, HttpStatus.OK);
					}


				@RequestMapping(method = RequestMethod.GET)
				@JsonView(Views.Public.class)
				public Iterable<PredmetEntity> getAllPredmeti() {
					return predmetRepository.findAll();
				}

				@RequestMapping(method = RequestMethod.GET, value = "/{id}")

				public ResponseEntity<?> getPredmetByID(@PathVariable Integer id) {
					try {
						Iterable<PredmetEntity> predmet = getAllPredmeti();
						for (PredmetEntity predmetEntity : predmet) {
							if (predmetEntity.getId().equals(id)) {

								return new ResponseEntity<PredmetEntity>(predmetEntity, HttpStatus.OK);
							}
						}

						return new ResponseEntity<RESTError>(new RESTError(1, "Predmet nije pronadjen!"), HttpStatus.NOT_FOUND);
					} catch (Exception e) {
						return new ResponseEntity<RESTError>(new RESTError(2, "Exception occurred: " + e.getMessage()),
								HttpStatus.INTERNAL_SERVER_ERROR);
					}
				}

				@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
				public ResponseEntity<?> updatePredmet(@PathVariable Integer id, @RequestParam String naziv,
						@RequestParam Integer fond) {
					try {
						
						Iterable<PredmetEntity> predmet = getAllPredmeti();
						for (PredmetEntity predmetEntity : predmet) {
							if (predmetEntity.getId().equals(id)) {
						PredmetEntity pe = predmetRepository.findOne(id);
						if (naziv != null) {
							pe.setIme(naziv);
						}
						if (fond != null) {
							pe.setFond(fond);
						}

						predmetRepository.save(pe);
						return new ResponseEntity<PredmetEntity>(pe, HttpStatus.OK);
							}}
						return new ResponseEntity<RESTError>(new RESTError(1, "Predmet nije pronadjen!"), HttpStatus.NOT_FOUND);
					} catch (Exception e) {
						return new ResponseEntity<RESTError>(new RESTError(2, "Exception occurred: " + e.getMessage()),
								HttpStatus.INTERNAL_SERVER_ERROR);
					}
				}

				@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
				public ResponseEntity<PredmetEntity> deletePredmet(@PathVariable Integer id) {

					PredmetEntity pe = predmetRepository.findOne(id);
					predmetRepository.delete(id);
					return new ResponseEntity<PredmetEntity>(pe, HttpStatus.OK);
				}
				
				@JsonView(Views.Public.class)
				@CrossOrigin(origins = "http://localhost:4200")
				@RequestMapping(method = RequestMethod.GET, value = "/ucenik/{id}")
				public List<PredmetEntity> addPredmetiByUcenik(@PathVariable Integer id) {
					UcenikEntity ucenik = ucenikRepository.findOne(id);
					RazredEntity razred = ucenik.getOdeljenje().getRazred();
					return razred.getPredmet();
				}
				
				 	@CrossOrigin(origins = "http://localhost:4200")
				    @RequestMapping(method = RequestMethod.GET, value="/razred/{id}")
				    @JsonView(Views.Public.class)
				    public ResponseEntity<List<PredmetEntity>> getPredmetByRazred(@PathVariable Integer id) {
				        RazredEntity razred = razredRepository.findOne(id);
				        return new ResponseEntity<>(predmetRepository.findByRazred(razred), HttpStatus.OK);
				    }

				
				@CrossOrigin(origins = "http://localhost:4200")
				@RequestMapping(method = RequestMethod.GET, value="/nastavnik/{id}")
				@JsonView(Views.Public.class)
				public ResponseEntity<List<PredmetEntity>> getPredmetByNastavnik(@PathVariable Integer id) {
					NastavnikEntity nastavnik = nastavnikRepository.findOne(id);
					return new ResponseEntity<>(predmetRepository.findByNastavnik(nastavnik), HttpStatus.OK);
				}

			   

			

		
	

}
