package com.iktpreobuka.dnevnik.services;

import java.util.List;

import com.iktpreobuka.dnevnik.entities.OdeljenjeEntity;

public interface OdeljenjeDao {
	public List<OdeljenjeEntity> findOdeljenjeByNastavnik(Integer id) ;

}
