package com.iktpreobuka.dnevnik.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.iktpreobuka.dnevnik.entities.UcenikEntity;


@Service
public class UcenikDaoImpl implements UcenikDao {
	
	@PersistenceContext
    EntityManager em;

	

	@Override
	public List<UcenikEntity> findUcenikByRoditelj(Integer id) {
		String sql = "select u " +
				"from UcenikEntity u " +
				"left join fetch u.roditelj r " +
				"where r.id= :id  ";
		
		Query query = em.createQuery(sql);
        query.setParameter("id", id);
        
        List<UcenikEntity> result = new ArrayList<UcenikEntity>();
        result = query.getResultList();
        return result;
	}

	@Override 
	public List<UcenikEntity> findUcenikByOdeljenje(Integer id) {
		String sql = "select u " +
				"from UcenikEntity u " +
				"left join fetch u.odeljenje o " +
				"where o.id= :id  ";
		
		Query query = em.createQuery(sql);
        query.setParameter("id", id);
        
        List<UcenikEntity> result = new ArrayList<UcenikEntity>();
        result = query.getResultList();
        return result;
	}

	 
	 
	@Override
	public List<UcenikEntity> findUcenikByOdeljenjeSort(Integer id) {
		String sql = "select u " +
				"from UcenikEntity u " +
				"left join fetch u.odeljenje o " +
				"where o.id= :id " + " order by u.ime asc";
		
		Query query = em.createQuery(sql);
        query.setParameter("id", id);
        
        List<UcenikEntity> result = new ArrayList<UcenikEntity>();
        result = query.getResultList();
        return result;
	}
}
