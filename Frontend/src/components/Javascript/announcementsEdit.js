const missingFieldError = "You seem to be missing a field. Please supply all data."
const missingNameError = "Missing Announcement Name"
const missingDescError = "Missing Announcement Description"
const invalidInputError = "The announcement you tried to add is invald. Try something else"
const transactionFailError = "The server could not complete the add. Try again later"
const announcementUpdated = "Announcement Edited!"
const announcementDeleteConfirmation = "Warning! You are about to delete the selected announcement.\nThis can not be undone, but the announcement can be re-created manually.\nAre you sure you want to do this?"
const announcementDeleted = "Announcement Deleted!"

var ValAnnouncement;

export function createPreview() {
    const announcement = createAnnouncement();
    announcement.id = ValAnnouncement.id;
    if (!validateAnnouncement(announcement)) {
        return;
    }
    showResults(announcement);
    ValAnnouncement = announcement;
}


function createAnnouncement() {
    const announcement = {
        "title": document.getElementById("editNameBox").value,
        "description": document.getElementById("editDescriptionBox").value,
    }
    console.log(announcement)
    return announcement;
}

function showResults(announcement) {
    document.getElementById("resultTitle").innerText = announcement.title;
    document.getElementById("resultDescription").innerText = announcement.description;

    document.getElementById("previewField").hidden = false;
}

export async function editAnnouncement(accessToken){
    var request = new XMLHttpRequest();
    var url = "https://sacglorychurch.org:8080/api/announcements/edit";

    request.open("PUT", (url));
    request.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    request.setRequestHeader("Authorization", "Bearer " + accessToken);
    request.send(JSON.stringify(ValAnnouncement));
    request.onload = () => {
        if (request.status === 200) {
            var requestResult = JSON.parse(request.response);
            console.log(requestResult);
            showResults(requestResult);
            alert(announcementUpdated);
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

function validateAnnouncement(announcement) {
    if (announcement == null) {
        return false;
    }
    if (!announcement.title) {
        alert(missingNameError)
        return false
    }
    if (!announcement.description) {
        alert(missingDescError)
        return false
    }
    //small fallback that probably won't happen but you never know!
    if (Object.keys(announcement).length != 3) {
        alert(missingFieldError);
        return false;
    }
    return true;
}

export async function searchByName(){
    var request = new XMLHttpRequest();
    var url = "https://sacglorychurch.org:8080/api/announcements/search/title/";

    request.open("GET", url + document.getElementById("nameBox").value.replace(" ", "%20"));
    request.send();

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
                title.innerText = result.title;

                var description = document.createElement("p");
                description.innerText = result.description;

                var location = document.createElement("p");
                location.innerText = result.announcementLocation;

                var dateTime = document.createElement("p");
                var printDate = new Date(result.dateTime).toLocaleString();
                dateTime.innerText = "Date and Time: " + printDate;

                var button = document.createElement("button");
                button.innerText = "Edit"
                button.id = "Button_"+i;
                button.dataset.announcementId = result.id;
                button.addEventListener('click', editAnnouncementByAnnouncement)

                document.getElementById("resultsField").appendChild(title);
                document.getElementById("resultsField").appendChild(description);
                document.getElementById("resultsField").appendChild(button);

            }

            // Show Results
            document.getElementById("resultsField").hidden = false;

        }else if(request.status === 404){
            if(request.code === 1){
                alert("No results. Try searching for something else");
            }else {
                alert("Something went wrong. Try again later!");
                console.log(`error ${request.status}`);

            }        
        }
    }
}

async function editAnnouncementByAnnouncement(e){
    e.preventDefault();
    console.log(e.target.dataset.announcementId);
    document.getElementById("resultsField").hidden = true;

    ValAnnouncement = await searchById(e.target.dataset.announcementId); 

    document.getElementById("editNameBox").value = ValAnnouncement.title; 
    document.getElementById("editDescriptionBox").value = ValAnnouncement.description;

    document.getElementById("editField").hidden = false;

}

async function searchById(id){
    return new Promise(function (resolve, reject) {
        var request = new XMLHttpRequest();
        var url = "https://sacglorychurch.org:8080/api/announcements/get/";

        request.open("GET", (url + id));
        request.send();
        request.onload = ()=>{
            if(request.status === 200){
                var result = JSON.parse(request.response);
                console.log(result);
                resolve(result);
            }else{
                alert("Something went wrong. Try again later!")
                console.log(`error ${request.status}`)
                reject({
                    status: request.status,
                    statusText: "error"
                })
            }
        }
    });
}

export function deleteAnnouncementConfirmation(accessToken){
    if(window.confirm(announcementDeleteConfirmation)){
       deleteAnnouncement(accessToken) 
    }
}

async function deleteAnnouncement(accessToken){
    var request = new XMLHttpRequest();
    var url = "https://sacglorychurch.org:8080/api/announcements/delete";

    request.open("DELETE", (url));
    request.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    request.setRequestHeader("Authorization", "Bearer " + accessToken);
    request.send(JSON.stringify(ValAnnouncement));
    request.onload = () => {
        if (request.status === 200) {
            alert(announcementDeleted);
        } else if (request.status === 500) {
            var requestResult = JSON.parse(request.response);
            console.log(requestResult);
            if(requestResult.code === 3){
                console.log(requestResult.message)
                alert(invalidInputError)
            }            
            if(requestResult.code === 4){
                console.log(requestResult.message)
                alert(transactionFailError)
            }
        } else if (request.status === 404) {
            alert("Something went REALLY wrong. Try again later, maybe?")
        }
    }
}
