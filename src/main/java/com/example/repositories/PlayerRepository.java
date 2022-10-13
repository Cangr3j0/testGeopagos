package com.example.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.example.entities.Player;


@NoRepositoryBean
public interface PlayerRepository extends CrudRepository<Player,Long>{

}
