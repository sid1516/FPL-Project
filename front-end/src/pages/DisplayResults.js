import { useEffect, useState } from "react";
import { useParams } from "react-router";
import Header from "../components/Header";
import classes from './DisplayResults.module.css'
const DisplayResults = () => {
  const [teams, setTeams] = useState([]);
  const {leagueKey} = useParams();
  useEffect(() => {
    async function fetchData() {
      const response = await fetch(
        `http://localhost:8080/league/${leagueKey}`
      );
      const data = await response.json();
      const dataTeams = data.teams;
      dataTeams.sort((t1, t2) => t2.standingsPoints - t1.standingsPoints);
      setTeams(dataTeams);
    }
    fetchData();
  }, [leagueKey]);

  return (
    <>
    <Header/>
      <h1 className= {classes.results}>Results</h1>
      <table className = {classes.table}>
        <thead>
          <tr>
            <th>Team Name</th>
            <th>Wins </th>
            <th>Ties</th>
            <th>Losses</th>
            <th>Standings Points</th>
            <th>Total Team Points</th>
          </tr>
        </thead>
        <tbody>
          {teams.map((team) => {
            return (
              <tr key={team.teamKey}>
                <th>{team.teamName}</th>
                <th>{team.numOfWins}</th>
                <th>{team.numOfDraws}</th>
                <th>{team.numOfLosses}</th>
                <th>{team.standingsPoints}</th>
                <th>{team.totalPlayerPoints}</th>
              </tr>
            );
          })}
        </tbody>
      </table>
    </>
  );
};

export default DisplayResults;
