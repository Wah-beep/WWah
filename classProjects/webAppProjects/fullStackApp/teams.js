/**
 * Wah Saw Tamalar
 * teams.js
 * Get teamData from database
 * Print teams' names
 * Dispaly teams' infos
 * Get players count from each team
 */

// Team data
let teamData = [];

$(document).ready(main);

// main
function main() {
    
    // Call getTeamData
    getTeamData();

    // Call countPlayers
    countPlayers();

    $("#playerNav").on("click", function(){

        // Opens the second page
        window.open("players.html");
    });
}

// Get counts of players from each team
function countPlayers() {

    // Get
    $.ajax({
        url: "/api/getPlayersCount",
        method: "GET",
        dataType: "json",
        success: function(data) {
            console.log(data);
            data.forEach(team => {
                console.log(`${team.teamName} has ${team.playerCount} players`);
            });
        },
        error: function(err) {
            console.log(err);
        }
    });
}

/**
 * getTeamData
 * Get data from database
 */
function getTeamData() {

    // Get team data from mysql and storing into the array
    $.ajax({
        url: "/api/teams",
        method: "GET",
        dataType: "json",
        success: function(teams) {
            teamData = teams;
            printTeamName();
            console.log(teamData);
        },
        error: function(err) {
            console.log("Error: " + err);
        }
    });
}

/**
 * printTeamName
 * Print teams' names
 */
function printTeamName() {

    // Empty string
    let letPrint = "";

    // Get team name from teamData and add li
    for (let each of teamData) {
        var teamName = each.name;
        letPrint += "<li>" + teamName + "</li>";
    }

    let $wherePrint = $("#teamList");
    $wherePrint.html(letPrint);

    // Click event
    $("#teamList").on("click", "li", displayTeamInfo);

}

/**
 * displayTeamInfo
 * Display each teams' infos
 */
function displayTeamInfo() {
    
    var nameTeam = this.textContent;

    for (let each of teamData) {
        if (each.name === nameTeam) {

            // Store to localStorage to use it in second page
            localStorage.setItem("currentTeam", each.id);
            localStorage.setItem("currentTeamImgPath", each.image_path);

            // Get spans
            var $spanTeamStadium = $("#teamStadium");
            var $spanTeamLocation = $("#teamLocation");
            var $spanTeamFounded = $("#teamFounded");

            // Set spans
            $spanTeamStadium.text(each.stadium);
            $spanTeamLocation.text(each.location);
            $spanTeamFounded.text(each.founded.split("T")[0]);

            // Img
            $("#teamImage").attr("src", each.image_path);
        }
    }
}


