fetch("/displayNotes")
    .then(response => response.json())
    .then(notes => {
        console.log(notes);
        notes.forEach(note => {
            console.log(note.messages);
        });
    });

//    fetch("https://data.kcmo.org/resource/frqd-pm5w")
//        .then(response => response.json())
//        .then(data => {
//          data.forEach(item => console.log(item));
//        });