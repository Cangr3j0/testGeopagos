package com.example.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.example.entities.FemaleTenisPlayer;

@RepositoryRestResource
public interface FemalePlayerRepository extends CrudRepository<FemaleTenisPlayer, Long>{

}
