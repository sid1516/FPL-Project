# FPL-Project
Full Stack Application simulating an FPL Season using Java, Spring, and React.js

Description: As an avid player of the Fantasy Premier League draft with my friends, I wanted to make my first full-stack project to be related to these topics. This project will allow users to set up their league by entering the league information and team name. The user will also set the number of CPUs that they would like to verse. From there, they can draft their team with a money limit. The CPU teams then draft their players in a snake draft. This project is also partially an experiment to see which version of Fantasy Premier League would result in higher scoring teams. A 38-game FPL season will simulate, and the results will be displayed.

# Backend/Database Setup

1. Download MySQL Community Server and MySQL Workbench.
2. Setup tables in MySQL Workbench using the sql file found in ./back-end/target/classes/SQL-Queries
3. Download eclipse and run the backend portion of the project in eclipse or any other IDE of your choice. Ensure you are using JDK 8 or higher and if not, download JDK 8 from oracle archive.
4. Import project as a maven project and run the spring boot application.
5. Import postman collection provided in the directory.
6. Populate the player tables using the player.csv file and POST method "File-Upload" in the Player directory. Include csv as form-data with key "file."

# Frontend Setup
1. Put the front-end portion of the project into your favorite editor.
2. Run npm-install to install all node_modules.
3. Run npm-start.

# Running Project
Ensure that both the front-end is running and the backend is running using the instructions provided above otherwise it will not work.
