const PlayersList = ({ players, searchedName, draft }) => {
  return (
    <tbody>
      {players
        .filter((player) => {
          if (searchedName.trim() === "") {
            return player;
          } else {
            if (
              player.lastName
                .toLowerCase()
                .includes(searchedName.toLowerCase())
            ) {
              return player;
            }
          }
        })
        .map((player) => (
          <tr key={player.playerKey}>
            <td>{player.firstName}</td>
            <td>{player.lastName}</td>
            <td>{player.price}</td>
            <td>{player.seasonPoints}</td>
            <td>{player.position}</td>
            <td>{player.clubTeam}</td>
            <td>
              {" "}
              <button
                onClick={(event) => {
                  event.preventDefault();
                  draft(player);
                }}
              >
                Sign
              </button>
            </td>
          </tr>
        ))}
    </tbody>
  );
};
export default PlayersList;
