const URL = 'http://localhost:8081';


const loginUser = (e) => {
    e.preventDefault();
    const formData = new FormData(e.target);
    const entry = {};
    entry['username'] = formData.get('username');
    entry['password'] = formData.get('password');

    fetch(`${URL}/login`, {
    method: 'POST',
        headers: {
        'Content-Type': 'application/json'
    },
    body:
        JSON.stringify(entry)
}).then((result)=> {
    window.localStorage.setItem('auth',result.headers.get("Authorization"));
});
};