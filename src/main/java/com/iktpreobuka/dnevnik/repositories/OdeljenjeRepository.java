package com.iktpreobuka.dnevnik.repositories;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.dnevnik.entities.OdeljenjeEntity;

import com.iktpreobuka.dnevnik.entities.RazredEntity;


public interface OdeljenjeRepository extends CrudRepository<OdeljenjeEntity, Integer> {
	OdeljenjeEntity findByOznakaAndRazred(String oznaka, RazredEntity razred);
}

