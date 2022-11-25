import classes from './TeamRequest.module.css'


const TeamRequest = (props) => {
  const forLabel = "Team Name #" + props.name + ": ";

  const changeHandler = (event) => {
    const name = event.target.value;
    props.onTeamNameChange(props.name, name)
  }



  return (
    <div className = {classes.teamRequest}>
      <label>{forLabel}</label>
      <input type="text" onChange={changeHandler}></input>
    </div>
  );
};

export default TeamRequest;
