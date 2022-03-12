fetch("/displayNotes")
    .then(response => response.json())
    .then(notes => {
        console.log(notes);
        notes.forEach(note => {
            console.log(note.messages);
        });
    });