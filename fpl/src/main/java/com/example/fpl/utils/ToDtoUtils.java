package com.example.fpl.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.util.CollectionUtils;

import com.example.fpl.model.League;
import com.example.fpl.model.Player;
import com.example.fpl.model.Schedule;
import com.example.fpl.model.Team;
import com.example.fpl.response.dto.LeagueResponseDTO;
import com.example.fpl.response.dto.PlayerResponseDTO;
import com.example.fpl.response.dto.ScheduleResponseDTO;
import com.example.fpl.response.dto.TeamResponseDTO;

public class ToDtoUtils {
	public static void playerConversionsInTeam(Team team, TeamResponseDTO teamResponse) {
		if(!CollectionUtils.isEmpty(team.getPlayers())) {
			List<PlayerResponseDTO> playersDTOs = new ArrayList<>();
			team.getPlayers().forEach((p) -> {
				PlayerResponseDTO playerResponse = new PlayerResponseDTO();
				playerResponse.setPlayerKey(p.getPlayerKey());
				playerResponse.setFirstName(p.getFirstName());
				playerResponse.setLastName(p.getLastName());
				playerResponse.setPosition(p.getPosition());
				playerResponse.setSeasonPoints(p.getSeasonPoints());
				playerResponse.setClubTeam(p.getClubTeam());
				playerResponse.setPrice(p.getPrice());
				playersDTOs.add(playerResponse);
			});
			teamResponse.setPlayers(playersDTOs);
		}
	}
	public static void leagueConversionInTeam(Team team, TeamResponseDTO teamResponse) {
		League league = team.getLeague();
		if(Objects.nonNull(league)) {
			LeagueResponseDTO leagueResponse = new LeagueResponseDTO();
			leagueResponse.setName(league.getName());
			leagueResponse.setDescription(league.getDescription());
			leagueResponse.setLeagueKey(league.getLeagueKey());
			teamResponse.setLeague(leagueResponse);
		}
	}
	public static void teamConversionInPlayer(Player player, PlayerResponseDTO playerResponse) {
		Team team = player.getTeam();
		if(Objects.nonNull(team)) {
			TeamResponseDTO response = new TeamResponseDTO();
			setTeamResponseFields(team, response);
			playerResponse.setTeam(response);
		}
	}
	public static void teamConversionInLeague(League league, LeagueResponseDTO leagueResponse) {
		Set<Team> teams = league.getTeams();
		if(!CollectionUtils.isEmpty(teams)) {
			Set<TeamResponseDTO> teamSet = new HashSet<>();
			teams.forEach(t -> {
				TeamResponseDTO tResponse = new TeamResponseDTO();
				setTeamResponseFields(t, tResponse);
				teamSet.add(tResponse);
			});
			leagueResponse.setTeamsResponses(teamSet);
		}
	}
	public static void teamConversionInSchedule(Schedule schedule , ScheduleResponseDTO scheduleResponse) {
		Team team1 = schedule.getTeam1();
		Team team2 = schedule.getTeam2();
		if(Objects.nonNull(team1) && Objects.nonNull(team2)) {
			TeamResponseDTO response1 = new TeamResponseDTO();
			TeamResponseDTO response2 = new TeamResponseDTO();
			setTeamResponseFields(team1, response1);
			setTeamResponseFields(team2, response2);
			scheduleResponse.setTeam1(response1);
			scheduleResponse.setTeam2(response2);
		}
	}
	
	public static void leagueConversionInSchedule(Schedule schedule , ScheduleResponseDTO scheduleResponse) {
		League league = schedule.getLeague();
		LeagueResponseDTO leagueResponse = new LeagueResponseDTO();
		leagueResponse.setName(league.getName());
		leagueResponse.setDescription(league.getDescription());
		leagueResponse.setLeagueKey(league.getLeagueKey());
		scheduleResponse.setLeague(leagueResponse);
	}
	
	private static void setTeamResponseFields(Team t, TeamResponseDTO tResponse) {
		tResponse.setTeamName(t.getTeamName());
		tResponse.setTeamKey(t.getTeamKey());
		tResponse.setNumOfGoalkeepers(t.getNumOfGoalkeepers());
		tResponse.setNumOfDefenders(t.getNumOfDefenders());
		tResponse.setNumOfMidfielders(t.getNumOfMidfielders());
		tResponse.setNumOfForwards(t.getNumOfForwards());
		tResponse.setNumOfWins(t.getNumOfWins());
		tResponse.setNumOfDraws(t.getNumOfDraws());
		tResponse.setNumOfLosses(t.getNumOfLosses());
		tResponse.setStandingsPoints(t.getStandingsPoints());
		tResponse.setTotalPlayerPoints(t.getTotalPlayerPoints());
		tResponse.setDraftPosition(t.getDraftPosition());
		tResponse.setMoneyLeft(t.getMoneyLeft());
	}
}
