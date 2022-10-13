package com.example.entities;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
@DiscriminatorValue(value = "MALE_TOURNAMENT")
public class MaleTournament extends Tournament<MaleTenisPlayer> {

	public MaleTournament(ArrayList<MaleTenisPlayer>list) {
		super(list);
	}


	public MaleTournament() {
		this.type="Male tournament";
	}

	@Override
	public List<MaleTenisPlayer> getParticipants() {
		return this.participants;
	}


	@Override
	protected int comparePlayers(MaleTenisPlayer player1, MaleTenisPlayer player2) {
		return Comparator.comparing(MaleTenisPlayer::getHabilityLevel).thenComparing(MaleTenisPlayer::getVelocity).thenComparing(MaleTenisPlayer::getStrength).compare(player1,
				player2);
	}

	@Override
	public MaleTenisPlayer startTournament(List<MaleTenisPlayer> list) {
		if (list.size() == 1) {
			return list.get(0);
		} else {
			MaleTenisPlayer player1 = list.remove(list.size()-1);
			MaleTenisPlayer player2 = list.remove(list.size()-1);
			int comp = comparePlayers(player1, player2);
			if (comp > 0) {
				list.add(player1);
			} else if (comp < 0) {
				list.add(player2);
			} else {
				list.add(player1);
			}
			MaleTenisPlayer winner=startTournament(list);	
		if(winner!=null) {
			return winner;
		}
		}
		return null;
	}

	@Override
	public String getType() {
		return this.type;
	}
	
	public void setParticipants(List<MaleTenisPlayer> participantsList) {
		this.participants = participantsList;
	}


	@Override
	public Long getTournamentId() {
		return this.tournamentId;
	}

	@Override
	public String getWinner() {
		return this.winner;
	}
	
	public void setWinner(String winner) {
		this.winner=winner;
	}


	@Override
	public Boolean getEnded() {
		return this.ended;
	}
	
	public void setEnded(Boolean ended) {
		this.ended=ended;
	}


	@Override
	public Date getStartDate() {
		return this.startDate;
	}
	
	public void setStartDate(Date date) {
		this.startDate=date;
	}

	@Override
	public Date getEndDate() {
		return this.endDate;
	}
	
	public void setEndDate(Date date) {
		this.endDate=date;
	}

}
