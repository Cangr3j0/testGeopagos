package com.example.repositories;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.entities.MaleTenisPlayer;
import com.example.entities.MaleTournament;
import com.example.entities.Player;
import com.example.entities.Tournament;

@Repository
public interface MaleTournamentRepository extends TournamentRepository<MaleTournament>{

	void save(Tournament<MaleTenisPlayer> maleTournament);
//	Iterable<MaleTournament>findAllMaleTournament();
//	@Query("")
//	Iterable<Tournament<MaleTenisPlayer>> findAllMalePlayers();

}
