package com.example.testgeopagos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.FemaleTenisPlayer;
import com.example.entities.FemaleTournament;
import com.example.entities.MaleTenisPlayer;
import com.example.entities.MaleTournament;
import com.example.entities.Player;
import com.example.entities.Tournament;
import com.example.repositories.FemTournamentRepository;
import com.example.repositories.FemalePlayerRepository;
import com.example.repositories.MalePlayerRepository;
import com.example.repositories.MaleTournamentRepository;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/tournaments")
public class TournamentController {

	@Autowired
	FemTournamentRepository femaleTournamentRepository;
	@Autowired
	MaleTournamentRepository maleTournamentRepository;
	@Autowired
	MalePlayerRepository malePlayerRepository;
	@Autowired
	FemalePlayerRepository femalePlayerRepository;

	@Operation(summary = "All tournaments", description = "All tournaments")
	@GetMapping("/all")
	public Iterable<Tournament> allTournaments() {
		List<Tournament>tournaments=new ArrayList<Tournament>();
		Iterable<FemaleTournament>females=femaleTournamentRepository.findAll();
		for(FemaleTournament p:females) {
			tournaments.add(p);
		}
		Iterable<MaleTournament>males=maleTournamentRepository.findAll();
		for(MaleTournament p:males) {
			tournaments.add(p);
		}
	return tournaments;	
	}
	@Operation(summary = "All female tournaments", description = "All female tournaments")
	@GetMapping("/female")
	public Iterable<FemaleTournament> femaleParticipants() {
		return femaleTournamentRepository.findAll();
	}

	@Operation(summary = "Find female tournament by id", description = "Find female tournament by id")
	@GetMapping("/female/{id}")
	public Optional<FemaleTournament> femaleTournamentById(@PathVariable("id") Long id) {
		return femaleTournamentRepository.findById(id);
	}
	
	@Transactional
	@Operation(summary = "Creates a female tournament with the participants", description = "Creates a female tournament with the participants")
	@PostMapping("/female")
	public FemaleTournament createFemaleTournament(@RequestBody FemaleTournament tournament) {
		for (FemaleTenisPlayer m : tournament.getParticipants()) {
			femalePlayerRepository.save(m);
		}
		tournament.setParticipants(tournament.getParticipants());
		FemaleTournament femaleTournament = femaleTournamentRepository.save(tournament);
		return femaleTournament;

	}

	@Operation(summary = "All male tournaments", description = "All male tournaments")
	@GetMapping("/male")
	public Iterable<MaleTournament> maleParticipants() {
		Iterable<MaleTournament> toreturn = maleTournamentRepository.findAll();
		return toreturn;
	}

	@Operation(summary = "Find male tournament by id", description = "Find male tournament by id")
	@GetMapping("/male/{id}")
	public Optional<MaleTournament> maleTournamentById(@PathVariable("id") Long id) {
		return maleTournamentRepository.findById(id);
	}

	@Transactional
	@Operation(summary = "Creates a male tournament with the participants", description = "Creates a male tournament with the participants")
	@PostMapping("/male")
	public MaleTournament create(@RequestBody MaleTournament tournament) {
		for (MaleTenisPlayer m : tournament.getParticipants()) {
			malePlayerRepository.save(m);
		}
		tournament.setParticipants(tournament.getParticipants());
		MaleTournament maleTournament = maleTournamentRepository.save(tournament);
		return maleTournament;

	}
	
	@Transactional
	@Operation(summary = "Start a new male tournament with certain participants", description = "Start a new male tournament with certain participants")
	@PostMapping("/startMaleTournament")
	public MaleTenisPlayer startMaleTournament(@RequestBody MaleTournament tournament) {
		for (MaleTenisPlayer m : tournament.getParticipants()) {
			malePlayerRepository.save(m);
		}
		MaleTenisPlayer winnerMaleTenisPlayer=tournament.startTournament(tournament.getParticipants());
	    tournament.setWinner(winnerMaleTenisPlayer.getName());
	    tournament.setEnded(true);
	    tournament.setEndDate(new Date());
		maleTournamentRepository.save(tournament);
		return winnerMaleTenisPlayer;
	}
	
	@Transactional
	@Operation(summary = "Start a new female tournament with certain participants", description = "Start a new female tournament with certain participants")
	@PostMapping("/startFemaleTournament")
	public FemaleTenisPlayer startFemaleTournament(@RequestBody FemaleTournament tournament) {
		for (FemaleTenisPlayer m : tournament.getParticipants()) {
			femalePlayerRepository.save(m);
		}
		FemaleTenisPlayer winnerFemaleTenisPlayer=tournament.startTournament(tournament.getParticipants());
	    tournament.setWinner(winnerFemaleTenisPlayer.getName());
	    tournament.setEnded(true);
	    tournament.setEndDate(new Date());
		femaleTournamentRepository.save(tournament);
		return winnerFemaleTenisPlayer;
	}
	

	@Operation(summary = "All ended tournaments", description = "All ended tournaments")
	@GetMapping("/maleEndedTournament")
	public Iterable<MaleTournament> endedMaleTournaments() {
		Iterable<MaleTournament> toreturn = maleTournamentRepository.findAll();
		List<MaleTournament> endedMaleTournaments=new ArrayList<MaleTournament>();
		for(MaleTournament tournament:toreturn) {
			if(tournament.getEnded()==true) {
				endedMaleTournaments.add(tournament);
			}
		}
		return endedMaleTournaments;
	}
	
	@Transactional
	@Operation(summary = "All ended tournaments before x date", description = "All ended tournaments before x date")
	@PostMapping("/maleEndedTournamentByDate")
	public Iterable<MaleTournament> endedMaleTournamentsByDate(@RequestBody @DateTimeFormat (iso = DateTimeFormat.ISO.DATE_TIME) Date date) {
		Iterable<MaleTournament> toreturn = maleTournamentRepository.findAll();
		List<MaleTournament> endedMaleTournaments=new ArrayList<MaleTournament>();
		for(MaleTournament tournament:toreturn) {
			if(tournament.getEnded()==true&&tournament.getEndDate().compareTo(date)<=0) {
				endedMaleTournaments.add(tournament);
			}
		}
		return endedMaleTournaments;
	}
	
	@Operation(summary = "Delete a male tournament by tournament id", description = "Delete a male tournament by tournament id")
	@Transactional
	@DeleteMapping("/male/{tournamentId}")
	public MaleTournament deleteMaleTournament(@PathVariable("tournamentId") Long id)
	{
		Optional<MaleTournament> deletedTournament=maleTournamentRepository.findById(id);
	    maleTournamentRepository.delete(deletedTournament.get());
	    return deletedTournament.get();
	}
	
	@Operation(summary = "Delete a female tournament by tournament id", description = "Delete a female tournament by tournament id")
	@Transactional
	@DeleteMapping("/female/{tournamentId}")
	public FemaleTournament deleteFemaleTournament(@PathVariable("tournamentId") Long id)
	{
		Optional<FemaleTournament> deletedTournament=femaleTournamentRepository.findById(id);
	    femaleTournamentRepository.delete(deletedTournament.get());
	    return deletedTournament.get();
	}
}
