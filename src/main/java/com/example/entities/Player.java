package com.example.entities;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.DiscriminatorType;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name = "PLAYER_TYPE",
        discriminatorType = DiscriminatorType.STRING)
public abstract class Player {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PLAYER_SEQ")
	@SequenceGenerator(sequenceName = "player_seq", allocationSize = 1, name = "PLAYER_SEQ")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected Long playerId;
	public abstract String getName();
	public abstract Integer getHabilityLevel();
	public abstract Long getPlayerId();
}
