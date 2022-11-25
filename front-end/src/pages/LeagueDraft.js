import { useEffect, useState} from "react";
import { useParams, useHistory } from "react-router-dom";
import PlayersList from "../components/PlayersList";
import Modal from "../components/UI/Modal.js";
import classes from "./LeagueDraft.module.css";
import PlayerTables from "../components/UI/PlayerTables";
import Header from "../components/Header";

const LeagueDraft = () => {
  const { leagueKey } = useParams();
  const [players, setPlayers] = useState([]);
  const [nameQuery, setNameQuery] = useState("");
  const [modalIsOpen, setModalIsOpen] = useState(false);
  const [userKeepers, setUserKeepers] = useState([]);
  const [userDefenders, setUserDefenders] = useState([]);
  const [userMidfielders, setUserMidfielders] = useState([]);
  const [userForwards, setUserForwards] = useState([]);
  const [userMoneyLeft, setUserMoneyLeft] = useState();
  const [userTeamKey, setUserTeamKey] = useState();
  const [error, setError] = useState("");
  const [val, setValue] = useState(0);
  const history = useHistory();

  useEffect(() => {
    setPlayers(JSON.parse(localStorage.getItem("players")));
    setUserTeamKey(localStorage.getItem("teamKey"));
    const moneyLeft = JSON.parse(localStorage.getItem("moneyLeft"));
    moneyLeft.toFixed(1)
    setUserMoneyLeft(moneyLeft);
    setUserKeepers(JSON.parse(localStorage.getItem("Keepers")));
    setUserDefenders(JSON.parse(localStorage.getItem("Defenders")));
    setUserMidfielders(JSON.parse(localStorage.getItem("Midfielders")));
    setUserForwards(JSON.parse(localStorage.getItem("Forwards")));
  }, [setPlayers, val]);

  const searchPlayerChangeHandler = (event) => {
    setNameQuery(event.target.value);
  };

  const modalChangeHandler = () => {
    setModalIsOpen((prev) => !prev);
  };

  // async function draftPlayerToTeam(teamKey, playerKey) {
  //   const response = await fetch(
  //     `http://localhost:8080/players/${playerKey}/teams/${teamKey}`,
  //     {
  //       method: "PUT",
  //     }
  //   );
  //   if (!response.ok) {
  //     throw new Error(
  //       "Can't draft another player of this position or this player costs too much money!"
  //     );
  //   }
  // }

  async function draftPlayerToTeamHandler(player) {
    setError(null);
    const playerKey = player.playerKey;
    try {
      const response = await fetch(
        `http://localhost:8080/players/${playerKey}/teams/${userTeamKey}`,
        {
          method: "PUT",
        }
      );
      if (!response.ok) {
        setModalIsOpen(true);
        throw new Error(
          "Can't draft another player of this position or this player costs too much money!"
        );
      }
      const removePlayerWithId = players.filter(
        (player) => player.playerKey !== playerKey
      );
      localStorage.setItem("players", JSON.stringify(removePlayerWithId));
      setPlayers(removePlayerWithId);
      addPlayerToList(player);
      const moneyLeft = userMoneyLeft - player.price;
      localStorage.setItem("moneyLeft", moneyLeft);
      setValue((prev) => prev + 1);
    } catch (error) {
      setError(error.message);
    }
  }

  const addPlayerToList = (player) => {
    const playerPosition = player.position;
    if (playerPosition === "Goalkeeper") {
      const newKeepers = [...userKeepers, player];
      localStorage.setItem("Keepers", JSON.stringify(newKeepers));
    } else if (playerPosition === "Defender") {
      const newDefenders = [...userDefenders, player];
      localStorage.setItem("Defenders", JSON.stringify(newDefenders));
    } else if (playerPosition === "Midfielder") {
      const newMidfielders = [...userMidfielders, player];
      localStorage.setItem("Midfielders", JSON.stringify(newMidfielders));
    } else {
      const newForwards = [...userForwards, player];
      localStorage.setItem("Forwards", JSON.stringify(newForwards));
    }
  };

  async function submitDraftHandler() {
    console.log("handling")
    const response = await fetch(
      `http://localhost:8080/league/${leagueKey}/draft`,
      {
        method: "PUT",
      }
    );
    const data = await response.json()
    console.log(data)
    if (response.ok) {
      console.log("entered");
      await fetch(`http://localhost:8080/league/${leagueKey}/schedule`, {
        method: "PUT",
      });
    }
    history.replace(`/league/${leagueKey}/results`);
  }
  return (
    <div>
      <Header/>
      {modalIsOpen && <Modal changeModal={modalChangeHandler}></Modal>}
      <div className={classes.draft}>
      <h1>League Draft</h1>
        <div className = {classes.inputMoney}>
        <input
          type="text"
          placeholder="Search..."
          onChange={searchPlayerChangeHandler}
        />
        <h2>Money Left: {userMoneyLeft}</h2>
        </div>
        <div className={classes.container}>
          <div className={classes.table1}>
            <table>
              <thead>
                <tr>
                  <th>First Name</th>
                  <th>Last Name</th>
                  <th>Price</th>
                  <th>Previous Season Points</th>
                  <th>Player Position</th>
                  <th>Club Team</th>
                  <th>Sign</th>
                </tr>
              </thead>
              <PlayersList
                players={players}
                searchedName={nameQuery}
                draft={draftPlayerToTeamHandler}
              />
            </table>
          </div>
          <PlayerTables
            defenders={userDefenders}
            goalkeepers={userKeepers}
            midfielders={userMidfielders}
            forwards={userForwards}
          />
        </div>
      </div>
      <div className={classes.buttoncontainer}>
        <button
          className={classes.button}
          disabled={
            userDefenders.length +
              userForwards.length +
              userKeepers.length +
              userMidfielders.length !==
            15
          }
          onClick={submitDraftHandler}
        >
          Submit Draft
        </button>
      </div>
    </div>
  );
};

export default LeagueDraft;
