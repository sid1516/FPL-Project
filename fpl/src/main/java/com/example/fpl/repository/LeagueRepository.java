package com.example.fpl.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fpl.model.League;

public interface LeagueRepository extends JpaRepository<League, Integer> {
	public League findByLeagueKey(String leagueKey);
}
