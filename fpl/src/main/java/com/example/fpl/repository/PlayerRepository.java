package com.example.fpl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.fpl.model.Player;

public interface PlayerRepository extends JpaRepository<Player, Integer>, JpaSpecificationExecutor<Player> {
	public Player findByPlayerKey(String playerKey);
	List<Player> findByOrderBySeasonPointsDesc();
}
