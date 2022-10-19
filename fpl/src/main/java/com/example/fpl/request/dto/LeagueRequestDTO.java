package com.example.fpl.request.dto;

import java.io.Serializable;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LeagueRequestDTO implements Serializable {
	
	private static final long serialVersionUID = 7320751324979800430L;

	@Size(min = 1, max = 64, message = "League's name must be between 1 and 64 characters!")
	@NotBlank
	private String name;
	
	@Size(min = 1, max = 255, message = "League description must be between 1 and 255 characters!")
	@NotBlank
	private String description;
	
	private Set<TeamRequestDTO> teams;
	
	public LeagueRequestDTO() {
		
	}
	
	public LeagueRequestDTO(String name, String description) {
		super();
		this.name = name;
		this.description = description;
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

	public Set<TeamRequestDTO> getTeams() {
		return teams;
	}

	public void setTeams(Set<TeamRequestDTO> teams) {
		this.teams = teams;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LeagueRequestDTO [name=").append(name).append(", description=").append(description)
				.append(", teams=").append(teams).append("]");
		return builder.toString();
	}
	
	
	
	
	
}
