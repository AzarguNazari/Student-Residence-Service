const LOCAL_STORAGE = {
    setToken: (tokenObj, role) => {
        localStorage.setItem('tokens', JSON.stringify(tokenObj));
        localStorage.setItem('role', role);
    },
    getAccessToken: () => {
        const tokens = JSON.parse(localStorage.getItem('tokens'));
        return tokens ? tokens['access_token'] : null;
    },
    getRefreshToken: () => JSON.parse(localStorage.getItem('tokens'))['refresh_token'],
    clearToken: () => {
        localStorage.clear()
    },
    getStudentId: () => localStorage.getItem('student_id'),
    getRole: () => localStorage.getItem('role')
}

export default LOCAL_STORAGE;