import React, { useState, useRef } from "react";
import TeamRequest from "./TeamRequest";
import { useHistory } from "react-router-dom";

import classes from "./TeamForm.module.css";
import { Button } from "react-bootstrap";

const defaultArr = [
  {
    id: 1,
    value: "",
  },
  {
    id: 2,
    value: "",
  },
  {
    id: 3,
    value: "",
  },
  {
    id: 4,
    value: "",
  },
  {
    id: 5,
    value: "",
  },
  {
    id: 6,
    value: "",
  },
  {
    id: 7,
    value: "",
  },
];

const TeamForm = () => {
  const [numberOfTeams, setNumOfTeams] = useState(3);
  const numberOfTeamsChangeHandler = (event) => {
    setNumOfTeams(event.target.value);
  };

  const history = useHistory();

  const leagueName = useRef("");
  const leagueDescription = useRef("");
  const playerTeamName = useRef("");
  const [otherTeamNames, setOtherTeamNames] = useState(defaultArr);

  const teamNameChangeHandler = (identification, currValue) => {
    const updatedTeamNames = otherTeamNames.filter(
      (name) => name.id !== identification
    );
    const updatedTeam = {
      id: identification,
      value: currValue,
    };
    const allTeamNames = [updatedTeam, ...updatedTeamNames];
    setOtherTeamNames(allTeamNames);
  };

  const teamRequests = [];

  for (let i = 1; i <= numberOfTeams; i++) {
    teamRequests.push(
      <TeamRequest key={i} name={i} onTeamNameChange={teamNameChangeHandler} />
    );
  }
  const submitHandler = (event) => {
    event.preventDefault();
    const teamsInBounds = otherTeamNames.filter(
      (team) => team.id <= numberOfTeams
    );
    setOtherTeamNames(defaultArr);
    const userTeamName = playerTeamName.current.value;
    const justTeamNames = teamsInBounds.map((team) => team.value);
    justTeamNames.push(userTeamName);

    const nameOfLeague = leagueName.current.value;
    const descriptionOfLeague = leagueDescription.current.value;
    const jsonForLeague = {
      name: nameOfLeague,
      description: descriptionOfLeague,
      teams: justTeamNames,
    };
    addLeagueHandler(jsonForLeague, userTeamName);
  };
  async function addLeagueHandler(league, userTeamName) {
    const response = await fetch("http://localhost:8080/league", {
      method: "POST",
      body: JSON.stringify(league),
      headers: {
        "Content-Type": "application/json",
      },
    });
    const data = await response.json();
    const userTeam = data.teams.find((team) => {
      return team.teamName.trim() === userTeamName.trim();
    });
    const loadPlayers = await fetch("http://localhost:8080/players");
    const players = await loadPlayers.json();
    players.sort((p1, p2) => p2.seasonPoints - p1.seasonPoints);
    const userTeamKey = userTeam.teamKey;
    const lKey = data.leagueKey;
    const keepers = []
    const defenders = []
    const midfielders = []
    const forwards = []
    localStorage.setItem("players", JSON.stringify(players));
    localStorage.setItem("teamKey", userTeamKey);
    localStorage.setItem("moneyLeft", 100.0);
    localStorage.setItem("Keepers", JSON.stringify(keepers));
    localStorage.setItem("Defenders", JSON.stringify(defenders));
    localStorage.setItem("Midfielders", JSON.stringify(midfielders));
    localStorage.setItem("Forwards", JSON.stringify(forwards));
    history.replace(`/league/${lKey}/draft`);
  }

  return (
    <div className={classes["team-form"]}>
      <div className={classes.title}>
        <h1>League Information and Team Selection</h1>
      </div>
      <form onSubmit={submitHandler}>
        <div>
          <label>League Name</label>
          <input type="text" ref={leagueName}></input>
        </div>
        <div>
          <label>League Description</label>
          <input type="text" ref={leagueDescription}></input>
        </div>
        <div className={classes["my-team"]}>
          <label>Your Team Name: </label>
          <input type="text" ref={playerTeamName}></input>
        </div>
        <div>
          <label>Number of Additional Teams</label>
          <select name="additional-teams" onChange={numberOfTeamsChangeHandler}>
            <option>3</option>
            <option>5</option>
            <option>7</option>
          </select>
        </div>
        <div>{teamRequests}</div>
        <Button type="submit">Submit Information</Button>
      </form>
    </div>
  );
};
export default TeamForm;
