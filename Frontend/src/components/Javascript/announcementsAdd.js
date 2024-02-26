const missingFieldError = "You seem to be missing a field. Please supply all data.";
const missingTitleError = "Missing Announcement Title";
const missingDescError = "Missing Announcement Description";
const invalidInputError = "The announcement you tried to add is invalid. Try something else";
const transactionFailError = "The server could not complete the add. Try again later";
const announcementAdded = "Announcement Added!";

var ValAnnouncement;

export function createPreview() {
    const announcement = createAnnouncement();
    if (!validateAnnouncement(announcement)) {
        return;
    }
    showResults(announcement);
    ValAnnouncement = announcement;
}

function createAnnouncement() {
    const announcement = {
        "title": document.getElementById("titleBox").value,
        "description": document.getElementById("descriptionBox").value,
    };
    console.log(announcement);
    return announcement;
}

function showResults(announcement) {
    document.getElementById("resultTitle").innerText = announcement.title;
    document.getElementById("resultDescription").innerText = announcement.description;

    document.getElementById("resultsField").hidden = false;
}

export async function addAnnouncement(accessToken) {
    var request = new XMLHttpRequest()
    var url = "http://localhost:8080/api/announcements/create";

    request.open("POST", (url))
    request.setRequestHeader("Content-Type", "application/json;charset=UTF-8")
    request.setRequestHeader("Authorization", "Bearer " + accessToken);
    request.send(JSON.stringify(ValAnnouncement));
    request.onload = () => {
        if (request.status === 201) {
            var requestResult = JSON.parse(request.response)
            console.log(requestResult)
            showResults(requestResult)
            alert(announcementAdded)
        } else if (request.status === 500) {
            var requestResult = JSON.parse(request.response)
            console.log(requestResult)
            if(requestResult.code == 3) {
                console.log(requestResult.message)
                alert(invalidInputError)
            }            
            if(requestResult.code == 4) {
                console.log(requestResult.message)
                alert(transactionFailError)
            }
        } else if (request.status === 404) {
            alert("Something went REALLY wrong. Try again later, maybe?")
        }
    }
}

function validateAnnouncement(announcement) {
    if (announcement == null) {
        return false;
    }
    if (!announcement.title) {
        alert(missingTitleError);
        return false;
    }
    if (!announcement.description) {
        alert(missingDescError);
        return false;
    }
    //small fallback that probably won't happen but you never know!
    if (Object.keys(announcement).length != 2) {
        alert(missingFieldError);
        return false;
    }
    return true;
}

export async function fetchRecentAnnouncements(){
    try {
        const response = await fetch(`http://localhost:8080/api/announcements/recent`);

        if(!response.ok){
            console.error(`Error: ${response.status}`);
            return null;
        }

        const data = await response.json();
        return data;
    } catch (error) {
        console.error("Error:", error);
        return null;
    }
}