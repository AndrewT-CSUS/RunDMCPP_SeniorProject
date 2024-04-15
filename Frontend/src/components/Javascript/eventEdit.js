const missingFieldError = "You seem to be missing a field. Please supply all data."
const missingNameError = "Missing Event Name"
const missingLocationError = "Missing Location"
const missingDescError = "Missing Event Description"
const missingDateTimeError = "Missing Date/Time"
const invalidInputError = "The event you tried to add is invald. Try something else"
const transactionFailError = "The server could not complete the add. Try again later"
const eventUpdated = "Event Edited!"
const eventDeleteConfirmation = "Warning! You are about to delete the selected event.\nThis can not be undone, but the event can be re-created manually.\nAre you sure you want to do this?" //Bad, reword later?
const eventDeleted = "Event Deleted!"

var ValEvent;

export function createPreview() {
    const event = createEvent();
    event.id = ValEvent.id;
    if (!validateEvent(event)) {
        return;
    }
    showResults(event);
    ValEvent = event;
}


function createEvent() {
    var dateTime = document.getElementById("editEventDateTime").value
    const event = {
        "name": document.getElementById("editNameBox").value,
        "eventDescription": document.getElementById("editDescriptionBox").value,
        "eventLocation": document.getElementById("editLocationBox").value,
        "dateTime": dateTime
    }
    console.log(event)
    return event;
}

function showResults(event) {
    document.getElementById("resultTitle").innerText = event.name;
    document.getElementById("resultDescription").innerText = event.eventDescription;
    document.getElementById("resultLocation").innerText = event.eventLocation;

    let printDate = new Date(Date.parse(event.dateTime)) 
    document.getElementById("resultDateTime").innerText = printDate.toDateString() + ", " + printDate.toLocaleTimeString();

    document.getElementById("previewField").hidden = false;
}

export async function editEvent(accessToken){
    var request = new XMLHttpRequest();
    var url = "https://sacglorychurch.org:8080/api/events/edit";

    request.open("PUT", (url));
    request.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    request.setRequestHeader("Authorization", "Bearer " + accessToken);
    request.send(JSON.stringify(ValEvent));
    request.onload = () => {
        if (request.status === 200) {
            var requestResult = JSON.parse(request.response);
            console.log(requestResult);
            showResults(requestResult);
            alert(eventUpdated);
        } else if (request.status === 500) {
            var requestResult = JSON.parse(request.response);
            console.log(requestResult);
            if(requestResult.code == 3){
                console.log(requestResult.message)
                alert(invalidInputError)
            }            
            if(requestResult.code == 4){
                console.log(requestResult.message)
                alert(transactionFailError)
            }
        } else if (request.status === 404) {
            alert("Something went REALLY wrong. Try again later, maybe?")
        }
    }
}

function validateEvent(event) {
    if (event == null) {
        return false;
    }
    if (!event.name) {
        alert(missingNameError)
        return false
    }
    if (!event.eventDescription) {
        alert(missingDescError)
        return false
    }
    if (!event.eventLocation) {
        alert(missingLocationError)
        return false
    }
    if (!event.dateTime) {
        alert(missingDateTimeError)
        return false
    }
    //small fallback that probably won't happen but you never know!
    if (Object.keys(event).length != 5) {
        alert(missingFieldError);
        return false;
    }
    return true;
}

