package com.example.fpl.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.fpl.exceptions.FPLBusinessException;
import com.example.fpl.exceptions.FPLResourceNotFoundException;
import com.example.fpl.request.dto.PlayerRequestDTO;
import com.example.fpl.response.dto.PlayerResponseDTO;
import com.example.fpl.service.PlayerService;
import com.example.fpl.utils.CSVHelper;

@RestController
@RequestMapping("/players")
@CrossOrigin

public class PlayerController {
	@Autowired
	PlayerService service;
	
	
	@PostMapping()
	public PlayerResponseDTO createPlayer(@Valid @RequestBody PlayerRequestDTO request) {
		return service.addPlayer(request);
	}
	
	@GetMapping("/{playerKey}")
	public PlayerResponseDTO getPlayerByPlayerKey(@PathVariable String playerKey) throws FPLResourceNotFoundException {
		return service.getPlayer(playerKey);
	}
	
	@GetMapping()
	public List<PlayerResponseDTO> getPlayers() {
		return service.getAllPlayers();
	}
	@DeleteMapping("/{playerKey}")
	public void deletePlayer(@PathVariable String playerKey) {
		service.deletePlayer(playerKey);
	}
	@PutMapping("{playerKey}")
	public PlayerResponseDTO updatePlayer(@Valid @PathVariable String playerKey, @RequestBody PlayerRequestDTO request) throws FPLResourceNotFoundException {
		return service.updatePlayer(playerKey, request);
	}
	@PutMapping("{playerKey}/teams/{teamKey}")
	public PlayerResponseDTO addTeamToPlayer(@PathVariable String playerKey, @PathVariable String teamKey) throws FPLBusinessException {
		return service.savePlayerToTeam(playerKey, teamKey);
	}
	@DeleteMapping("{playerKey}/teams/{teamKey}")
	public void removeTeamFromPlayer(@PathVariable String playerKey, @PathVariable String teamKey) {
		service.removePlayerFromTeam(playerKey, teamKey);
	}
	@PostMapping("/csv/upload")
	public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
		if(CSVHelper.hasCSVFormat(file)) {
			try {
				service.bulkSave(file);
				return ResponseEntity.ok().build();
			} catch(Exception e) {
				ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Could not upload the file!");
			}
		}
		return null;
	}
	
}
