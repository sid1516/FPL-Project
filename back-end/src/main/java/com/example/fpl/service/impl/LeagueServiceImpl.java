package com.example.fpl.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fpl.exceptions.FPLBusinessException;
import com.example.fpl.exceptions.FPLResourceNotFoundException;
import com.example.fpl.exceptions.FPLRuntimeException;
import com.example.fpl.model.League;
import com.example.fpl.model.Player;
import com.example.fpl.model.Schedule;
import com.example.fpl.model.Team;
import com.example.fpl.repository.LeagueRepository;
import com.example.fpl.repository.PlayerRepository;
import com.example.fpl.repository.ScheduleRepository;
import com.example.fpl.request.dto.LeagueRequestDTO;
import com.example.fpl.request.dto.TeamRequestDTO;
import com.example.fpl.response.dto.LeagueResponseDTO;
import com.example.fpl.response.dto.TeamResponseDTO;
import com.example.fpl.service.LeagueService;
import com.example.fpl.service.PlayerService;
import com.example.fpl.service.TeamService;
import com.example.fpl.utils.ScheduleUtils;

@Service
public class LeagueServiceImpl implements LeagueService {
	
	@Autowired
	private LeagueRepository leagueRepo;
	
	@Autowired
	private TeamService teamService;
	
	@Autowired
	private PlayerRepository playerRepo;
	
	@Autowired
	private PlayerService playerService;
	
	@Autowired
	private ScheduleRepository scheduleRepo;
	
	@Override
	public List<LeagueResponseDTO> getAllLeagues() {
		List<LeagueResponseDTO> leagues = leagueRepo.findAll().stream().map(l -> l.toDTO()).collect(Collectors.toList());
		return leagues;
	}
	@Override
	public LeagueResponseDTO getLeagueByLeagueKey(String leagueKey) throws FPLResourceNotFoundException {
		League league = leagueRepo.findByLeagueKey(leagueKey);
		if(Objects.isNull(league)) {
			throw new FPLResourceNotFoundException("No league with this key.");
		}
		return league.toDTO();
	}

	@Transactional(rollbackOn = {FPLBusinessException.class, FPLRuntimeException.class})
	@Override
	public LeagueResponseDTO addLeague(LeagueRequestDTO request) throws FPLBusinessException, FPLResourceNotFoundException {
		League league = new League();
		if(CollectionUtils.isEmpty(request.getTeams())) {
			throw new FPLBusinessException("No teams in the league!");
		}
		if(!(request.getTeams().size() == 4 || 
				request.getTeams().size() == 6 || 
				request.getTeams().size() == 8)) {
			throw new FPLBusinessException("You can only have 4, 6, or 8 teams!!");
		}
		mapRequestToEntity(request, league, true);
		leagueRepo.save(league);
		Set<TeamResponseDTO> teamResponses = new HashSet<>();
		int draftPosition = request.getTeams().size();
		for(TeamRequestDTO teamRequest : request.getTeams()) {
			TeamResponseDTO teamResponse = teamService.addTeam(teamRequest, league.getLeagueKey(), draftPosition);
			teamResponse.setLeague(null);
			teamResponses.add(teamResponse);
			draftPosition--;
		}
		LeagueResponseDTO leagueResponse = league.toDTO();
		leagueResponse.setTeamsResponses(teamResponses);
		return leagueResponse;
	}
	
	@Override
	public LeagueResponseDTO draftForLeague(String leagueKey) throws FPLResourceNotFoundException, FPLBusinessException {
		League league = leagueRepo.findByLeagueKey(leagueKey);
		if(Objects.isNull(league)) {
			throw new FPLResourceNotFoundException("No league with that key!");
		}
		draft(league);
		return leagueRepo.save(league).toDTO();
	}

