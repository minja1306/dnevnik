package com.iktpreobuka.dnevnik.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.dnevnik.entities.NastavnikEntity;
import com.iktpreobuka.dnevnik.entities.PredmetEntity;
import com.iktpreobuka.dnevnik.entities.RazredEntity;




public interface PredmetRepository extends CrudRepository<PredmetEntity,Integer>{
	List<PredmetEntity> findByNastavnik(NastavnikEntity nastavnik);
    List<PredmetEntity> findByRazred(RazredEntity razred);

	
	
}
