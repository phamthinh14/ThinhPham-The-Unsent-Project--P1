fetch("/displayNotes")
    .then(response => response.json())
    .then(notes => {
        console.log(notes);
        notes.forEach(note => {
            console.log(note.messages);

            const encoded = encodeURI(`To: ${note.receiverName} \n Messages: ${note.messages} \n From: ${note.senderName}`);
            let cardDeck = document.querySelector(".card-columns");
            cardDeck.insertAdjacentHTML("beforeend",
                `<div class="card w-auto h-auto">
                 <div class="flip-card">
                    <div class="flip-card-inner">
                        <div class="flip-card-front">
                        <img src="https://api.qrserver.com/v1/create-qr-code/?data=${encoded}&size=200x200" alt="Avatar" style="width:200px;height:200px;" />
                        </div>
                        <div class="flip-card-back">
                            <h1>To ${note.receiverName}</h1>
                            <p>${note.messages}</p>
                            <p>From ${note.senderName}</p>
                        </div>
                    </div>
                </div>
            </div>`
            );

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

function searchBtt() {
    let url = "http://localhost:8080/SearchName.html";
    location.href = url;
}
