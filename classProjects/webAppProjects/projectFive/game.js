/**
 * Wah Saw Tamalar
 * game.js
 * Display player's info
 * Storing player's info
 * Getting player's info
 * Get a secret number from server.js
 * Display hints for player's guesses
 * Process of player's guesses
 */
$(main);

// Global
let players, currentUser, record;
let secretNumber;
let guessList = [];

// main
function main() {

    // Call loadUser
    loadUser();

    // Call getSecretNumber
    getSecretNumber();

    // Event, when the guess button is click to guess
    // Call processGuess
    $("#guessBtn").click(processGuess);
}

// loadUser
function loadUser() {

    console.log("Game is loaded.");

    // Get infos
    players = JSON.parse(localStorage.getItem("players"));
    currentUser = localStorage.getItem("currentUser");
    record = players[currentUser];

    // Display infos
    $("#userName").text(currentUser);
    $("#userGames").text(record.games);
    $("#userBest").text(record.bestScore ?? "--");
}

// getSecretNumber
function getSecretNumber() {

    // Get a number generate from server.js
    $.get("/api/newNumber", function(data) {
        secretNumber = data.number;
        console.log("Secret number: ", secretNumber);
    });
}

// processGuess
function processGuess() {

    // Get player input
    let guess = parseInt($("#userInput").val());
    if (!guess) return;

    // Add to guessList to display player's guesses
    guessList.push(guess);
    $("#userGuess").text(guessList.join(", "));

    // Checking if player's guesses are correct
    if (guess === secretNumber) {

        // Dispaly if player is correct
        $("#userHint").text("You got it!");

        // To store guesses record
        finishGame(guessList.length);
    } 
    else if (guess < secretNumber) {

        // Give player hint to guess higher
        $("#userHint").text("Higher!");
    } 
    else {

        // Give player hint to guess lower
        $("#userHint").text("Lower!");
    }

    // Reset player's input
    $("#userInput").val("");
}

// finishGame, parameter tries
function finishGame(tries) {

    // Increment games
    record.games++;

    // Set new player best guesses
    if (record.bestScore === null || tries < record.bestScore) {
        record.bestScore = tries;
    }

    // Storing
    players[currentUser] = record;
    localStorage.setItem("players", JSON.stringify(players));

    // Display record
    $("#userGames").text(record.games);
    $("#userBest").text(record.bestScore);

    // If player want to play again
    setTimeout(() => {
        if (confirm("You got it in " + tries + " guesses! Play again?")) {

            // Reset
            restartGame();
            getSecretNumber();
        }
    });
}

// restartGame
function restartGame() {

    // Reset guessList, guess textbox, and hints
    guessList = [];
    $("#userGuess").text("");
    $("#userHint").text("");
}