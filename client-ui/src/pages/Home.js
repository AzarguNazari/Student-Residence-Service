import React, { useState } from "react";

import "./Home.css";
import { Redirect } from "react-router-dom";
import { post } from 'axios';
import qs from "qs";
import useAuth from "../context/auth";

const Home = () => {
    const [isLoggedIn, setLoggedIn] = useState(false);
    const [isError, setIsError] = useState(false);
    const [username, setUserName] = useState("");
    const [password, setPassword] = useState("");
    const { setAuthTokens } = useAuth();

    const requestConfig = {
        headers: {
            'Accept': '*/*',
            'Access-Control-Allow-Origin': '*',
            'Authorization': 'Basic YnJvd3Nlcjpicm93c2Vy',
            'Content-Type': 'application/x-www-form-urlencoded'
        }
    };

    const requestBody = {
        username,
        password,
        'grant_type': "password"
    };

    const postLogin = () => {
        post("http://localhost:9852/oauth/token", qs.stringify(requestBody), requestConfig).then(({ data }) => {
            setLoggedIn(true);
            setAuthTokens(data);
        }).catch(e => {
            setIsError(true);
            console.error(e);
        })
    }

    return isLoggedIn ? <Redirect to="/" /> : (
        <div className="App">
            <h2>Student Residence Service 🏠</h2>

            <div className="form">
                <div className="input-container">
                    <input required type="text" onChange={e => {
                        setUserName(e.target.value)
                    }} />
                    <label>Username</label>
                </div>

                <div className="input-container">
                    <input required type="password" onChange={e => {
                        setPassword(e.target.value);
                    }} />
                    <label>Password</label>
                </div>
                <div className="input-container">
                    <button className="login" onClick={postLogin}>Login</button>
                </div>
            </div>
            {isError && <p>The username or password provided were incorrect!</p>}
        </div>
    )
};

export default Home;