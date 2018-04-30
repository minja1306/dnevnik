package com.iktpreobuka.dnevnik.services;

import java.util.List;

import com.iktpreobuka.dnevnik.entities.OcenaEntity;



public interface OcenaDao {
	public List<OcenaEntity> findOcenaByUcenik(String ime,String prezime);
	
	public List<OcenaEntity> findOcenaByUcenikID(Integer id);
}
