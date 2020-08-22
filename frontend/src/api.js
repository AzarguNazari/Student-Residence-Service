import axios from "axios";
import qs from "qs";

import LOCAL_STORAGE from "./local-storage";

let api = axios.create({
    baseUrl: `http://localhost:9852`,
    paramsSerializer: (params) => qs.stringify(params, { indices: false })
});

api.interceptors.request.use((config) => {
    const token = LOCAL_STORAGE.getAccessToken();

    const headers = {
        ...config.headers,
        'Accept': 'application/json',
        'Authorization': 'Bearer ' + token,
        'Content-Type': 'application/json'
      };
    if (token) {
        config.headers = headers;
    }
    return config;
}, error => {
    console.log('what the fuck is the error', error);
    const originalRequest = error.config;

    const requestConfig = {
        headers: {
            'Accept': '*/*',
            'Access-Control-Allow-Origin': '*',
            'Authorization': 'Basic YnJvd3Nlcjpicm93c2Vy',
            'Content-Type': 'application/x-www-form-urlencoded'
        }
    };

    if (error.response.status === 401 && !originalRequest._retry) {
        originalRequest._retry = true;
        const refreshToken = LOCAL_STORAGE.getRefreshToken();
        const requestBody = {
            "refresh_token": refreshToken,
            "grant_type": "refresh_token"
        };
        return axios.post('http://localhost:9852/oauth/token', qs.stringify(requestBody), requestConfig)
            .then(({ data }) => {
                
                LOCAL_STORAGE.setToken(data);

                const newReq = {
                    ...originalRequest,
                    headers: {
                        ...originalRequest.headers,
                        "Authorization": "Bearer " + LOCAL_STORAGE.getAccessToken()
                    }
                }                
                return axios(newReq);
            })
    }
    return Promise.reject(error);
});


api.interceptors.response.use((response) => {
    return response;
}, (error) => {
    const originalRequest = error.config;

    const requestConfig = {
        headers: {
            'Accept': '*/*',
            'Access-Control-Allow-Origin': '*',
            'Authorization': 'Basic YnJvd3Nlcjpicm93c2Vy',
            'Content-Type': 'application/x-www-form-urlencoded'
        }
    };

    if (error.response.status === 401 && !originalRequest._retry) {
        originalRequest._retry = true;
        const refreshToken = LOCAL_STORAGE.getRefreshToken();
        const requestBody = {
            "refresh_token": refreshToken,
            "grant_type": "refresh_token"
        };
        return axios.post('http://localhost:9852/oauth/token', qs.stringify(requestBody), requestConfig)
            .then(({ data }) => {
                
                LOCAL_STORAGE.setToken(data);

                const newReq = {
                    ...originalRequest,
                    headers: {
                        ...originalRequest.headers,
                        "Authorization": "Bearer " + LOCAL_STORAGE.getAccessToken()
                    }
                }                
                return axios(newReq);
            })
    }
    return Promise.reject(error);
});

export default api;