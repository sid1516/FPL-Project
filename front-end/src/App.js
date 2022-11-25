import { Route, Switch, Redirect } from "react-router-dom";
import LeagueInfo from "./pages/LeagueInfo";
import LeagueDraft from "./pages/LeagueDraft";
import DisplayResults from "./pages/DisplayResults";

function App() {
  return (
    <Switch>
      <Route path="/" exact>
        <Redirect to="/league" />
      </Route>
      <Route path="/league" exact>
        <LeagueInfo />
      </Route>
      <Route path="/league/:leagueKey/draft">
        <LeagueDraft />
      </Route>
      <Route path="/league/:leagueKey/results">
        <DisplayResults />
      </Route>
    </Switch>
  );
}

export default App;
