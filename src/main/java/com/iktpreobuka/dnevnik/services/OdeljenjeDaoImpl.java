package com.iktpreobuka.dnevnik.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.iktpreobuka.dnevnik.entities.OdeljenjeEntity;

@Service
public class OdeljenjeDaoImpl implements OdeljenjeDao{
	@PersistenceContext
    EntityManager em;
	
	@Override
	public List<OdeljenjeEntity> findOdeljenjeByNastavnik(Integer id) {
		String sql = "select o " +
				"from OdeljenjeEntity o " +
				"left join fetch o.nastavnik n " +
				"where n.id= :id  ";
		
		Query query = em.createQuery(sql);
        query.setParameter("id", id);
        
        List<OdeljenjeEntity> result = new ArrayList<OdeljenjeEntity>();
        result = query.getResultList();
        return result;
	}
	
	
}
