package com.example.fpl.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.fpl.exceptions.FPLBusinessException;
import com.example.fpl.exceptions.FPLResourceNotFoundException;
import com.example.fpl.model.Player;
import com.example.fpl.model.Team;
import com.example.fpl.repository.PlayerRepository;
import com.example.fpl.repository.TeamRepository;
import com.example.fpl.request.dto.PlayerRequestDTO;
import com.example.fpl.response.dto.PlayerResponseDTO;
import com.example.fpl.service.PlayerService;
import com.example.fpl.utils.CSVHelper;

@Service
public class PlayerServiceImpl implements PlayerService {
	@Autowired
	private PlayerRepository repo;
	
	@Autowired
	private TeamRepository teamRepo;
	
	
	private void mapRequestToEntity(PlayerRequestDTO dto, Player player, boolean isCreate) {
		if(isCreate) {
			player.setPlayerKey(UUID.randomUUID().toString());
		}
		player.setFirstName(dto.getFirstName());
		player.setLastName(dto.getLastName());
		String position = dto.getPosition().toLowerCase();
		if(position.contains("defender") ) {
			player.setPosition("Defender");
		} else if(position.contains("midfielder")) {
			player.setPosition("Midfielder");
		} else if(position.contains("forward")) {
			player.setPosition("Forward");
		} else {
			player.setPosition("Goalkeeper");
		}
		player.setSeasonPoints(dto.getSeasonPoints());
		player.setClubTeam(dto.getClubTeam());
		player.setPrice(dto.getPrice());
	}

	@Override
	public List<PlayerResponseDTO> getAllPlayers() {
		List<PlayerResponseDTO> responseList = new ArrayList<>();
		for(Player p : repo.findAll()) {
			responseList.add(p.toDTO());
		}
		return responseList;
	}

	@Override
	public PlayerResponseDTO getPlayer(String playerKey) throws FPLResourceNotFoundException {
		Player p = repo.findByPlayerKey(playerKey);
		if(Objects.isNull(p)) {
			throw new FPLResourceNotFoundException("No player with this key.");
		}
		return p.toDTO();
	}

	@Override
	public PlayerResponseDTO addPlayer(PlayerRequestDTO request) {
		Player p = new Player();
		mapRequestToEntity(request, p, true);
		return repo.save(p).toDTO();
	}

	@Override
	public void deletePlayer(String playerKey) {
		Player p = repo.findByPlayerKey(playerKey);
		if(Objects.isNull(p)) {
			throw new RuntimeException("Invalid playerKey!!!");
		}
		repo.delete(p);
	}

	@Override
	public PlayerResponseDTO updatePlayer(String playerKey, PlayerRequestDTO request) throws FPLResourceNotFoundException {
		Player p = repo.findByPlayerKey(playerKey);
		if(Objects.isNull(p)) {
			throw new FPLResourceNotFoundException("No player with this key.");
		}
		mapRequestToEntity(request, p, false);
		return repo.save(p).toDTO();

	}

	@Override
	public PlayerResponseDTO savePlayerToTeam(String playerKey, String teamKey) throws FPLBusinessException {
		System.out.println(playerKey);
		System.out.println(teamKey);
		Player p = repo.findByPlayerKey(playerKey);
		Team t = teamRepo.findByTeamKey(teamKey);
		if(p.getPrice() > t.getMoneyLeft()) {
			throw new FPLBusinessException("Not enough money left!");
		}
		p.setTeam(t);
		if(p.getPosition().equals("Midfielder")) {
			if(t.getNumOfMidfielders() == 5) {
				throw new FPLBusinessException("Too many players with this position!");
			}
			t.setNumOfMidfielders(t.getNumOfMidfielders()+1);
		} else if(p.getPosition().equals("Forward")) {
			if(t.getNumOfForwards() == 3) {
				throw new FPLBusinessException("Too many players with this position!");
			}
			t.setNumOfForwards(t.getNumOfForwards()+1);
		} else if(p.getPosition().equals("Defender")) {
			if(t.getNumOfDefenders() == 5) {
				throw new FPLBusinessException("Too many players with this position!");
			}
			t.setNumOfDefenders(t.getNumOfDefenders() + 1);
		} else {
			if(t.getNumOfGoalkeepers() == 2) {
				throw new FPLBusinessException("Too many players with this position!");
			}
			t.setNumOfGoalkeepers(t.getNumOfGoalkeepers()+1);
		}
		t.setMoneyLeft(t.getMoneyLeft() - p.getPrice());
		return repo.save(p).toDTO();
	}
	@Override
	public void removePlayerFromTeam(String playerKey, String teamKey) {
		Player p = repo.findByPlayerKey(playerKey);
		Team t = teamRepo.findByTeamKey(teamKey);
		if(p.getPosition().equals("Midfielder")) {
			t.setNumOfMidfielders(t.getNumOfMidfielders() - 1);
		} else if(p.getPosition().equals("Forward")) {
			t.setNumOfForwards(t.getNumOfForwards() - 1);
		} else if(p.getPosition().equals("Defender")) {
			t.setNumOfDefenders(t.getNumOfDefenders() - 1);
		} else {
			t.setNumOfGoalkeepers(t.getNumOfGoalkeepers() - 1);
		}
		t.setMoneyLeft(t.getMoneyLeft() + p.getPrice());
		p.setTeam(null);
		repo.save(p);
	}

	@Override
	public void bulkSave(MultipartFile file) {
		try {
			List<Player> players = CSVHelper.csvToPlayers(file.getInputStream());
			repo.saveAll(players);

		} catch (IOException e) {
		      throw new RuntimeException("fail to store csv data: " + e.getMessage());
	    }
	}
}
