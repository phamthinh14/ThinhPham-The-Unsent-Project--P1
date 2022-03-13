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
//    fetch("https://data.kcmo.org/resource/frqd-pm5w")
//        .then(response => response.json())
//        .then(data => {
//          data.forEach(item => console.log(item));
//        });