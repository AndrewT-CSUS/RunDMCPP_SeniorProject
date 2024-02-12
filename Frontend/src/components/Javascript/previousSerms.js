const SearchByName = "Search By Name Results"
const SearchByDate = "Search By Date Results"
const NoSearchTerm = "Please supply a search term!"

export async function searchByName(){
    var request = new XMLHttpRequest();
    var url = "http://localhost:8080/api/sermons/search/title/";
    var name = document.getElementById("searchBox").value.replace(" ", "%20");

    if(!name){
        alert(NoSearchTerm)
        return
    }

    request.open("GET", url + name);
    request.send();

    request.onload = async () => {
        if (request.status === 200) {
            showResults(request, true);
        } else {
            alert("Something went wrong. Try again later!");
            console.log(`error ${request.status}`);
        }
    }
}

export async function searchByDate(){
    var request = new XMLHttpRequest();
    var url = "http://localhost:8080/api/sermons/search/date?";
    var startDate = document.getElementById("startDateBox").value;
    var endDate = document.getElementById("endDateBox").value;
    
    if(!startDate && !endDate){
        alert(NoSearchTerm)
        return
    }
    if(startDate){
        url += "startDate=" + startDate;
    }else{
        url += "startDate=0001";
    }
    if(endDate){
        if(startDate){
            url += "&";
        }
        url += "endDate=" + endDate;
    }else{
        if(startDate){
            url += "&";
        }
        url += "endDate=9999";
    }

    request.open("GET", url);
    request.send();

    request.onload = async () => {
        if (request.status === 200) {
            showResults(request, false);
        } else {
            alert("Something went wrong. Try again later!");
            console.log(`error ${request.status}`);
        }
    }
}

function showResults(request, searchByName){
    var objectText = JSON.parse(request.response);
    console.log(objectText);

    // Clear previous results
    document.getElementById("resultsField").innerHTML = "";

    var legend = document.createElement("legend");
    if(searchByName == true){
        legend.innerText = SearchByName;
    }else{
        legend.innerText = SearchByDate;
    }
    document.getElementById("resultsField").appendChild(legend);

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
    

}
