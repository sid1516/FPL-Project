import classes from './Description.module.css'

const Description = () => {
  return (
    <div className={classes.description}>
      <h1>Description</h1>
      <p>
        This is a simulator of the FPL draft. You will chose a team name and
        then select the number of CPU teams you would like to go against. This
        options are an additional 3, 5, or 7 teams. You will then draft your
        team choosing 2 goalkeepers, 5 defenders, 5 midfielders, and 3 forwards.
        A head to head 38 game premier league season will then be simulated and
        the standings will then be outputed.
      </p>
    </div>
  );
};
export default Description;
