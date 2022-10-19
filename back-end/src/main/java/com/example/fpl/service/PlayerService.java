package com.example.fpl.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.fpl.exceptions.FPLBusinessException;
import com.example.fpl.exceptions.FPLResourceNotFoundException;
import com.example.fpl.request.dto.PlayerRequestDTO;
import com.example.fpl.response.dto.PlayerResponseDTO;

public interface PlayerService {

	public List<PlayerResponseDTO> getAllPlayers();
	public PlayerResponseDTO getPlayer(String playerKey) throws FPLResourceNotFoundException;
	public PlayerResponseDTO addPlayer(PlayerRequestDTO request);
	public void deletePlayer(String playerKey);
	public PlayerResponseDTO updatePlayer(String playerKey, PlayerRequestDTO p) throws FPLResourceNotFoundException;
	public PlayerResponseDTO savePlayerToTeam(String playerKey, String teamKey) throws FPLBusinessException;
	public void removePlayerFromTeam(String playerKey, String teamKey);
	public void bulkSave(MultipartFile file);
}
