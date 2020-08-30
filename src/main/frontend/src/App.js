import React from 'react';
import {
  BrowserRouter as Router,
  Route,
} from 'react-router-dom';
import PollPage from './components/PollPage';
import LandingPage from './components/LandingPage';
import PollDetail from './components/PollDetail';
import PollDashBoard from './components/PollDashBoard'

const App = () => (
  <Router>
    <div>
      <Route exact path="/polls/:id" component={PollDetail} />
      <Route exact path="/polls/:id/dashboard" component={PollDashBoard} />
      <Route exact path="/polls" component={PollPage} />
      <Route exact path="/" component={LandingPage} />
    </div>
  </Router>
);



export default App;
