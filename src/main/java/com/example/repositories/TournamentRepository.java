package com.example.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.example.entities.FemaleTenisPlayer;
import com.example.entities.FemaleTournament;
import com.example.entities.MaleTenisPlayer;
import com.example.entities.Player;
import com.example.entities.Tournament;

@NoRepositoryBean
public interface TournamentRepository<T extends Tournament> extends CrudRepository<T, Long> {

}
