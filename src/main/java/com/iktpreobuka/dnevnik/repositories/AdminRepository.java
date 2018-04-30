package com.iktpreobuka.dnevnik.repositories;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.dnevnik.entities.AdminEntity;

public interface AdminRepository extends CrudRepository<AdminEntity,Integer> {

}
