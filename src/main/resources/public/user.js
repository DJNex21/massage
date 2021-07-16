const URL = 'http://localhost:8081';
let users = [];
let updateUser = {
    update: false,
    id: null
};

const createUser = (appUser) => {
    appUser.username = formData.get('username'), formData.get('password');
    fetch(`${URL}/users/sign-up`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(appUser)
    }).then((result) => {
        result.json().then((appUser) => {
            users.push(appUser);
            //removeUser();
            loginUser(appUser);
        });
    });
};
const loginUser = (appUser) => {
    fetch(`${URL}/login`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(appUser)
    }).then((result) => {
        window.localStorage.setItem('auth',result.headers.get("Authorization"));
    });
};

const saveUser = (appUser) => {
    if (updateUser.update) {
        editUser(appUser);
    } else {
        createUser(appUser);
    }
};
const removeUser = (id) => {
    fetch(`${URL}/users`, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'plain/text'
        },
        body: id
    }).then((result) => {
        indexUsers();
    });
}

const editUser = (appUser) => {
    appUser.username = formData.get('username'), formData.get('password');
    appUser.id = updateUser.id;
    fetch(`${URL}/users`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(appUser)
    }).then((result) => {
        indexUsers();
        updateUser.id = null;
        updateUser.update = false;
    });
}
const indexUsers = () => {
    fetch(`${URL}/users`, {
        method: 'GET'
    }).then((result) => {
        result.json().then((result) => {
            users = result;
            renderUser();
        });
    });
    renderUser();
};

const createCell = (text) => {
    const cell = document.createElement('td');
    cell.innerText = text;
    return cell;
};

const createDeleteButton = (user) =>{
    let btn = document.createElement("button");
    btn.innerHTML = "Delete";
    btn.addEventListener("click", () => removeUser(user.id))
    return btn
}

const createEditButton = (appUser) =>{
    let editbtn = document.createElement("button");
    editbtn.innerHTML = "Edit";
    editbtn.addEventListener("click", function () {
        const usrname = appUser.username.split('T')
        const pswrd = appUser.password.split('T')


        let username = document.getElementsByName("username");
        let password = document.getElementsByName("password");


        username.item(0).value = usrname[0]
        password.item(0).value = pswrd[0]

        updateUser.update = true;
        updateUser.id = appUser.id;

    })
    return editbtn;
}

const renderUser = () => {
    const display = document.querySelector('#userDisplay');
    display.innerHTML = '';
    users.forEach((appUser) => {
        const row = document.createElement('tr');

        row.appendChild(createCell(appUser.id));
        row.appendChild(createCell(appUser.user));
        //row.appendChild(createCell(appUser.contract));
        row.appendChild(createDeleteButton(appUser));
        row.appendChild(createEditButton(appUser));

        display.appendChild(row);
    });
};


document.addEventListener('DOMContentLoaded', function(){
    //document.getElementById("submit").addEventListener('submit',saveUser)
    const createUserForm = document.querySelector('#createUserForm');
    createUserForm.addEventListener('submit', saveUser);
    indexUsers();
});