package com.example.fpl;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.fpl.model.Player;
import com.example.fpl.repository.PlayerRepository;
import com.example.fpl.repository.TeamRepository;
import com.example.fpl.search.PlayerSpecification;
import com.example.fpl.search.SearchCriteria;

@SpringBootTest
class FplApplicationTests {
	@Autowired
	PlayerRepository playerRepo;
	@Autowired
	TeamRepository teamRepo;

	@Test
	void contextLoads() {
	}
	
	@Test 
	@Transactional
	public void getAllPlayersWithCertainClubTeam() {
		PlayerSpecification spec = new PlayerSpecification(new SearchCriteria("lastName", ":", "Tangri"));
		List<Player> players = playerRepo.findAll(spec);
		players.forEach(p -> System.out.println(p));
		
	}

}