export async function searchByName(){
    var request = new XMLHttpRequest();
    var url = "https://sacglorychurch.org:8080/api/events/search/title/";
    console.log("starting up!!!");

    request.open("GET", url + document.getElementById("nameBox").value.replace(" ", "%20"));
    console.log("making response!!!");
    request.send();
    console.log("sending response!!!");

    request.onload = async () => {
        if (request.status === 200) {
            var objectText = JSON.parse(request.response);
            console.log(objectText);

            // Clear previous results
            document.getElementById("resultsField").innerHTML = "";
            document.getElementById("editField").hidden = true;
            document.getElementById("previewField").hidden = true;

            for (let i = 0; i < objectText.length; i++) {
                var result = objectText[i];

                var title = document.createElement("h4");
                title.innerText = result.name;

                var description = document.createElement("p");
                description.innerText = result.eventDescription;

                var location = document.createElement("p");
                location.innerText = result.eventLocation;

                var dateTime = document.createElement("p");
                var printDate = new Date(result.dateTime).toLocaleString();
                dateTime.innerText = "Date and Time: " + printDate;

                var button = document.createElement("button");
                button.innerText = "Edit"
                button.id = "Button_"+i;
                button.dataset.eventId = result.id;
                button.addEventListener('click', editEventByEvent)

                document.getElementById("resultsField").appendChild(title);
                document.getElementById("resultsField").appendChild(description);
                document.getElementById("resultsField").appendChild(dateTime);
                document.getElementById("resultsField").appendChild(button);

            }

            // Show Results
            document.getElementById("resultsField").hidden = false;
        } else if(request.status === 404){
                if(request.code === 1){
                    alert("No results. Try searching for something else");
                }else{
                    alert("Something went wrong. Try again later!")
                    console.log(`error ${request.status}`)
                    reject({
                        status: request.status,
                        statusText: "error"
                    })
                }
        }else{
            alert("Something went wrong. Try again later!");
            console.log(`error ${request.status}`);
            console.log("Hahahaha");
        }
    }
}

async function editEventByEvent(e){
    e.preventDefault();
    console.log(e.target.dataset.eventId);
    console.log("Editing Event!");
    document.getElementById("resultsField").hidden = true;

    ValEvent = await searchById(e.target.dataset.eventId); 
   
    console.log("showing edit field!")

    document.getElementById("editNameBox").value = ValEvent.name; 
    console.log("namebox")
    document.getElementById("editDescriptionBox").value = ValEvent.eventDescription;
    console.log("description")
    document.getElementById("editEventDateTime").value = ValEvent.dateTime;
    console.log("eventdate")
    document.getElementById("editLocationBox").value = ValEvent.eventLocation;
    console.log("loc")
    
    document.getElementById("editField").hidden = false;
    
}

async function searchById(id){
    return new Promise(function (resolve, reject) {
        var request = new XMLHttpRequest();
        var url = "https://sacglorychurch.org:8080/api/events/get/";

        request.open("GET", (url + id));
        request.send();
        request.onload = ()=>{
            if(request.status === 200){
                var result = JSON.parse(request.response);
                console.log(result);
                resolve(result);
            }else if(request.status === 404){
                if(request.code === 1){
                    alert("No results. Try searching for something else");
                }else{
                    alert("Something went wrong. Try again later!")
                    console.log(`error ${request.status}`)
                    reject({
                        status: request.status,
                        statusText: "error"
                    })
                }
            }
        }
    });
}

export function deleteEventConfirmation(accessToken){
    if(window.confirm(eventDeleteConfirmation)){
       deleteEvent(accessToken) 
    }
}

async function deleteEvent(accessToken){
    var request = new XMLHttpRequest();
    var url = "https://sacglorychurch.org:8080/api/events/delete";

    request.open("DELETE", (url));
    request.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    request.setRequestHeader("Authorization", "Bearer " + accessToken);
    request.send(JSON.stringify(ValEvent));
    request.onload = () => {
        if (request.status === 200) {
            alert(eventDeleted);
        } else if (request.status === 500) {
            var requestResult = JSON.parse(request.response);
            console.log(requestResult);
            if(requestResult.code == 3){
                console.log(requestResult.message)
                alert(invalidInputError)
            }            
            if(requestResult.code == 4){
                console.log(requestResult.message)
                alert(transactionFailError)
            }
        } else if (request.status === 404) {
            alert("Something went REALLY wrong. Try again later, maybe?")
        }
    }
}
