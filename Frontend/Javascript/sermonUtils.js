async function fetchAsync () {
    //  alert('calling!')
    let response = await fetch("http://localhost:8080/api/sermons/get/Uhhhhh");
    let data = await response.json();
    console.log(data);
    //  alert(data);

    return data;
}

async function addSermon(){
    var sermon = {"name":null, "description":null, "youtubeLink":null, "dateTime":null}

    console.log(sermon);
    var sermonName = nameBox.value; 
    if( sermonName != null && sermonName != "" && sermonName != "Sermon Name"){
        sermon.name = sermonName;
    }
    var sermonDesc = descriptionBox.value; 
    if( sermonDesc != null && sermonDesc != "" && sermonDesc != "Sermon Description"){
        sermon.description = sermonDesc;
    }
    var sermonLink = youtubeLinkBox.value; 
    if( sermonLink != null && sermonLink != "" && sermonLink != "YouTube Link"){
        sermon.youtubeLink = sermonLink;
    }
    var sermonDateTime = dateTimeBox.value;
    if( sermonDateTime != null && sermonDateTime != "" && sermonDateTime != "Date and Time"){
        sermon.dateTime = sermonDateTime;
    }

    var request = new XMLHttpRequest();
    var url = "http://localhost:8080/api/sermons/create";

    request.open("POST", (url));
    request.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    request.send(JSON.stringify(sermon));
    request.onload = ()=>{
        if(request.status === 201){
            var objectText = JSON.parse(request.response);
            console.log(objectText);

            //Show Results
            document.getElementById("resultTitle").innerText = objectText.name;
            document.getElementById("resultDescription").innerText = objectText.description;
            document.getElementById("resultDateTime").innerText = objectText.dateTime;
            document.getElementById("video").src = objectText.youtubeLink.replace("watch?v=", "embed/");

            document.getElementById("resultsField").hidden = false;
            document.getElementById("video").hidden = false;
        }else if(request.status === 500){
         
        }else if(request.status === 404){
            alert("Something went REALLY wrong. Try again later, maybe?") 
        }
    }


}

async function searchById(){
    var request = new XMLHttpRequest();
    var url = "http://localhost:8080/api/sermons/get/";

    request.open("GET", (url + idBox.value));
    request.send();
    request.onload = ()=>{
        if(request.status === 200){
            var objectText = JSON.parse(request.response);
            console.log(objectText);
            //Show Results
            document.getElementById("resultsField").hidden = false;
            document.getElementById("resultTitle").innerText = objectText.name;
            document.getElementById("resultDescription").innerText = objectText.description;
            document.getElementById("resultDateTime").innerText = objectText.dateTime;
            document.getElementById("video").src = objectText.youtubeLink.replace("watch?v=", "embed/");

            document.getElementById("video").hidden = false;
        }else{
            alert("Something went wrong. Try again later!")
            console.log(`error ${request.status}`)
        }
    }
}

async function searchByName() {
    var request = new XMLHttpRequest();
    var url = "http://localhost:8080/api/sermons/search/title/";

    request.open("GET", url + nameBox.value.replace(" ", "%20"));
    request.send();

    request.onload = async () => {
        if (request.status === 200) {
            var objectText = JSON.parse(request.response);
            console.log(objectText);

            // Clear previous results
            document.getElementById("resultsField").innerHTML = "";

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

                document.getElementById("resultsField").appendChild(title);
                document.getElementById("resultsField").appendChild(description);
                document.getElementById("resultsField").appendChild(dateTime);
                document.getElementById("resultsField").appendChild(video);
            }

            // Show Results
            document.getElementById("resultsField").hidden = false;
        } else {
            alert("Something went wrong. Try again later!");
            console.log(`error ${request.status}`);
        }
    };
}