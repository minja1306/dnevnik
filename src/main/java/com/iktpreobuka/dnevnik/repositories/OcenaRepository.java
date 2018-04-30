package com.iktpreobuka.dnevnik.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.dnevnik.entities.NastavnikEntity;
import com.iktpreobuka.dnevnik.entities.OcenaEntity;
import com.iktpreobuka.dnevnik.entities.PredmetEntity;
import com.iktpreobuka.dnevnik.entities.UcenikEntity;

public interface OcenaRepository extends CrudRepository <OcenaEntity,Integer> {
	List<OcenaEntity> findByPredmetAndUcenik(PredmetEntity predmet, UcenikEntity ucenik);
	List<OcenaEntity> findByUcenikAndNastavnik(UcenikEntity ucenik, NastavnikEntity nastavnik);
	
}
