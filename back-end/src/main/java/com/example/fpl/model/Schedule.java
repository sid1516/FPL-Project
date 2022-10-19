package com.example.fpl.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.example.fpl.response.dto.ScheduleResponseDTO;
import com.example.fpl.utils.ToDtoUtils;

@Entity
public class Schedule {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int id;
	
	@Column(name = "schedule_key")
	private String scheduleKey;
	
	@Column(name = "gameweek")
	private int gameWeek;
	
	@ManyToOne
	@JoinColumn(name = "team1_id")
	private Team team1;
	@ManyToOne
	@JoinColumn(name = "team2_id")
	private Team team2;
	
	@Column(name = "team1_score")
	private int team1Score;
	
	@Column(name = "team2_score")
	private int team2Score;
	
	@ManyToOne
	@JoinColumn(name = "league_id")
	private League league;
	
	public Schedule() {
		
	}

	public Schedule(String scheduleKey, int gameWeek, Team team1, Team team2, int team1Score, int team2Score,
			League league) {
		super();
		this.scheduleKey = scheduleKey;
		this.gameWeek = gameWeek;
		this.team1 = team1;
		this.team2 = team2;
		this.team1Score = team1Score;
		this.team2Score = team2Score;
		this.league = league;
	}



	public String getScheduleKey() {
		return scheduleKey;
	}
	public void setScheduleKey(String scheduleKey) {
		this.scheduleKey = scheduleKey;
	}
	public int getGameWeek() {
		return gameWeek;
	}
	public void setGameWeek(int gameWeek) {
		this.gameWeek = gameWeek;
	}
	public Team getTeam1() {
		return team1;
	}
	public void setTeam1(Team team1) {
		this.team1 = team1;
	}
	public Team getTeam2() {
		return team2;
	}
	public void setTeam2(Team team2) {
		this.team2 = team2;
	}
	public League getLeague() {
		return league;
	}
	public void setLeague(League league) {
		this.league = league;
	}

	public int getTeam1Score() {
		return team1Score;
	}

	public void setTeam1Score(int team1Score) {
		this.team1Score = team1Score;
	}

	public int getTeam2Score() {
		return team2Score;
	}

	public void setTeam2Score(int team2Score) {
		this.team2Score = team2Score;
	}
	
	public ScheduleResponseDTO toDTO() {
		ScheduleResponseDTO response = new ScheduleResponseDTO();
		response.setScheduleKey(scheduleKey);
		response.setGameWeek(gameWeek);
		response.setTeam1Score(team1Score);
		response.setTeam2Score(team2Score);
		ToDtoUtils.teamConversionInSchedule(this, response);
		ToDtoUtils.leagueConversionInSchedule(this, response);
		return response;
	}
	
}
