// server.js
// An express server app running on the web server
var express = require("express");
var app = express();

// Serve any static files from the public dir
app.use(express.static("public"));

// mysql object
var mysql = require("mysql2");

// Create endpoint fetching
// for team
app.get("/api/teams", getTeamData);
function getTeamData(req, res){

    // Parameter to the query
    console.log("getTeamData called");

    // Connection to MySQL
    var conn = mysql.createConnection({
        host: "127.0.0.1",
        user: "sawtamw",
        password: "$311wa911",
        database: "wahSportTeam"
    });

    // Calling the connect function
    conn.connect(function(err) {
        if(err){
            console.log("Error connecting: " + err)
        }
        else {
            console.log("Connection established");
        }
    });

    // sql query
    var sql = "SELECT * FROM team";

    // Query returns a result
    function selectTeam(err, rows) {

        console.log("selectTeam called");

        if (err) {
            console.log("Error");
        }
        else {
            res.send(JSON.stringify(rows));
        }
    }

    // Statement
    conn.query(sql, selectTeam);

    // Close connection
    conn.end();
}

// Create endpoint fetching
// for player
app.get("/api/players", getPlayerData);
function getPlayerData(req, res){

    let teamId = req.query.teamId;

    // Parameter to the query
    console.log("getPlayerData called");

    // Connection to MySQL
    var conn = mysql.createConnection({
        host: "127.0.0.1",
        user: "sawtamw",
        password: "$311wa911",
        database: "wahSportTeam"
    });

    // Calling the connect function
    conn.connect(function(err) {
        if(err){
            console.log("Error connecting: " + err)
        }
        else {
            console.log("Connection established");
        }
    });

    // sql query
    var sql = "SELECT * FROM player WHERE team_id = ?";

    // Query returns a result
    function selectPlayer(err, rows) {

        console.log("selectPlayer called");

        if (err) {
            console.log("Error");
        }
        else {
            res.send(JSON.stringify(rows));
        }
    }

    // Statement
    conn.query(sql, [teamId], selectPlayer);

    // Close connection
    conn.end();
}

// Create endpoint
// Add a new player
app.post("/api/addPlayer", addNewPlayer);
function addNewPlayer(req, res) {

    console.log("addNewPlayer called");

    // Extract data from request body
    const {name, age, height, nationality, team_id, image_path} = req.body;

    // Connection to MySQL
    var conn = mysql.createConnection({
        host: "127.0.0.1",
        user: "sawtamw",
        password: "$311wa911",
        database: "wahSportTeam"
    });

    // Calling the connect function
    conn.connect(function(err) {
        if(err){
            console.log("Error connecting: " + err)
        }
        else {
            console.log("Connection established");
        }
    });

    // sql statement
    var sql = "INSERT INTO player (name, age, height, nationality, team_id, image_path) VALUES (?, ?, ?, ?, ?, ?)";

    conn.query(sql, [name, age, height, nationality, team_id, image_path], (err, result) => {
        if (err) {
            console.log("Failed to add player: " + err);
        }
        else {
            console.log("Player added!");
        }
    });

    // Close connection
    conn.end();
}

// Create endpoint
// Get players count on a team
app.get("/api/getPlayersCount", getPlayersCount);
function getPlayersCount(req, res) {

    console.log("getPlayersCount called");

    // Connection to MySQL
    var conn = mysql.createConnection({
        host: "127.0.0.1",
        user: "sawtamw",
        password: "$311wa911",
        database: "wahSportTeam"
    });

    // sql statement
    var sql = `
        SELECT t.name AS teamName, COUNT(p.id) AS playerCount
        FROM team t
        LEFT JOIN player p on p.team_id = t.id
        GROUP BY t.id, t.name
        ORDER BY t.name
    `;

    conn.query(sql, (err, results) => {
        if (err) {
            console.log("Failed to count players!");
        }
        else {
            res.json(results);
        }
    });

    // Close connection
    conn.end();
}

// Server port 6130
app.listen(6130);