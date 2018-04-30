package com.iktpreobuka.dnevnik.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.iktpreobuka.dnevnik.entities.PredmetEntity;
@Service
public class PredmetDaoImpl implements PredmetDao{
	

	@PersistenceContext
    EntityManager em;



	@Override
	public List<PredmetEntity> findPredmetByUcenik(Integer id) {
		String sql = "select p " +
				"from PredmetEntity p " +
				"left join fetch p.ocena u " +
				"where u.ucenik.id= :id  ";
		
		Query query = em.createQuery(sql);
        query.setParameter("id", id);
        
        List<PredmetEntity> result = new ArrayList<PredmetEntity>();
        result = query.getResultList();
        return result;
        
       
	}	

}

