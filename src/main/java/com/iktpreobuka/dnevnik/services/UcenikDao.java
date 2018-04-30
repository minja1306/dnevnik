package com.iktpreobuka.dnevnik.services;

import java.util.List;

import com.iktpreobuka.dnevnik.entities.UcenikEntity;



public interface UcenikDao {
	 public List<UcenikEntity> findUcenikByRoditelj(Integer id) ;
	 public List<UcenikEntity> findUcenikByOdeljenje(Integer id) ;
	 public List<UcenikEntity> findUcenikByOdeljenjeSort(Integer id) ;

}
