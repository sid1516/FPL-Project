package com.example.fpl.model;


import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.example.fpl.response.dto.TeamResponseDTO;
import com.example.fpl.utils.ToDtoUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Team {

	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	@Column(name = "team_key")
	private String teamKey;
	@Column(name = "team_name")
	private String teamName;
	@Column(name = "goalkeeper_count")
	private int numOfGoalkeepers;
	@Column(name = "defender_count")
	private int numOfDefenders;
	@Column(name = "midfielder_count")
	private int numOfMidfielders;
	@Column(name = "forward_count")
	private int numOfForwards;
	
	@Column(name = "money_left")
	private double moneyLeft;

	@JsonIgnore
	@OneToMany(mappedBy = "team", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private Set<Player> players;

	@ManyToOne
	@JoinColumn(name = "league_id")
	private League league;

	@Column(name = "wins")
	private int numOfWins;

	@Column(name = "draws")
	private int numOfDraws;

	@Column(name = "losses")
	private int numOfLosses;

	@Column(name = "standing_points")
	private int standingsPoints;

	@Column(name = "player_points")
	private int totalPlayerPoints;
	
	@Column(name = "draft_position")
	private int draftPosition;

	public Team() {

	}

	public Team(String teamKey, String teamName, int numOfGoalkeepers, int numOfDefenders, int numOfMidfielders,
			int numOfForwards, Set<Player> players, League league, int numOfWins, int numOfDraws, int numOfLosses,
			int standingsPoints, int totalPlayerPoints) {
		super();
		this.teamKey = teamKey;
		this.teamName = teamName;
		this.numOfGoalkeepers = numOfGoalkeepers;
		this.numOfDefenders = numOfDefenders;
		this.numOfMidfielders = numOfMidfielders;
		this.numOfForwards = numOfForwards;
		this.players = players;
		this.league = league;
		this.numOfWins = numOfWins;
		this.numOfDraws = numOfDraws;
		this.numOfLosses = numOfLosses;
		this.standingsPoints = standingsPoints;
		this.totalPlayerPoints = totalPlayerPoints;
	}

	public League getLeague() {
		return league;
	}

	public void setLeague(League league) {
		this.league = league;
	}

	public int getId() {
		return Id;
	}

	public String getTeamKey() {
		return teamKey;
	}

	public void setTeamKey(String teamKey) {
		this.teamKey = teamKey;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public int getNumOfGoalkeepers() {
		return numOfGoalkeepers;
	}

	public void setNumOfGoalkeepers(int numOfGoalkeepers) {
		this.numOfGoalkeepers = numOfGoalkeepers;
	}

	public int getNumOfMidfielders() {
		return numOfMidfielders;
	}

	public void setNumOfMidfielders(int numOfMidfielders) {
		this.numOfMidfielders = numOfMidfielders;
	}

	public int getNumOfForwards() {
		return numOfForwards;
	}

	public void setNumOfForwards(int numOfForwards) {
		this.numOfForwards = numOfForwards;
	}

	public int getNumOfDefenders() {
		return numOfDefenders;
	}

	public void setNumOfDefenders(int numOfDefenders) {
		this.numOfDefenders = numOfDefenders;
	}

	public Set<Player> getPlayers() {
		return players;
	}

	public void setPlayers(Set<Player> players) {
		this.players = players;
	}

	public double getMoneyLeft() {
		return moneyLeft;
	}

	public void setMoneyLeft(double moneyLeft) {
		this.moneyLeft = moneyLeft;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Team [teamKey=").append(teamKey).append(", teamName=").append(teamName)
				.append(", numOfGoalkeepers=").append(numOfGoalkeepers).append(", numOfDefenders=")
				.append(numOfDefenders).append(", numOfMidfielders=").append(numOfMidfielders)
				.append(", numOfForwards=").append(numOfForwards).append(", players=").append(players)
				.append(", league=").append(league).append(", numOfWins=").append(numOfWins).append(", numOfDraws=")
				.append(numOfDraws).append(", numOfLosses=").append(numOfLosses).append(", standingsPoints=")
				.append(standingsPoints).append(", totalPlayerPoints=").append(totalPlayerPoints)
				.append(", draftPosition=").append(draftPosition).append("]");
		return builder.toString();
	}

	public int getNumOfWins() {
		return numOfWins;
	}

	public void setNumOfWins(int numOfWins) {
		this.numOfWins = numOfWins;
	}

	public int getNumOfDraws() {
		return numOfDraws;
	}

	public void setNumOfDraws(int numOfDraws) {
		this.numOfDraws = numOfDraws;
	}

	public int getNumOfLosses() {
		return numOfLosses;
	}

	public void setNumOfLosses(int numOfLosses) {
		this.numOfLosses = numOfLosses;
	}

	public int getStandingsPoints() {
		return standingsPoints;
	}

	public void setStandingsPoints(int standingsPoints) {
		this.standingsPoints = standingsPoints;
	}

	public int getTotalPlayerPoints() {
		return totalPlayerPoints;
	}

	public void setTotalPlayerPoints(int totalPlayerPoints) {
		this.totalPlayerPoints = totalPlayerPoints;
	}

	public int getDraftPosition() {
		return draftPosition;
	}

	public void setDraftPosition(int draftPosition) {
		this.draftPosition = draftPosition;
	}

	public TeamResponseDTO toDTO() {
		TeamResponseDTO response = new TeamResponseDTO();
		response.setTeamName(teamName);
		response.setTeamKey(teamKey);
		response.setNumOfGoalkeepers(numOfGoalkeepers);
		response.setNumOfDefenders(numOfDefenders);
		response.setNumOfMidfielders(numOfMidfielders);
		response.setNumOfForwards(numOfForwards);
		ToDtoUtils.playerConversionsInTeam(this, response);
		ToDtoUtils.leagueConversionInTeam(this, response);
		response.setNumOfWins(numOfWins);
		response.setNumOfDraws(numOfDraws);
		response.setNumOfLosses(numOfLosses);
		response.setStandingsPoints(standingsPoints);
		response.setTotalPlayerPoints(totalPlayerPoints);
		response.setDraftPosition(draftPosition);
		return response;
	}

	public void resetFieldsWithLeagueInfo() {
		setNumOfGoalkeepers(0);
		setNumOfDefenders(0);
		setNumOfMidfielders(0);
		setNumOfForwards(0);
		setNumOfWins(0);
		setNumOfDraws(0);
		setNumOfLosses(0);
		setStandingsPoints(0);
		setTotalPlayerPoints(0);
		setMoneyLeft(100.0);
		for (Player p : players) {
			p.setTeam(null);
		}
	}
}
