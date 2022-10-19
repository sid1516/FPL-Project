package com.example.fpl.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fpl.exceptions.FPLBusinessException;
import com.example.fpl.exceptions.FPLResourceNotFoundException;
import com.example.fpl.model.League;
import com.example.fpl.model.Team;
import com.example.fpl.repository.LeagueRepository;
import com.example.fpl.repository.TeamRepository;
import com.example.fpl.request.dto.TeamRequestDTO;
import com.example.fpl.response.dto.TeamResponseDTO;
import com.example.fpl.service.PlayerService;
import com.example.fpl.service.TeamService;

@Service
public class TeamServiceImpl implements TeamService {
	@Autowired
	private TeamRepository repo;
	
	@Autowired
	private PlayerService playerService;
	
	@Autowired
	private LeagueRepository leagueRepo;
	
	@Override
	public List<TeamResponseDTO> getAllTeams() {
		List<TeamResponseDTO> responseList = new ArrayList<>();
		for (Team t : repo.findAll()) {
			responseList.add(t.toDTO());
		}
		return responseList;
	}

	@Override
	public TeamResponseDTO getTeambyTeamKey(String teamKey) throws FPLResourceNotFoundException {
		Team team = repo.findByTeamKey(teamKey);
		if(Objects.isNull(team)) {
			throw new FPLResourceNotFoundException("No team with this key.");
		}
		return team.toDTO();
	}

	@Override
	public TeamResponseDTO addTeam(TeamRequestDTO request) {
		return addTeam(request, null, null);
	}
	
	@Override
	public TeamResponseDTO addTeam(TeamRequestDTO request, String leagueKey, Integer draftPosition) {
		Team team = new Team();
		mapRequestToEntity(request, team, true);
		if(StringUtils.isNotEmpty(leagueKey)) {
			team.setLeague(leagueRepo.findByLeagueKey(leagueKey));
		}
		if(draftPosition != null) {
			team.setDraftPosition(draftPosition);
		}
		return repo.saveAndFlush(team).toDTO();
	}
	@Override
	public TeamResponseDTO updateTeam(String teamKey, TeamRequestDTO request) throws FPLResourceNotFoundException {
		Team team = repo.findByTeamKey(teamKey);
		if(Objects.isNull(team)) {
			throw new FPLResourceNotFoundException("No team with this key.");
		}
		mapRequestToEntity(request, team, false);
		return repo.save(team).toDTO();
	}

	@Override
	public void deleteTeam(String teamKey) throws FPLResourceNotFoundException {
		Team team = repo.findByTeamKey(teamKey);
		if(Objects.isNull(team)) {
			throw new FPLResourceNotFoundException("No team with this key.");
		}
		team.getPlayers().forEach(p -> playerService.removePlayerFromTeam(p.getPlayerKey(), teamKey));
		repo.delete(team);
	}
	
	private void mapRequestToEntity(TeamRequestDTO request, Team team, boolean isCreate) {
		if(isCreate) {
			team.setTeamKey(UUID.randomUUID().toString());
			team.setNumOfGoalkeepers(0);
			team.setNumOfDefenders(0);
			team.setNumOfMidfielders(0);
			team.setNumOfForwards(0);
			team.setNumOfWins(0);
			team.setNumOfDraws(0);
			team.setNumOfLosses(0);
			team.setStandingsPoints(0);
			team.setTotalPlayerPoints(0);
			team.setMoneyLeft(100.0);
		}
		team.setTeamName(request.getTeamName());
	}

	@Override
	public TeamResponseDTO saveLeagueToTeam(String teamKey, String leagueKey) throws FPLBusinessException {
		League l = leagueRepo.findByLeagueKey(leagueKey);
		Team t = repo.findByTeamKey(teamKey);
		if(Objects.nonNull(t.getLeague())) {
			throw new FPLBusinessException("This team is already in a league!!");
		}
		t.setLeague(l);
		return repo.save(t).toDTO();
	}

	@Override
	public void deleteLeagueFromTeam(String teamKey, String leagueKey) throws FPLBusinessException {
		Team t = repo.findByTeamKey(teamKey);
		if(Objects.isNull(t.getLeague())) {
			throw new FPLBusinessException("This team isn't in a league!!");
		}
		t.setLeague(null);
		repo.save(t);
	}

}
