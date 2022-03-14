fetch("/displayNotes")
    .then(response => response.json())
    .then(notes => {
        console.log(notes);
        notes.forEach(note => {
            console.log(note.messages);
        });
    });

let app = document.querySelector("#animatedLogo");

let typewriter = new Typewriter(app, {
    loop: true
});

typewriter.typeString('<strong>THE UNSENT PROJECT</strong>')
    .pauseFor(2500)
    .deleteAll()
    .start();

function enableBtt() {
    let url = "http://localhost:8080/AddNote.html";
    location.href = url;
}

