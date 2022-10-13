package com.example.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.entities.FemaleTenisPlayer;
import com.example.entities.FemaleTournament;
import com.example.entities.Player;
import com.example.entities.Tournament;

@Repository
public interface FemTournamentRepository extends TournamentRepository<FemaleTournament>{

	void save(Tournament<FemaleTenisPlayer> femaleTournament);


}
