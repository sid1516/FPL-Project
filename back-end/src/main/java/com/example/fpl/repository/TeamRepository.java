package com.example.fpl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fpl.model.Team;

public interface TeamRepository extends JpaRepository<Team, Integer> {
	public Team findByTeamKey(String teamKey);
	public List<Team> findByLeagueId(int leagueId);
}
