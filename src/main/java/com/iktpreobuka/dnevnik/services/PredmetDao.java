package com.iktpreobuka.dnevnik.services;

import java.util.List;

import com.iktpreobuka.dnevnik.entities.PredmetEntity;



public interface PredmetDao {
	public List<PredmetEntity> findPredmetByUcenik(Integer id) ;


}
