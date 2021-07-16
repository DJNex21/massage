const URL = 'http://localhost:8081';
let entries = [];
let updateEntry = {
    update: false,
    id: null
};

const dateAndTimeToDate = (dateString, timeString) => {
    return new Date(`${dateString}T${timeString}`).toISOString();
};

const saveEntry = (e) => {
    if (updateEntry.update) {
        editEntries(e);
    } else {
        createEntry(e);
    }
};

const createEntry = (e) => {
    e.preventDefault();
    const formData = new FormData(e.target);
    const entry = {};
    entry['checkIn'] = dateAndTimeToDate(formData.get('checkInDate'), formData.get('checkInTime'));

    fetch(`${URL}/entries`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(entry)
    }).then((result) => {
        result.json().then((entry) => {
            entries.push(entry);
            renderEntries();
        });
    });
};

const removeEntries = (id) =>{
    fetch(`${URL}/entries`, {
        method: 'DELETE',
        headers:{
            'Content-Type':'plain/text'
        },
        body: id
    }).then((result) => {
        indexEntries();
    });
}

const editEntries = (e) => {
    e.preventDefault();
    const formData = new FormData(e.target);
    const entry = {};
    entry['checkIn'] = dateAndTimeToDate(formData.get('checkInDate'), formData.get('checkInTime'));
    entry.id = updateEntry.id;
    fetch(`${URL}/entries`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(entry)
    }).then((result) => {
        indexEntries();
        updateEntry.id = null;
        updateEntry.update = false;
    });
}

const indexEntries = () => {
    fetch(`${URL}/entries`, {
        method: 'GET'
    }).then((result) => {
        result.json().then((result) => {
            entries = result;
            renderEntries();
        });
    });
    renderEntries();
};

const createCell = (text) => {
    const cell = document.createElement('td');
    cell.innerText = text;
    return cell;
};

const createDeleteButton = (entry) =>{
    let btn = document.createElement("button");
    btn.innerHTML = "Delete";
    btn.addEventListener("click", () => removeEntries(entry.id))
    return btn
}

const createEditButton = (entry) =>{
    let editbtn = document.createElement("button");
    editbtn.innerHTML = "Edit";
    editbtn.addEventListener("click", function () {
        const checkIn = entry.checkIn.split('T')
        let checkInDate = document.getElementsByName("checkInDate");
        let checkInTime = document.getElementsByName("checkInTime");
        entry.getElementsByName('massageType')

        checkInDate.item(0).value = checkIn[0]
        checkInTime.item(0).value = checkIn[1]

        updateEntry.update = true;
        updateEntry.id = entry.id;

    })
    return editbtn;
}

const renderEntries = () => {
    const display = document.querySelector('#entryDisplay');
    display.innerHTML = '';
    entries.forEach((entry) => {
        const row = document.createElement('tr');

        row.appendChild(createCell(entry.id));
        row.appendChild(createCell(new Date(entry.checkIn).toLocaleString()));
        row.appendChild(createCell(entry.massageType))
        row.appendChild(createCell(entry.user));
        //row.appendChild(createCell(entry.massageur));
        row.appendChild(createDeleteButton(entry));
        row.appendChild(createEditButton(entry));

        display.appendChild(row);
    });
};


document.addEventListener('DOMContentLoaded', function(){
    const createEntryForm = document.querySelector('#createEntryForm');
    createEntryForm.addEventListener('submit', saveEntry);
    indexEntries();
});