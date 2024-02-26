import { getAccessToken } from "./authTokenUtil.js";

const missingFieldError = "You seem to be missing a field. Please supply all data."
const missingNameError = "Missing Sermon Name"
const missingLinkError = "Missing Youtube Link"
const badLinkError = "Your Youtube link is invalid"
const missingDescError = "Missing Sermon Description"
const missingDateTimeError = "Missing Date/Time"
const invalidInputError = "The sermon you tried to add is invalid. Try something else"
const transactionFailError = "The server could not complete the add. Try again later"
const sermonAdded = "Sermon Added!"

var ValSermon;

export function createPreview() {
    const sermon = createSermon();
    if (!validateSermon(sermon)) {
        return;
    }
    showResults(sermon);
    ValSermon = sermon;
}


function createSermon() {
    var dateTime = document.getElementById("sermonDateTime").value
    const sermon = {
        "name": document.getElementById("nameBox").value,
        "description": document.getElementById("descriptionBox").value,
        "youtubeLink": document.getElementById("youtubeLinkBox").value,
        "dateTime": dateTime
    }
    console.log(sermon)
    return sermon;
}

function showResults(sermon) {
    document.getElementById("resultTitle").innerText = sermon.name;
    document.getElementById("resultDescription").innerText = sermon.description;
    document.getElementById("video").src = sermon.youtubeLink.replace("watch?v=", "embed/");

    let printDate = new Date(Date.parse(sermon.dateTime)) 
    document.getElementById("resultDateTime").innerText = printDate.toDateString() + ", " + printDate.toLocaleTimeString();

    document.getElementById("resultsField").hidden = false;
    document.getElementById("video").hidden = false;
}

export async function addSermon(accessToken){
    var request = new XMLHttpRequest();
    var url = "http://localhost:8080/api/sermons/create";

    request.open("POST", (url));
    request.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    request.setRequestHeader("Authorization", "Bearer " + accessToken);
    request.send(JSON.stringify(ValSermon));
    request.onload = () => {
        if (request.status === 201) {
            var requestResult = JSON.parse(request.response);
            console.log(requestResult);
            showResults(requestResult);
            alert(sermonAdded);
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

function validateSermon(sermon) {
    const youtubeRegex = new RegExp('^((?:https?:)?\\\/\\\/)?((?:www|m)\\.)?((?:youtube(-nocookie)?\\.com|youtu.be))(\\\/(?:[\\w\\-]+\\?v=|embed\\\/|v\\\/)?)([\\w\\-]+)(\\S+)?$', 'img');
    if (sermon == null) {
        return false;
    }
    if (!sermon.name) {
        alert(missingNameError)
        return false
    }
    if (!sermon.description) {
        alert(missingDescError)
        return false
    }
    if (!sermon.youtubeLink) {
        alert(missingLinkError)
        return false
    }
    if(!youtubeRegex.test(sermon.youtubeLink)){
        alert(badLinkError)
        return false
    }
    if (!sermon.dateTime) {
        alert(missingDateTimeError)
        return false
    }
    //small fallback that probably won't happen but you never know!
    if (Object.keys(sermon).length != 4) {
        alert(missingFieldError);
        return false;
    }
    return true;
}

