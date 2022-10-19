package com.example.fpl.response.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.example.fpl.customvalidators.ValueOfEnum;
import com.example.fpl.request.dto.PlayerRequestDTO;
import com.example.fpl.utils.Position;

public class PlayerResponseDTO extends PlayerRequestDTO {

	private static final long serialVersionUID = -4247110106351601390L;
	private String playerKey;
	private TeamResponseDTO team;

	public PlayerResponseDTO() {

	}

	public PlayerResponseDTO(
			@Size(min = 1, max = 64, message = "Player's first name must be between 1 and 64 characters!") @NotBlank String firstName,
			@Size(min = 1, max = 64, message = "Player's last name must be between 1 and 64 characters!") @NotBlank String lastName,
			@ValueOfEnum(enumClass = Position.class, message = "Position must contain Forward, Midfielder, Goalkeeper, or Defender.") String position,
			@Min(value = 0, message = "Player's last season points must be at least 0!") @Min(value = 0, message = "Player's price must be at least 0!") @Max(value = 15, message = "Player's price must not exceed 15!") int price,
			int seasonPoints,
			@Size(min = 1, max = 255, message = "Player's club team must be between 1 and 255 characters!") @NotBlank String clubTeam,
			String playerKey) {
		super(firstName, lastName, position, price, seasonPoints, clubTeam);
		this.playerKey = playerKey;
	}

	public String getPlayerKey() {
		return playerKey;
	}

	public void setPlayerKey(String playerKey) {
		this.playerKey = playerKey;
	}

	public TeamResponseDTO getTeam() {
		return team;
	}

	public void setTeam(TeamResponseDTO team) {
		this.team = team;
	}

	@Override
	public String toString() {
		return "PlayerResponseDTO [playerKey=" + playerKey + ", toString()=" + super.toString() + "]";
	}

}
