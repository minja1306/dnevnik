package com.iktpreobuka.dnevnik.repositories;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.dnevnik.entities.RazredEntity;

public interface RazredRepository extends CrudRepository<RazredEntity,Integer>{
	RazredEntity findByGodina(Integer godina);
}
