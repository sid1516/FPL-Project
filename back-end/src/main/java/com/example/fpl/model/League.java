package com.example.fpl.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.example.fpl.response.dto.LeagueResponseDTO;
import com.example.fpl.utils.ToDtoUtils;

@Entity
public class League {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "league_name")
	private String name;
	
	@Column(name = "league_description")
	private String description;
	
	@Column(name = "league_key")
	private String leagueKey;
	
	@OneToMany(mappedBy = "league", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private Set<Team> teams;
	
	@OneToMany(mappedBy = "league", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	private Set<Schedule> schedules;
	
	public String getLeagueKey() {
		return leagueKey;
	}
	public void setLeagueKey(String leagueKey) {
		this.leagueKey = leagueKey;
	}
	public Set<Team> getTeams() {
		return teams;
	}
	public void setTeams(Set<Team> teams) {
		this.teams = teams;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getId() {
		return id;
	}
	public Set<Schedule> getSchedules() {
		return schedules;
	}
	public void setSchedules(Set<Schedule> schedules) {
		this.schedules = schedules;
	}
	public LeagueResponseDTO toDTO() {
		LeagueResponseDTO response = new LeagueResponseDTO();
		response.setName(name);
		response.setDescription(description);
		response.setLeagueKey(leagueKey);
		ToDtoUtils.teamConversionInLeague(this, response);
		return response;
	}
}
