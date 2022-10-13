package com.example.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonValueFormat;

import io.swagger.v3.oas.annotations.Parameter;


@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TOURNAMENT_TYPE",discriminatorType = DiscriminatorType.STRING)
public abstract class Tournament<T> {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TOURNAMENT_SEQ")
	@SequenceGenerator(sequenceName = "tournament_seq", allocationSize = 1, name = "TOURNAMENT_SEQ")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected Long tournamentId;
	
	@JsonProperty(required = false, access = JsonProperty.Access.READ_ONLY)
	@Parameter(required = false, hidden = true)
	protected String winner;

	@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.MERGE,orphanRemoval = false)
	@JoinColumn(name = "tournament_id")
	protected List<T> participants=new ArrayList<T>();
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected String type;
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	protected Boolean ended=false;
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	 @Temporal(TemporalType.DATE)
	 @DateTimeFormat(pattern = "dd-MM-yyyy")
	@JsonFormat (shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	protected Date startDate=new Date();

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	 @Temporal(TemporalType.DATE)
	 @DateTimeFormat(pattern = "dd-MM-yyyy")
	@JsonFormat (shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	protected Date endDate;
	
	public Tournament() {
	}
	public Tournament(ArrayList<T>list) {
		participants=list;
	}
	
	public abstract List<T> getParticipants();
	
	protected abstract int comparePlayers(T player1, T player2);

	public abstract T startTournament(List<T> list);
	
	public abstract String getType();
	
	public abstract Long getTournamentId();
	
	public abstract String getWinner();
	
	public abstract Boolean getEnded();
	
	public abstract Date getStartDate();
	
	public abstract Date getEndDate();
	
}
