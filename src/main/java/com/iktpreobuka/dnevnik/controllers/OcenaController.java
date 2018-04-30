package com.iktpreobuka.dnevnik.controllers;



import java.util.Date;
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
import com.iktpreobuka.dnevnik.entities.OcenaEntity;
import com.iktpreobuka.dnevnik.entities.PredmetEntity;
import com.iktpreobuka.dnevnik.entities.UcenikEntity;
import com.iktpreobuka.dnevnik.entities.dto.OcenaDTO;
import com.iktpreobuka.dnevnik.repositories.NastavnikRepository;
import com.iktpreobuka.dnevnik.repositories.OcenaRepository;
import com.iktpreobuka.dnevnik.repositories.PredmetRepository;
import com.iktpreobuka.dnevnik.repositories.UcenikRepository;
import com.iktpreobuka.dnevnik.security.Views;
import com.iktpreobuka.dnevnik.services.OcenaDao;




	@RestController
	@RequestMapping(path="api/v1/ocena")
	public class OcenaController {
		
		@Autowired
		private OcenaRepository ocenaRepository;
		
		@Autowired
		private PredmetRepository predmetRepository;
		
		@Autowired
		private UcenikRepository ucenikRepository;
		
		@Autowired
		private NastavnikRepository nastavnikRepository;
		
		@Autowired
		private OcenaDao ocenadao;
		
		@CrossOrigin(origins = "http://localhost:4200")
		@RequestMapping(method=RequestMethod.POST)
		public ResponseEntity<?> addNewOcena(@RequestParam Integer predmetId, @RequestParam Integer ucenikId,
				@RequestParam Integer nastavnikId, @RequestBody OcenaDTO ocena) {
				if (predmetRepository.exists(predmetId)) {
					if (ucenikRepository.exists(ucenikId)) {
						if (nastavnikRepository.exists(nastavnikId)) {
							if ((ocena.getVrednost() >0) && (ocena.getVrednost() <6)) {
								PredmetEntity predmet = predmetRepository.findOne(predmetId);
								UcenikEntity ucenik = ucenikRepository.findOne(ucenikId);
								NastavnikEntity nastavnik = nastavnikRepository.findOne(nastavnikId);
								OcenaEntity oe = new OcenaEntity();
								oe.setPredmet(predmet);
								oe.setUcenik(ucenik);
								oe.setNastavnik(nastavnik);
								oe.setVrednost(ocena.getVrednost());
								oe.setDatum(ocena.getDatum());
								oe.setPolugodiste(ocena.getPolugodiste());
								oe.setZakljucna(ocena.isZakljucna());
				//oc.setDeleted(false);
				ocenaRepository.save(oe);
				return new ResponseEntity<OcenaEntity>(oe, HttpStatus.OK);
				}
				return new ResponseEntity<RESTError>(new RESTError(1, "Ocena mora biti izmedju 1 i 5!"), HttpStatus.BAD_REQUEST);
				}
				return new ResponseEntity<RESTError>(new RESTError(2, "Nastavnik ne postoji u bazi!"), HttpStatus.NOT_FOUND);
				}
				return new ResponseEntity<RESTError>(new RESTError(3, "Ucenik ne postoji u bazi!"), HttpStatus.NOT_FOUND);
				}
				return new ResponseEntity<RESTError>(new RESTError(4, "Predmet ne postoji u bazi!"), HttpStatus.NOT_FOUND);
				}



		/*public ResponseEntity<?> addNewOcena(@RequestParam Integer vrednost,@RequestParam Date datum,@RequestParam boolean zakljucna,@RequestParam Integer polugodiste){
			OcenaEntity ocena = new OcenaEntity();
			ocena.setVrednost(vrednost);
			ocena.setDatum(datum);
			ocena.setZakljucna(zakljucna);
			ocena.setPolugodiste(polugodiste);
			ocenaRepository.save(ocena);
			return ocena;
			
		} */
		
		@RequestMapping(method=RequestMethod.GET)
		@JsonView(Views.Public.class)
	
		public Iterable<OcenaEntity>getAllOcene(){
			return ocenaRepository.findAll();
			
		}
		
		
		
		
		@RequestMapping(method = RequestMethod.GET, value = "/{id}")
		public ResponseEntity<?>getOcenaById(@PathVariable Integer id) {
			try{
					Iterable<OcenaEntity> ocena = getAllOcene();
					for (OcenaEntity ocenaEntity : ocena) {
						if (ocenaEntity.getId().equals(id)) {

							return new ResponseEntity<OcenaEntity>(ocenaEntity, HttpStatus.OK);
						}
					}

					return new ResponseEntity<RESTError>(new RESTError(1, "Ocena nije pronadjena!"), HttpStatus.NOT_FOUND);
				} catch (Exception e) {
					return new ResponseEntity<RESTError>(new RESTError(2, "Exception occurred: " + e.getMessage()),
							HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}

		
		
		

		//update
		
		@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
		public ResponseEntity<?> updateOcena(@PathVariable Integer id, @RequestParam Integer vrednost,
				@RequestParam Date datum,@RequestParam Integer polugodiste,@RequestParam Boolean zakljucna) {
			try {
				Iterable<OcenaEntity> ocena = getAllOcene();
				for (OcenaEntity ocenaEntity : ocena) {
					if (ocenaEntity.getId().equals(id)) {

						OcenaEntity oe = ocenaRepository.findOne(id);
						if (vrednost != null) {
							oe.setVrednost(vrednost);
						}
						if (datum != null) {
							oe.setDatum(datum);
						}
						if (polugodiste != null) {
							oe.setPolugodiste(polugodiste);
						}
						if (zakljucna != null) {
							oe.setZakljucna(zakljucna);
						}

						ocenaRepository.save(oe);
						return new ResponseEntity<OcenaEntity>(oe, HttpStatus.OK);
					}
				}
				return new ResponseEntity<RESTError>(new RESTError(1, "Ocena nije pronadjena"), HttpStatus.NOT_FOUND);
			} catch (Exception e) {
				return new ResponseEntity<RESTError>(new RESTError(2, "Exception occurred: " + e.getMessage()),
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		
		
		
		

		// delete
		@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
		public ResponseEntity<OcenaEntity> deleteOcena(@PathVariable Integer id) {

			OcenaEntity oe= ocenaRepository.findOne(id);
			ocenaRepository.delete(id);
			return new ResponseEntity<OcenaEntity>(oe, HttpStatus.OK);
		}
		
		
		
		@RequestMapping(method = RequestMethod.PUT, value = "/{id}/ucenik")
		public ResponseEntity<?> addUcenikToOcena(@PathVariable Integer id, @RequestParam Integer ucenik) {
			try {
				Iterable<OcenaEntity> ocena = getAllOcene();
				for (OcenaEntity ocenaEntity : ocena) {
					if (ocenaEntity.getId().equals(id)) {
						OcenaEntity oe = ocenaRepository.findOne(id);
						UcenikEntity ue = ucenikRepository.findOne(ucenik);
						oe.setUcenik(ue);
						ocenaRepository.save(oe);
						return new ResponseEntity<OcenaEntity>(oe, HttpStatus.OK);
					}
				}
				return new ResponseEntity<RESTError>(new RESTError(1, "Ocena nije pronadjena!"), HttpStatus.NOT_FOUND);
			} catch (Exception e) {
				return new ResponseEntity<RESTError>(new RESTError(2, "Exception occurred: " + e.getMessage()),
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
		
		@RequestMapping(method = RequestMethod.PUT, value = "/{id}/nastavnik")
		public ResponseEntity<?> addNastavnikToOcena(@PathVariable Integer id, @RequestParam Integer nastavnik) {
			try {
				Iterable<OcenaEntity> ocena = getAllOcene();
				for (OcenaEntity ocenaEntity : ocena) {
					if (ocenaEntity.getId().equals(id)) {
						OcenaEntity oe = ocenaRepository.findOne(id);
						NastavnikEntity ne = nastavnikRepository.findOne(nastavnik);
						oe.setNastavnik(ne);
						ocenaRepository.save(oe);
						return new ResponseEntity<OcenaEntity>(oe, HttpStatus.OK);
					}
				}
				return new ResponseEntity<RESTError>(new RESTError(1, "Ocena nije pronadjena!"), HttpStatus.NOT_FOUND);
			} catch (Exception e) {
				return new ResponseEntity<RESTError>(new RESTError(2, "Exception occurred: " + e.getMessage()),
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
		
		@RequestMapping(method = RequestMethod.PUT, value = "/{id}/predmet")
		public ResponseEntity<?> addPredmetToOcena(@PathVariable Integer id, @RequestParam Integer predmet) {
			try {
				Iterable<OcenaEntity> ocena = getAllOcene();
				for (OcenaEntity ocenaEntity : ocena) {
					if (ocenaEntity.getId().equals(id)) {
						OcenaEntity oe = ocenaRepository.findOne(id);
						PredmetEntity pe = predmetRepository.findOne(predmet);
						oe.setPredmet(pe);
						ocenaRepository.save(oe);
						return new ResponseEntity<OcenaEntity>(oe, HttpStatus.OK);
					}
				}
				return new ResponseEntity<RESTError>(new RESTError(1, "Ocena nije pronadjena!"), HttpStatus.NOT_FOUND);
			} catch (Exception e) {
				return new ResponseEntity<RESTError>(new RESTError(2, "Exception occurred: " + e.getMessage()),
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
		@JsonView(Views.Private.class)
		@CrossOrigin(origins = "http://localhost:4200")
		@RequestMapping(method = RequestMethod.GET, value = "/ucenik/{id}")
		public List<OcenaEntity> addOcenatoUcenik(@PathVariable Integer id) {
		return ocenadao.findOcenaByUcenikID(id);
		}
		
		@JsonView(Views.Private.class)
		@RequestMapping(method = RequestMethod.GET, value = "/ucenik")
		public List<OcenaEntity> addOcenatoUcenik(@RequestParam String ime,@RequestParam String prezime) {
		return ocenadao.findOcenaByUcenik(ime,prezime);
		}
		
		@JsonView(Views.Private.class)
		@CrossOrigin(origins = "http://localhost:4200")
		@RequestMapping(method = RequestMethod.GET, value = "/ucenik/{ucenikId}/predmet/{predmetId}")
		public List<OcenaEntity> addOcenatoUcenik(@PathVariable Integer ucenikId, @PathVariable Integer predmetId) {
			PredmetEntity predmet = predmetRepository.findOne(predmetId);
			UcenikEntity ucenik = ucenikRepository.findOne(ucenikId);
			return ocenaRepository.findByPredmetAndUcenik(predmet, ucenik);
		}
		
		@CrossOrigin(origins = "http://localhost:4200")
		@RequestMapping(method = RequestMethod.GET, value = "/ucenik/{ucenikId}/nastavnik/{nastavnikId}")
		@JsonView(Views.Public.class)
		public ResponseEntity<List<OcenaEntity>> getOcenaByUcenikAndNastavnik(@PathVariable Integer ucenikId,
																			  @PathVariable Integer nastavnikId) {
			UcenikEntity ucenik = ucenikRepository.findOne(ucenikId);
			NastavnikEntity nastavnik = nastavnikRepository.findOne(nastavnikId);

			return new ResponseEntity<>(ocenaRepository.findByUcenikAndNastavnik(ucenik, nastavnik), HttpStatus.OK);
		}

		
		
		}


		
		
		
		

	

	

