package com.example.fpl.request.dto;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.example.fpl.customvalidators.ValueOfEnum;
import com.example.fpl.utils.Position;

public class PlayerRequestDTO implements Serializable {

	private static final long serialVersionUID = -243260495275138056L;
	@Size(min = 1, max = 64, message = "Player's first name must be between 1 and 64 characters!")
	@NotBlank
	private String firstName;
	@Size(min = 1, max = 64, message = "Player's last name must be between 1 and 64 characters!")
	@NotBlank
	private String lastName;
	@ValueOfEnum(enumClass = Position.class, message = "Position must contain Forward, Midfielder, Goalkeeper, or Defender.")
	private String position;
	@Min(value = 0, message = "Player's price must be at least 0!")
	@Max(value = 15, message = "Player's price must not exceed 15!")
	private double price;
	@Min(value = 0, message = "Player's last season points must be at least 0!")
	private int seasonPoints;
	@Size(min = 1, max = 255, message = "Player's club team must be between 1 and 255 characters!")
	@NotBlank
	private String clubTeam;

	public PlayerRequestDTO() {

	}

	public PlayerRequestDTO(
			@Size(min = 1, max = 64, message = "Player's first name must be between 1 and 64 characters!") @NotBlank String firstName,
			@Size(min = 1, max = 64, message = "Player's last name must be between 1 and 64 characters!") @NotBlank String lastName,
			@ValueOfEnum(enumClass = Position.class, message = "Position must contain Forward, Midfielder, Goalkeeper, or Defender.") String position,
			@Min(value = 0, message = "Player's price must be at least 0!") @Max(value = 15, message = "Player's price must not exceed 15!") double price,
			@Min(value = 0, message = "Player's last season points must be at least 0!") int seasonPoints,
			@Size(min = 1, max = 255, message = "Player's club team must be between 1 and 255 characters!") @NotBlank String clubTeam) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.position = position;
		this.price = price;
		this.seasonPoints = seasonPoints;
		this.clubTeam = clubTeam;
	}


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public int getSeasonPoints() {
		return seasonPoints;
	}

	public void setSeasonPoints(int seasonPoints) {
		this.seasonPoints = seasonPoints;
	}

	public String getClubTeam() {
		return clubTeam;
	}

	public void setClubTeam(String clubTeam) {
		this.clubTeam = clubTeam;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "PlayerRequestDTO [firstName=" + firstName + ", lastName=" + lastName + ", position=" + position
				+ ", price=" + price + ", seasonPoints=" + seasonPoints + ", clubTeam=" + clubTeam + "]";
	}
	

}
