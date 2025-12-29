/**
 * Wah Saw Tamalar
 * index.js
 * Storing the user/player input from the textbox to localStorage
 * Creating a currentUser to track the right user/player
 */
$(main);

// main
function main() {
    $("#enterBtn").click(storeInfo);
}

// storeInfo
function storeInfo() {

    console.log("Enter button is clicked.");

    // Get user/player input from the textbox
    let user = $("#username").val().trim();
    if (!user) return;

    // Get user/player
    let players = JSON.parse(localStorage.getItem("players")) || {};

    // New user/player
    if (!players[user]) {
        players[user] = {
            games: 0,
            bestScore: null
        };
    }

    // Store in locatStorage
    localStorage.setItem("players", JSON.stringify(players));

    // Store the currentUser
    localStorage.setItem("currentUser", user);

    // Opens the second page
    window.open("game.html");
}