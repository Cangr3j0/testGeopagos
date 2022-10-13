package com.example.testgeopagos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.FemaleTenisPlayer;
import com.example.entities.MaleTenisPlayer;
import com.example.entities.MaleTournament;
import com.example.entities.Player;
import com.example.repositories.FemalePlayerRepository;
import com.example.repositories.MalePlayerRepository;

import io.swagger.v3.oas.annotations.media.Schema;



@RestController
@RequestMapping("/player")
public class PlayerController {
	@Autowired
	private FemalePlayerRepository femalePlayerRepository;
	@Autowired
	private MalePlayerRepository malePlayerRepository;
	
	@GetMapping("/female")
	public Iterable<FemaleTenisPlayer> getFemalePlayerIterable(){
		return femalePlayerRepository.findAll();
	}
	@GetMapping("/male")
	public Iterable<MaleTenisPlayer> getMalePlayerIterable(){
		return malePlayerRepository.findAll();
	}
	@PostMapping("/male")
	public MaleTenisPlayer createMaleTenisPlayer(@RequestBody MaleTenisPlayer player) {
	return	malePlayerRepository.save(player);
	}
	@PostMapping("/female")
	public MaleTenisPlayer createFeMaleTenisPlayer(@RequestBody MaleTenisPlayer player) {
	return	malePlayerRepository.save(player);
	}
}
