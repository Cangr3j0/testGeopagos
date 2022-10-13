package com.example.entities;

import java.util.Comparator;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.example.interfaces.FemalePlayer;

@Entity
@DiscriminatorValue(value = "FEMALE_PLAYER")
public class FemaleTenisPlayer extends Player implements FemalePlayer {

	private String name;
	private Integer habilityLevel;
	private Integer reactionTime;

	public FemaleTenisPlayer() {
		this.habilityLevel=0;
		this.reactionTime=0;
	}

	public FemaleTenisPlayer(String name, int habilityLevel, int reactionTime) {
		this.name = name;
		this.habilityLevel = habilityLevel;
		this.reactionTime = reactionTime;
	}

	public FemaleTenisPlayer(String name) {
		this.name = name;
		this.habilityLevel=0;
		this.reactionTime=0;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Integer getHabilityLevel() {
		return habilityLevel;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setHabilityLevel(int habilityLevel) {
		this.habilityLevel = habilityLevel;
	}

	@Override
	public Integer getReactionTime() {
		return reactionTime;
	}

	public void setReactionTime(int reactionTime) {
		this.reactionTime = reactionTime;
	}

	@Override
	public Long getPlayerId() {
		return this.playerId;
	}


}
