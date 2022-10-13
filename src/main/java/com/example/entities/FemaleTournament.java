package com.example.entities;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@DiscriminatorValue(value = "FEMALE_TOURNAMENT")
public class FemaleTournament extends Tournament<FemaleTenisPlayer>{

	public FemaleTournament() {
		this.type="Female tournament";
	}
	
	public FemaleTournament(ArrayList<FemaleTenisPlayer>list) {
		super(list);
	}
	
	@Override
	public List<FemaleTenisPlayer> getParticipants() {
		return this.participants;
	}


	@Override
	protected int comparePlayers(FemaleTenisPlayer player1, FemaleTenisPlayer player2) {
		return Comparator.comparing(FemaleTenisPlayer::getHabilityLevel).thenComparing(FemaleTenisPlayer::getReactionTime).compare(player1,
				player2);
	}

	@Override
	public FemaleTenisPlayer startTournament(List<FemaleTenisPlayer> list) {
		if (list.size() == 1) {
			return list.get(0);
		} else {
			FemaleTenisPlayer player1 = list.remove(list.size()-1);
			FemaleTenisPlayer player2 = list.remove(list.size()-1);
			int comp = comparePlayers(player1, player2);
			if (comp > 0) {
				list.add(player1);
			} else if (comp < 0) {
				list.add(player2);
			} else {
				list.add(player1);
			}
			FemaleTenisPlayer winner=startTournament(list);	
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
	
	public void setParticipants(List<FemaleTenisPlayer> participantsList) {
		this.participants = participantsList;
	}

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
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
