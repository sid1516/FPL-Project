package com.example.fpl.response.dto;

import java.util.List;

import com.example.fpl.request.dto.TeamRequestDTO;

public class TeamResponseDTO extends TeamRequestDTO {
	private String teamKey;
	private int numOfGoalkeepers;
	private int numOfDefenders;
	private int numOfMidfielders;
	private int numOfForwards;
	private List<PlayerResponseDTO> players;
	
	private LeagueResponseDTO league;
	
	private int numOfWins;
	
	private int numOfDraws;
	
	private int numOfLosses;
	
	private int standingsPoints;
	
	private int totalPlayerPoints;
	
	private int draftPosition;
	
	private double moneyLeft;

	public TeamResponseDTO() {

	}
	
	public TeamResponseDTO(String teamName, String teamKey, int numOfGoalkeepers, int numOfDefenders,
			int numOfMidfielders, int numOfForwards, List<PlayerResponseDTO> players, LeagueResponseDTO league,
			int numOfWins, int numOfDraws, int numOfLosses, int standingsPoints, int totalPlayerPoints,
			int draftPosition, double moneyLeft) {
		super(teamName);
		this.teamKey = teamKey;
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
		this.draftPosition = draftPosition;
		this.moneyLeft = moneyLeft;
	}

	public String getTeamKey() {
		return teamKey;
	}

	public void setTeamKey(String teamKey) {
		this.teamKey = teamKey;
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

	public List<PlayerResponseDTO> getPlayers() {
		return players;
	}

	public void setPlayers(List<PlayerResponseDTO> players) {
		this.players = players;
	}
	
	public LeagueResponseDTO getLeague() {
		return league;
	}

	public void setLeague(LeagueResponseDTO league) {
		this.league = league;
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

	public double getMoneyLeft() {
		return moneyLeft;
	}

	public void setMoneyLeft(double moneyLeft) {
		this.moneyLeft = moneyLeft;
	}

	@Override
	public String toString() {
		return "TeamResponseDTO [teamKey=" + teamKey + ", numOfGoalkeepers=" + numOfGoalkeepers + ", numOfDefenders="
				+ numOfDefenders + ", numOfMidfielders=" + numOfMidfielders + ", numOfForwards=" + numOfForwards
				+ ", players=" + players + ", league=" + league + ", numOfWins=" + numOfWins + ", numOfDraws="
				+ numOfDraws + ", numOfLosses=" + numOfLosses + ", standingsPoints=" + standingsPoints
				+ ", totalPlayerPoints=" + totalPlayerPoints + ", draftPosition=" + draftPosition + ", moneyLeft="
				+ moneyLeft + ", toString()=" + super.toString() + "]";
	}
}
