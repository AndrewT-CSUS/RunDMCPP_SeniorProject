const missingFieldError = "You seem to be missing a field. Please supply all data."
const missingNameError = "Missing Sermon Name"
const missingLinkError = "Missing Youtube Link"
const badLinkError = "Your Youtube link is invalid"
const missingDescError = "Missing Sermon Description"
const missingDateTimeError = "Missing Date/Time"
const invalidInputError = "The sermon you tried to add is invald. Try something else"
const transactionFailError = "The server could not complete the add. Try again later"
const sermonUpdated = "Sermon Edited!"
const sermonDeleteConfirmation = "Warning! You are about to delete the selected sermon.\nThis can not be undone, but the sermon can be re-created manually.\nAre you sure you want to do this?" //Bad, reword later?
const sermonDeleted = "Sermon Deleted!"
const NoSearchResults = "No results found!"

var ValSermon;

export function createPreview() {
    const sermon = createSermon();
    sermon.id = ValSermon.id;
    if (!validateSermon(sermon)) {
        return;
    }
    showResults(sermon);
    ValSermon = sermon;
}


function createSermon() {
    var dateTime = document.getElementById("editSermonDateTime").value
    const sermon = {
        "name": document.getElementById("editNameBox").value,
        "description": document.getElementById("editDescriptionBox").value,
        "youtubeLink": document.getElementById("editYoutubeLinkBox").value,
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

    document.getElementById("previewField").hidden = false;
    document.getElementById("video").hidden = false;
}

export async function editSermon(){
    var request = new XMLHttpRequest();
    var url = "http://localhost:8080/api/sermons/edit";

    request.open("PUT", (url));
    request.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    request.send(JSON.stringify(ValSermon));
    request.onload = () => {
        if (request.status === 200) {
            var requestResult = JSON.parse(request.response);
            console.log(requestResult);
            showResults(requestResult);
            alert(sermonUpdated);
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
    if (Object.keys(sermon).length != 5) {
        alert(missingFieldError);
        return false;
    }
    return true;
}

export async function searchByName(){
    var request = new XMLHttpRequest();
    var url = "http://localhost:8080/api/sermons/search/title/";

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
                title.innerText = result.name;

                var description = document.createElement("p");
                description.innerText = result.description;

                var dateTime = document.createElement("p");
                var printDate = new Date(result.dateTime).toLocaleString();
                dateTime.innerText = "Date and Time: " + printDate;

                var video = document.createElement("iframe");
                video.src = result.youtubeLink.replace("watch?v=", "embed/");
                video.width = 420;
                video.height = 315;
                video.hidden = false;

                var button = document.createElement("button");
                button.innerText = "Edit"
                button.id = "Button_"+i;
                button.dataset.sermonId = result.id;
                button.addEventListener('click', editSermonBySermon)

                document.getElementById("resultsField").appendChild(title);
                document.getElementById("resultsField").appendChild(description);
                document.getElementById("resultsField").appendChild(dateTime);
                document.getElementById("resultsField").appendChild(video);
                document.getElementById("resultsField").appendChild(button);

            }

            // Show Results
            document.getElementById("resultsField").hidden = false;

        } else if (request.status === 404) {
            alert(NoSearchResults);
        } else {
            alert("Something went wrong. Try again later!");
            console.log(`error ${request.status}`);
        }
    }
}

async function editSermonBySermon(e){
    e.preventDefault();
    console.log(e.target.dataset.sermonId);
    console.log("Editing Sermon!");
    document.getElementById("resultsField").hidden = true;

    ValSermon = await searchById(e.target.dataset.sermonId); 
   
    console.log("showing edit field!")

    document.getElementById("editNameBox").value = ValSermon.name; 
    document.getElementById("editDescriptionBox").value = ValSermon.description;
    document.getElementById("editSermonDateTime").value = ValSermon.dateTime;
    document.getElementById("editYoutubeLinkBox").value = ValSermon.youtubeLink
    document.getElementById("editYoutubeEmbed").src = ValSermon.youtubeLink.replace("watch?v=", "embed/");
    
    document.getElementById("editField").hidden = false;
    
}

async function searchById(id){
    return new Promise(function (resolve, reject) {
        var request = new XMLHttpRequest();
        var url = "http://localhost:8080/api/sermons/get/";

        request.open("GET", (url + id));
        request.send();
        request.onload = ()=>{
            if(request.status === 200){
                var result = JSON.parse(request.response);
                console.log(result);
                resolve(result);
            } else if (request.status === 404){
                alert(NoSearchResults);
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

export function deleteSermonConfirmation(){
    if(window.confirm(sermonDeleteConfirmation)){
       deleteSermon() 
    }
}

async function deleteSermon(){
    var request = new XMLHttpRequest();
    var url = "http://localhost:8080/api/sermons/delete";

    request.open("DELETE", (url));
    request.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    request.send(JSON.stringify(ValSermon));
    request.onload = () => {
        if (request.status === 200) {
            alert(sermonDeleted);
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