	@Override
	@Transactional(rollbackOn = {FPLBusinessException.class, FPLRuntimeException.class})
	public void deleteLeague(String leagueKey) throws FPLResourceNotFoundException, FPLBusinessException  {
		League league = leagueRepo.findByLeagueKey(leagueKey);
		if(Objects.isNull(league)) {
			throw new FPLResourceNotFoundException("No league with this key.");
		}
		for(Team t : league.getTeams()) {
			 teamService.deleteLeagueFromTeam(t.getTeamKey(), leagueKey);
		}
	}
	@Override
	public LeagueResponseDTO updateLeague(String leagueKey, LeagueRequestDTO request)
			throws FPLResourceNotFoundException, FPLBusinessException {
		League league = leagueRepo.findByLeagueKey(leagueKey);
		mapRequestToEntity(request, league, false);
		return leagueRepo.save(league).toDTO();
	}
	
	private void mapRequestToEntity(LeagueRequestDTO request, League league, boolean isCreate) throws FPLBusinessException {
		if(isCreate) {
			league.setLeagueKey(UUID.randomUUID().toString());
		}
		league.setName(request.getName());
		league.setDescription(request.getDescription());
	}
	
	private void draft(League league) throws FPLBusinessException {
		List<Team> teamsAscendingDraftOrder = league.getTeams().stream()
				.sorted((t1, t2) -> t1.getDraftPosition() - t2.getDraftPosition())
				.collect(Collectors.toList());
		List<Team> teamsDescendingDraftOrder = league.getTeams().stream()
				.sorted((t1, t2) -> t2.getDraftPosition() - t1.getDraftPosition())
				.collect(Collectors.toList());
		List<Player> playersSortedByPoints = playerRepo.findByOrderBySeasonPointsDesc();
		for(int i = 0; i < 15; i++) {
			if(i % 2 == 0) {
				for(int j = 0; j < teamsAscendingDraftOrder.size(); j++) {
					pickPlayer(teamsAscendingDraftOrder.get(j), playersSortedByPoints);
				}
			} else {
				for(int j = 0; j < teamsDescendingDraftOrder.size(); j++) {
					pickPlayer(teamsDescendingDraftOrder.get(j), playersSortedByPoints);
				}
			}
		}

	}
	private void pickPlayer(Team team, List<Player> playersSorted) throws FPLBusinessException {
		int index = 0;
		if(team.getNumOfDefenders() + team.getNumOfForwards() + team.getNumOfMidfielders() + team.getNumOfGoalkeepers() == 15) {
			return;
		}
		while(true) {
			Player player = playersSorted.get(index);
			if(canCreateAssociation(team, player)) {
				playerService.savePlayerToTeam(player.getPlayerKey(), team.getTeamKey());
				team.setMoneyLeft(team.getMoneyLeft() + player.getPrice());
				playersSorted.remove(player);
				return;
			} else {
				index++;
			}
		}
	}
	
	private boolean canCreateAssociation(Team team, Player player) {
		if(Objects.nonNull(player.getTeam())) {
			return false;
		}
		if(player.getPosition().equals("Midfielder")) {
			return team.getNumOfMidfielders() < 5;
		} else if(player.getPosition().equals("Forward")) {
			return team.getNumOfForwards() < 3;
		} else if(player.getPosition().equals("Defender")) {
			return team.getNumOfDefenders() < 5;
		} else if(player.getPosition().equals("Goalkeeper")) {
			return team.getNumOfGoalkeepers() < 2;
		}
		return false;
	}
	@Override
	@Transactional
	public LeagueResponseDTO scheduleGames(String leagueKey) throws FPLResourceNotFoundException {
		League league = leagueRepo.findByLeagueKey(leagueKey);
		if(Objects.isNull(league)) {
			throw new FPLResourceNotFoundException("No league with this key.");
		}
		HashMap<Integer, Team> teamsOrder = new HashMap<>();
		int index = 1;
		for(Team t : league.getTeams()) {
			teamsOrder.put(index, t);
			index++;
		}
		if(teamsOrder.size() == 4) {
			scheduleForFourTeams(league, teamsOrder);
		}
		else if(teamsOrder.size() == 6) {
			scheduleForSixTeams(league, teamsOrder);
		} else if(teamsOrder.size() == 8) {
			scheduleForEightTeams(league, teamsOrder);
		}
		return leagueRepo.save(league).toDTO();
	}
	
