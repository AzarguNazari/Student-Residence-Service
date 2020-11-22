import React from 'react';
import { Route, Redirect } from 'react-router-dom';
import LOCAL_STORAGE from './local-storage';

const PrivateRoute = ({ component: Component, ...rest }) => {
    const hasToken = LOCAL_STORAGE.getAccessToken();

    return (
        <Route
            {...rest}
            render={props =>
                hasToken ? (
                    <Component {...props} />
                ) : (
                        <Redirect to="/" />
                    )
            }
        />
    );
}

export default PrivateRoute;