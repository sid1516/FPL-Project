package com.example.fpl.service;

import java.util.List;

import com.example.fpl.exceptions.FPLBusinessException;
import com.example.fpl.exceptions.FPLResourceNotFoundException;
import com.example.fpl.request.dto.TeamRequestDTO;
import com.example.fpl.response.dto.TeamResponseDTO;

public interface TeamService {
	public List<TeamResponseDTO> getAllTeams();
	public TeamResponseDTO getTeambyTeamKey(String teamKey) throws FPLResourceNotFoundException;
	public TeamResponseDTO addTeam(TeamRequestDTO request);
	public TeamResponseDTO updateTeam(String teamKey, TeamRequestDTO request) throws FPLResourceNotFoundException;
	public void deleteTeam(String teamKey) throws FPLResourceNotFoundException;
	public TeamResponseDTO saveLeagueToTeam(String teamKey, String leagueKey) throws FPLBusinessException;
	public void deleteLeagueFromTeam(String teamKey, String leagueKey) throws FPLBusinessException;
	TeamResponseDTO addTeam(TeamRequestDTO request, String leagueKey, Integer draftPosition);

}
