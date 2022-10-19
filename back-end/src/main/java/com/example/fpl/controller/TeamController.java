package com.example.fpl.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fpl.exceptions.FPLBusinessException;
import com.example.fpl.exceptions.FPLResourceNotFoundException;
import com.example.fpl.request.dto.TeamRequestDTO;
import com.example.fpl.response.dto.TeamResponseDTO;
import com.example.fpl.service.TeamService;

@RestController
@RequestMapping("/teams")
@CrossOrigin

public class TeamController {
	@Autowired
	TeamService service;
	
	@GetMapping()
	public List<TeamResponseDTO> getAllTeams() {
		return service.getAllTeams();
	}
	@GetMapping("/{teamKey}")
	public TeamResponseDTO getTeam(@PathVariable String teamKey) throws FPLResourceNotFoundException {
		return service.getTeambyTeamKey(teamKey);
	}
	@PostMapping()
	public TeamResponseDTO addTeam(@Valid @RequestBody TeamRequestDTO request) {
		return service.addTeam(request);
	}
	@PutMapping("/{teamKey}")
	public TeamResponseDTO updateTeam(@RequestBody TeamRequestDTO request, @PathVariable String teamKey) throws FPLResourceNotFoundException {
		return service.updateTeam(teamKey, request);
	} 
	@DeleteMapping("/{teamKey}")
	public void deleteTeam(@PathVariable String teamKey) throws FPLResourceNotFoundException {
		service.deleteTeam(teamKey);
	}
	@PutMapping("/{teamKey}/league/{leagueKey}")
	public TeamResponseDTO addLeagueToTeam(@PathVariable String teamKey, @PathVariable String leagueKey) throws FPLBusinessException {
		return service.saveLeagueToTeam(teamKey, leagueKey);
	}
	@DeleteMapping("/{teamKey}/league/{leagueKey}")
	public void removeLeagueFromTeam(@PathVariable String teamKey, @PathVariable String leagueKey) throws FPLBusinessException {
		service.deleteLeagueFromTeam(teamKey, leagueKey);
	}
}
