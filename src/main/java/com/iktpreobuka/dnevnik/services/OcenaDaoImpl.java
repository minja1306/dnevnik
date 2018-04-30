package com.iktpreobuka.dnevnik.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.iktpreobuka.dnevnik.entities.OcenaEntity;

@Service
public class OcenaDaoImpl implements OcenaDao {
	
	
	
	@PersistenceContext
    EntityManager em;
	
	@Override
	public List<OcenaEntity> findOcenaByUcenik(String ime,String prezime) {
		String sql = "select o " +
				"from OcenaEntity o " +
				"left join fetch o.ucenik u " +
				"where u.ime= :ime and  u.prezime= :prezime ";
		
		Query query = em.createQuery(sql);
        query.setParameter("ime", ime);
        query.setParameter("prezime", prezime);
        List<OcenaEntity> result = new ArrayList<OcenaEntity>();
        result = query.getResultList();
        return result;
        
	}

	@Override
	public List<OcenaEntity> findOcenaByUcenikID(Integer id) {
		String sql = "select o " +
				"from OcenaEntity o " +
				"left join fetch o.ucenik u " +
				"where u.id= :id ";
		
		Query query = em.createQuery(sql);
        query.setParameter("id", id);
      
        List<OcenaEntity> result = new ArrayList<OcenaEntity>();
        result = query.getResultList();
        return result;

}
}