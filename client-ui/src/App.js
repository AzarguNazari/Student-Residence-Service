import React, { useState } from 'react';
import { Router } from "react-router-dom";
import { Route, Switch } from "react-router";
import get from "lodash/get";
import jwt_decode from "jwt-decode";

import Home from './pages/Home';
import Admin from './pages/Admin';
import PrivateRoute from './PrivateRoute';
import { AuthContext } from "./context/auth";
import history from "./context/history";
import LOCAL_STORAGE from './local-storage';
import Student from './pages/Student';
import StudentRental from './pages/StudentRental';
import ActiveRents from './pages/ActiveRents';
import TerminatedRents from './pages/TerminatedRents';
import Announcements from './pages/Announcements';

const App = () => {
  const [authTokens, setAuthTokens] = useState();

  const setTokens = (data) => {
    const decodedAccessToken = data && jwt_decode(data['access_token']);
    const role = get(decodedAccessToken, ["authorities", "0"], "ROLE_ADMIN");
    setAuthTokens(data);
    LOCAL_STORAGE.setToken(data, role);
    if(role === "ROLE_ADMIN") {
      history.push("/admin");
    } else {
      localStorage.setItem("student_id", get(decodedAccessToken, ["student_id"], null));
      history.push("/student");
    }
  }

  return (
    <AuthContext.Provider value={{authTokens, setAuthTokens: setTokens}}>
      <Router history={history}>
        <Switch>
          <Route exact path="/" component={Home} />
          <PrivateRoute path="/admin" component={Admin} />
          <PrivateRoute path="/student" component={Student} />
          <PrivateRoute path="/rentals" component={StudentRental} />
          <PrivateRoute path="/admin-active-rents" component={ActiveRents} />
          <PrivateRoute path="/admin-terminated-rents" component={TerminatedRents} />
          <PrivateRoute path="/announcements" component={Announcements} />
        </Switch>
      </Router>
    </AuthContext.Provider>
  )
}

export default App;
