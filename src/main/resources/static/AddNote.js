function getRandomInt(max) {
  return Math.floor(Math.random() * max);
}

let arr = [];
arr.push(getRandomInt(31415789));
arr.push(getRandomInt(31415789));
arr.push(getRandomInt(31415789));
arr.push(getRandomInt(31415789));
arr.push(getRandomInt(31415789));
const shuffleArray = (arr) => arr.sort(() => 0.5 - Math.random());
//console.log(arr[0]);


let myString = "my name is\n yes myString";
//console.log(myString);

function submitFunction() {
  let note = {
    "id": arr[0],
    "senderName": document.querySelector("#senderName").value,
    "receiverName": document.querySelector("#receiverName").value,
    "messages": document.querySelector("#messages").value
  }
  console.log(note);

  fetch("/addNotes", {
    method: "post",
    headers: {
      "Accept": "application/json",
      "Content-Type": "application/json"
    },
    body: JSON.stringify(note)
  }).then(result => {
    if (result.status != 200) {
      throw new Error("Bad Server Response");
    }
    console.log(result.text());
    let url = "http://localhost:8080/Display.html";
    location.href = url;
  }).catch(error => console.log(error));
}

function enableBtt() {
  let url = "http://localhost:8080/Display.html";
  location.href = url;
}

