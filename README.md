# Football World Cup ScoreBoard

This is a simple in-memory Java library to manage live football match scores.  
It supports starting, updating, finishing matches, and retrieving a sorted summary.

## Features
- Start a new match (initial score 0–0)
- Update match scores
- Finish matches
- Get summary sorted by total score and recency

## Test-Driven Development
The application was implemented using **TDD principles** and thoroughly covered with unit tests using **JUnit 5**.

## Tech Stack
- Java 17
- Maven
- JUnit 5

# Assumptions
ScoreBoard.updateScore() && ScoreBoard.finishGame() - HomeTeam and AwayTeam name and score will not be misplaced (swapped)