	private void scheduleForFourTeams(League league, HashMap<Integer, Team> teamsMap) {
		int gameweek = 1;
		List<List<Integer>> fourSchedule = ScheduleUtils.fourTeamSchedule;
		for(int i = 0; i < 12; i++) {
			for(int j = 0; j < fourSchedule.size(); j++) {
				for(int k = 0; k < 4; k+=2) {
					Team firstTeam = teamsMap.get(fourSchedule.get(j).get(k));
					Team secondTeam = teamsMap.get(fourSchedule.get(j).get(k+1));
					int firstTeamScore = generateScoreForTeam(firstTeam);
					int secondTeamScore = generateScoreForTeam(secondTeam);
					modifyTeamsRecords(firstTeam, secondTeam, firstTeamScore, secondTeamScore);
					String scheduleKey = UUID.randomUUID().toString();
					Schedule schedule = new Schedule(scheduleKey, gameweek, firstTeam, secondTeam, firstTeamScore, secondTeamScore,league);
					scheduleRepo.save(schedule);
				}
				gameweek++;
			}
		}
		for(int j = 0; j < fourSchedule.size() - 1; j++) {
			for(int k = 0; k < 4; k+=2) {
				Team firstTeam = teamsMap.get(fourSchedule.get(j).get(k));
				Team secondTeam = teamsMap.get(fourSchedule.get(j).get(k+1));
				String scheduleKey = UUID.randomUUID().toString();
				int firstTeamScore = generateScoreForTeam(firstTeam);
				int secondTeamScore = generateScoreForTeam(secondTeam);
				modifyTeamsRecords(firstTeam, secondTeam, firstTeamScore, secondTeamScore);
				Schedule schedule = new Schedule(scheduleKey, gameweek, firstTeam, secondTeam, firstTeamScore, secondTeamScore,league);
				scheduleRepo.save(schedule);
			}
			gameweek++;
		}
	}
	private void scheduleForSixTeams(League league, HashMap<Integer, Team> teamsMap) {
		int gameweek = 1;
		List<List<Integer>> sixSchedule = ScheduleUtils.sixTeamSchedule;
		for(int i = 0; i < 7; i++) {
			for(int j = 0; j < sixSchedule.size(); j++) {
				for(int k = 0; k < 6; k+=2) {
					Team firstTeam = teamsMap.get(sixSchedule.get(j).get(k));
					Team secondTeam = teamsMap.get(sixSchedule.get(j).get(k+1));
					int firstTeamScore = generateScoreForTeam(firstTeam);
					int secondTeamScore = generateScoreForTeam(secondTeam);
					modifyTeamsRecords(firstTeam, secondTeam, firstTeamScore, secondTeamScore);
					String scheduleKey = UUID.randomUUID().toString();
					Schedule schedule = new Schedule(scheduleKey, gameweek, firstTeam, secondTeam, firstTeamScore, secondTeamScore,league);
					scheduleRepo.save(schedule);
				}
				gameweek++;
			}
		}
		for(int j = 0; j < sixSchedule.size() - 2; j++) {
			for(int k = 0; k < 6; k+=2) {
				Team firstTeam = teamsMap.get(sixSchedule.get(j).get(k));
				Team secondTeam = teamsMap.get(sixSchedule.get(j).get(k+1));
				int firstTeamScore = generateScoreForTeam(firstTeam);
				int secondTeamScore = generateScoreForTeam(secondTeam);
				modifyTeamsRecords(firstTeam, secondTeam, firstTeamScore, secondTeamScore);
				String scheduleKey = UUID.randomUUID().toString();
				Schedule schedule = new Schedule(scheduleKey, gameweek, firstTeam, secondTeam, firstTeamScore, secondTeamScore,league);
				scheduleRepo.save(schedule);
			}
			gameweek++;
		}
	}
	private void scheduleForEightTeams(League league, HashMap<Integer, Team> teamsMap) {
		int gameweek = 1;
		List<List<Integer>> eightSchedule = ScheduleUtils.eightTeamSchedule;
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < eightSchedule.size(); j++) {
				for(int k = 0; k < 8; k+=2) {
					Team firstTeam = teamsMap.get(eightSchedule.get(j).get(k));
					Team secondTeam = teamsMap.get(eightSchedule.get(j).get(k+1));
					int firstTeamScore = generateScoreForTeam(firstTeam);
					int secondTeamScore = generateScoreForTeam(secondTeam);
					modifyTeamsRecords(firstTeam, secondTeam, firstTeamScore, secondTeamScore);
					String scheduleKey = UUID.randomUUID().toString();
					Schedule schedule = new Schedule(scheduleKey, gameweek, firstTeam, secondTeam, firstTeamScore, secondTeamScore,league);
					scheduleRepo.save(schedule);
				}
				gameweek++;
			}
		}
		for(int j = 0; j < eightSchedule.size() - 4; j++) {
			for(int k = 0; k < 8; k+=2) {
				Team firstTeam = teamsMap.get(eightSchedule.get(j).get(k));
				Team secondTeam = teamsMap.get(eightSchedule.get(j).get(k+1));
				int firstTeamScore = generateScoreForTeam(firstTeam);
				int secondTeamScore = generateScoreForTeam(secondTeam);
				modifyTeamsRecords(firstTeam, secondTeam, firstTeamScore, secondTeamScore);
				String scheduleKey = UUID.randomUUID().toString();
				Schedule schedule = new Schedule(scheduleKey, gameweek, firstTeam, secondTeam, firstTeamScore, secondTeamScore,league);
				scheduleRepo.save(schedule);
			}
			gameweek++;
		}
	}
	
	private int generateScoreForTeam(Team team) {
		int overallScore = 0;
		for(Player p : team.getPlayers()) {
			int lastSeasonPoints = p.getSeasonPoints();
			int multiplier = ThreadLocalRandom.current().nextInt(50, 201);
			overallScore += Math.round((lastSeasonPoints/38) * (multiplier/100));
		}
		return overallScore;
	}
	
	private void modifyTeamsRecords(Team team1, Team team2, int team1Score, int team2Score) {
		if(team1Score > team2Score) {
			team1.setNumOfWins(team1.getNumOfWins()+1);
			team2.setNumOfLosses(team2.getNumOfLosses()+1);
			team1.setStandingsPoints(team1.getStandingsPoints() + 3);
		} else if(team1Score < team2Score) {
			team2.setNumOfWins(team2.getNumOfWins()+1);
			team1.setNumOfLosses(team1.getNumOfLosses()+1);
			team2.setStandingsPoints(team2.getStandingsPoints() + 3);

		} else {
			team1.setNumOfDraws(team1.getNumOfDraws()+1);
			team2.setNumOfDraws(team2.getNumOfDraws()+1);
			team1.setStandingsPoints(team1.getStandingsPoints() + 1);
			team2.setStandingsPoints(team2.getStandingsPoints() + 1);
		}
		team1.setTotalPlayerPoints(team1.getTotalPlayerPoints() + team1Score);
		team2.setTotalPlayerPoints(team2.getTotalPlayerPoints() + team2Score);
		
	}
	@Override
	@Transactional
	public LeagueResponseDTO resetLeague(String leagueKey) throws FPLResourceNotFoundException {
		League league = leagueRepo.findByLeagueKey(leagueKey);
		if(Objects.isNull(league)) {
			throw new FPLResourceNotFoundException("No league with the entered league key");
		}
		scheduleRepo.deleteAll(league.getSchedules());
		league.setSchedules(null);
		int draftPosition = 1;
		for(Team t : league.getTeams()) {
			t.setDraftPosition(draftPosition);
			t.resetFieldsWithLeagueInfo();
			draftPosition++;
		}
		return leagueRepo.save(league).toDTO();
	}
}
