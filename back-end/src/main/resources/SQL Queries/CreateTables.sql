use fpl;

create table Player(
id int PRIMARY KEY AUTO_INCREMENT,
player_key char(36),
first_name varchar(64),
last_name varchar(64),
position varchar(30),
price FLOAT,
prev_season_points int,
club_team varchar(255),
team_id int,
FOREIGN KEY(team_id)
REFERENCES team(id)
);

create table Team(
id int PRIMARY KEY AUTO_INCREMENT,
team_key char(36),
team_name varchar(255),
goalkeeper_count int,
defender_count int,
midfielder_count int,
forward_count int,
money_left FLOAT,
wins int,
draws int,
losses int,
standing_points int,
player_points int,
draft_position int,
league_id int,
FOREIGN KEY(league_id) REFERENCES League(id)
);

create table League(
id int PRIMARY KEY AUTO_INCREMENT,
league_name varchar(64),
league_description varchar(255),
league_key char(36)
);

create table Schedule(
id int PRIMARY KEY AUTO_INCREMENT,
schedule_key char(36),
gameweek int,
team1_id int,
team2_id int,
team1_score int,
team2_score int,
league_id int,
FOREIGN KEY(team1_id) REFERENCES Team(id),
FOREIGN KEY (team2_id) REFERENCES Team(id),
FOREIGN KEY (league_id) REFERENCES League(id)
);

select * from team;
select * from player;
select * from league;
delete from team;
delete from league;
drop table player;
drop table team;
drop table league;
drop table schedule;
