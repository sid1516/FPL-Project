package com.example.fpl.request.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class TeamRequestDTO {
	
	@Size(min = 1, max=255, message = "Your team name must be between 1 and 255 characters!")
	@NotBlank
	private String teamName;
	public TeamRequestDTO() {
		
	}

	public TeamRequestDTO(String teamName) {
		super();
		this.teamName = teamName;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	@Override
	public String toString() {
		return "TeamRequestDTO [teamName=" + teamName + "]";
	}
	
}
