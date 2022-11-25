import React from "react";
import Header from "../components/Header";
import TeamForm from "../components/TeamForm";
import Description from "../components/UI/Description";
const LeagueInfo = () => {
  return (
    <React.Fragment>
      <Header />
      <Description/>
      <TeamForm />
    </React.Fragment>
  );
};

export default LeagueInfo