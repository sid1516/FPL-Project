package com.example.fpl.response.dto;

public class ScheduleResponseDTO {
	private String scheduleKey;
	private int gameWeek;
	private TeamResponseDTO team1;
	private TeamResponseDTO team2;
	private int team1Score;
	private int team2Score;
	private LeagueResponseDTO league;
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
	public TeamResponseDTO getTeam1() {
		return team1;
	}
	public void setTeam1(TeamResponseDTO team1) {
		this.team1 = team1;
	}
	public TeamResponseDTO getTeam2() {
		return team2;
	}
	public void setTeam2(TeamResponseDTO team2) {
		this.team2 = team2;
	}
	public LeagueResponseDTO getLeague() {
		return league;
	}
	public void setLeague(LeagueResponseDTO league) {
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
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ScheduleResponseDTO [scheduleKey=").append(scheduleKey).append(", gameWeek=").append(gameWeek)
				.append(", team1=").append(team1).append(", team2=").append(team2).append(", league=").append(league)
				.append("]");
		return builder.toString();
	}
}
