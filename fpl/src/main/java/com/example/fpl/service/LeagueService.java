package com.example.fpl.service;

import java.util.List;

import com.example.fpl.exceptions.FPLBusinessException;
import com.example.fpl.exceptions.FPLResourceNotFoundException;
import com.example.fpl.request.dto.LeagueRequestDTO;
import com.example.fpl.response.dto.LeagueResponseDTO;

public interface LeagueService {
	public List<LeagueResponseDTO> getAllLeagues();
	public LeagueResponseDTO getLeagueByLeagueKey(String leagueKey) throws FPLResourceNotFoundException;
	public LeagueResponseDTO addLeague(LeagueRequestDTO request) throws FPLBusinessException, FPLResourceNotFoundException;
	public void deleteLeague(String leagueKey) throws FPLResourceNotFoundException, FPLBusinessException;
	public LeagueResponseDTO updateLeague(String leagueKey, LeagueRequestDTO request) throws FPLResourceNotFoundException, FPLBusinessException;
	public LeagueResponseDTO draftForLeague(String leagueKey) throws FPLResourceNotFoundException, FPLBusinessException;
	public LeagueResponseDTO scheduleGames(String leagueKey) throws FPLResourceNotFoundException;
	public LeagueResponseDTO resetLeague(String leagueKey) throws FPLResourceNotFoundException;
}
