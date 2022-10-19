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
import com.example.fpl.request.dto.LeagueRequestDTO;
import com.example.fpl.response.dto.LeagueResponseDTO;
import com.example.fpl.service.LeagueService;

@RestController
@RequestMapping("/league")
@CrossOrigin
public class LeagueController {
	@Autowired
	LeagueService service;
	
	@GetMapping()
	public List<LeagueResponseDTO> getAllLeagues() {
		return service.getAllLeagues();
	}
	@GetMapping("/{leagueKey}")
	public LeagueResponseDTO getLeague(@PathVariable String leagueKey) throws FPLResourceNotFoundException {
		return service.getLeagueByLeagueKey(leagueKey);
	}
	@PostMapping()
	public LeagueResponseDTO addLeague(@Valid @RequestBody LeagueRequestDTO request) throws FPLBusinessException, FPLResourceNotFoundException {
		return service.addLeague(request);
	}
	@PutMapping("/{leagueKey}")
	public LeagueResponseDTO updateLeague(@RequestBody LeagueRequestDTO request, @PathVariable String leagueKey) throws FPLResourceNotFoundException, FPLBusinessException {
		return service.updateLeague(leagueKey, request);
	} 
	@DeleteMapping("/{leagueKey}")
	public void deleteLeague(@PathVariable String teamKey) throws FPLResourceNotFoundException, FPLBusinessException {
		service.deleteLeague(teamKey);
	}
	@PutMapping("/{leagueKey}/draft")
	public LeagueResponseDTO draftForLeague(@PathVariable String leagueKey) throws FPLResourceNotFoundException, FPLBusinessException {
		return service.draftForLeague(leagueKey);
	}
	@PutMapping("/{leagueKey}/schedule")
	public LeagueResponseDTO createLeagueSchedule(@PathVariable String leagueKey) throws FPLResourceNotFoundException {
		return service.scheduleGames(leagueKey);
	}
	@PutMapping("/{leagueKey}/reset")
	public LeagueResponseDTO resetLeague(@PathVariable String leagueKey) throws FPLResourceNotFoundException {
		return service.resetLeague(leagueKey);
				
	}
	
}
