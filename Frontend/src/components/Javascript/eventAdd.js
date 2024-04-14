const missingFieldError = "You seem to be missing a field. Please supply all data."
const missingNameError = "Missing Event Name"
const missingDescError = "Missing Event Description"
const missingDateTimeError = "Missing Date/Time"
const missingLocationError = "Missing Location"
const invalidInputError = "The event you tried to add is invalid. Try something else"
const transactionFailError = "The server could not complete the add. Try again later"
const eventAdded = "Event Added!"

var ValEvent;

export function createPreview() {
    const event = createEvent();
    if (!validateEvent(event)) {
        return;
    }
    showResults(event);
    ValEvent = event;
}


function createEvent() {
    var dateTime = document.getElementById("eventDateTime").value
    const event = {
        "name": document.getElementById("nameBox").value,
        "eventLocation": document.getElementById("locationBox").value,
        "eventDescription": document.getElementById("descriptionBox").value,
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
    document.getElementById("resultsField").hidden = false;
}

export async function addEvent(accessToken){
    console.log(ValEvent)
    var request = new XMLHttpRequest();
    var url = "http://sacglorychurch.org:8080/api/events/create";

    request.open("POST", (url));
    request.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    request.setRequestHeader("Authorization", "Bearer " + accessToken);
    request.send(JSON.stringify(ValEvent));
    request.onload = () => {
        if (request.status === 201) {
            var requestResult = JSON.parse(request.response);
            console.log(requestResult);
            showResults(requestResult);
            alert(eventAdded);
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
    if (!event.eventLocation) {
        alert(missingLocationError)
        return false
    }
    if (!event.eventDescription) {
        alert(missingDescError)
        return false
    }
    if (!event.dateTime) {
        alert(missingDateTimeError)
        return false
    }
    //small fallback that probably won't happen but you never know!
    if (Object.keys(event).length != 4) {
        alert(missingFieldError);
        return false;
    }
    return true;
}
