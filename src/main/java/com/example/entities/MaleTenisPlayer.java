package com.example.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.example.interfaces.MalePlayer;

@Entity
@DiscriminatorValue(value = "MALE_PLAYER")
public class MaleTenisPlayer extends Player implements MalePlayer{

	private String name;
	private Integer habilityLevel;
	private Integer strength;
	private Integer velocity;
	
	public MaleTenisPlayer(String name,Integer habilityLevel, Integer strength,Integer velocity) {
		this.name=name;
		this.habilityLevel=habilityLevel;
		this.strength=strength;
		this.velocity=velocity;
	}
	public MaleTenisPlayer() {
		this.habilityLevel=0;
	}

	public MaleTenisPlayer(String name) {
		this.name = name;
		this.habilityLevel=0;
		this.strength=0;
		this.velocity=0;
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
	public Integer getStrength() {
		return strength;
	}

	@Override
	public Integer getVelocity() {
		return velocity;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public void setVelocity(int velocity) {
		this.velocity = velocity;
	}
	@Override
	public Long getPlayerId() {
		return this.playerId;
	}

}
