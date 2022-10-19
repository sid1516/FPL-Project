package com.example.fpl.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.example.fpl.response.dto.PlayerResponseDTO;
import com.example.fpl.utils.ToDtoUtils;

@Entity
public class Player {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	@Column(name="player_key")
	private String playerKey;
	@Column(name="first_name")
	private String firstName;
	@Column(name="last_name")
	private String lastName;
	private String position;
	private double price;
	@Column(name="prev_season_points")
	private int seasonPoints;
	@Column(name="club_team")
	private String clubTeam;
	@ManyToOne
	@JoinColumn(name="team_id")
	private Team team;
	
	public Player() {

	}
	
	
	public Player(String playerKey, String firstName, String lastName, String position, double price, int seasonPoints,
			String clubTeam) {
		super();
		this.playerKey = playerKey;
		this.firstName = firstName;
		this.lastName = lastName;
		this.position = position;
		this.price = price;
		this.seasonPoints = seasonPoints;
		this.clubTeam = clubTeam;
	}



	public int getId() {
		return Id;
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

	public String getPlayerKey() {
		return playerKey;
	}

	public void setPlayerKey(String playerKey) {
		this.playerKey = playerKey;
	}
	
	

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}
	
	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Player [Id=" + Id + ", playerKey=" + playerKey + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", position=" + position + ", seasonPoints=" + seasonPoints + ", clubTeam=" + clubTeam + "]";
	}

	public PlayerResponseDTO toDTO() {
		PlayerResponseDTO dto = new PlayerResponseDTO();
		dto.setPlayerKey(playerKey);
		dto.setFirstName(firstName);
		dto.setLastName(lastName);
		dto.setPosition(position);
		dto.setSeasonPoints(seasonPoints);
		dto.setClubTeam(clubTeam);
		dto.setPrice(price);
		ToDtoUtils.teamConversionInPlayer(this, dto);
		return dto;
	}
}
