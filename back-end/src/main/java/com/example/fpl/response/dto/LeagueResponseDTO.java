package com.example.fpl.response.dto;

import java.util.Set;

import com.example.fpl.request.dto.LeagueRequestDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LeagueResponseDTO extends LeagueRequestDTO {

	private static final long serialVersionUID = 3537983487562495144L;

	private String leagueKey;
	
	@JsonProperty(value = "teams")
	private Set<TeamResponseDTO> teamResponses;

	public LeagueResponseDTO() {

	}

	public LeagueResponseDTO(String name, String description, String leagueKey, Set<TeamResponseDTO> teamResponses) {
		super(name, description);
		this.leagueKey = leagueKey;
		this.teamResponses = teamResponses;
	}

	public String getLeagueKey() {
		return leagueKey;
	}

	public void setLeagueKey(String leagueKey) {
		this.leagueKey = leagueKey;
	}
	
	
	@JsonProperty(value = "teams")
	public Set<TeamResponseDTO> getTeamResponses() {
		return teamResponses;
	}

	@JsonProperty(value = "teams")
	public void setTeamsResponses(Set<TeamResponseDTO> teamResponses) {
		this.teamResponses = teamResponses;
	}

	@Override
	public String toString() {
		return "LeagueResponseDTO [leagueKey=" + leagueKey + ", teams=" + teamResponses + ", toString()=" + super.toString()
				+ "]";
	}

}
