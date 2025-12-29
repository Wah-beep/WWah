/*
  ajaxstarter.js
  In-class exercise for using an open web API
  
  Name: Wah Saw Tamalar
  
*/

// Global Code:
window.addEventListener( "DOMContentLoaded", main);

// New one
function main() {
    //var zipcode = document.getElementById("zip").value;
    var xhr = new XMLHttpRequest();
    xhr.addEventListener("load", responseReceivedHandlerStuff);   
    xhr.open("GET", "	https://yesno.wtf/api");
    xhr.send();
}

function responseReceivedHandlerStuff() {
    if (this.status !== 200) {
        alert("Error making HTTP request");
        return;
    }

    var data = JSON.parse(this.response);
    
    var html = "";
    html += "<h1>Answer: " + data.answer.toUpperCase() + "<h1/>";
    html += "<img src='"+ data.image +"' alt = 'Anser image' width='auto'>";

    document.getElementById("displayStuff").innerHTML = html;
}
