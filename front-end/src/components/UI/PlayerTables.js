import React from "react";
import classes from "./PlayerTables.module.css";

const PlayerTables = ({ defenders, goalkeepers, midfielders, forwards }) => {
  const lGoalkeepers = goalkeepers.map((goalkeeper) => (
    <li key = {goalkeeper.playerKey} >{goalkeeper.lastName}</li>
  ));
  var gkCount = 0
  while (lGoalkeepers.length !== 2) {
    lGoalkeepers.push(<li key = {`Goalkeeper${gkCount}`}></li>);
    gkCount++
  }
  const lDefenders = defenders.map((defender) => <li key = {defender.playerKet}>{defender.lastName}</li>);
  var defCount = 0
  while (lDefenders.length !== 5) {
    lDefenders.push(<li key = {`Defender${defCount}`}></li>);
    defCount++
  }
  const lMidfielders = midfielders.map((midfielder) => (
    <li key = {midfielder.playerKey}>{midfielder.lastName}</li>
  ));
  var midCount = 0
  while (lMidfielders.length !== 5) {
    lMidfielders.push(<li key={`Midfielder${midCount}`}></li>);
    midCount++
  }
  const lForwards = forwards.map((forward) => <li key = {forward.playerKey}>{forward.lastName}</li>);
  var forwardCount = 0
  while (lForwards.length !== 3) {
    lForwards.push(<li key = {`Forward${forwardCount}`}></li>);
    forwardCount++
  }
  return (
    <div className={classes.tables}>
      <ul className={classes.playersList}>
        <li>
          Goalkeepers
          <ul>{lGoalkeepers}</ul>
        </li>
        <li>
          Defenders
          <ul>{lDefenders}</ul>
        </li>
        <li>
          Midfielders
          <ul>{lMidfielders}</ul>
        </li>
        <li>
          Forwards
          <ul>{lForwards}</ul>
        </li>
      </ul>
    </div>
  );
};

export default PlayerTables;
