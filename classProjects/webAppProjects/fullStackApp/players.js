/**
 * Wah Saw Tamalar
 * players.js
 * Get playerData from my databse
 * Print players name
 * Get currentTeam, and currentTeamImgPath from localStorage
 * Display players' info
 */

// Player data
let playerData = [];

$(document).ready(main);

// main
function main() {

    // Call getPlayerData
    getPlayerData();
}

/**
 * getPlayerData
 * Get players' data from database
 */
function getPlayerData() {

    // Get currentTeam key from localStorage
    let teamId = localStorage.getItem("currentTeam");
    if (!teamId) {
        console.log("No team selected!");
        return;
    }

    // Get player data from mysql and storing into the array
    $.ajax({
        url: "/api/players",
        method: "GET",
        data: {teamId: teamId},
        dataType: "json",
        success: function(players) {
            playerData = players;
            printPlayerName();
            console.log(playerData);
        },
        error: function(err) {
            console.log("Error: " + err);
        }
    });
}

/**
 * printPlayerName
 * Print players' name in the list
 */
function printPlayerName() {

    // Empty string
    let letPrint = "";

    // Get playerName from playerData and add li
    for (let each of playerData) {
        var playerName = each.name;
        letPrint += "<li>" + playerName + "</li>";
    }

    // Display players' name in the list
    let $wherePrint = $("#playerList");
    $wherePrint.html(letPrint);

    // Display team logo
    let teamImageP = localStorage.getItem("currentTeamImgPath");
    $("#teamImageP").attr("src", teamImageP);
    $("#playerImage").attr("src", teamImageP);

    // Click event
    $("#playerList").on("click", "li", displayPlayerInfo);

}

/**
 * displayPlayerInfo
 * Display players' infos
 */
function displayPlayerInfo() {
    
    var namePlayer = this.textContent;

    for (let each of playerData) {
        if (each.name === namePlayer) {

            // Get spans
            var $spanPlayerName = $("#playerName");
            var $spanPlayerAge = $("#playerAge");
            var $spanPlayerHeight = $("#playerHeight");
            var $spanPlayerNationality = $("#playerNationality");

            // Add to spans
            $spanPlayerName.text(each.name);
            $spanPlayerAge.text(each.age);
            $spanPlayerHeight.text(each.height);
            $spanPlayerNationality.text(each.nationality);

            // Set the image of the player's select
            $("#playerImage").attr("src", each.image_path);
        }
    }
}