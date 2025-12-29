-- sportTeam.sql
-- This file contains the sql commands used to create and fill up
-- the wahSportTeam database.

CREATE DATABASE wahSportTeam;
USE wahSportTeam;

-- Create a table with an integer id for each team
CREATE TABLE team (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(128) NOT NULL,
    stadium VARCHAR(128),
    location VARCHAR(128),
    founded DATE
);

-- Create a table with an integer id for each player
CREATE TABLE player (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(128),
    age INTEGER,
    height CHAR(6),
    nationality VARCHAR(128),
    team_id INTEGER,

    FOREIGN KEY (team_id) REFERENCES team(id)
);

-- Insert a team
INSERT INTO team (name, stadium, location, founded)
VALUES ('Chelsea', 'Stamford Bridge', 'London', '1905-03-10');

-- Insert player
-- (SELECT id FROM team WHERE name = 'Chelsea'), if team_id is unknown
INSERT INTO player (name, age, height, nationality, team_id)
VALUES ('Cole Palmer', 23, '6''1"', 'English', 1),
    ('Alejandro Garnacho', 21, '5''1"', 'Argentine', 1),
    ('Joao Pedro', 24, '6''0"', 'Brazilian', 1),
    ('Estevao Willian', 18, '5''10"', 'Brazilian', 1),
    ('Enzo Fernandez', 24, '5''10"', 'Argentine', 1),
    ('Moises Caicedo', 24, '5''10"', 'Ecuadorian', 1);

-- Insert a team
INSERT INTO team (name, stadium, location, founded)
VALUES ('Minnesota Timberwolves', 'Target Center', 'Minnesota', '1989-11-03');

-- Insert players
INSERT INTO player (name, age, height, nationality, team_id)
VALUES ('Anthony Edwards', 24, '6''4"', 'American', 2),
    ('Julius Randle', 31, '6''9"', 'American', 2),
    ('Rudy Gobert', 33, '7''1"', 'French', 2);

-- Altering team table
ALTER TABLE team
ADD COLUMN image_path VARCHAR(255);

-- Update
UPDATE team
SET image_path = 'images/teams/chelsea.png'
WHERE name = 'Chelsea';

UPDATE team
SET image_path = 'images/teams/timberwolves.png'
WHERE name = 'Minnesota Timberwolves';

-- Altering player table
ALTER TABLE player
ADD COLUMN image_path VARCHAR(255);

-- Update players
UPDATE player
SET image_path = 'images/players/alejandrogarnacho.png'
WHERE name = 'Alejandro Garnacho';
